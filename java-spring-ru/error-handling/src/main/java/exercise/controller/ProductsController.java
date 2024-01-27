package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id " + id + " not found"));

    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            productRepository.save(product);
            return product;
        }
        throw new ResourceNotFoundException("Product with id " + id + " not found");
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
