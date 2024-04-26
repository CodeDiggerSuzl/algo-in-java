package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.BTreePrinter;
import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import org.junit.Test;

/*
 * 题目描述:
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum。
 * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 * 叶子节点 是指没有子节点的节点。
 *
 * URL : https://leetcode.cn/problems/path-sum/description/
 *
 * 类似题目: 377. 组合总和 Ⅳ: https://leetcode.cn/problems/combination-sum-iv/description/
 *
 * 我的题解:https://leetcode.cn/problems/path-sum/solutions/2757415/bian-li-he-di-gui-lai-shi-xian-by-suz1-i6ua
 *
 */
public class No_112_SumOfPath {

    /**
     * 使用递归的方式
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        boolean leftOk = hasPathSum(root.left, targetSum - root.val);
        boolean rightOk = hasPathSum(root.right, targetSum - root.val);
        return leftOk || rightOk;
    }


    /**
     * 使用遍历的方式
     */
    private boolean findTarget = false;

    public boolean hasPathSum_2(TreeNode root, int targetSum) {
        findTarget(root, targetSum);
        return findTarget;
    }

    public void findTarget(TreeNode root, int target) {
        if (root == null || findTarget) { // 已经发现了存在的节点
            return;
        }
        if (root.left == null && root.right == null) {
            if (root.val == target) {
                findTarget = true;
            }
        }
        findTarget(root.left, target - root.val);
        findTarget(root.right, target - root.val);
    }

    @Test
    public void test() {
        //Integer[] arr = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1};
        Integer[] arr = {1, 2, 3};
        TreeNode treeNode = BTreeUtil.buildTree(arr);

        System.out.println("hasPathSum_2(treeNode,22) = " + hasPathSum_2(treeNode, 5));
    }


}
