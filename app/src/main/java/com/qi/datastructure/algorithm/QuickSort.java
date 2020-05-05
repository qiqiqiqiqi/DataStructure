package com.qi.datastructure.algorithm;

import android.graphics.Bitmap;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LruCache;

/**
 * Created by feng on 2020/4/20.
 */
public class QuickSort {
    public static void main(String[] args) {

        LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(1024 * 1024 * 20) {
            @Override
            protected int sizeOf(@NonNull String key, @NonNull Bitmap value) {

                return value.getByteCount();
            }

            @Override
            protected void entryRemoved(boolean evicted, @NonNull String key, @NonNull Bitmap oldValue, @Nullable Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
            }
        };
        QuickSort quickSort = new QuickSort();
        int[] array = new int[20];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        quickSort.toString(array);
        int k = quickSort.findK(array, 4);
        System.out.println("k=" + k);
        quickSort.sort(array);
        quickSort.toString(array);

    }

    /**
     * @param array
     */
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * quickSort(array,start,end)=quickSort(array,start,partition()-1)+quickSort(array,partition()+1,end);
     *
     * @param array
     * @param start
     * @param end
     */
    private void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int partition = partition(array, start, end);
        quickSort(array, start, partition - 1);
        quickSort(array, partition + 1, end);
    }

    private void quickSort2(int[] array, int start, int end) {
        int partition = partition(array, start, end);

    }

    /**
     * 第k大值
     */
    public int findK(int[] array, int k) {
        if (array == null || array.length == 0 || array.length < k) {
            return -1;
        }
        int start = 0, end = array.length - 1;
        while (true) {
            int partition = partition(array, start, end);
            if (partition + 1 == k) {
                return array[partition];
            } else if (k > partition + 1) {
                start = partition + 1;
            } else {
                end = partition - 1;
            }
        }

    }

    private int partition(int[] array, int start, int end) {
        int pvoit = array[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (array[j] < pvoit) {
                if (i != j) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
                i++;
            }
        }
        array[end] = array[i];
        array[i] = pvoit;
        return i;
    }

    private void toString(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int num : array) {
            stringBuilder.append(num + " ");
        }
        System.out.println(
                stringBuilder.toString()
        );
    }
}
