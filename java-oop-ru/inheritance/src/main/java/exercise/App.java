package exercise;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<String, String> attributes1 = new LinkedHashMap<>();
        attributes1.put("class", "m-10");
        attributes1.put("id", "10");
        attributes1.put("lang", "en");


        Tag p = new PairedTag("p", attributes1, "Text paragraph", new ArrayList<Tag>());
        System.out.println(p.toString());
    }
}
