package comic_algorithm.fifth_chapter;

/**
 * 给出两个很大的整数，要求实现程序求出两个整数之和
 * <p>
 * 使用数组来模拟竖式计算，数组的长度比给出的数字长一位，
 * 可以倒序存储这样更符合从左到右的计算逻辑
 */
public class Test10 {
    public static void main(String[] args) {
        System.out.println("Test10-->method1=>" + method1("666", "777"));
    }

    /**
     * 创造两个足够大的数组，分别存储对应的值，
     * 同时还需要创建一个数组用来存储结果
     * <p>
     * 整体时间复杂度为O(n)
     *
     * @return
     */
    private static String method1(String strNum1, String strNum2) {
        int maxLength = Math.max(strNum1.length(), strNum2.length());
        int[] arrStrNum1 = new int[maxLength + 1];
        int[] arrStrNum2 = new int[maxLength + 1];
        int[] arrResult = new int[maxLength + 1];

        for (int i = strNum1.length() - 1; i >= 0; i--) {
            char c = strNum1.charAt(i);
            arrStrNum1[strNum1.length() - 1 - i] = c - '0';
        }

        for (int i = strNum2.length() - 1; i >= 0; i--) {
            char c = strNum2.charAt(i);
            arrStrNum2[strNum2.length() - 1 - i] = c - '0';
        }

        int decimal = 0;
        for (int i = 0; i < arrStrNum1.length; i++) {
            int i1 = arrStrNum1[i];
            int i2 = arrStrNum2[i];
            if ((i1 + i2 + decimal) > 10) {
                arrResult[i] = i1 + i2 + decimal - 10;
                decimal = 1;
            } else {
                arrResult[i] = i1 + i2 + decimal;
                decimal = 0;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        boolean existNonZeroValue = false;
        //倒序输出数组中元素
        for (int i = arrResult.length - 1; i >= 0; i--) {
            int result = arrResult[i];
            if (result == 0 && !existNonZeroValue) {
                continue;
            }

            if (!existNonZeroValue) {
                existNonZeroValue = true;
            }

            stringBuilder.append(result);
        }

        return stringBuilder.toString();
    }

    /**
     * 优化算法
     *
     * @param strNum1
     * @param strNum2
     * @return
     */
    private static String method2(String strNum1, String strNum2) {

    }
}

