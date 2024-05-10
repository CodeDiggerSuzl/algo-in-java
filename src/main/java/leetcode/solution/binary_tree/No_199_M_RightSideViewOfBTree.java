package leetcode.solution.binary_tree;

import annotion.PASS;
import annotion.ToDo;
import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
 * [题目名称]:二叉树的右视图
 *
 * [题目链接]:https://leetcode.cn/problems/binary-tree-right-side-view/description/
 * [题目描述]:给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * [解题思路]: 遍历
 *
 */
@PASS(tips = "遍历树,dfs,先遍历右数")
@ToDo(value = "使用层次遍历解决该问题")
public class No_199_M_RightSideViewOfBTree {

    private int maxLevel = 0;
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 1);
        return ans;
    }


    public void dfs(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (level > maxLevel) {
            maxLevel = level;
            ans.add(node.val);
        }
        level++;
        dfs(node.right, level); // 现遍历右数
        dfs(node.left, level);
    }

    public void dfs_better(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (level > ans.size()) {
            // maxLevel = level;
            ans.add(node.val);
        }
        level++;
        dfs_better(node.right, level); // 现遍历右数
        dfs_better(node.left, level);
    }


    @ToDo
    public List<Integer> rightSideViewByLevelOrder(TreeNode root) {
        return null;
    }


    @Test
    public void test() {
        TreeNode node = BTreeUtil.createTree("[1,null,3]");
        System.out.println("rightSideView(node) = " + rightSideView(node));
    }


}
