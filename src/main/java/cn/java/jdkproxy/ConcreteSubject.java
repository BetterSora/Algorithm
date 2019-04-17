package cn.java.jdkproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcreteSubject implements ISubject {
    private static final Logger LOG = LoggerFactory.getLogger(ConcreteSubject.class);

    @Override
    public void action() {
        LOG.info("ConcreteSubject action()");
    }

    @Override
    public void doSomething() {
        LOG.info("ConcreteSubject doSomething()");
    }
}
