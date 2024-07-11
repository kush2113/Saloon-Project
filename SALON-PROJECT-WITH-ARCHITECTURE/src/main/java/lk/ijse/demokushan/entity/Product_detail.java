package lk.ijse.demokushan.entity;

public class Product_detail {
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

    public Product_detail(String sname, String pname) {
        this.sname = sname;
        this.pname = pname;
    }
}
