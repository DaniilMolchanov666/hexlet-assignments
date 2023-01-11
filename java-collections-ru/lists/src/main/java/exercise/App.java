package exercise;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static boolean scrabble(String word1, String word2) {

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

    public static void main(String[] args) {

        String symbols = "12345";
        String[] letters = symbols.split("");

        System.out.println(letters.length);
    }
}
