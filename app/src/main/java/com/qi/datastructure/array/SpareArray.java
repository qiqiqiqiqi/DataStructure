package com.qi.datastructure.array;


/**
 * 稀疏数组:压缩数组，只存储有数据的节点
 * Created by feng on 2020/3/23.
 */
public class SpareArray {

    public int[][] toSpareArray(int[][] sourceArrays) {
        int count = 0;
        //有效数据的个数
        if (sourceArrays != null) {
            for (int[] i : sourceArrays) {
                for (int t : i) {
                    if (t > 0) {
                        count++;
                    }
                }
            }

            int[][] spareArray = new int[count + 1][3];//第一行存储原数组的行列数及有效数据的个数
            spareArray[0][0] = sourceArrays.length;
            spareArray[0][1] = sourceArrays[0].length;
            spareArray[0][2] = count;
            //填充稀疏数组
            count = 1;
            for (int i = 0; i < sourceArrays.length; i++) {
                for (int j = 0; j < sourceArrays[i].length; j++) {
                    if (sourceArrays[i][j] > 0) {
                        spareArray[count][0] = i;
                        spareArray[count][1] = j;
                        spareArray[count][2] = sourceArrays[i][j];
                        count++;
                    }
                }
            }

            for (int[] i : sourceArrays) {
                for (int t : i) {
                    System.out.printf("%-6d", t);
                }
                System.out.println();
            }
            System.out.println();
            for (int[] i : spareArray) {
                for (int t : i) {
                    System.out.printf("%-6d", t);
                }
                System.out.println();

            }
            System.out.println();
            return spareArray;
        }
        return null;
    }

    public int[][] toSourceArray(int[][] spareArray) {
        if (spareArray != null && spareArray[0].length == 3) {
            int[][] sourceArrays = new int[spareArray[0][0]][spareArray[0][1]];
            for (int[] arry : spareArray) {
                if (arry != spareArray[0])
                    sourceArrays[arry[0]][arry[1]] = arry[2];
            }
            for (int[] i : sourceArrays) {
                for (int t : i) {
                    System.out.printf("%-6d", t);
                }
                System.out.println();
            }
            return sourceArrays;
        }
        return null;
    }


}
