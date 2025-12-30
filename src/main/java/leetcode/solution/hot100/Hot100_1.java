package leetcode.solution.hot100;

import annotion.PASS;
import annotion.Stocked;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonUtil;

import java.util.*;

@Slf4j
public class Hot100_1 {
    /**
     * <a href="https://leetcode.cn/problems/two-sum/?envType=study-plan-v2&envId=top-100-liked">1.tow-sum</a>
     *
     */
    @PASS
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int idx = 0; idx < nums.length; idx++) {
            int val = nums[idx];
            if (idxMap.get(target - val) != null) {
                return new int[]{idx, idxMap.get(target - val)};
            }
            idxMap.put(val, idx);
        }
        return null;
    }

    @Test
    public void test_1() {
        int[] arr = {2, 7, 11, 15};
        int[] ans = twoSum(arr, 9);
        log.info("No.22-sum = {}", ans);

    }
    /* -----------------------------------------NO.1----------------------------------------------*/

    /**
     * <a href="https://leetcode.cn/problems/group-anagrams/?envType=study-plan-v2&envId=top-100-liked">49.字母异位词分组</a>
     */
    @PASS
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sorted = new String(charArray);
            List<String> list = map.getOrDefault(sorted, new ArrayList<>());
            list.add(str);
            map.put(sorted, list);
        }
        return new ArrayList<>(map.values());
    }

    @Test
    public void test_49() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> list = groupAnagrams(strs);
        log.info("No.49 result={}", JsonUtil.toJson(list));
    }

    /*--------------------------------------------------------------------------------------------------------*/

    /**
     * No.128
     */
    @Stocked
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        Arrays.sort(nums);
        int ans = 1;
        int currLen = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] == nums[i]) {
                continue;
            }
            if (nums[i] == nums[i - 1] + 1) {
                currLen++;
                ans = Math.max(currLen, ans);
            } else {
                currLen = 1;
            }
        }
        return ans;
    }

    @Test
    public void test_128() {
        int[] arr = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int len = longestConsecutive(arr);
        System.out.println("len = " + len);
    }


    /* ---------------------------------------------------------------------------------------*/

    /**
     * <a href="https://leetcode.cn/problems/container-with-most-water/description/?envType=study-plan-v2&envId=top-100-liked">...</a>
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * 说明：你不能倾斜容器。
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        while (left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];
            int area = Math.min(leftHeight, rightHeight) * (right - left);
            ans = Math.max(area, ans);
            if (leftHeight < rightHeight) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }


    /* ---------------------------------------------------------------------------------------*/

    /**
     * <a href="https://leetcode.cn/problems/trapping-rain-water/description/">42. 接雨水 - 力扣（LeetCode）</a>
     */
    public int trap(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int preMax = 0, sufMax = 0;
        while (left < right) {
            preMax = Math.max(preMax, height[left]);
            sufMax = Math.max(sufMax, height[right]);

            if (sufMax < preMax) {
                ans += sufMax - height[right];
                right--;
            } else {
                ans += preMax - height[left];
                left++;
            }
        }
        return ans;
    }

    @Test
    public void test_42() {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int trap = trap(arr);
        System.out.println("trap = " + trap);

    }


}
