package lk.ijse.demokushan.controller;

import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.demokushan.entity.Appointment;
import lk.ijse.demokushan.entity.Customer;
import lk.ijse.demokushan.view.tdm.MostAppointmentTM;
import lk.ijse.demokushan.dao.custom.*;
import lk.ijse.demokushan.dao.DAOFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static javafx.fxml.FXMLLoader.load;

public class DashBoardFormController {

    public Rectangle rectangal;

    public AnchorPane rootNode;
    public Button CustomerBtn;
    public Button EmployeeBtn;
    public Button HairCutBtn;
    public Button PaymentBtn;
    public Button SupplierBtn;
    public Button ProductBtn;
    public Button FeedbackBtn;
    public Button AppointmentBtn;
    public AnchorPane root;
    public Label lblCustomer;
    public Label lblCount;
    public AnchorPane root1;
    public AnchorPane root2;
    public Label lblApCount;
    public Label lblAppointment;
    public AnchorPane root21;
    public Label lblCompleteCount;
    public Label lblCompleteAppointment;
    public AnchorPane root212;
    public Label lblInCompleteCount;
    public Label lblInCompleteAppointment2;
    public ImageView cmbMenu;
    public ComboBox cmbMenuBar;
    public AnchorPane root211;
    public Label lblEmployeeCount;
    public Label lblEmployee;
    public Label lblFullPaymentCount;
    public Label lblFullPayment;
    public AnchorPane root2111;
    public Button BtnFeedback;
    public Label lblSupplierCount;
    public Label lblGoodCommentCount;
    public javafx.scene.chart.PieChart PieChart;
    public AnchorPane rootNod25;
    public Label lblDate;
    public javafx.scene.chart.BarChart BarChart;

    @FXML
    private Label lblTime;

    private int cCount;

    private int aCount;

    private int eCount;

    private int sCount;


    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    AppointmentDAO appointmentDAO = (AppointmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.APPOINTMENT);
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);



    public void initialize() throws ClassNotFoundException {

        setTime();
        setDate();
        pieChartConnect();
        animatePieChart();

        int completeCount = 0;
        int incompleteCount = 0;
        double fullPaymentCount = 0;

        try {
            cCount = customerDAO.getCustomerCount();
            completeCount = appointmentDAO.getCompleteAppointmentCount();
            incompleteCount = appointmentDAO.getIncompleteAppointmentCount();
            aCount = completeCount + incompleteCount;
            eCount = employeeDAO.getEmployeeCount();
            sCount = supplierDAO.getSupplierCount();

            fullPaymentCount = paymentDAO.getAllPaymentCount();


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setSupplierCount(sCount);
        setCount(cCount);
        setAppointmentCount(aCount);
        setEmployeeCount(eCount);
        setCompleteAppointmentCount(completeCount);
        setIncompleteAppointmentCount(incompleteCount);
        setFullPaymentCount(fullPaymentCount);

    }

    private void setCount(int cCount) {
        lblCount.setText(String.valueOf(cCount));
    }

    private void setSupplierCount(int sCount) {
        lblSupplierCount.setText(String.valueOf(sCount));
    }

    private void setAppointmentCount(int aCount) {
        lblApCount.setText(String.valueOf(aCount));
    }

    private void setCompleteAppointmentCount(int completeCount) {
        lblCompleteCount.setText(String.valueOf(completeCount));
    }

    private void setIncompleteAppointmentCount(int incompleteCount) {
        lblInCompleteCount.setText(String.valueOf(incompleteCount));
    }

    private void setEmployeeCount(int aCount) {
        lblEmployeeCount.setText(String.valueOf(eCount));

    }

    private void setFullPaymentCount(double fullPaymentCount) {

        lblFullPaymentCount.setText(String.valueOf(fullPaymentCount));
    }

    public void pieChartConnect() throws ClassNotFoundException {

        List<MostAppointmentTM> itemList = null;
        try {
            itemList = queryDAO.getMostSellItem();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Customer item;
        for (MostAppointmentTM sellItem : itemList) {
            try {
                item = customerDAO.search(sellItem.getCustomerId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data(item.getCustomerId(), sellItem.getVisitCount())
                    );

            PieChart.getData().addAll(pieChartData);

        }
    }

    private void animatePieChart() {
        PieChart.setOpacity(0);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(PieChart.opacityProperty(), 1))
        );

        timeline.setOnFinished(event -> setPieChartData());

        timeline.play();
    }

    private void setPieChartData() {

        for (PieChart.Data data : PieChart.getData()) {
//            data.getNode().setScaleX(0);
//            data.getNode().setScaleY(0);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1),
                            new KeyValue(data.getNode().scaleXProperty(), 1),
                            new KeyValue(data.getNode().scaleYProperty(), 1))
            );
            timeline.play();
        }
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


    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("/view/employee_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane load = fxmlLoader.load();
        root1.getChildren().clear();
        root1.getChildren().add(load);

    }

    public void btnProductOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("/view/product_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane load = fxmlLoader.load();
        root1.getChildren().clear();
        root1.getChildren().add(load);

    }

//        URL resource = getClass().getResource("/view/feedback_form.fxml");
//        FXMLLoader fxmlLoader = new FXMLLoader(resource);
//        AnchorPane load = fxmlLoader.load();
//        root1.getChildren().clear();
//        root1.getChildren().add(load);


    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("/view/supplier_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane load = fxmlLoader.load();
        root1.getChildren().clear();
        root1.getChildren().add(load);

    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("/view/customer_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane load = fxmlLoader.load();
        root1.getChildren().clear();
        root1.getChildren().add(load);

    }

    public void btnHariCutonAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("/view/haircut_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane load = fxmlLoader.load();
        root1.getChildren().clear();
        root1.getChildren().add(load);
        ;
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("/view/payment_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane load = fxmlLoader.load();
        root1.getChildren().clear();
        root1.getChildren().add(load);
        ;
    }

    public void btnAppointmentOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("/view/appointmnt_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane load = fxmlLoader.load();
        root1.getChildren().clear();
        root1.getChildren().add(load);
    }

    public void btnDashBoardonAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/home_page.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane load = fxmlLoader.load();
        root1.getChildren().clear();
        root1.getChildren().add(load);
    }


    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {

        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
    }

    public void btnFeedbackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/feedback_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.BtnFeedback.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
    }

    public void btnProfileOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/profile_details.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane load = fxmlLoader.load();
        rootNod25.getChildren().clear();
        rootNod25.getChildren().add(load);


    }
}





