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
    // ----------------------------------------------------------------------


    // leetcode 162 寻找峰值
    public int findPeakElement(int[] nums) {
        int left = -1;
        int right = nums.length - 1; // 开区间 (-1, n-1)
        while (left + 1 < right) { // 开区间不为空
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid;
            }
            log.info("left={},right={}", left, right);
        }
        return right;
    }

    @Test
    public void testFindPeak() {
        int[] arr = {1, 3, 4, 2};
        int peakElement = findPeakElement(arr);
        System.out.println("peakElement = " + peakElement);
    }

    // ----------------------- 2529 -----------------------------------------------

    public int maximumCount(int[] nums) {
        int positive = findIndexBiggerThan(nums, 0);
        int negative = findIndexBiggerThan(nums, -1);
        log.info("positive={},minus={}", positive, negative);
        return Math.max(negative, nums.length - positive);
    }

    // 大于 target 的数
    public int findIndexBiggerThan(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
            log.info("left={},r={}", left, right);
        }
        return right;
    }

    public int maximumCount2(int[] nums) {
        int positive = findIndexBiggerThan2(nums, 1);
        int negative = findIndexBiggerThan2(nums, 0) - 1;
        log.info("positive={},negative={}", positive, negative);
        return Math.max(negative + 1, nums.length - positive);
    }

    public int findIndexBiggerThan2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // num[left-1] < target
            // num[right+1]>= target
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            log.info("left={},r={}", left, right);
        }
        return left;
    }


    @Test
    public void testFindIndexBiggerThan() {
        // int[] arr = {-1, 0, 0, 5, 20, 66, 1314};
        int[] arr = {-2, -1, -1, 1, 2, 3};
        int indexBiggerThan = maximumCount2(arr);
        System.out.println("indexBiggerThan = " + indexBiggerThan);
    }
    // ----------------------- 2529 end  -----------------------------------------------


}
