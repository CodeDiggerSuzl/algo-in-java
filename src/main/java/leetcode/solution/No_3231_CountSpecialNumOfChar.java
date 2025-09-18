package leetcode.solution;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * [name]: 3121. Count the Number of Special Characters II
 * <p>
 * [link]: https://leetcode.cn/problems/count-the-number-of-special-characters-ii/
 */
@Slf4j
public class No_3231_CountSpecialNumOfChar {

    public int numberOfSpecialChars(String word) {
        if (word == null || word.isEmpty()) {
            return 0;
        }
        int cnt = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isLowerCase(c)) {
                map.put(c, i);
                continue;
            }
            if (!map.containsKey(c)) {
                map.put(c, i);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Character key = entry.getKey();
            if (Character.isUpperCase(key)) {
                continue;
            }
            Character upperCase = Character.toUpperCase(key);

            Integer upIdx = map.get(upperCase);
            if (upIdx == null) {
                continue;
            }
            if (upIdx > entry.getValue()) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * https://leetcode.cn/problems/count-the-number-of-special-characters-i/description/
     * 3120
     */
    public int numberOfSpecialChars_1(String word) {
        if (word == null || word.isEmpty()) return 0;
        int cnt = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (!set.add(curr)) continue;
            char upperCase = Character.toUpperCase(curr);
            int upIdx = word.indexOf(upperCase);
            if (Character.isLowerCase(curr) && upIdx > -1) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 520
     *https://leetcode.cn/problems/detect-capital/description/
     * 我们定义，在以下情况时，单词的大写用法是正确的：
     *
     * 全部字母都是大写，比如 "USA" 。
     * 所有字母都不是大写，比如 "leetcode" 。
     * 只有首字母大写， 比如 "Google" 。
     * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
     */
    public boolean detectCapitalUse(String word) {
        if (word.length() == 1) {
            return true;
        }
        char firstChar = word.charAt(0);
        Set<Character> set = new HashSet<>();
        for (int i = 1; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                set.add('A');
            } else {
                set.add('a');
            }
        }
        if (set.size() > 1) {
            return false;
        }
        Character c = new ArrayList<>(set).get(0);
        return !Character.isLowerCase(firstChar) || !Character.isUpperCase(c);
    }

    public boolean detectCapitalUseElegant(String word) {

    }

    @Test
    public void test() {
        //        String s = "Cc";
        //        System.out.println("numberOfSpecialChars(s) = " + numberOfSpecialChars_1(s));


        String s = "FlaG";
        boolean b = detectCapitalUse(s);
        System.out.println("b = " + b);


    }


}

