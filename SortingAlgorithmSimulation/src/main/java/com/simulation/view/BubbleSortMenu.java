package com.simulation.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class BubbleSortMenu {

    public BubbleSortMenu() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("BubbleSortMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bubble Sort Algorithm Simulation");
        stage.setScene(scene);
        stage.show();
    }
}