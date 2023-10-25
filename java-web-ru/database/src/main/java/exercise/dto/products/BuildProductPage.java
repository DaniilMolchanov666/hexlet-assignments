package exercise.dto.products;

import java.util.List;
import java.util.Map;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuildProductPage {
    private String title;
    private int price;
    private Map<String, List<ValidationError<Object>>> errors;
}
