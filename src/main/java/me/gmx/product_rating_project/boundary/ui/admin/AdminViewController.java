package me.gmx.product_rating_project.boundary.ui.admin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import me.gmx.product_rating_project.control.PRSApplication;
import me.gmx.product_rating_project.entity.Product;
import me.gmx.product_rating_project.entity.Review;

import java.util.ArrayList;
import java.util.List;

public class AdminViewController {



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
        for (Review r : PRSApplication.getInstance().db.getUnconfirmedReviews()){
            productList.getChildren().add(createReviewEntry(r));
        }
        productList.getChildren().addAll();
    }

/*    public List<GridPane> generateProducts(){
        List<Product> products = PRSApplication.getInstance().db.fetchAllProducts();
        List<GridPane> hb = new ArrayList<>();
        for (Product p : products)
            hb.add(createProduct(p));
        return hb;
    }*/

    public GridPane createReviewEntry(Review r){
        if (!init)
            productList.getChildren().remove(dummyProduct);

        GridPane p = new GridPane();



        Label title = new Label(r.getProduct().getName());
        title.setAlignment(Pos.CENTER);
        title.setContentDisplay(ContentDisplay.CENTER);

        Button button = new Button("Review");
        button.setAlignment(Pos.CENTER);
        button.setContentDisplay(ContentDisplay.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    PRSApplication.getInstance().gui.openReviewPanel(r);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        ColumnConstraints c = new ColumnConstraints();
        c.setPercentWidth(50);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(button, HPos.CENTER);

        p.addColumn(0,title);
        p.addColumn(1,button);
        p.getColumnConstraints().addAll(c,c);

        /*b.getChildren().addAll(ratingLabel,title,button);
        return b;*/
        //p.getChildren().addAll(title,ratingLabel, button);
        return p;
    }



}
