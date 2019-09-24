package comic_algorithm.fifth_chapter;

public class Test09 {
    public static void main(String[] args) {
        System.out.println(title1("30200", 5));
    }

    /**
     * 删去k个数字之后的最小值
     * <p>
     * 思路：以遍历数字作为外层循环，以k作为内层循环。
     *
     * <p>
     * 时间复杂度为：O(n)
     */
    private static String title1(String number, int k) {
        int newLength = number.length() - k;
        char[] stack = new char[number.length()];

        int topIndex = 0;

        //根据偏移的规则，向指定数组中写入数据
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);

            if (topIndex != 0 && stack[topIndex - 1] > c && k > 0) {
                topIndex--;
                k--;
            }
            stack[topIndex++] = c;
        }

        int offset = 0;

        //这里还需要添加这一重判断！！！！！
        while (stack[offset] == '0' && offset < newLength) {
            offset++;
        }

        if (offset == newLength) {
            return "0";
        } else {
            return new String(stack, offset, newLength - offset);
        }
    }
}
