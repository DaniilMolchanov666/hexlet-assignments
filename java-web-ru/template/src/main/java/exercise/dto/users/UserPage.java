package exercise.dto.users;

import exercise.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserPage {

    private final int id;

    private final List<User> users;

    public UserPage(int id, List<User> users) {
        this.id = id;
        this.users = users;
    }

    public User getUser() {
        return this.users.stream()
                .filter(user -> id == user.getId())
                .findFirst()
                .orElse(null);
    }
}
