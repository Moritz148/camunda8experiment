package io.camunda.demo.process_payments;

import org.springframework.stereotype.Component;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class ExternalTaskWorker {

    @JobWorker(type = "task1")
    public void dummyWorker1() {
//        LOG.info("dummyWorker1  ---  Done");
    }

    @JobWorker(type = "task2")
    public void dummyWorker2() {}

    @JobWorker(type = "task3")
    public void dummyWorker3() {}

    @JobWorker(type = "task4")
    public void dummyWorker4() {}

    @JobWorker(type = "task5")
    public void dummyWorker5() {}

    @JobWorker(type = "task6")
    public void dummyWorker6() {}

    @JobWorker(type = "task7")
    public void dummyWorker7() {}

    @JobWorker(type = "task8")
    public void dummyWorker8() {}

    @JobWorker(type = "task9")
    public void dummyWorker9() {}

    @JobWorker(type = "task10")
    public void dummyWorker10() {}

    @JobWorker(type = "task11")
    public void dummyWorker11() {}

    @JobWorker(type = "task12")
    public void dummyWorker12() {}

    @JobWorker(type = "task13")
    public void dummyWorker13() {}

    @JobWorker(type = "task14")
    public void dummyWorker14() {}

    @JobWorker(type = "task15")
    public void dummyWorker15() {}

    @JobWorker(type = "task16")
    public void dummyWorker16() {}

    @JobWorker(type = "task17")
    public void dummyWorker17() {}

    @JobWorker(type = "task18")
    public void dummyWorker18() {}

    @JobWorker(type = "task19")
    public void dummyWorker19() {}

    @JobWorker(type = "task20")
    public void dummyWorker20() {}

    @JobWorker(type = "task21")
    public void dummyWorker21() {}

    @JobWorker(type = "task22")
    public void dummyWorker22() {}

    @JobWorker(type = "task23")
    public void dummyWorker23() {}

    @JobWorker(type = "task24")
    public void dummyWorker24() {}


}


