import java.util.ArrayList;
import java.util.List;

public class BubbleSort implements SortingAlgorithm {
    private List<int[]> steps = new ArrayList<>();

    @Override
    public void sort(int[] array) {
        steps.clear();
        steps.add(array.clone());

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    swapped = true;
                    steps.add(array.clone());
                }
            }
            if (!swapped) break;
        }
    }

    @Override
    public List<int[]> getSteps() {
        return steps;
    }
}

