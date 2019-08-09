package sort;

/**
 * 插入排序
 * <p>
 * 简单插入排序(稳定的排序算法)
 * <p>
 * 直接插入排序(Insertion Sort)的基本思想是：每次将一个待排序的记录，
 * 按其关键字大小插入到前面已经排好序的子序列中的适当位置，
 * 直到全部记录插入完成为止。
 * <p>
 * 实现：从头到尾遍历数组，设置一个变量作为哨兵，记录当前元素；
 * 然后从当前位置依次往前寻找插入点，如果哨兵元素值要小，
 * 就将前面的元素往后移动一位，直到哨兵元素大于前面的元素为止。
 * <p>
 * 希尔排序
 */

public class InsertSort {
    /**
     * 从小到大排序
     *
     * @param array
     */
    public static void simpleSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int anchor;


        for (int i = 1; i < array.length; i++) {
            anchor = array[i];
            int j = (i - 1);

            while (j >= 0 && array[j] > anchor) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = anchor;
        }
    }
}
