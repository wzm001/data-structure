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
    public boolean remove(T value) {
        BinaryTreeNode<T> node = find(value);
        if (node == null) return false;
        setRoot(innerRemove(root(), node));
        size--;
        return true;
    }

    /**
     * 删除指定节点
     * <p>二叉搜索树的节点删除分为3种情况：</p>
     * <p>1. 如果要删除的节点是叶子节点，则直接把该节点在父节点的引用置为null</p>
     * <p>2. 如果要删除的节点只包含一个子节点，则让该节点的父节点把对该节点的引用重置为对该节点的子节点的引用</p>
     * <p>3. 如果要删除的节点有两个子节点，需要先遍历该节点的右子树，找到右子树上最小的节点（实际就是找到比待删除节点大的第一个节点），
     * 然后两个节点互换位置，再删除掉换位后的节点，换位节点的删除操作必然是前两种情况之一</p>
     * <p>另外需要注意，如果删除的节点正好是根节点，需要切换根节点的引用</p>
     *
     * @param root  二叉树根节点
     * @param node  要删除的节点，调用方确保该节点一定在当前二叉树上
     * @return  新的根节点，因为删除操作有可能导致根节点变化
     */
    private BinaryTreeNode<T> innerRemove(BinaryTreeNode<T> root, BinaryTreeNode<T> node) {
        BinaryTreeNode<T> parent = node.getParent();
        // 情况1
        if (node.isLeaf()) {
            if (parent == null) {
                // 删除根节点
                return null;
            } else {
                replaceChild(parent, node, null);
                return root;
            }
        }
        // 情况2
        if (node.getLeftChild() == null) {
            if (parent == null) {
                BinaryTreeNode<T> rightChild = node.getRightChild();
                rightChild.setParent(null);
                return rightChild;
            } else {
                replaceChild(parent, node, node.getRightChild());
                return root;
            }
        }
        // 情况2
        if (node.getRightChild() == null) {
            if (parent == null) {
                BinaryTreeNode<T> leftChild = node.getLeftChild();
                leftChild.setParent(null);
                return leftChild;
            } else {
                replaceChild(parent, node, node.getLeftChild());
                return root;
            }
        }
        // 情况3，先找到比待删除节点大的第一个节点，这个节点将会是最终被删掉的节点
        BinaryTreeNode<T> toBeDeletedNode = findFirstNode(node.getRightChild());
        // 更换节点值
        T value = node.getValue();
        node.setValue(toBeDeletedNode.getValue());
        toBeDeletedNode.setValue(value);
        return innerRemove(root, toBeDeletedNode);
    }

    private static <T> BinaryTreeNode<T> findFirstNode(BinaryTreeNode<T> root) {
        if (root.isLeaf() || root.getLeftChild() == null) return root;
        return findFirstNode(root.getLeftChild());
    }

    private static <T> void replaceChild(BinaryTreeNode<T> parent, BinaryTreeNode<T> oldChild, BinaryTreeNode<T> newChild) {
        if (newChild != null) {
            newChild.setParent(parent);
        }
        if (oldChild.getValue().equals(parent.getLeftChild().getValue())) {
            parent.setLeftChild(newChild);
        } else if (oldChild.getValue().equals(parent.getRightChild().getValue())) {
            parent.setRightChild(newChild);
        }
    }
}
