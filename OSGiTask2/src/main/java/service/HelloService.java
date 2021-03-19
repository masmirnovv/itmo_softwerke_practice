package service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import hello.*;

public class HelloService implements BundleActivator {

    public void start(BundleContext context) {
        HelloI service = new Hello();
        context.registerService(HelloI.class.getName(), service, null);
        System.out.println("Service started");
        service.hello();
    }

    public void stop(BundleContext context) {
        System.out.println("Service stopped");
    }

}