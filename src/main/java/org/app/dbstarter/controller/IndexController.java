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
            postgreSQLService.startPostgres(textAreaResult);
        } else {
            postgreSQLService.stopPostgres(textAreaResult);
        }
    }

    public void onSavePath() {
        dbConnection.updatePostgreSQLPath(textFieldPath.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textFieldPath.setText(dbConnection.getPostgreSQLPath());
    }

    public void onClose() {
        //postgreSQLService.stopPostgres(textAreaResult);
        System.out.println("Exiting...");
        System.exit(0);
    }
}