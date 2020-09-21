package id.training.restapi.service;

import id.training.restapi.entity.Product;
import id.training.restapi.model.request.ProductRequestDTO;
import id.training.restapi.model.response.ProductResponseDTO;
import id.training.restapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Product> product = productRepository.findById(id);
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(product.get().getId(), product.get().getProductName());
        return productResponseDTO;
    }
}
