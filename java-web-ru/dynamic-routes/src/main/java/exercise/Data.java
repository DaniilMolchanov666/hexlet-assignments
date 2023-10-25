package exercise;

import java.util.Random;
import java.util.Locale;
import com.github.javafaker.Faker;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Collections;

public class Data {
    private static final int ITEMS_COUNT = 30;

    private static int idCounter = ITEMS_COUNT;

    public static List<Map<String, String>> getCompanies() {
        Random random = new Random(123);
        Faker faker = new Faker(new Locale("en"), random);

        List<String> ids = IntStream
            .range(1, ITEMS_COUNT + 1)
            .mapToObj(i -> Integer.toString(i))
            .collect(Collectors.toList());
        Collections.shuffle(ids, random);

        List<Map<String, String>> companies = new ArrayList<>();

        for (int i = 0; i < ITEMS_COUNT; i++) {
            Map<String, String> company = new HashMap<>();
            company.put("id", ids.get(i));
            company.put("name", faker.company().name());
            company.put("phone", faker.phoneNumber().cellPhone());
            companies.add(company);
        }

        return companies;
    }

    public static String getNextId() {
        int id = ++idCounter;
        return Integer.toString(id);
    }
}
