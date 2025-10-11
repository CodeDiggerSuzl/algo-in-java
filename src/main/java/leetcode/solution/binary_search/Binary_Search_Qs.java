package leetcode.solution.binary_search;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Binary_Search_Qs {

    /**
     *https://leetcode.cn/problems/search-insert-position/description/?envType=study-plan-v2&envId=top-interview-150
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 请必须使用时间复杂度为 O(log n) 的算法。
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0, end = nums.length - 1;
        if (nums[start] > target) return 0;
        if (nums[end] < target) return end + 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            int value = nums[mid];
            if (value == target) {
                return mid;
            }
            if (value > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
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


    @Test
    public void test() {
        int[] arr = {1, 3, 5, 6};
        int i = searchInsert(arr, 7);
        System.out.println(i);

    }


}
