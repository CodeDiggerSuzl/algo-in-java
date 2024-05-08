package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
@Slf4j
public class No_129_SumUpRootToLeafNums {


    /*--------------------------------------------------------------------------------------------------------------*/


    public int sumNumbers(TreeNode root) {
        dfs(root, 0, 0);
        return ans;

    }


    int ans = 0;

    /**
     * 遍历的方式
     */
    public void dfs(TreeNode node, int prevValue) {
        if (node == null) {
            return;
        }
        int currVal = prevValue * 10 + node.val;
        if (node.left == null && node.right == null) {
            ans += currVal;
        }
        log.info("node={},prevVal={},currVal={},ans={}", node.val, prevValue, currVal, ans);
        dfs(node.left, currVal);
        dfs(node.right, currVal);
    }

    /*--------------------------------------------------------------------------------------------------------------*/
    @Test
    public void test() {
        TreeNode node = BTreeUtil.createTree("[4,9,0,5,1]");
        System.out.println("sumNumbers(node) = " + sumNumbers(node));
    }


    public void dfs(TreeNode node, int prevValue, int level) {
        if (node == null) {
            return;
        }
        int currVal = prevValue + (int) Math.pow(10, level) * node.val;
        if (node.left == node.right) {
            ans += currVal;
        }
        dfs(node.left, currVal, level + 1);
        dfs(node.right, currVal, level + 1);
    }
}
