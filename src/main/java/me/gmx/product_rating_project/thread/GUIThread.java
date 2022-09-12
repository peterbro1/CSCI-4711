package me.gmx.product_rating_project.thread;

import javafx.application.Application;
import me.gmx.product_rating_project.ui.FrontEndGUI;

public class GUIThread extends Thread {


    private FrontEndGUI apo;
    public GUIThread(String s, FrontEndGUI apo) {
        super(s);
        this.apo = apo;
    }

    public void run() {
        Application.launch(FrontEndGUI.class);
    }

}

