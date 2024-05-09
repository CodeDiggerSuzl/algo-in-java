package leetcode.solution.binary_tree;

import annotion.SimilarTo;
import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * [name]:101. 对称二叉树
 * <p>
 * [link]: <a href="https://leetcode.cn/problems/symmetric-tree/description/">link</a>
 * <p>
 * [description]: 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * <p>
 * [category]: 二叉树
 * <p>
 * [solving tips]: 类似于相同的树
 * <p>
 */
@Slf4j
@SimilarTo("100")
public class No_101_E_SymmetricTree {


    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root.left, root.right);
    }

    public boolean check(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null || n2 == null) {
            return false;
        }
        return n1.val == n2.val && check(n1.left, n2.right) && check(n1.right, n2.left);
    }


    @Test
    public void test_1() {
         TreeNode node = BTreeUtil.createTree("[1,2,2,null,3,null,3]");
        System.out.println("isSymmetric(node) = " + isSymmetric(node));

    }
}
