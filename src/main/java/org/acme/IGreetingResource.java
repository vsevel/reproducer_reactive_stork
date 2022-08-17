/*
 * Copyright (c) 2022 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */

package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Uni;

@Path("/greeting")
public interface IGreetingResource {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    String hello();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello-reactive")
    Uni<String> helloReactive();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/stork")
    String helloFromStork();
}
