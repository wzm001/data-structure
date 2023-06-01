package com.wzm.algo.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * 树的工具类
 *
 * @author wuzhiming@itiger.com
 */
public class Trees {

    /**
     * 判断二叉树是否是平衡二叉树
     * <p>平衡二叉树：指二叉树的任意节点的左右子树高度差不超过 1</p>
     * @param root  二叉树的根节点
     * @return  true/false
     */
    public static boolean isBalanced(TreeNode<?> root) {
        // 使用递归实现
        if (root == null) return true; // 空树定义为平衡的，这样方便递归判断
        if (root.isLeaf()) return true; // 叶子节点也是平衡的
        // 当前节点是平衡二叉树的定义是：左右子树都是平衡二叉树，而且左右子树的高度差不超过 1
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 判断二叉树是否对称（对称表示二叉树的左右子树互为镜像）
     * <p>判断逻辑：层序遍历，对比每一层的元素是否对称，如果不对称，说明二叉树也不对称</p>
     * @param root  根节点
     * @return  true/false
     */
    public static <T> boolean isSymmetric(TreeNode<T> root) {
        if (root == null) return false;
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<T> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode<T> node = queue.poll();
                list.add(node == null ? null : node.value);
                if (node != null) {
                    queue.offer(node.left); // 空节点也放到队列中
                    queue.offer(node.right);
                }
            }
            // 判断list是否对称
            for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
                if (!Objects.equals(list.get(i), list.get(j))) {
                    return false; // 不对称
                }
            }
        }
        return true;
    }

    /**
     * 在控制台上打印二叉树
     * @param root  根节点
     */
    public static void print(TreeNode<?> root) {
        List<List<String>> lines = new ArrayList<>();

        List<TreeNode<?>> level = new ArrayList<>();
        List<TreeNode<?>> next = new ArrayList<>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();

            nn = 0;

            for (TreeNode<?> n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.value.toString();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.left);
                    next.add(n.right);

                    if (n.left != null) nn++;
                    if (n.right != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<TreeNode<?>> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }

    /**
     * 统计二叉树节点数量
     * @param root  根节点
     * @return  节点数量
     */
    public static int size(TreeNode<?> root) {
        if (root == null) return 0;
        if (root.isLeaf()) return 1;
        if (isCompletedBinaryTree(root)) {
            // 统计完全二叉树的节点数量，完全二叉树和满二叉树有更高效率的统计方式
            return sizeOfCompletedBinaryTree(root);
        }
        // 统计普通二叉树的节点数量
        return sizeOfCommonBinaryTree(root);
    }

    private static int sizeOfCompletedBinaryTree(TreeNode<?> root) {
        if (root == null) return 0;
        if (root.isLeaf()) return 1;
        // 判断当前子树是否是满二叉树（完全二叉树一定有是满二叉树的子树）
        TreeNode<?> left = root.left;
        TreeNode<?> right = root.right;
        int leftDepth = 1, rightDepth = 1; // 定义为1，方便后续计算
        // 判断当前节点对应的子树是否为满二叉树
        while (left != null) {
            leftDepth++;
            left = left.left;
        }
        while (right != null) {
            rightDepth++;
            right = right.right;
        }
        if (leftDepth == rightDepth) {
            // 当前节点对应的子树为满二叉树，直接计算节点数量并返回
            return (2 << (leftDepth - 1)) - 1;
        }
        // 如果不是满二叉树，则计算左右子树的节点数量
        return 1 + sizeOfCompletedBinaryTree(root.left) + sizeOfCompletedBinaryTree(root.right);
    }

    private static int sizeOfCommonBinaryTree(TreeNode<?> root) {
        if (root == null) return 0;
        if (root.isLeaf()) return 1;
        return 1 + sizeOfCommonBinaryTree(root.left) + sizeOfCommonBinaryTree(root.right);
    }

    /**
     * 判断二叉树是否是完美二叉树（满二叉树）
     * 
     * <p>完美二叉树(Perfect binary tree)又叫满二叉树，指的是所有非叶子节点都有两个子节点，所有叶子节点都在同一层的树</p>
     * @param root  根节点
     * @return  true/false
     */
    public static boolean isPerfectBinaryTree(TreeNode<?> root) {
        if (!isCompletedBinaryTree(root)) {
            // 如果不是完全二叉树，肯定不是满二叉树
            return false;
        }
        // 完全二叉树左右深度相等，说明是满二叉树
        int leftDepth = 0, rightDepth = 0;
        TreeNode<?> left = root, right = root;
        while (left != null) {
            left = left.left;
            leftDepth++;
        }
        while (right != null) {
            right = right.right;
            rightDepth++;
        }
        return leftDepth == rightDepth;
    }

    /**
     * 判断二叉树是否是完全二叉树
     * <p>完全二叉树(Complete binary tree)，满二叉树去掉最右边的若干个连续叶子节点后，成为完全二叉树</p>
     * <p>满二叉树一定是完全二叉树(Complete binary tree)</p>
     * <p>判断逻辑：二叉树层序遍历，如果遍历到null之后又遍历了非空的元素，则说明不是完全二叉树，因为完全二叉树层序遍历中间不会有空元素</p>
     *
     * @param root 根节点
     * @return true/false
     */
    public static boolean isCompletedBinaryTree(TreeNode<?> root) {
        if (root == null) return false; // 空树可以是完全二叉树，也可以不是，这里返回 false
        Queue<TreeNode<?>> queue = new LinkedList<>();
        queue.offer(root);
        boolean meetNull = false;
        while (!queue.isEmpty()) {
            TreeNode<?> node = queue.poll();
            if (node == null) {
                meetNull = true;
                continue;
            }
            // 如果之前遇到过空节点，则说明不是完全二叉树
            if (meetNull) return false;
            queue.offer(node.left);
            queue.offer(node.right);
        }
        // 遍历完没有返回，说明是完全二叉树
        return true;
    }

    /**
     * 计算二叉树中某节点的深度(二叉树元素不重复)
     * <p>深度指的是从根节点到该节点的路径长度（可以是变得条数或者节点的个数，取决于不同的定义）</p>
     * <p>本方法统计节点的个数。相应的边的数量是节点数量-1</p>
     * <p>深度的计算可以使用前序遍历</p>
     * @param root  二叉树的根节点
     * @param node  需要计算深度的节点，如果该节点不在对应的二叉树中，返回深度为 -1
     * @return  节点相对根节点的深度，如果二叉树中不存在该节点，则返回 -1
     */
    public static int depth(TreeNode<?> root, TreeNode<?> node) {
        if (root == null || node == null) return -1;
        if (Objects.equals(root.value, node.value)) return 1; // 根节点深度为 1
        // 分别计算左子树和右子树的深度，当前深度就是子树深度 + 1
        int leftDepth = depth(root.left, node);
        int rightDepth = depth(root.right, node);
        if (leftDepth == -1 && rightDepth == -1) return -1; // 没有找对对应节点
        return Math.max(leftDepth, rightDepth) + 1; // 二叉树的元素不重复，所以 leftDepth 和 rightDepth 必然有一个等于 -1，因此获取最大的深度即可
    }

    /**
     * 计算二叉树中某节点的高度(二叉树元素不重复)
     * <p>高度指的是从该节点到叶子节点的最长路径（边的条数或节点的个数，取决于不同的定义）</p>
     * <p>本方法按照叶子节点的高度为 1 进行计算，统计的是节点的个数，相应的边的数量是节点数量-1</p>
     * <p>根节点的高度也就是根节点对应的树的最大深度</p>
     * @param root  节点，也可以理解为以该节点为根的二叉树，这样计算出的高度就是对应二叉树的高度
     * @return  节点的高度，如果节点为空，则返回 0
     */
    public static int height(TreeNode<?> root) {
        if (root == null) return 0; // 空节点高度为0
        if (root.isLeaf()) return 1; // 叶子节点的高度为1
        // 通过递归求解：当前节点的高度=左右子树的最大高度+1（相当于后序遍历）
        return 1 + Math.max(height(root.left), height(root.right));
    }

    /**
     * 构造通用二叉树
     * @param elements  元素，按照完全二叉树层序遍历的顺序传入，空节点为null
     * @return  头节点
     * @param <T>   元素类型
     */
    public static <T> TreeNode<T> buildBinaryTree(List<T> elements) {
        if (elements == null || elements.isEmpty()) return null;
        // 去掉 elements 前面和后面的 null 元素，只保留中间的
        int start = 0, end = elements.size() - 1;
        while (start < elements.size() && elements.get(start) == null) {
            start++;
        }
        while (end >= 0 && elements.get(end) == null) {
            end--;
        }
        if (start > end) {
            // 说明链表都是空元素
            return null;
        }
        if (start != 0 || end != elements.size() - 1) {
            elements = elements.subList(start, end + 1);
        }
        // 使用队列按照层序初始化二叉树
        Queue<TreeNode<T>> queue = new LinkedList<>();
        // 先初始化根节点
        TreeNode<T> root = new TreeNode<>(elements.get(0));
        queue.offer(root);
        int index = 1;
        while (index < elements.size() && !queue.isEmpty()) {
            // 从队列中取出一个节点，然后初始化左右节点，并加入队列
            TreeNode<T> node = queue.poll();
            T left = elements.get(index++);
            // 注意这里需要判断 index 的位置，防止出现溢出
            T right = null;
            if (index < elements.size()) {
                right = elements.get(index++);
            }
            if (left != null) {
                TreeNode<T> leftNode = new TreeNode<>(left);
                node.left = leftNode;
                queue.offer(leftNode);
            }
            if (right != null) {
                TreeNode<T> rightNode = new TreeNode<>(right);
                node.right = rightNode;
                queue.offer(rightNode);
            }
        }
        return root;
    }
}
