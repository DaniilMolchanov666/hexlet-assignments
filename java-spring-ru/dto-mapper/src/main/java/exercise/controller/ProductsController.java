package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

   @GetMapping()
   @ResponseStatus(HttpStatus.OK)
   public List<ProductDTO> getAllProductsDTO() {
       return productRepository.findAll().stream().map(product ->
               productMapper.map(product)).toList();
   }

   @GetMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
    public ProductDTO getCurrentProductDTO(@PathVariable long id) {
       var p = productRepository.findById(id).get();
       return productMapper.map(p);
   }

   @PostMapping()
   @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addNewProductDTO(@RequestBody ProductCreateDTO productCreateDTO) {
       var product = productMapper.productCreateDTOtoProduct(productCreateDTO);
       productRepository.save(product);
       return productMapper.map(product);
   }

   @PutMapping("/{id}")
    public ProductDTO updateProduct(@RequestBody ProductUpdateDTO productUpdateDTO,
                              @PathVariable long id) {
       var product = productRepository.findById(id).get();
       productMapper.productUpdateDTOToProduct(productUpdateDTO, product);
       productRepository.save(product);
       return productMapper.map(product);
   }
}
