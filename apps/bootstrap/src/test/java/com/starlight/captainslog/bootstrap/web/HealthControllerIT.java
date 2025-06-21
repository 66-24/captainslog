package com.starlight.captainslog.bootstrap.web;


import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthControllerIT {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void shouldReturnHealthCheckMessage() {
        RestAssured
            .given()
            .when()
            .get("/health")
            .then()
            .statusCode(200)
            .body(equalTo("Ahoy! All systems green. 🌊🟢"));
    }
}
