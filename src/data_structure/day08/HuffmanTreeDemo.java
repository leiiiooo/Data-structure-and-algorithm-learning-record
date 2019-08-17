package data_structure.day08;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class HuffmanTreeDemo {
    public TreeNode<String> root;

    /**
     * @param list
     * @return
     */
    public TreeNode<String> createHuffmanTree(List<TreeNode<String>> list) {
        if (list == null || list.isEmpty()) {
            root = null;
            return null;
        }

        if (list.size() == 1) {
            root = list.get(0);
        }

        while (list.size() > 1) {
            Collections.sort(list);
            TreeNode<String> left = list.get(list.size() - 1);
            TreeNode<String> right = list.get(list.size() - 2);
            TreeNode<String> parent = new TreeNode<>("p", left.weight + right.weight);
            parent.leftChild = left;
            left.parent = parent;
            parent.rightChild = right;
            right.parent = parent;
            list.remove(left);
            list.remove(right);
            list.add(parent);
        }

        root = list.get(0);
        return root;
    }

    /**
     * 遍历Huffman树
     *
     * @param treeNode
     */
    public void traverseHuffmanTree(TreeNode<String> treeNode) {
        if (treeNode == null) {
            return;
        }

        LinkedList<TreeNode<String>> list = new LinkedList<>();
        list.offer(treeNode);
        while (!list.isEmpty()) {
            TreeNode<String> pop = list.pop();
            System.out.println(pop.toString());
            if (pop.leftChild != null) {
                list.offer(pop.leftChild);
            }

            if (pop.rightChild != null) {
                list.offer(pop.rightChild);
            }
        }
    }

    /**
     * 获取当前节点编码
     *
     * @param node
     */
    public void getCode(TreeNode<String> node) {
        if (node == null) {
            return;
        }

        Stack<String> stack = new Stack<>();
        while (node != null && node.parent != null) {
            if (node.parent.leftChild == node) {
                stack.push("0");
            } else if (node.parent.rightChild == node) {
                stack.push("1");
            }

            node = node.parent;
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static class TreeNode<T> implements Comparable<TreeNode<T>> {
        public T data;
        public TreeNode<T> leftChild;
        public TreeNode<T> rightChild;
        public TreeNode<T> parent;
        public int weight;

        public TreeNode(T data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public int compareTo(TreeNode<T> o) {
            //大数字放在前面，方便数据存取
            if (this.weight > o.weight) {
                return -1;
            } else if (this.weight < o.weight) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    '}';
        }
    }
}
