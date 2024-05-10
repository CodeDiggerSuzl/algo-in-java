package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

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

    public List<List<Integer>> levelOrder_1(TreeNode root) {
        levelTraverse(root);
        return ans;
    }

    List<List<Integer>> ans = new ArrayList<>();

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
                TreeNode cur = q.remove();
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
        levelOrder_1(node);
        System.out.println("ans = " + ans);

    }

    /* ----------------------------------------------- 上面是形式 1 ----------------------------------------------- */
    public void levelOrder_2(TreeNode node) {
        if (node == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                System.out.println(" = " + poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
    }


    @Test
    public void test_2() {
        TreeNode node = BTreeUtil.createTree("[3,9,20,null,null,15,7]");
        levelOrder_2(node);
    }

    /* ----------------------------------------------- 上面是形式 2 ----------------------------------------------- */

    public List<List<Integer>> levelOrder_3(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int size = queue.size();
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
        return ans;
    }

    @Test
    public void test_3() {
        TreeNode node = BTreeUtil.createTree("[3,9,20,null,null,15,7]");
        System.out.println("levelOrder_3(node) = " + levelOrder_3(node));
    }

    /* ----------------------------------------------- 下面是递归的方式 ----------------------------------------------- */
    List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        dns(root, 0);
        return list;
    }

    public void dns(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        log.debug("node = {},level={},list={}", node.val, level, list);
        if (list.size() == level) {
            // 第一次处理每一层的时候,加入一个空 list
            list.add(new ArrayList<>());
        }

        // 当为特定层时,放入 node
        list.get(level).add(node.val);

        dns(node.left, level + 1);
        dns(node.right, level + 1);
        Collections.reverse(list);
    }

    @Test
    public void test_4() {
        TreeNode node = BTreeUtil.createTree("[3,9,20,null,null,15,7]");
        List<List<Integer>> lists = levelOrder(node);
    }

}
