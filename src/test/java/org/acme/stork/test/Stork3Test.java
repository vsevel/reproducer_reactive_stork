/*
 * Copyright (c) 2022 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */

package org.acme.stork.test;

import java.net.URI;
import java.net.URISyntaxException;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.IGreetingResource;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
public class Stork3Test {

    private static final Logger log = LoggerFactory.getLogger(Stork3Test.class);

    @Test
    public void testHelloEndpoint() throws URISyntaxException {
        IGreetingResource greetingResource = RestClientBuilder.newBuilder().baseUri(new URI("stork://hello-service:8081")).build(IGreetingResource.class);
        Assertions.assertEquals("Hello from RESTEasy", greetingResource.hello());
    }
}
