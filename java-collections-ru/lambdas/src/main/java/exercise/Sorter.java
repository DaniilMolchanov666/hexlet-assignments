package exercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class Sorter {
    public static List <String> takeOldestMans(List<Map<String, String>> users) throws ParseException {
        SimpleDateFormat one = new SimpleDateFormat("yyyy-MM-dd");

        return users.stream()
                .filter(x->!x.get("gender").equals("female"))
                .sorted((x,y) -> {
                    try {
                        return one.parse(x.get("birthday")).compareTo(one.parse(y.get("birthday")));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(x->x.get("name"))
                .collect(Collectors.toList());

    }
}


