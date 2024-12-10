import java.util.Random;

public class Array {
    private int[] array;

    // Tạo mảng ngẫu nhiên
    public void createRandomArray(int size, int maxValue) {
        Random random = new Random();
        array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(maxValue) + 1;
        }
    }

    // Lấy mảng hiện tại
    public int[] getArray() {
        return array;
    }

    // Đặt mảng từ người dùng
    public void setArray(int[] inputArray) {
        array = inputArray.clone();
    }
}
