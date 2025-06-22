package com.starlight.captainslog.infrastructure.api;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import io.restassured.RestAssured;

@SpringBootTest( classes = com.starlight.captainslog.rest.CaptainsLogBootstrap.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LogEntriesControllerIT {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void shouldRecordLogEntry() {
        var request = new HashMap<String, String>();
        request.put("title", "Fix warp core plasma leak");
        request.put("category", "Engineering");

        RestAssured
                .given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/api/v1/log-entries")
                .then()
                .statusCode(201)
                .body("title", equalTo("Fix warp core plasma leak"))
                .body("category", equalTo("Engineering"));
    }

}
