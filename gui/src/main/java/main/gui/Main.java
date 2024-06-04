package main.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.gui.collections.CollectionsWindow;
import main.gui.login.LoginWindow;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        CollectionsWindow collectionsWindow = new CollectionsWindow();
        collectionsWindow.show();
//        LoginWindow loginWindow = new LoginWindow(stage);
//        loginWindow.show();
    }

    public static void main(String[] args) {
        launch();
    }
}