import java.util.ArrayList;
import java.util.List;

public class InsertionSort implements SortingAlgorithm {
    private List<int[]> steps = new ArrayList<>();
    private List<List<Integer>> highlightedIndices = new ArrayList<>(); // Danh sách các danh sách chỉ số cần highlight cho từng bước

    @Override
    public void sort(int[] array) {
        int n = array.length;
        steps.add(array.clone()); // Lưu trạng thái ban đầu
        highlightedIndices.add(new ArrayList<>()); // Danh sách rỗng cho bước đầu tiên

        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            // Di chuyển các phần tử của mảng lớn hơn key một vị trí về phía sau
            List<Integer> currentHighlight = new ArrayList<>();
            while (j >= 0 && array[j] > key) {
                currentHighlight.add(j + 1); // Highlight phần tử hiện tại đang xét
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;

            // Lưu trạng thái và danh sách highlight
            steps.add(array.clone());
            highlightedIndices.add(currentHighlight);
        }
    }

    @Override
    public List<int[]> getSteps() {
        return steps;
    }

    public List<List<Integer>> getHighlightedIndices() {
        return highlightedIndices;
    }
}
