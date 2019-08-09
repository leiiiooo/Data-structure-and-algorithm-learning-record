package data_structure.day04;

public class Day04TreeDemo {
    public static void main(String[] args) {
        DemoTree demoTree = new DemoTree("A");
        DemoTree.Node<String> tree = demoTree.createTree();
//        demoTree.preOrderTraverse(tree);
//        demoTree.midOrderTraverse(tree);
//        demoTree.postOrderTraverse(tree);
//        demoTree.preOrderTraverseRecursive(tree);
        demoTree.midOrderTraverseRecursive(tree);


    }


}
