package ds.leetcode.queue;

import leetcodeds.statckqueue.queue.LcCircleQueue;
import org.junit.Test;

/**
 * @author Suz1
 * @date 2020/4/5 4:10 下午
 */
public class QueueTest {
    @Test
    public void test() {
        LcCircleQueue queue = new LcCircleQueue(3);
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.deQueue();
        queue.deQueue();
        queue.enQueue(5);
        System.out.println(queue);
    }
}
