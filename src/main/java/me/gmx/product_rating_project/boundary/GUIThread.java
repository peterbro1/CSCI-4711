package me.gmx.product_rating_project.boundary;

import javafx.application.Application;
import me.gmx.product_rating_project.boundary.ui.GUIController;

public class GUIThread extends Thread {


    public GUIThread(String s) {
        super(s);
    }

    public void run() {
        Application.launch(GUIController.class);
    }

}

