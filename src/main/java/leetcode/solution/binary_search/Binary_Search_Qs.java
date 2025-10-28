package leetcode.solution.binary_search;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Binary_Search_Qs {

    /**
     *<a href="https://leetcode.cn/problems/search-insert-position/description/?envType=study-plan-v2&envId=top-interview-150">...</a>
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 请必须使用时间复杂度为 O(log n) 的算法。
     */
    public int binSearch3(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            log.info("left={},right={},mid={}", left, right, mid);
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
            log.info("after left={},right={},mid={}", left, right, mid);
        }
        return left + 1;
    }

    public int binSearch1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public int binSearch2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }


    @Test
    public void test() {
        int[] arr = {1, 3, 5, 6};
        int i = binSearch3(arr, 5);
        System.out.println(i);
    }

    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < mountain.length - 1; i++) {
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]) {
                res.add(i);
            }
        }
        return res;
    }


    /**
     * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     *
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     *
     * 示例 1：
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     */
    public int[] searchRange(int[] nums, int target) {
        int search = search(nums, target);
        if (search == nums.length || nums[search] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{search, search(nums, target + 1) - 1};
    }


    @Test
    public void test34() {
        int[] arr = {-1, 0, 3, 5, 9, 12};
        int search = search(arr, 2);
        System.out.println("search = " + search);
        // System.out.println("searchRange(arr,8) = " + Arrays.toString(searchRange(arr, 8)));
    }


    public int search(int[] nums, int target) {
        int left = -1, rigth = nums.length;
        while (left + 1 < rigth) {
            int mid = (left + rigth) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                rigth = mid;
            }
        }
        return rigth;
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int result = find(letters, (char) (target + 1));
        if (result == letters.length /*|| letters[result] != target + 1*/) {
            return letters[0];
        }
        return letters[result];
    }

    public int find(char[] letters, char target) {
        int left = -1, right = letters.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    @Test
    public void test744() {
        char[] arr = {'c', 'f', 'j'};
        char c = nextGreatestLetter(arr, 'c');
        System.out.println("c = " + c);
    }

    /**
     * 2529. 正整数和负整数的最大计数
     * 简单
     * 相关标签
     * premium lock icon
     * 相关企业
     * 提示
     * 给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。
     * 换句话讲，如果 nums 中正整数的数目是 pos ，而负整数的数目是 neg ，返回 pos 和 neg二者中的最大值。
     * 注意：0 既不是正整数也不是负整数。
     *<a href="https://leetcode.cn/problems/maximum-count-of-positive-integer-and-negative-integer/description/">...</a>
     */
    public int maximumCount(int[] nums) {
        int plus = bsSearch(nums, 1); // >= 1 或者说大于 0 的数
        int minus = bsSearch(nums, 0) - 1; // 小于 0 的数
        log.info("plus={},minus={}", plus, minus);
        return Math.max(nums.length - plus, minus + 1);
    }

    public int bsSearch(int[] num, int target) {
        int left = -1, right = num.length;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (num[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    @Test
    public void test2529() {
        int[] arr = {5, 20, 66, 1314};
        int result = maximumCount(arr);
        log.info("input={} result={}", Arrays.toString(arr), result);
    }


    /**
     *

     2300. 咒语和药水的成功对数
     中等
     相关标签
     premium lock icon
     相关企业
     提示
     给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，其中 spells[i] 表示第 i 个咒语的能量强度，potions[j] 表示第 j 瓶药水的能量强度。

     同时给你一个整数 success 。一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。

     请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int[] ans = new int[spells.length];
        for (int i = 0; i < spells.length; i++) {
            int curr = spells[i];
            // int target = (int) (success / curr);
            long target = ((success + curr - 1) / curr);
            log.info("success={},curr={},target={}", success, curr, target);
            int idx = bsSearchPotion(potions, target);
            log.info("search in {} target={},result={}", Arrays.toString(potions), target, idx);
            if (idx == potions.length) {
                ans[i] = 0;
            } else {
                ans[i] = potions.length - idx;
            }
        }
        return ans;
    }

    public int bsSearchPotion(int[] potions, long target) {
        int left = 0, right = potions.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) potions[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    @Test
    public void test2300() {
        int[] spells = {3, 1, 2};
        int[] potions = {8, 5, 8};
        long success = 16;

        int[] ans = successfulPairs(spells, potions, success);
        log.info("result={}", Arrays.toString(ans));
    }

    /**
     * [69. x 的平方根 - 力扣（LeetCode）](https://leetcode.cn/problems/sqrtx/description/)
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        long left = 0, right = (long) x + 1;
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (mid * mid <= (long) x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return (int) left;
    }

    public int mySqrt_2(int x) {
        int left = 0, right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid * mid <= x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public int mySqrt_3(int x) {
        int L = 0, R = x;
        while (L < R) {
            int mid = L + (R - L) / 2;
            if (mid * mid <= x) {
                L = mid + 1;
            } else {
                R = mid;
            }
        }
        return L - 1;
    }

    @Test
    public void test69() {
        System.out.println("mySqrt(1) = " + mySqrt_3(1));
        System.out.println("mySqrt(0) = " + mySqrt_3(0));
        System.out.println("mySqrt(16) = " + mySqrt_3(16));
        System.out.println("mySqrt(32) = " + mySqrt_3(32));
        System.out.println("mySqrt(2147483647) = " + mySqrt_3(2147483647));
    }

    /**
     * https://leetcode.cn/problems/search-in-rotated-sorted-array/description/
     * 33. 搜索旋转排序数组
     * 相关企业
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     *
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。
     *
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     *
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     *   /\
     *  /  \
     * /    \
     */
    public int search_33(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            // 左边有序
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
                // 右边有序
            } else {
                if (nums[mid] <= target && target < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    @Test
    public void test33() {
        int[] arr = {5, 1, 3};
        int ans = search_33(arr, 3);
        System.out.println("ans = " + ans);
    }

    /**
     * 162. 寻找峰值
     * 峰值元素是指其值严格大于左右相邻值的元素。
     * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     * 你可以假设 nums[-1] = nums[n] = -∞ 。
     * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     * 示例 1：
     *
     * 输入：nums = [1,2,3,1]
     * 输出：2
     * 解释：3 是峰值元素，你的函数应该返回其索引 2。
     * 示例 2：
     *
     * 输入：nums = [1,2,1,3,5,6,4]
     * 输出：1 或 5
     * 解释：你的函数可以返回索引 1，其峰值元素为 2；
     *      或者返回索引 5， 其峰值元素为 6。
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 1000
     * -2^31 <= nums[i] <= 2^31 - 1
     * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
     */
    public int findPeakElement(int[] nums) {
        int left = -1, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= nums[mid + 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public int findPeakElement_2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    @Test
    public void test_162() {
        int[] arr = {1, 2};
        int peakElement = findPeakElement_2(arr);
        System.out.println("peakElement = " + peakElement);
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     * 中等
     * 相关标签
     * premium lock icon
     * 相关企业
     * 提示
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     *
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     *
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * @param nums
     * @return
     */
    public int findMin_153(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[right] < nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    @Test
    public void test_153() {
        int[] arr = {3, 4, 5, 1, 2};
        int peakElement = findMin(arr);
        System.out.println("peakElement = " + peakElement);
    }

    public int findMin(int[] nums) {
        int left = -1, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[nums.length - 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[right - 1 < 0 ? right : right - 1];
    }

    /**
     *74. 搜索二维矩阵
     * 相关企业
     * 给你一个满足下述两条属性的 m x n 整数矩阵：
     *
     * 每行中的整数从左到右按非严格递增顺序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
     *
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] m : matrix) {
            int length = m.length;
            if (length == 0) {
                continue;
            }
            boolean find = m[0] <= target && m[length - 1] >= target;
            if (!find) {
                continue;
            }
            int ans = bsearchMatrix(m, target);
            return ans != -1;

        }
        return false;
    }

    public int bsearchMatrix(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return -1;
    }

    @Test
    public void test74() {
        int[][] arr = {{1, 3}};
        boolean b = searchMatrix(arr, 3);
        System.out.println("b = " + b);

    }


}
