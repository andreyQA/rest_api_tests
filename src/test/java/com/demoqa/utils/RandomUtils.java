package com.demoqa.utils;

import com.demoqa.testData.RegistrationData;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomUtils {
    public static String getRandomMonth() {
        List<String> months = Arrays.asList(
                "January", "February", "March", "April",
                "May", "June", "July", "August",
                "September", "October", "November", "December");
        return months.get(new Random().nextInt(months.size()));

    }
    public static String getRandomSubject() {
        List<String> subject = Arrays.asList(
                "Physics", "Chemistry", "Computer science", "Commerce",
                "Accounting", "Economics", "Social Studies", "Civics");
        return subject.get(new Random().nextInt(subject.size()));

    }
    public static String getRandomHobby() {
        List<String> hobby = Arrays.asList(
                "Sports", "Reading", "Music");
        return hobby.get(new Random().nextInt(hobby.size()));

    }
    public static String getRandomState() {
        List<String> state = Arrays.asList(
                "NCR", "Uttar Pradesh", "Haryara", "Rajasthan");
        return state.get(new Random().nextInt(state.size()));

    }
    public static String getRandomCity() {
        switch (RegistrationData.state) {
            case "NCR": {
                List<String> city = Arrays.asList(
                        "Delhi", "Gurgaon", "Noida");
                return city.get(new Random().nextInt(city.size()));

            }
            case "Uttar Pradesh": {
                List<String> city = Arrays.asList(
                        "Agra", "Lucknow", "Merrut");
                return city.get(new Random().nextInt(city.size()));

            }
            case "Haryara": {
                List<String> city = Arrays.asList(
                        "Karnal", "Panipat");
                return city.get(new Random().nextInt(city.size()));

            }
            case "Rajasthan": {
                List<String> city = Arrays.asList(
                        "Jaipur", "Jaiselmer");
                return city.get(new Random().nextInt(city.size()));

            }
            default: return "incredible miracle";
        }
    }
}
