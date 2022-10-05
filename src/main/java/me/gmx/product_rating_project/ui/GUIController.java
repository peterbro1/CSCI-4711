package me.gmx.product_rating_project.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.gmx.product_rating_project.PRSApplication;
import me.gmx.product_rating_project.Product;
import me.gmx.product_rating_project.ui.user.RatingViewController;

import java.io.IOException;

public class GUIController extends Application {

    private Parent content;
    public static GUIController ins;
    private Stage stage;

    public GUIController(){
        ins = this;
        PRSApplication.getInstance().guiCallback(this);
    }

    @Override
    public void start(Stage stage) throws IOException {
        initMain();
        Scene scene = new Scene(content, 1280, 720);
        stage.setTitle("Product Rating System");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }

    public void initMain() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/login-view.fxml"));
        content = fxmlLoader.load();
    }


    public void openUserPanel()throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/user-view.fxml"));
        content = loader.load();
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.show();
    }

    public void openLoginPanel()throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/login-view.fxml"));
        content = fxmlLoader.load();
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.show();
    }

    public void openRatingPanel(Product p)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/rating-view.fxml"));
        RatingViewController.product = p;
        content = fxmlLoader.load();
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.show();
    }


    public synchronized static GUIController getInstance() {
        if (ins == null) {
            PRSApplication.getInstance().startGUIThread();
            while (ins == null)
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        return ins;
    }


}
