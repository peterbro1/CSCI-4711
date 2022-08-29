module me.gmx.product_rating_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.sql;

    opens me.gmx.product_rating_project to javafx.fxml;
    exports me.gmx.product_rating_project;
    exports me.gmx.product_rating_project.ui;
    opens me.gmx.product_rating_project.ui to javafx.fxml;
}