import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class mainMenuController {

    @FXML
    private Button bubbleSortButton;

    @FXML
    private Button helpmenu;

    @FXML
    private Button insertionSortButton;

    @FXML
    private Button quickSortButton;

    @FXML
    private Button quitExit;

    // Handle BubbleSort selection
    @FXML
    void btnBubbleSort(ActionEvent event) {
        try {
            // Switch to the BubbleSort demonstration scene
            MainMenuApp.changeScene("bubbleSortDemo.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handle Help selection
    @FXML
    void btnHelp(ActionEvent event) {
        // Show help dialog with basic usage and instructions
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Sorting Algorithm Demonstrator");
        alert.setContentText("This application demonstrates three sorting algorithms: Bubble Sort, Quick Sort, and Insertion Sort.\n\n" +
                "1. Select a sorting algorithm.\n" +
                "2. Choose to create a random array or input your own.\n" +
                "3. Click 'Start' to see the sorting process step by step.\n" +
                "4. Click 'Back' to return to the main menu.");
        alert.showAndWait();
    }

    // Handle InsertionSort selection
    @FXML
    void btnInsertionSort(ActionEvent event) {
        try {
            // Switch to the InsertionSort demonstration scene
            MainMenuApp.changeScene("insertionSortDemo.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handle QuickSort selection
    @FXML
    void btnQuickSort(ActionEvent event) {
        try {
            // Switch to the QuickSort demonstration scene
            MainMenuApp.changeScene("quickSortDemo.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handle Quit selection
    @FXML
    void btnQuit(ActionEvent event) {
        // Show confirmation dialog before quitting
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Are you sure you want to quit?");
        alert.showAndWait().ifPresent(response -> {
            if (response.getText().equals("OK")) {
                System.exit(0); // Exit the application
            }
        });
    }
}
