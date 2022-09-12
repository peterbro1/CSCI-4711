package me.gmx.product_rating_project;

import me.gmx.product_rating_project.db.DatabaseManager;
import me.gmx.product_rating_project.thread.GUIThread;
import me.gmx.product_rating_project.ui.GUIController;

public class PRSApplication {

    private GUIThread thread;


    public DatabaseManager db;
    private static PRSApplication ins;
    public PRSApplication(){
        ins = this;
    }

    public void init(){
        //Dependency injection :D
        startGUIThread();
        db = new DatabaseManager(ins);
    }

    public void startGUIThread(){
        if (thread == null)
            thread = new GUIThread("GUI");
        thread.run();
    }

    //Dependency injection D:
    public static PRSApplication getInstance(){
        return ins;
    }

}
