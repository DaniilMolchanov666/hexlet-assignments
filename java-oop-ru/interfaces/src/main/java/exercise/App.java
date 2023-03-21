package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class App {
    public static List<String>  buildApartmentsList(List<Home> listOfHomes, int count) {
        return listOfHomes.stream()
                .limit(count)
                .sorted(Comparator.comparingDouble(Home::getArea))
                .map(Home::toString)
                .toList();
    }

    public static void main(String[] args) {
        List<Home> apartments = new ArrayList<>(List.of(
                new Flat(41, 3, 10),
                new Cottage(125.5, 2),
                new Flat(80, 10, 2),
                new Cottage(150, 3)
        ));

        List<String> result = App.buildApartmentsList(apartments, 3);
        System.out.println(result);


        ReversedSequence r = new ReversedSequence("");
        System.out.println(r.subSequence(0, 10));
    }

}