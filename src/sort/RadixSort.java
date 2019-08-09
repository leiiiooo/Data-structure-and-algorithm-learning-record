package sort;

/**
 * 基数排序
 * <p>
 * 时间复杂度为O(n)
 * <p>
 * LSD的基數排序適用於位數小的數列，如果位數多的話，
 * 使用MSD的效率會比較好，MSD的方式恰與LSD相反，
 * 是由高位數為基底開始進行分配
 * <p>
 * 基數排序法會使用到「桶子」（bucket），顧名思義，
 * 它是透過鍵值的部份資訊，將要排序的元素分配至某些「桶」中，
 * 藉以達到排序的作用，基數排序法是屬於穩定性的排序，
 * 其時間複雜度為O (nlog(r)m)，其中r為所採取的基數，
 * 而m為堆數，在某些時候，基數排序法的效率高於其它的比較性排序法。
 */
public class RadixSort {
    //正整数
    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (maxValue < array[i]) {
                maxValue = array[i];
            }
        }

        //十进制
        int decimal = 10;

        //临时存储数据数组
        int[][] temp = new int[array.length][array.length];
        int[] bucket = new int[decimal];


        int radix = 1;
        int originIndex = 0;
        while (radix < maxValue) {
            for (int i = 0; i < array.length; i++) {
                int lsd = (array[i] / radix) % decimal;
                //填入具体数据
                temp[lsd][bucket[lsd]] = array[i];
                bucket[lsd]++;
            }

            //将数据会填入数组
            for (int i = 0; i < decimal; i++) {
                if (bucket[i] != 0) {
                    for (int j = 0; j < bucket[i]; j++) {
                        array[originIndex] = temp[i][j];
                        originIndex++;
                    }
                }

                bucket[i] = 0;
            }

            radix = radix * decimal;
            originIndex = 0;
        }
    }
}
