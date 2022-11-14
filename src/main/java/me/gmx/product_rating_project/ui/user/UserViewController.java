package me.gmx.product_rating_project.ui.user;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import me.gmx.product_rating_project.Main;
import me.gmx.product_rating_project.PRSApplication;
import me.gmx.product_rating_project.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class UserViewController {



    private boolean init = false;
    @FXML
    public Button logoutButton;

    @FXML
    public Label userLabel;

    @FXML
    public VBox productList;

    @FXML
    public HBox dummyProduct;


    public void logout(){
        PRSApplication.getInstance().logout();
    }
    @FXML
    public void initialize(){
        userLabel.setText(PRSApplication.getInstance().getcurrentUser().getName());
        productList.getChildren().addAll(generateProducts());

    }

    public List<GridPane> generateProducts(){
        List<Product> products = PRSApplication.getInstance().db.fetchAllProducts();
        List<GridPane> hb = new ArrayList<>();
        for (Product p : products)
            hb.add(createProduct(p));
        return hb;
    }

    public GridPane createProduct(Product product){
        if (!init)
            productList.getChildren().remove(dummyProduct);

        GridPane p = new GridPane();
        HBox b = new HBox();
        b.setAlignment(Pos.TOP_CENTER);
        b.setSpacing(300);
        b.setMaxHeight(200);


        Label title = new Label(product.getName());
        title.setAlignment(Pos.CENTER);
        title.setContentDisplay(ContentDisplay.CENTER);
        Label ratingLabel = new Label(String.valueOf(product.getRating()));
        ratingLabel.setAlignment(Pos.CENTER);
        ratingLabel.setContentDisplay(ContentDisplay.CENTER);

        Button button = new Button("Rate");
        button.setAlignment(Pos.CENTER);
        button.setContentDisplay(ContentDisplay.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                try {
                    PRSApplication.getInstance().gui.openRatingPanel(product);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        if (PRSApplication.getInstance().db.hasUserRated(PRSApplication.getInstance().getcurrentUser(), product)) {
            button.setDisable(true);
            button.setText("Rated");
        }
        ColumnConstraints c = new ColumnConstraints();
        c.setPercentWidth(33.333);
        GridPane.setHalignment(ratingLabel, HPos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(button, HPos.CENTER);

        p.addColumn(0,ratingLabel);
        p.addColumn(1,title);
        p.addColumn(2,button);
        p.getColumnConstraints().addAll(c,c,c);

        /*b.getChildren().addAll(ratingLabel,title,button);
        return b;*/
        //p.getChildren().addAll(title,ratingLabel, button);
        return p;
    }


}
