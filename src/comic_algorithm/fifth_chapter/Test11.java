package comic_algorithm.fifth_chapter;

/**
 * 总共有十名工人，5座金矿，
 * 200kg/3人、
 * 300kg/4人、
 * 350kg/3人、
 * 400kg/5人、
 * 500kg/5人
 * 每座金矿要么全挖，要么不挖，
 * 不能派一半人挖取一半的金矿，
 * 要想尽可能挖取最多，求最大收益？
 * <p>
 * 时间复杂度和空间复杂度为O(golds*(persons+1))
 * <p>
 * 在程序中并不需要保存整个表格，无论金矿有多少座，
 * 我们只保存1行的数据即可。
 * 在计算下一行时，要从右向左统计，时间空间复杂度降低至O(n)
 */
public class Test11 {
    public static void main(String[] args) {
        title1();
    }

    private static void title1() {
        int golds = 5;
        int persons = 10;

        //创建人数辅助数组
        int[] p = new int[]{3, 4, 3, 5, 5};
        //创建金矿辅助数组
        int[] gold = new int[]{200, 300, 350, 400, 500};
        //创建辅助数组
        int[][] data = new int[golds][persons + 1];


        int minPersons = 3;

        for (int i = 0; i < minPersons; i++) {
            for (int j = 0; j < golds; j++) {
                data[j][i] = 0;
            }
        }

        //初始化第一行数据
        for (int i = minPersons; i < persons + 1; i++) {
            data[0][i] = 200;
        }

        //依次补充数据填入
        for (int i = minPersons; i < persons + 1; i++) {
            for (int j = 1; j < golds; j++) {
                if (i < p[j]) {
                    data[j][i] = data[j - 1][i];
                    continue;
                }

                data[j][i] = Math.max(data[j - 1][i], data[j - 1][i - p[j]] + gold[j]);
            }
        }

        //最多获取金矿数量
        System.out.printf("==>" + data[golds - 1][persons]);
    }
}
