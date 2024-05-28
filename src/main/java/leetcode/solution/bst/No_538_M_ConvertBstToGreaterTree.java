package leetcode.solution.bst;

import annotion.SimilarTo;
import leetcode.solution.binary_tree.common.BTreePrinter;
import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * [name]: 538. 把二叉搜索树转换为累加树
 * <p>
 * [link]: <a href="https://leetcode.cn/problems/convert-bst-to-greater-tree/">538. 把二叉搜索树转换为累加树</a>
 * <p>
 * [description]: 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * <p>
 * 提醒一下，二叉搜索树满足下列约束条件：
 * <p>
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * <p>
 * [category]:Bst
 * <p>
 * [solving tips]:
 * <p>
 * <p>
 * [answers]:
 */
@Slf4j
@SimilarTo("1038")
public class No_538_M_ConvertBstToGreaterTree {

    int preSum = 0;

    @Test
    public void test_right() {
        TreeNode node = BTreeUtil.createTree("[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]");
        dfsR(node);
        BTreePrinter.printTree(node);
    }

    public void dfsR(TreeNode node) {
        if (node == null) {
            return;
        }
        dfsR(node.right);
        node.val = node.val + preSum;
        preSum = node.val;
        dfsR(node.left);
    }

}
