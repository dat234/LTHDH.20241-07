package com.simulation.controller;

import com.simulation.view.BubbleSortMenu;
import com.simulation.view.InsertionSortMenu;
import com.simulation.view.QuickSortMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;

public class MainMenuController {

    @FXML private void bubbleSortRedirect() throws IOException {
        new BubbleSortMenu();
    }

    @FXML private void quickSortRedirect() throws IOException {
        new QuickSortMenu();
    }

    @FXML private void insertionSortRedirect() throws IOException {
        new InsertionSortMenu();
    }

    @FXML private void displayHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông tin");
        alert.setHeaderText("Thông tin và hướng dẫn cơ bản về chương trình");
        alert.setContentText("""
                1. Mục đích của chương trình:
                Mô phỏng ba thuật toán sắp xếp phổ biến: Sắp xếp nổi bọt, sắp xếp chèn, sắp xếp nhanh.
                2. Cách dùng:
                2.1 Nhập mảng:
                Có 2 cách để nhập mảng. Hoặc là tự nhập vào bằng bàn phím, mỗi phần tử của mảng phải là số nguyên\
                 và được cách nhau bằng một dấu cách, hoặc là nhập khoảng giá trị và kích thước mảng, sau đó nhấn nút \
                tạo mảng ngẫu nhiên và hệ thống sẽ tự sinh ra mảng ngẫu nhiên để điền vào ô trống.
                2.2 Các phần phụ trợ (không yêu cầu nhập):
                Nếu muốn sắp xếp mảng theo thứ tự giảm dần, hãy tích vào ô sắp xếp giảm dần. Nếu muốn điều chỉnh thời \
                gian dừng giữa mỗi bước swap để có thể theo dõi rõ hơn quá trình mảng được sắp xếp, hãy nhập thời gian \
                vào phần delay theo đơn vị là mili-giây (ms).
                2.3 Thực hiện sắp xếp:
                Sau khi đã nhập mảng hãy nhấn nút bắt đầu để sắp xếp
                Sau mỗi bước hoán đổi vị trí, mảng sẽ được hiện ở thanh mảng đầu ra, trong khi các phần từ được chọn \
                để hoán đổi sẽ được ghi ở 2 ô vuông bên phải, riêng Quick Sort sẽ có thêm một ô nữa để hiển thị vị trí \
                của phần tử chốt.""");
        alert.showAndWait();
    }

    @FXML private void displayAboutUs() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông tin");
        alert.setHeaderText("Về chúng tôi");
        alert.setContentText("""
                Nhóm 7:
                20225708 - Trần Hoàng Dũng
                20225811 - Nguyễn Minh Đức
                20225610 - Lê Minh Đức
                20225607 - Vũ Thành Đạt
                20194517 - Phạm Minh Đức""");
        alert.showAndWait();
    }
}