package leetcode.data_structure.linkedlist;

import nodes.ListNode;

/**
 * LeetCode 链表优秀的问题
 *
 * @author Suz1
 * @date 2020/6/3 10:12 上午
 */
public class LinkedListSolution {


    /**
     * 找到两个链表<b>交汇</b>的节点
     * <p>
     * leetcode
     * <p>https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/ </p>
     * <p>https://leetcode-cn.com/problems/intersection-of-two-linked-lists/</p>
     * <p> ! SO GOD DAMN ELEGANT !!!!!</p>
     * <p>
     * Answer: https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/tu-jie-xiang-jiao-lian-biao-by-user7208t/
     *
     * @param headA 第一个链表的 head
     * @param headB 第二个节点的 head
     * @return 交汇的节点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode boy = headA, gf = headB;

        while (boy != gf) {
            boy = boy == null ? headB : boy.next;
            gf = gf == null ? headA : gf.next;
        }
        return boy;
    }

}
