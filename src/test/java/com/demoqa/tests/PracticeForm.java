package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.PracticeFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.demoqa.testData.RegistrationData.*;

public class PracticeForm {
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void testForm() {
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