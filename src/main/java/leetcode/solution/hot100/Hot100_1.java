package leetcode.solution.hot100;

import annotion.PASS;
import annotion.Stocked;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
