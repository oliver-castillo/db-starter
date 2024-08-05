module org.app.dbstarter {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.app.dbstarter to javafx.fxml;
    exports org.app.dbstarter;
}