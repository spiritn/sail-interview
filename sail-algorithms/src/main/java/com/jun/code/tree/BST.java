package com.jun.code.tree;


import apple.laf.JRSUIUtils;

import java.util.*;

/**
 * 二分查找树 Binary Search Tree
 */
public class BST {

    public static void main(String[] args) {
        BST bst = new BST(new TreeNode(8));
        bst.insert(5);
        bst.insert(12);
        bst.insert(19);
        bst.insert(5);
        bst.insert(2);
        TreeNode treeNode = bst.get(5);

        bst.printTree();
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

        TreeNode current = root;
        while (true) {
            // 遍历查找，比较大小分三种情况
            if (key > current.value) { // 大于
                if (current.right == null) {
                    current.right = new TreeNode(key);
                    return;
                }
                current = current.right;
            } else if (key < current.value) { // 小于
                if (current.left == null) {
                    current.left = new TreeNode(key);
                    return;
                }
                current = current.left;
            } else {
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

        // 此时current为要删除的节点，然后分三种情况讨论
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
     * 寻找要被删除的节点 右子树中最小的节点
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

    /**
     * ====================
     * 遍历树
     * ====================
     */
    public void printTree() {

        preOrderRecursion(root);
        preOrderStack(root);
        System.out.println("前根序遍历");

        inOrderRecursion(root);
        inOrderStack(root);
        System.out.println("中续遍历");

        postOrderRecursion(root);
        postOrderStack(root);
        System.out.println("后续遍历");

        bfs(root);
        bfs2(root);
        System.out.println("层级遍历");
    }

    /**
     * 使用递归实现前根序遍历
     */
    public void preOrderRecursion(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preOrderRecursion(node.left);
        preOrderRecursion(node.right);
    }

    /**
     * 使用递归实现中根序遍历, 正好是从小到大的顺序排列
     */
    public void inOrderRecursion(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderRecursion(node.left);
        System.out.print(node.value + " ");
        inOrderRecursion(node.right);
    }

    /**
     * 使用递归实现后根序遍历
     */
    public void postOrderRecursion(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrderRecursion(node.left);
        postOrderRecursion(node.right);
        System.out.print(node.value + " ");
    }

    /**
     * 使用栈来实现前根序遍历，面试常见哎
     */
    public void preOrderStack(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.print(current.value + " ");
            // 先把right放进去
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    /**
     * 利用栈中根序遍历
     */
    public void inOrderStack(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            TreeNode node = stack.pop();
            System.out.print(node.value + " ");
            if (node.right != null) {
                current = node.right;
            }
        }
    }

    /**
     * 使用栈后序遍历
     */
    public void postOrderStack(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> resultStack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            resultStack.push(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        while (!resultStack.isEmpty()) {
            System.out.print(resultStack.pop().value + " ");
        }
    }

    /**
     * 层序遍历
     */
    public void bfs(TreeNode node) {
        if (node == null) {
            return;
        }
        // 用一个单向队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.value + " ");
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    /**
     * 层序遍历，每层都分开
     */
    public List<List<Integer>> bfs2(TreeNode node) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            temp = new ArrayList<>();
            int size = queue.size();
            while (size-- > 0) {
                TreeNode current = queue.poll();
                temp.add(current.value);
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            result.add(temp);
        }
        System.out.print(result);
        return result;
    }
}
