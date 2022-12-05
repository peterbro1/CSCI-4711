package me.gmx.product_rating_project;

import me.gmx.product_rating_project.control.DBConnector;
import me.gmx.product_rating_project.control.GUIController;
import me.gmx.product_rating_project.control.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static Logger logger;
    private DBConnector db;
    private GUIController gui;


    public static void main(String[] args) {

        logger = Logger.getLogger("log");
        Controller app = new Controller();
        app.init();
    }


    public static void logI(String s){
        logger.log(Level.INFO, "[INFO] " + s);
    }
    public static void logE(String s){
        logger.log(Level.SEVERE, "[ERROR] " + s);
    }
}