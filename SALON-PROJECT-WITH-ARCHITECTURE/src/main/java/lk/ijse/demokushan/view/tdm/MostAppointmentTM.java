package lk.ijse.demokushan.view.tdm;

public class MostAppointmentTM {
    private String customerId;
    private String customerName;

    private int visitCount;

    public MostAppointmentTM(){}

    public MostAppointmentTM(String customerId, String customerName, int visitCount) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.visitCount = visitCount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    @Override
    public String toString() {
        return "MostAppointmentTM{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", visitCount=" + visitCount +
                '}';
    }
}
