package comic_algorithm.fifth_chapter;

public class Test05 {
    public static void main(String[] args) {
        System.out.println(title1(7));
    }

    /**
     * 判断一个数是不是2的整数次幂
     * <p>
     * 思路：2的整数次幂，使用二进制表示为
     * 10000000 减去1之后的结果为 1111111
     * 二者相相与结果为0
     * <p>
     * 时间复杂度为：O(1)
     */
    private static final boolean title1(int num) {
        return (num & (num - 1)) == 0;
    }
}
