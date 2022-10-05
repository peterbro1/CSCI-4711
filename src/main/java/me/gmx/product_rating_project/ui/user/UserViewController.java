package me.gmx.product_rating_project.ui.user;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import me.gmx.product_rating_project.Main;
import me.gmx.product_rating_project.PRSApplication;
import me.gmx.product_rating_project.Product;
import me.gmx.product_rating_project.auth.User;
import me.gmx.product_rating_project.ui.GUIController;
import org.controlsfx.control.action.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
        Collection<ImageView> c = new HashSet<>();
        userLabel.setText(PRSApplication.getInstance().getcurrentUser().getName());
        List<Product> p = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            File f = new File(Main.class.getResource("/img/blender.png").getPath());
            try {
                p.add(new Product(i, "Product " + i, new FileInputStream(f)));
            }catch(Exception e){e.printStackTrace();}

        }
        List<HBox> products = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            products.add(createProduct(p.get(i)));
        }

        productList.getChildren().addAll(products);

/*        for(int i = 0; i < 20; i++){
            ImageView v = new ImageView();
            v.setImage(new Image("https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/322868_1100-800x825.jpg"));
            v.fitWidthProperty().bind(grid.prefTileWidthProperty());
            v.fitHeightProperty().bind(grid.prefTileHeightProperty());
            c.add(v);
        }*/
        /*for (Product p : PRSApplication.getInstance().productList){
            ImageView v = new ImageView(p.image);
            v.fitWidthProperty().bind(grid.prefTileWidthProperty());
            v.fitHeightProperty().bind(grid.prefTileHeightProperty());
            c.add(v);
        }
        grid.getChildren().addAll(c);*/
    }

    public HBox createProduct(Product product){
        if (!init)
            productList.getChildren().remove(dummyProduct);

        HBox b = new HBox();
        b.setAlignment(Pos.TOP_CENTER);
        b.setSpacing(300);

        Label title = new Label(product.getName());
        title.setAlignment(Pos.CENTER_LEFT);
        title.setContentDisplay(ContentDisplay.LEFT);

        Label ratingLabel = new Label(String.valueOf(product.getRating()));
        ratingLabel.setAlignment(Pos.CENTER_LEFT);
        ratingLabel.setContentDisplay(ContentDisplay.LEFT);

        Button button = new Button("Rate");
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
        b.getChildren().addAll(ratingLabel,title,button);
        return b;
    }


}
