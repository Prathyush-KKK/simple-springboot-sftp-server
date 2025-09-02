package com.demo.sftpServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SftpServerApplication {
    public static void main(String[] args) {
        // Prevent Spring Boot from shutting down immediately
        System.setProperty("spring.main.keep-alive", "true");
        SpringApplication.run(SftpServerApplication.class, args);
    }
}