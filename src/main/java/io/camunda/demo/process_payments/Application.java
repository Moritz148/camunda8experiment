package io.camunda.demo.process_payments;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@Deployment(resources = "classpath:C8_benchmark.bpmn")
public class Application implements CommandLineRunner {

	private ZeebeClient zeebeClient = ZeebeClient.newClientBuilder()
			.gatewayAddress("zeebe:26500")
			.usePlaintext()
			.build();

	public static void main(String[] args) {
		//Spring-Application starten
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

		//Spring-Application schließen
		ctx.close();
	}

	@Override
	public void run(String... args) throws Exception {

		if(zeebeClient == null){
			System.out.println("Zeebe client not set");
			return;
		}

		var bpmnProcessId = "C8_benchmark";

		//Variable zum ändern der Anzahl der zu startenden Prozessinstanzen
		int numberOfInstances = 100;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

		for (int i = 1; i <= numberOfInstances; i++) {
			String timestampStarted = LocalDateTime.now().format(formatter);

			System.out.println("Instance #" + i + " STARTED - " + timestampStarted);
			zeebeClient.newCreateInstanceCommand()
					.bpmnProcessId(bpmnProcessId)
					.latestVersion()
					.withResult()
					.send()
					.join();
			String timestampEnded = LocalDateTime.now().format(formatter);
			System.out.println("Instance #" + i + " DONE - " + timestampEnded);
		}
	}

}
