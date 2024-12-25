import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import java.util.Optional;
import java.util.Random;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class insertionSortDemo {
    private final Object lock = new Object();
    private boolean stepByStep = false;
    @FXML
    private TextField Array;

    @FXML
    private Button nextStep;

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
    private InsertionSort insertionSort; // Lớp insertionsort để thực hiện sắp xếp
    private boolean isProgramRunning = false; // Biến kiểm tra trạng thái chương trình

    public insertionSortDemo() {
        insertionSort = new InsertionSort(); // Khởi tạo đối tượng insertionsort
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
        if (isProgramRunning) {
            showAlert("Lỗi", "Vui lòng chờ quá trình sắp xếp hoàn tất trước khi tạo mảng mới.");
            return;
        }

        // Đặt lại mảng cũ
        array = null;
        insertionSort = new InsertionSort(); // Khởi tạo lại đối tượng InsertionSort

        // Xóa tất cả nội dung cũ từ TextFlow
        ArrayDemo.getChildren().clear();

        try {
            int size;
            if (RandomBox.isSelected()) {
                size = Integer.parseInt(SoPhanTu.getText());
                array = new int[size]; // Tạo mảng mới
                Random random = new Random();
                for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(100); 
                }
            } else {
                String input = Array.getText();
                String[] inputArray = input.split(" ");
                size = inputArray.length;
                array = new int[size];
                for (int i = 0; i < size; i++) {
                array[i] = Integer.parseInt(inputArray[i]); 
                }
            }

            Array.setText(arrayToString(array)); // Gán mảng mới vào TextField
        } catch (NumberFormatException e) {
            showAlert("Error", "Vui lòng nhập một số hợp lệ cho số phần tử hoặc giá trị mảng.");
        }
    }

    @FXML
    void Steps(ActionEvent event) {
        if (array == null || array.length == 0) {
            showAlert("Lỗi", "Vui lòng tạo mảng ngẫu nhiên hoặc nhập mảng thủ công trước.");
            return;
        }

        if (isProgramRunning) {
            showAlert("Lỗi", "Vui lòng chờ quá trình sắp xếp hoàn tất trước khi thực hiện lại.");
            return;
        }

        isProgramRunning = true; // Đặt trạng thái chương trình đang chạy

        insertionSort.sort(array);

        Thread sortingThread = new Thread(() -> {
            try {
                for (int i = 0; i < insertionSort.getSteps().size(); i++) {
                    final int stepIndex = i;
                    final List<Integer> highlightedIndices = insertionSort.getHighlightedIndices().get(stepIndex);

                    javafx.application.Platform.runLater(() -> {
                        ArrayDemo.getChildren().clear(); // Xóa các phần tử cũ trước khi thêm mới
                        ArrayDemo.getChildren().addAll(convertToTextNodes(insertionSort.getSteps().get(stepIndex), highlightedIndices));
                    });

                    synchronized (lock) {
                        if (stepByStep) {
                            lock.wait(); // Wait for the next step
                        } else {
                            Thread.sleep(750); // Default delay
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                isProgramRunning = false; // Đặt lại trạng thái khi hoàn tất
            }
        });

        
        sortingThread.setDaemon(true);
        sortingThread.start();
    }
    
    @FXML
    void nextStep(ActionEvent event) {
        synchronized (lock) {
            lock.notify(); // Notify the sorting thread to proceed to the next step
        }
    }

    @FXML
    void toggleStepByStep(ActionEvent event) {
        stepByStep = !stepByStep; // Toggle step-by-step mode
        if (stepByStep) {
            nextStep.setDisable(false);
        } else {
            nextStep.setDisable(true);
            synchronized (lock) {
                lock.notify(); // Notify the sorting thread to proceed if step-by-step mode is turned off
            }
        }
    }

    private List<Text> convertToTextNodes(int[] array, List<Integer> highlightedIndices) {
        List<Text> textNodes = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            Text textElement;
            if (highlightedIndices.contains(i)) {
                textElement = new Text(array[i] + " "); // Chỉ hiển thị giá trị mà không có dấu ngoặc
                textElement.setFill(Color.RED); // Đặt màu đỏ cho số cần highlight
            } else {
                textElement = new Text(array[i] + " ");
            }
            textNodes.add(textElement);
        }
        return textNodes;
    }

    @FXML
    void goBackToMainMenu(ActionEvent event) {
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
        ArrayDemo.getChildren().clear(); // Xóa tất cả các phần tử hiện có trước khi thêm mới
    
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(" ");
            Text textElement = new Text(array[i] + " ");
            ArrayDemo.getChildren().add(textElement);
        }
    
        return sb.toString();
    }    
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
