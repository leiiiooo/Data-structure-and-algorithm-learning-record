package comic_algorithm.fifth_chapter;

public class Test08 {
    public static void main(String[] args) {
        int[] number = {1, 2, 3, 4, 5};
        for (int i : title1(number)) {
            System.out.println(i);
        }
    }

    /**
     * 寻找全排列的下一个数
     * <p>
     * 思路：
     * 1、从结尾开始向前查找到最长的逆序数据段
     * 2、向前取一个索引和当前数据段中，刚好大于它的值发生交换
     * 3、将逆序段中的数据对称交换位置，使其变成顺序
     * <p>
     * 又名，字典序算法
     * <p>
     * 时间复杂度:O(n)
     */
    private static int[] title1(int[] number) {
        int criticalIndex = 0;
        for (int i = number.length - 1; i > 0; i--) {
            if (number[i] > number[i - 1]) {
                criticalIndex = i;
                break;
            }
        }

        //当前传入数组，已经是最大的组合排列
        if (criticalIndex == 0) {
            return number;
        }

        int exchangeIndex = criticalIndex - 1;

        for (int i = number.length - 1; i >= criticalIndex; i--) {
            if (number[i] > number[exchangeIndex]) {
                //交换两个值
                int temp = number[exchangeIndex];
                number[exchangeIndex] = number[i];
                number[i] = temp;
            }
        }

        //reverse
        for (int i = criticalIndex, j = number.length - 1; i < j; i++, j--) {
            int temp = number[i];
            number[i] = number[j];
            number[j] = temp;
        }

        return number;
    }
}
