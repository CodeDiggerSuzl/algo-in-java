package leetcode.solution.binary_tree;

import annotion.SimilarTo;
import annotion.Stocked;
import annotion.ToDo;
import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *
 * [name]: 863. 二叉树中所有距离为 K 的结点
 * <p>
 * [link]: https://leetcode.cn/problems/all-nodes-distance-k-in-binary-tree/
 * <p>
 * [description]:给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 k 。
 * 返回到目标结点 target 距离为 k 的所有结点的值的列表。 答案可以以 任何顺序 返回。
 * <p>
 * [category]: 二叉树
 * <p>
 * [solving tips]: 类似于题目 2385 的题目,
 * 1.可以构建一个图,记录每个节点的父节点
 * 2.通过一次遍历 TODO
 *
 */
@Slf4j
@SimilarTo({"2385"})
@ToDo(value = "一遍历的情况")
public class No_863_M_distanceK {

    List<Integer> ans = new ArrayList<>();


    /*-------------------------------------------------方法 1-------------------------------------------------------------*/

    TreeNode theTarget;

    public List<Integer> distanceK_1(TreeNode root, int targetVal, int k) {
        findTarget(root, targetVal);
        System.out.println("JsonUtil.toJson(memo) = " + JsonUtil.toJson(memo_v));
        dfs(theTarget, theTarget, k);
        return ans;
    }

    Map<TreeNode, TreeNode> memo = new HashMap<>();
    Map<Integer, Integer> memo_v = new HashMap<>();

    public void findTarget(TreeNode root, int targetVal) {
        if (root == null) {
            return;
        }
        if (root.right != null) {
            memo.put(root.right, root);
            memo_v.put(root.right.val, root.val);
        }
        if (root.left != null) {
            memo.put(root.left, root);
            memo_v.put(root.left.val, root.val);

        }
        if (root.val == targetVal) {
            theTarget = root;
        }
        findTarget(root.left, targetVal);
        findTarget(root.right, targetVal);
    }

    public void dfs(TreeNode node, TreeNode from, int k) {
        if (node == null || k < 0) {
            return;
        }
        log.debug("处理 node={},k={}", node.val, k);
        if (k == 0) {
            ans.add(node.val);
        }
        --k;
        if (node.left != from) {
            dfs(node.left, node, k);
        }
        if (node.right != from) {
            dfs(node.right, node, k);
        }
        TreeNode parent = memo.get(node);
        log.debug("node={},parent={}", node.val, parent == null ? "null" : JsonUtil.toJson(parent.val));
        if (parent != from) {
            dfs(parent, node, k);
        }
    }

    @Test
    public void test_method_1() {
        TreeNode tree = BTreeUtil.createTree("[3,5,1,6,2,0,8,null,null,7,4]");
        List<Integer> integers = distanceK_1(tree, 5, 2);
        System.out.println("integers = " + integers);

    }

    @Stocked
    /*-------------------------------------------------方法 2-------------------------------------------------------------*/
    //    public List<Integer> distanceK_2(TreeNode root, int target, int k) {
    //        dfsOnce(root, target, k);
    //        System.out.println("ans = " + ans);
    //        return ans;
    //    }
    //
    //    int maxLen;
    //    public int[] dfsOnce(TreeNode node, Integer targetVal, int k) {
    //        if (node == null) {
    //            return new int[]{0, 0};
    //        }
    //        int[] leftRes = dfsOnce(node.left, targetVal,k);
    //        int[] rightRes = dfsOnce(node.right, targetVal,k);
    //        log.debug("processing node={}", node.val);
    //        log.debug("node={},左边返回={},右边返回={}", node.val, JsonUtil.toJson(leftRes), JsonUtil.toJson(rightRes));
    //        int lLen = leftRes[0], rLen = rightRes[0], lFound = leftRes[1], rFound = rightRes[1];
    //
    //
    //        // 如果找到了目标节点
    //        if (node.val == targetVal) {
    //            // 结果设置为以 start 为 root节点的树的最大深度
    //            maxLen = Math.max(lLen, rLen);
    //            if (maxLen == k) {
    //                ans.add(node.val);
    //            }
    //            // 返回的以 start 节点作为叶子的深度, 第一个 1 表示深度,第二个 1 表示找到了
    //            return new int[]{1, 1};
    //        }
    //
    //        // 当 start 节点为子节点,而不是整个树的root节点的时候,才更新答案
    //        if (lFound == 1 || rFound == 1) {
    //            maxLen = Math.max(maxLen, lLen + rLen);
    //            if (maxLen == k) {
    //                ans.add(node.val);
    //            }
    //            int which = lFound == 1 ? lLen : rLen;
    //            return new int[]{which + 1, 1};
    //        }
    //        // 当 start 节点为子树的 root 节点,此时就是求树的深度
    //        return new int[]{Math.max(lLen, rLen) + 1, 0};
    //    }
    //
    //    @Test
    //    public void test_method_2() {
    //        TreeNode tree = BTreeUtil.createTree("[3,5,1,6,2,0,8,null,null,7,4]");
    //        List<Integer> integers = distanceK_2(tree, 5, 2);
    //        System.out.println("integers = " + integers);
    //
    //    }

    /*-------------------------------------------------下面的是求得一个节点到 root 节点为 k 的距离-------------------------------------------------------------*/


    /**
     * 方法 1:找到距离root 节点为 k 的节点
     */
    public void findFromRoot_1(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        if (k == 0) {
            ans.add(root.val);
        }
        --k;
        findFromRoot_1(root.left, k);
        findFromRoot_1(root.right, k);
    }

    /**
     * 方法 2:找到距离root 节点为 k 的节点
     */
    public List<Integer> findFromRoot_2(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (k == 0) {
            res.add(root.val);
        }
        --k;
        res.addAll(findFromRoot_2(root.left, k));
        res.addAll(findFromRoot_2(root.right, k));
        return res;
    }


    /*--------------------------------------------------------------------------------------------------------------*/

    @Test
    public void test_findFromRoot() {
        TreeNode node = BTreeUtil.createTree("[1,2,3,4,5]");
        findFromRoot_1(node, 2);
        System.out.println("ans = " + ans);
    }

    @Test
    public void test_findFromRoot_2_() {
        TreeNode node = BTreeUtil.createTree("[1,2,3,4,5]");

        System.out.println("findFromRoot_(node,2) = " + findFromRoot_2(node, 1));
    }


}
