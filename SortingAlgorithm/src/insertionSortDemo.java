import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import java.util.Random;

public class insertionSortDemo {

    @FXML
    private TextField Array;

    @FXML
    private TextField ArrayDemo;

    @FXML
    private Button OKbtn;

    @FXML
    private Button Random;

    @FXML
    private TextField SoPhanTu;

    @FXML
    private CheckBox RandomBox; // CheckBox để chọn tạo mảng ngẫu nhiên

    private int[] array; // Mảng chứa các phần tử
    private InsertionSort insertionSort; // Lớp insertionSort để thực hiện sắp xếp

    public insertionSortDemo() {
        insertionSort = new InsertionSort(); // Khởi tạo đối tượng insertionSort
    }

    // Xử lý khi người dùng tick vào CheckBox Random
    @FXML
    void TickRandom(ActionEvent event) {
        // Nếu RandomBox được tick, người dùng có thể tạo mảng ngẫu nhiên
        if (RandomBox.isSelected()) {
            SoPhanTu.setDisable(false); // Cho phép nhập số phần tử khi RandomBox được tick
            Array.setDisable(true); // Tắt TextField nhập mảng khi RandomBox được tick
        } else {
            SoPhanTu.setDisable(true); // Tắt TextField nhập số phần tử khi RandomBox không được tick
            Array.setDisable(false); // Cho phép nhập mảng thủ công
        }
    }

    @FXML
    void RandomArray(ActionEvent event) {
        try {
            // Kiểm tra nếu người dùng tick vào CheckBox Random
            if (RandomBox.isSelected()) {
                int size = Integer.parseInt(SoPhanTu.getText()); // Lấy số phần tử từ TextField SoPhanTu
                array = new int[size];
                Random random = new Random();

                // Tạo mảng ngẫu nhiên
                for (int i = 0; i < size; i++) {
                    array[i] = random.nextInt(100); // Số ngẫu nhiên từ 0 đến 99
                }

                // Hiển thị mảng đã tạo trong TextField Array
                Array.setText(arrayToString(array));
            } else {
                // Nếu không chọn Random, lấy mảng thủ công từ TextField
                String input = Array.getText();
                String[] inputArray = input.split(" "); // Tách chuỗi thành mảng
                array = new int[inputArray.length];

                for (int i = 0; i < inputArray.length; i++) {
                    array[i] = Integer.parseInt(inputArray[i]);
                }
            }
        } catch (NumberFormatException e) {
            // Hiển thị thông báo nếu có lỗi
            showAlert("Error", "Please enter a valid number for the array size or the array values.");
        }
    }

    @FXML
    void Steps(ActionEvent event) {
        if (array == null || array.length == 0) {
            showAlert("Error", "Please generate a random array or enter an array manually first.");
            return;
        }
    
        // Thực hiện sắp xếp insertionSort và hiển thị các bước
        insertionSort.sort(array);
    
        // Sử dụng một Thread để cập nhật giao diện liên tục sau mỗi bước
        Thread sortingThread = new Thread(() -> {
            try {
                // Hiển thị các bước sắp xếp
                for (int i = 0; i < insertionSort.getSteps().size(); i++) {
                    final int stepIndex = i;
    
                    // Cập nhật giao diện trên GUI thread
                    javafx.application.Platform.runLater(() -> {
                        ArrayDemo.setText(arrayToString(insertionSort.getSteps().get(stepIndex)));
                    });
    
                    // Tạm dừng một chút để người dùng có thể thấy quá trình
                    Thread.sleep(750); // Giảm thời gian nghỉ để quá trình mượt mà hơn, tùy chỉnh
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    
        sortingThread.setDaemon(true); // Đặt Thread làm daemon để tự động kết thúc khi ứng dụng đóng
        sortingThread.start();
    }

        // Helper method để chuyển mảng thành chuỗi
        private String arrayToString(int[] array) {
            StringBuilder sb = new StringBuilder();
            for (int num : array) {
                sb.append(num).append(" ");
            }
            return sb.toString().trim(); // Loại bỏ dấu cách cuối cùng
        }

        // Helper method để hiển thị thông báo lỗi
        private void showAlert(String title, String message) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.showAndWait();
        }
}
