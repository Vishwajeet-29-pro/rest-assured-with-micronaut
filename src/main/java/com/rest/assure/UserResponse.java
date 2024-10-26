package com.rest.assure;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record UserResponse(int userId,String name) {
}
