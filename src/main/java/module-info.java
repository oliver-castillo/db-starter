module org.app.dbstarter {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires java.sql;
    requires org.slf4j;


    opens org.app.dbstarter to javafx.fxml;
    exports org.app.dbstarter;
    exports org.app.dbstarter.controller;
    opens org.app.dbstarter.controller to javafx.fxml;
}