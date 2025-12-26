package leetcode.solution.linkedlist;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LinkedListSolutions {

    /**
     * <a href="https://leetcode.cn/problems/reverse-linked-list/description/">206. 反转链表 - 力扣（LeetCode）</a>
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

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList2(head.next);
        log.debug("new head={},head.val={}", newHead.val, head.val);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    @Test
    public void test_206() {
        int[] arr = {1, 2, 3, 5};
        ListNode node = createLinkedList(arr);
        ListNode head = reverseList2(node);
        printLinkedList(head);
    }

    /* ---------------------------------------------------------------------------------------*/

    /**
     * <a href="https://leetcode.cn/problems/reverse-linked-list-ii/description/">92. 反转链表 II - 力扣（LeetCode）</a>
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * <p>
     * 输入：head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        // 0 1 2 3 4 5
        ListNode p0 = dummy;
        for (int i = 1; i < left; i++) {
            p0 = p0.next; // 找到 left 前一个节点
        }
        log.info("after finding p0 = {}", p0.val);
        // 翻转数组
        ListNode prev = null;
        ListNode curr = p0.next;
        for (int j = left; j <= right; j++) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        log.info("after loop prev={},curr={},p0={}", prev.val, curr.val, p0.val);
        p0.next.next = curr;
        p0.next = prev;
        return dummy.next;
    }

    @Test
    public void test_92() {
        ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});
        ListNode listNode = reverseBetween(head, 2, 4);
        printLinkedList(listNode);
    }

    /* ---------------------------------------------------------------------------------------*/

    /**
     * <a href="https://leetcode.cn/problems/add-two-numbers-ii/description/">445. 两数相加 II - 力扣（LeetCode）</a>
     * <p>
     * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode h1 = reverse(l1);
        ListNode h2 = reverse(l2);
        ListNode dummy = null;
        int carry = 0;
        while (h1 != null || h2 != null || carry > 0) {
            int sum = carry;
            sum += h1 == null ? 0 : h1.val;
            sum += h2 == null ? 0 : h2.val;
            carry = sum / 10;
            ListNode tmp = new ListNode();
            tmp.val = sum % 10;
            tmp.next = dummy;

            if (h1 != null) {
                h1 = h1.next;
            }
            if (h2 != null) {
                h2 = h2.next;
            }
            dummy = tmp;
        }
        return dummy;
    }

    private ListNode reverse(ListNode head) {
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

    public ListNode addTwoNumbersStack(ListNode l1, ListNode l2) {
        return null;
    }

    @Test
    public void test_225() {
        ListNode l1 = createLinkedList(new int[]{7, 2, 4, 3});
        ListNode l2 = createLinkedList(new int[]{5, 6, 4});
        ListNode l3 = addTwoNumbers(l1, l2);
        printLinkedList(l3);

    }

    /* ---------------------------------------------------------------------------------------*/

    /**
     * <a href="https://leetcode.cn/problems/reverse-nodes-in-k-group/description/">25. K 个一组翻转链表 - 力扣（LeetCode）</a>
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        // 链表做到这里了 TODO
        return null;
    }


    /* ---------------------------------------------------------------------------------------*/
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
            System.out.print(cur.val + " -> ");
            cur = cur.next;
        }
        System.out.println("NULL");
    }
}

@Data
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

