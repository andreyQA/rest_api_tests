package com.demoqa.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
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
        Faker faker = new Faker();
        String job = faker.job().position();
        String name = faker.name().fullName();
        String random = faker.random().toString();
        String data = "{ \"name\": \"" +name + "\", \"job\": \"" + job + "\", \"random\": \"" + random + "\" }";
        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "name",is(name),
                        "job", is(job),
                        "random",is(random));
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
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        given()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .body("data.email", not(email));
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
