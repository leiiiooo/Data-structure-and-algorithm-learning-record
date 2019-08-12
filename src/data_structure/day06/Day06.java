package data_structure.day06;

public class Day06 {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        int[] array = new int[]{5, 2, 7, 3, 4, 1, 6};

        for (int i : array) {
            tree.put(i);
        }
        tree.delNode(tree.searchTreeNode(7));
        tree.delNode(tree.searchTreeNode(1));
        tree.delNode(tree.searchTreeNode(4));
        tree.delNode(tree.searchTreeNode(2));
        tree.delNode(tree.searchTreeNode(3));
        tree.delNode(tree.searchTreeNode(5));
        tree.delNode(tree.searchTreeNode(6));
        
        tree.midOrderTraverse(tree.root);
    }
}
