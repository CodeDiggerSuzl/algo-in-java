package leetcode.solution.binary_tree;

import annotion.LongTime;
import leetcode.solution.binary_tree.util.BinaryTreeBuilder;
import org.junit.Test;

/**
 * 二叉树的最小深度
 * <a href="https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/">111. 二叉树的最小深度 - 力扣（LeetCode）</a>
 * <p>
 * <p>
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明：叶子节点是指没有子节点的节点。
 */
@LongTime
public class No_111_MinDepth {

    /**
     思路:
     🌟和最大深度的区别再次,题目说的叶子节点是没有左右子节点的节点
     当为叶子节点的时候再更新最小值
     */

    /**
     * 使用遍历的方式做
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, 1);
        return ans;
    }

    private int ans = Integer.MAX_VALUE;

    public void dfs(TreeNode root, int cnt) {
        if (root == null) {
            return;
        }
        // 🌟和最大深度的区别再次,题目说的叶子节点是没有左右子节点的节点
        // 当为叶子节点的时候再更新最小值
        if (root.left == null && root.right == null) {
            if (cnt < ans) {
                ans = cnt;
            }
            return;
        }
        System.out.println("root.val = " + root.val);
        System.out.println("cnt = " + cnt);
        cnt++;

        dfs(root.left, cnt);
        dfs(root.right, cnt);

    }

    /**
     * 递归的方式没有考虑出来:
     * 灵神答案:<a href="https://leetcode.cn/problems/minimum-depth-of-binary-tree/solutions/2730984/liang-chong-fang-fa-zi-ding-xiang-xia-zi-0sxz">...</a>
     * <p>
     * <p>
     * 原因是边界条件仅仅考虑到了
     * 1. 当节点为 null 的情况
     * 2. 当节点为叶子节点的情况 node.left == null && node.right == null 的情况
     */
    @LongTime
    public int minDepth_2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        int left = minDepth_2(root.left);
        int right = minDepth_2(root.right);

        return Math.min(left, right) + 1;
    }

    // 答案:https://leetcode.cn/problems/minimum-depth-of-binary-tree/solutions/2730984/liang-chong-fang-fa-zi-ding-xiang-xia-zi-0sxz


    @Test
    public void testDfs() {
        Integer[] arr = {3, 9, 20, null, null, 15, 7};
        //        Integer[] arr = {2, null, 3, null, 4, null, 5, null, 6};
        TreeNode treeNode = BinaryTreeBuilder.buildTree(arr);
        System.out.println("minDepth_2(treeNode) = " + minDepth_2(treeNode));
    }


}
