package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class GenerateRandomData {
    private Faker fakerTestData = new Faker(new Locale("en"));

    public int generateRandomNumber() {
        return fakerTestData.number().numberBetween(0, 9);

    }
}
