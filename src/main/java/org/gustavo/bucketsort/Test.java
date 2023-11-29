/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.gustavo.bucketsort;

import org.gustavo.bucketsort.enums.AlgorithmEnum;
import org.gustavo.bucketsort.enums.Scenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    private int[] randomArray;

    public List<TestResult> run(int input, Scenario scenario) {
        initializeRandomArray(input);
        var results = new ArrayList<TestResult>();
        var insertion = sort(input, scenario, AlgorithmEnum.InsertionSort);
        var selection = sort(input, scenario, AlgorithmEnum.SelectionSort);
        var merge = sort(input, scenario, AlgorithmEnum.MergeSort);
        var quick = sort(input, scenario, AlgorithmEnum.QuickSort);
        var insertionBucket = sort(input, scenario, AlgorithmEnum.InsertionBucketSort);
        var selectionBucket = sort(input, scenario, AlgorithmEnum.SelectionBucketSort);
        var mergeBucket = sort(input, scenario, AlgorithmEnum.MergeBucketSort);
//        var radixBucket = sort(input, scenario, AlgorithmEnum.InsertionBucketSort);
        
        results.add(new TestResult("Insertion sort", insertion[0], insertion[1]));
        results.add(new TestResult("Selection sort", selection[0], selection[1]));
        results.add(new TestResult("Merge sort", merge[0], merge[1]));
        results.add(new TestResult("Quick sort", quick[0], quick[1]));
        results.add(new TestResult("Insertion bucket sort", insertionBucket[0], insertionBucket[1]));
        results.add(new TestResult("Selection bucket sort", selectionBucket[0], selectionBucket[1]));
        results.add(new TestResult("Merge bucket sort", mergeBucket[0], mergeBucket[1]));
        
        return results;
    }
    
    private long[] sort(int input, Scenario scenario, AlgorithmEnum algorithEnum) {
        int[] array = new int[input];
        
        for (int i = 0; i < input; ++i) {
            switch (scenario) {
                case Random:
                    array[i] = randomArray[i];
                    break;
                case Ascending:
                    array[i] = i;
                    break;
                case Descending:
                    array[i] = input - i - 1;
                    break;
            }
        }
        
        var algorithm = new Algorithm();
        var elapsedTime = System.nanoTime();
        long swaps = switch (algorithEnum) {
            case InsertionSort -> algorithm.insertionSort(array, array.length);
            case SelectionSort -> algorithm.selectionSort(array, array.length);
            case MergeSort -> algorithm.mergeSort(array, array.length);
            case QuickSort -> algorithm.quickSort(array, array.length);
            case InsertionBucketSort -> algorithm.bucketSort(array, array.length, AlgorithmEnum.InsertionSort);
            case SelectionBucketSort -> algorithm.bucketSort(array, array.length, AlgorithmEnum.SelectionSort);
            case MergeBucketSort -> algorithm.bucketSort(array, array.length, AlgorithmEnum.MergeSort);
        };

        elapsedTime = System.nanoTime() - elapsedTime;
        
        return new long[]{ elapsedTime, swaps };
    }
    
    private void initializeRandomArray(int size) {
        Random random = new Random();
        randomArray = new int[size];
        
        for (int i = 0; i < size; ++i) {
            randomArray[i] = random.nextInt(Integer.MAX_VALUE);
        }
    }
}
