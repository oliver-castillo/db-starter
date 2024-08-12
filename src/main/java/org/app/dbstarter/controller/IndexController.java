package org.app.dbstarter.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.app.dbstarter.config.DBConnection;
import org.app.dbstarter.service.PostgreSQLService;
import org.controlsfx.control.ToggleSwitch;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    @FXML
    private ToggleSwitch switchPostgreSQL;

    @FXML
    private TextArea textAreaResult;

    @FXML
    private TextField textFieldPath;

    private PostgreSQLService postgreSQLService = new PostgreSQLService();
    private DBConnection dbConnection = new DBConnection();

    public void onSwitchPostgreSQL() {
        if (switchPostgreSQL.isSelected()) {
            System.out.println("Starting...");
            postgreSQLService.start(textAreaResult);
        } else {
            System.out.println("Stopping...");
            postgreSQLService.stop(textAreaResult);
        }
    }

    public void onSavePath() {
        dbConnection.updatePostgreSQLPath(textFieldPath.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textAreaResult.setEditable(false);
        textFieldPath.setText(dbConnection.getPostgreSQLPath());
    }

    public void onClose() {
        postgreSQLService.stop(null);
        System.exit(0);
    }
}