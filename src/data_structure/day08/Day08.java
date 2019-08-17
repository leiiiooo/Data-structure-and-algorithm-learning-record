package data_structure.day08;

import java.util.ArrayList;

public class Day08 {
    public static void main(String[] args) {

        ArrayList<HuffmanTreeDemo.TreeNode<String>> list = new ArrayList<>();
        HuffmanTreeDemo.TreeNode<String> node = new HuffmanTreeDemo.TreeNode("good", 50);
        list.add(node);
        list.add(new HuffmanTreeDemo.TreeNode("morning", 10));
        HuffmanTreeDemo.TreeNode<String> node2 = new HuffmanTreeDemo.TreeNode("afternoon", 20);
        list.add(node2);
        list.add(new HuffmanTreeDemo.TreeNode("hell", 110));
        list.add(new HuffmanTreeDemo.TreeNode("hi", 200));
        HuffmanTreeDemo tree = new HuffmanTreeDemo();
        tree.createHuffmanTree(list);
        tree.traverseHuffmanTree(tree.root);
        tree.getCode(node2);
    }
}
