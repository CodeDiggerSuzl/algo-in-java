import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class Solution {
    public int mySqrt(int x) {
        int left = 0, right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    @Test
    public void test() {
        int i = mySqrt(2147395599);
        System.out.println(i);
    }
    /*--------------------------------------------------------------------------------------------------------*/

    /**
     * leetcode 5 最长的回文串
     *
     * @param S
     * @return
     */

    public String longestPalindrome(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int ansLeft = 0;
        int ansRight = 0;
        // 奇回文串
        for (int i = 0; i < n; i++) {
            int l = i;
            int r = i;
            while (l >= 0 && r < n && s[l] == s[r]) {
                l--;
                r++;
            }
            // 循环结束后，s[l+1] 到 s[r-1] 是回文串
            if (r - l - 1 > ansRight - ansLeft) {
                ansLeft = l + 1;
                ansRight = r; // 左闭右开区间
            }
        }
        // 偶回文串
        for (int i = 0; i < n - 1; i++) {
            int l = i;
            int r = i + 1;
            while (l >= 0 && r < n && s[l] == s[r]) {
                l--;
                r++;
            }
            if (r - l - 1 > ansRight - ansLeft) {
                ansLeft = l + 1;
                ansRight = r; // 左闭右开区间
            }
        }
        return S.substring(ansLeft, ansRight);
    }

    public String longestPalindromeBetter(String S) {
        char[] s = S.toCharArray();
        int len = s.length;
        int ansLeft = 0;
        int ansRight = 0;
        int N = 2 * len - 1;
        for (int i = 0; i < N; i++) {
            int left = i / 2;
            int right = (i + 1) / 2;
            while (left >= 0 && right < len && s[left] == s[right]) {
                left--;
                right++;
            }
            // 循环结束后:left已经减去1了,right已经+上了1了
            if ((right - 1) - (left + 1) > ansRight - ansLeft) {
                ansRight = right - 1;
                ansLeft = left + 1;
            }
        }
        return S.substring(ansLeft, ansRight + 1);
    }

    @Test
    public void testLongestPalindrome() {
        String s = "cbbcd";
        String res = longestPalindromeBetter(s);
        System.out.println(res);

        String testStr = "012";
        String substring = testStr.substring(0, 2);
        System.out.println(substring);
    }

}