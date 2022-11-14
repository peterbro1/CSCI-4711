module me.gmx.product_rating_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.sql;

    opens me.gmx.product_rating_project to javafx.fxml;
    exports me.gmx.product_rating_project;
    exports me.gmx.product_rating_project.boundary.ui;
    opens me.gmx.product_rating_project.boundary.ui to javafx.fxml;
    opens me.gmx.product_rating_project.boundary.ui.user to javafx.fxml;
    exports me.gmx.product_rating_project.entity;
    opens me.gmx.product_rating_project.entity to javafx.fxml;
    exports me.gmx.product_rating_project.control;
    opens me.gmx.product_rating_project.control to javafx.fxml;
}