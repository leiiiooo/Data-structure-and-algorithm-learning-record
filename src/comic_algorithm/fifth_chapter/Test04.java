package comic_algorithm.fifth_chapter;

import java.util.Stack;

public class Test04 {
    public static void main(String[] args) {
        System.out.println("最大公约数为==>" + title1(10, 5));
        System.out.println("最大公约数为==>" + title2(100, 30));
        System.out.println("最大公约数为==>" + title3(100, 20));
    }

    /**
     * 求两个正整数的最大公约数
     * <p>
     * 辗转相除法
     * <p>
     * 两个正整数a、b(a>b)的最大公约数，为a和b余数c，和b
     * 之前的最大公约数
     * <p>
     * 缺点：取模运算的性能较差
     */
    private static int title1(int num1, int num2) {
        int big = Math.max(num1, num2);
        int smmall = num1 + num2 - big;
        int remainder = big % smmall;
        while (0 != remainder) {
            big = smmall;
            smmall = remainder;
            remainder = big % smmall;
        }

        return smmall;
    }

    /**
     * 求两个正整数的最大公约数
     * <p>
     * 更相减损术
     * <p>
     * 当两个数字相差较大的时候，
     * 计算的次数要远大于辗转相除法
     */
    private static int title2(int num1, int num2) {
        int big = Math.max(num1, num2);
        int small = num1 + num2 - big;
        int diff = big - small;

        while (0 != diff) {
            big = Math.max(diff, small);
            small = diff + small - big;
            diff = big - small;
        }

        return small;
    }

    /**
     * 求两个正整数的最大公约数
     * <p>
     * 最优算法
     * <p>
     * 结合更相减损术和移位操作相结合
     * <p>
     * 前提：
     * 当a和b均为偶数时，gcd（a，b）=2×gcd（a/2，b/2）=2×gcd（a＞＞1，b＞＞1）。
     * 当a为偶数，b为奇数时，gcd（a，b）=gcd（a/2，b）=gcd（a＞＞1，b）。
     * 当a为奇数，b为偶数时，gcd（a，b）=gcd（a，b/2）=gcd（a，b＞＞1）。
     * 当a和b均为奇数时，先利用更相减损术运算一次，gcd（a，b）=gcd（b，ab），
     * 此时ab必然是偶数，然后又可以继续进行移位运算。
     */
    private static int title3(int num1, int num2) {
        Stack<Integer> stack = new Stack<>();
        stack.push(num1);
        stack.push(num2);

        int shift = 0;
        int temp = 0;

        while (!stack.isEmpty()) {
            Integer value1 = stack.pop();
            Integer value2 = stack.pop();

            if (value1 == value2) {
                temp = value1;
                break;
            }

            if ((value1 & 1) == 0 && (value2 & 1) == 0) {
                shift++;
                stack.push(value1 >> 1);
                stack.push(value2 >> 1);
            } else if ((value1 & 1) == 1 && (value2 & 1) == 0) {
                stack.push(value1);
                stack.push(value2 >> 1);
            } else if ((value1 & 1) == 0 && (value2 & 1) == 1) {
                stack.push(value1 >> 1);
                stack.push(value2);
            } else {
                int bigger = Math.max(value1, value2);
                stack.push(bigger - (value1 + value2 - bigger));
                stack.push(value1 + value2 - bigger);
            }
        }

        return temp << shift;
    }
}
