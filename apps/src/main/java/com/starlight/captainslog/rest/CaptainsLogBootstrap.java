package com.starlight.captainslog.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("PMD.HideUtilityClassConstructor")
@SpringBootApplication(scanBasePackages = "com.starlight.captainslog")
public class CaptainsLogBootstrap {
public static void main(String[] args) {
        SpringApplication.run(CaptainsLogBootstrap.class, args);
    }

}
