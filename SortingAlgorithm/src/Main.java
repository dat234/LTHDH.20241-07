import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Array arrayObj = new Array();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Nhập kích thước mảng:");
        int size = scanner.nextInt();
        
        int[] inputArray = new int[size];
        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < size; i++) {
            inputArray[i] = scanner.nextInt();
        }
        
        arrayObj.setArray(inputArray);

        int[] originalArray = arrayObj.getArray();

        SortingAlgorithm quickSort = new QuickSort();
        System.out.println("\nQuickSort:");
        int[] quickSortArray = originalArray.clone(); // Tạo bản sao của mảng ban đầu
        quickSort.sort(quickSortArray);
        printSteps(quickSort.getSteps());

        // Chạy InsertionSort và hiển thị các bước
        SortingAlgorithm insertionSort = new InsertionSort();
        System.out.println("\nInsertionSort:");
        int[] insertionSortArray = originalArray.clone(); // Tạo bản sao của mảng ban đầu
        insertionSort.sort(insertionSortArray);
        printSteps(insertionSort.getSteps());

        // Chạy BubbleSort và hiển thị các bước
        SortingAlgorithm bubbleSort = new BubbleSort();
        System.out.println("\nBubbleSort:");
        int[] bubbleSortArray = originalArray.clone(); // Tạo bản sao của mảng ban đầu
        bubbleSort.sort(bubbleSortArray);
        printSteps(bubbleSort.getSteps());
    }

    // Phương thức in ra các bước sắp xếp
    private static void printSteps(List<int[]> steps) {
        for (int[] step : steps) {
            for (int i : step) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}

