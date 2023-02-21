package com.haceral.com.demoinitialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoInitializationApplication implements ApplicationRunner {

    @Autowired
    private EventDispatcher eventDispatcher;
    @Autowired
    private EventDispatcherFactory eventDispatcherFactory;

    public static void main(String[] args) {
        SpringApplication.run(DemoInitializationApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            eventDispatcher.handle("device.fault", "hello world!");
            eventDispatcherFactory.EVENT_DISPATCHER.handle("device.offline", "hello world!");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
