package io.camunda.demo.process_payments;

import io.camunda.zeebe.client.ZeebeClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import io.camunda.zeebe.broker.*;
import io.camunda.zeebe.engine.processing.processinstance.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Deployment(resources = "classpath:typical_process.bpmn")
public class ProcessPaymentsApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessPaymentsApplication.class);

	private ZeebeClient zeebeClient = ZeebeClient.newClientBuilder()
			.gatewayAddress("zeebe:26500")
			.usePlaintext()
			.build();

	public static void main(String[] args) {
		//Spring-Application starten
		ConfigurableApplicationContext ctx = SpringApplication.run(ProcessPaymentsApplication.class, args);

		//Spring-Application schließen
		ctx.close();
	}

	@Override
	public void run(String... args) throws Exception {

		if(zeebeClient == null){
			LOG.info("Zeebe client not set");
			return;
		}

		var bpmnProcessId = "typical-process";
		//erzeugen von 100 Prozessinstanzen
		for (int i = 1; i <= 100; i++) {
			LOG.info("Instance #" + i + " startet jetzt.");
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
