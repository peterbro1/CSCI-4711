package me.gmx.product_rating_project.ui.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import me.gmx.product_rating_project.Product;

import java.util.Arrays;

public class RatingViewController {

    private static ObservableList<String> ratingList;

    public static Product product;
    @FXML
    public Label productNameLabel;

    @FXML
    public ChoiceBox<String> ratingMenu;

    @FXML
    public Button submitButton;

    @FXML
    public void initialize(){
        if (product == null)
            return;
        //cache ftw
        if (ratingList == null) {
            ratingList = FXCollections.observableList(Arrays.asList("1", "2", "3", "4", "5"));
        }
        ratingMenu.getItems().addAll(ratingList);
        productNameLabel.setText(product.getName());
    }



}
