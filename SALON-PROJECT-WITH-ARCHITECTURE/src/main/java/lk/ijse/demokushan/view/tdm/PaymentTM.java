package lk.ijse.demokushan.view.tdm;

public class PaymentTM {
    private String paymentId;
    private String paymentType;
    private String appointmentId;
    private String amount;






    public PaymentTM() {

    }

    public PaymentTM(String paymentId, String paymentType, String appointmentId, String amount) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.appointmentId = appointmentId;
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "paymentTm{" +
                "paymentId='" + paymentId + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", appointment='" + appointmentId + '\'' +
                ", amount='" + amount + '\'' +


                '}';
    }
}
