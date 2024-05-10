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
 * [name]: 102. 二叉树的层序遍历
 * <p>
 * [link]: <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal/description/?envType=study-plan-v2&envId=top-100-liked">link</a>
 * <p>
 * [description]: 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * [category]: 二叉树
 * <p>
 * [solving tips]:
 * <p>
 * <p>
 * [answers]:
 */
@Slf4j
public class No_102_M_LevelOrderBTree {

    public List<List<Integer>> levelOrder(TreeNode root) {
        levelTraverse(root);
        return ans;
    }

    List<List<Integer>> ans = new ArrayList<>();

    Queue<TreeNode> q = new LinkedList<>();

    void levelTraverse(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        //从上到下遍历二叉树的每一层
        while (!q.isEmpty()) {
            int sz = q.size();

            //从左到右遍历每一层的每个节点
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                list.add(cur.val);
                if (i == sz - 1) {
                    ans.add(list);
                }
                //将下一层节点放入队列
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
    }


    @Test
    public void test_1() {
        TreeNode node = BTreeUtil.createTree("[3,9,20,null,null,15,7]");
        levelOrder(node);
        System.out.println("ans = " + ans);

    }


    @Test
    public void test_2() {
    }
}