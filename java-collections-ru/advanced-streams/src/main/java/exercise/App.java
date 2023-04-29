package exercise;

import java.util.Arrays;
import java.util.stream.Collectors;

class App {
    public static String getForwardedVariables(String file) {

        return Arrays.stream(file.split("\n"))
                .flatMap(i -> Arrays.stream(i.split(",")))
                .map(i -> i.replaceAll("environment=", "").replaceAll("\"", ""))
                .filter(i -> i.startsWith("X_FORWARDED_"))
                .map(i -> i.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
