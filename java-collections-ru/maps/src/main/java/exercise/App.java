package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

    public static Map getWordCount2(String sentence) {

        String[] words = sentence.split(" ");
        Map map = new HashMap();

        if (sentence.length() == 0) {
            return map;
        }

        for (String word : words) {
            int wordCount = (int) map.getOrDefault(word, 0);
            wordCount += 1;
            map.put(word, wordCount);
        }

        return map;
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

    public static void main(String[] args) {

        Map <String, Integer> map = getWordCount(" A A A A B C C D D D");

        System.out.println(map.values());
        System.out.println(map.replace("A", 10));
        System.out.println(map);
        System.out.println(map.remove("A"));
        System.out.println(map);
        map.replace("B", 1, 5);
        System.out.println(map);

        for (String a: map.keySet()) {
            System.out.println(a);
        }
        for (int a: map.values()) {
            System.out.println(a);
        }

    }
}
