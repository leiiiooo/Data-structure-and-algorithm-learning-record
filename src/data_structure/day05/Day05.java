package data_structure.day05;

public class Day05 {
    public static void main(String[] args) {
        /*int[] array = new int[]{1, 2, 10, 10, 11, 16, 39, 50, 56};
        int key = 10;
        System.out.println(binarySearch(array, 0, array.length, key));*/

        /*int[] array = new int[]{2, 1, 7, 4, 9, 3, 2, 6, 5, 8};
        quickSort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.println(i);
        }*/

        /*int[] array = new int[]{2, 1, 7, 4, 9, 100, 3, 2, 6, 5, 8};
        mergeSort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.println(i);
        }*/
    }

    private static void mergeSort(int[] data, int left, int right) {
        if (left != right) {
            int middle = (left + right) / 2;
            mergeSort(data, left, middle);
            mergeSort(data, middle + 1, right);
            merge(data, left, middle + 1, right);
        }
    }

    private static void merge(int[] data, int left, int middle, int right) {
        int[] leftArray = new int[middle - left];
        int[] rightArray = new int[right - middle + 1];

        for (int i = left; i < middle; i++) {
            leftArray[i - left] = data[i];
        }

        for (int i = middle; i <= right; i++) {
            rightArray[i - middle] = data[i];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftArray.length && j < rightArray.length) {
            //至少有一个含有相等条件
            if (leftArray[i] <= rightArray[j]) {
                data[k] = leftArray[i];
                k++;
                i++;
            } else if (rightArray[j] < leftArray[i]) {
                data[k] = rightArray[j];
                k++;
                j++;
            }
        }

        while (i < leftArray.length) {
            data[k] = leftArray[i];
            k++;
            i++;
        }

        while (j < rightArray.length) {
            data[k] = rightArray[j];
            k++;
            j++;
        }
    }

    private static void quickSort(int[] data, int begin, int end) {
        if (begin >= end) {
            return;
        }

        int low = begin;
        int high = end;

        //基准
        int x = data[low];
        //查询方向
        boolean direction = true;

        L1:
        while (low < high) {
            //低处值为基准，所以第一次从高出开始搞起来
            if (direction) {
                for (int i = high; i > low; i--) {
                    //这里的等号要有
                    if (data[i] <= x) {
                        data[low++] = data[i];
                        high = i;
                        //换方向
                        direction = false;
                        continue L1;
                    }
                }

                high = low;
            } else {
                for (int i = low; i < high; i++) {
                    //这里的等号要有
                    if (data[i] >= x) {
                        data[high--] = data[i];
                        low = i;
                        direction = true;
                        continue L1;
                    }
                }
                low = high;
            }
        }

        data[low] = x;

        quickSort(data, begin, low - 1);
        quickSort(data, low + 1, end);
    }

    /**
     * 二分查找设计成->左闭右开
     *
     * @param data
     * @param startIndex
     * @param toIndex
     * @param key
     */
    private static int binarySearch(int[] data, int startIndex, int toIndex, int key) {
        int from = startIndex;
        int end = toIndex - 1;

        while (from <= end) {
            int middle = (from + end) / 2;
            int middleValue = data[middle];
            if (key > middleValue) {
                from = middle + 1;
            } else if (key < middleValue) {
                end = middle - 1;
            } else {
                return middle;
            }
        }

        return -(from + 1);

    }
}
