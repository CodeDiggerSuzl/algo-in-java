package leetcode.solution.binary_tree;

import annotion.LongTime;
import annotion.SimilarTo;
import annotion.Stocked;
import annotion.ToDo;
import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * [name]:2385. 感染二叉树需要的总时间
 * <p>
 * [link]: <a href="https://leetcode.cn/problems/amount-of-time-for-binary-tree-to-be-infected/description/?envType=daily-question&envId=2024-04-24">link</a>
 * <p>
 * [description]: 给你一棵二叉树的根节点 root ，二叉树中节点的值 互不相同 。另给你一个整数 start 。在第 0 分钟，感染 将会从值为 start 的节点开始爆发。
 * <p>
 * 每分钟，如果节点满足以下全部条件，就会被感染：
 * <p>
 * 节点此前还没有感染。
 * 节点与一个已感染节点相邻。
 * 返回感染整棵树需要的分钟数。
 * <p>
 * [category]: 二叉树
 * <p>
 * [solving tips]:
 * <p>
 * 方法 1: 记录父节点+DFS. 遍历一遍树,找到目标节点:target,同时记录每个节点的父节点. 然后做 从 target 节点开始做 dfs,类似于三叉树的最大深度
 * <p>
 * 方法 2: TODo
 * [answers]: <a href="https://leetcode.cn/problems/amount-of-time-for-binary-tree-to-be-infected/solutions/2753470/cong-liang-ci-bian-li-dao-yi-ci-bian-li-tmt0x/?envType=daily-question&envId=2024-04-24">ans</a>
 */
@LongTime
@Stocked(cause = "没有想到[1,null,2,3,4,null,5] start=4这种情况")
@ToDo(value = "做相关的题.写出题解.梳理思路")
@Slf4j
@SimilarTo({"863"})
public class No_2385_AmountOfTime_A {

    /**
     * 本质求得是每个节点最大的"半径"?
     * 现遍历每个节点 获取到每个节点的最大半径
     */
    public int amountOfTime(TreeNode root, int start) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        if (root.left == null) {
            dfs(root.right, map, 1, false);
        } else if (root.right == null) {
            dfs(root.left, map, -1, true);
        } else {
            dfs(root.left, map, -1, true);
            dfs(root.right, map, 1, false);
        }

        map.put(root.val, 0);
        System.out.println(map);


        int leftMax = 0, rightMax = 0;
        int leftVal = 0, rightVal = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer node_val = entry.getKey();
            Integer value = entry.getValue();
            if (value > 0) {
                if (value > rightMax) {
                    rightMax = value;
                    rightVal = node_val;
                }
            } else {
                if (value < leftMax) {
                    leftMax = value;
                    leftVal = node_val;
                }
            }
        }
        System.out.println("lmax" + leftMax);
        System.out.println("rmax" + rightMax);
        Integer gap = map.get(start);
        System.out.println(gap);
        // 右子树
        if (gap > 0) {
            if (rightMax > leftMax) {

            }
            return Math.max((rightMax - gap), gap - leftMax);
        }
        // root 节点
        if (gap == 0) {
            System.out.println("gap is 0");
            int max = Math.max(rightMax, -1 * leftMax);
            System.out.println("gap is 0,last return ma:" + max);
            return max;
        }

        // 左子树
        return Math.max((rightMax - gap), gap - leftMax);

    }


    /**
     * 每个节点到 root 的距离
     *
     * @param root
     * @param map
     * @return
     */
    public void dfs(TreeNode root, Map<Integer, Integer> map, int cnt, boolean left) {
        if (root == null) {
            return;
        }
        map.put(root.val, cnt);
        System.out.println("val = " + root.val + "cnt:" + cnt);
        cnt = left ? cnt - 1 : cnt + 1;

        dfs(root.left, map, cnt, left);
        dfs(root.right, map, cnt, left);
    }

    /*--------------------------------------------------------------------------------------------------------------*/
    // 上面的答案对 输入：root = [1,5,3,null,4,10,6,9,2], start = 3 这种情况好使
    // 但是对 [1,null,2,3,4,null,5] start=4 这种情况不好使,因为最深节点和感染点都是 root 的右子树
    // 应该的思路获取是遍历每一个 node,获取 node 到感染点的距离,
    /*--------------------------------------------------------------------------------------------------------------*/


    // 看了题解后的答案:
    // 需要做以下几题:
    // 104. 二叉树的最大深度 https://leetcode.cn/problems/maximum-depth-of-binary-tree/
    // 543. 二叉树的直径 https://leetcode.cn/problems/diameter-of-binary-tree/description/
    // 2385. 感染二叉树需要的总时间 https://leetcode.cn/problems/amount-of-time-for-binary-tree-to-be-infected/description/?envType=daily-question&envId=2024-04-24
    // 然后在做:
    // 863. 二叉树中所有距离为 K 的结点


    Map<TreeNode, TreeNode> map = new HashMap();
    Map<Integer, Integer> map_V = new HashMap();
    TreeNode target;

    @Test
    public void test() {
        TreeNode tree = BTreeUtil.createTree("[1,5,3,null,4,10,6,9,2]");
        findTargetNode(tree, 3);
        // log.debug("map={}", JsonUtil.toJson(map_V));
        //        log.debug("target={}", JsonUtil.toJson(target));
        System.out.println("maxDistance(target,map.get(target)) = " + maxDistance_V2(target, target));
    }

    // 正确答案在此 ✅

    /**
     * 记录每个节点的父节点
     */
    public void findTargetNode(TreeNode root, int start) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            map.put(root.left, root);
            map_V.put(root.left.val, root.val);
        }
        if (root.right != null) {
            map.put(root.right, root);
            map_V.put(root.right.val, root.val);
        }
        if (root.val == start) {
            target = root;
        }
        findTargetNode(root.left, start);
        findTargetNode(root.right, start);
    }


    /**
     * 这个方法是对的
     * 通过第一步
     * 从树某个点开始最大的距离
     */
    public int maxDistance_V2(TreeNode node, TreeNode from) {
        if (node == null) {
            return -1;
        }
        int res = -1;
        if (node.left != from) {
            res = Math.max(res, maxDistance_V2(node.left, node));
        }

        if (node.right != from) {
            res = Math.max(res, maxDistance_V2(node.right, node));
        }
        if (map.get(node) != from) {
            res = Math.max(res, maxDistance_V2(map.get(node), node));
        }
        return res + 1;
    }

    @Test
    public void test_method_2() {
        TreeNode tree = BTreeUtil.createTree("[1,2,null,3,null,4,null,5]");
        dfsOnce(tree, 4);
        System.out.println("ans = " + ans);

    }

    int ans;

    /**
     * 后序遍历:
     * 递归时，除了返回当前子树的最大链长加一，还需要返回一个布尔值，表示当前子树是否包含 start
     * 将问题拆分成求 一个树的深度和最大直径的问题
     */
    public int[] dfsOnce(TreeNode node, int start) {

        if (node == null) {
            return new int[]{0, 0};
        }
        int[] leftRes = dfsOnce(node.left, start);
        int[] rightRes = dfsOnce(node.right, start);
        log.debug("processing node={}", node.val);
        log.debug("node={},左边返回={},右边返回={}", node.val, JsonUtil.toJson(leftRes), JsonUtil.toJson(rightRes));
        int lLen = leftRes[0], rLen = rightRes[0], lFound = leftRes[1], rFound = rightRes[1];

        // 如果找到了目标节点
        if (node.val == start) {
            // 结果设置为以 start 为 root节点的树的最大深度
            ans = Math.max(lLen, rLen);
            // 返回的以 start 节点作为叶子的深度, 第一个 1 表示深度,第二个 1 表示找到了
            return new int[]{1, 1};
        }

        // 当 start 节点为子节点,而不是整个树的root节点的时候,才更新答案
        if (lFound == 1 || rFound == 1) {
            ans = Math.max(ans, lLen + rLen);
            int which = lFound == 1 ? lLen : rLen;
            return new int[]{which + 1, 1};
        }
        // 其他情况:当 start 节点为子树的 root 节点或者没有start节点的关系,此时就是求树的深度
        // 未找到 target 节点的情况
        return new int[]{Math.max(lLen, rLen) + 1, 0};
    }

}
