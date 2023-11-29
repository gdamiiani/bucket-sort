/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.gustavo.bucketsort;


import org.gustavo.bucketsort.enums.AlgorithmEnum;

import java.util.Arrays;

public class Algorithm {
    public long bucketSort(int[] array, int length, AlgorithmEnum algorithmEnum) {
        var optInt = Arrays.stream(array).max();

        assert optInt.isPresent();

        long swaps = 0;
        int max = optInt.getAsInt();
        int numberOfBuckets = (int) Math.sqrt(length);
        int[][] buckets = new int[numberOfBuckets][length];
        int[] indexBuckets = new int [numberOfBuckets];

        for (int i = 0; i < length; ++i) {
            int bucketIndex = (int) ((double) array[i] / (max + 1) * numberOfBuckets);
            buckets[bucketIndex][indexBuckets[bucketIndex]] = array[i];
            
            ++indexBuckets[bucketIndex];
        }
        
        for (int i = 0; i < numberOfBuckets; ++i) {
            switch (algorithmEnum) {
                case InsertionSort:
                    swaps += insertionSort(buckets[i], indexBuckets[i]);
                    break;
                case SelectionSort:
                    swaps += selectionSort(buckets[i], indexBuckets[i]);
                    break;
                case MergeSort:
                    swaps += mergeSort(buckets[i], indexBuckets[i]);
                    break;
                case QuickSort:
                    swaps += quickSort(buckets[i], indexBuckets[i]);
                    break;
            }
        }
        
        int arrayIndex = 0;
        
        for (int i = 0; i < numberOfBuckets; ++i) {
            for (int j = 0; j < indexBuckets[i]; ++j) {
                array[arrayIndex] = buckets[i][j];
                ++arrayIndex;
            }
        }
        
        return swaps;
    }
    
    public long insertionSort(int[] array, int length) {
        return insertionSort(array, 0, length);
    }
    
    public long insertionSort(int[] array, int lo, int hi) {
        long swaps = 0;

        for (int i = lo + 1; i < hi; ++i) {
            int j = i;

            while (j > 0 && array[j] < array[j - 1]) {
                swaps += swap(array, j, j - 1);
                --j;
            }
        }
        
        return swaps;
    }
    
    public long selectionSort(int[] array, int length) {
        return selectionSort(array, 0, length);
    }
    
    public long selectionSort(int[] array, int lo, int hi) {
        long swaps = 0;

        for (int i = lo; i < hi - 1; ++i) {
            for (int j = i + 1; j < hi; ++j) {
                if (array[j] < array[i]) {
                    swaps += swap(array, i, j);
                }
            }
        }
        
        return swaps;
    }
    
    public long mergeSort(int[] array, int length) {
        return mergeSort(array, 0, length);
    }
    
    public long mergeSort(int[] array, int lo, int hi) {
        if (hi - lo < 2) {
            return 0;
        }
        
        int mid = (lo + hi) / 2;        
        long swaps = 0;
        swaps += mergeSort(array, lo, mid);
        swaps += mergeSort(array, mid, hi);
        swaps += merge(array, lo, mid, hi);

        return swaps;
    }
    
    private long merge(int[] array, int lo, int mid, int hi) {
        int[] out = new int[hi - lo];
        int i = 0;
        int j = lo;
        int k = mid;
        long swaps = 0;
        
        while (j < mid && k < hi) {
            if (array[j] < array[k]) {
                out[i] = array[j];
                ++j;
            } else {
                out[i] = array[k];
                ++k;
            }
            
            ++i;
        }
        
        while (j < mid) {
            out[i] = array[j];
            ++i;
            ++j;
        }
        
        while (k < hi) {
            out[i] = array[k];
            ++i;
            ++k;
        }
        
        for (int l = 0; l < i; ++l) {
            array[lo + l] = out[l];
            ++swaps;
        }
        
        return swaps;
    }
    
    public long quickSort(int[] array, int length) {
        return quickSort(array, 0, length - 1);
    }
    
    public long quickSort(int[] array, int lo, int hi) {
        if (hi - lo < 2) {
            return 0;
        }
        
        int[] partition = partition(array, lo, hi);
        int pivotIndex = partition[0];
        long swaps = partition[1];
        
        swaps += quickSort(array, lo, pivotIndex - 1);
        swaps += quickSort(array, pivotIndex + 1, hi);
        
        return swaps;
    }
    
    private int[] partition(int[] array, int lo, int hi) {
        int mid = (lo + hi) / 2;
        
        if (array[mid] < array[lo]) {
            swap(array, mid, lo);
        }
        
        if (array[hi] < array[lo]) {
            swap(array, hi, lo);
        }
        
        if (array[mid] < array[hi]) {
            swap(array, mid, hi);
        }
        
        int pivot = array[hi];
        int pivotIndex = lo;
        int swaps = 0;

        for (int j = lo; j < hi; ++j) {
            if (array[j] <= pivot) {
                swaps += swap(array, pivotIndex, j);
                ++pivotIndex;
            }
        }
        
        swaps += swap(array, pivotIndex, hi);
        
        return new int[] { pivotIndex, swaps };
    }
    
    private int swap(int[] array, int indexA, int indexB) {
        if (indexA == indexB) return 0;
        
        array[indexA] = array[indexA] + array[indexB];
        array[indexB] = array[indexA] - array[indexB];
        array[indexA] = array[indexA] - array[indexB];
        
        return 1;
    }
}
