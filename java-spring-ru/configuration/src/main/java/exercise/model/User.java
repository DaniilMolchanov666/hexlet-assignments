package exercise.model;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Setter
@Getter
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
}
