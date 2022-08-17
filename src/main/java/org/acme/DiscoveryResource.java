/*
 * Copyright (c) 2022 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */

package org.acme;

import io.smallrye.mutiny.Uni;

public class DiscoveryResource implements IDiscoveryResource {

    @Override
    public Uni<String> host() {
        return Uni.createFrom().item("localhost");
    }
}
