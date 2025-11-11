package leetcode.solution.sliding_window;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 滑动窗口答案
 *
 */
@Slf4j
public class SlidingWindowSolutions {

    /**
     * 209. 长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
     * 如果不存在符合条件的子数组，返回 0 。
     * <p>
     * <a href="https://leetcode.cn/problems/minimum-size-subarray-sum/description/">url</a>
     */
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int ans = len + 1; // 大于最大长度
        int left = 0;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left];
                left++;
            }

        }
        return ans > len ? 0 : ans;
    }

    public int minSubArrayLen2(int target, int[] nums) {
        int len = nums.length, ans = len + 1, left = 0, sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum - nums[left] >= target) {
                sum -= nums[left];
                left += 1;
            }
            if (sum > target) {
                ans = Math.min(ans, right - left + 1);
            }
        }
        return ans > len ? 0 : ans;
    }

    @Test
    public void test_209() {
        //        int[] arr = {2, 3, 1, 2, 4, 3};
        int[] arr = {1, 1, 1, 1, 1, 1, 1, 1};
        int res = minSubArrayLen2(11, arr);
        System.out.println("res = " + res);
    }





    /* -----------------------------------------------  ----------------------------------------------- */
}
