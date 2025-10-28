import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

@Slf4j
public class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] ans = new int[spells.length];
        Arrays.sort(potions);
        System.out.println("potions = " + Arrays.toString(potions));
        for (int i = 0; i < spells.length; i++) {
            int curr = spells[i];
            long target = (success + curr - 1) / (long) curr;
            int idx = findBigger(potions, target);
            ans[i] = potions.length - idx;
        }
        return ans;
    }

    public int findBigger(int[] arr, long target) {
        int left = -1, right = arr.length;
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            System.out.println("mid = " + mid);
            if ((long) arr[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    @Test
    public void test() {
        int[] spell = {3, 1, 2}, potions = {8, 5, 8};

        int[] ints = successfulPairs2(spell, potions, 16);
        System.out.println("ints = " + Arrays.toString(ints));
    }


    public int[] successfulPairs2(int[] spells, int[] potions, long success) {
        int mx = 0;
        for (int y : potions) {
            mx = Math.max(mx, y);
        }

        int[] cnt = new int[mx + 1];
        for (int y : potions) {
            cnt[y]++; // 统计每种药水的出现次数
        }

        // 计算 cnt 的后缀和
        for (int i = mx - 1; i >= 0; i--) {
            cnt[i] += cnt[i + 1];
        }
        // 计算完毕后，cnt[i] 就是 potions 值 >= i 的药水个数

        for (int i = 0; i < spells.length; i++) {
            long low = (success - 1) / spells[i] + 1;
            spells[i] = low <= mx ? cnt[(int) low] : 0;
        }
        return spells;
    }

    // ** https://leetcode.cn/problems/count-the-number-of-fair-pairs/

    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int ans = 0;
        for (int j = 0; j < nums.length; j++) {
            int num = nums[j];
            int small = bsFind(nums, j, lower - nums[j]);
            int big = bsFind(nums, j, upper - nums[j] + 1);
            ans += big - small;
        }

        return ans;
    }

    int bsFind(int[] arr, int right, int target) {
        int left = -1;
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    @Test
    public void testPair() {
        int[] arr = {1, 7, 9, 2, 5};
        long l = countFairPairs(arr, 11, 11);
        System.out.println("l = " + l);
    }
}
