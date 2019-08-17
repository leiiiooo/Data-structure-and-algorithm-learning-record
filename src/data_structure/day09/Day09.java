package data_structure.day09;

public class Day09 {
    public static void main(String[] args) {
        Integer[] nums = {5, 8, 2, 0, 1, -2};
        AVLTreeDemo<Integer> tree = new AVLTreeDemo<>();
        for (Integer num : nums) {
            tree.inserElement(num);
        }
        tree.traverse(tree.root);
    }
}