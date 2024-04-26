package leetcode.solution.binary_tree.util;

import leetcode.solution.binary_tree.TreeNode;

import java.util.*;

public class BinaryTreeBuilder {
    public static TreeNode buildTree(Integer[] array) {
        if (array == null || array.length == 0 || array[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for (int i = 1; i < array.length; i += 2) {
            TreeNode current = queue.poll();
            if (array[i] != null) {
                current.left = new TreeNode(array[i]);
                queue.offer(current.left);
            }

            if (i + 1 < array.length && array[i + 1] != null) {
                current.right = new TreeNode(array[i + 1]);
                queue.offer(current.right);
            }
        }

        return root;
    }

    // TreeNode class definition goes here...

    public static void main(String[] args) {
        Integer[] array = {1, null, 2, 3, 4, null, 5};
        BinaryTreeBuilder builder = new BinaryTreeBuilder();
        TreeNode root = builder.buildTree(array);

        printTree(root);

    }

    public static List<List<String>> levelOrder(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<String> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    level.add("#");
                    continue;
                }
                level.add(node.val + "");
                if (node.left != null) {
                    queue.offer(node.left);
                } else {
                    queue.offer(null);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                } else {
                    queue.offer(null);
                }
            }
            result.add(level);
        }

        return result;
    }


    public static void printTree(TreeNode root) {
        List<List<String>> lines = new ArrayList<>();
        List<TreeNode> level = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            depth++;
            int levelSize = queue.size();
            List<String> line = new ArrayList<>();
            maxWidth = Math.max(maxWidth, levelSize * 2 - 1);

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                String nodeStr = (node != null) ? String.valueOf(node.val) : " ";
                line.add(nodeStr);

                if (node != null) {
                    level.add(node.left);
                    level.add(node.right);
                } else {
                    level.add(null);
                    level.add(null);
                }
            }

            lines.add(line);

            if (allNull(level)) {
                break;
            }

            queue.addAll(level);
            level.clear();
        }

        int levelWidth = maxWidth;
        for (List<String> line : lines) {
            int spaces = (levelWidth - line.size()) / 2;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < spaces; i++) {
                sb.append(" ");
            }
            for (String nodeStr : line) {
                sb.append(nodeStr.equals(" ") ? " " : nodeStr);
                for (int i = 0; i < levelWidth - line.size() + 1; i++) {
                    sb.append(" ");
                }
            }
            System.out.println(sb.toString().trim());
            levelWidth /= 2;
        }
    }

    private static boolean allNull(List<TreeNode> list) {
        for (TreeNode node : list) {
            if (node != null) {
                return false;
            }
        }
        return true;
    }

}
