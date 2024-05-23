package leetcode.solution.binary_tree;

import annotion.ToDo;
import leetcode.solution.binary_tree.common.BTreePrinter;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonUtil;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * [name]: 654. 最大二叉树
 * <p>
 * [link]: https://leetcode.cn/problems/maximum-binary-tree/description/
 * <p>
 * [description]:
 * <p>
 * [category]:
 * <p>
 * [solving tips]:
 * <p>
 * <p>
 * [answers]:
 */
@Slf4j
@ToDo(value = "单调栈解法")
// https://leetcode.cn/problems/maximum-binary-tree/solutions/1762400/zhua-wa-mou-si-by-muse-77-myd7/
public class No_654_M_MaximumBTree {


    /**
     * 第三种方法不用 split
     */
    public TreeNode constructMaximumBinaryTree_1(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int maxIdx = getMaxIdx(nums);
        log.debug("maxIdx={},nums={}", maxIdx, nums.length);
        TreeNode left = constructMaximumBinaryTree_1(0 <= maxIdx ? Arrays.copyOfRange(nums, 0, maxIdx) : new int[]{});
        TreeNode right = constructMaximumBinaryTree_1(maxIdx + 1 <= nums.length ? Arrays.copyOfRange(nums, maxIdx + 1, nums.length) : new int[]{});
        return new TreeNode(nums[maxIdx], left, right);
    }

    public int getMaxIdx(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }

        int maxIdx = -1;
        int maxVal = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxVal) {
                maxIdx = i;
                maxVal = nums[i];
            }
        }
        return maxIdx;
    }

    @Test
    public void test_1() {
        int[] arr = {3, 2, 1, 6, 0, 5};
        TreeNode treeNode = constructMaximumBinaryTree_1(arr);
        BTreePrinter.printTree(treeNode);
    }


    /* -----------------------------------------------  ----------------------------------------------- */

    @Test
    public void test_arrSplit() {
        int[] arr = {3, 2, 1, 6, 0, 5};
        int[] ints = Arrays.copyOfRange(arr, 0, 6);
        // 该方法前包后不包
        log.debug("arr split={}", ints);
    }

    @Test
    public void test_2() {
        int[] arr = {3, 2, 1, 6, 0, 5};
        TreeNode root = constructMaximumBinaryTree_2(arr);
        BTreePrinter.printTree(root);

    }

    // 单调栈的解法!
    public TreeNode constructMaximumBinaryTree_2(int[] nums) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        Deque<Integer> deque_val = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            log.debug("num={},deque={}", num, JsonUtil.toJson(deque_val));
            TreeNode node = new TreeNode(num);
            while (!deque.isEmpty()) {
                TreeNode topNode = deque.peekLast();
                if (topNode.val > node.val) {
                    deque.addLast(node);
                    deque_val.addLast(node.val);
                    topNode.right = node;
                    break;  // 注意要 break
                } else {
                    deque.removeLast(); // 出栈操作
                    deque_val.removeLast();
                    node.left = topNode;
                }
            }
            if (deque.isEmpty()) {
                deque.addLast(node);
                deque_val.addLast(node.val);
            }
        }
        log.info("deque last={}", JsonUtil.toJson(deque_val));
        return deque.peek();
    }

    //    作者：爪哇缪斯
    //    链接：https://leetcode.cn/problems/maximum-binary-tree/solutions/1762400/zhua-wa-mou-si-by-muse-77-myd7/
    //    来源：力扣（LeetCode）
    //    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    //

    /* ----------------------------------------------- method3 ----------------------------------------------- */
    /* 主函数 */
    TreeNode constructMaximumBinaryTree_3(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    // 定义：将 nums[lo..hi] 构造成符合条件的树，返回根节点
    TreeNode build(int[] nums, int lo, int hi) {
        // base case
        if (lo > hi) {
            return null;
        }

        // 找到数组中的最大值和对应的索引
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }

        // 先构造出根节点
        TreeNode root = new TreeNode(maxVal);
        // 递归调用构造左右子树
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);

        return root;
    }
}

