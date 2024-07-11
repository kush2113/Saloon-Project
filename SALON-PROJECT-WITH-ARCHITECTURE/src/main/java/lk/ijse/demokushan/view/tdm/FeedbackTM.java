package lk.ijse.demokushan.view.tdm;

import javafx.scene.control.Button;

public class FeedbackTM {
    private String feedbackId;
    private String comment;
    private String appointmentId;

    private Button btnRemove;



    public FeedbackTM() {

    }

    public FeedbackTM(String feedbackId, String comment, String appointmentId, Button btnRemove) {
        this.feedbackId = feedbackId;
        this.comment = comment;
        this.appointmentId = appointmentId;
        this.btnRemove = btnRemove;
    }

    public FeedbackTM(String feedbackId, String comment, String appointmentId) {
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

    public Button getBtnRemove() {
        return btnRemove;
    }

    public void setBtnRemove(Button btnRemove) {
        this.btnRemove = btnRemove;
    }

    @Override
    public String toString() {
        return "FeedbackTM{" +
                "feedbackId='" + feedbackId + '\'' +
                ", comment='" + comment + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", btnRemove=" + btnRemove +
                '}';
    }
}
