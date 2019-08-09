package data_structure.day02;

import data_structure.common.model.Mahjong;

import java.util.LinkedList;

public class day02 {
    public static void main(String[] args) {
        /*LinkedList<Mahjong> list = new LinkedList<>();
        list.add(new Mahjong(3, 1));
        list.add(new Mahjong(2, 3));
        list.add(new Mahjong(3, 7));
        list.add(new Mahjong(1, 1));
        list.add(new Mahjong(3, 8));
        list.add(new Mahjong(2, 2));
        list.add(new Mahjong(3, 2));
        list.add(new Mahjong(1, 3));
        list.add(new Mahjong(3, 9));
        System.out.println(list);
        radixSort(list);
        System.out.println(list);*/
        testMyLinkedList();
    }

    private static void testMyLinkedList() {
        LinkedList<Integer> extra = new LinkedList<>();
        extra.add(99);
        extra.add(99);
        extra.add(99);
        extra.add(99);
        extra.add(99);

        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.add(4);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);// 43 66 12
        linkedList.add(66);
        linkedList.add(0, 88);
        linkedList.add(1, 87);
        linkedList.remove(0);
        linkedList.remove(1);
        linkedList.remove(4);
        linkedList.addAll(1, extra);
        // 0:66 1:4 2:3 3:1 4:2
        // linkedList.remove(0);

        for (int i = 0; i < linkedList.size; i++) {
            System.out.print(i + ":" + linkedList.get(i) + "    ");
        }
    }

    /**
     * 麻将排序:
     * <p>
     * 使用链表+基数排序
     *
     * @param list
     */
    private static void radixSort(LinkedList<Mahjong> list) {
        //9个桶够了
        LinkedList[] rankLinkedLists = new LinkedList[9];
        for (int i = 0; i < rankLinkedLists.length; i++) {
            rankLinkedLists[i] = new LinkedList();
        }
        //点数
        while (list.size() > 0) {
            Mahjong mahjong = list.remove();
            rankLinkedLists[mahjong.rank - 1].add(mahjong);
        }

        for (int i = 0; i < rankLinkedLists.length; i++) {
            list.addAll(rankLinkedLists[i]);
        }
        //花色(第二个计数纬度)
        //3个桶
        LinkedList[] suitLinkedLists = new LinkedList[3];
        for (int i = 0; i < suitLinkedLists.length; i++) {
            suitLinkedLists[i] = new LinkedList();
        }

        while (list.size() > 0) {
            Mahjong mahjong = list.remove();
            suitLinkedLists[mahjong.suit - 1].add(mahjong);
        }
        for (int i = 0; i < suitLinkedLists.length; i++) {
            list.addAll(suitLinkedLists[i]);
        }
    }
}
