package leetcode.solution.linkedlist;


import org.junit.Test;

public class LinkedListSolutions {

    /**
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    @Test
    public void test_() {
        int[] arr = {1, 2, 3, 5};
        ListNode node = createLinkedList(arr);
        ListNode head = reverseList(node);
        printLinkedList(head);

    }


    public static ListNode createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(-1); // 虚拟头节点
        ListNode cur = dummy;              // 移动指针

        for (int val : arr) {
            cur.next = new ListNode(val);  // 新建节点接在后面
            cur = cur.next;                // 指针后移
        }

        ListNode next = dummy.next;
        printLinkedList(next);
        return next; // 返回真正的头节点
    }

    // ---------------------------------------------------------
    // 3.【核心工具】打印链表 (用于检查结果)
    // ---------------------------------------------------------
    public static void printLinkedList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " → ");
            cur = cur.next;
        }
        System.out.println("null");
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

