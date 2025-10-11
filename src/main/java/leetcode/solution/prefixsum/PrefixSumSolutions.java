package leetcode.solution.prefixsum;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


@Slf4j
public class PrefixSumSolutions {

    // [3147. 从魔法师身上吸取的最大能量 - 力扣（LeetCode）](https://leetcode.cn/problems/taking-maximum-energy-from-the-mystic-dungeon/?envType=daily-question&envId=2025-10-10)
    public int maximumEnergy(int[] energy, int k) {
        int ans = Integer.MIN_VALUE;
        int len = energy.length - 1;
        int start = len - k;
        for (int i = len - 1; i > start; i--) {
            int sum = 0;
            for (int j = i; j >= 0; j -= k) {
                sum += energy[j];
                ans = Math.max(ans, sum);
            }
        }
        return ans;
    }

    public int maximumEnergy2(int[] energy, int k) {
        int n = energy.length;
        int[] dp = new int[n];
        int ans = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = energy[i];
            // log.info("case normal: i={},dp={},dp[i]={},i+k={}", i, JsonUtil.toJson(dp), dp[i], i + k);
            if (i + k < n) {
                // log.info("case special: i={},dp={},dp[i]={},dp[i+k]={}", i, JsonUtil.toJson(dp), dp[i], dp[i + k]);
                dp[i] += dp[i + k];
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    @Test
    public void test2() {
        int[] arr = {5, 2, -10, -5, 1}; // -2,-1;-3;-1
        int ans = maximumEnergy2(arr, 3);
        System.out.println("ans = " + ans);
        assert ans == 3;
    }


    @Test
    public void test() {
        int[] arr = {-2, -3, -1}; // -2,-1;-3;-1
        int ans = maximumEnergy2(arr, 2);
        System.out.println("ans = " + ans);
        assert ans == -1;
    }

    @Test
    public void test3() {
        int[] arr = {-2, -3, -1}; // -2,-1;-3;-1
        int ans = maximumEnergy2(arr, 2);
        System.out.println("ans = " + ans);
        assert ans == -1;
    }


    // Input: energy = [5,2,-10,-5,1], k = 3
    //
    // Output: 3
    //
    // Explanation: We can gain a total energy of 3 by starting from magician 1 absorbing 2 + 1 = 3.
    //
    // Example 2:
    //
    // Input: energy = [-2,-3,-1], k = 2
}
