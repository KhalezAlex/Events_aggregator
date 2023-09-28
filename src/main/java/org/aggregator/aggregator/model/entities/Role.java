package org.aggregator.aggregator.model.entities;

import java.util.stream.Stream;

public enum Role {
    ADMIN,
    CUSTOMER,
    EXECUTOR;

    public static Stream<Role> stream() {
        return Stream.of(Role.values());
    }
    
}
