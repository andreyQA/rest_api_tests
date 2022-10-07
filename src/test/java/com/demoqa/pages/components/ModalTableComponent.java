 package com.demoqa.pages.components;

 import static com.codeborne.selenide.Condition.text;
 import static com.codeborne.selenide.Selectors.byText;
 import static com.codeborne.selenide.Selenide.$;

 public class ModalTableComponent {
     private final static String TITLE_TEXT = "Thanks for submitting the form";

     public ModalTableComponent isDisplayed() {
         $("#example-modal-sizes-title-lg").shouldHave(text(TITLE_TEXT));
         return this;
     }
     public ModalTableComponent checkResult(String key, String value) {
         $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));
         return this;
     }
 }
