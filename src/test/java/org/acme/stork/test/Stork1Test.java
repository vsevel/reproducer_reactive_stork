/*
 * Copyright (c) 2022 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */

package org.acme.stork.test;

import javax.inject.Inject;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.IGreetingResource;
import org.acme.ServiceFinder;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class Stork1Test {

    @Inject
    ServiceFinder finder;

    @Test
    void finder() {
        IGreetingResource service1 = finder.getService("myservice");
        // Assertions.assertEquals("Hello from RESTEasy", service1.hello());
    }
}
