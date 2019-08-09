package sort;

import sort.common.Tools;

import java.util.Stack;

/**
 * 在数组的头部和尾部分别设置一个哨兵，同时向对方走去。尾部的哨兵如发现有比基准数小的数，停下。
 * 头部的哨兵如发现有比基准数大的数，停下。交换两个数。
 * 再重新走重复前面的交换过程。直到两个哨兵相遇，交换基准数和尾哨兵。
 * <p>
 * 有一点需特别注意：若以第一个元素为基准数（就如上面的示例），在哨兵互走过程需右边的哨兵先走。
 * 原因很好理解，看上面过程解析就会明白：哨兵互走交换的过程就是不断排序的过程。
 * 若右边的哨兵先走，不管走多少次，最后相遇时的那个数是小于基准数的。
 * 这时与基准数交换，正好分为两个序列。可若是左边的先走，相遇在大于基准数上就不好办了。
 * <p>
 * 6 1 2 5 [9] 3 [4] 7 10 8
 * 6 1 2 5 [4] 3 [9] 7 10 8
 * 3 1 2 5 [4] 6 [9] 7 10 8
 */
public class QuickSort {
    /**
     * 非递归版本
     *
     * @param array
     * @return
     */
    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        Stack<Integer> indexStack = new Stack<>();
        //end index
        indexStack.push(array.length - 1);
        //start index
        indexStack.push(0);
        while (!indexStack.empty()) {
            int low = indexStack.pop();
            int high = indexStack.pop();

            if (low >= high) {
                continue;
            }

            int i = low;
            int j = high;
            int base = array[low];

            while (i < j) {
                // TODO: 2019/6/11 一定要右侧先行
                while (array[j] >= base && i < j) {
                    j--;
                }

                while (array[i] <= base && i < j) {
                    i++;
                }

                Tools.swap(array, i, j);
            }

            Tools.swap(array, low, j);

            //cache index
            indexStack.push(high);
            indexStack.push(j + 1);

            indexStack.push(j - 1);
            indexStack.push(low);
        }
    }

    /**
     * 递归版本
     * <p>
     * low,high 为每次处理数组时的首、尾元素索引
     * <p>
     * 当low==high是表示该序列只有一个元素，不必排序了
     *
     * @param array
     * @return
     */
//    public static void quickSort(int[] arr, int low, int high) {
//        if(arr == null || arr.length <= 1){
//            return;
//    }
//        if (low >= high) {
//            return;
//        }
//        // 选出哨兵元素和基准元素。这里左边的哨兵元素也是基准元素
//        int i = low, j = high, base = arr[low];
//        while (i < j) {
//            //右边哨兵从后向前找
//            while (arr[j] >= base && i < j) {
//                j--;
//            }
//            //左边哨兵从前向后找
//            while (arr[i] <= base && i < j) {
//                i++;
//            }
//            //交换元素
//            Tools.swap(arr, i, j);
//        }
//        //基准元素与右哨兵交换
//        Tools.swap(arr, low, j);
//
//        //递归调用，排序左子集合和右子集合
//        quickSort(arr, low, j - 1);
//        quickSort(arr, j + 1, high);
//
//    }
}
