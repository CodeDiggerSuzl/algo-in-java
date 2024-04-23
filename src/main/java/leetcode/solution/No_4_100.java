package leetcode.solution;

import nodes.tree.TreeNode;

public class No_4_100 {
    // https://leetcode.cn/problems/same-tree/?envType=study-plan-v2&envId=top-interview-150

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
