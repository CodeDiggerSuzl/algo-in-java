package nodes;

/**
 * 双向链表节点
 *
 * @author Suz1
 * @date 2020/6/4 9:50 上午
 */
public class DouListNode {
    public int val;
    public DouListNode next;
    public DouListNode prev;

    public DouListNode(int data) {
        this.val = data;
    }
}
