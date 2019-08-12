package data_structure.day06;

import java.util.Stack;

/**
 * 删除节点的时候，时刻考虑根节点
 */
public class BinarySearchTree {
    public BinarySearchTreeNode root;

    public BinarySearchTreeNode put(int data) {
        if (root == null) {
            root = new BinarySearchTreeNode(data);
            return root;
        } else {
            BinarySearchTreeNode parent = null;
            BinarySearchTreeNode node = root;
            while (node != null) {
                parent = node;
                if (data < node.data) {
                    node = node.leftChild;
                } else if (data > node.data) {
                    node = node.rightChild;
                } else {
                    return node;
                }
            }

            BinarySearchTreeNode newNode = new BinarySearchTreeNode(data);
            newNode.parent = parent;

            if (data < parent.data) {
                parent.leftChild = newNode;
            } else {
                parent.rightChild = newNode;
            }
            return newNode;
        }
    }

    /**
     * 前提是已经存在
     */
    public void delNode(BinarySearchTreeNode node) {
        if (node == null) {
            return;
        }

        BinarySearchTreeNode parent = node.parent;
        BinarySearchTreeNode leftChild = node.leftChild;
        BinarySearchTreeNode rightChild = node.rightChild;

        if (leftChild == null && rightChild == null) {
            //叶子结点
            if (parent == null) {
                //根节点
                root = null;
            } else {
                if (node == parent.leftChild) {
                    parent.leftChild = null;
                } else if (node == parent.rightChild) {
                    parent.rightChild = null;
                }
            }
        } else if (leftChild != null && rightChild == null) {
            //只有左child
            if (parent == null) {
                root = root.leftChild;
                root.parent = null;
            } else {
                if (node == parent.leftChild) {
                    parent.leftChild = leftChild;
                } else if (node == parent.rightChild) {
                    parent.rightChild = leftChild;
                }

                leftChild.parent = parent;
            }
        } else if (leftChild == null) {
            //只有右child
            if (parent == null) {
                root = root.rightChild;
                root.parent = null;
            } else {
                if (node == parent.leftChild) {
                    parent.leftChild = rightChild;
                } else if (node == parent.rightChild) {
                    parent.rightChild = rightChild;
                }

                rightChild.parent = parent;
            }
        } else {
            //双child
            //判断删除节点的右子树的左子树是否为空，是否需要获取最小左子树
            if (rightChild.leftChild == null) {
                //将当前节点的左子树接到右子树的左子树
                rightChild.leftChild = node.rightChild;
                node.rightChild.parent = rightChild;

                if (parent == null) {
                    root = rightChild;
                    rightChild.parent = null;
                } else {
                    if (node == parent.leftChild) {
                        parent.leftChild = rightChild;
                    } else if (node == parent.rightChild) {
                        parent.rightChild = rightChild;
                    }
                    rightChild.parent = parent;
                }
            } else {
                //需要查找出右节点的最小左子树
                BinarySearchTreeNode minimumLeftNode = findMinimumLeftNode(rightChild);

                //断开其parent关联,同时绑定其右child节点到其parent
                if (minimumLeftNode == minimumLeftNode.parent.leftChild) {
                    minimumLeftNode.parent.leftChild = minimumLeftNode.rightChild;
                } else if (minimumLeftNode == minimumLeftNode.parent.rightChild) {
                    minimumLeftNode.parent.rightChild = minimumLeftNode.rightChild;
                }
                minimumLeftNode.parent = null;

                //当前节点的左子树绑定到min左节点
                minimumLeftNode.leftChild = leftChild;
                leftChild.parent = minimumLeftNode;
                //当前节点的右子树绑定到min右节点
                minimumLeftNode.rightChild = rightChild;
                rightChild.parent = minimumLeftNode;

                if (parent == null) {
                    root = minimumLeftNode;
                    root.parent = null;
                } else {
                    if (node == parent.leftChild) {
                        parent.leftChild = minimumLeftNode;
                    } else if (node == parent.rightChild) {
                        parent.rightChild = minimumLeftNode;
                    }

                    minimumLeftNode.parent = parent;
                }
            }
        }
    }

    public BinarySearchTreeNode findMinimumLeftNode(BinarySearchTreeNode node) {
        if (node == null) {
            return null;
        }

        BinarySearchTreeNode temp = null;

        while (node != null) {
            temp = node;
            node = node.leftChild;
        }

        return temp;
    }

    public BinarySearchTreeNode searchTreeNode(int data) {
        if (root == null) {
            return null;
        } else {
            BinarySearchTreeNode temp = root;
            while (temp != null) {
                if (data < temp.data) {
                    temp = temp.leftChild;
                } else if (data > temp.data) {
                    temp = temp.rightChild;
                } else {
                    return temp;
                }
            }

            return null;
        }
    }

    @SuppressWarnings("Duplicates")
    public void midOrderTraverse(BinarySearchTreeNode node) {
        if (node == null) {
            return;
        }

        Stack<BinarySearchTreeNode> stack = new Stack<>();
        BinarySearchTreeNode point = node;

        while (point != null || !stack.isEmpty()) {
            while (point != null) {
                stack.push(point);
                point = point.leftChild;
            }

            //打印，并转入右节点
            if (!stack.isEmpty()) {
                point = stack.pop();
                System.out.println(point.toString());
                point = point.rightChild;
            }
        }
    }


    public static class BinarySearchTreeNode {
        /**
         * data直接使用int，方便
         */
        public int data;
        public BinarySearchTreeNode leftChild;
        public BinarySearchTreeNode rightChild;
        public BinarySearchTreeNode parent;

        public BinarySearchTreeNode(int data) {
            this.data = data;
        }

        public BinarySearchTreeNode(int data, BinarySearchTreeNode leftChild, BinarySearchTreeNode rightChild, BinarySearchTreeNode parent) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "BinarySearchTreeNode{" +
                    "data=" + data +
                    '}';
        }
    }
}
