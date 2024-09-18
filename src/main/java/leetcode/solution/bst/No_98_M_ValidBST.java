package leetcode.solution.bst;

import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * [name]:98. 验证二叉搜索树
 * <p>
 * [link]: <a href="https://leetcode.cn/problems/validate-binary-search-tree/description/">98. 验证二叉搜索树</a>
 * <p>
 * [description]: 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左
 * 子树
 * 只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * [category]:
 * <p>
 * [solving tips]:
 * <p>
 * <p>
 * [answers]:
 */
@Slf4j
public class No_98_M_ValidBST {

    // Essentials

    // using for loop
    private Long preMax = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftOk = isValidBST(root.left);
        if (root.val > preMax) {
            preMax = (long) root.val;
            return leftOk && isValidBST(root.right);
        } else {
            return false;
        }
    }


    // using recursion method
    // pass the min and max value to the next recursion.
    public boolean isValidBST_(TreeNode root) {
        return isValidBST_(root, null, null);
    }

    private boolean isValidBST_(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min) {
            return false;
        }
        if (max != null && root.val >= max) {
            return false;
        }
        return isValidBST_(root.left, min, root.val) && isValidBST_(root.right, root.val, max);
    }

    @Test
    public void test_1() {
        TreeNode node = BTreeUtil.createTree("[1,null,1]");
        System.out.println("isValidBST(node) = " + isValidBST(node));
        System.out.println("isValidBST(node) = " + isValidBST_(node));

    }


}