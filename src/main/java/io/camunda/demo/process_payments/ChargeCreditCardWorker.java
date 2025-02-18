package io.camunda.demo.process_payments;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;

@Component
public class ChargeCreditCardWorker {
    private final static Logger LOG = LoggerFactory.getLogger(ChargeCreditCardWorker.class);
    @JobWorker(type = "charge-credit-card")
    public Map<String, Double> chargeCreditCard(@Variable(name = "totalWithTax") Double totalWithTax) {
        LOG.info("charging credit card: {}", totalWithTax);
        return Map.of("amountCharged", totalWithTax);
    }

    @JobWorker(type = "testing1")
    public void dummyWorker1() {
        LOG.info("dummyWorker1  ---  Done");
    }

    @JobWorker(type = "testing2")
    public void dummyWorker2() {
        LOG.info("dummyWorker2  ---  Done");
    }

    @JobWorker(type = "testing3")
    public void dummyWorker3() {
        LOG.info("dummyWorker3  ---  Done");
    }

    @JobWorker(type = "testing4")
    public void dummyWorker4() {
        LOG.info("dummyWorker4  ---  Done");
    }

    @JobWorker(type = "testing5")
    public void dummyWorker5() {
        LOG.info("dummyWorker5  ---  Done");
    }

    @JobWorker(type = "testing6")
    public void dummyWorker6() {
        LOG.info("dummyWorker6  ---  Done");
    }

    @JobWorker(type = "testing7")
    public void dummyWorker7() {
        LOG.info("dummyWorker7  ---  Done");
    }

    @JobWorker(type = "testing8")
    public void dummyWorker8() {
        LOG.info("dummyWorker8 ---  Done");
    }

    @JobWorker(type = "testing9")
    public void dummyWorker9() {
        LOG.info("dummyWorker9  ---  Done");
    }

    @JobWorker(type = "testing10")
    public void dummyWorker10() {
        LOG.info("dummyWorker10  ---  Done");
    }



}


