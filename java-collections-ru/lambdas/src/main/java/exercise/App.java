package exercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class App {
    public static void main(String[] args) throws ParseException {
        List<Map<String, String>> users;
        users = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1990-12-27", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );

        List<String> men = Sorter.takeOldestMans(users);
        System.out.println(men); // ["John Smith", "Andrey Petrov", "Vladimir Nikolaev"]

        SimpleDateFormat one = new SimpleDateFormat("yyyy-MM-dd");

        List<String> list1 = new ArrayList<>();

        for (Map<String, String> a : users) {
            for (Map.Entry<String, String> pair : a.entrySet()) {
                if (pair.getKey().equals("birthday")) {
                    Date d = one.parse(pair.getValue());
                    list1.add(d.toString());
                    System.out.println(one.format(d));
                }
            }
        }
    }
}

