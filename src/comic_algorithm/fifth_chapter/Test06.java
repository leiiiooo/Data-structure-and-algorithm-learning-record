package comic_algorithm.fifth_chapter;

public class Test06 {
    public static void main(String[] args) {
        System.out.println(title1(new int[]{100, 3, 1, 2, 7, 10, 12}));
    }

    /**
     * 无序数组排序后的最大相邻差
     * <p>
     * 使用桶排序思想:
     * 根据给出的数组长度，定义n个桶，区间为(max-min)/(n-1)
     * 将数据一次放入桶中，计算当前桶的最大值和右侧不为空的桶的最小值
     * 左闭右开区间
     * <p>
     * 时间复杂度为：O(n)
     */
    private static int title1(int[] data) {
        int max = data[0];
        int min = data[0];

        int result = 0;

        //find min and max value
        for (int i = 0; i < data.length; i++) {
            if (data[i] < min) {
                min = data[i];
            } else if (data[i] > max) {
                max = data[i];
            }
        }

        if (min == max) {
            return result;
        }

        Bucket[] buckets = new Bucket[data.length];
        //初始化桶数组
        for (int i = 0; i < data.length; i++) {
            buckets[i] = new Bucket();
        }

        int bucketInterval = (max - min) / (data.length - 1);
        for (int i = 0; i < data.length; i++) {
            int bucketIndex = (data[i] - min) / bucketInterval;
            Bucket bucket = buckets[bucketIndex];
            if (bucket.maxValue == null || data[i] > bucket.maxValue) {
                bucket.maxValue = data[i];
            }

            if (bucket.minValue == null || data[i] < bucket.minValue) {
                bucket.minValue = data[i];
            }
        }
        //遍历所有的桶，计算最大差值
        Integer tempMaxValue = null;
        for (int i = 0; i < buckets.length; i++) {
            Bucket bucket = buckets[i];
            if (bucket.maxValue != null
                    && bucket.minValue != null) {
                if (tempMaxValue == null) {
                    tempMaxValue = bucket.maxValue;
                    continue;
                }

                if (bucket.minValue - tempMaxValue > result) {
                    result = bucket.minValue - tempMaxValue;
                }

                tempMaxValue = bucket.maxValue;
            }
        }

        return result;
    }

    /**
     * 定义指定的数据模型
     */
    private static class Bucket {
        public Integer maxValue;
        public Integer minValue;
    }
}
