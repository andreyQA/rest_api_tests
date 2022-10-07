package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.PracticeFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
        .setFirstName("Andrey")
        .setLastName("Izvekov")
        .setEmail("mailtest@mail.com")
        .setGender("Male")
        .setNumber("1234567890")
        .setBirthDate("10", "January", "1989")
        .setSubject("Computer Science")
        .setHobbies("Sports")
        .setPicture("1.jpeg")
        .setAddress("Address, Street, Building")
        .setState("NCR")
        .setCity("Delhi")
        .submitForm()
        //Check submitted form
        .checkModalTableVisible()
        .checkModalTableResult("Student Name","Andrey Izvekov")
        .checkModalTableResult("Student Email", "mailtest@mail.com")
        .checkModalTableResult("Gender", "Male")
        .checkModalTableResult("Mobile","1234567890")
        .checkModalTableResult("Date of Birth", "10 January,1989")
        .checkModalTableResult("Subjects", "Computer Science")
        .checkModalTableResult("Hobbies", "Sports")
        .checkModalTableResult("Picture", "1.jpeg")
        .checkModalTableResult("Address", "Address, Street, Building")
        .checkModalTableResult("State and City", "NCR Delhi");
    }
}