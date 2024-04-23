package leetcode.solution.k_sum;

import java.util.Arrays;

// No.16
// url: https://leetcode.cn/problems/3sum-closest/description/
// time: 90 min almost
// tips: sort, remove duplicate, two-pointers
// tag: K-Sum,two pointers
public class No_16_ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int gap = Integer.MAX_VALUE;
        int ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int currGap = Math.abs(target - sum);
                if (currGap < gap) {
                    gap = currGap;
                    ans = sum;
                }
                if (sum > target) {
                    --k;
                }
                if (sum < target) {
                    ++j;
                }
                if (sum == target) {
                    return ans;
                }
            }
        }
        return ans;
    }

}
