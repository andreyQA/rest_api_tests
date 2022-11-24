package com.demoqa.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class TestRestAPI {
    @Test
    void checkCreateUserWithoutParameters() {
        given()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .body("id", notNullValue());
    }
    @Test
    void checkCreateUserWithParameters() {
        String data = "{\n" +
                "    \"name\": \"username\",\n" +
                "    \"job\": \"qa god\",\n" +
                "    \"random\": \"blablabla\"\n" +
                "}";
        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("id", not("null"),
                        "name",notNullValue(),
                        "job", notNullValue(),
                        "random",notNullValue());
    }
    @Test
    void checkCountUsersPerPage() {
        given()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .body("data.id", hasSize(Integer.parseInt("6")));
    }
    @Test
    void checkNonexistentUserOnFirstPage() {
        given()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .body("data.email", not("blablauser@mail.com"));
    }
    @Test
    void checkExistUserOnFirstPage() {
        given()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .body("data.email", hasItem("janet.weaver@reqres.in"));
    }

}
