package lk.ijse.demokushan.dto;

public class Product_detailDTO {
    private String sname;
    private String pname;

    @Override
    public String toString() {
        return "Product_detail{" +
                "sname='" + sname + '\'' +
                ", pname='" + pname + '\'' +
                '}';
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Product_detailDTO(String sname, String pname) {
        this.sname = sname;
        this.pname = pname;
    }
}
