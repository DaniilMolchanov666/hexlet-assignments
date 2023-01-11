package exercise;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class App {
    public static String getForwardedVariables(String file) {

        return Arrays.stream(file.split("\n"))
                .flatMap(i-> Arrays.stream(i.split(",")))
                .map(i->i.replaceAll("environment=", "").replaceAll("\"", ""))
                .filter(i->i.startsWith("X_FORWARDED_"))
                .map(i->i.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
}

    public static void main(String[] args) throws IOException {
        Path a = Path.of("src/test/resources/fixtures/s2.conf");
        String b = Files.readString(a);
        System.out.println(getForwardedVariables(b));
    }
}