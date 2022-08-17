/*
 * Copyright (c) 2022 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */

package org.acme;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.stork.Stork;
import io.smallrye.stork.api.ServiceDefinition;
import io.smallrye.stork.api.config.LoadBalancerConfig;
import io.smallrye.stork.api.config.ServiceDiscoveryConfig;
import io.smallrye.stork.spi.config.SimpleServiceConfig;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

@ApplicationScoped
public class ServiceFinder {

    public IGreetingResource getService(String name) {
        Stork stork = Stork.getInstance();
        ServiceDiscoveryConfig serviceDiscoveryConfig = new SimpleServiceConfig.SimpleServiceDiscoveryConfig("foo", new HashMap<>());
        LoadBalancerConfig loadBalancerConfig = new SimpleServiceConfig.SimpleLoadBalancerConfig("least-response-time", Collections.emptyMap());
        stork.defineIfAbsent(name, ServiceDefinition.of(serviceDiscoveryConfig, loadBalancerConfig));
        return RestClientBuilder.newBuilder().baseUri(URI.create("stork://myservice:8081")).build(IGreetingResource.class);
    }
}
