package lk.ijse.demokushan.view.tdm;

public class SupplierTM {

    private String supplierId;
    private String name;
    private String nic;
    private String phoneNumber;




    public SupplierTM() {

    }

    public SupplierTM(String supplierId, String name, String nic, String phoneNumber) {
        this.supplierId = supplierId;
        this.name = name;
        this.nic = nic;
        this.phoneNumber = phoneNumber;

    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "supplierTM{" +
                "supplierId='" + supplierId + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
