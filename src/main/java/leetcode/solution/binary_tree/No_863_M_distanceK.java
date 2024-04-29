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

    // method
    public void findFromRoot(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        if (k == 0) {
            ans.add(root.val);
        }
        --k;
        findFromRoot(root.left, k);
        findFromRoot(root.right, k);
    }


    public List<Integer> findFromRoot_(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (k == 0) {
            res.add(root.val);
        }
        --k;
        res.addAll(findFromRoot_(root.left, k));
        res.addAll(findFromRoot_(root.right, k));
        return res;
    }


    /*--------------------------------------------------------------------------------------------------------------*/
    TreeNode node = BTreeUtil.createTree("[1,2,3,4,5]");

    @Test
    public void test_findFromRoot() {
        findFromRoot(node, 2);
        System.out.println("ans = " + ans);
    }

    @Test
    public void test_findFromRoot_() {
        System.out.println("findFromRoot_(node,2) = " + findFromRoot_(node, 1));
    }


}
