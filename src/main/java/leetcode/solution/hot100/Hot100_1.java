package leetcode.solution.hot100;

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

}
