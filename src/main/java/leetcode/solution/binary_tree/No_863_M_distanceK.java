package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * [name]:
 * <p>
 * [link]:
 * <p>
 * [description]:
 * <p>
 * [category]:
 * <p>
 * [solving tips]:
 *
 */
public class No_863_M_distanceK {


    /*--------------------------------------------------------------------------------------------------------------*/


    List<Integer> ans = new ArrayList<>();

    // method
    public void findFromRoot(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        if (k == 0) {
            ans.add(root.val);
        }
        --k;
        findFromRoot(root.left, k);
        findFromRoot(root.right, k);
    }


    public List<Integer> findFromRoot_(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (k == 0) {
            res.add(root.val);
        }
        --k;
        res.addAll(findFromRoot_(root.left, k));
        res.addAll(findFromRoot_(root.right, k));
        return res;
    }


    /*--------------------------------------------------------------------------------------------------------------*/
    TreeNode node = BTreeUtil.createTree("[1,2,3,4,5]");

    @Test
    public void test_findFromRoot() {
        findFromRoot(node, 2);
        System.out.println("ans = " + ans);
    }

    @Test
    public void test_findFromRoot_() {
        System.out.println("findFromRoot_(node,2) = " + findFromRoot_(node, 1));
    }


    private void dfs(TreeNode node, int depth, List<Integer> result) {
        if (node == null) return;
        if (depth == 0) {
            result.add(node.val);
            return;
        }
        dfs(node.left, depth - 1, result);
        dfs(node.right, depth - 1, result);
    }

    /**
     * 要找出二叉树中距离特定节点为 2 的其他节点，可以采取以下步骤：
     * <p>
     * 从给定的特定节点开始，向上搜索到距离为 1 的父节点和距离为 1 的兄弟节点。
     * 从这些父节点和兄弟节点开始，向下搜索，距离为 2 的节点即为所求节点。
     */
    private int findParentAndSibling(TreeNode root, TreeNode target, int k, List<Integer> result) {
        if (root == null) return -1;
        if (root == target) return 0;

        int left = findParentAndSibling(root.left, target, k, result);
        int right = findParentAndSibling(root.right, target, k, result);

        if (left != -1) {
            if (left + 1 == k) result.add(root.val);
            else dfs(root.right, k - left - 2, result);
            return left + 1;
        }
        if (right != -1) {
            if (right + 1 == k) result.add(root.val);
            else dfs(root.left, k - right - 2, result);
            return right + 1;
        }
        return -1;
    }
}
