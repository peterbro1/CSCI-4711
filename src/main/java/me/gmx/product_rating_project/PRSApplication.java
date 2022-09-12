package me.gmx.product_rating_project;

import me.gmx.product_rating_project.db.DatabaseManager;
import me.gmx.product_rating_project.thread.GUIThread;
import me.gmx.product_rating_project.ui.FrontEndGUI;

public class PRSApplication {

    private GUIThread thread;
    public DatabaseManager db;
    private static PRSApplication ins;
    public PRSApplication(){
        ins = this;
    }

    public void init(){
        //Dependency injection :D
        thread = new GUIThread("GUI", new FrontEndGUI(ins));
        thread.run();
        db = new DatabaseManager(ins);

    }

    //Dependency injection D:
    public static PRSApplication getInstance(){
        return ins;
    }

}
