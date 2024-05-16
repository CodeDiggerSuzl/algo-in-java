package leetcode.solution.binary_tree;

import annotion.Stocked;
import leetcode.solution.binary_tree.common.BTreePrinter;
import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * [name]:114. 二叉树展开为链表
 * <p>
 * [link]: https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/
 * <p>
 * [description]:给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * [category]:
 * <p>
 * [solving tips]:
 * <p>
 * <p>
 * [answers]: https://mp.weixin.qq.com/s/odbfGvHdCT6YmPiRA2MT2g
 */
@Slf4j
@Stocked
public class No_114_M_Flatten_BTree {

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flat(root);

    }

    public TreeNode flat(TreeNode node) {
        if (node == null) {
            return node;
        }
        TreeNode left = flat(node.left);
        TreeNode right = flat(node.right);

        node.left = null;
        node.right = left;
        TreeNode p = node;
        while (p.right != null) {
            // 找到最右边的节点
            p = p.right;
        }
        log.debug("p={}", p.val);
        // 最右边的节点和新的右边节点连接
        p.right = right;
        BTreePrinter.printTree(node);
        return node;
    }

    @Test
    public void test_1() {
        TreeNode node = BTreeUtil.createTree("[1,2,5,3,4,null,6]");
        flatten(node);
        BTreePrinter.printTree(node);
    }
    /* ----------------------------------------------- 上面是递归的的方式 ----------------------------------------------- */


    // TODO 阅读这个
    // https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/solutions/131121/dong-hua-yan-shi-si-chong-jie-fa-114-er-cha-shu-zh



    /* ----------------------------------------------- 方法 2 使用前序遍历,使用 list 存储 ----------------------------------------------- */

    public void flatten_2(TreeNode root) {
        if (root == null) return;
        List<TreeNode> ans = new ArrayList<>();
        dfs(root, ans);
        for (int i = 0; i < ans.size(); i++) {
            TreeNode treeNode = ans.get(i);
            treeNode.left = null;
            if (i < ans.size() - 1) {
                treeNode.right = ans.get(i + 1);
            }
        }
    }

    public void dfs(TreeNode node, List<TreeNode> ans) {
        if (node == null) {
            return;
        }
        ans.add(node);
        dfs(node.left, ans);
        dfs(node.right, ans);
    }

    @Test
    public void test_2() {
        TreeNode node = BTreeUtil.createTree("[1,2,5,3,4,null,6]");
        flatten_2(node);
        BTreePrinter.printTree(node);
    }

}
