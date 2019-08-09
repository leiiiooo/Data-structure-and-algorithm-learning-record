package sort;

import sort.common.Tools;

/**
 * 选择排序
 * <p>
 * 选择排序(Selection sort)是一种简单直观的排序算法。
 * 它的工作原理是每一次从待排序的数据元素中选出最小(或最大)的一个元素，存放在序列的起始(末尾)位置，
 * 直到全部待排序的数据元素排完。选择排序是不稳定的排序方法（比如序列[5， 5， 3]
 * 第一次就将第一个[5]与[3]交换，导致第一个5挪动到第二个5后面）。
 */
public class SelectSort {
    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int endIndex = array.length - 1;

        int maxIndex;
        int maxValue;

        while (endIndex > 0) {
            maxIndex = 0;
            maxValue = array[0];

            for (int i = 1; i <= endIndex; i++) {
                if (array[i] > maxValue) {
                    maxValue = array[i];
                    maxIndex = i;
                }
            }

            Tools.swap(array, maxIndex, endIndex);
            endIndex--;
        }
    }
}
