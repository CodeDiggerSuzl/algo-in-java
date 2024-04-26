package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.TreeNode;

/**
 * https://leetcode.cn/problems/same-tree/?envType=study-plan-v2&envId=top-interview-150
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
public class No_100_IsSameTree {


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }
        if (q.val != p.val) {
            return false;
        }

        return isSameTree(q.left, p.left) && isSameTree(q.right, p.right);
    }
}
