package exercise;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static boolean scrabble(String word1, String word2) {

        if (word2.length() > word1.length()) {
            return false;
        }

        char[] array1 = word1.toLowerCase().toCharArray();
        char[] array2 = word2.toLowerCase().toCharArray();

        List<Character> scrabbleList = new ArrayList<>();

        for (char a: array1) {
            scrabbleList.add(a);
        }

        for (int i = 0; i < array2.length; i++) {

            if (scrabbleList.contains(array2[i])) {
                scrabbleList.remove(scrabbleList.indexOf(array2[i]));
                continue;
            }
            return false;
        }
        return true;
    }
}
