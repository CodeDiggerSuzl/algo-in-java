package leetcode.solution.binary_search;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


@Slf4j
public class BinarySearchQuestions {

    /**
     * 278. First Bad Version
     * https://leetcode.cn/problems/first-bad-version/description/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
     * <p>
     * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
     * <p>
     * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
     * <p>
     * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
     * <p>
     * The isBadVersion API is defined in the parent class VersionControl.
     * boolean isBadVersion(int version);
     */
    boolean isBadVersion(int version) {
        return version == 4;
    }

    // 1 2 3 4
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {
            System.out.println("left = " + left);
            System.out.println("right = " + right);
            System.out.println("----");
            int mid = (right + left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    @Test
    public void test() {
        int i = firstBadVersion(5);
        System.out.println("i = " + i);
    }
}
