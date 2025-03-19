package leetcode.solution.sliding_window;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * [name]:3. 无重复字符的最长子串
 * <p>
 * [link]:https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 */
@Slf4j
public class No_3_M_Longest_without_repeating_characters {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int left = 0, ans = 0;
        Map<Character, Integer> idxMap = new HashMap<>();
        for (int right = 0; right < len; right++) {
            char currChar = s.charAt(right);
            Integer preIdx = idxMap.get(currChar);
            if (preIdx != null && preIdx >= left) {
                left = preIdx + 1;
            }
            idxMap.put(currChar, right);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public int method2(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int ans = 0, left = 0;
        int[] memo = new int[256]; // see ASCII TABLE
        for (int right = 0; right < s.length(); right++) {
            char currChar = s.charAt(right);
            memo[currChar]++;
            // 核心在下面的代码
            while (memo[currChar] > 1) {
                memo[s.charAt(left)]--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public int method3_whth_map(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int ans = 0, left = 0;
        HashMap<Character, Integer> memo = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char currChar = s.charAt(right);
            memo.put(currChar, memo.getOrDefault(currChar, 0) + 1);
            while (memo.getOrDefault(currChar, 0) > 1) {
                memo.put(s.charAt(left), memo.get(s.charAt(left)) - 1);
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    @Test
    public void test() {
        String testStr = "pwwkew";
        int ans = method3_whth_map(testStr);
        assert ans == 3;
    }
}
