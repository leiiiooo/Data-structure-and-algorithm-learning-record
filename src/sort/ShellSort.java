package sort;

/**
 * 希尔排序(非稳定排序算法)
 * <p>
 * 直接插入排序，它的效率在数组本身就是基本有序以及元素个数较少时，它的效率是很高的。
 * 但问题就是，这两个条件本身就很苛刻。如何让程序争取实现这俩条件呢？
 * <p>
 * 答案就是讲原本有大量元素的数组进行分组，分隔成若干子数组，
 * 这样每个子数组的待排序的元素个数就比较少了，
 * 然后在子数组内分别进行「直接插入排序」，
 * 当整个数组基本有序时，再对全体元素进行一次「直接插入排序」。
 * <p>
 * 因此我们在分割子数组时，需要采取跳跃分割的策略：将相距某个增量的记录组成一个子数组，
 * 这样才能保证在子数组内分别进行直接插入排序后的得到的结果是基本有序，而不是局部有序。
 */
public class ShellSort {
    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int anchor;
        int stepLength = array.length / 2;

        while (stepLength > 0) {
            for (int i = stepLength; i < array.length; i++) {
                anchor = array[i];
                int j = (i - stepLength);

                while (j >= 0 && array[j] > anchor) {
                    array[j + stepLength] = array[j];
                    j = j - stepLength;
                }

                array[j + stepLength] = anchor;
            }

            stepLength = stepLength / 2;
        }
    }
}
