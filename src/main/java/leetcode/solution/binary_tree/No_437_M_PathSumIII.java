package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * [437. 路径总和 III - 力扣（LeetCode）](https://leetcode.cn/problems/path-sum-iii/description/?envType=study-plan-v2&envId=top-100-liked)
 * <p>
 * [link]:
 * <p>
 * [description]:
 * <p>
 * [category]:
 * <p>
 * [solving tips]: 遍历每一个节点,处理类似于 root 的逻辑.就是把遍历的 "打印"改为了一个方法
 * 这套题挺恶意.还需要将 targetSum 改为 long 类型
 * <p>
 * <p>
 * [answers]:https://leetcode.cn/problems/path-sum-iii/solutions/100992/qian-zhui-he-di-gui-hui-su-by-shi-huo-de-xia-tian/?envType=study-plan-v2&envId=top-100-liked
 */
@Slf4j
public class No_437_M_PathSumIII {

    int ans = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        count(root, (long) targetSum);
        return ans;
    }

    // 遍历节点中的每一个节点执行相同的方法
    void count(TreeNode node, Long targetSum) {
        if (node == null) {
            return;
        }
        dfs(node, targetSum);
        count(node.left, targetSum);
        count(node.right, targetSum);
    }


    public void dfs(TreeNode node, Long targetSum) {
        if (node == null) {
            return;
        }

        if (targetSum == node.val) {
            ans++;
        }

        long targetSum1 = targetSum - node.val;
        System.out.println("targetSum1 = " + targetSum1);
        System.out.println("(int) targetSum1 = " + (int) targetSum1);
        dfs(node.left, targetSum1);
        dfs(node.right, targetSum1);
    }


    @Test
    public void test_1() {
        TreeNode node = BTreeUtil.createTree("[1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000]");

        pathSum(node, 0);
        System.out.println("ans = " + ans);
    }

    /* ----------------------------------------------- 上面是遍历的情况 ----------------------------------------------- */


    public int dfs_2(TreeNode node, Long targetSum) {
        if (node == null) {
            return 0;
        }
        int ans = 0;
        if (targetSum == node.val) {
            ans++;
        }
        ans += dfs_2(node.left, targetSum - node.val);
        ans += dfs_2(node.right, targetSum - node.val);
        return ans;
    }

    public int pathSum_2(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        ans += dfs_2(root, targetSum);
        ans += pathSum_2(root.left, targetSum);
        ans += pathSum_2(root.right, targetSum);
        return ans;
    }

    @Test
    public void test_2() {
        double num_10E11 = Math.pow(10, 11);
        System.out.println("Math.pow(10,11) = " + num_10E11);
        double num_2_31 = Math.pow(2, 32);
        System.out.println("Math.max(num_2_31,num_10E11) = " + Math.max(num_2_31, num_10E11));
    }
}

