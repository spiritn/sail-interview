package com.jun.code.linear.linkedlist;

/**
 * 1) 单链表反转
 * 2) 链表中环的检测
 * 3) 两个有序的链表合并
 * 4) 删除链表倒数第n个结点
 * 5) 求链表的中间结点
 * 6) 判断是否回文
 * <p>
 * 主要为体现算法，这里没有实现单链表的插入和删除。只是用头结点来代表链表
 */
public class LinkedListAlgo {

    public static void main(String[] args) {
        System.out.println("回文字符串" + isPalindrome(createNodeList2()));

        Node list = createNodeList();
        printAll("原始Node", list);

        Node middleNode = findMiddleNode(list);
        printAll("中间节点", middleNode);

        Node reverseList = reverseNode(list);
        printAll("反转后的Node", reverseList);

        Node node = removeNode(list, 1);
        printAll("删除节点后的Node", node);

        //System.out.println("环的长度为 "+ checkCircle(list));

        //printAll(mergeSortList(createNodeList(), createNodeList2()));
    }

    /**
     * 单链表反转
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public static Node reverseNode(Node head) {
        Node prev = null;
        Node curr = head;
        Node next;

        // 其实就是prev跟着curr，往后走
        while (curr != null) {
            // 注意这样记忆，顺序上一行的右边就是下一行的左边
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 寻找中间结点
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @return 如果链表为偶数，返回中间偏右那个结点
     */
    public static Node findMiddleNode(Node head) {
        if (head == null) {
            return null;
        }
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 链表中环的检测
     *
     * @return 环的长度
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public static int checkCircle(Node head) {
        Node fast = head;
        Node slow = head;
        int count = 0;
        boolean meet = false;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // 相遇，说明有环。meet == true开始计数
            if (fast == slow) {
                meet = true;
            }

            if (meet) {
                // 因为fast是slow的2倍，所以fast和slow再次相遇时，正好fast跑2圈，slow跑了一圈，也就是环的长度
                if (count != 0 && fast == slow) {
                    return count;
                }
                count++;
            }
        }
        return 0;
    }

    /**
     * 判断是否回文
     * https://blog.csdn.net/shengqianfeng/article/details/99612480
     */
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node fast = head;
        Node slow = head;
        Node prev = null;
        Node next;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            //单链表反转
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // fast到达尾结点时为空，说明回文字符串为偶数，slow到达中间的偏右一个
        // fast最后不是空，说明回文字符串为奇数，slow正好到达中点，此时需要再往右移动一位
        if (fast != null) {
            slow = slow.next;
        }

        // slow指针继续往右走，prev指针中间结点的左一位。正好可以和slow一个一个比较
        while (slow != null) {
            if (slow.data != prev.data) {
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }
        return true;
    }

    /**
     * 合并两个有序链表
     *
     * @param head1 从小到大排序的链表
     * @param head2 从小到大排序的链表
     * @return 合并后的链表
     * 时间复杂度 O(m+n)
     */
    public static Node mergeSortList(Node head1, Node head2) {
        //引入一个哨兵。来避免下面还要判断头结点的问题
        Node soldier = new Node(0, null);
        Node curr = soldier;

        Node n1 = head1;
        Node n2 = head2;

        while (n1 != null && n2 != null) {
            if (n1.data < n2.data) {
                curr.next = n1;
                n1 = n1.next;
            } else {
                curr.next = n2;
                n2 = n2.next;
            }
            curr = curr.next;
        }

        //此时说明有一个链表走到头，另一个链表还没走到头
        if (n1 != null) {
            curr.next = n1;
        }
        if (n2 != null) {
            curr.next = n2;
        }

        //哨兵的下一个节点就是合并后的头结点
        return soldier.next;
    }

    /**
     * 删除链表倒数第k个结点
     *
     * @param k 从1开始数
     */
    public static Node removeNode(Node head, int k) {
        Node fast = head;
        int n = 1;
        while (fast != null && n < k) {
            fast = fast.next;
            n++;
        }

        // fast走到头，说明链表的长度还不到k。直接返回原链表
        if (fast == null) {
            return head;
        }

        Node slow = head;
        Node prev = null;
        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        // prev指的是要删除的第k个节点前的那个节点
        if (prev == null) {
            head = head.next;
        } else {
            prev.next = prev.next.next;
        }
        return head;
    }

    public static class Node {
        private int data;
        public Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 根据头节点逐个打印所有结点
     */
    public static void printAll(String desc, Node list) {
        System.out.print(desc + ": ");
        Node p = list;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }


    public static Node createNode(int value) {
        return new Node(value, null);
    }

    public static Node createNodeList() {
        Node a = createNode(1);
        Node b = createNode(2);
        a.next = b;
        Node c = createNode(3);
        b.next = c;
        Node d = createNode(4);
        c.next = d;
        Node e = createNode(3);
        d.next = e;
        Node f = createNode(2);
        e.next = f;
        // 形成环
        // f.next = c;
        return a;
    }

    public static Node createNodeList2() {
        Node a = createNode(1);
        Node b = createNode(2);
        a.next = b;
        Node c = createNode(3);
        b.next = c;
        Node d = createNode(2);
        c.next = d;
        Node e = createNode(1);
        d.next = e;
        return a;
    }

}
