package lk.ijse.demokushan.entity;

public class HairCutDetails {
    private String productId;
    private String hairCutId;
    private int productQty;

    public HairCutDetails(){}
    public HairCutDetails(String productId, String hairCutId, int productQty) {
        this.productId = productId;
        this.hairCutId = hairCutId;
        this.productQty = productQty;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getHairCutId() {
        return hairCutId;
    }

    public void setHairCutId(String hairCutId) {
        this.hairCutId = hairCutId;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    @Override
    public String toString() {
        return "HairCutDetails{" +
                "productId='" + productId + '\'' +
                ", hairCutId='" + hairCutId + '\'' +
                ", productQty=" + productQty +
                '}';
    }
}


