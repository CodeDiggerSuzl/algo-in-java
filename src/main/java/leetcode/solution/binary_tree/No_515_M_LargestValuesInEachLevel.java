package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [name]: 515. 在每个树行中找最大值
 * <p>
 * [link]: <a href="https://leetcode.cn/problems/find-largest-value-in-each-tree-row/">url</a>
 * <p>
 * [description]: 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 * <p>
 * [category]: 二叉树
 * <p>
 * [solving tips]:
 * <p>
 * <p>
 * [answers]:
 */
@Slf4j
public class No_515_M_LargestValuesInEachLevel {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Integer max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 找到最大值
                max = Math.max(node.val, max);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(max);
        }
        return ans;
    }
}
