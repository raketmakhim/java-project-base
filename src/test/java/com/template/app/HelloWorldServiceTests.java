package com.template.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for HelloWorldService.
 */
@SpringBootTest
class HelloWorldServiceTests {

    @Autowired
    private HelloWorldService helloWorldService;

    @Test
    void serviceLoads() {
        assertNotNull(helloWorldService, "HelloWorldService should be autowired");
    }

    @Test
    void shouldReturnHelloWorld() {
        String greeting = helloWorldService.getGreeting();
        assertEquals("Hello World", greeting, "Greeting should be 'Hello World'");
    }
}
