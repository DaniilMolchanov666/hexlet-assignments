package exercise;

import java.util.HashMap;
import java.util.Map;
class App {

    public static Map<String, Integer> getWordCount(String sentence){

        String[] array = sentence.split(" ");
        Map <String, Integer> dictionary = new HashMap<>();

        if (sentence.length() == 0) {
            return dictionary;
        }

        for (int i = 0; i < array.length; i++) {
            int count = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[i].equals(array[j])) {
                    count++;
                }
            }
            dictionary.put(array[i], count);
        }
        return dictionary;
    }

    public static String toString(Map<String, Integer> map) {

        if (map.isEmpty()) {
            return "{}";
        }

        StringBuilder a = new StringBuilder();
        a.append("{" + "\n");

        for (Map.Entry <String, Integer> pair: map.entrySet()) {
            a.append("  " + pair.getKey() + ": " + pair.getValue() + "\n");

        }
        a.append("}" + "\n");

        return a.toString();

    }
}
