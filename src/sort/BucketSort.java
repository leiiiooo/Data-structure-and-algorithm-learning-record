package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 桶排序
 * <p>
 * 重点其实就是桶数量的选择，根据给定的具体区间的数据，
 * 确定桶的数量。避免数据过于集中
 * <p>
 * 桶排序的适用范围是，待排序的元素能够均匀分布在某一个范围[MIN, MAX]之间
 * <p>
 * 桶排序需要两个辅助空间：
 * <p>
 * 第一个辅助空间，是桶空间B
 * 第二个辅助空间，是桶内的元素链表空间
 * <p>
 * 总的来说，空间复杂度是O(n)。
 * <p>
 * 桶排序有两个关键步骤：
 * <p>
 * 扫描待排序数据A[N]，对于元素A[i]，放入对应的桶X
 * A[i]放入桶X，如果桶X已经有了若干元素，使用插入排序，将arr[i]放到桶内合适的位置
 */
public class BucketSort {

    public static ArrayList sort(int[] array) {
        if (array == null || array.length <= 1) {
            return (ArrayList) Arrays.asList(array);
        }

        int minValue = array[0];
        int maxValue = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
                continue;
            }

            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }

        int bucketNumber = (maxValue - minValue) / 2;

        ArrayList[] bucketArray = new ArrayList[bucketNumber];

        int interval = (maxValue - minValue) / (bucketNumber);

        for (int value : array) {
            int index = (value - minValue) / interval;

            //越界检查
            if (index >= (bucketNumber - 1)) {
                index = bucketNumber - 1;
            }

            if (bucketArray[index] == null) {
                bucketArray[index] = new ArrayList();
            }

            bucketArray[index].add(value);
        }

        //对桶中的数据使用插入排序
        for (int i = 0; i < bucketArray.length; i++) {
            if (bucketArray[i] != null && bucketArray[i].size() >= 1) {
                Collections.sort(bucketArray[i]);
            }
        }
        //还原原数据
        ArrayList result = new ArrayList();
        for (int i = 0; i < bucketArray.length; i++) {
            if (bucketArray[i] != null && bucketArray[i].size() >= 1) {
                result.addAll(bucketArray[i]);
            }
        }

        return result;
    }
}
