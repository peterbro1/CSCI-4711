package me.gmx.product_rating_project.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.gmx.product_rating_project.Main;
import me.gmx.product_rating_project.PRSApplication;

import java.io.IOException;

public class FrontEndGUI extends Application {

    private PRSApplication ins;

    public FrontEndGUI(PRSApplication ins){
        this.ins = ins;
    }

    public FrontEndGUI(){}

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Product Rating System");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
