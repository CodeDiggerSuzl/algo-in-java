package leetcode.solution;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class No_5_377 {
    // https://leetcode.cn/problems/combination-sum-iv/description/

    public int combinationSum4(int[] nums, int target) {
        int cnt = 0; // 输出的 cnt 永远为 0
        c(nums, target, cnt);
        return cnt;
    }


    /**
     * 第一版 自己得的
     */
    public void c(int[] nums, int target, Integer cnt) {
        for (int num : nums) {
            if (num == target) {
                ++cnt;
                System.out.println("cnt = " + cnt);
                return;
            } else { // 这里错了  没考虑 target - num 如果小于 0 的情况
                c(nums, target - num, cnt);
            }
        }
    }

    public int cal(int[] nums, int target, Map<Integer, Integer> memo) {
        int cnt = 0;
        for (int num : nums) {
            if (num == target) {
                ++cnt;
                memo.put(target, cnt);
            } else if (target - num > 0) { // chatgpt 帮写了这个代码,自己又加了 memo. 但是还是很慢
                int tar = target - num;
                if (memo.containsKey(tar)) {
                    cnt += memo.get(tar);
                } else {
                    cnt += cal(nums, tar, memo);
                }
            }
        }
        return cnt;
    }



    // chat gpt 优化了代码 缓存用的不对了上面 👇🏻
    // 这个方法之所以慢，是因为每次都需要重新计算相同的子问题，而没有充分利用已经计算过的结果。为了提高效率，可以使用动态规划来避免重复计算。
    //
    // 你可以修改代码，使用动态规划技术来存储已经计算过的子问题的结果。这样，当遇到相同的子问题时，就可以直接从存储的结果中获取，而不需要重新计算。以下是修改后的代码示例：
    private int calHelper(int[] nums, int target, Map<Integer, Integer> memo) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }

        int cnt = 0;
        for (int num : nums) {
            if (num == target) {
                ++cnt;
            } else if (target - num > 0) {
                cnt += calHelper(nums, target - num, memo);
            }
        }

        memo.put(target, cnt);
        return cnt;
    }
    @Test
    public void test() {
        int[] arr = {1, 2, 3};
        Map<Integer, Integer> memo = new HashMap<>();
        long l = System.currentTimeMillis();
        int cal = calHelper(arr, 35, memo);
        System.out.println((System.currentTimeMillis() - l) / 1000); // 很慢 输入为 4s
        System.out.println("---");
        System.out.println(cal);
    }
}
