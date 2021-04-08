package ru.itmo.masmirnov.task4.cmdservice.impl;

import org.osgi.service.component.annotations.*;
import ru.itmo.masmirnov.task4.cmdservice.CmdServiceI;

@Component(service = CmdServiceI.class, immediate = true)
public class CmdService implements CmdServiceI {

    @Override
    public void hello(String arg) {
        System.out.println("Hello " + arg + "!");
    }

    @Activate
    public void start() {
        System.out.println("Cmd service started");
    }

    @Deactivate
    public void stop() {
        System.out.println("Cmd service stopped");
    }

}