package ds.linaerlist;

import java.util.Iterator;

/**
 * 单向链表
 *
 * @author Suz1
 * @date 2020/6/1 5:30 下午
 */
public class SinglyLinkedList<E> implements Iterable<E> {
    // 头节点
    private Node<E> head;
    private int size;

    // 构造器
    public SinglyLinkedList() {
        this.head = new Node<E>(null, null);
        this.size = 0;
    }

    public void print() {
        Node<E> n = head;
        while (n.next != null) {
            n = n.next;
            System.out.print(n.data + "->");
        }
        System.out.println();
    }

    // insert to the end
    public void insert(E e) {
        // find the last node
        Node<E> n = head;
        while (n.next != null) {
            n = n.next;
        }
        // create new node and append to the last
        n.next = new Node<E>(e, null);
        size++;
    }

    // Insert to the certain index
    public void insert(int i, E e) {
        Node<E> pre = head;
        // 找到要插入的前驱节点
        for (int idx = 0; idx < i - 1; idx++) {
            pre = pre.next;
        }
        // newNode的 next 是原来的 node 的 next,再把原来 node 的 next 指向新 node
        pre.next = new Node<>(e, pre.next);
        size++;
    }

    // Delete the certain index
    public E delete(int i) {
        // 找到要删除的前驱节点
        Node<E> pre = head;
        for (int idx = 0; idx < i - 1; idx++) {
            pre = pre.next;
        }
        // 要删除位置上的节点
        Node<E> curr = pre.next;
        // 前驱节点的 next 是原来节点的 next
        pre.next = curr.next;
        size--;
        return curr.data;
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head.next = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void reverse() {
        if (isEmpty()) {
            return;
        }
        this.reverse(head.next);
    }

    // 反转指定的结点curr，并把反转后的结点返回
    public Node<E> reverse(Node<E> curr) {

        // 递归终止条件,找到最后的元素
        if (curr.next == null) {
            // 头结点的 next 指向 最后的节点
            head.next = curr;
            // 返回最后的节点
            return curr;
        }
        // 递归的反转当前结点curr的下一个结点；
        // 返回值就是链表反转后，当前结点的上一个结点
        Node<E> pre = reverse(curr.next);
        // 让返回的节点的下一节点变为当前节点 curr
        pre.next = curr;
        // 当前的节点的下一节点变为 null
        curr.next = null;
        return curr;
    }

    // Get the element of certain index
    public E get(int index) {
        // 头节点不存储数据 从头结点开始向后查找 查找 n 次,就能查找到
        Node<E> n = head.next;
        for (int idx = 0; idx < index; idx++) {
            n = n.next;
        }
        return n.data;
    }

    public int indexOf(E e) {
        Node<E> n = this.head;
        for (int i = 0; n.next != null; i++) {
            n = n.next;
            if (e.equals(n.data)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Literator();
    }


    /**
     * each node of singly-linked-list
     *
     * @param <E> Element
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        private Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private class Literator implements Iterator<E> {

        private Node<E> n;

        public Literator() {
            this.n = head;
        }

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public E next() {
            n = n.next;
            return n.data;
        }
    }
}



