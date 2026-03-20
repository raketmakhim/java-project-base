package com.template.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// CHECKSTYLE:OFF: HideUtilityClassConstructor - Spring Boot application class
// CHECKSTYLE:OFF: FinalClass - Spring Boot needs to proxy this class
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
// CHECKSTYLE:ON
