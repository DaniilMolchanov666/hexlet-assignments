package exercise;

import java.util.List;
import java.util.Arrays;

public class App {
    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                .filter(x -> x.contains("@gmail.com") || x.contains("@yandex.ru") || x.contains("@hotmail.com"))
                .count();
    }

    public static void main(String[] args) {
        List<Integer> a = List.of(1, 2, 3, 4, 5, 6, 7, 8, 10);
        String[] emails = {
                "info@gmail.com",
                "info@yandex.ru",
                "mk@host.com",
                "support@hexlet.io",
                "info@hotmail.com",
                "support.yandex.ru@host.com"
        };

        List<String> emailsList = Arrays.asList(emails);
        System.out.println(getCountOfFreeEmails(emailsList));
    }
}
