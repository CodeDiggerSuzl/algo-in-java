package leetcode.solution.binary_tree;

import annotion.LongTime;
import annotion.Stocked;
import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import org.junit.Test;

/*
 *
 * [name]:
 * [link]:
 * [description]:
 * 给你一棵二叉树的根节点，返回该树的直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 *
 * [category]:
 * [solving tips]:
 *
 */
@Stocked
@LongTime
public class No_543_MaxDiameterOfBTree {


    private int max = 0;


    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);
        max = Math.max(left + right, max);
        return max;
    }


    public int diameterOfBinaryTree2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        int left = diameterOfBinaryTree2(root.left);
        int right = diameterOfBinaryTree2(root.right);
        max = Math.max(left + right + 2, max) - 1;
        return max;
    }


    @Test
    public void test() {
        TreeNode node = BTreeUtil.createTree("[1,2,3,4,5]");
        int i = diameterOfBinaryTree2(node);
        System.out.println("diameterOfBinaryTree(node) = " + (i));
    }

}
