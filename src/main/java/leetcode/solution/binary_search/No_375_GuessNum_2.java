package leetcode.solution.binary_search;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * [375. 猜数字大小 II - 力扣（LeetCode）](https://leetcode.cn/problems/guess-number-higher-or-lower-ii/description/)
 *
 *
 *[极小化极大算法 - 维基百科，自由的百科全书](https://zh.wikipedia.org/wiki/%E6%9E%81%E5%B0%8F%E5%8C%96%E6%9E%81%E5%A4%A7%E7%AE%97%E6%B3%95)
 */
@Slf4j
public class No_375_GuessNum_2 {

    public int getMoneyAmount(int n) {
        int max = 0;

        for (int i = 0; i < n; i++) {
            max += i;
        }
        return binary(n, max);

    }

    private int binary(int n, int max) {
        int mid = 0;
        int low = 1, high = n;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (valid(mid, max)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean valid(int mid, int max) {
        return false;
    }

    @Test
    public void test() {
        int moneyAmount = getMoneyAmount(10);
        System.out.println("moneyAmount = " + moneyAmount);

    }
}
