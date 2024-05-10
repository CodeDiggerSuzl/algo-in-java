package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * [name]: 1448. 统计二叉树中好节点的数目
 * <p>
 * [link]: <a href="https://leetcode.cn/problems/count-good-nodes-in-binary-tree/description/">link</a>
 * <p>
 * [description]: 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
 * <p>
 * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
 * <p>
 * 二叉树中节点数目范围是 [1, 10^5] 。
 * 每个节点权值的范围是 [-10^4, 10^4] 。
 * <p>
 * [category]:
 * <p>
 * [solving tips]:
 */
@Slf4j
public class No_1448_M_GoodNodesInBTree {


    /*--------------------------------------------------------------------------------------------------------------*/

    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return ans;
    }

    int ans = 0;

    public void dfs(TreeNode node, int preMax) {
        if (node == null) {
            return;
        }
        int currVal = node.val;
        if (currVal >= preMax) {
            System.out.println("currVal = " + currVal);
            ans++;
        }
        dfs(node.left, Math.max(preMax, currVal));
        dfs(node.right, Math.max(preMax, currVal));
    }

    @Test
    public void test_1() {
        TreeNode node = BTreeUtil.createTree("[3,3,null,4,2]");
        int cnt = goodNodes(node);
        System.out.println("cnt = " + cnt);
    }
    /*--------------------------------------------------------------------------------------------------------------*/

    public int goodNodes_2(TreeNode root) {
        return count_(root, Integer.MIN_VALUE);
    }


    public int count(TreeNode node, int preMax) {
        if (node == null) {
            return 0;
        }
        int currVal = node.val;
        int left = count(node.left, Math.max(preMax, currVal));
        int right = count(node.right, Math.max(preMax, currVal));
        return left + right + (currVal >= preMax ? 1 : 0);
    }

    public int count_(TreeNode node, int preMax) {
        if (node == null) {
            return 0;
        }
        int currVal = node.val;
        int left = count_(node.left, Math.max(preMax, currVal));
        int right = count_(node.right, Math.max(preMax, currVal));
        // 不加括号就不对.❌
        return left + right + currVal >= preMax ? 1 : 0;
    }

    @Test
    public void test_2() {
        TreeNode node = BTreeUtil.createTree("[3,3,null,4,2]");
        int cnt = goodNodes_2(node);
        System.out.println("cnt = " + cnt);
    }

}
