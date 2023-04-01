package com.groupone.backendalgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync //3.1
@SpringBootApplication
public class SSEofAlgorithmSimulation {

    public static void main(String[] args) {
        SpringApplication.run(SSEofAlgorithmSimulation.class, args);
    }
}