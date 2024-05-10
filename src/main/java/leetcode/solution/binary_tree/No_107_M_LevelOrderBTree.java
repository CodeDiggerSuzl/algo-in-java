package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * [name]:107. 二叉树的层序遍历 II
 * <p>
 * [link]:<a href="https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/description/">url</a>
 * <p>
 * [description]:
 * <p>
 * [category]:
 * <p>
 * [solving tips]:
 * <p>
 * <p>
 * [answers]:
 */
@Slf4j
public class No_107_M_LevelOrderBTree {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans.add(tmp);
        }
        Collections.reverse(ans);
        return ans;
    }
    
}
