package com.template.app;

import org.springframework.stereotype.Service;

/**
 * Service for handling Hello World business logic.
 */
@Service
public class HelloWorldService {

    /**
     * Returns a greeting message.
     *
     * @return the greeting message
     */
    public String getGreeting() {
        return "Hello World";
    }
}
