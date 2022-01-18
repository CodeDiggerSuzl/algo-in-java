package codeofbooks.codinginterviewguide.ch2linkedlist;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 第二章-链表问题相关解答
 *
 * @author Suz1
 * @date 2020/6/2 3:28 下午
 */
public class LinkedListQuestions {

    /**
     * No1-打印两个有序链表的公共部分
     *
     * <p></p>
     * 思路: 1 小于 2, 1 向后移动, 反之亦然. 只有相同的时候才打印然后都向后移动.
     *
     * @param head1 链表 1 的头指针
     * @param head2 链表 2 的头指针
     */
    public void printCommonElement(Node head1, Node head2) {
        System.out.println("Common elements: ");
        if (head1.val > head2.val) {
            head2 = head2.next;
        } else if (head1.val < head2.val) {
            head1 = head1.next;
        } else {
            System.out.print(head1.val);
            head1 = head1.next;
            head2 = head2.next;
        }
        System.out.println();
    }

    /**
     * 删除单链表的倒数第 K 个元素
     * <p></p>
     *
     * @param head    头指针
     * @param lastKth 倒数第K个
     * @return The deleted Node
     */
    public Node removeLastKthNodeOfSinglyList(Node head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }
        Node cur = head;
        // 遍历一遍 k--
        while (cur != null) {
            lastKth--;
            cur = cur.next;
        }
        // 恰好为 0;说明倒数为 list 长度和 K 相同
        if (lastKth == 0) {
            head = head.next;
        }

        //  小于 0 说明找到了,再次遍历,++k
        if (lastKth < 0) {
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            // 删除节点
            cur.next = cur.next.next;
        }
        return head;
    }

    /**
     * ch2-No2
     * 删除双向链表的倒数第 K 个元素
     * <p></p>
     *
     * @param head    头指针
     * @param lastKth 倒数元素
     * @return 新链表的 head
     */
    public DoubleNode rmLastKthNodeOfDoubleList(DoubleNode head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }
        DoubleNode curr = head;
        while (curr != null) {
            lastKth--;
            curr = curr.next;
        }
        // len(list) = k
        if (lastKth == 0) {
            head.prev = null;
            head = head.next;
        }

        if (lastKth < 0) {
            curr = head;
            while (++lastKth != 0) {
                curr = curr.next;
            }
            // * different stuff
            DoubleNode temp = curr.next.next;
            curr.next = temp;
            if (temp != null) {
                temp.prev = curr;
            }
        }

        return head;
    }

    /**
     * ch2-No3-Basic
     * ! 实现删除链表中间节点的函数 TODO
     *
     * <p>
     * e.g.
     * 1-null: 删除 none<p>
     * 1-2-null: del 1 <p>
     * 1-2-3-null: del 2  <p>
     * 1-2-3-4-null:del 2  <p>
     * 1-2-3-4-5-null: del 3 <p>
     * </p>
     * <p>
     * 分析:链表长度每增加 2,要删除的节点向后移动 1
     * <p></p>
     * <p>删除节点关键是找到前驱节点</b>
     * </p>
     *
     * @param head 头指针
     * @return removed node
     */
    public Node rmMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        // if only one element remove none
        if (head.next.next == null) {
            return head.next;
        }
        Node prev = head;
        Node curr = head.next.next;
        while (curr.next != null && curr.next.next != null) {
            prev = prev.next;
            curr = curr.next.next;
        }
        prev.next = prev.next.next;
        return head;
    }

    /**
     * ch2-No3-adv
     * <p>Delete the node where the pos in a/b </p>
     *
     * @param head head of the list
     * @param a    a
     * @param b    b
     * @return removed node
     */
    public Node rmByMidRatio(Node head, int a, int b) {
        if (a < 1 || a > b) {
            return head;
        }
        // find the len of the list
        int len = 0;
        Node curr = head;
        // ! curr is not null
        while (curr != null) {
            curr = curr.next;
            len++;
        }
        // find the pos of the node to delete
        len = (int) Math.ceil((double) (a * len) / (double) b);
        if (len == 1) {
            return head.next;
        }
        // 删除
        if (len > 1) {
            curr = head;
            // ! NOT 0: 是 1 说明
            while (--len != 1) {
                curr = curr.next;
            }
            curr.next = curr.next.next;
        }
        return head;
    }

    /**
     * ch2-No4 reverse singly list & double list
     *
     * @param head head
     * @return new head after reverse
     */
    public Node reverseSinglyList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node cur = head.next; // next is curr
        head.next = null;
        Node newNode = reverseSinglyList(cur);
        newNode.next = head;
        return newNode;
    }

    /**
     * Using loop to reverse the linked-list
     *
     * @param head The head of a list
     * @return new head
     */
    public Node reverseSingleListWithLoop(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            // 向后移动
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * Reverse the double linked list node
     *
     * @param head head
     * @return new head
     */
    public DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        DoubleNode prev = null;
        DoubleNode curr = head;
        while (curr != null) {
            DoubleNode next = curr.next;
            curr.next = prev;
            curr.prev = next;
            // 向后移动
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * No 5 of ch2 : reverse part linked list
     * <p>
     * LeetCode: https://leetcode-cn.com/problems/reverse-linked-list-ii/
     * </p>
     *
     * @param head head
     * @param from start
     * @param to   end
     * @return new head
     */
    public Node reversePartList(Node head, int from, int to) {
        int len = 0; // for length
        Node node1 = head;
        Node fPre = null, tPost = null;
        // 获取长度 获取反转部分到前一个和后一个节点
        while (node1 != null) {
            len++;
            fPre = len == from - 1 ? node1 : fPre;
            tPost = len == to + 1 ? node1 : tPost;
            node1 = node1.next;
        }
        // Judging Special cases
        if (from > to || from < 1 || to > len) {
            return head;
        }
        // 获取 node1 的值
        node1 = fPre == null ? head : fPre.next;
        Node node2 = node1.next;
        node1.next = tPost;
        Node next = null;
        // reverse part
        while (node2 != tPost) {
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }
        if (fPre != null) {
            fPre.next = node1;
            return head;
        }
        return node1;
    }

    /**
     * Second way of reverse part linked list.
     * <p>
     * https://pic.leetcode-cn.com/3158b23f7e6919d47a11a2f57e921b5645fceb84212450336f2256f5659fa9e7.jpg
     *
     * @param head head
     * @param m    start post
     * @param n    end post
     * @return new head
     */
    public Node reversePartList2(Node head, int m, int n) {
        Node res = new Node(0);
        res.next = head;

        Node node = res;
        for (int i = 1; i < m; i++) {
            node = node.next;
        }

        // node.next is start of the part linked-list
        Node nextHead = node.next;
        Node next = null, prev = null;
        // reverse the part
        for (int i = m; i <= n; i++) {
            next = nextHead.next;
            nextHead.next = prev;
            prev = nextHead;
            nextHead = next;
        }
        // 将反转的起点 next 指向 next
        node.next.next = next;
        // 需要反转的那一段的上一个节点的 next 节点指向反转后链表的头结点
        node.next = prev;
        return res.next;
    }

    /**
     * No 6 of ch2: Josephus Kill problem
     *
     * <b>regular solution</b>
     * <p>
     * 时间复杂度 O(m*n) 时间复杂度高
     *
     * @param head head
     * @param m    if is m,kill
     * @return last node
     */
    public Node josephusKill(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }

        Node last = head;
        // find the last node
        while (last.next != head) {
            last = last.next;
        }

        int cnt = 0;
        while (head != last) {
            if (++cnt == m) {
                // remove the node
                last.next = head.next;
                cnt = 0; // start recount
            } else {
                last = last.next;
            }
            // if 执行完之后在进行 head 的复制 保持 head = last.next
            head = last.next;
        }
        return head;
    }

    /**
     * todo
     */
    public Node josephusKillAdv(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }

        Node curr = head.next;
        int tmp = 1;
        // get length
        while (curr != head) {
            tmp++;
            curr = curr.next;
        }
        tmp = getLive(tmp, m);
        while (--tmp != 0) {
            head = head.next;
        }
        // become loop
        head.next = head;
        return head;
    }

    private int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i - 1, m) + m - 1) % i + 1;
    }

    /**
     * 复制含有随机指针的节点的链表,利用 map
     */
    public RandNode copyRandNodeListWithHashmap(RandNode head) {
        Map<RandNode, RandNode> map = new HashMap<>(8);
        RandNode curr = head;
        // 遍历放入到 map
        while (curr != null) {
            map.put(curr, new RandNode(curr.value));
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).rand = map.get(curr.rand);
            curr = curr.next;
        }
        return map.get(head);
    }

    /**
     * 单向链表 node
     */
    static class Node {
        public int val;
        public Node next;

        public Node(int data) {
            this.val = data;
        }
    }

    /**
     * 双向链表 node
     */
    static class DoubleNode {
        public int val;
        public DoubleNode next;
        public DoubleNode prev;

        public DoubleNode(int data) {
            this.val = data;
        }
    }

    /**
     * 包含随机指针的节点
     */
    @Data
    static class RandNode {
        public int value;
        public RandNode next;
        public RandNode rand;

        public RandNode(int data) {
            this.value = data;
        }
    }
}
