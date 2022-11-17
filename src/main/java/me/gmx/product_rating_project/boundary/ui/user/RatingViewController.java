package me.gmx.product_rating_project.boundary.ui.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import me.gmx.product_rating_project.control.PRSApplication;
import me.gmx.product_rating_project.entity.Product;
import me.gmx.product_rating_project.entity.Review;

import java.util.Arrays;

public class RatingViewController {

    private static ObservableList<String> ratingList;

    public static Product product;
    @FXML
    public Label productNameLabel;

    @FXML
    public ChoiceBox<String> ratingMenu;

    @FXML
    public TextArea reviewBox;

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


    public void submitReview(){//product, user, rating, comment
        Review review = new Review(PRSApplication.getInstance().getcurrentUser()
                , product, Integer.valueOf(ratingMenu.getValue()), reviewBox.getText());
        PRSApplication.getInstance().db.addUserReview(review, PRSApplication.getInstance().getcurrentUser(),
                product);
        try {
            PRSApplication.getInstance().gui.openUserPanel();
        }catch(Exception e){
            e.printStackTrace();
        }


    }



}
