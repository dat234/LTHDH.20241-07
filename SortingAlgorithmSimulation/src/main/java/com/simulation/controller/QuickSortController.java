package com.simulation.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Arrays;
import java.util.List;
import com.simulation.util.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class QuickSortController extends SortingAlgorithmController {

    private int pivot = 0;
    @FXML private TextField inputTextField;
    @FXML private TextArea outputTextField;
    @FXML private TextField minTextField;
    @FXML private TextField maxTextField;
    @FXML private TextField sizeTextField;
    @FXML private TextField delayTextField;
    @FXML private TextField firstTextField;
    @FXML private TextField secondTextField;
    @FXML private TextField pivotTextField;
    @FXML private CheckBox descendingBox;
    @FXML private Button returnBtn;

    @FXML private void initialize() {
        outputTextField.setWrapText(true);
    }

    @FXML private void returnOption() {
        Stage stage = (Stage)returnBtn.getScene().getWindow();
        stage.close();
    }

    @FXML private void createRandomArray() {
        if (sizeTextField.getText().isEmpty() || minTextField.getText().isEmpty() || maxTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Mục min, max, và kích thước cần phải được nhập!");
            alert.showAndWait();
            return;
        }
        int size = Integer.parseInt(sizeTextField.getText());
        int min = Integer.parseInt(minTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());
        List<Integer> randomList = Utility.generateRandomList(size, min, max);
        String inputPrompt = String.join(" ", randomList.stream().map(String::valueOf).toArray(String[]::new));
        inputTextField.setText(inputPrompt);
    }

    @Override public void swap(int first, int second) {
        int firstValue = array[first];
        int secondValue = array[second];
        int pivotValue = pivot;
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
        int[] currentArray = new int[array.length];
        System.arraycopy(array, 0, currentArray, 0, array.length);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(timeIndex * delay), e -> {
            firstTextField.setText(String.valueOf(firstValue));
            secondTextField.setText(String.valueOf(secondValue));
            pivotTextField.setText(String.valueOf(pivotValue));
            outputTextField.setText(Arrays.toString(currentArray));
        }));
        timeIndex++;
    }

    private int partition(int low, int high) {
        pivot = array[low + (high - low) / 2];
        while (true) {
            while ((array[low] < pivot && ascending) || (array[low] > pivot && !ascending)) low++;
            while ((array[high] > pivot && ascending) || (array[high] < pivot && !ascending)) high--;
            if (low >= high) return high;
            swap(low, high);
            low++;
            high--;
        }
    }

    private void recursion(int low, int high) {
        if (high > low) {
            int partitionIndex = partition(low, high);
            recursion(low, partitionIndex);
            recursion(partitionIndex + 1, high);
        }
    }

    @Override public void sort() { recursion(0, array.length - 1); }

    @FXML private void beginSort() {
        if (inputTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Bạn chưa nhập mảng");
            alert.showAndWait();
            return;
        }
        String[] arrayString = inputTextField.getText().split(" ");
        array = new int[arrayString.length];
        for (int index = 0; index < arrayString.length; index++)
            array[index] = Integer.parseInt(arrayString[index]);
        ascending = !descendingBox.isSelected();
        delay = delayTextField.getText().isEmpty() ? 500 : Long.parseLong(delayTextField.getText());
        timeline = new Timeline();
        timeIndex = 0;
        sort();
        timeline.play();
    }
}