package io.camunda.demo.process_payments;

import java.util.Map;

import io.camunda.zeebe.client.api.search.filter.ProcessInstanceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Deployment(resources = "classpath:typical_process.bpmn")
public class ProcessPaymentsApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessPaymentsApplication.class);

	@Autowired
	private ZeebeClient zeebeClient;

	public static void main(String[] args) {
		//Spring Application starten
		ConfigurableApplicationContext ctx = SpringApplication.run(ProcessPaymentsApplication.class, args);

		//Spring Application schließen
		ctx.close();
	}

	@Override
	public void run(String... args) throws Exception {
		var bpmnProcessId = "typical-process";
		for (int i = 1; i <= 100; i++) {
			var event = zeebeClient.newCreateInstanceCommand()
					.bpmnProcessId(bpmnProcessId)
					.latestVersion()
					.withResult()
					.send()
					.join();

			LOG.info("Instance #" + i + " DONE");
		}


	}

}
