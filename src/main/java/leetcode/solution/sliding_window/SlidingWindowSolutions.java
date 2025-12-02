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

    /**
     * <a href="https://leetcode.cn/problems/find-the-longest-semi-repetitive-substring/description/">2730. 找到最长的半重复子字符串 - 力扣（LeetCode）</a>
     * <p>
     * 给你一个下标从 0 开始的字符串 s ，这个字符串只包含 0 到 9 的数字字符。
     * 如果一个字符串 t 中至多有一对相邻字符是相等的，那么称这个字符串 t 是 半重复的 。
     * 例如，"0010" 、"002020" 、"0123" 、"2002" 和 "54944" 是半重复字符串，而 "00101022" （相邻的相同数字对是 00 和 22）和 "1101234883" （相邻的相同数字对是 11 和 88）不是半重复字符串。
     * <p>
     * 请你返回 s 中最长 半重复 子字符串 的长度。
     */
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

    /**
     * 给你一个下标从 0 开始的整数数组 nums 和一个 非负 整数 k 。
     * <p>
     * 在一步操作中，你可以执行下述指令：
     * <p>
     * 在范围 [0, nums.length - 1] 中选择一个 此前没有选过 的下标 i 。
     * 将 nums[i] 替换为范围 [nums[i] - k, nums[i] + k] 内的任一整数。
     * 数组的 美丽值 定义为数组中由相等元素组成的最长子序列的长度。
     * <p>
     * 对数组 nums 执行上述操作任意次后，返回数组可能取得的 最大 美丽值。
     * <p>
     * 注意：你 只 能对每个下标执行 一次 此操作。
     * <p>
     * 数组的 子序列 定义是：经由原数组删除一些元素（也可能不删除）得到的一个新数组，且在此过程中剩余元素的顺序不发生改变。
     * <b>数学推导题</b>
     */
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

    /**
     * <a href="https://leetcode.cn/problems/max-consecutive-ones-iii/description/">1004. 最大连续1的个数 III - 力扣（LeetCode）</a>
     * <p>
     * <b>和2730题很像</b>
     * <p>
     * 核心思路：滑动窗口（双指针）
     * 你维护一个窗口 [left, right]，窗口里面最多允许有 k 个 0。
     * 每次 right 往右扩时：
     * 如果遇到 0，你的“额度”就少一个；
     * 当窗口里 0 的数量超过 k，就移动 left 缩小窗口，直到窗口里的 0 再次 ≤ k；
     * 每一步都更新最大窗口长度。
     * 窗口能容纳多少元素，就说明翻转 k 个 0 后最多能把多少 1 连在一起。
     */
    public int longestOnes(int[] nums, int k) {
        int ans = 0, left = 0, cnt = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                cnt++;
            }
            while (cnt > k) {
                if (nums[left] == 0) {
                    cnt--;
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    @Test
    public void test_1004() {
        // 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
        int[] arr = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int ans = longestOnes(arr, 2);
        System.out.println("ans = " + ans);
    }
    /*--------------------------------------------------------------------------------------------------------*/

    /**
     * <a href="https://leetcode.cn/problems/count-subarrays-where-max-element-appears-at-least-k-times/description/">2962. 统计最大元素出现至少 K 次的子数组 - 力扣（LeetCode）</a>
     * 2962. 统计最大元素出现至少 K 次的子数组
     * 给你一个整数数组 nums 和一个正整数 k 。
     * <p>
     * 请你统计有多少满足 「nums 中的最大元素」至少出现 k 次的子数组，并返回满足这一条件的子数组的数目。
     * <p>
     * 子数组是数组中的一个连续元素序列。
     * <p>
     * 输入：nums = [1,3,2,3,3], k = 2
     * 输出：6
     * 解释：包含元素 3 至少 2 次的子数组为：[1,3,2,3]、[3,2,3]、[3,2,3,3]、[2,3,3] 和 [3,3] [1,3,2,3,3]
     */
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();
        long ans = 0;
        int left = 0, cnt = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] == max) cnt++;
            while (cnt >= k) {
                // 关键公式：以 right 为起点往右扩展的子数组都合法
                ans += (n - right);
                if (nums[left] == max) cnt--;
                left++;
            }
        }
        return ans;
    }

    /**
     * 你滑动窗口，right 往右推进。
     * 一旦窗口内最大值出现 ≥ k 次：
     * •	你锁定最小的 left，使 [left, right] 是第一个满足条件的窗口。
     * •	从这之后，任何以 right 或更右结尾的子数组，都满足条件。
     * •	数量就是 n - right。
     * <p>
     * 然后继续向右移动 left，去找下一个满足的窗口。
     * 这就是典型的 “固定 right，移动 left，累计贡献” 模式。
     */
    public long countSubArrays_good(int[] nums, int k) {
        int ans = 0, left = 0, cnt = 0, len = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();
        for (int right = 0; right < len; right++) {
            if (nums[right] == max) {
                cnt++;
            }
            // 满足条件
            while (cnt >= k) {
                if (nums[left] == max) {
                    cnt--;
                }
                left++;
            }
            log.info("l={},r={}", left, right);
            ans += left;
        }
        return ans;
    }

    @Test
    public void test_2692() {
        //        int[] arr = {61, 23, 38, 23, 56, 40, 82, 56, 82, 82, 82, 70, 8, 69, 8, 7, 19, 14, 58, 42, 82, 10, 82, 78, 15, 82};
        int[] arr = {1, 3, 2, 3, 3};
        long cnt = countSubarrays(arr, 2);
        System.out.println("cnt = " + cnt);
        long cnt2 = countSubArrays_good(arr, 2);
        System.out.println(cnt2);
    }

    /**
     * tips: 数组长度一般是: right - left + 1
     * 其他的是: len-right
     */
}
