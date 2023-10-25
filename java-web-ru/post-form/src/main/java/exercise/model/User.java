package exercise.model;

import io.javalin.validation.ValidationError;
import io.javalin.validation.ValidationException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@ToString
public final class User {

    private Long id;
    private String firstName;

    @ToString.Include
    private String lastName;

    private String email;
    private String password;

    private Map<String, List<ValidationError<Object>>> errors;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, List<ValidationError<Object>>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<ValidationError<Object>>> errors) {
        this.errors = errors;
    }
}
