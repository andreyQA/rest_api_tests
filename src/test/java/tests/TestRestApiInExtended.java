package tests;

import com.github.javafaker.Faker;
import models.CreateUserRequestModel;
import models.CreateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static specs.CreateUserRequestSpec.createRequestSpec;
import static specs.CreateUserResponseSpec.createResponseSpec;
import static specs.ListOfUsersRequestSpec.usersRequestSpec;
import static specs.ListOfUsersResponseSpec.usersResponseSpec;

public class TestRestApiInExtended {
    @DisplayName("Проверка создания юзера без заданных параметров")
    @Test
    void checkCreateUserWithoutParameters() {
        given(createRequestSpec)
                .when()
                .post()
                .then()
                .spec(createResponseSpec)
                .extract().as(CreateUserResponseModel.class);
    }
    @DisplayName("Проверка создания юзера с заданными параметрами")
    @Test
    void checkCreateUserWithParameters() {
        CreateUserRequestModel body = new CreateUserRequestModel();

        Faker faker = new Faker();
        String job = faker.job().position();
        String name = faker.name().fullName();
        String random = faker.random().toString();

        body.setName(name);
        body.setJob(job);
        body.setRandom(random);
        //String data = "{ \"name\": \"" +name + "\", \"job\": \"" + job + "\", \"random\": \"" + random + "\" }";
        CreateUserResponseModel response = given(createRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(createResponseSpec)
                .extract().as(CreateUserResponseModel.class);

        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getJob()).isEqualTo(job);
        assertThat(response.getRandom()).isEqualTo(random);
        assertThat(response.getId()).isNotNull();
    }
    @DisplayName("Проверка количества юзеров на странице")
    @Test
    void checkCountUsersPerPage() {
        given(usersRequestSpec)
                .when()
                .get()
                .then()
                .spec(usersResponseSpec)
                .body("data.id", hasSize(Integer.parseInt("6")));
    }
    @DisplayName("Проверка несуществющего емейла")
    @Test
    void checkNonexistentUserOnFirstPage() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        given(usersRequestSpec)
                .when()
                .get()
                .then()
                .body("data.email", not(email));
    }
    @DisplayName("Проверка существующего емейла")
    @Test
    void checkExistUserOnFirstPage() {
        given(usersRequestSpec)
                .when()
                .get()
                .then()
                .body("data.email", hasItem("janet.weaver@reqres.in"));
    }

}
