package data_structure.day01;

import data_structure.common.model.Card;

public class day01 {
    public static void main(String[] args) {
//        swap();
//        testSort();
        testCard();
    }

    private static void testCard() {
        Card[] array = new Card[]{
                new Card(3, 2), new Card(2, 9), new Card(1, 7),
                new Card(3, 5), new Card(4, 3)
        };

        for (Card cards : array) {
            System.out.println(cards.toString());
        }

        System.out.println();

        bubbleSort(array);

        for (Card cards : array) {
            System.out.println(cards.toString());
        }

    }

    /**
     * 数量较少推荐使用蛮力法，冒泡或者选择性能较好些
     *
     * @param array
     */
    private static void bubbleSort(Card[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            boolean noSwap = true;
            for (int j = 0; j < i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    Card temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    noSwap = false;
                }
            }

            if (noSwap) {
                break;
            }
        }
    }

    private static void testSort() {
        int[] array = new int[]{3, 2, 5, 8, 1, 9, 4, 6, 7};
        System.out.print("原数组->");
        for (int i : array) {
            System.out.print(i + ",");
        }
        System.out.println();

//        bubbleSort(array);
        selectSort(array);

        for (int i : array) {
            System.out.print(i + ",");
        }
    }

    /**
     * 从小到大
     *
     * @param array
     */
    private static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[index]) {
                    index = j;
                }
            }

            if (index != i) {
                int temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
        }
    }

    /**
     * 从小到大
     *
     * @param array
     */
    private static void bubbleSort(int[] array) {
        for (int j = array.length - 1; j > 0; j--) {
            boolean swapTag = false;
            for (int i = 0; i < j; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;

                    swapTag = true;
                }
            }

            if (!swapTag) {
                break;
            }
        }
    }

    private static void swap() {
        int a = 5;
        int b = 6;

        //第一种方式
        int temp;
        temp = a;
        a = b;
        b = temp;
        System.out.println("a==>" + a + "   b==>" + b);

        int a1 = 5;
        int b1 = 6;
        //第二种方式
        a1 = a1 + b1;
        b1 = a1 - b1;
        a1 = a1 - b1;
        System.out.println("a1==>" + a1 + "   b1==>" + b1);

        int a2 = 5;
        int b2 = 6;
        //第三种方式
        a2 = a2 ^ b2;
        b2 = a2 ^ b2;
        a2 = a2 ^ b2;
        System.out.println("a2==>" + a2 + "   b2==>" + b2);
    }
}
