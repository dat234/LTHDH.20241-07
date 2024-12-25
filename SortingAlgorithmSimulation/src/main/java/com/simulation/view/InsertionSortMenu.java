package com.simulation.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class InsertionSortMenu {

    public InsertionSortMenu() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("InsertionSortMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Insertion Algorithm Simulation");
        stage.setScene(scene);
        stage.show();
    }
}