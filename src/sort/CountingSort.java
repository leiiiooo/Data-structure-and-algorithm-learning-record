package sort;

/**
 * 计数排序
 * <p>
 * 时间复杂度为O(n)
 * <p>
 * 前提条件：待排序的元素在某一个范围[MIN, MAX]之间。
 * <p>
 * 计数排序需要一个辅助空间，空间大小为O(MAX-MIN)，用来存储所有元素出现次数(“计数”)。
 * 画外音：计数排序的核心是，空间换时间。
 * <p>
 * 插入排序、归并排序、堆排序、快速排序这四种排序算法，他们的运行时间上界不会超过O(nlgn)。
 * 这些算法都有一个有趣的性质：在排序的最终结果中，各元素的次序依赖于它们之间的比较。我们把这类排序算法称为比较排序。
 * <p>
 * 可以证明，基于比较的排序算法在最坏情况下的时间下界是Ω(nlgn)。
 * <p>
 * 堆排序和归并排序的运行时间上界为O(nlgn)，因此这两种排序算法都是渐进最优的比较排序算法。
 * <p>
 * 计数排序的关键步骤?
 * <p>
 * 步骤一：扫描待排序数据arr[N]，使用计数数组counting[MAX-MIN]，对每一个arr[N]中出现的元素进行计数;
 * 步骤二：扫描计数数组counting[]，还原arr[N]，排序结束;
 */
public class CountingSort {
    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
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

        //创建具有足够长索引的数组
        int indexArrayLength = maxValue - minValue + 1;
        int[] indexArray = new int[indexArrayLength];

        for (int i = 0; i < array.length; i++) {
            indexArray[array[i] - minValue]++;
        }

        int originCountIndex = 0;

        //遍历结果数组还原原数组
        for (int i = 0; i < indexArray.length; i++) {
            while (indexArray[i] > 0) {
                array[originCountIndex++] = i + minValue;
                indexArray[i]--;
            }
        }
    }
}
