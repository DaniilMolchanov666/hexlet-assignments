package exercise;

import java.util.*;
import java.util.Map.Entry;
class App {

public static List<Map<String, String>> findWhere (List <Map<String, String>> books, Map<String, String> strings) {

        List <Map<String, String>> result = new LinkedList<>();

        for (Map <String,String> a: books) {
                if (a.entrySet().containsAll(strings.entrySet())) {
                        result.add(a);
                }
        }
        return result;
}

public static void main(String[]args){
        List<Map<String, String>> BOOKS = List.of(
                Map.of("title", "Cymbeline", "author", "Shakespeare", "year", "1611"),
                Map.of("title", "Book of Fooos", "author", "FooBar", "year", "1111"),
                Map.of("title", "The Tempest", "author", "Shakespeare", "year", "1611"),
                Map.of("title", "Book of Foos Barrrs", "author", "FooBar", "year", "2222"),
                Map.of("title", "Still foooing", "author", "FooBar", "year", "3333"),
                Map.of("title", "Happy Foo", "author", "FooBar", "year", "4444")
        );

        List<Map<String, String>> expected = List.of(
                Map.of("title", "Cymbeline", "author", "Shakespeare", "year", "1611"),
                Map.of("title", "The Tempest", "author", "Shakespeare", "year", "1611")
        );

        Map<String, String> where = Map.of("author", "Shakespeare", "year", "1611");
        System.out.println(findWhere(BOOKS,where));

        }
}

