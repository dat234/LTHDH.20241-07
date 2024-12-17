import java.util.ArrayList;
import java.util.List;

public class QuickSort implements SortingAlgorithm {
    private List<int[]> steps = new ArrayList<>(); // Danh sách các trạng thái của mảng
    private List<List<Integer>> highlightedIndices = new ArrayList<>(); // Danh sách chỉ số cần highlight (phần tử đang xét và pivot)
    private List<Integer> pivotIndices = new ArrayList<>(); // Danh sách chỉ số pivot tại mỗi bước

    @Override
    public void sort(int[] array) {
        steps.clear();
        highlightedIndices.clear();
        pivotIndices.clear();

        steps.add(array.clone());
        highlightedIndices.add(new ArrayList<>()); // Highlight rỗng cho trạng thái ban đầu
        pivotIndices.add(-1); // Không có pivot ban đầu

        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        pivotIndices.add(high); // Lưu chỉ số pivot
        highlightedIndices.add(new ArrayList<>()); // Không highlight tại trạng thái ban đầu của bước này

        int i = low - 1;

        for (int j = low; j < high; j++) {
            highlightedIndices.add(List.of(j, high)); // Highlight phần tử đang xét (j) và pivot (high)
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
                steps.add(array.clone());
                highlightedIndices.add(List.of(i, j, high)); // Highlight phần tử hoán đổi và pivot
                pivotIndices.add(high); // Lưu chỉ số pivot
            }
        }

        swap(array, i + 1, high);
        steps.add(array.clone());
        highlightedIndices.add(List.of(i + 1, high)); // Highlight phần tử hoán đổi cuối cùng và pivot
        pivotIndices.add(high); // Lưu chỉ số pivot

        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    @Override
    public List<int[]> getSteps() {
        return steps;
    }

    public List<List<Integer>> getHighlightedIndices() {
        return highlightedIndices;
    }

    public List<Integer> getPivotIndices() {
        return pivotIndices;
    }
}
