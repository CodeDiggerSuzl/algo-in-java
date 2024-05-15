package leetcode.solution.binary_tree;

import annotion.SimilarTo;
import annotion.Smart;
import leetcode.solution.binary_tree.common.BTreeUtil;
import leetcode.solution.binary_tree.common.TreeNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [name]: [116. 填充每个节点的下一个右侧节点指针 - 力扣（LeetCode）](https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/description/)
 * <p>
 * [link]:
 * <p>
 * [description]:
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <pre>
 *
 * <code>
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * </code>
 * </pre>
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * [category]: 二叉树的转换问题
 * <p>
 * [solving tips]: 很自然的想到层序遍历 还有一种巧妙的方法
 * <p>
 * <p>
 * [answers]:
 */
@Slf4j
@Smart("抽象成三叉树")
@SimilarTo("117. 填充每个节点的下一个右侧节点指针 II") // 116 和 117 基本一样
public class No_116_M_Connect_Right_Node {

    /* ----------------------------------------------- 巧妙的方法 ----------------------------------------------- */
    public Node connect_smart(Node root) {

        if (root == null) {
            return null;
        }
        link(root.left, root.right);
        return root;
    }

    public void link(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            return;
        }
        n1.next = n2;
        link(n1.left, n1.right);
        link(n1.right, n2.left);
        link(n2.left, n2.right);
    }

    /* ----------------------------------------------- 下面是层序遍历的方法 ----------------------------------------------- */

    public Node connect_1(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            Node temp = null;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                if (i == 0) {
                    temp = node;
                } else if (i == size - 1) {
                    temp.next = node;
                    temp = null;
                } else {
                    temp.next = node;
                    node = temp;
                }

            }
        }
        return root;
    }

    // 上面的方法可以优化

    public TreeNode connect(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                log.debug("nodeVal={}", node.val);
                log.debug("size={}", queue.size());

                TreeNode peek = queue.peek();
                log.debug("peek={}", JsonUtil.toJson(peek));
                if (i < size - 1) { // 这一样代码很关键
                    node.next = peek;
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    /* -----------------------------------------------  一种优雅的方法 ----------------------------------------------- */
    // https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/solutions/214548/java-san-xing-he-xin-dai-ma-chao-jian-ji-yi-yu-li-
    // 每个 node 左子树的 next , 就是 node 的右子树
    // 每个 node 右子树的 next, 就是 node next 的 左子树


    /* -----------------------------------------------  灵神 ----------------------------------------------- */

    // https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/solutions/2510369/san-chong-fang-fa-dfsbfsbfslian-biao-fu-5alnq
    // 使用 list 也优雅
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        List<Node> q = List.of(root);
        while (!q.isEmpty()) {
            List<Node> tmp = q;
            q = new ArrayList<>();
            for (int i = 0; i < tmp.size(); i++) {
                Node node = tmp.get(i);
                if (i > 0) { // 连接同一层的两个相邻节点
                    tmp.get(i - 1).next = node;
                }
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }
        return root;
    }

    @Test
    public void test_1() {
        TreeNode node = BTreeUtil.createTree("[1,2,3,4,5,6,7]");
        System.out.println("connect(node) = " + connect(node));

    }

    @Data
    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    }
}
