package io.camunda.demo.process_payments;

import io.camunda.zeebe.client.api.response.Topology;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.context.ConfigurableApplicationContext;
import java.time.Instant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@Deployment(resources = "classpath:C8_benchmark.bpmn")
public class Application implements CommandLineRunner {

	private ZeebeClient zeebeClient = ZeebeClient.newClientBuilder()
			.gatewayAddress("zeebe-gateway:26500")
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



        final Topology topology = zeebeClient.newTopologyRequest().send().join();

        System.out.println("Topology:");
        topology
                .getBrokers()
                .forEach(
                        b -> {
                            System.out.println("    " + b.getAddress());
                            b.getPartitions()
                                    .forEach(
                                            p ->
                                                    System.out.println(
                                                            "      " + p.getPartitionId() + " - " + p.getRole()));
                        });

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

        for (int i = 1; i <= numberOfInstances; i++) {
//			String timestampStarted = LocalDateTime.now().format(formatter);
//
//			System.out.println("Instance #" + i + " STARTED - " + timestampStarted);
            if(i==1) {
                Instant start = Instant.now();
                long startMicros = start.getEpochSecond() * 1_000_000L + start.getNano() / 1_000;
                System.out.println("Instance #" + i + " STARTED - " + startMicros);
            }

            zeebeClient.newCreateInstanceCommand()
                    .bpmnProcessId(bpmnProcessId)
                    .latestVersion()
                    .withResult()
                    .send()
                    .join();

//			String timestampEnded = LocalDateTime.now().format(formatter);
//			System.out.println("Instance #" + i + " DONE - " + timestampEnded);
            if(i==100){
                Instant end = Instant.now();
                long endMicros = end.getEpochSecond() * 1_000_000L + end.getNano() / 1_000;
                System.out.println("Instance #" + i + " DONE - " + endMicros);
            }
		}
	}
}
