package ds.lineartest;

import ds.linaerlist.SinglyLinkedList;
import org.junit.Test;

/**
 * @author Suz1
 * @date 2020/6/1 7:25 下午
 */
public class SinglyListTest {
    @Test
    public void TestSingleList() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.insert("1");
        list.insert("2");
        list.insert("3");
        list.insert("4");
        list.insert(3, "3.1");
        list.print();

        list.reverse();
        list.print();
    }
}
