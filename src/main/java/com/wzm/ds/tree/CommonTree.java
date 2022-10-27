package com.wzm.ds.tree;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wuzhiming@itiger.com
 */
public class CommonTree<T> implements Tree<T> {

    private TreeNode<T> root;
    protected int size;

    @Override
    public TreeNode<T> root() {
        return root;
    }

    protected void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    @Override
    public int height() {
        if (isEmpty()) return -1;
        return heightOfNode(root);
    }

    private static int heightOfNode(TreeNode<?> root) {
        if (root.isLeaf()) return 0;
        int subHeight = 0;
        for (TreeNode<?> subNode : root.children) {
            if (subNode != null) {
                subHeight = Math.max(subHeight, heightOfNode(subNode));
            }
        }
        return subHeight + 1;
    }

    @Override
    public boolean contains(T value) {
        return findNode(root, value) != null;
    }

    @Override
    public TreeNode<T> find(T value) {
        return findNode(root, value);
    }

    private static <T> TreeNode<T> findNode(TreeNode<T> root, T value) {
        if (root == null || value == null) return null;
        if (root.getValue().equals(value)) return root;
        if (root.isLeaf()) return null;
        for (TreeNode<T> child : root.getChildren()) {
            TreeNode<T> node = findNode(child, value);
            if (node != null) return node;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean isBalance() {
        if (isEmpty()) return false;
        return isBalanceNode(root);
    }

    private boolean isBalanceNode(TreeNode<T> root) {
        // 空节点和叶子节点都是平衡的子树
        if (root == null || root.isLeaf()) return true;
        int minLevel = -1, maxLevel = -1;
        for (TreeNode<T> child : root.getChildren()) {
            if (!isBalanceNode(child)) return false;
            int level = levelOfNode(child);
            if (minLevel < 0) {
                minLevel = level;
                maxLevel = level;
            } else {
                minLevel = Math.min(minLevel, level);
                maxLevel = Math.max(maxLevel, level);
            }
        }
        return maxLevel - minLevel < 2;
    }

    private static int levelOfNode(TreeNode<?> node) {
        if (node == null) return 0; // 空树的层数为0
        return heightOfNode(node) + 1;
    }

    @Override
    public Iterator<TreeNode<T>> iterator() {
        return new TreeIterator<>(this, TreeIterator.PRE_ORDER_MODE);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName()).append(", size=").append(size).append("\n");
        List<PrintNode> list = new ArrayList<>(size);
        initPrintNodes(list, null, root, null);
        for (PrintNode printNode : list) {
            sb.append(printNode);
        }
        return sb.toString();
    }

    private static final class PrintNode {
        TreeNode<?> node;
        PrintNode parent;
        Boolean isLast;

        public PrintNode(TreeNode<?> node, PrintNode parent, Boolean isLast) {
            this.node = node;
            this.parent = parent;
            this.isLast = isLast;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (isLast != null) {
                if (isLast) {
                    sb.append("└── ");
                } else {
                    sb.append("├── ");
                }
            }
            if (node == null) {
                sb.append("(NUL)");
            } else {
                sb.append(node.value);
            }
            PrintNode temp = parent;
            while (temp != null) {
                if (temp.isLast != null) {
                    if (temp.isLast) {
                        sb.insert(0, "    ");
                    } else {
                        sb.insert(0, "│   ");
                    }
                }
                temp = temp.parent;
            }
            sb.append("\n");
            return sb.toString();
        }
    }

    private static void initPrintNodes(@NotNull List<PrintNode> list, PrintNode parent, TreeNode<?> node, Boolean isLast) {
        PrintNode printNode = new PrintNode(node, parent, isLast);
        list.add(printNode);
        if (node == null || node.isLeaf()) {
            return;
        }
        for (int i = 0; i < node.children.size(); i++) {
            initPrintNodes(list, printNode, node.children.get(i), i == node.children.size() - 1);
        }
    }
}
