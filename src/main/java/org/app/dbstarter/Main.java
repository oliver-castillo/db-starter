package org.app.dbstarter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.app.dbstarter.controller.IndexController;

import java.io.IOException;

public class Main extends Application {
    private final IndexController indexController = new IndexController();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/index-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DB Starter");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(e -> indexController.onClose());
    }

    public static void main(String[] args) {
        launch();
    }
}