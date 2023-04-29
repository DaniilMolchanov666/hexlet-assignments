package exercise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
@Getter
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
