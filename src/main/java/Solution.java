import org.junit.Test;

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

}