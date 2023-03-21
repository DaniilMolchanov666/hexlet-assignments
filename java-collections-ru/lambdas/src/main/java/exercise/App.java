package exercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class App {

    // решение преподавателя
    public static int findIndexOfNearest2(List<Integer> numbers, int num) {
        if (numbers.isEmpty()) {
            return -1;
        }
        List<Integer> diffs = numbers.stream()
                .map(element -> Math.abs(num - element))
                .toList();

        var minDiff = diffs.stream().mapToInt(Integer::intValue).min().getAsInt();

        return diffs.indexOf(minDiff);
    }

    // мое решение
    public static int findIndexOfNearest(List<Integer> coll, int number) {
        if (coll.isEmpty()) {
            return -1;
        }
        int index = coll.stream()
                .reduce(coll.get(0), (a,b) -> Math.abs(a - number) > Math.abs(b - number) ? b: a);
        return coll.indexOf(index);
    }

    public static Map<Integer, Long> getMenCountByYear(List<Map<String, String>> mapList) {
            return mapList.stream()
                    .filter(i->i.get("gender").equals("male"))
                    .map(i->LocalDate.parse(i.get("birthday")).getYear())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }

    public static List<Integer> sameParityFilter(List<Integer> list) {

        if(list.isEmpty()) {
            return new ArrayList<Integer>();
        }

            return list.stream()
                .filter(i->{
                    if(list.get(0)%2 == 0 && i%2 == 0) {
                        return true;
                    }
                    else if(list.get(0)%2 != 0 && i%2 != 0) {
                        return true;
                    }
                    return false;
                })
                    .collect(Collectors.toList());

    }

    public static List<String> filterAnagram(String word, List<String> list) {

        String wordSorted = Arrays.stream(word.split(""))
                .sorted()
                .collect(Collectors.joining());

        return list.stream()
                .filter(i->{
                    String wordOfListSorted = Arrays.stream(i.split(""))
                            .sorted()
                            .collect(Collectors.joining());
                    if (wordOfListSorted.equals(wordSorted)){
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public static <R> R let(R a) {
        return a;
    }

    public static String[][] duplicates(String[][] a) {
            return Arrays.stream(a)
                    .flatMap(i->Arrays.stream(new String[][] {i, i}))
                    .toArray(String[][]:: new);

    }

        public static int cubeOdd(int[] a) {
            return Arrays.stream(a)
                    .map(i->i*i*i)
                    .filter(i->i%2!=0)
                    .sum();
        }
    public static void main(String[] args) throws ParseException {
        /* List<Map<String, String>> users;
        users = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1990-12-27", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );

        List<String> men = Sorter.takeOldestMans(users);
        System.out.println(men); // ["John Smith", "Andrey Petrov", "Vladimir Nikolaev"]

        SimpleDateFormat one = new SimpleDateFormat("yyyy-MM-dd");

        List<String> list1 = new ArrayList<>();

        for (Map<String, String> a : users) {
            for (Map.Entry<String, String> pair : a.entrySet()) {
                if (pair.getKey().equals("birthday")) {
                    Date d = one.parse(pair.getValue());
                    list1.add(d.toString());
                    System.out.println(one.format(d));
                }
            }
        }
        */
        int[] a = new int[]{1,2,3,4};
        int b = Arrays.stream(a)
                .map(i->i*i*i)
                .filter(i->i%2!=0)
                .sum();
        System.out.println(cubeOdd(a));
        System.out.println(let("Let"));

        List<Integer> list = List.of(4,0,-2,3,4,5);
        System.out.println(sameParityFilter(list));

        List<String> coll3 = List.of("crazer", "carer", "racar", "caers", "racer");
        System.out.println(filterAnagram("racer", coll3));

        String[] array = {"a", "c", "b"};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        List<Map<String, String>> users = List.of(
                Map.of("name", "Bronn", "gender", "male", "birthday", "1973-03-23"),
                Map.of("name", "Reigar", "gender", "male", "birthday", "1973-11-03"),
                Map.of("name", "Eiegon", "gender", "male", "birthday", "1963-11-03"),
                Map.of("name", "Sansa", "gender", "female", "birthday", "2012-11-03"),
                Map.of("name", "Jon", "gender", "male", "birthday", "1980-11-03"),
                Map.of("name", "Robb", "gender", "male", "birthday", "1980-05-14"),
                Map.of("name", "Tisha", "gender", "female", "birthday", "2012-11-03"),
                Map.of("name", "Rick", "gender", "male", "birthday", "2012-11-03"),
                Map.of("name", "Joffrey", "gender", "male", "birthday", "1999-11-03"),
                Map.of("name", "Edd", "gender", "male", "birthday", "1973-11-03")
        );

        Map<Integer, Long> result = App.getMenCountByYear(users);
        System.out.println(result); // {1980=2, 1999=1, 1963=1, 1973=3, 2012=1}

        List<Integer> coll1 = List.of(15, 10, 3, 4);
        System.out.println(App.findIndexOfNearest2(coll1, 7)); // 2

    }
}

