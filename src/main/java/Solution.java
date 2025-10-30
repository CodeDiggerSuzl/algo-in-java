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
        long ans = 0; // TODO
        for (int j = 0; j < nums.length; j++) {
            int small = bsFind(nums, j, lower - nums[j]);
            int big = bsFind(nums, j, upper - nums[j] + 1);
            ans += big - small;
        }

        return ans;
    }

    public long countFairPairsWithLog(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long ans = 0;

        log.info("nums={}", Arrays.toString(nums));

        for (int j = 0; j < nums.length; j++) {
            int smallTarget = lower - nums[j];
            int bigTarget = upper - nums[j] + 1;

            int small = bsFind(nums, j, smallTarget);
            int big = bsFind(nums, j, bigTarget);

            int from = Math.max(0, small);
            int to = Math.max(from, Math.min(big, j)); // 保证 i < j
            int count = Math.max(0, to - from);

            log.info("---- j = {} (nums[j]={}) ----", j, nums[j]);
            log.info("  valid index range i ∈ [{} , {})", from, to);

            // ✅ 打印每一个满足条件的 (i, j) 以及对应的子数组 [nums[i], nums[j]]
            for (int i = from; i < to; i++) {
                log.info("    pair: (i={}, j={}) -> [{}, {}]", i, j, nums[i], nums[j]);
            }

            ans += count;
            log.info("  count added this round = {}", count);
            log.info("  ans so far = {}\n", ans);
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
        int[] arr = {0, 1, 7, 4, 4, 5};
        long l = countFairPairsWith2Pointer(arr, 3, 6);
        System.out.println("l = " + l);
    }

    public long countFairPairsWith2Pointer(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return count(nums, upper) - count(nums, lower - 1);
    }

    public long count(int[] nums, int upper) {
        long res = 0;
        int j = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            while (j > i && nums[j] + nums[i] > upper) {
                j--;
            }
            if (i == j) {
                break;
            }
            res += j - i;
        }
        return res;
    }


    public int hIndex(int[] citations) {
        int len = citations.length;
        int left = 0, right = len;
        while (left < right) {
            int mid = (left + right) / 2;
            if (citations[mid] >= len - mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return len - left;
    }

    @Test
    public void testHIndex() {
        int[] arr = {1, 100, 100, 100};
        int cnt = hIndex(arr);
        System.out.println("cnt = " + cnt);
    }

}
