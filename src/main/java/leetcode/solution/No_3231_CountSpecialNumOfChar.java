package leetcode.solution;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * [name]: 3121. Count the Number of Special Characters II
 * <p>
 * [link]: https://leetcode.cn/problems/count-the-number-of-special-characters-ii/
 */
@Slf4j
public class No_3231_CountSpecialNumOfChar {

    public int numberOfSpecialChars(String word) {
        int cnt = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            String str = String.valueOf(c);
            int lowIdx = word.indexOf(str.toLowerCase());
            int upIndex = word.indexOf(str.toUpperCase());
            if (upIndex > 0 && lowIdx > 0) {
                if (lowIdx < upIndex) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public int numberOfSpecialChars2(String word) {
        int cnt = 0;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int lowIdx = word.indexOf(ch);
            int upIdx = word.indexOf(Character.toUpperCase(ch));
            if (lowIdx >= 0 && upIdx >= 0 && lowIdx < upIdx) {
                cnt++;
            }
        }
        return cnt;
    }


    @Test
    public void test() {
        String testString = "aaAbcBC";
        int cnt = numberOfSpecialChars(testString);
        assert cnt == 3;
    }
}
