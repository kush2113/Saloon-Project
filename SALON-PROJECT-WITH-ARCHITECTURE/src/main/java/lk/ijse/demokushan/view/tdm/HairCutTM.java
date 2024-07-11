package lk.ijse.demokushan.view.tdm;
import com.jfoenix.controls.JFXButton;

public class HairCutTM {


     private String hairCutId;
     private String hairCutStyle;
    private Double price;

    private String productId;
    private String productName;
    private double unitPrice;
    private int qty;
    private double total;
    private JFXButton btnRemove;





    public HairCutTM() {}

    public HairCutTM(String productId, String productName, double unitPrice, int qty, double total, JFXButton btnRemove) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
        this.btnRemove = btnRemove;
    }

    public HairCutTM(String hairCutId, String hairCutStyle, double price) {
        this.hairCutId = hairCutId;
        this.hairCutStyle = hairCutStyle;
        this.price = price;
    }

    public String getHairCutId() {
        return hairCutId;
    }

    public void setHairCutId(String hairCutId) {
        this.hairCutId = hairCutId;
    }

    public String getHairCutStyle() {
        return hairCutStyle;
    }

    public void setHairCutStyle(String hairCutStyle) {
        this.hairCutStyle = hairCutStyle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public JFXButton getBtnRemove() {
        return btnRemove;
    }

    public void setBtnRemove(JFXButton btnRemove) {
        this.btnRemove = btnRemove;
    }

    @Override
    public String toString() {
        return "HairCutTM{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", total=" + total +
                ", btnRemove=" + btnRemove +
                '}';
    }

}
