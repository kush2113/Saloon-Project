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
import lk.ijse.demokushan.Util.Regex;
import lk.ijse.demokushan.bo.BOFactory;
import lk.ijse.demokushan.bo.custom.EmployeeBO;
import lk.ijse.demokushan.dao.DAOFactory;
import lk.ijse.demokushan.dto.EmployeeDTO;
import lk.ijse.demokushan.entity.Employee;
import lk.ijse.demokushan.view.tdm.EmployeeTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.demokushan.Util.TextField.SALARY;

public class EmployeeFormController {


    public TextField txtName;
    public TextField txtId;
    public TextField txtposition;
    public TextField txteNumber;
    public TextField txtHId;

    public TableView tableEmployee;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colPosition;
    public TableColumn colNumber;
    public TableColumn colSalary;

    public TableColumn colHId;
    public TextField txtSalary;


    public AnchorPane rootNode;
    public TextField txtSearch;
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    public void initialize() {
        setcellValues();
        loadAllEmployee();
        genarateNextEmployeeId();
        showSelectedProductDetails();
    }
    private void showSelectedProductDetails() {
        EmployeeTM selectedUser = (EmployeeTM) tableEmployee.getSelectionModel().getSelectedItem();
        tableEmployee.setOnMouseClicked(event -> showSelectedProductDetails());
        if (selectedUser != null) {

            txtId.setText(selectedUser.getEmployeeId());
            txtName.setText(selectedUser.getName());
            txtId.setText(selectedUser.getEmployeeId());
            txteNumber.setText(selectedUser.getContactNumber());
            txtposition.setText(selectedUser.getPosition());
            txtSalary.setText(selectedUser.getSalary());

        }
    }
    private void genarateNextEmployeeId() {
        try {
            String currentId = employeeBO.generateNewID();


            String nextOrderId = genarateNextEmployeeId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String genarateNextEmployeeId(String currentId) {
        if (currentId != null && currentId.matches("E\\d{3}")) {
            int idNum = Integer.parseInt(currentId.substring(2));
            return "E" + String.format("%03d", idNum + 1);
        } else {
            return "E001";
        }
    }


    private void loadAllEmployee() {

        ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();

        try {
            ArrayList<Employee> cusList = employeeBO.getAll();
            for (Employee cusModle : cusList) {

                EmployeeTM TM = new EmployeeTM(cusModle.getEmployeeId(), cusModle.getName(), cusModle.getContactNumber(), cusModle.getPosition(), cusModle.getSalary());

                obList.add(TM);
                tableEmployee.setItems(obList);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void setcellValues() {

        colId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    public boolean isValied() {
        boolean idValied = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.EID, txtId);
        boolean nameValid = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.NAME, txtName);
        boolean validPhone = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.PHONENUMBER, txteNumber);
        boolean validPosition = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.POSITION, txtposition);
        boolean salaryValid = Regex.setTextColor(SALARY, txtSalary);


        return nameValid && idValied && validPhone && validPosition && salaryValid;

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (isValied()) {

            String id = txtId.getText();
            String name = txtName.getText();
            String number = txteNumber.getText();
            String position = txtposition.getText();
            String salary = txtSalary.getText();

            Employee employee = new Employee(id, name, number, position, salary);

            try {
                boolean isSaved = employeeBO.add(employee);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                    initialize();
                    clearFields();
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
        Regex.setTextColor(lk.ijse.demokushan.Util.TextField.EID, txtId);
    }

    public void eNameKeyReleaseOnAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.demokushan.Util.TextField.NAME, txtName);
    }

    public void eNumberKeyReleaseOnAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.demokushan.Util.TextField.PHONENUMBER, txteNumber);
    }

    public void positionKeyReleaseOnAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.demokushan.Util.TextField.POSITION, txtposition);
    }

    public void salaryKeyReleaseOnAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(SALARY, txtSalary);
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
        genarateNextEmployeeId();
    }
    private void clearFields() {

        txtSearch.setText("");
        txtName.setText("");
        txteNumber.setText("");
        txtposition.setText("");
        txtSalary.setText("");
    }



    public void btnUpadateOnAction(ActionEvent actionEvent) {
        if (isValidIdee()) {
            try {
                String id = txtId.getText();
                String name = txtName.getText();
                String phoneNumber = txteNumber.getText();
                String position = txtposition.getText();
                String salary = txtSalary.getText();

                EmployeeDTO employee = new EmployeeDTO(id, name, phoneNumber, position, salary);

                boolean isUpdated = employeeBO.update(employee);
                System.out.println(isUpdated);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee updated successfully!");
                    initialize(); // Reload data or reset UI
                    clearFields(); // Clear input fields after successful update
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update employee.");
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error updating employee: " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Database driver not found.").show();
                throw new RuntimeException(e); // Rethrow as runtime exception for logging and debugging
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please enter valid employee information.");
            alert.showAndWait();
        }
    }


    public boolean isValidIdee(){
        boolean idValied = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.SALARY, txtSalary);

        return idValied ;
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(isValidIde()){

        String id = txtId.getText();

        Employee employee = employeeBO.search(id);
        if (employee != null) {
            txtId.setText(employee.getEmployeeId());
            txtName.setText(employee.getName());
            txtposition.setText(employee.getPosition());
            txteNumber.setText(employee.getContactNumber());
            txtSalary.setText(employee.getSalary());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "employee not found!").show();
        }
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please enter valid Employee ID correctly.");
            alert.showAndWait();
        }
    }

    public boolean isValidIde(){
        boolean idValied = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.EID, txtId);

        return idValied ;
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (isValidId()) {

            String id = txtId.getText();

            try {
                boolean isDeleted = employeeBO.delete(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();

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

    public boolean isValidId() {
        boolean idValied = Regex.setTextColor(lk.ijse.demokushan.Util.TextField.EID, txtId);

        return idValied;
    }

    public void btnSearchchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String id = txtSearch.getText();

        Employee employee = employeeBO.search(id);
        if (employee != null) {
            txtId.setText(employee.getEmployeeId());
            txtName.setText(employee.getName());
            txtposition.setText(employee.getPosition());
            txteNumber.setText(employee.getContactNumber());
            txtSalary.setText(employee.getSalary());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "employee not found!").show();
        }
    }
}
