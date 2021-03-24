package ru.itmo.masmirnov.client;

import ru.itmo.masmirnov.hello.HelloI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class HelloClient implements BundleActivator {

    public void start(BundleContext context) {
        ServiceReference<?> serviceRef = context.getServiceReference(HelloI.class.getName());
        if (serviceRef == null) {
            System.out.println("Oof! No services found");
            return;
        }
        HelloI service = (HelloI) context.getService(serviceRef);
        System.out.println("Client started");
        service.hello();
    }

    public void stop(BundleContext context) {
        System.out.println("Client stopped");
    }

}