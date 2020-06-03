package codinginterviewguide.linkedlist;

/**
 * 链表问题相关解答
 *
 * @author Suz1
 * @date 2020/6/2 3:28 下午
 */
public class LinkedListQuestions {
    /**
     * ch2-No1
     * 打印两个链表的公共部分
     *
     * <p></p>
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
        if (head == null || lastKth < 1) { return head; }
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
            while (--len != 0) {
                curr = curr.next;
            }
            curr.next = curr.next.next;
        }
        return head;
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
}
