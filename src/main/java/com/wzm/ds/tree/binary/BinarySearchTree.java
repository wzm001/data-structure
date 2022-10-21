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
public class BinarySearchTree<T extends Comparable<T>> extends CommonBinaryTree<T> implements BinaryTree<T> {

    protected BinarySearchTree(T value) {
        super(value);
    }

    @Override
    public void add(T element) {
        if (element == null) return;
        if (this.value.compareTo(element) == 0) return;
        if (this.value.compareTo(element) > 0) {
            if (this.left == null) {
                BinarySearchTree<T> node = new BinarySearchTree<>(element);
                node.parent = this;
                this.left = node;
            } else {
                this.left.add(element);
            }
        } else {
            if (this.right == null) {
                BinarySearchTree<T> node = new BinarySearchTree<>(element);
                node.parent = this;
                this.right = node;
            } else {
                this.right.add(element);
            }
        }
    }

    @Override
    public BinaryTree<T> find(T value) {
        if (value == null) return null;
        int compareResult = this.value.compareTo(value);
        if (compareResult == 0) return this;
        if (compareResult > 0) {
            if (this.left != null) return this.left.find(value);
            else return null;
        } else {
            if (this.right != null) return this.right.find(value);
            else return null;
        }
    }

    @Override
    public boolean remove(T element) {
        throw new UnsupportedOperationException();
    }
}
