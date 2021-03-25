package ru.itmo.masmirnov.task3.client;

import org.osgi.service.component.annotations.*;
import ru.itmo.masmirnov.task3.service.*;

@Component
public class HelloClient {

    @Reference
    private HelloI hello;

    @Activate
    public void start() {
        System.out.println("Client started");
        hello.hello();
    }

    @Deactivate
    public void stop() {
        System.out.println("Client stopped");
    }

}