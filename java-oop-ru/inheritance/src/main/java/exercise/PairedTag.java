package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class PairedTag extends Tag{

    private String tag1;

    private Map<String, String> map;

    private String body;

    private List<Tag> list;

    private final StringBuilder build = new StringBuilder();

    public PairedTag(String tag1, Map<String, String> map, String body, List<Tag> list) {
        this.tag1 = tag1;
        this.map = map;
        this.body = body;
        this.list = list;
    }

    @Override
    public String toString() {
        build.append("<").append(tag1);
        map.forEach((key, value) -> build.append(" " + key).append("=").append("\"").append(value).append("\""  ));
        build.append(">").append(body);
        for (Tag tag: list) {
            build.append(tag.toString());
        }
        build.append("</").append(tag1).append(">");
        return build.toString();

    }
}
