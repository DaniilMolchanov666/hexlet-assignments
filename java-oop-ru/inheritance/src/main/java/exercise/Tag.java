package exercise;

import java.util.List;
import java.util.Map;

public class Tag {
    private String tag1;

    private Map<String, String> map;

    private String body;

    private List<Tag> list;

    private final StringBuilder build = new StringBuilder();

    public String toString() {
        build.append("<").append(tag1);
        map.forEach((key, value) -> build.append(" " + key).append("=").append("\"").append(value).append("\""  ));
        build.append(">");
        return build.toString();
    }
}
