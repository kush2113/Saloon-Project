package lk.ijse.demokushan.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import lk.ijse.demokushan.bo.BOFactory;
import lk.ijse.demokushan.bo.custom.FeedbackBO;

import java.sql.SQLException;

public class FeedbackFormController {

    public AnchorPane rootNode;

    public Rectangle rectangal;
    public TextField txtFId;
    public TextField txtComment;
    public TableView TableFeedback;
    public TableColumn colFId;

    public TableColumn colComment;

    public TableColumn colAppointmentId;
    public ComboBox cmbAppointmentId;
    public TableColumn colAction;

    FeedbackBO feedbackBO =(FeedbackBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.FEEDBACK);


    public  void initialize() {
//        setcellValues();
//        loadAllFeedback();
//        loadCmbAppointment();
//        genarateNextFeedbackId();

    }
//    private void genarateNextFeedbackId() {
//        try {
//            String currentId = FeedbackRepo.getCurrentId();
//
//            String nextOrderId = genarateNextFeedbackId(currentId);
//            txtFId.setText(nextOrderId);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private String genarateNextFeedbackId(String currentId) {
//        if (currentId != null && currentId.matches("F\\d{3}")) {
//            int idNum = Integer.parseInt(currentId.substring(2));
//            return "F" + String.format("%03d", idNum + 1);
//        } else {
//            return "F001";
//        }
//    }
//
//
//
//
//    private void loadCmbAppointment() {
//
//
//        ObservableList<String> obList = FXCollections.observableArrayList();
//        try {
//            List<String> nameList = AppointmentRepo.getAppointmentId();
//
//            for (String code : nameList) {
//                obList.add(code);
//            }
//            cmbAppointmentId.setItems(obList);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
////    }
//        private void loadAllFeedback () {
//
//            ObservableList<FeedbackTM> obList = FXCollections.observableArrayList();
//
//            try {
//                List<Feedback> feedbackList = FeedbackRepo.getAll();
//                for (Feedback fbModle : feedbackList) {
//
//                    FeedbackTM TM = new FeedbackTM(fbModle.getFeedbackId(), fbModle.getComment(), fbModle.getAppointmentId());
//
//                    obList.add(TM);
//                    TableFeedback.setItems(obList);
//
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//
//        }
//        private  void setcellValues() {
//
//            colFId.setCellValueFactory(new PropertyValueFactory<>("feedbackId"));
//            colComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
//            colAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
//
//        }

//    @FXML
////
    public void btnSaveOnAction(ActionEvent event) throws SQLException {}
////
////            String feedbackId = txtFId.getText();
////            String comment = txtComment.getText();
////            String rating = txtRating.getText();
////            String appointmentId = (String) cmbAppointmentId.getValue();
////
////        List<String>appointmentIdList = AppointmentRepo.getAppointmentId(appointmentId);
////        System.out.println(appointmentIdList.get(0));
////
////            Feedback feedback = new Feedback(feedbackId,comment,appointmentIdList.get(0));
////
////            try {
////                boolean isSaved = FeedbackRepo.save(feedback);
////                if (isSaved) {
////                    new Alert(Alert.AlertType.CONFIRMATION, "feedback saved!").show();
//////                clearFields();
////                    initialize();
////                }
////            } catch (SQLException e) {
////                throw new RuntimeException(e);
////            }
////        }
//
    public void btnClearOnAction(ActionEvent actionEvent) {}
//        txtFId.setText("");
//        txtComment.setText("");
//        txtRating.setText("");
//        cmbAppointmentId.setValue("");
//    }
//
    public void btnDeleteOnAction(ActionEvent actionEvent) {}
//        String id = txtFId.getText();
//
//        try {
//            boolean isDeleted = FeedbackRepo.delete(id);
//            if(isDeleted) {
//                new Alert(Alert.AlertType.CONFIRMATION, "feedback deleted!").show();
//
//                initialize();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//    }
    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException {}
//        String id = txtFId.getText();
//
//        Feedback feedback = FeedbackRepo.searchById(id);
//        if (feedback != null) {
//            txtFId.setText(feedback.getFeedbackId());
//            txtComment.setText(feedback.getComment());
//            txtRating.setText(feedback.getRating());
//            cmbAppointmentId.setValue(feedback.getAppointmentId());
//        } else {
//            new Alert(Alert.AlertType.INFORMATION, "feedback not found!").show();
//        }
//    }


    }
