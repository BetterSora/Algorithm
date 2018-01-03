package cn.njupt.avltree;

import static java.lang.Integer.max;

/**
 * Java实现AVL树(自平衡二叉查找树)
 * @author Qin
 * 2017/12/28
 */
public class AVLTree<T extends Comparable<T>> {
    // 根节点
    private AVLTreeNode<T> mRoot;

    private class AVLTreeNode<T extends Comparable<T>> {
        T key;
        int height;
        AVLTreeNode<T> left;
        AVLTreeNode<T> right;

        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.key = key;
            this.height = 0;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 返回树的高度，空二叉树的高度为0
     * @param tree 子树
     * @return 旋转后的根节点
     */
    private int height(AVLTreeNode<T> tree) {
        if (tree != null) {
            return tree.height;
        }

        return 0;
    }

    public int height() {
        return height(mRoot);
    }

    /**
     * LL:左左对应的情况，左单旋转
     * @param k2 子树的根节点
     * @return 旋转后的根节点
     */
    private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> k2) {
        AVLTreeNode<T> k1;

        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;

        return k1;
    }

    /**
     * RR:右右对应的情况，右单旋转
     * @param k1 子树的根节点
     * @return 旋转后的根节点
     */
    private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> k1) {
        AVLTreeNode<T> k2;

        k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(k1.height, height(k2.right)) + 1;

        return k1;
    }

    /**
     * LR:左右对应的情况(左双旋转)
     * @param k3 子树的根节点
     * @return 旋转后的根节点
     */
    private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> k3) {
        k3.left = rightRightRotation(k3.left);

        return leftLeftRotation(k3);
    }

    /**
     * RL:右左对应的情况(右双旋转)
     * @param k1 子树的根节点
     * @return 旋转后的根节点
     */
    private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> k1) {
        k1.right = leftLeftRotation(k1.right);

        return rightRightRotation(k1);
    }

    /**
     *
     * @param tree AVL树的根节点
     * @param key 插入的节点的键值
     * @return 根节点
     */
    private AVLTreeNode<T> insert(AVLTreeNode<T> tree, T key) {
        if (tree == null) {
            tree = new AVLTreeNode<T>(key, null, null);
        } else {
            int cmp = key.compareTo(tree.key);

            if (cmp < 0) {
                // 将key插入到tree的左子树
                tree.left = insert(tree.left, key);

                // 判断插入后树是否失去平衡
                if ((height(tree.left) - height(tree.right)) == 2) {
                    if (key.compareTo(tree.left.key) < 0) {
                        tree = leftLeftRotation(tree);
                    } else {
                        tree = leftRightRotation(tree);
                    }
                }
            } else if (cmp > 0) {
                // 将key插入到tree的右子树
                tree.right = insert(tree.right, key);

                // 判断树是否失去平衡
                if ((height(tree.right) - height(tree.left)) == 2) {
                    if (key.compareTo(tree.right.key) < 0) {
                        tree = rightLeftRotation(tree);
                    } else {
                        tree = rightRightRotation(tree);
                    }
                }
            } else {
                System.out.println("添加失败，不能添加相同的节点");
            }
        }

        tree.height = max(height(tree.left), height(tree.right)) + 1;

        return tree;
    }

    public void insert(T key) {
        mRoot = insert(mRoot, key);
    }

    /**
     * 删除节点
     * @param tree 根节点
     * @param z 待删除的节点
     * @return 根节点
     */
    private AVLTreeNode<T> remove(AVLTreeNode<T> tree, AVLTreeNode<T> z) {
        if (tree == null || z == null) {
            return null;
        }

        int cmp = z.key.compareTo(tree.key);

        if (cmp < 0) { // 待删除的节点在tree的左子树中
            tree.left = remove(tree.left, z);

            // 判断删除节点后树是否失去平衡
            if ((height(tree.right) - height(tree.left)) == 2) {
                AVLTreeNode<T> r = tree.right;
                if (height(r.left) > height(r.right)) {
                    tree = rightLeftRotation(tree);
                } else {
                    tree = rightRightRotation(tree);
                }
            }
        } else if (cmp > 0) { // 待删除的节点在tree的右子树中
            tree.right = remove(tree.right, z);

            // 判断删除节点后树是否失去平衡
            if ((height(tree.left) - height(tree.right)) == 2) {
                AVLTreeNode<T> r = tree.left;
                if (height(r.left) > height(r.right)) {
                    tree = leftLeftRotation(tree);
                } else {
                    tree = leftRightRotation(tree);
                }
            }
        } else { // tree就是要删除的节点
            // tree的左右孩子都非空
            if ((tree.left!=null) && (tree.right!=null)) {
                if (height(tree.left) > height(tree.right)) {
                    // 如果tree的左子树比右子树高；
                    // 则(01)找出tree的左子树中的最大节点
                    //   (02)将该最大节点的值赋值给tree。
                    //   (03)删除该最大节点。
                    // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
                    AVLTreeNode<T> max = maximum(tree.left);
                    tree.key = max.key;
                    tree.left = remove(tree.left, max);
                } else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
                    AVLTreeNode<T> min = minimum(tree.right);
                    tree.key = min.key;
                    tree.right = remove(tree.right, min);
                }
            } else {
                tree = (tree.left != null) ? tree.left : tree.right;
            }
        }

        if (tree != null) {
            tree.height = max(height(tree.left), height(tree.right)) + 1;
        }

        return tree;
    }

    public void remove(T key) {
        AVLTreeNode<T> z;

        if ((z = search(key)) != null)
            mRoot = remove(mRoot, z);
    }

    /**
     * 查找最大节点
     * @param node 根节点
     * @return 最大节点
     */
    private AVLTreeNode<T> maximum(AVLTreeNode<T> node) {
        AVLTreeNode<T> maxNode = null;

        while (node != null) {
            maxNode = node;
            node = node.right;
        }

        return maxNode;
    }

    public T maximum() {
        AVLTreeNode<T> n = maximum(mRoot);

        if (n != null) {
            return n.key;
        } else {
            return null;
        }
    }

    /**
     * 查找最小节点
     * @param node 根节点
     * @return 最小节点
     */
    private AVLTreeNode<T> minimum(AVLTreeNode<T> node) {
        AVLTreeNode<T> minNode = null;

        while (node != null) {
            minNode = node;
            node = node.left;
        }

        return minNode;
    }

    public T minimum() {
        AVLTreeNode<T> n = minimum(mRoot);

        if (n != null) {
            return n.key;
        } else {
            return null;
        }
    }

    /**
     * 前序遍历 中左右
     * @param node 根节点
     */
    private void preOrder(AVLTreeNode<T> node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /**
     * 中序遍历 左中右
     * @param node 根节点
     */
    private void inOrder(AVLTreeNode<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    /**
     * 后序遍历 左右中
     * @param node 根节点
     */
    private void postOrder(AVLTreeNode<T> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    /**
     * 递归实现 查找二叉树中键值为key的节点
     * @param node 根节点
     * @param key 要查找节点的键值
     * @return 找到的节点
     */
    private AVLTreeNode<T> search(AVLTreeNode<T> node, T key) {
        if (node == null) {
            return node;
        }

        int tmp = key.compareTo(node.key);
        if (tmp < 0) {
            return search(node.left, key);
        } else if (tmp > 0) {
            return search(node.right, key);
        } else {
            return node;
        }
    }

    public AVLTreeNode<T> search(T key) {
        return search(mRoot, key);
    }
}
