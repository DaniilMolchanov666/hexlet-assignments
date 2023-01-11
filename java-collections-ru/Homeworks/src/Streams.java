import java.util.Arrays;
import java.util.List;

public class Streams {
    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                .filter(x -> x.contains("@gmail.com") || x.contains("@yandex.ru") || x.contains("@hotmail.com"))
                .count();
    }

    public static void main(String[] args) {
        List<Integer> a = List.of(1,2,3,4,5,6,7,8,10);
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

        List<Integer> b = a.stream()
                .reduce((x, y) -> Math.max(x, y))
                .filter(x -> x % 2 == 0)
                .stream()
                .toList();
        System.out.println(b);

    }
}
