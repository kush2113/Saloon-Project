package lk.ijse.demokushan.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import lk.ijse.demokushan.Util.Regex;
import lk.ijse.demokushan.bo.BOFactory;
import lk.ijse.demokushan.bo.custom.SupplierBO;
import lk.ijse.demokushan.dto.SupplierDTO;
import lk.ijse.demokushan.entity.Supplier;
import lk.ijse.demokushan.view.tdm.SupplierTM;


import java.sql.SQLException;
import java.util.List;

public class SupplierFormController {
    public TextField txtSId;
    public TextField txtName;
    public TextField txtNic;
    public TextField txtPNumber;

    public AnchorPane rootNode;

    public Rectangle rectangal;
    public TableView tableSupplier;
    public TableColumn colSId;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colNumber;


    SupplierBO supplierBO =(SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);


    public void initialize() {
        setcellValues();
        loadAllSupplier();
        genarateNextSupplierId();
        showSelectedProductDetails();
    }
    private void showSelectedProductDetails() {
        SupplierTM selectedUser = (SupplierTM) tableSupplier.getSelectionModel().getSelectedItem();
        tableSupplier.setOnMouseClicked(event -> showSelectedProductDetails());
        if (selectedUser != null) {

            txtSId.setText(selectedUser.getSupplierId());
            txtName.setText(selectedUser.getName());
            txtNic.setText(selectedUser.getNic());
            txtPNumber.setText(selectedUser.getPhoneNumber());

        }
    }

    private void genarateNextSupplierId() {
        try {
            String currentId = supplierBO.generateNewID();

            String nextOrderId = genarateNextSupplierId(currentId);
            txtSId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String genarateNextSupplierId(String currentId) {
        if (currentId != null && currentId.matches("S\\d{3}")) {
            int idNum = Integer.parseInt(currentId.substring(2));
            return "S" + String.format("%03d", idNum + 1);
        } else {
            return "S001";
        }
    }

    private void loadAllSupplier() {

        ObservableList<SupplierTM> obList = FXCollections.observableArrayList();

        try {
            List<Supplier> supList = supplierBO.getAll();
            for (Supplier supModle : supList) {

                SupplierTM TM = new SupplierTM(supModle.getSupplierId(), supModle.getName(), supModle.getNic(), supModle.getPhoneNumber());

                obList.add(TM);
                tableSupplier.setItems(obList);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void setcellValues() {

        colSId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

    }

    public boolean isValied() {
        boolean idValied = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.SID, txtSId);
        boolean nameValid = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.NAME, txtName);
        boolean validNic = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.NIC, txtNic);
        boolean validPhone = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.PHONENUMBER, txtPNumber);


        return nameValid && idValied && validNic && validPhone;
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        if (isValied()) {

            String sId = txtSId.getText();
            String name = txtName.getText();
            String nic = txtNic.getText();
            String phoneNumber = txtPNumber.getText();

            SupplierDTO supplierDTO = new SupplierDTO(sId, name, nic, phoneNumber);

            try {
                boolean isSaved = supplierBO.add(supplierDTO);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Saved!").show();
//                clearFields();
                    initialize();
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
        Regex.setTextColor(lk.ijse.demokushan.Util.TextField.SID, txtSId);
    }

    public void nameKeyReleaseOnAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.demokushan.Util.TextField.NAME, txtName);
    }

    public void numberKeyReleaseOnAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.demokushan.Util.TextField.PHONENUMBER, txtPNumber);
    }

    public void nicKeyReleaseOnAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.demokushan.Util.TextField.NIC, txtNic);
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtSId.setText("");
        txtName.setText("");
        txtNic.setText("");
        txtPNumber.setText("");

    }

    public void btnUpadateOnAction(ActionEvent actionEvent) {

        String supplierId = txtSId.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String phoneNumber = txtPNumber.getText();


        SupplierDTO supplierDTO = new SupplierDTO(supplierId, name, nic, phoneNumber);

        try {
            boolean isUpdated = supplierBO.update(supplierDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier updated!").show();

                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {

        if (isValidNic()) {

            String nic = txtNic.getText();

            try {
                boolean isDeleted = supplierBO.delete(nic);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "supplier deleted!").show();

                    initialize();
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
            alert.setContentText("Please enter valid Supplier NIC correctly.");
            alert.showAndWait();
        }
    }

    public boolean isValidNic() {
        boolean nicValied = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.NIC, txtNic);

        return nicValied;
    }


    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (isValidNIC()) {

            String nic = txtNic.getText();

            Supplier supplier = supplierBO.search(nic);
            if (supplier != null) {
                txtSId.setText(supplier.getSupplierId());
                txtName.setText(supplier.getName());
                txtNic.setText(supplier.getNic());
                txtPNumber.setText(supplier.getPhoneNumber());

            } else {
                new Alert(Alert.AlertType.INFORMATION, "supplier not found!").show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please enter valid Supplier NIC correctly.");
            alert.showAndWait();
        }
    }

    public boolean isValidNIC() {
        boolean nicValied = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.NIC, txtNic);

        return nicValied;
    }

}
