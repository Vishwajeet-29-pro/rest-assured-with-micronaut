package com.rest.assure;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
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

    @Test
    public void testPostRequest(RequestSpecification spec) {

        UserRequest userRequest = new UserRequest(1, "John Doe");

        spec
                .contentType(ContentType.JSON)
                .body(userRequest)
                .when()
                .post("/hello/my-post-request")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("userId", notNullValue())
                .body("name", equalTo("John Doe"));
    }

    @Test
    public void testPathVariable(RequestSpecification spec) {
        spec
                .pathParam("userId", 1)
                .when()
                .get("/hello/getById/{userId}")
                .then()
                .statusCode(200)
                .body("userId",equalTo(1))
                .body("name", equalTo("John Doe"));
    }

    @Test
    public void testPathVariableIfRecordNotFoundShouldReturn404(RequestSpecification spec) {
        spec
                .pathParam("userId", 99)
                .when()
                .get("/hello/getById/{userId}")
                .then()
                .statusCode(404);
    }
}
