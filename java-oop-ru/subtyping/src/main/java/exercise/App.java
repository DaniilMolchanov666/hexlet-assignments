package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class App {
    public static void swapKeyValue(KeyValueStorage storage) {

        Map<String, String> newMap = storage.toMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    }

    public static void main(String[] args) {
        KeyValueStorage storage = new InMemoryKV(Map.of("key", "value", "key2", "value2"));
        App.swapKeyValue(storage);
        storage.set("jopa", "slona");
        storage.get("key", "default"); // "default"
        storage.get("value", "default"); // "key"
        System.out.println(storage.toMap() + storage.get("key", "default") // "default"
        + storage.get("value", "default"));
    }
}
