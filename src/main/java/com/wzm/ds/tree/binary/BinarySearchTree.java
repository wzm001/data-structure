package com.wzm.ds.tree.binary;

/**
 * 二叉搜索树
 * <p>二叉搜索树指一颗空树或具有下列性质的树</p>
 * <li>若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值</li>
 * <li>若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值</li>
 * <li>任意节点的左、右子树也分别为二叉查找树</li>
 *
 * 二叉搜索树相比于其他数据结构的优势在于查找、插入的时间复杂度较低。为O(log n)
 *
 * @author wuzhiming@itiger.com
 */
public class BinarySearchTree<T extends Comparable<T>> extends CommonBinaryTree<T> {

    @Override
    public void add(T value) {
        BinaryTreeNode<T> node = new BinaryTreeNode<>(value);
        if (isEmpty()) {
            setRoot(node);
            size++;
            return;
        }
        if (innerAdd(root(), node)) {
            size++;
        }
    }

    private static <T extends Comparable<T>> boolean innerAdd(BinaryTreeNode<T> root, BinaryTreeNode<T> node) {
        int compareResult = root.getValue().compareTo(node.getValue());
        if (compareResult == 0) return false;
        if (compareResult > 0) {
            if (root.getLeftChild() == null) {
                node.setParent(root);
                root.setLeftChild(node);
                return true;
            } else {
                return innerAdd(root.getLeftChild(), node);
            }
        } else {
            if (root.getRightChild() == null) {
                node.setParent(root);
                root.setRightChild(node);
                return true;
            } else {
                return innerAdd(root.getRightChild(), node);
            }
        }
    }

    @Override
    public BinaryTreeNode<T> find(T value) {
        return innerFind(root(), value);
    }

    private static <T extends Comparable<T>> BinaryTreeNode<T> innerFind(BinaryTreeNode<T> root, T value) {
        if (root == null || value == null) return null;
        int compareResult = root.getValue().compareTo(value);
        if (compareResult == 0) return root;
        if (compareResult > 0) {
            return innerFind(root.getLeftChild(), value);
        } else {
            return innerFind(root.getRightChild(), value);
        }
    }

    @Override
    public boolean remove(T element) {
        throw new UnsupportedOperationException();
    }
}
