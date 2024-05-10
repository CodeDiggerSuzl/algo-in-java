package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * [name]:
 * <p>
 * [link]: <a href="https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/">link</a>
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
public class No_102_M_ZigzagLevelOrderBTree {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            System.out.println("ans = " + ans);
            if (ans.size() % 2 != 0) {
                Collections.reverse(temp);
            }
            ans.add(temp);
        }
        return ans;
    }

    @Test
    public void test_1() {
        TreeNode node = BTreeUtil.createTree("[3,9,20,null,null,15,7]");
        List<List<Integer>> lists = zigzagLevelOrder(node);
        System.out.println("lists = " + lists);
    }
}
