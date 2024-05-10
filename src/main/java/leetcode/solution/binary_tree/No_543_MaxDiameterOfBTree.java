package leetcode.solution.binary_tree;

import annotion.LongTime;
import annotion.Stocked;
import annotion.ToDo;
import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * [name]:
 * <p>
 * [link]:
 * <p>
 * [description]:
 * 给你一棵二叉树的根节点，返回该树的直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 * 1
 * / \
 * 2   3
 * / \
 * 4 5
 * <p>
 * [category]: 二叉树
 * <p>
 * [solving tips]: 无法仅仅通过递归去做, 是通过递归和遍历结合的方法
 */
@Stocked
@LongTime
@ToDo(value = "重新梳理思路")
@Slf4j
public class No_543_MaxDiameterOfBTree {


    private int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs_2(root);
        return ans;
    }

    /*
     * https://www.bilibili.com/video/BV17o4y187h1/?spm_id_from=333.880.my_history.page.click&vd_source=aca956c6b30e207641786b78016d94ff
     */
    public int dfs(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        ans = Math.max(left + right + 2, ans);
        return Math.max(left, right) + 1;
    }

    /*
     * 这个方法似乎更好理解一点
     * https://www.bilibili.com/video/BV1CK4y1Y76h/?spm_id_from=333.880.my_history.page.click&vd_source=aca956c6b30e207641786b78016d94ff
     */
    public int dfs_2(TreeNode root) {
        log.info("开始处理:{}", root == null ? null : root.val);
        if (root == null) {
            return 0;
        }
        int left = dfs_2(root.left);
        int right = dfs_2(root.right);
        log.info("节点{},left={},right={}", root.val, left, right);
        ans = Math.max(left + right, ans);
        return Math.max(left, right) + 1;
    }


    @Test
    public void test() {
        TreeNode node = BTreeUtil.createTree("[1,2,3,4,5]");
        System.out.println("diameterOfBinaryTree(node) = " + diameterOfBinaryTree(node));
    }

}
