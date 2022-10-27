package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseConfig {
    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = false;

        //Configuration.browserSize = "1920x1080";
        //System.setProperty("browser","chrome");
        String browserName = System.getProperty("browser", "chrome");
        Configuration.browser = browserName;

        //System.setProperty("browser_version","105");
        String browserVersion = System.getProperty("browser_version", "100");
        Configuration.browserVersion = browserVersion;

        //System.setProperty("browser_size","1920x1080");
        String browserSize = System.getProperty("browser_size","800x600");
        Configuration.browserSize = browserSize;

        String remoteUrl = System.getProperty("remote_url");
        if (remoteUrl != null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
            Configuration.remote = remoteUrl;
        }

    }
    @AfterEach
    void addAttachments() {
        Attachments.screenshotAs("screenshot");
        Attachments.pageSource();
        Attachments.consoleLogs();
        Attachments.addVideo();
    }
//    @AfterAll
//    void addVideo() {
//     Attachments.addVideo();
//    }
}
