package lk.ijse.demokushan.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lk.ijse.demokushan.Util.Regex;
import lk.ijse.demokushan.bo.BOFactory;
import lk.ijse.demokushan.bo.custom.*;
import lk.ijse.demokushan.db.DbConnection;
import lk.ijse.demokushan.dto.AppointmentDTO;
import lk.ijse.demokushan.dto.FeedbackDTO;
import lk.ijse.demokushan.dto.PaymentDTO;
import lk.ijse.demokushan.entity.Appointment;
import lk.ijse.demokushan.entity.Feedback;
import lk.ijse.demokushan.entity.Payment;
import lk.ijse.demokushan.view.tdm.AppointmentTM;
import lk.ijse.demokushan.view.tdm.FeedbackTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentFormController {
    public Rectangle rectangal;

    public AnchorPane rootNode;
    public TableView TableAppointment;
    public TableColumn colApId;
    public TableColumn colStatus;
    public TableColumn colDate;
    public TableColumn colTime;
    public TextField txtApId;

    public TextField txtTime;
    public ComboBox cmbProductName;
    public DatePicker txtDatePicker;
    public ComboBox cmbCustomerId;
    public ComboBox cmbEmployeeId;
    public ComboBox<String> cmbHairCutStyle;
    public Label lblQty;
    public TextField txtQty;
    public Label lblQty1;
    public ComboBox cmbStatus;
    public TableColumn colEmployeeId;
    public TableColumn colCustomerId;
    public TableColumn colHaircutId;
    public TextField txtPaymentId;
    public Label lblPaymentId;
    public ComboBox cmbPaymentType;
    public Label lblTotal;
    public Label lblFeedbackId;
    public ComboBox cmbComment;
    public TableView TableFeedback;
    public TableColumn colFId;
    public TableColumn colComment;
    public TableColumn colAppointmentId;
    public Label lblTime;
    public Label lblDate;
    public TableColumn colAction;
    public TextField txtSearch;

    AppointmentBO appointmentBO =(AppointmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.APPOINTMENT);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    PaymentBO paymentBO =(PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    FeedbackBO feedbackBO =(FeedbackBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.FEEDBACK);
    HairCutBO hairCutBO = (HairCutBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HAIRCUT);

    public void initialize() {
        setcellValuese();
        setcellValues();
        loadAllAppointment();
        loadCmbHairStyle();
        loadCmbEmployee();
        loadCmbCustommer();
        loadCmbStatus();
        genarateNextAppointmentId();
        genarateNextPaymentId();
        loadCmbPaymentType();
        genarateNextFeedbackId();
        loadCmbComment();
        loadAllFeedback();
        showSelectedProductDetails();
        setDate();
        setTime();
        ObservableList<String> statusOptions = FXCollections.observableArrayList("Complete", "Incomplete");
        cmbStatus.setItems(statusOptions);

    }
    private void setDate() {
        LocalDate nowDate = LocalDate.now();
        lblDate.setText(String.valueOf(nowDate));
    }

    private void setTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalTime nowTime = LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String formattedTime = nowTime.format(formatter);
                    lblTime.setText(formattedTime);
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    private void showSelectedProductDetails() {
        AppointmentTM selectedUser = (AppointmentTM) TableAppointment.getSelectionModel().getSelectedItem();
        TableAppointment.setOnMouseClicked(event -> showSelectedProductDetails());
        if (selectedUser != null) {
            txtApId.setText(selectedUser.getAppointmentId());
            txtTime.setText(selectedUser.getTime());
            txtDatePicker.setValue(LocalDate.parse(selectedUser.getDate()));
            cmbEmployeeId.setValue(selectedUser.getEmployeeId());
            cmbCustomerId.setValue(selectedUser.getCustomerId());
            cmbHairCutStyle.setValue(selectedUser.getHairCutId());
            cmbStatus.setValue(selectedUser.getStatus());
        }
    }

    private void loadAllFeedback() {

        ObservableList<FeedbackTM> obList = FXCollections.observableArrayList();

        try {
            List<Feedback> feedbackList = feedbackBO.getAll();
            for (Feedback fbModle : feedbackList) {

                FeedbackTM TM = new FeedbackTM(fbModle.getFeedbackId(), fbModle.getComment(), fbModle.getAppointmentId());

                obList.add(TM);
                TableFeedback.setItems(obList);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCompleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String status = (String) cmbStatus.getValue();

        String paymentId = lblPaymentId.getText();
        String paymentType = (String) cmbPaymentType.getValue();
        String appointmentId = txtApId.getText();
        String amount = lblTotal.getText();

        String fId = lblFeedbackId.getText();
        String comment = (String) cmbComment.getValue();

        PaymentDTO payment = new PaymentDTO(paymentId, paymentType, appointmentId, amount);
        FeedbackDTO feedback = new FeedbackDTO(fId, comment, appointmentId);

        String hairCutId = hairCutBO.getHairCutId(cmbHairCutStyle.getValue());


        try {
            boolean isPlaced = appointmentBO.completeAppointment(payment, feedback, hairCutId);
            if (isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment complete Successfully!").show();

                initialize();
                lblTotal.setText("");
            } else {
                new Alert(Alert.AlertType.WARNING, "Failed to complete appointment!").show();
            }
            initialize();
            clearFields();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }

    private void loadCmbComment() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Bad");
        obList.add("Average");
        obList.add("Good");
        obList.add("Very good");
        cmbComment.setItems(obList);
        cmbComment.setValue("Average");
    }

    private void genarateNextFeedbackId() {
        try {
            String currentId = feedbackBO.generateNewID();

            String nextOrderId = genarateNextFeedbackId(currentId);
            lblFeedbackId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String genarateNextFeedbackId(String currentId) {
        if (currentId != null && currentId.matches("F\\d{3}")) {
            int idNum = Integer.parseInt(currentId.substring(2));
            return "F" + String.format("%03d", idNum + 1);
        } else {
            return "F001";
        }
    }

    private void loadCmbPaymentType() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        obList.add("Card");
        obList.add("Cash");

        cmbPaymentType.setItems(obList);
        cmbPaymentType.setValue("Cash");
    }

    private void genarateNextPaymentId() {
        try {
            String currentId = paymentBO.generateNewID();

            String nextPaymentId = genarateNextPaymentId(currentId);
            lblPaymentId.setText(nextPaymentId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String genarateNextPaymentId(String currentId) {
        if (currentId != null && currentId.matches("PA\\d{3}")) {
            int idNum = Integer.parseInt(currentId.substring(2));
            return "PA" + String.format("%03d", idNum + 1);
        } else {
            return "PA001";
        }
    }

    private void genarateNextAppointmentId() {
        try {
            String currentId = appointmentBO.generateNewID();

            String nextOrderId = genarateNextAppointmentId(currentId);
            txtApId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String genarateNextAppointmentId(String currentId) {
        if (currentId != null && currentId.matches("A\\d{3}")) {
            int idNum = Integer.parseInt(currentId.substring(2));
            return "A" + String.format("%03d", idNum + 1);
        } else {
            return "A001";
        }
    }

    private void loadCmbStatus() {

        ObservableList<String> obList = FXCollections.observableArrayList();

        try {

            List<String> nameList = appointmentBO.getStatus();

            for (String code : nameList) {
                obList.add(code);
            }
            cmbStatus.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCmbEmployee() {


        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> nameList = employeeBO.getEmployeeId();

            for (String code : nameList) {
                obList.add(code);
            }
            cmbEmployeeId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCmbHairStyle() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> nameList = hairCutBO.getHairCutNames();

            for (String code : nameList) {
                obList.add(code);
            }
            cmbHairCutStyle.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCmbCustommer() {


        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> nameList = customerBO.getCustomerId();

            for (String code : nameList) {
                obList.add(code);
            }
            cmbCustomerId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllAppointment() {

        ObservableList<AppointmentTM> obList = FXCollections.observableArrayList();

        try {
            List<Appointment> appointmentList = appointmentBO.getAll();
            for (Appointment apModle : appointmentList) {

                AppointmentTM TM = new AppointmentTM(apModle.getAppointmentId(), apModle.getTime(), apModle.getDate(), apModle.getEmployeeId(), apModle.getCustomerId(), apModle.getHairCutId(), apModle.getStatus());

                obList.add(TM);
                TableAppointment.setItems(obList);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setcellValuese() {

        colApId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colHaircutId.setCellValueFactory(new PropertyValueFactory<>("hairCutId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    private void setcellValues() {

        colFId.setCellValueFactory(new PropertyValueFactory<>("feedbackId"));
        colComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        colAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

    }

    public boolean isValied() {

        boolean idValied = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.APID, txtApId);

        return idValied;
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (isValied()) {

            String appointmentId = txtApId.getText();
            String time = txtTime.getText();
            LocalDate date = txtDatePicker.getValue();
            String employeeId = (String) cmbEmployeeId.getValue();
            String customerId = (String) cmbCustomerId.getValue();
            String hairCutName = (String) cmbHairCutStyle.getValue();
            String status = (String) cmbStatus.getValue();

            if (status == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a status").show();
                return;
            }


            List<String> employeeIdList = employeeBO.getEmployeeId(employeeId);
            System.out.println(employeeIdList.get(0));

            String hairCutId = hairCutBO.getHairCutId(hairCutName);

            AppointmentDTO appointmentDTO = new AppointmentDTO(appointmentId, time, date.toString(), employeeIdList.get(0), customerId, hairCutId, status);

            try {
                boolean isSaved = appointmentBO.add(appointmentDTO);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "All data saved").show();
                    initialize();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "reservation not saved").show();
                }
            } catch (SQLException e) {

                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }

    public void idKeyReleaseOnAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.demokushan.Util.TextField.APID, txtApId);
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
        genarateNextAppointmentId();
    }


    private void clearFields() {
        txtSearch.setText("");
        txtTime.setText("");
        txtDatePicker.setValue(null);
        cmbEmployeeId.setValue("");
        cmbCustomerId.setValue("");
        cmbHairCutStyle.setValue("");
        cmbStatus.setValue("");
        lblTotal.setText("");

    }

    public void btnUpadateOnAction(ActionEvent actionEvent) {

        String appointmentId = txtApId.getText();
        String time = txtTime.getText();
        LocalDate date = txtDatePicker.getValue();
        String emloyeeId = (String) cmbEmployeeId.getValue();
        String customerId = (String) cmbCustomerId.getValue();
        String hairCutId = (String) cmbHairCutStyle.getValue();
        String status = (String) cmbStatus.getValue();

       AppointmentDTO appointmentDTO = new AppointmentDTO(appointmentId, time, date.toString(), emloyeeId, customerId, hairCutId, status);

        try {
            boolean isUpdated = appointmentBO.update(appointmentDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "appointment updated!").show();

                initialize();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        if (isValidId()) {

            String id = txtApId.getText();

            try {
                boolean isDeleted = appointmentBO.delete(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Appointment deleted!").show();

                    initialize();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please enter valid Appointment ID correctly.");
            alert.showAndWait();
        }
    }

    public boolean isValidId() {
        boolean idValied = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.APID, txtApId);

        return idValied;
    }


    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(isValidIde()){

        String id = txtApId.getText();

        Appointment appointment = appointmentBO.search(id);

        if (appointment != null) {

            txtApId.setText(appointment.getAppointmentId());
            txtTime.setText(appointment.getTime());
            txtDatePicker.setValue(LocalDate.parse(appointment.getDate()));
            cmbEmployeeId.setValue(appointment.getEmployeeId());
            cmbCustomerId.setValue(appointment.getCustomerId());
            cmbHairCutStyle.setValue(appointment.getHairCutId());
            cmbStatus.setValue(appointment.getStatus());

            lblTotal.setText(hairCutBO.getHairCutPrice(appointment.getHairCutId()));

        } else {
            new Alert(Alert.AlertType.INFORMATION, "appointment not found!").show();
        }
    } else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("Validation Failed");
        alert.setContentText("Please enter valid Appointment ID correctly.");
        alert.showAndWait();
    }
}

public boolean isValidIde(){
    boolean idValied = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.APID, txtApId);

    return idValied ;
}

    public void btnBillOnAction(ActionEvent actionEvent) throws SQLException, JRException {

        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/SalonOneBill.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("appointmentId", txtApId.getText());

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);



        }

    public void btnSearchchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String id = txtSearch.getText();

        Appointment appointment = appointmentBO.search(id);

        if (appointment != null) {

            txtApId.setText(appointment.getAppointmentId());
            txtTime.setText(appointment.getTime());
            txtDatePicker.setValue(LocalDate.parse(appointment.getDate()));
            cmbEmployeeId.setValue(appointment.getEmployeeId());
            cmbCustomerId.setValue(appointment.getCustomerId());
            cmbHairCutStyle.setValue(appointment.getHairCutId());
            cmbStatus.setValue(appointment.getStatus());

            lblTotal.setText(hairCutBO.getHairCutPrice(appointment.getHairCutId()));

        } else {
            new Alert(Alert.AlertType.INFORMATION, "appointment not found!").show();
        }
    }
}

















//                JFXButton btnRemove = new JFXButton("Remove");
//                btnRemove.setOnAction((k) -> {
//                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
//                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
//
//                    if (type.orElse(no) == yes) {
//                        int selectedIndex = TableFeedback.getSelectionModel().getSelectedIndex();
//                        obList.remove(selectedIndex);
//                        TableFeedback.refresh();
//
//                    }
//                });
