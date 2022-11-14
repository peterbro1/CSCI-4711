package me.gmx.product_rating_project.control;

import me.gmx.product_rating_project.entity.Product;
import me.gmx.product_rating_project.entity.User;
import me.gmx.product_rating_project.boundary.GUIThread;
import me.gmx.product_rating_project.boundary.ui.GUIController;

import java.util.ArrayList;
import java.util.List;

public class PRSApplication {

    private GUIThread thread;

    public GUIController gui;
    public List<Product> productList;

    private User currentUser;
    public DatabaseManager db;
    private static PRSApplication ins;
    public PRSApplication(){
        ins = this;
        productList = new ArrayList<>();
    }

    public void init(){
        startGUIThread();
        //Dependency injection :D
        db = new DatabaseManager(ins);
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

    public void logout(){

        currentUser = null;
        try {
            GUIController.getInstance().openLoginPanel();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void startGUIThread(){
        if (thread == null)
            thread = new GUIThread("GUI");
        thread.start();
    }

    //Dependency injection D:
    public static PRSApplication getInstance(){
        return ins;
    }

}
