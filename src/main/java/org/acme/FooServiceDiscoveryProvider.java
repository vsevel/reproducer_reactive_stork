/*
 * Copyright (c) 2022 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */

package org.acme;

import io.smallrye.stork.api.ServiceDiscovery;
import io.smallrye.stork.api.config.ServiceConfig;
import io.smallrye.stork.api.config.ServiceDiscoveryType;
import io.smallrye.stork.spi.ServiceDiscoveryProvider;
import io.smallrye.stork.spi.StorkInfrastructure;

@ServiceDiscoveryType("foo")
// @ServiceDiscoveryAttribute(name= "h", description = "host alias", required = true)
public class FooServiceDiscoveryProvider implements ServiceDiscoveryProvider<FooConfiguration> {
    @Override
    public ServiceDiscovery createServiceDiscovery(FooConfiguration o, String s, ServiceConfig serviceConfig, StorkInfrastructure storkInfrastructure) {
        // return new FooServiceDiscoverySimple();
        return new FooServiceDiscoveryCached();
    }
}