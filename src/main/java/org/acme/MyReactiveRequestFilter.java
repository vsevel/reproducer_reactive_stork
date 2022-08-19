/*
 * Copyright (c) 2022 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */

package org.acme;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.quarkus.arc.Arc;
import io.quarkus.security.identity.CurrentIdentityAssociation;
import org.jboss.resteasy.reactive.client.spi.ResteasyReactiveClientRequestContext;
import org.jboss.resteasy.reactive.client.spi.ResteasyReactiveClientRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class MyReactiveRequestFilter implements ResteasyReactiveClientRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(MyReactiveRequestFilter.class);

    @Inject
    CurrentIdentityAssociation currentIdentityAssociation;

    @Override
    public void filter(ResteasyReactiveClientRequestContext requestContext) {

        if(Arc.container().requestContext().isActive()) {
            log.info("processing " + requestContext.getUri() + "; suspend request context");
            requestContext.suspend();

            currentIdentityAssociation.getDeferredIdentity().subscribe()
                    .with(securityIdentity -> {
                                log.info("anonymous = " + securityIdentity.isAnonymous());
                                requestContext.resume();
                            },
                            throwable -> {
                                log.error("filter failed", throwable);
                                requestContext.abortWith(Response.status(500).build());
                                requestContext.resume();
                            });
        }
    }
}
