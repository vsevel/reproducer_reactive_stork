/*
 * Copyright (c) 2022 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */

package org.acme;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.smallrye.mutiny.Uni;
import io.smallrye.stork.api.ServiceInstance;
import io.smallrye.stork.impl.CachingServiceDiscovery;
import io.smallrye.stork.impl.DefaultServiceInstance;
import io.smallrye.stork.utils.ServiceInstanceIds;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

public class FooServiceDiscoveryCached extends CachingServiceDiscovery {

    private Map<String, Long> ids = new ConcurrentHashMap<>();

    public FooServiceDiscoveryCached() {
        super("5M");
    }

    @Override
    public Uni<List<ServiceInstance>> fetchNewServiceInstances(List<ServiceInstance> previousInstances) {
        IDiscoveryResource discovery = RestClientBuilder.newBuilder().baseUri(URI.create("http://localhost:8081")).build(IDiscoveryResource.class);
        return discovery.host().map(this::createInstances);
    }

    private List<ServiceInstance> createInstances(String host) {
        return List.of(new DefaultServiceInstance(getServiceId(host, 8081), host, 8081, false));
    }

    private long getServiceId(String host, int port) {
        return ids.computeIfAbsent(host + ":" + port, k -> ServiceInstanceIds.next());
    }
}
