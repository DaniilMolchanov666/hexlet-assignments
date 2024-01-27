package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.exception.ResourceAlreadyExistsException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody Product p) {
        if (productRepository.findAll().contains(p)) {
            throw new ResourceAlreadyExistsException("This product already have in list!");
        } else if (p.getId() < 0) {
            throw new ResourceAlreadyExistsException("product 'id' must be more then 0!");
        }
        productRepository.save(p);
        return ResponseEntity.status(201).body(p);
    }

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {
        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }

    @PutMapping(path = "/{id}")
    public Product update(@PathVariable long id, @RequestBody Product productData) {
        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        product.setTitle(productData.getTitle());
        product.setPrice(productData.getPrice());

        productRepository.save(product);

        return product;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
