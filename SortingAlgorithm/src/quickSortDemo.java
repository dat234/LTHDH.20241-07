import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.paint.Color;

public class quickSortDemo {

    @FXML
    private TextField Array;

    @FXML
    private TextFlow ArrayDemo;

    @FXML
    private Button OKbtn;

    @FXML
    private Button Random;

    @FXML
    private TextField SoPhanTu;

    @FXML
    private Button btnBacktoM;

    @FXML
    private CheckBox RandomBox; // CheckBox để chọn tạo mảng ngẫu nhiên

    private int[] array; // Mảng chứa các phần tử
    private QuickSort quickSort; // Lớp quickSort để thực hiện sắp xếp
    private boolean isProgramRunning = false; // Biến kiểm tra trạng thái chương trình

    public quickSortDemo() {
        quickSort = new QuickSort(); // Khởi tạo đối tượng quickSort
    }

    // Xử lý khi người dùng tick vào CheckBox Random
    @FXML
    void TickRandom(ActionEvent event) {
        if (RandomBox.isSelected()) {
            SoPhanTu.setDisable(false); 
            Array.setDisable(true); 
        } else {
            SoPhanTu.setDisable(true); 
            Array.setDisable(false); 
        }
    }

    @FXML
    void RandomArray(ActionEvent event) {
        try {
            if (RandomBox.isSelected()) {
                int size = Integer.parseInt(SoPhanTu.getText());
                array = new int[size];
                Random random = new Random();

                for (int i = 0; i < size; i++) {
                    array[i] = random.nextInt(100); 
                }

                Array.setText(arrayToString(array));
            } else {
                String input = Array.getText();
                String[] inputArray = input.split(" ");
                array = new int[inputArray.length];

                for (int i = 0; i < inputArray.length; i++) {
                    array[i] = Integer.parseInt(inputArray[i]);
                }
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number for the array size or the array values.");
        }
    }

    @FXML
    void Steps(ActionEvent event) {
        if (array == null || array.length == 0) {
            showAlert("Error", "Please generate a random array or enter an array manually first.");
            return;
        }
    
        isProgramRunning = true;
    
        quickSort.sort(array);
    
        Thread sortingThread = new Thread(() -> {
            try {
                for (int i = 0; i < quickSort.getSteps().size(); i++) {
                    final int stepIndex = i;
                    final int[] stepArray = quickSort.getSteps().get(stepIndex);
                    final List<Integer> highlighted = quickSort.getHighlightedIndices().get(stepIndex);
                    final int pivotIndex = quickSort.getPivotIndices().get(stepIndex);
    
                    javafx.application.Platform.runLater(() -> {
                        ArrayDemo.getChildren().clear();
                        ArrayDemo.getChildren().addAll(convertToTextNodes(stepArray, highlighted, pivotIndex));
                    });
    
                    Thread.sleep(750);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                isProgramRunning = false;
            }
        });
    
        sortingThread.setDaemon(true);
        sortingThread.start();
    }    

    private List<Text> convertToTextNodes(int[] array, List<Integer> highlightedIndices, int pivotIndex) {
        List<Text> textNodes = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            Text textElement = new Text(array[i] + " ");
            if (highlightedIndices.contains(i)) {
                textElement.setFill(Color.RED); // Highlight phần tử đang xét
            } else if (i == pivotIndex) {
                textElement.setFill(Color.BLUE); // Highlight pivot
            } else {
                textElement.setFill(Color.BLACK); // Màu mặc định
            }
            textNodes.add(textElement);
        }
        return textNodes;
    }
    

    @FXML
    private void goBackToMainMenu(ActionEvent event) {
        if (isProgramRunning) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Action");
            alert.setHeaderText("Program is still running!");
            alert.setContentText("Do you really want to go back to the main menu? Any unsaved progress will be lost.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    MainMenuApp.changeScene("mainMenu.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                MainMenuApp.changeScene("mainMenu.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int num : array) {
            sb.append(num).append(" ");
        }
        return sb.toString().trim();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
