package exercise;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class App {
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {

        Map<String, String> a = map1.entrySet().stream()
                .filter(i -> map2.containsKey(i.getKey()) && map2.get(i.getKey()).equals(i.getValue()))
                .collect(Collectors.toSet();




    }

    public static void main(String[] args) {

        Map<String, Object> data1 = new HashMap<>();
        data1.put("two", "own");
        Map<String, Object> data2 = new HashMap<>();
        data2.put("one", "eon");

        Map<String, String> result = App.genDiff(data1, data2);
        System.out.println(result);
    }
}

//END
