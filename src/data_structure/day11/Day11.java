package data_structure.day11;

import java.util.LinkedList;

/**
 * 通过链表构建二叉树（链表中的数据相当于前序遍历）
 */
public class Day11 {
    public static void main(String[] args) {
        LinkedList<Integer> datas = new LinkedList<>();
        datas.offer(2);
        datas.offer(5);
        datas.offer(6);
        datas.offer(7);
        datas.offer(1);
        datas.offer(9);
        TreeNode treeNode = constructATree(datas);
        //前序递归打印
        preLog(treeNode);
    }

    private static void preLog(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.println(treeNode.toString());
            preLog(treeNode.leftNode);
            preLog(treeNode.rightNode);
        }
    }

    private static TreeNode constructATree(LinkedList<Integer> datas) {
        if (null == datas || datas.isEmpty()) {
            return null;
        }
        TreeNode treeNode = null;
        Integer data = datas.removeFirst();
        if (data != null) {
            treeNode = new TreeNode(data);
            treeNode.leftNode = constructATree(datas);
            treeNode.rightNode = constructATree(datas);
        }

        return treeNode;
    }

    static class TreeNode {
        public TreeNode leftNode;
        public TreeNode rightNode;
        private int data;

        public TreeNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    '}';
        }
    }
}
