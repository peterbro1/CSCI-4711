package me.gmx.product_rating_project.control;

import me.gmx.product_rating_project.entity.Product;
import me.gmx.product_rating_project.entity.User;
import me.gmx.product_rating_project.boundary.GUIThread;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private GUIThread thread;

    public GUIController gui;
    public List<Product> productList;

    public static User currentUser;
    public DBConnector db;
    private static Controller ins;
    public Controller(){
        ins = this;
        productList = new ArrayList<>();
    }

    public void init(){
        startGUIThread();
        //Dependency injection :D
        db = new DBConnector(ins);
        db.init();
    }

    public User getcurrentUser(){
        return currentUser;
    }

    public boolean tryLogin(User u){
        if (u == null)
            return false;

        currentUser = u;
        return true;
    }

    public void guiCallback(GUIController c){
        if (gui == null)
            this.gui = c;
    }





    public void startGUIThread(){
        if (thread == null)
            thread = new GUIThread("GUI");
        thread.start();
    }

    //Dependency injection D:
    public static Controller getInstance(){
        return ins;
    }

}
