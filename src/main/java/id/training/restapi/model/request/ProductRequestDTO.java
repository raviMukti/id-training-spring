package id.training.restapi.model.request;

public class ProductRequestDTO {

    private String productName;

    public ProductRequestDTO(String id, String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
