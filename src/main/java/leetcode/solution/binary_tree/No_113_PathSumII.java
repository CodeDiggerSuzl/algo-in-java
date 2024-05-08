package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class No_113_PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        dfs(root, targetSum, new ArrayList<>());
        return ans;
    }

    List<List<Integer>> ans = new ArrayList<>();

    public void dfs(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        int diff = k - node.val;
        if (node.left == null && node.right == null) {
            if (diff == 0) {
                System.out.println("node.val = " + node.val);
            }
        }
        dfs(node.left, diff);
        dfs(node.right, diff);
    }


    public void dfs(TreeNode node, int k, List<Integer> preList) {
        if (node == null) {
            return;
        }
        int diff = k - node.val;


        preList.add(node.val);
        log.debug("node.val ={},pList={}", node.val, preList);
        if (node.left == null && node.right == null) {
            if (diff == 0) {
                ans.add(preList);
            }
            return;
        }
        //        List<Integer> list = new ArrayList<>(preList);
        //        dfs(node.left, diff, list); // 这样写是错误的,左子树和右子树用的是同一个list, list 会很长!
        //        dfs(node.right, diff, list);

        // ! 应该左子树和 和右子树都使用新的 list
        dfs(node.left, diff, new ArrayList<>(preList)); // 子这里
        dfs(node.right, diff, new ArrayList<>(preList));
    }

    @Test
    public void test_method_1() {
        TreeNode tree = BTreeUtil.createTree("[5,4,8,11,null,13,4,7,2,null,null,5,1]");
        List<List<Integer>> lists = pathSum(tree, 22);
        System.out.println("integers = " + lists);

    }

}
