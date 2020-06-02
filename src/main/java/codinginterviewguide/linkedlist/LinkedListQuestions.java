package codinginterviewguide.linkedlist;

/**
 * 链表问题相关解答
 *
 * @author Suz1
 * @date 2020/6/2 3:28 下午
 */
public class LinkedListQuestions {
    /**
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
            // ! different stuff
            DoubleNode temp = curr.next.next;
            curr.next = temp;
            if (temp != null) {
                temp.prev = curr;
            }
        }

        return head;
    }

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
