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

    // 520
    public boolean detectCapitalUseElegant(String word) {
        // 统计某个字符的操作 全部统计大写字符或者消息字符在和字符的长度作比较
        int upperCnt = 0;
        int length = word.length();
        for (int i = 0; i < length; i++) {
            char curr = word.charAt(i);
            if (Character.isUpperCase(curr)) {
                upperCnt++;
            }
        }
        return upperCnt == length || upperCnt == 0 || (upperCnt == 1 && Character.isUpperCase(word.charAt(0)));
    }

    /**
     *2129. 将标题首字母大写
     * 给你一个字符串 title ，它由单个空格连接一个或多个单词组成，每个单词都只包含英文字母。请你按以下规则将每个单词的首字母
     * 大写 ：
     *
     * 如果单词的长度为 1 或者 2 ，所有字母变成小写。
     * 否则，将单词首字母大写，剩余字母变成小写。
     * 请你返回 大写后 的 title 。
     */
    public String capitalizeTitle(String title) {
        String[] arr = title.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            String word = arr[i];
            if (word.length() == 1 || word.length() == 2) {
                sb.append(word.toLowerCase());
            } else {
                String lowerCase = word.toLowerCase();
                sb.append(Character.toUpperCase(lowerCase.charAt(0)));
                sb.append(lowerCase.substring(1));
            }
            if (i != arr.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
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

