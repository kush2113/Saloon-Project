package lk.ijse.demokushan.entity;

public class HairCut {
    String hairCutId;
    String style;
    double price;

    public  HairCut(){

    }

    public HairCut(String hairCutId,String style,double price){
        this.hairCutId = hairCutId;
        this.style = style;
        this.price = price;
    }

    public String getHairCutId() {
        return hairCutId;
    }

    public void setHairCutId(String hairCutId) {
        this.hairCutId = hairCutId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
