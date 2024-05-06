package leetcode.solution.binary_tree;

import annotion.LongTime;
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
 * <a href="https://leetcode.cn/problems/amount-of-time-for-binary-tree-to-be-infected/description/?envType=daily-question&envId=2024-04-24">2385. 感染二叉树需要的总时间 - 力扣（LeetCode）</a>
 * <p>
 * 给你一棵二叉树的根节点 root ，二叉树中节点的值 互不相同 。另给你一个整数 start 。在第 0 分钟，感染 将会从值为 start 的节点开始爆发。
 * <p>
 * 每分钟，如果节点满足以下全部条件，就会被感染：
 * <p>
 * 节点此前还没有感染。
 * 节点与一个已感染节点相邻。
 * 返回感染整棵树需要的分钟数。
 *
 *
 * 思路:
 * 1. 记录每个节点的父节点,然后做 dfs,类似于三叉树的最大深度
 */
@LongTime
@Stocked(cause = "没有想到[1,null,2,3,4,null,5] start=4这种情况")
@ToDo(doWhat = "做相关的题.写出题解.梳理思路")
@Slf4j
public class No_2385_AmountOfTime {

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

    @Test
    public void test() {

        TreeNode tree = BTreeUtil.createTree("[1,2,3,4,5]");
        putIntoMap(tree, 2);
        log.debug("map={}", JsonUtil.toJson(map_V));
        log.debug("target={}", JsonUtil.toJson(target));
        System.out.println("maxDistance(target,map.get(target)) = " + maxDistance(target, map.get(target)));
    }


    Map<TreeNode, TreeNode> map = new HashMap();
    Map<Integer, Integer> map_V = new HashMap();
    TreeNode target;

    public void putIntoMap(TreeNode root, int start) {
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
        putIntoMap(root.left, start);
        putIntoMap(root.right, start);
    }


    int ans = 0;

    /**
     * 这个方法不对
     */
    @Deprecated
    public int maxDistance(TreeNode node, TreeNode parent) {
        if (node == null) {
            return 0;
        }
        if (parent == null) {
            int dis_r = maxDistance(node.right, node);
            int dis_l = maxDistance(node.left, node);
            return Math.max(dis_l, dis_r) + 1;
        }
        if (parent.left == node) {
            int dis_p = maxDistance(parent, map.get(parent));
            int dis_r = maxDistance(parent.right, parent);
            return Math.max(dis_r, dis_p) + 1;
        }
        if (parent.right == node) {
            int dis_l = maxDistance(parent.left, parent);
            int dis_p = maxDistance(parent, map.get(parent));
            return Math.max(dis_l, dis_p) + 1;
        }
        return -1;

    }

    public int maxDepth(TreeNode node, TreeNode from) {

    }

}
