import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class Solution {

    // 15. 三数之和 https://leetcode.cn/problems/3sum/
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int target = 0;
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    ans.add(List.of(nums[i], nums[left], nums[right]));
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return ans;
    }

    @Test
    public void test15() {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(arr);
        log.info(JsonUtil.toJson(lists));
    }

    // 2824. 统计和小于目标的下标对数目 - 力扣（LeetCode）
    // https://leetcode.cn/problems/count-pairs-whose-sum-is-less-than-target/description/
    // 注意题目中 "对数"的算法,当 leftVal + rightVal == target 的时候,那么 right-left 就是小于 0 的对数
    public int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);
        int right = nums.size() - 1;
        int left = 0;
        int ans = 0;
        while (left < right) {
            int leftVal = nums.get(left);
            int rightVal = nums.get(right);
            if (leftVal + rightVal < target) {
                ans += (right - left);
                left++;
            } else if (rightVal + leftVal >= target) {
                right--;
            }
        }
        return ans;
    }

    @Test
    public void test2824() {
        List<Integer> nums = Lists.newArrayList(-6, 2, 5, -2, -7, -1, 3);
        int i = countPairs(nums, -2);
        System.out.println("i = " + i);
    }

    /* -----------------------------------------------  ----------------------------------------------- */

    public int threeSumClosest(int[] nums, int target) {
        Integer gap = Integer.MAX_VALUE;
        int length = nums.length;
        Integer ans = Integer.MAX_VALUE;
        Arrays.sort(nums);

        for (int i = 0; i < length - 2; i++) {
            int left = i + 1, right = length - 1;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int currGap = Math.abs(sum - target);
                if (currGap < gap) {
                    gap = currGap;
                    ans = sum;
                }
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }

    @Test
    public void test_16() {
        int[] arr = {0, 0, 0};
        int ans = threeSumClosest(arr, 1);
        log.info("16 ans={}", ans);
    }

    /* -----------------------------------------------  ----------------------------------------------- */

    // [18. 四数之和 - 力扣（LeetCode）](https://leetcode.cn/problems/4sum/description/)
    public List<List<Integer>> fourSum(int[] nums, int target) {

    }

    @Test
    public void test_18() {

    }
    /* -----------------------------------------------  ----------------------------------------------- */
}
