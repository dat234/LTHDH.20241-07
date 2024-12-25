import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InsertionSortApp extends Application {

    private static Stage mainStage; // Stage chính để thay đổi Scene

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Lưu tham chiếu đến Stage chính
        mainStage = primaryStage;

        // Tải giao diện từ tệp FXML (Khởi động ứng dụng với mainMenu.fxml)
        Parent root = FXMLLoader.load(getClass().getResource("insertionSortDemo.fxml"));

        // Đặt tiêu đề cho cửa sổ
        primaryStage.setTitle("Sorting Algorithms");

        // Gắn giao diện vào Scene
        primaryStage.setScene(new Scene(root, 600, 400));

        // Hiển thị cửa sổ
        primaryStage.show();
    }

    // Phương thức tĩnh để thay đổi Scene
    public static void changeScene(String fxmlFile) throws Exception {
        if (mainStage == null) {
            throw new IllegalStateException("Main stage has not been initialized yet.");
        }

        // Tải giao diện từ tệp FXML
        Parent root = FXMLLoader.load(InsertionSortApp.class.getResource(fxmlFile));

        // Thay đổi Scene trên Stage chính
        Scene newScene = new Scene(root);
        mainStage.setScene(newScene);
        mainStage.setWidth(newScene.getWidth());
        mainStage.setHeight(newScene.getHeight());
    }

    public static void main(String[] args) {
        launch(args); // Khởi động ứng dụng
    }
}
