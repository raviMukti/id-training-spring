package id.training.restapi.model.response;

import java.util.UUID;

public class ProductResponseDTO {
    private UUID id;

    private String productName;

    public ProductResponseDTO()
    {
        super();
    }

    public ProductResponseDTO(UUID id, String productName) {
        this.id = id;
        this.productName = productName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
