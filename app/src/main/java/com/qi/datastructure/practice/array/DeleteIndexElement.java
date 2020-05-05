package com.qi.datastructure.practice.array;

/**
 * 1~100的int数组，循环打印输出index的为8的倍数的value并删除，要求，效率要高
 * Created by feng on 2020/4/19.
 */
public class DeleteIndexElement {

    public void deleteElement(int[] array, int range) {
        int deleteSumCount = 0;
        for (int i = 0; i < array.length; i += range) {
            array[i] = 0;//删除元素
            for (int j = i + 1; j < array.length && j < i + range; j++) {
                array[j - (deleteSumCount + 1)] = array[j];
                array[j] = 0;//删除元素
            }

            deleteSumCount++;
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
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        DeleteIndexElement deleteIndexElement = new DeleteIndexElement();
        deleteIndexElement.deleteElement(array, 8);

    }

}
