package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.BTreePrinter;
import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * [name]:
 * 代码
 * 测试用例
 * 测试结果
 * 测试结果
 * 226. 翻转二叉树
 *
 * <p>
 * [link]: <a href="https://leetcode.cn/problems/invert-binary-tree/description/">link</a>
 * <p>
 * [description]:给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * <p>
 * [category]:
 * <p>
 * [solving tips]:  注意递归的方法
 * <p>
 * <p>
 * [answers]:
 */
@Slf4j
public class No_226_E_InvertTree {


    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }


    /**
     * 遍历的方法
     */
    public void invert(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        invert(node.left);
        invert(node.right);
    }

    @Test
    public void test_1() {
        TreeNode node = BTreeUtil.createTree("[4,2,7,1,3,6,9]");

        invertTree(node);
        BTreePrinter.printTree(node);

    }
    /*-----------------------------👆🏻是遍历的解法-----------------------------*/

    /**
     * 递归的方法
     */

    public TreeNode invertTree_2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree_2(root.left);
        TreeNode right = invertTree_2(root.right);
        root.right = left;
        root.left = right;
        return root;
    }

    @Test
    public void test_2() {
        TreeNode node = BTreeUtil.createTree("[3,9,20,null,null,15,7]");
        invertTree_2(node);
        BTreePrinter.printTree(node);
    }

}
