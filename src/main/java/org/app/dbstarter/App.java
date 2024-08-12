package org.app.dbstarter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.app.dbstarter.config.DBConnection;
import org.app.dbstarter.controller.IndexController;

import java.io.IOException;

public class App extends Application {
    private final IndexController indexController = new IndexController();
    private final DBConnection dbConnection = new DBConnection();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/index-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image(getClass().getResourceAsStream("img/database.png"));
        stage.getIcons().add(icon);
        stage.setTitle("DB Starter");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(e -> indexController.onClose());
        dbConnection.createTable();
    }

    public static void main(String[] args) {
        launch();
    }
}