package com.qi.datastructure.algorithm;

import java.util.Random;

/**
 * https://time.geekbang.org/column/article/41913
 * Created by feng on 2020/4/20.
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] array = new int[20];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        mergeSort.toString(array);
        mergeSort.sort(array);
        mergeSort.toString(array);

    }

    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
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

    /**
     * 一直折半拆分直至一个元素然后合并，分而治之
     * mergeSort(array,start,end)=merge(mergeSort(array,start,(start+end))+mergeSort(array,(start+end)+1,end))
     * 退出条件 start>=end
     *
     * @param array
     * @param start
     * @param end
     */
    private void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;
            mergeSort(array, start, middle);
            mergeSort(array, middle + 1, end);
            merge(array, start, end);
        }
    }

    private void merge(int[] array, int start, int end) {
        //定义一个临时的数组存储有序的数据
        int[] tempArray = new int[end - start + 1];
        int e0 = (start + end) / 2;
        int index = 0, s0 = start, s1 = e0 + 1;
        while (s0 <= e0 && s1 <= end) {
            if (array[s0] <= array[s1]) {
                tempArray[index++] = array[s0++];
            } else {
                tempArray[index++] = array[s1++];
            }
        }
        //哪段还有数据
        int s = s0, e = e0;
        if (s1 <= end) {
            s = s1;
            e = end;
        }
        //复制剩余的数据到临时数组
        while (s <= e) {
            tempArray[index++] = array[s++];
        }
        //copy至原来的数组
        for (int value : tempArray) array[start++] = value;
    }

}
