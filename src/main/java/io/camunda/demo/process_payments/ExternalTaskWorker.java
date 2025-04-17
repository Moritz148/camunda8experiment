package io.camunda.demo.process_payments;

import org.springframework.stereotype.Component;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class ExternalTaskWorker {

    @JobWorker(type = "testing1")
    public void dummyWorker1() {
//        LOG.info("dummyWorker1  ---  Done");
    }
}