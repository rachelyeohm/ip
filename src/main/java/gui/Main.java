package gui;

import java.io.IOException;

import components.Storage;
import components.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import task.TaskList;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Nyabot nyabot = new Nyabot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNyabot(nyabot);  // inject the Nyabot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
