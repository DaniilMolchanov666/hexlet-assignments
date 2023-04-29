package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public static ArrayList<String> validate(Address address) {

        ArrayList <String> list = new ArrayList<>();

        for(Field field: address.getClass().getDeclaredFields()) {
            NotNull n = field.getAnnotation(NotNull.class);
            if(n != null) {
                try {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        list.add(field.getName());
                    }
                } catch (NoSuchFieldError | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return list;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> map = new HashMap<>();

        for(Field field: address.getClass().getDeclaredFields()) {
            NotNull n = field.getAnnotation(NotNull.class);
            MinLength m = field.getAnnotation(MinLength.class);

                try {
                    field.setAccessible(true);

                    if(n != null && field.get(address) == null ) {
                        map.put(field.getName(), List.of("can not be null"));
                        continue;
                    }
                    if(m != null && field.get(address).toString().length() < m.minLength()) {
                        map.put(field.getName(), List.of("length less than " + m.minLength() + ""));
                    }

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

        return map;
    }

    public static void main(String[] args) {
        Address a = new Address(null, "2020", "22222", "3", "0");
        System.out.println(validate(a));
        System.out.println(advancedValidate(a));
    }
}