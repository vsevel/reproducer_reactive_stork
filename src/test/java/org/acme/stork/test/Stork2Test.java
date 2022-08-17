/*
 * Copyright (c) 2022 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */

package org.acme.stork.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
public class Stork2Test {

    private static final Logger log = LoggerFactory.getLogger(Stork2Test.class);

    @Test
    public void testHelloEndpointFromStork() {
        given()
                .when().get("/greeting/stork")
                .then()
                .statusCode(200)
                .body(is("Hello from stork: Hello from RESTEasy"));
    }
    
}
