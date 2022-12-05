package me.gmx.product_rating_project.boundary.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.gmx.product_rating_project.control.GUIController;
import me.gmx.product_rating_project.control.Controller;
import me.gmx.product_rating_project.control.LoginControl;
import me.gmx.product_rating_project.util.PasswordUtil;
import me.gmx.product_rating_project.entity.User;
import me.gmx.product_rating_project.util.ValidationUtil;

public class LoginForm {

    private final String loginFailMsg = "Incorrect username or password!";
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label alertLabel;

    @FXML
    protected void submit() {
        String username, password;
        username = usernameField.getText();
        password = passwordField.getText();
        usernameField.clear();
        passwordField.clear();
        if (ValidationUtil.isAlphaNumeric(username) || ValidationUtil.isAlphaNumeric(password)){
         alertLabel.setText("Usernames and passwords must be alpha-numeric!");
         return;
        }else if (username.length() < 3){
            alertLabel.setText("Username must be at least 3 characters long!");
            return;
        }
        String hash = PasswordUtil.hashPassword(password,username);
        try {
            LoginControl.submit(username,hash);
            if (LoginControl.verifyAccount()){
                if (Controller.currentUser.type == User.UserType.NORMAL)
                    GUIController.getInstance().openUserPanel();
                else if (Controller.currentUser.type == User.UserType.ADMIN)
                    GUIController.getInstance().openAdminPanel();
            }else{
                alertLabel.setText(loginFailMsg);
            }
        }catch (Exception e){
            e.printStackTrace();
            alertLabel.setText(loginFailMsg);
        }
    }
}