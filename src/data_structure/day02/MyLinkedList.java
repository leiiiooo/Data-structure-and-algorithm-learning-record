package data_structure.day02;

import java.util.Collection;

public class MyLinkedList<E> {
    /**
     * 头节点
     */
    private Node<E> first;

    /**
     * 尾节点
     */
    private Node<E> last;

    /**
     * 大小
     */
    public int size;

    /**
     * 添加数据
     *
     * @param e
     */
    public void add(E e) {
        linkLast(e);
    }

    /**
     * 在尾部添加集合数据
     *
     * @param collection
     */
    public void addAll(Collection<E> collection) {
        addAll(size, collection);
    }

    /**
     * 在指定位置添加一个集合数据
     *
     * @param size
     * @param collection
     */
    public void addAll(int index, Collection<E> collection) {
        if (index < 0 || index > size) {
            return;
        }

        if (collection == null) {
            return;
        }

        Object[] objects = collection.toArray();

        if (objects.length == 0) {
            return;
        }

        int newLength = objects.length;

        Node<E> middleNode, lastestNode;

        if (index == size) {
            middleNode = null;
            lastestNode = last;
        } else {
            middleNode = node(index);
            lastestNode = middleNode.pre;
        }

        for (Object object : objects) {
            Node<E> newNode = new Node<>(lastestNode, ((E) object), null);
            if (lastestNode == null) {
                first = newNode;
            } else {
                lastestNode.next = newNode;
            }

            lastestNode = newNode;
        }

        if (middleNode == null) {
            last = lastestNode;
        } else {
            lastestNode.next = middleNode;
            middleNode.pre = lastestNode;
        }

        size += newLength;
    }

    /**
     * 添加到尾部
     *
     * @param e
     */
    private void linkLast(E e) {
        Node<E> currentLast = last;
        Node<E> newNode = new Node<>(last, e, null);
        last = newNode;

        if (currentLast == null) {
            //没有节点
            first = newNode;
        } else {
            currentLast.next = newNode;
        }

        size++;
    }

    /**
     * 获取指定位置元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        //边界判断
        if (index < 0 || index > size) {
            return null;
        }
        //根据节点获取里面元素
        return node(index).item;
    }

    private Node<E> node(int index) {
        Node<E> node;

        if (index < (size >> 1)) {
            //first 开始
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            //last 开始
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.pre;
            }
        }


        return node;
    }

    /**
     * 指定位置添加元素
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            return;
        }

        if (index == size) {
            linkLast(e);
        } else {
            Node<E> node = node(index);
            Node<E> pre = node.pre;

            Node<E> newNode = new Node<>(pre, e, node);

            if (pre == null) {
                first = newNode;
                node.pre = newNode;
            } else {
                pre.next = newNode;
                node.pre = newNode;
            }

            size++;
        }
    }

    /**
     * 移除指定索引
     *
     * @param index
     */
    public void remove(int index) {
        if (index < 0 || index > size - 1) {
            return;
        }

        unLinked(node(index));
    }

    private void unLinked(Node<E> node) {
        Node<E> pre = node.pre;
        Node<E> next = node.next;

        if (pre == null) {
            first = next;
        } else {
            pre.next = node.next;
        }

        if (next == null) {
            last = pre;
        } else {
            next.pre = node.pre;
        }

        size--;
    }

    class Node<E> {
        public E item;
        public Node<E> next;
        public Node<E> pre;

        public Node(Node<E> pre, E item, Node<E> next) {
            this.pre = pre;
            this.item = item;
            this.next = next;
        }
    }
}
