package me.gmx.product_rating_project;

import me.gmx.product_rating_project.db.DatabaseManager;
import me.gmx.product_rating_project.ui.FrontEndGUI;

public class PRSApplication {

    public FrontEndGUI gui;
    public DatabaseManager db;
    private static PRSApplication ins;
    public PRSApplication(){
        ins = this;
    }

    public void init(){
        //Dependency injection :D
        gui = new FrontEndGUI(ins);
        db = new DatabaseManager(ins);
    }

    //Dependency injection D:
    public static PRSApplication getInstance(){
        return ins;
    }

}
