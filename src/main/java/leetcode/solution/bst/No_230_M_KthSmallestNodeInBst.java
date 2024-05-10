package leetcode.solution.bst;

import annotion.ToDo;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * [name]: 230. 二叉搜索树中第K小的元素
 * <p>
 * [link]: <a href="https://leetcode.cn/problems/kth-smallest-element-in-a-bst/description/?envType=study-plan-v2&envId=top-100-liked">link</a>
 * <p>
 * [description]:
 * <p>
 * [category]:
 * <p>
 * [solving tips]:
 * <p>
 * <p>
 * [answers]: 相关 link :<a href="https://labuladong.online/algo/data-structure/bst-part1/">...</a>
 */
@Slf4j
@ToDo("更好的解法")
public class No_230_M_KthSmallestNodeInBst {

    /**
     * O(n) 的解法
     */
    List<Integer> ans = new ArrayList<>();

    public int kthSmallest(TreeNode root, int k) {
        dfs(root);
        return ans.get(k - 1);
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        ans.add(node.val);
        dfs(node.right);
    }

    /* ----------------------------------------------- 下面是更好的解法 ----------------------------------------------- */

    // TODO
}
