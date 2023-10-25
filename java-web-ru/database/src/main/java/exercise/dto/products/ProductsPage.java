package exercise.dto.products;

import java.util.List;
import exercise.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import exercise.dto.BasePage;

@AllArgsConstructor
@Getter
public class ProductsPage extends BasePage {
    private List<Product> products;
}
