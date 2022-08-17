package org.acme;

import java.util.function.Supplier;

import javax.inject.Inject;

import io.smallrye.mutiny.Uni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GreetingResource implements IGreetingResource {

    private static final Logger log = LoggerFactory.getLogger(GreetingResource.class);

    @Inject
    ServiceFinder finder;

    @Override
    public String hello() {
        return "Hello from RESTEasy";
    }

    @Override
    public Uni<String> helloReactive() {
        log.info("hello reactive");
        return Uni.createFrom().item(getResult());
    }

    private Supplier<String> getResult() {
        return () -> "Hello from RESTEasy Reactive";
    }

    @Override
    public String helloFromStork() {
        return "Hello from stork: " + finder.getService("myservice").hello();
    }
}