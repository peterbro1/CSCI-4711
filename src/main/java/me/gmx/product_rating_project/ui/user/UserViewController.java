package me.gmx.product_rating_project.ui.user;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import me.gmx.product_rating_project.ui.GUIController;

import java.io.IOException;

public class UserViewController {

    @FXML
    public TilePane grid;

    @FXML
    public Button logoutButton;


    public void logout() throws IOException {
        GUIController.getInstance().openLoginPanel();
    }


}
