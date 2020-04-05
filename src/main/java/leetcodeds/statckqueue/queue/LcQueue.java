package leetcodeds.statckqueue.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode of Array
 * https://leetcode-cn.com/explore/learn/card/queue-stack/216/queue-first-in-first-out-data-structure/862/
 *
 * @author Suz1
 * @date 2020/4/5 3:32 下午
 */
public class LcQueue {

    // store elements
    private List<Integer> data;
    private int pStart;

    public LcQueue() {
        data = new ArrayList<>();
        pStart = 0;
    }

    public boolean enQueue(int x) {
        data.add(x);
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        pStart++;
        return true;
    }

    public boolean isEmpty() {
        return pStart > data.size();
    }

    public int front() {
        return data.get(pStart);
    }

}
