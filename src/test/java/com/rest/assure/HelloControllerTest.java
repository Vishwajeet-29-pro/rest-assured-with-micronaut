package com.rest.assure;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
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

    @Test
    public void testMyEndpoint(RequestSpecification spec) {
        RestAssured.baseURI = "http://localhost:8080";

        spec
                .when()
                .get("hello/my-endpoint")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("message", equalTo("Hello Micronaut"));
    }

    @Test
    public void testEndPointReturnsJsonData(RequestSpecification spec) {

        spec
                .when()
                .get("/hello/my-json-endpoint")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("name", equalTo("John Doe"));
    }
}
