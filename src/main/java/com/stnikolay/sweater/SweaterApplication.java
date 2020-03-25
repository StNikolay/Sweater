package com.stnikolay.sweater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SweaterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SweaterApplication.class, args);
    }

}
