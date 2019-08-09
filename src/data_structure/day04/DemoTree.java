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

    /**
     * 要保证根结点在左孩子和右孩子访问之后才能访问，因此对于任一结点P，先将其入栈。
     * 如果P不存在左孩子和右孩子，则可以直接访问它；
     * 或者P存在左孩子或者右孩子，但是其左孩子和右孩子都已被访问过了，则同样可以直接访问该结点。
     * 若非上述两种情况，则将P的右孩子和左孩子依次入栈，这样就保证了每次取栈顶元素的时候，
     * 左孩子在右孩子前面被访问，左孩子和右孩子都在根结点前面被访问。
     * <p>
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
    public void postOrderTraverseRecursive(Node<String> root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node<String> point;
        Node<String> last = null;
        stack.push(root);
        while (!stack.isEmpty()) {
            point = stack.peek();
            if ((point.leftChild == null && point.rightChild == null)
                    || (last != null && (last == point.leftChild || last == point.rightChild))) {
                System.out.println(point.toString());
                stack.pop();
                last = point;
            } else {
                if (point.rightChild != null) {
                    stack.push(point.rightChild);
                }

                if (point.leftChild != null) {
                    stack.push(point.leftChild);
                }
            }
        }
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
     * <p>
     * 根据前序遍历访问的顺序，优先访问根结点，然后再分别访问左孩子和右孩子。
     * 即对于任一结点，其可看做是根结点，因此可以直接访问，访问完之后，若其左孩子不为空，
     * 按相同规则访问它的左子树；当访问其左子树时，再访问它的右子树。因此其处理过程如下：
     * <p>
     * 对于任一结点P：
     * <p>
     * 1)访问结点P，并将结点P入栈;
     * <p>
     * 2)判断结点P的左孩子是否为空，若为空，则取栈顶结点并进行出栈操作，并将栈顶结点的右孩子置为当前的结点P，循环至1);若不为空，则将P的左孩子置为当前的结点P;
     * <p>
     * 3)直到P为NULL并且栈为空，则遍历结束。
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
     * 根据中序遍历的顺序，对于任一结点，优先访问其左孩子，而左孩子结点又可以看做一根结点，
     * 然后继续访问其左孩子结点，直到遇到左孩子结点为空的结点才进行访问，
     * 然后按相同的规则访问其右子树。因此其处理过程如下：
     * <p>
     * 对于任一结点P，
     * <p>
     * 1)若其左孩子不为空，则将P入栈并将P的左孩子置为当前的P，然后对当前结点P再进行相同的处理；
     * <p>
     * 2)若其左孩子为空，则取栈顶元素并进行出栈操作，访问该栈顶结点，然后将当前的P置为栈顶结点的右孩子；
     * <p>
     * 3)直到P为NULL并且栈为空则遍历结束
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
