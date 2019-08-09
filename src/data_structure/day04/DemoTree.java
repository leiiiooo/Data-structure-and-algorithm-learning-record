package data_structure.day04;

import java.util.Stack;

public class DemoTree {

    private final Node<String> root;

    public DemoTree(String root) {
        this.root = new Node<>(root, null, null);
    }

    public Node<String> createTree() {
        Node<String> b = new Node<>("B", null, null);
        Node<String> c = new Node<>("C", null, null);
        Node<String> d = new Node<>("D", null, null);
        Node<String> e = new Node<>("E", null, null);
        Node<String> f = new Node<>("F", null, null);
        Node<String> g = new Node<>("G", null, null);
        Node<String> h = new Node<>("H", null, null);
        Node<String> i = new Node<>("I", null, null);
        Node<String> j = new Node<>("J", null, null);

        root.leftChild = b;
        root.rightChild = c;

        b.leftChild = d;
        b.rightChild = e;

        c.leftChild = f;
        c.rightChild = g;

        d.leftChild = h;
        d.rightChild = i;

        e.leftChild = j;

        return root;
    }

    /**
     * Node{data=H}
     * Node{data=I}
     * Node{data=D}
     * Node{data=J}
     * Node{data=E}
     * Node{data=B}
     * Node{data=F}
     * Node{data=G}
     * Node{data=C}
     * Node{data=A}
     *
     * @param root
     */
    public void postOrderTraverse(Node<String> root) {
        if (root == null) {
            return;
        }

        postOrderTraverse(root.leftChild);
        postOrderTraverse(root.rightChild);
        System.out.println(root.toString());
    }

    /**
     * Node{data=A}
     * Node{data=B}
     * Node{data=D}
     * Node{data=H}
     * Node{data=I}
     * Node{data=E}
     * Node{data=J}
     * Node{data=C}
     * Node{data=F}
     * Node{data=G}
     *
     * @param root
     */
    public void preOrderTraverse(Node<String> root) {
        if (root == null) {
            return;
        }

        System.out.println(root.toString());
        preOrderTraverse(root.leftChild);
        preOrderTraverse(root.rightChild);
    }

    /**
     * Node{data=H}
     * Node{data=D}
     * Node{data=I}
     * Node{data=B}
     * Node{data=J}
     * Node{data=E}
     * Node{data=A}
     * Node{data=F}
     * Node{data=C}
     * Node{data=G}
     *
     * @param root
     */
    public void midOrderTraverse(Node<String> root) {
        if (root == null) {
            return;
        }

        midOrderTraverse(root.leftChild);
        System.out.println(root.toString());
        midOrderTraverse(root.rightChild);
    }

    // TODO: 2019/8/9
    public void postOrderTraverseRecursive(Node<String> root) {

    }

    /**
     * Node{data=A}
     * Node{data=B}
     * Node{data=D}
     * Node{data=H}
     * Node{data=I}
     * Node{data=E}
     * Node{data=J}
     * Node{data=C}
     * Node{data=F}
     * Node{data=G}
     *
     * @param root
     */
    public void preOrderTraverseRecursive(Node<String> root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node<String> point = root;
        while (point != null || !stack.isEmpty()) {
            while (point != null) {
                System.out.println(point.toString());
                stack.push(point);
                point = point.leftChild;
            }

            if (!stack.isEmpty()) {
                point = stack.pop();
                point = point.rightChild;
            }
        }
    }

    /**
     * Node{data=H}
     * Node{data=D}
     * Node{data=I}
     * Node{data=B}
     * Node{data=J}
     * Node{data=E}
     * Node{data=A}
     * Node{data=F}
     * Node{data=C}
     * Node{data=G}
     *
     * @param root
     */
    public void midOrderTraverseRecursive(Node<String> root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node<String> point = root;

        while (point != null || !stack.isEmpty()) {
            //压入栈
            while (point != null) {
                stack.push(point);
                point = point.leftChild;
            }

            if (!stack.isEmpty()) {
                point = stack.pop();
                System.out.println(point.toString());
                point = point.rightChild;
            }
        }
    }

    class Node<E> {
        public E data;
        public Node<E> leftChild;
        public Node<E> rightChild;

        public Node(E data, Node<E> leftChild, Node<E> rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }
}
