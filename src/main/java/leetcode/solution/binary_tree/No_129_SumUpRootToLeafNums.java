package leetcode.solution.binary_tree;

import annotion.SimilarTo;
import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * [name]: 129. 求根节点到叶节点数字之和
 * <p>
 * [link]: https://leetcode.cn/problems/sum-root-to-leaf-numbers/description/
 * <p>
 * [description]:给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * <p>
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * <p>
 * 叶节点 是指没有子节点的节点。
 * <p>
 * [category]: 二叉树
 * <p>
 * [solving tips]: 1. 注意叶子节点的定义 2. 当前节点的值需要拿到上个节点的值.
 */
@SimilarTo({"113", "112"})
@Slf4j
public class No_129_SumUpRootToLeafNums {


    /*--------------------------------------------------------------------------------------------------------------*/


    public int sumNumbers(TreeNode root) {
        dfs(root, 0, 0);
        return ans;

    }


    int ans = 0;

    /**
     * 遍历的方式
     * 根节点作为最大位
     */
    public void dfs(TreeNode node, int prevValue) {
        if (node == null) {
            return;
        }
        int currVal = prevValue * 10 + node.val;
        if (node.left == null && node.right == null) {
            ans += currVal;
        }
        log.info("node={},prevVal={},currVal={},ans={}", node.val, prevValue, currVal, ans);
        dfs(node.left, currVal);
        dfs(node.right, currVal);
    }

    /*----------------------------------------------方法 2----------------------------------------------------------------*/


    public int sumNumbers_2(TreeNode root) {
        return dfs_method_2(root, 0);

    }

    /**
     * 带返回值的方法
     */
    public int dfs_method_2(TreeNode root, int prevVal) {
        if (root == null) {
            return 0;
        }
        int currVal = 10 * prevVal + root.val;
        // 如果是叶子节点,就需要返回结果
        if (root.left == null && root.right == null) {
            return currVal;
        }
        // 否则返回左右节点值和
        int left = dfs_method_2(root.left, currVal);
        int right = dfs_method_2(root.right, currVal);
        return left + right;
    }

    @Test
    public void test_method_2() {
        TreeNode node = BTreeUtil.createTree("[1,0]");
        int i = sumNumbers_2(node);
        System.out.println("i = " + i);
    }


    /*----------------------------------------------下面这个是另一个求法----------------------------------------------------------------*/
    @Test
    public void test() {
        TreeNode node = BTreeUtil.createTree("[4,9,0,5,1]");
        System.out.println("sumNumbers(node) = " + sumNumbers(node));
    }


    /**
     * 叶子节点作为最大位
     */
    public void dfs(TreeNode node, int prevValue, int level) {
        if (node == null) {
            return;
        }
        int currVal = prevValue + (int) Math.pow(10, level) * node.val;
        if (node.left == node.right) {
            ans += currVal;
        }
        dfs(node.left, currVal, level + 1);
        dfs(node.right, currVal, level + 1);
    }
}
