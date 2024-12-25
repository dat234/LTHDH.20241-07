package com.simulation.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utility {
    public static List<Integer> generateRandomList(int size, int min, int max) {
        Random random = new Random();
        List<Integer> randomList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int randomNum = random.nextInt(max - min + 1) + min;
            randomList.add(randomNum);
        }
        return randomList;
    }
}