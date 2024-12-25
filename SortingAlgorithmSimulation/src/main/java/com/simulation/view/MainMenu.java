package com.simulation.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenu extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("MainMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sorting Algorithm Simulation");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            event.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thoát khỏi chương trình");
            alert.setHeaderText("Bạn có chắc là muốn thoát chứ?");
            alert.setContentText("Nhấn OK để thoát, Cancel để quay lại");
            if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK)
                stage.close();
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}