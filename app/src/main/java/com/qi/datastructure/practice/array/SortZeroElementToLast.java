package com.qi.datastructure.practice.array;

/**
 * Created by feng on 2020/3/31.
 */
public class SortZeroElementToLast {
    public void sortZeroElementToLast(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] != 0) {
                    continue;
                }
                array[j] = array[j + 1];
                array[j + 1] = 0;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int num : array) {
            stringBuilder.append(num + " ");
        }
        System.out.println(
                stringBuilder.toString()
        );

    }

    public static void main(String[] args) {
        int[] array = {1, 5, 0, 3, 0, 23, 9, 0, 2, 7};
        SortZeroElementToLast sortZeroElementToLast = new SortZeroElementToLast();
        sortZeroElementToLast.sortZeroElementToLast(array);
    }
}
