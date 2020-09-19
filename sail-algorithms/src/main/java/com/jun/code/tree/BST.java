package com.jun.code.tree;


/**
 * 二分查找树 Binary Search Tree
 */
public class BST {

    public static void main(String[] args) {
        BST bst = new BST(new TreeNode(8));
        bst.insert(5);
        bst.insert(9);
        bst.insert(2);
        TreeNode treeNode = bst.get(5);
        System.out.println(treeNode);
    }


    /**
     * 表示根节点，一个排序二叉树只需要用一个根节点表示即可
     */
    private TreeNode root;

    public BST(TreeNode root) {
        this.root = root;
    }

    public TreeNode get(int key) {
        TreeNode current = this.root;
        while (current != null && current.value != key) {
            if (key > current.value) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return current;
    }

    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }

        // 需要两个指针
        TreeNode current = root;
        TreeNode parent;
        while (true) {
            parent = current;
            // 遍历查找，比较大小分三种情况
            if (key > parent.value) { // 大于parentNode
                current = parent.right;
                if (current == null) {
                    parent.right = new TreeNode(key);
                    return;
                }
            } else if (key < parent.value) { // 小于parentNode
                current = parent.left;
                if (current == null) {
                    parent.left = new TreeNode(key);
                    return;
                }
            } else { // 相等 直接返回
                return;
            }
        }
    }

    public boolean remove(int key) {
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChildren = false;
        // 先去寻找到该节点
        while (current != null && key != current.value) {
            parent = current;
            if (key > current.value) {
                isLeftChildren = false;
                current = current.right;
            } else {
                isLeftChildren = true;
                current = current.left;
            }
        }
        if (current == null) {
            return false;
        }

        // current为要删除的节点，然后分三种情况讨论
        // case1：current没有子节点
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChildren) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // case2: 只有一个子节点
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChildren) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChildren) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }
        // case3: 有两个子节点，这里选择从右节点选取最小的值
        else {
            TreeNode successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChildren) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
        }
        return true;
    }

    /**
     * 寻找要被删除的节点右子树中最小的节点
     *
     * @param node 要被删除的节点
     */
    private TreeNode getSuccessor(TreeNode node) {
        TreeNode successor = null;
        TreeNode successorParent = null;
        TreeNode current = node.right;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        // 此时successor为最左边的节点,要把successor提到被删除的节点node位置处
        if (successor != node.right) {
            successorParent.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }
}
