package me.gmx.product_rating_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.gmx.product_rating_project.db.DatabaseManager;
import me.gmx.product_rating_project.ui.FrontEndGUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static Logger logger;
    private DatabaseManager db;

    private FrontEndGUI gui;


    public static void main(String[] args) {
        logger = Logger.getLogger("log");
        PRSApplication app = new PRSApplication();
        app.
    }

    public void init(){

    }

    public static void logI(String s){
        logger.log(Level.INFO, "[INFO] " + s);
    }
    public static void logE(String s){
        logger.log(Level.SEVERE, "[ERROR] " + s);
    }
}