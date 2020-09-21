package id.training.restapi.service;

import id.training.restapi.entity.Product;
import id.training.restapi.model.request.ProductRequestDTO;
import id.training.restapi.model.response.ProductResponseDTO;
import id.training.restapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts()
    {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public ProductResponseDTO createProducts(ProductRequestDTO requestDTO)
    {
        Product product = new Product();
        product.setProductName(requestDTO.getProductName());

        productRepository.save(product);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId(product.getId());
        productResponseDTO.setProductName(product.getProductName());

        return  productResponseDTO;
    }

    public ProductResponseDTO findProductsById(UUID id)
    {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return ProductResponseDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .build();
    }

    public ProductResponseDTO updateProductById(UUID id, String productName)
    {
        Product product = productRepository.findById(id)
                            .map(p -> {
                                p.setProductName(productName);
                                return productRepository.save(p);
                            })
                            .orElseThrow(() -> new EntityNotFoundException());

        return ProductResponseDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .build();
    }
}
