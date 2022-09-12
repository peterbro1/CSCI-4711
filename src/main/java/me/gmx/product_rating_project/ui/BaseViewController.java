package me.gmx.product_rating_project.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.gmx.product_rating_project.PRSApplication;

import java.io.IOException;

public class BaseViewController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label alertLabel;

    @FXML
    protected void attemptLogin() {
        /*String username, password;
        username = usernameField.getText();
        password = passwordField.getText();
        if (ValidationUtil.isAlphaNumeric(username) || ValidationUtil.isAlphaNumeric(password)){
         alertLabel.setText("Usernames and passwords must be alpha-numeric!");
         return;
        }
        String hash = PasswordUtil.hashPassword(password);*/
        try {
            GUIController.getInstance().openUserPanel();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}