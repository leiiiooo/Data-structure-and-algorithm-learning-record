package comic_algorithm.fifth_chapter;


public class Test12 {
    public static void main(String[] args) {
        titleThree();
    }

    /**
     * 在一个无序的数组里，有99个不重复的正整数，范围是1～100，唯独缺少一个
     */
    private static void titleOne() {
        /**
         * 思路一：使用Hash表
         *
         * 空间:O(n)
         * 时间:O(n)
         */
        /**
         * 思路二：从小到大进行排序
         * 遍历，发现不连续的就是缺失的元素
         *
         * 空间:O(1)
         * 时间:O(nlog(n))
         */
        /**
         * 思路三：计算出1～100的和，减去当前已经有了的值，剩下的就是缺少的
         *
         * 空间:O(1)
         * 时间:O(n)
         */
    }

    /**
     * 一个无序数组里有若干个正整数，范围是1～100，其中99个整数都出现了偶数次，
     * 只有1个整数出现了奇数次，如何找到这个出现奇数次的整数？
     */
    private static void titleTwo() {
        /**
         * 思路：依次对数组中的数据进行异或处理，
         * 剩下的数字就是出现奇数次的数字
         *
         * 空间：O(1)
         * 时间：O(n)
         */
    }

    /**
     * 假设一个无序数组里有若干个正整数，范围是1～100，其中有98个整数出现了偶数次，
     * 只有2个整数出现了奇数次，如何找到这2个出现奇数次的整数？
     */
    private static void titleThree() {
        int[] titleArray = {4, 1, 2, 2, 5, 1, 4, 3};
        /**
         * 思路：将所有数字进行异或，最终的值肯定是一个非0的结果，
         * 找到任意一位为1的二进制位，以此为分界将原数据分成两组，
         * 分别异或得出最终结果
         *
         * 时间：O(n)
         * 空间：O(1)
         */
        int[] ints = solutionForTitle3(titleArray);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }

    private static int[] solutionForTitle3(int array[]) {
        int[] result = new int[2];
        int temp = 0;
        for (int item : array) {
            temp = temp ^ item;
        }

        if (temp == 0) {
            return new int[2];
        }

        int bitIndex = 1;

        while (0 == (temp & bitIndex)) {
            bitIndex = bitIndex << 1;
        }

        for (int value : array) {
            if (0 == (value & bitIndex)) {
                result[0] = value ^ result[0];
            } else {
                result[1] = value ^ result[1];
            }
        }

        return result;
    }
}
