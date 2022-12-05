package me.gmx.product_rating_project.boundary.ui.user;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import me.gmx.product_rating_project.control.Controller;
import me.gmx.product_rating_project.control.GUIController;
import me.gmx.product_rating_project.control.LogoutControl;
import me.gmx.product_rating_project.control.RateControl;
import me.gmx.product_rating_project.entity.Product;

import java.util.ArrayList;
import java.util.List;
import me.gmx.product_rating_project.entity.User;

public class MainMenu {



    @FXML
    public Button logoutButton;

    @FXML
    public Label userLabel;

    @FXML
    public VBox productList;

    @FXML
    public HBox dummyProduct;


    public static void open(String usn)  {
        try {
            GUIController.getInstance().openUserPanel();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void logout(){
        LogoutControl.logout(Controller.currentUser.getName());
    }
    @FXML
    public void initialize(){
        userLabel.setText(Controller.getInstance().getcurrentUser().getName());
        productList.getChildren().addAll(generateProducts());
    }

    public List<GridPane> generateProducts(){
        List<Product> products = Controller.getInstance().db.getItems(Controller.currentUser);
        List<GridPane> hb = new ArrayList<>();
        for (Product p : products) {
            hb.add(createProduct(p));
            System.out.printf("%s ", p.getName());
        }

        return hb;
    }

    public GridPane createProduct(Product product){
        productList.getChildren().remove(dummyProduct);

        GridPane p = new GridPane();
        HBox b = new HBox();
        b.setAlignment(Pos.TOP_CENTER);
        b.setSpacing(300);
        b.setMaxHeight(200);


        Label title = new Label(product.getName());
        title.setAlignment(Pos.CENTER);
        title.setContentDisplay(ContentDisplay.CENTER);
        Label ratingLabel = new Label(String.valueOf(Controller.getInstance().db.getAverageRatingByProduct(product)));
        ratingLabel.setAlignment(Pos.CENTER);
        ratingLabel.setContentDisplay(ContentDisplay.CENTER);

        Button button = new Button("Rate");
        button.setAlignment(Pos.CENTER);
        button.setContentDisplay(ContentDisplay.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    RateControl.rate(Controller.currentUser, product);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        if (Controller.getInstance().db.hasUserRated(Controller.getInstance().getcurrentUser(), product)) {
            button.setDisable(true);
            button.setText("Rated");
        }
        ColumnConstraints c = new ColumnConstraints();
        c.setPercentWidth(33.333);
        GridPane.setHalignment(ratingLabel, HPos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(button, HPos.CENTER);

        p.addColumn(1,ratingLabel);
        p.addColumn(0,title);
        p.addColumn(2,button);
        p.getColumnConstraints().addAll(c,c,c);

        /*b.getChildren().addAll(ratingLabel,title,button);
        return b;*/
        //p.getChildren().addAll(title,ratingLabel, button);
        return p;
    }




}
