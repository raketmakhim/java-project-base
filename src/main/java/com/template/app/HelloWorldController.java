package com.template.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for Hello World endpoints.
 */
@RestController
@RequestMapping("/api")
public class HelloWorldController {

    private final HelloWorldService helloWorldService;

    /**
     * Constructor for HelloWorldController.
     *
     * @param helloWorldService the service to handle business logic
     */
    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    /**
     * GET endpoint that returns Hello World.
     *
     * @return the greeting message
     */
    @GetMapping("/hello")
    public String hello() {
        return helloWorldService.getGreeting();
    }
}
