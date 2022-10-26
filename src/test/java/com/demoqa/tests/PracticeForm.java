package com.demoqa.tests;

import com.demoqa.pages.PracticeFormPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.demoqa.testData.RegistrationData.*;

public class PracticeForm extends BaseConfig{
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    @Test
    @Feature("Тестище фича")
    @Story("тестище стори")
    @DisplayName("Тест формы")
    @Owner("Andrey I")
    void testForm() {
//        System.setProperty("browser","chrome");
//        String browserName = System.getProperty("browser");
//        System.out.println(browserName);
        //Fill form
        practiceFormPage.openPage()
        .setFirstName(firstName)
        .setLastName(lastName)
        .setEmail(email)
        .setGender(gender)
        .setNumber(phone)
        .setBirthDate(day, month, year)
        .setSubject(subject)
        .setHobbies(hobby)
        .setPicture(picture)
        .setAddress(address)
        .setState(state)
        .setCity(city)
        .submitForm()
        //Check submitted form
        .checkModalTableVisible()
        .checkModalTableResult("Student Name",firstName + " " + lastName)
        .checkModalTableResult("Student Email", email)
        .checkModalTableResult("Gender", gender)
        .checkModalTableResult("Mobile",phone)
        .checkModalTableResult("Date of Birth", birthday)
        .checkModalTableResult("Subjects", subject)
        .checkModalTableResult("Hobbies", hobby)
        .checkModalTableResult("Picture", picture)
        .checkModalTableResult("Address", address)
        .checkModalTableResult("State and City", state + " " + city);
    }
}