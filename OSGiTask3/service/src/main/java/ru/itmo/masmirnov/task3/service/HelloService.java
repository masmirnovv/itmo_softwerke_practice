package ru.itmo.masmirnov.task3.service;

import org.osgi.service.component.annotations.*;

@Component
public class HelloService implements HelloI {

    public void hello() {
        System.out.println("Hello OSGi world!");
    }

    @Activate
    public void start() {
        System.out.println("Service started");
        hello();
    }

    @Deactivate
    public void stop() {
        System.out.println("Service stopped");
    }

}