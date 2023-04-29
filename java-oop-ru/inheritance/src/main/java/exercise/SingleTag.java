package exercise;

import java.util.Map;

public class SingleTag extends Tag{

    private final String tag;
    private final Map<String, String> map;

    private final StringBuilder build = new StringBuilder();

    public SingleTag(String tag, Map<String, String> map) {
        this.tag = tag;
        this.map = map;
    }

    @Override
    public String toString() {
        build.append("<").append(tag);
        map.forEach((key, value) -> build.append(" " + key).append("=").append("\"").append(value).append("\""  ));
        build.append(">");
        return build.toString();
    }
}
