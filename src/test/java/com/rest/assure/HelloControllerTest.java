package com.rest.assure;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;

@MicronautTest
public class HelloControllerTest {

    @Test
    public void testHelloEndpoint(RequestSpecification spec) {
        spec
                .when()
                    .get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello World"));
    }
}
