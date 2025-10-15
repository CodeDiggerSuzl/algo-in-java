package leetcode.solution.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * [name]:
 * <p>
 * [link]: 
 */
@Slf4j
public class DpSolution {

    /**
     * 70. 爬楼梯
     * 简单
     * 相关标签
     * premium lock icon
     * 相关企业
     * 提示
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * https://leetcode.cn/problems/climbing-stairs/description/
     */
    public int climbStairs(int n) {
        int[] dp = new int[n];
        if (n == 1) return 1;
        if (n == 2) return 2;
        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i <= n - 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    // dp[i] 花的最小的钱
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        if (len <= 2) {
            return 0;
        }
        int[] dp = new int[len + 1];
        dp[0] = dp[1] = 0;

        for (int i = 2; i <= len; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[len];
    }

    @Test
    public void testClimbStairMin() {
        int[] arr = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};

        int i = minCostClimbingStairs(arr);
        log.info("i={}", i);
        assert i == 6;
    }


    @Test
    public void testClimbStairs() {
        int i = climbStairs2(4);
        assert i == 5;
    }

    public int maximumEnergy(int[] energy, int k) {
        int len = energy.length;
        int[] dp = new int[len];
        int ans = Integer.MIN_VALUE;
        for (int i = len - 1; i >= 0; i--) {
            dp[i] = energy[i];
            if (i + k < len) {
                dp[i] = dp[i + k];
            }
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    public int climbStairs2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int rob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        if (len <= 2) {
            return dp[1];
        }
        for (int i = 2; i <= len - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len - 1];
    }

    @Test
    public void testRob() {
        int[] arr = {1, 2, 3, 1};

        int i = rob(arr);
        assert i == 4;
    }
}
