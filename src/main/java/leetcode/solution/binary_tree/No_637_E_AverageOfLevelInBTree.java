package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [name]: 637. 二叉树的层平均值
 * <p>
 * [link]: <a href="https://leetcode.cn/problems/average-of-levels-in-binary-tree/">url</a>
 * <p>
 * [description]: 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
 * <p>
 * [category]:
 * <p>
 * [solving tips]:
 * <p>
 * <p>
 * [answers]:
 */
@Slf4j
public class No_637_E_AverageOfLevelInBTree {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            double total = 0D;
            int size = que.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = que.poll();
                total = total + node.val;
                if (node.left != null) {
                    que.add(node.left);
                }
                if (node.right != null) {
                    que.add(node.right);
                }
            }
            ans.add(total / size);
        }
        return ans;
    }


    @Test
    public void test_1() {
        TreeNode node = BTreeUtil.createTree("[3,9,20,null,null,15,7]");

        System.out.println("averageOfLevels(node) = " + averageOfLevels(node));
    }
}
