package me.gmx.product_rating_project.boundary.ui.admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import me.gmx.product_rating_project.control.PRSApplication;
import me.gmx.product_rating_project.entity.Review;

import java.util.Arrays;

public class AdminReviewRatingController {

    public static Review review;


    @FXML
    public Label productNameLabel;

    @FXML
    public Label ratingLabel;

    @FXML
    public Label reviewBox;

    @FXML
    public Button rejectButton, approveButton;

    @FXML
    public void initialize(){
        if (review == null)
            return;

        ratingLabel.setText(String.valueOf(review.getRating()));
        productNameLabel.setText(review.getProduct().getName());
        reviewBox.setText(review.getComment());
    }


    public void confirmReview(){//product, user, rating, comment
        PRSApplication.getInstance().db.confirmUserReview(review);
        try {
            PRSApplication.getInstance().gui.openAdminPanel();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void rejectReview(){
        PRSApplication.getInstance().db.deleteUserReview(review);
        try {
            PRSApplication.getInstance().gui.openAdminPanel();
        }catch (Exception e){
            e.printStackTrace();
        }    }
}
