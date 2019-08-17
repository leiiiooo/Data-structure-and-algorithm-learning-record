package data_structure.day09;

import java.util.LinkedList;

public class AVLTreeDemo<E extends Comparable<E>> {
    public Node<E> root;
    public int size = 0;
    private static final int LH = 1;
    private static final int RH = -1;
    private static final int EH = 0;

    /**
     * 左旋转
     */
    public void leftRotate(Node<E> node) {
        if (node == null) {
            return;
        }
        Node<E> parent = node.parent;
        Node<E> rightChild = node.rightChild;
        Node<E> rightLeftChild = rightChild.leftChild;

        if (parent == null) {
            root = rightChild;
            rightChild.parent = null;
        } else {
            if (node == parent.leftChild) {
                parent.leftChild = rightChild;
            } else {
                parent.rightChild = rightChild;
            }

            rightChild.parent = parent;
        }

        rightChild.leftChild = node;
        node.parent = rightChild;

        node.rightChild = rightLeftChild;
        if (rightLeftChild != null) {
            rightLeftChild.parent = node;
        }
    }

    /**
     * 右旋转
     */
    public void rightRotate(Node<E> node) {
        if (node == null) {
            return;
        }
        Node<E> parent = node.parent;
        Node<E> leftChild = node.leftChild;
        Node<E> leftRightChild = leftChild.rightChild;

        if (parent == null) {
            root = leftChild;
            leftChild.parent = null;
        } else {
            if (node == parent.leftChild) {
                parent.leftChild = leftChild;
            } else {
                parent.rightChild = leftChild;
            }

            leftChild.parent = parent;
        }

        leftChild.rightChild = node;
        node.parent = leftChild;

        node.leftChild = leftRightChild;
        if (leftRightChild != null) {
            leftRightChild.parent = node;
        }
    }

    /**
     * 左平衡:LRE
     */
    public void leftBalance(Node<E> t) {
        Node<E> tl = t.leftChild;
        switch (tl.balance) {
            case LH:
                rightRotate(t);
                t.balance = EH;
                tl.balance = EH;
                break;
            case RH:
                Node<E> tlr = tl.rightChild;
                switch (tlr.balance) {
                    case LH:
                        t.balance = RH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                    case RH:
                        t.balance = EH;
                        tl.balance = LH;
                        tlr.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                    default:
                        break;
                }

                leftRotate(tl);
                rightRotate(t);
                break;
            default:
                break;
        }
    }

    /**
     * 右平衡:RLE
     */
    public void rightBalance(Node<E> t) {
        Node<E> tr = t.rightChild;
        switch (tr.balance) {
            case RH:
                leftRotate(t);
                t.balance = EH;
                tr.balance = EH;
                break;
            case LH:
                Node<E> trl = tr.leftChild;
                switch (trl.balance) {
                    case LH:
                        t.balance = EH;
                        tr.balance = RH;
                        trl.balance = EH;
                        break;
                    case RH:
                        t.balance = LH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                    default:
                        break;
                }

                rightRotate(tr);
                leftRotate(t);
                break;
            default:
                break;
        }
    }

    /**
     * 插入
     */
    public boolean inserElement(E element) {
        Node<E> node = this.root;
        if (node == null) {
            root = new Node<>(element, null);
            size = 1;
            root.balance = 0;
            return true;
        } else {
            Node<E> parent;
            int cmp;
            Comparable<? super E> e = element;
            do {
                parent = node;
                cmp = e.compareTo(node.element);
                if (cmp < 0) {
                    node = node.leftChild;
                } else if (cmp > 0) {
                    node = node.rightChild;
                } else {
                    return false;
                }
            } while (node != null);

            Node<E> insertNode = new Node<>(element, parent);
            //插入节点
            if (cmp < 0) {
                parent.leftChild = insertNode;
            } else {
                parent.rightChild = insertNode;
            }

            //平衡调整父节点
            while (parent != null) {
                cmp = e.compareTo(parent.element);
                if (cmp < 0) {
                    parent.balance++;
                } else {
                    parent.balance--;
                }
                if (parent.balance == 0) {
                    //已经是平衡的
                    break;
                }
                if (Math.abs(parent.balance) == 2) {
                    fixBalance(parent);
                    //修复完成之后直接退出循环
                    break;
                } else {
                    parent = parent.parent;
                }
            }
        }

        size++;
        return true;
    }

    /**
     * 调整
     */
    private void fixBalance(Node<E> parent) {
        if (parent.balance == 2) {
            leftBalance(parent);
        }

        if (parent.balance == -2) {
            rightBalance(parent);
        }
    }

    /**
     * 遍历打印
     */
    public void traverse(Node node) {
        if (node == null) {
            return;
        }

        LinkedList<Node> list = new LinkedList<>();
        list.offer(node);
        while (!list.isEmpty()) {
            Node pop = list.pop();
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
     * 节点
     */
    public class Node<E extends Comparable<E>> {
        public E element;
        public int balance;
        Node<E> leftChild;
        Node<E> rightChild;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }
}
