package org.app.dbstarter.service;

import javafx.scene.control.TextArea;
import org.app.dbstarter.config.DBConnection;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class PostgreSQLService {
    private final DBConnection dbConnection = new DBConnection();

    public void start(TextArea textAreaResult) {
        SwingWorker<Void, String> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                ProcessBuilder processBuilder = new ProcessBuilder(
                        "pg_ctl",
                        "-D", dbConnection.getPostgreSQLPath(),
                        "start"
                );

                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    publish(line);
                    textAreaResult.appendText(line + "\n");
                }
                int exitCode = process.waitFor();
                publish("Process terminated with exit code: " + exitCode);
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String line : chunks) {
                    System.out.println(line);
                }
            }

            @Override
            protected void done() {
            }
        };
        worker.execute();
    }

    public void stop(TextArea textAreaResult) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "pg_ctl",
                    "-D", dbConnection.getPostgreSQLPath(),
                    "stop"
            );
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (textAreaResult != null) {
                    textAreaResult.appendText(line + "\n");
                }
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            System.out.println("Process terminated with exit code: " + exitCode);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
            Thread.currentThread().interrupt();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
