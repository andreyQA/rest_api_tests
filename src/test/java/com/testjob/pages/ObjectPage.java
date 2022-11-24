package com.testjob.pages;

import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class ObjectPage {
    public ObjectPage checkTextInTitle() {
        step("Open url hh.ru", () ->
            open("/"));

        step("Page title should have text 'Работа в Москве, поиск персонала и публикация вакансий - hh.ru'", () -> {
            String expectedTitle = "Работа в Москве, поиск персонала и публикация вакансий - hh.ru";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    return this;
    }
    public ObjectPage checkErrorsInConsole() {
        step("Open url hh.ru", () ->
            open("/"));

        step("Console logs should not contain text 'SEVERE'", () -> {

            String consoleLogs = String.join("\n", Selenide.getWebDriverLogs(BROWSER));
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    return this;
    }
    public ObjectPage checkLoginButton() {
            step("Open url hh.ru", () ->
                open("/"));

            step("Click on the Login button", () -> {
                $("a[data-qa='login']").click();
            });
            step("Check title", () -> {
                String expectedTitle = "Вход в личный кабинет";
                String actualTitle = title();

                assertThat(actualTitle).isEqualTo(expectedTitle);
            });
        return this;
        }
    public ObjectPage checkSignUpButton() {
            step("Open url hh.ru", () ->
                open("/"));

            step("Click on the Signup button", () -> {
                $("a[data-qa='signup']").click();
            });
            step("Check title", () -> {
                String expectedTitle = "Регистрация соискателя";
                String actualTitle = title();

                assertThat(actualTitle).isEqualTo(expectedTitle);
            });
        return this;
        }
    public ObjectPage checkSearchInput() {
            step("Open url hh.ru", () ->
                open("/"));

            step("Input 'QA' to search field", () ->
                $("input[data-qa='search-input']").setValue("QA"));

            step("Submit query", () ->
                $("button[data-qa='search-button']").click());

            step("Check first vacancy's title for expected query", () ->
                $("a[data-qa='serp-item__title']").shouldHave(text("QA")));
        return this;
        }
    }
