package exercise;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class Sorter {
    public static List <String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(x->!x.get("gender").equals("female"))
                .sorted((x,y) -> {
                    LocalDate d1 = LocalDate.parse(x.get("birthday"));
                    LocalDate d2 = LocalDate.parse(y.get("birthday"));
                    return d1.compareTo(d2);
                })
                .map(x->x.get("name"))
                .collect(Collectors.toList());
    }
}


