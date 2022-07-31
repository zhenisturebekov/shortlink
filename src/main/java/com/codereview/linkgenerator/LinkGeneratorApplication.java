package com.codereview.linkgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LinkGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkGeneratorApplication.class, args);
    }
}
