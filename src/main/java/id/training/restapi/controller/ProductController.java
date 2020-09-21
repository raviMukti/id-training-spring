package id.training.restapi.controller;

import id.training.restapi.entity.Product;
import id.training.restapi.model.request.ProductRequestDTO;
import id.training.restapi.model.response.ProductResponseDTO;
import id.training.restapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/onlineshop")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO> createProducts(@RequestBody ProductRequestDTO request)
    {
        ProductResponseDTO productResponseDTO = productService.createProducts(request);

        return  new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> findProductsById(@PathVariable UUID id)
    {
        ProductResponseDTO productResponseDTO = productService.findProductsById(id);
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponseDTO> updateProductById(@PathVariable UUID id, @RequestBody String productName)
    {
        ProductResponseDTO responseDTO = productService.updateProductById(id, productName);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
