package tests;

import api.AuthorizationApi;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import models.AddSteps;
import models.CreateTestCase;
import models.Step;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

import static api.AuthorizationApi.ALLURE_TESTOPS_SESSION;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helper.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;


public class TestOpsTests {
    public final static String
            USERNAME = "allure8",
            PASSWORD = "allure8",
            USER_TOKEN = "8f277067-2669-4f13-ace9-b3131cb5c230";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://allure.autotests.cloud";
        RestAssured.baseURI = "https://allure.autotests.cloud";
        RestAssured.filters(withCustomTemplates());
    }

    @Test
    void loginTest() {
        open("");

        $(byName("username")).setValue(USERNAME);
        $(byName("password")).setValue(PASSWORD).pressEnter();

        $("button[aria-label=\"User menu\"]").click();
        $(".Menu__item_info").shouldHave(text(USERNAME));
    }
    @Test
    void loginTestWithAPI() {
        String token = given()
                .formParam("grant_type", "apitoken")
                .formParam("scope", "openid")
                .formParam("token", "8f277067-2669-4f13-ace9-b3131cb5c230")
                .when()
                .post("/api/uaa/oauth/token")
                .then()
                .statusCode(200)
                .extract()
                .path("jti");

        String authorizationCookie = given()
                .header("X-XSRF-TOKEN", token)
                .header("Cookie", "XSRF-TOKEN=" + token)
                .formParam("username", USERNAME)
                .formParam("password", PASSWORD)
                .when()
                .post("/api/login/system")
                .then()
                .statusCode(200).extract().response()
                .getCookie(ALLURE_TESTOPS_SESSION);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie(ALLURE_TESTOPS_SESSION, authorizationCookie));

        open("");
        $("button[aria-label=\"User menu\"]").click();
        $(".Menu__item_info").shouldHave(text(USERNAME));
    }
    @Test
    void loginTestWithApiExtended() {
        String authorizationCookie = new AuthorizationApi()
                .getAuthorizationCookie(USER_TOKEN, USERNAME, PASSWORD);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie(ALLURE_TESTOPS_SESSION, authorizationCookie));

        open("");
        $("button[aria-label=\"User menu\"]").click();
        $(".Menu__item_info").shouldHave(text(USERNAME));
    }
    @Test
    void viewTestCaseWithApiTest() {
        String authorizationCookie = new AuthorizationApi()
                .getAuthorizationCookie(USER_TOKEN, USERNAME, PASSWORD);

//        open("/favicon.ico");
//        getWebDriver().manage().addCookie(new Cookie(ALLURE_TESTOPS_SESSION, authorizationCookie));
//
//        open("");
//        $("button[aria-label=\"User menu\"]").click();
//        $(".Menu__item_info").shouldHave(text(USERNAME));
        given()
                .log().all()
                .cookie(ALLURE_TESTOPS_SESSION, authorizationCookie)
                .get("/api/rs/testcase/13673/overview")
                .then()
                .log().all()
                .statusCode(200)
                .body("name",is("Проверка критических ошибок в консоли на главной"));
    }
    @Test
    void viewTestCaseWithUiTest() {
        String authorizationCookie = new AuthorizationApi()
                .getAuthorizationCookie(USER_TOKEN, USERNAME, PASSWORD);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie(ALLURE_TESTOPS_SESSION, authorizationCookie));

        open("/project/1747/test-cases/13673");
        $(".TestCaseLayout__name").shouldHave(text("Проверка критических ошибок в консоли на главной"));
    }
    @Test
    void createTestCaseWithApiTest() {
        AuthorizationApi authorizationApi = new AuthorizationApi();

        String token = authorizationApi.getToken(USER_TOKEN);
        String authorizationCookie = authorizationApi
                .getAuthorizationCookie(USER_TOKEN, token, USERNAME, PASSWORD);

        Faker faker = new Faker();
        String caseName = faker.animal().name();

        CreateTestCase testCaseBody = new CreateTestCase();
        testCaseBody.setName(caseName);

        int caseId = given()
                .log().all()
                .header("X-XSRF-TOKEN", token)
                .cookies("XSRF-TOKEN", token,
                        ALLURE_TESTOPS_SESSION, authorizationCookie)
                .body(testCaseBody)
                .contentType(JSON)
                .queryParam("projectId","1747")
                .post("/api/rs/testcasetree/leaf")
                //.post("/api/rs/testcasetree/leaf?projectId=1747")
                .then()
                .log().body()
                .statusCode(200)
                .body("name", is(caseName))
                .body("automated", is(false))
                .extract()
                .path("id");

        AddSteps steps = new AddSteps();
        List<Step> testCaseSteps = new ArrayList<>();
        int numberOfSteps = faker.number().numberBetween(1,5);

        for (int i=0; i < numberOfSteps; i++) {
            Step step = new Step();
            step.setName(faker.rickAndMorty().character());
            testCaseSteps.add(step);
        }
        steps.setSteps(testCaseSteps);

        given()
                .log().all()
                .header("X-XSRF-TOKEN", token)
                .cookies("XSRF-TOKEN", token,
                        ALLURE_TESTOPS_SESSION, authorizationCookie)
                .contentType(JSON)
                .body(steps)
                //.queryParam("projectId","1747")
                .post("/api/rs/testcase/"+ caseId +"/scenario")
                .then()
                .log().body()
                .statusCode(200)
                .body("steps", hasSize(numberOfSteps));

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie(ALLURE_TESTOPS_SESSION, authorizationCookie));

        open("/project/1747/test-cases/" + caseId);
        $(".TestCaseLayout__name").shouldHave(text(caseName));
        for (int i=0; i<numberOfSteps; i++){
            $$(".TreeElement__node").get(i).shouldHave(text(testCaseSteps.get(i).getName()));
        }

    }

}
