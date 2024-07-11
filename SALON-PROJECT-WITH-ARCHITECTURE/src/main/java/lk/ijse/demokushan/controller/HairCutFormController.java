package lk.ijse.demokushan.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import lk.ijse.demokushan.Util.Regex;
import lk.ijse.demokushan.bo.BOFactory;
import lk.ijse.demokushan.bo.custom.HairCutBO;
import lk.ijse.demokushan.entity.AddNewHairCut;
import lk.ijse.demokushan.entity.HairCut;
import lk.ijse.demokushan.entity.HairCutDetails;
import lk.ijse.demokushan.view.tdm.HairCutTM;
import lk.ijse.demokushan.dto.HairCutDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HairCutFormController {

    public AnchorPane rootNode;

    public Rectangle rectangal;
    public TextField txtHcId;
    public TextField txtStyle;
    public TextField txtPrice;
    public TableView TableHairCut;
    public TableColumn colHcId;
    public TableColumn colStyle;
    public TableColumn colPrice;
    public Label lblProductQtyOnHand;
    public Label lblProductUnitPrice;
    public Label lblProductId;
    public ComboBox<String> cmbProductName;
    public Label lblNetProductCost;
    public TextField txtQty;
    public TableColumn colProductId;
    public TableColumn colProductName;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colAction;
    public TableView<HairCutTM> tblHairCutProductDetails;
    public TableColumn colHairCutId;
    public TableColumn colHairCutStyle;
    public TableColumn coltPrice;
    public TableView tblHairCut;
    public TextField txtSearch;

    private ObservableList<HairCutTM> obList = FXCollections.observableArrayList();

    HairCutBO hairCutBO =(HairCutBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HAIRCUT);


    public  void initialize(){
        setcellValuese();
        setcellValues();
        loadAllHirCut();
        loadAllProductNames();
        genarateNextHairCutId();
        showSelectedProductDetails();
    }
    private void showSelectedProductDetails() {
        HairCutTM selectedUser = (HairCutTM) tblHairCut.getSelectionModel().getSelectedItem();
        tblHairCut.setOnMouseClicked(event -> showSelectedProductDetails());
        if (selectedUser != null) {

            txtHcId.setText(selectedUser.getHairCutId());
            txtStyle.setText(selectedUser.getHairCutStyle());
            txtPrice.setText(String.valueOf(selectedUser.getPrice()));


        }
    }


    private void genarateNextHairCutId() {
        try {
            String currentId = hairCutBO.generateNewID();

            String nextOrderId = generateNextHairCutId(currentId);
            txtHcId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextHairCutId(String currentId) {
        if (currentId != null && currentId.matches("HC\\d{3}")) {
            int idNum = Integer.parseInt(currentId.substring(2));
            return "HC" + String.format("%03d", idNum + 1);
        } else {
            return "HC001";
        }
    }

    private void loadAllProductNames() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> nameList = hairCutBO.getProductNames();

            for (String code : nameList) {
                obList.add(code);
            }

            cmbProductName.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private  void loadAllHirCut(){

        ObservableList<HairCutTM> obList = FXCollections.observableArrayList();

        try {
            List<HairCut> hairCutList = hairCutBO.getAll();
            for (HairCut hcModle : hairCutList){

                HairCutTM TM = new HairCutTM(hcModle.getHairCutId(),hcModle.getStyle(),hcModle.getPrice());

                obList.add(TM);
                tblHairCut.setItems(obList);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private  void setcellValuese(){

        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

    }
    public boolean isValied() {
        boolean idValied = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.HID, txtHcId);
        boolean styleValid = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.STYLE, txtStyle);
        boolean priceValid = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.PRICE, txtPrice);


        return idValied && styleValid && priceValid ;

    }


    public void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (isValied()){

        String hairCutId = txtHcId.getText();
        String hairCutStyle = txtStyle.getText();
        double price = Double.parseDouble(txtPrice.getText());

        var hairCut = new HairCut(hairCutId, hairCutStyle, price);
        List<HairCutDetails> odList = new ArrayList<>();



        for (int i = 0; i < tblHairCutProductDetails.getItems().size(); i++) {
            HairCutTM tm = obList.get(i);

            ResultSet productIdResultSet = hairCutBO.getProductId(tm.getProductName());
            String pId = null;
            if (productIdResultSet.next()) {
                pId = productIdResultSet.getString(1);
            }

            HairCutDetails hd = new HairCutDetails(
                    pId,
                    hairCutId,
                    tm.getQty()
            );

            odList.add(hd);
        }


        AddNewHairCut newHairCut = new AddNewHairCut(hairCut, odList);
        try {
            boolean isPlaced = hairCutBO.placeHairCut(newHairCut);
            if (isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "Hair Cut Added Successfully!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Failed to Add Hair Cut!").show();
            }
            initialize();
            clearFields();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

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
        Regex.setTextColor(lk.ijse.demokushan.Util.TextField.HID, txtHcId);
    }
    public void styleKeyReleaseOnAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.demokushan.Util.TextField.STYLE, txtStyle);
    }
    public void priceKeyReleaseOnAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.demokushan.Util.TextField.PRICE, txtPrice);
    }


    public void btnUpadateOnAction(ActionEvent actionEvent) {

        String hId = txtHcId.getText();
        String style = txtStyle.getText();
        double price = Double.parseDouble(txtPrice.getText());;

        HairCutDTO hairCut = new HairCutDTO(hId, style, price);

        try {
            boolean isUpdated = hairCutBO.update(hairCut);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "hairCut updated!").show();

                initialize();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
        genarateNextHairCutId();
    }

    private void clearFields() {

        txtSearch.setText("");
        txtStyle.setText("");
        txtPrice.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        if (isValidId()) {

            String id = txtHcId.getText();

            try {
                boolean isDeleted = hairCutBO.delete(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "haircut deleted!").show();

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
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }
    public boolean isValidId(){
        boolean idValied = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.HID, txtHcId);

        return idValied ;
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(isValidIde()){

        String id = txtHcId.getText();

        HairCut hairCut = hairCutBO.search(id);
        if (hairCut != null) {
            txtHcId.setText(hairCut.getHairCutId());
            txtStyle.setText(hairCut.getStyle());
            txtPrice.setText(String.valueOf(hairCut.getPrice()));

        } else {
            new Alert(Alert.AlertType.INFORMATION, "hairCut not found!").show();
        }
    } else{

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("Validation Failed");
        alert.setContentText("Please enter valid Customer ID correctly.");
        alert.showAndWait();
    }
}
     public boolean isValidIde(){

           boolean idValied = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.HID,txtHcId);

      return idValied ;
}

    public void cmbProductNameOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        getProductDetails();
    }

    private void getProductDetails() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        String name = (String) cmbProductName.getValue();

            List<String> detailList = hairCutBO.getProductDetails(name);

            lblProductId.setText(detailList.get(0));
            lblProductUnitPrice.setText(detailList.get(1));
            lblProductQtyOnHand.setText(detailList.get(2));
    }

    public void btnAddToTableOnAction(ActionEvent actionEvent) {

        String productId = lblProductId.getText();
        String productName = (String) cmbProductName.getValue();
        double unitPrice = Double.parseDouble(lblProductUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        System.out.println(qty);

        double total = qty * unitPrice;

        JFXButton btnRemove = new JFXButton("Remove");
        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int selectedIndex = tblHairCutProductDetails.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);

                tblHairCutProductDetails.refresh();
                calculateNetTotal();
            }
        });

        HairCutTM cartItem = new HairCutTM(productId, productName, unitPrice, qty, total, btnRemove);
        obList.add(cartItem);

        tblHairCutProductDetails.setItems(obList);
        calculateNetTotal();


    }

    private void calculateNetTotal() {
        double netTotal = 0;
        for (HairCutTM item : obList) {
            netTotal += item.getTotal();
        }
        lblNetProductCost.setText(String.valueOf(netTotal));
    }
    private  void setcellValues() {

        colHairCutId.setCellValueFactory(new PropertyValueFactory<>("hairCutId"));
        colHairCutStyle.setCellValueFactory(new PropertyValueFactory<>("hairCutStyle"));
        coltPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

//        initialize();

    }

    public void btnAddOnAction(ActionEvent actionEvent) {

    }

    public void btnSearchchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtSearch.getText();

        HairCut hairCut = hairCutBO.search(id);
        if (hairCut != null) {
            txtHcId.setText(hairCut.getHairCutId());
            txtStyle.setText(hairCut.getStyle());
            txtPrice.setText(String.valueOf(hairCut.getPrice()));

        } else {
            new Alert(Alert.AlertType.INFORMATION, "hairCut not found!").show();
        }

    }
}
