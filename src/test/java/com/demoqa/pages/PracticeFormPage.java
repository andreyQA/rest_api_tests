package com.demoqa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import com.demoqa.pages.components.ModalTableComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    private CalendarComponent calendarComponent = new CalendarComponent();
    private ModalTableComponent modalTableComponent = new ModalTableComponent();
    private final static String TITLE_TEXT = "Student  Registration Form";
    //Elements
    private SelenideElement
        firstNameInput = $("#firstName"),
        lastNameInput = $("#lastName"),
        emailInput = $("#userEmail"),
        numberInput = $("#userNumber"),
        subjectInput = $("#subjectsInput"),
        hobbiesInput = $("#hobbiesWrapper"),
        picInput = $("#uploadPicture"),
        addressInput =  $("#currentAddress"),
        stateDropdown = $("#state"),
        stateSelect =  $("#stateCity-wrapper"),
        cityDropdown = $("#city"),
        citySelect = $("#stateCity-wrapper"),
        submitButton =  $("#submit");

    //Actions
    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(Condition.text(TITLE_TEXT));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }
    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }
    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }
    public PracticeFormPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }
    public PracticeFormPage setGender(String value) {
        $("#genterWrapper").$(byText(value)).click();
        return this;
    }
    public PracticeFormPage setNumber(String value) {
        numberInput.setValue(value);
        return this;
    }
    public PracticeFormPage setBirthDate(String day,String month,String year) {
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(day, month, year);
        return this;
    }
    public PracticeFormPage setSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }
    public PracticeFormPage setHobbies(String value) {
        hobbiesInput.$(byText(value)).click();
        return this;
    }
    public PracticeFormPage setPicture(String value) {
        picInput.uploadFromClasspath(value);
        return this;
    }
    public PracticeFormPage setAddress(String value) {
        addressInput.setValue(value);
        return this;
    }
    public PracticeFormPage setState(String value) {
        stateDropdown.click();
        stateSelect.$(byText(value)).click();
        return this;
    }
    public PracticeFormPage setCity(String value) {
        cityDropdown.click();
        citySelect.$(byText(value)).click();
        return this;
    }
    public PracticeFormPage submitForm() {
        submitButton.click();
        return this;
    }
    public PracticeFormPage checkModalTableVisible() {
        modalTableComponent.isDisplayed();
        return this;
    }
    public PracticeFormPage checkModalTableResult(String key, String value) {
        modalTableComponent.checkResult(key, value);
        return this;
    }
}
