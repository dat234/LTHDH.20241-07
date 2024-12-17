import java.util.ArrayList;
import java.util.List;

public class BubbleSort implements SortingAlgorithm {
    private List<int[]> steps = new ArrayList<>();
    private List<Integer> index1Steps = new ArrayList<>();
    private List<Integer> index2Steps = new ArrayList<>();

    @Override
    public void sort(int[] array) {
        steps.clear();
        index1Steps.clear();
        index2Steps.clear();

        steps.add(array.clone()); // Lưu trạng thái ban đầu

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                index1Steps.add(j); // Lưu chỉ số phần tử thứ nhất
                index2Steps.add(j + 1); // Lưu chỉ số phần tử thứ hai

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    swapped = true;
                    steps.add(array.clone()); // Lưu trạng thái sau khi hoán đổi
                }
            }

            if (!swapped) break; // Nếu không có hoán đổi, kết thúc sớm
        }
    }

    public int getIndex1AtStep(int step) {
        return index1Steps.get(step);
    }

    public int getIndex2AtStep(int step) {
        return index2Steps.get(step);
    }

    @Override
    public List<int[]> getSteps() {
        return steps;
    }
}

