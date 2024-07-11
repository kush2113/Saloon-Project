package lk.ijse.demokushan.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.demokushan.Util.Regex;

import java.io.IOException;

public class LoginFormController {
    public TextField txtName;
    public TextField txtPassword;
    public AnchorPane rootNode;
    public ImageView icon1;
    public ImageView icon2;
    @FXML
    boolean btnLoginOnAction(ActionEvent actionEvent) throws IOException {

            String name = "a";
            String pass = "a";

            String userId = txtName.getText();
            String pw = txtPassword.getText();


            if (userId.equals(name) && pass.equals(pw)) {
                navigateToTheDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "password is wrong ").show();
            }
            return false;

        }

        private void navigateToTheDashboard () throws IOException {

            AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

            Scene scene = new Scene(rootNode);

            Stage stage = (Stage) this.rootNode.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

            stage.setTitle("Dashboard");

        }

        public void btnNameOnAction (KeyEvent actionEvent) throws IOException {


            String name = "a";
            String pass = "a";

            String userId = txtName.getText();
            String pw = txtPassword.getText();


            if (userId.equals(name) && pass.equals(pw)) {
                navigateToTheDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "password is wrong ").show();
            }

        }

        public void btnPasswordOnAction (ActionEvent actionEvent) throws IOException {

            String name = "a";
            String pass = "a";

            String userId = txtName.getText();
            String pw = txtPassword.getText();

            if (userId.equals(name) && pass.equals(pw)) {
                navigateToTheDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "password is wrong ").show();
            }
        }
    }


