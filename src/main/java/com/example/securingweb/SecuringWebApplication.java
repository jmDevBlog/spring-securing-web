package com.example.securingweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SecuringWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuringWebApplication.class, args);
    }

}
