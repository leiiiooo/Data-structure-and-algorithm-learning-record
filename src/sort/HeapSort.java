package sort;

import sort.common.Tools;

/**
 * 堆排序(https://juejin.im/post/5bea6af051882548161b0f02)
 * <p>
 * 堆排序是指利用堆这种数据结构所设计的一种排序算法。
 * 堆是一个近似完全二叉树的结构，
 * 并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
 * <p>
 * 堆的特征
 * <p>
 * 堆的数据结构近似完全二叉树，即每个节点存在两个子节点
 * 当节点的值小于或等于父节点值，大于或等于子节点值称为大顶堆（也即根节点的值最大）
 * 当节点的值大于或等于父节点值，小于或等于子节点值称为小顶堆（也即根节点的值最小）
 * 若当前节点的索引为 k(用数组模拟的话是从索引0开始的，其左节点索引才为2k+1) , 那么左子节点索引为 2k + 1,
 * 右子节点索引为 2k + 2, 父节点索引为 (k - 1) / 2
 * <p>
 * 因为堆是完全二叉树的特性, 所以下标小于等于 array.length / 2 的节点为非叶子节点(使用下沉的时候，是对所有非叶子节点进行操作)
 * <p>
 */
public class HeapSort {
    /**
     * 本文采用上浮+大顶堆 进行数组排序
     * <p>
     * 首先将无序数组构造成一个最大堆，此时根节点为最大值
     * 将最后一个节点与根节点值交换，剔除最大值节点；
     * 将剩下节点重新执行构造堆
     * 循环执行第 2,3 两步操作
     *
     * @param array
     */
    public static void upSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        //使用上浮构造大顶堆
        for (int i = 1; i < array.length; i++) {
            siftUp(i, array);
        }

        int endIndex = array.length - 1;
        while (endIndex > 0) {

            Tools.swap(array, 0, endIndex);

            for (int i = 1; i <= endIndex - 1; i++) {
                siftUp(i, array);
            }

            endIndex--;
        }
    }

    public static void downSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        for (int i = array.length / 2; i >= 0; i--) {
            siftDown(i, array.length, array);
        }

        int n = array.length - 1;

        while (n > 0) {
            // 因为每次完成堆的构造后, 根节点为最大(小)值节点
            // 将根节点与最后一个节点交换
            Tools.swap(array, 0, n);

            for (int i = n / 2; i >= 0; i--) {
                // 排除有序的节点
                // 重新构造堆
                siftDown(i, n, array);
            }
            n--;
        }
    }


    /**
     * 上浮操作
     *
     * @param index
     */
    private static void siftUp(int index, int[] array) {
        int parentIndex;

        while (index > 0) {
            parentIndex = (index - 1) / 2;

            if (array[index] <= array[parentIndex]) {
                break;
            }

            //交换同时改变索引
            Tools.swap(array, index, parentIndex);
            index = parentIndex;
        }
    }

    /**
     * 下沉操作
     *
     * @param index
     * @param length
     */
    private static void siftDown(int index, int length, int[] array) {
        // loop
        // 判断是否存在子节点
        int childIndex = 2 * index + 1;

        while (childIndex < length) {
            // 查找最大的子节点
            if (childIndex + 1 < length && array[childIndex + 1] >= array[childIndex]) {
                childIndex++;
            }
            // 若当前节点大于子节点 退出循环
            if (array[index] > array[childIndex]) {
                break;
            }

            // 判断当前节点是否小于子节点, 若小于执行交换
            Tools.swap(array, index, childIndex);
            // 改变 k 指向
            index = childIndex;

            childIndex = 2 * index + 1;
        }
    }
}
