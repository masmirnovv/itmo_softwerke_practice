package ru.itmo.masmirnov.task3.scr_service.impl;

import org.osgi.service.component.annotations.*;
import ru.itmo.masmirnov.task3.scr_service.HelloI;

@Component(service = HelloI.class, immediate = true)
public class HelloService implements HelloI {

    @Override
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