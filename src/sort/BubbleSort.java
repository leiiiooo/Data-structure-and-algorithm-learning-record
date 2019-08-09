package sort;

/**
 * 对当前还未排好序的范围内的全部数，自上而下对相邻的俩个数依次进行比较和调整，
 * 让较大的数下沉，较小的数往上冒。
 * 即：每当俩相邻的数比较后发现他们的排序与排序的要求相反时，就将他们交换。
 * 每次遍历都可确定一个最大值放到待排数组的末尾，下次遍历，
 * 对该最大值以及它之后的元素不再排序（已经排好）。
 * <p>
 * 优化1：可以通过加入标志位，标记是否发生了数字调换，如果没有
 * 则说明当前顺序已经正确，则可以直接break循环，无需再次循环
 * <p>
 * 优化2：设置一个pos指针，pos后面的数据在上一轮排序中没有发生交换，
 * 下一轮排序时，就对pos之后的数据不再比较。
 */
public class BubbleSort {
    /**
     * 按照从小到大排序
     *
     * @param array
     */
    public static void normalSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                //没有必要对相等的数据进行交换
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void optimizeOneSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        boolean change = false;

        for (int i = 0; i < array.length; i++) {
            change = false;

            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    change = true;
                }
            }

            if (!change) {
                break;
            }
        }
    }

    public static void optimizeTwoSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int endPoint = array.length - 1;
        int tempChangeIndex;

        while (endPoint > 0) {

            tempChangeIndex = 0;

            for (int i = 1; i <= endPoint; i++) {
                if (array[i - 1] > array[i]) {
                    int temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;

                    tempChangeIndex = i - 1;
                }
            }

            endPoint = tempChangeIndex;
        }
    }
}
