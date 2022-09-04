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

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Product Rating System");
        stage.setScene(scene);
        stage.show();
    }

}
