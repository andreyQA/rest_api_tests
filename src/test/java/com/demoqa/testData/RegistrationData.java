package com.demoqa.testData;

import com.demoqa.utils.RandomUtils;
import com.github.javafaker.Faker;
import java.util.Locale;

public class RegistrationData {
    public static Faker faker = new Faker(new Locale("en"));

    public static String
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().safeEmailAddress(),
            gender = faker.demographic().sex(),
            phone = faker.phoneNumber().subscriberNumber(10),
            day = String.format("%02d", faker.number().numberBetween(1,28)),
            month = RandomUtils.getRandomMonth(),
            year= String.valueOf(faker.number().numberBetween(1920,2004)),
            address = faker.address().fullAddress(),
            birthday = day + " " + month + "," + year,
            subject = RandomUtils.getRandomSubject(),
            hobby = RandomUtils.getRandomHobby(),
            picture = "1.jpeg",
            state = RandomUtils.getRandomState(),
            city = RandomUtils.getRandomCity();

}
