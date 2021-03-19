package client;

import hello.HelloI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class HelloClient implements BundleActivator {

    public void start(BundleContext context) {
        ServiceReference serviceRef = context.getServiceReference(HelloI.class.getName());
        HelloI service = (HelloI) context.getService(serviceRef);
        System.out.println("Client started");
        service.hello();
    }

    public void stop(BundleContext context) {
        System.out.println("Client stopped");
    }

}