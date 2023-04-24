package exercise;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class InMemoryKV implements KeyValueStorage{

    private String key;

    private String value;
    public Map<String, String> map;

    public InMemoryKV(Map<String, String> map) {
        //Map<String, String> newMap = map.entrySet().stream()
        //        .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        this.map = map;
    }

    @Override
    public void set(String key, String value) {
        map.put(key, value);

    }

    @Override
    public void unset(String key) {
        map.remove(key);

    }

    @Override
    public String get(String key, String defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return this.map;
    }

}
