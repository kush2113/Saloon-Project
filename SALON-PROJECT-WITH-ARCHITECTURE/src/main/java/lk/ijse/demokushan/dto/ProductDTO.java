package lk.ijse.demokushan.dto;


public class ProductDTO {
    private String productId;
    private String name;
    private String unitPrice;
    private String qtyOnHand;

   // private double total;


    public ProductDTO() {

    }

    public ProductDTO(String productId, String name, String unitPrice, String qtyOnHand) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

}
