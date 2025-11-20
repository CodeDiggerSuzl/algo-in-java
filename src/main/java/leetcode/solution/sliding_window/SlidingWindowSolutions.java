package leetcode.solution.sliding_window;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口答案
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
        int ans = len + 1; // 大于最大长度 Or Integer.MAX_VALUE
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
    // [713. 乘积小于 K 的子数组 - 力扣（LeetCode）](https://leetcode.cn/problems/subarray-product-less-than-k/description/)
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0; // ⚠️ 防止除法死循环,边界处理
        int ans = 0, left = 0, product = 1;
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            log.info("left={},right={},prod={}", left, right, product);
            while (product >= k) {
                // while 里面是不合法的
                product = product / nums[left];
                left++;
            }
            // 应该在合法的区域更新的答案
            ans += (right - left + 1);
        }
        return ans;
    }

    @Test
    public void test_713() {
        int[] arr = {10, 5, 2, 6};
        int ans = numSubarrayProductLessThanK(arr, 100);
        log.info("ans={}", ans);
    }

    /* ---------------------------------------------------------------------------------------*/

    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
     * 提示：
     * <p>
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     */
    public int lengthOfLongestSubstring(String s) {
        int ans = 0, left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int R = 0; R < s.length(); R++) {
            char c = s.charAt(R);
            Integer preCnt = map.getOrDefault(c, 0);
            map.merge(c, 1, Integer::sum);
            while (map.get(c) > 1) {
                char key = s.charAt(left);
                map.computeIfPresent(key, (k, prevLeftCnt) -> prevLeftCnt - 1);
                left++;
            }
            ans = Math.max(ans, R - left + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstring_2(String s) {
        int ans = 0, L = 0;
        int[] arr = new int[256];
        for (int R = 0; R < s.length(); R++) {
            char c = s.charAt(R);
            arr[c]++;
            while (arr[c] > 1) {
                arr[s.charAt(L)]--;
                L++;
            }
            ans = Math.max(ans, R - L + 1);
        }
        return ans;
    }

    @Test
    public void test_3() {
        String s = "pwwkew";
        int cnt = lengthOfLongestSubstring_2(s);
        log.info("cnt={}", cnt);
    }

    /* ---------------------------------------------------------------------------------------*/
    // 3090. 每个字符最多出现两次的最长子字符串 - 力扣（LeetCode）
    // https://leetcode.cn/problems/maximum-length-substring-with-two-occurrences/description/
    public int maximumLengthSubstring(String s) {
        if (s.length() <= 1) return 0;
        int ans = 0, left = 0;
        int[] arr = new int[26];
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            arr[c - 'a']++;
            while (arr[c - 'a'] > 2) { // not satisfied in while loop
                arr[s.charAt(left) - 'a']--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    @Test
    public void test_3090() {
        String s = "aaaa";
        int cnt = maximumLengthSubstring(s);
        log.info("cnt={}", cnt);
    }

    /* ---------------------------------------------------------------------------------------*/

    /**
     * <a href="https://leetcode.cn/problems/length-of-longest-subarray-with-at-most-k-frequency/description/">2958. 最多 K 个重复元素的最长子数组 - 力扣（LeetCode）</a>
     */
    public int maxSubarrayLength(int[] nums, int k) {
        int left = 0, ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int right = 0; right < nums.length; right++) {
            int rightVal = nums[right];
            map.merge(rightVal, 1, Integer::sum);
            while (map.get(rightVal) > k) {
                int leftVal = nums[left];
                map.computeIfPresent(leftVal, (key, v) -> v - 1);
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public int maxSubarrayLength2(int[] nums, int k) {
        int left = 0, ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int right = 0; right < nums.length; right++) {
            int rVal = nums[right];
            Integer rPrevCnt = map.getOrDefault(rVal, 0);
            map.put(rVal, rPrevCnt + 1);
            while (map.get(rVal) > k) {
                int lVal = nums[left];
                Integer lPrevCnt = map.get(lVal);
                map.put(lVal, lPrevCnt - 1);
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    @Test
    public void test_2958() {
        int[] arr = {5, 5, 5, 5, 5, 5, 5};
        int ans = maxSubarrayLength2(arr, 4);
        System.out.println("ans = " + ans);
    }

    /* ---------------------------------------------------------------------------------------*/
    // <a href="https://leetcode.cn/problems/find-the-longest-semi-repetitive-substring/description/">2730. 找到最长的半重复子字符串 - 力扣（LeetCode）</a>
    public int longestSemiRepetitiveSubstring(String s) {
        int ans = 1, left = 0, count = 0;
        for (int right = 1; right < s.length(); right++) {
            // 窗口右边移动，扩大窗口，条件是相邻的字符相同
            if (s.charAt(right - 1) == s.charAt(right)) {
                count++;
            }
            // 当条件被破坏不满足的时候， 左侧窗口右边移动，同时只能有一对
            while (count > 1) {
                if (s.charAt(left) == s.charAt(left + 1)) {
                    count--;
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    @Test
    public void test_2730() {
        String s = "5494";
        int ans = longestSemiRepetitiveSubstring(s);
        System.out.println("ans = " + ans);
    }

    /* ---------------------------------------------------------------------------------------*/
    //<a href="https://leetcode.cn/problems/maximum-beauty-of-an-array-after-applying-operation/description/">2779. 数组的最大美丽值 - 力扣（LeetCode）</a>
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            while (left < right && nums[right] - nums[left] > 2 * k) {
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    @Test
    public void test_2779() {
        int[] arr = {4, 6, 1, 2};
        int ans = maximumBeauty(arr, 2);
        System.out.println("ans = " + ans);
    }
    /* ---------------------------------------------------------------------------------------*/


}
