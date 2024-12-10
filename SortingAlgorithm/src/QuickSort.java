import java.util.ArrayList;
import java.util.List;

public class QuickSort implements SortingAlgorithm {
    private List<int[]> steps = new ArrayList<>();

    @Override
    public void sort(int[] array) {
        steps.clear();
        steps.add(array.clone());
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
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
                steps.add(array.clone());
            }
        }

        swap(array, i + 1, high);
        steps.add(array.clone());
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
}
