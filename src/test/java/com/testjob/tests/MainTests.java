package com.testjob.tests;

import com.testjob.pages.ObjectPage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainTests extends BaseConfig {
    ObjectPage objectPage = new ObjectPage();
    @Owner("Andrey I")
    @Test
    @DisplayName("Проверка тайтла на главной")
    void testOpeningPage() {
        objectPage.checkTextInTitle();
    }
    @Test
    @DisplayName("Проверка критических ошибок в консоли на главной")
    void testConsoleLog() {
        objectPage.checkErrorsInConsole();
    }
    @Test
    @DisplayName("Проверка перехода на страницу авторизации")
    void checkLoginButton() {
        objectPage.checkLoginButton();
    }
    @Test
    @DisplayName("Проверка перехода на страницу регистрации")
    void checkSignUpButton() {
        objectPage.checkSignUpButton();
    }
    @Test
    @DisplayName("Проверка поиска вакансии")
    void checkSearchInput() {
        objectPage.checkSearchInput();
    }
}