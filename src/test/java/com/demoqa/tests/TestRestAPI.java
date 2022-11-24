package com.demoqa.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestRestAPI {
    @Test
    void checkServerAnswer() {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(403);
    }
}
