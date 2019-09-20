package comic_algorithm.fifth_chapter;

public class Test02 {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = node6;

//        System.out.println(title1(node1));
//        System.out.println(title2(node1));
//        System.out.println(title3(node3).toString());
    }

    /**
     * 如何判断一个单向链表是否有环
     * <p>
     * 思路：两个指针同时从头节点出发，当指针出现null停止，
     * 没有追上说明没有环
     * <p>
     * 时间：O(n)
     * 空间：O(1) 除了两个指针以外没有使用任何额外的存储空间
     */
    private static boolean title1(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (null != p1 && null != p2 && null != p2.next) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return true;
            }
        }

        return false;
    }

    /**
     * 如果链表有环，求环的长度
     * <p>
     * 思路：从第一次相遇开始，直到下一次追上(因为二者相差一)
     * <p>
     * 本解法前提一定有环
     */
    private static int title2(Node head) {
        Node p1 = head;
        Node p2 = head;

        boolean hasMeet = false;

        int steps = 0;

        while (null != p1 && null != p2.next && null != p2.next.next) {
            p1 = p1.next;
            p2 = p2.next.next;

            if (hasMeet) {
                steps++;
            }

            if (p1 == p2) {
                if (hasMeet) {
                    break;
                } else {
                    hasMeet = true;
                }
            }
        }

        return steps;
    }

    /**
     * 如果链表有环，求出入环节点
     * 假设从链表开始节点到切入点的距离是D
     * 从切入点到第一次相遇的点距离是S1
     * 从第一次相遇点到切入点的距离是S2
     * <p>
     * 则以下等式成立
     * <p>
     * 2(D+S1) = n(S1+S2) + D
     * ⬇
     * D = S2+(n-1)(S1+S2)
     * <p>
     * tip: 2是因为，假设p2的速度为2，p1的速度为1
     * <p>
     * 所以实际差值为(n-1)倍的圆环长度
     * <p>
     * 前提是一定有环
     */
    private static Node title3(Node head) {
        Node p1 = head;
        Node p2 = head;

        Node meet = null;

        while (null != p1 && null != p2 && null != p2.next) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                meet = p1;
                break;
            }
        }

        p1 = head;
        p2 = meet;

        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    private static class Node {
        int value;

        public Node next;

        private Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
