package leetcode.solution.binary_tree;

import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
public class No_863_M_distanceK {


    /*--------------------------------------------------------------------------------------------------------------*/


    List<Integer> ans = new ArrayList<>();

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
    TreeNode node = BTreeUtil.createTree("[1,2,3,4,5]");

    @Test
    public void test_findFromRoot() {
        findFromRoot_1(node, 2);
        System.out.println("ans = " + ans);
    }

    @Test
    public void test_findFromRoot_2_() {
        System.out.println("findFromRoot_(node,2) = " + findFromRoot_2(node, 1));
    }


}
