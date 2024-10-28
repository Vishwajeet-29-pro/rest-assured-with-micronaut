package com.rest.assure;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.validation.Valid;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
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

    @Post(value = "/my-post-request")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<?> createUser(@Body @Valid UserRequest userRequest) {
        HashMap<Integer, String> user = new HashMap<>();
        if (userRequest.getUserId() == null || userRequest.getUserId() == 0 || userRequest.getName() == null || userRequest.getName().isEmpty()) {
            return HttpResponse.badRequest("Invalid request: userId and name are required fields."); // 400 response with a message
        }
        return HttpResponse.created(userRequest);
    }

    @Get("/getById/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<?> getUserById(@PathVariable int userId) {
        HashMap<Integer, String>  user = new HashMap<>();
        user.put(1, "John Doe");

        String name = user.get(userId);
        if (name == null) {
            return HttpResponse.notFound();
        }
        else {
            return HttpResponse.ok(new UserResponse(userId, name));
        }
    }
}
