package lk.ijse.demokushan.dto;

public class PaymentDTO {
    private String paymentId;
    private String paymentType;

    private String appintmentId;
    private String amount;





    public PaymentDTO() {

    }

    public PaymentDTO(String paymentId, String paymentType, String appintmentId, String amount) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.appintmentId = appintmentId;
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

    public String getAppintmentId() {
        return appintmentId;
    }

    public void setAppintmentId(String appintmentId) {
        this.appintmentId = appintmentId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}

