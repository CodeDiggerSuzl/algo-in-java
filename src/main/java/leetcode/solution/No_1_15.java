package leetcode.solution;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// No.15
// url: https://leetcode.cn/problems/3sum/description/
// time: 90 min almost
// tips: sort, remove duplicate, two-pointers
// tag: K-Sum,two pointers

public class No_1_15 {

    @Test
    public void test() {
        int[] arr = {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};// 1, -1, 0, 1
        System.out.println(threeSum2(arr));
    }

    //
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // MUST SORT FIRST !!!
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
        List<List<Integer>> ans = new ArrayList<>();
        // int k = nums.length - 1; // k will be : 10,8,7,7 put k in here is not right ❌
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // remove duplicate of 'i'
            int k = nums.length - 1; // k will always be 10 !
            int j = i + 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    ans.add(List.of(nums[i], nums[j], nums[k]));
                    k--;
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (k > j && nums[k] == nums[k + 1]) k--;
                } else if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                    while (k > j && nums[k] == nums[k + 1]) k--;  //虽然下一个值小于本次的值但是也要判断是否下一个值的和是否为 0,本题是等值,所以这行代码要注释掉
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) j++;
                }
            }
        }
        return ans;
    }
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int l = 0; l < nums.length; l++) {
            if (l > 0 && nums[l] == nums[l - 1])
                continue;
            int m = l + 1, r = nums.length - 1;
            while (m < r) {
                int lv = nums[l];
                int mv = nums[m];
                int rv = nums[r];
                int sum = lv + mv + rv;
                if (sum > 0) {
                    r--;
                    while (m < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                }
                if (sum < 0) {
                    m++;
                    while (m < r && nums[m] == nums[m - 1]) {
                        m++;
                    }
                }

                if (sum == 0) {
                    ans.add(List.of(lv, mv, rv));
                    m++;
                    r--;
                    while (m < r && nums[m] == nums[m - 1]) {
                        m++;
                    }
                    while (m < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                }
            }

        }
        return ans;
    }

}

