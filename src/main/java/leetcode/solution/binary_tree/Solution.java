package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.BTreePrinter;
import leetcode.solution.binary_tree.common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            TreeNode treeNode = new TreeNode(nums[i]);
            while (!deque.isEmpty()) {
                TreeNode pl = deque.peekLast();
                if (pl.val > treeNode.val) {
                    pl.right = treeNode;
                    deque.addLast(treeNode);
                    break;
                } else {
                    treeNode.left = pl;
                    deque.removeLast();
                }
            }

            if (deque.isEmpty()) {
                deque.addLast(treeNode);
            }
        }

        return deque.peek();

    }

    @Test
    public void test_1() {
        int[] arr = {3, 2, 1, 6, 0, 5};
        TreeNode root = constructMaximumBinaryTree(arr);
        BTreePrinter.printTree(root);
    }


}
