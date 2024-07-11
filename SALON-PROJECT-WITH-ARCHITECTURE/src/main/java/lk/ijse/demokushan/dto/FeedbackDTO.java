package lk.ijse.demokushan.dto;

public class FeedbackDTO {
    private String feedbackId;
    private String comment;
    private String appointmentId;


    public FeedbackDTO() {

    }

    public FeedbackDTO(String feedbackId, String comment, String appointmentId) {
        this.feedbackId = feedbackId;
        this.comment = comment;
        this.appointmentId = appointmentId;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
}
