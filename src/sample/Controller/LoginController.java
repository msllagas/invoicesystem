package sample.Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Model.Admin;

public class LoginController {

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonExit;

    @FXML
    private Label labelMessage;

    @FXML
    private TextField fieldAdmin;

    Admin admin;


    @FXML
    void initialize() {

        buttonExit.setOnAction(actionEvent -> {
            Stage stage = (Stage) buttonExit.getScene().getWindow();
            stage.close();
        });

        buttonLogin.setOnAction(actionEvent -> {
            if (fieldAdmin.getText().isEmpty() && fieldPassword.getText().isEmpty()){
                labelMessage.setText("Please fill up the fields.");
            }
            else
                validate();
        });
    }
    private void validate(){
        admin = new Admin();
        if (fieldAdmin.getText().equals(admin.getUsername()) && fieldPassword.getText().equals(admin.getPassword())){
            buttonLogin.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/View/table.fxml"));

            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        }
        else
            labelMessage.setText("Incorrect username/password!");
    }

    public void menuContainer() {
        buttonLogin.setStyle("-fx-background-color:  #017eec");
    }

    @FXML
    void menuOut(MouseEvent event) {
        buttonLogin.setStyle("-fx-background-color: #0066c8");
    }

}