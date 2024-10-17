package com.rest.assure;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

import java.util.Collections;
import java.util.Map;

@Controller("/hello")
public class HelloController {

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "Hello World";
    }

    @Get("/my-endpoint")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> myEndpoint() {
        String message = "Hello Micronaut";
        return Collections.singletonMap("message", message);
    }

    @Get("/my-json-endpoint")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse jsonResponse() {
        return new MyResponse(1, "John Doe");
    }
}
