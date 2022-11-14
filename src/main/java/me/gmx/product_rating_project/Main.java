package me.gmx.product_rating_project;

import me.gmx.product_rating_project.control.DatabaseManager;
import me.gmx.product_rating_project.boundary.ui.GUIController;
import me.gmx.product_rating_project.control.PRSApplication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static void test(){
        DatabaseManager db = new DatabaseManager(PRSApplication.getInstance());
        db.init();
        PreparedStatement st = db.getPreparedStatement("SELECT * FROM USERS WHERE username LIKE \"alice\"");
        try {
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("password"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        System.exit(0);
    }

    public static void logI(String s){
        logger.log(Level.INFO, "[INFO] " + s);
    }
    public static void logE(String s){
        logger.log(Level.SEVERE, "[ERROR] " + s);
    }
}