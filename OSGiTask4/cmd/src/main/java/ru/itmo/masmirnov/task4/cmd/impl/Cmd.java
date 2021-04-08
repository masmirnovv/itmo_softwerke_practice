package ru.itmo.masmirnov.task4.cmd.impl;

import org.osgi.service.component.annotations.*;
import osgi.enroute.debug.api.Debug;
import ru.itmo.masmirnov.task4.cmd.CmdI;
import ru.itmo.masmirnov.task4.cmdservice.CmdServiceI;

@Component(property = {
        Debug.COMMAND_SCOPE + "=practice",
        Debug.COMMAND_FUNCTION + "=hello"
})
public class Cmd implements CmdI {

    @Override
    public void hello(String arg) {
        service.hello(arg);
    }

    @Reference
    private CmdServiceI service;

}
