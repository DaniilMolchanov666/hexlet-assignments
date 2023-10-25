package exercise.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class Post {

    private Long id;
    private String name;
    private String body;

    public Post(String name, String body) {
        this.name = name;
        this.body = body;
    }

    public Post(Long id, String name, String body) {
        this.name = name;
        this.body = body;
        this.id = id;
    }
}
