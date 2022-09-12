package me.gmx.product_rating_project;

import me.gmx.product_rating_project.db.DatabaseManager;
import me.gmx.product_rating_project.ui.GUIController;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static Logger logger;
    private DatabaseManager db;
    private GUIController gui;


    public static void main(String[] args) {
        logger = Logger.getLogger("log");
        PRSApplication app = new PRSApplication();
        app.init();
    }

    public static void logI(String s){
        logger.log(Level.INFO, "[INFO] " + s);
    }
    public static void logE(String s){
        logger.log(Level.SEVERE, "[ERROR] " + s);
    }
}