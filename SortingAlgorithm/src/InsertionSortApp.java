import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InsertionSortApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Tải giao diện từ tệp FXML
        Parent root = FXMLLoader.load(getClass().getResource("insertionSortDemo.fxml"));
        
        // Đặt tiêu đề cho cửa sổ
        primaryStage.setTitle("Insertion Sort Demo");
        
        // Gắn giao diện vào Scene
        primaryStage.setScene(new Scene(root, 600, 400));
        
        // Hiển thị cửa sổ
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Khởi động ứng dụng
    }
}
