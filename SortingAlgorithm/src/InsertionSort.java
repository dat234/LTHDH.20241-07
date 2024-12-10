import java.util.ArrayList;
import java.util.List;

public class InsertionSort implements SortingAlgorithm {
    private List<int[]> steps = new ArrayList<>();

    @Override
    public void sort(int[] array) {
        int n = array.length;
        steps.add(array.clone()); // Lưu trạng thái ban đầu

        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            // Di chuyển các phần tử của mảng lớn hơn key một vị trí về phía sau
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;

            // Lưu trạng thái sau mỗi bước
            steps.add(array.clone());
        }
    }

    @Override
    public List<int[]> getSteps() {
        return steps;
    }
}