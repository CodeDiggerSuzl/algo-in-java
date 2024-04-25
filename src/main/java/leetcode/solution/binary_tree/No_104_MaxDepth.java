package leetcode.solution.binary_tree;

import org.junit.Test;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/">...</a>*
 * <p>
 * 求一个数的最大深度
 */
public class No_104_MaxDepth {


    /**
     * 使用递归的方式
     * 拆分成子树的问题
     */
    public int maxDepth_1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth_1(root.left);
        int right = maxDepth_1(root.right);
        return Math.max(left, right) + 1; // 左子树和右子树的较大值加上自己
    }


    int ans = 0;
    /**
     * 使用遍历的方式
     *
     */
    public int maxDepth_2(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    public void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        level++;
        System.out.println("root.val = " + root.val);
        System.out.println("level = " + level);
        if (level > ans) {
            ans = level;
        }
        dfs(root.left, level);
        dfs(root.right, level);
    }

    @Test
    public void testDfs() {
        Integer[] arr = {3, 9, 20, null, null, 15, 7};
        TreeNode treeNode = BinaryTreeBuilder.buildTree(arr);
        System.out.println("maxDepth_2(treeNode) = " + maxDepth_2(treeNode));
    }




}
