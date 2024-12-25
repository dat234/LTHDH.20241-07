package com.simulation.controller;

import javafx.animation.Timeline;

public abstract class SortingAlgorithmController {

    protected int[] array;
    protected boolean ascending;
    protected long delay;
    protected Timeline timeline;
    protected long timeIndex;

    public SortingAlgorithmController() {}

    public abstract void swap(int first, int second);

    public abstract void sort();
}