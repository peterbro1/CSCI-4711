package me.gmx.product_rating_project.thread;

import javafx.application.Application;
import me.gmx.product_rating_project.ui.FrontEndGUI;

public class GUIThread extends Thread {

    public GUIThread(String s) {
        super(s);
    }

    public void run() {
        Application.launch(FrontEndGUI.class);
    }

}

