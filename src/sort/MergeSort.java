package sort;

/**
 * 归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列分为若干个子序列，
 * 每个子序列是有序的，然后再把有序子序列合并为整体有序序列。
 * <p>
 * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。
 * 若将两个有序表合并成一个有序表，称为2-路归并。
 * <p>
 * 时间复杂度为O(nlogn)，空间复杂度为 O(n)，归并排序比较占用内存，但却效率高且是稳定的算法。
 */
public class MergeSort {
    public static void recursiveSort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    /**
     * 递归排序
     *
     * @param array
     * @param startIndex
     * @param endIndex
     */
    private static void sort(int[] array, int startIndex, int endIndex) {
        if (array == null || array.length <= 1) {
            return;
        }

        int centerIndex;

        if (startIndex < endIndex) {
            centerIndex = (startIndex + endIndex) / 2;
            sort(array, startIndex, centerIndex);
            sort(array, centerIndex + 1, endIndex);
            merge(array, startIndex, centerIndex, endIndex);
        }
    }

    public static void nonRecursiveSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        //归并的步长
        int len = 1;

        int start;
        int center;
        int end;

        while (len < array.length - 1) {
            //归并开始
            for (int i = 0; i + len <= array.length - 1; i += 2 * len) {
                start = i;
                center = i + len - 1;
                end = i + (2 * len) - 1;

                if (end > array.length - 1) {
                    end = array.length - 1;
                }

                merge(array, start, center, end);
            }

            len += len;
        }
    }

    private static void merge(int[] array, int startIndex, int centerIndex, int endIndex) {
        int[] copyArray = new int[array.length];
        //最终将数据拷贝回愿数组的时候，原始数组的索引位置
        int copyStartIndex = startIndex;

        int tempMiddleIndex = centerIndex + 1;
        //将比较结果依次插入当前新创建的数组的索引
        int tempStartIndex = startIndex;

        while (startIndex <= centerIndex && tempMiddleIndex <= endIndex) {
            if (array[startIndex] <= array[tempMiddleIndex]) {
                copyArray[tempStartIndex++] = array[startIndex++];
            } else {
                copyArray[tempStartIndex++] = array[tempMiddleIndex++];
            }
        }

        //将剩余数据插入到copy集合中
        while (startIndex <= centerIndex) {
            copyArray[tempStartIndex++] = array[startIndex++];
        }

        while (tempMiddleIndex <= endIndex) {
            copyArray[tempStartIndex++] = array[tempMiddleIndex++];
        }

        //将copy array 中的数据回写到原始数组中
        while (copyStartIndex <= endIndex) {
            array[copyStartIndex] = copyArray[copyStartIndex++];
        }
    }
}
