import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
public class Temp {


    //  输入: s = "pwwkew"
    //  输出: 3
    //  解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
    //     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
    public static int lengthOfLongestSubstring(String s) {
        int res = 0, left = 0;
        Map<Character, Integer> cntMap = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            cntMap.put(c, cntMap.getOrDefault(c, 0) + 1);
            while (cntMap.get(c) > 1) {
                char leftChar = s.charAt(left);
                cntMap.put(leftChar, cntMap.get(leftChar) - 1);
                left++;
            }
            System.out.println("s.substring(left,right) = " + s.substring(left, right + 1));
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public static int lenOfLongestSubstring(String s) {
        int left = 0, ans = 0;
        HashMap<Character, Integer> idxMap = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            Integer preIdx = idxMap.get(c);
            if (preIdx != null && preIdx >= left) {
                if (preIdx == left) {
                    log.info("currChar={}", c);
                }
                left = preIdx + 1;
            }
            idxMap.put(c, right);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = lenOfLongestSubstring("pwwkew");
        System.out.println(i);
    }

    /*--------------------------------------------------------------------------------------------------------------*/
    @Test
    public void test() {
        int[] ints = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] result = maxSlidingWindowWithPQueue(ints, 3);
        System.out.println("Arrays.toString(result) = " + Arrays.toString(result));
    }


    /**
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * <p>
     * 返回 滑动窗口中的最大值 。
     * <p>
     * https://leetcode.cn/problems/sliding-window-maximum/description/
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7      3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     * 示例 2：
     */

    public int[] maxSlidingWindow(int[] nums, int k) {
        ArrayDeque<Integer> window = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<Integer>(3);

        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if (i <= k - 1) {
                window.addLast(curr);
                if (i == k - 1) {
                    ans.add(Collections.max(window));
                    System.out.println("window.toString()-- = " + window);
                }
                //  1  3  -1  -3  5 [3  6  7]
            } else {
                window.removeFirst();
                System.out.println("window remove first= " + window);

                window.addLast(curr);
                System.out.println("window add last= " + window);

                System.out.println("window= " + window);
                ans.add(Collections.max(window)); // !!!! 这个操作很慢导致整个方法复杂度变成 O(N*k) 很慢

            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 你这个实现超时的根本原因是：优先队列（最大堆）的 remove(Object o) 操作在 Java 中是 O(n) 时间复杂度，而你的滑动窗口每移动一步，都要调用一次 queue.remove(nums[i - k])，这就导致整体时间复杂度退化为 O(nk)，在大数据量下会超时。
     * <p>
     * ⸻
     * <p>
     * 举个例子：
     * <p>
     * 假设 nums.length = 10^5，k = 5000，你要循环 10^5 次，每次还要 O(k) 删除一个元素，总共操作量接近 10^8~10^9，肯定爆炸。
     * <p>
     * ⸻
     * <p>
     * 正确高效的思路是用 单调队列（双端队列），它能把时间复杂度降到 O(n)。
     * <p>
     * 用 Deque<Integer> 存储元素的下标，保证队列中元素对应的值是从大到小单调递减的，这样队首永远是当前窗口的最大值。
     * <p>
     * ⸻
     * <p>
     * 单调队列高效写法（Java）：
     * <p>
     * class leetcode.solution.tow_pointer.Solution {
     * public int[] maxSlidingWindow(int[] nums, int k) {
     * int n = nums.length;
     * int[] res = new int[n - k + 1];
     * Deque<Integer> deque = new ArrayDeque<>();  // 存下标
     * <p>
     * for (int i = 0; i < n; i++) {
     * // 移除不在窗口范围内的下标
     * while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
     * deque.pollFirst();
     * }
     * <p>
     * // 保持队列单调递减，移除队尾比当前小的元素
     * while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
     * deque.pollLast();
     * }
     * <p>
     * deque.offerLast(i);  // 加入当前元素下标
     * <p>
     * // 当窗口形成后，记录最大值
     * if (i >= k - 1) {
     * res[i - k + 1] = nums[deque.peekFirst()];
     * }
     * }
     * return res;
     * }
     * }
     * <p>
     * <p>
     * <p>
     * ⸻
     * <p>
     * 时间复杂度分析：
     * •	每个元素最多入队、出队一次 → O(n)
     * •	无需堆的 remove 操作，性能稳得一批
     * <p>
     * ⸻
     * <p>
     * 总结：
     * <p>
     * 你的写法的问题在于 PriorityQueue.remove() 是 O(n)，无法高效支持滑动窗口。
     * 👉 替换成单调队列，时间复杂度从 O(nk) 降到 O(n)，直接飞升。
     * <p>
     * 需要我帮你用 Go 或 Python 实现吗？或者你想用堆优化一下不超时的写法也可以继续探讨~
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowWithPQueue(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        // 最大堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if (i <= k - 1) {
                queue.add(curr);
                System.out.println("window.toString()-- = " + queue);

                if (i == k - 1) {
                    ans[0] = queue.peek();
                    System.out.println("window.toString()-- = " + queue);
                }
                //  1  3  -1  -3  5 [3  6  7]
            } else {
                System.out.println("window.toString()-- = " + queue);
                queue.remove(nums[i - k]); // 写法的问题在于 PriorityQueue.remove() 是 O(n)，无法高效支持滑动窗口。👉 替换成单调队列，时间复杂度从 O(nk) 降到 O(n)，直接飞升。
                queue.add(curr);
                ans[i - k + 1] = queue.peek();
            }
        }
        return ans;
    }

    public int[] maxSlidingWindowWithDeque(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            if (i >= k - 1) {
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }

    @Test
    public void testDeque() {
        int[] ints = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] result = maxSlidingWindowWithDeque(ints, 3);
        System.out.println("Arrays.toString(result) = " + Arrays.toString(result));

    }

}
