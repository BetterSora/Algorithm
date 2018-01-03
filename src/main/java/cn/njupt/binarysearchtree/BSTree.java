package cn.njupt.binarysearchtree;

/**
 * Java实现二叉查找树
 * @author Qin
 * 2017/12/24
 */
public class BSTree<T extends Comparable<T>> {
    private BSTNode<T> mRoot;

    private class BSTNode<T> {
        T key;
        BSTNode<T> left;
        BSTNode<T> right;
        BSTNode<T> parent;

        public BSTNode(T key, BSTNode<T> left, BSTNode<T> right, BSTNode<T> parent) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    /**
     * 前序遍历 中左右
     */
    private void preOrder(BSTNode<T> node) {
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
     */
    private void inOrder(BSTNode<T> node) {
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
     */
    private void postOrder(BSTNode<T> node) {
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
     */
    private BSTNode<T> search(BSTNode<T> node, T key) {
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

    public BSTNode<T> search(T key) {
        return search(mRoot, key);
    }

    /**
     * 非递归实现 查找二叉树中键值为key的节点
     */
    private BSTNode<T> iterativeSearch(BSTNode<T> node, T key) {
        while (node != null) {
            int tmp = key.compareTo(node.key);

            if (tmp < 0) {
                node = node.left;
            } else if (tmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }

        return node;
    }

    public BSTNode<T> iterativeSearch(T key) {
        return iterativeSearch(mRoot, key);
    }

    /*
     * 查找最大结点：返回tree为根结点的二叉树的最大结点。
     */
    private BSTNode<T> maximum(BSTNode<T> node) {
        BSTNode<T> maxNode = null;

        while (node != null) {
            maxNode = node;
            node = node.right;
        }

        return maxNode;
    }

    public T maximum() {
        BSTNode<T> n = maximum(mRoot);

        if (n != null) {
            return n.key;
        } else {
            return null;
        }
    }

    /*
     * 查找最小结点：返回tree为根结点的二叉树的最小结点。
     */
    private BSTNode<T> minimum(BSTNode<T> node) {
        BSTNode<T> minNode = null;

        while (node != null) {
            minNode = node;
            node = node.left;
        }

        return minNode;
    }

    public T minimum() {
        BSTNode<T> n = minimum(mRoot);

        if (n != null) {
            return n.key;
        } else {
            return null;
        }
    }

    /**
     * 查找节点的前驱结点：小于当前节点的最大值
     */
    public BSTNode<T> predecessor(BSTNode<T> x) {
        // 如果节点x有左孩子，则x的前驱结点为以它的的左孩子为根的子树的最大节点
        if (x.left != null) {
            return maximum(x.left);
        }

        // 如果x没有左孩子。则x有以下两种可能：
        // x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"
        // x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"
        BSTNode<T> y = x.parent;
        // 不满足这个条件说明节点有右孩子，即为满足条件的最低父节点
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    /**
     * 查找节点的后继节点：大于当前节点的最小值
     */
    public BSTNode successor(BSTNode<T> x) {
        // 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"
        if (x.right != null) {
            return minimum(x.right);
        }

        // 如果x没有右孩子。则x有以下两种可能：
        // x是"一个左孩子"，则"x的后继结点"为 "它的父结点"
        // x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"
        BSTNode<T> y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    /**
     * 插入节点
     */
    private void insert(BSTree<T> bst, BSTNode<T> z) {
        // 用来记录插入节点Z的根节点
        BSTNode<T> y = null;
        BSTNode<T> x = bst.mRoot;
        int tmp;

        // 查找插入节点z的根节点
        while (x != null) {
            y = x;

            tmp = z.key.compareTo(x.key);
            if (tmp < 0) {
                x = x.left;
            } else if (tmp > 0) {
                x = x.right;
            } else {
                throw new RuntimeException("不能插入重复节点");
            }
        }

        z.parent = y;
        if (y == null) {
            bst.mRoot = z;
        } else {
            tmp = z.key.compareTo(y.key);
            if (tmp < 0) {
                y.left = z;
            } else {
                y.right = z;
            }
        }
    }

    public void insert(T key) {
        BSTNode<T> node = new BSTNode<>(key, null, null, null);

        insert(this, node);
    }

    /**
     * 删除节点，并返回被删除的节点
     */
    private BSTNode<T> remove(BSTree<T> tree, BSTNode<T> wantDelete) {
        // 被删节点的全量子树：把树理解成双向链表来处理
        BSTNode<T> realDeleteChildren = null;

        // 真正被删除的节点
        BSTNode<T> realDelete = null;

        // ① wantDelete.left = wantDelete.right = null(只有左子树)
        // ② wantDelete.left = null wantDelete.right != null(只有右子树)
        // ③ wantDelete.left ！= null wantDelete.right = null(独立节点)
        // 此种情况真正删除的节点realDelete就是wantDelete
        if ((wantDelete.left == null) || (wantDelete.right == null)) {
            realDelete = wantDelete;
        }
        else {
            // 删除节点wantDelete具有双子树，在这种情况下隐晦的删除动作是删除转移,转移为删除realDelete
            // 寻找realDelete则为寻找后继节点。因为后继节点是大于当前节点的最小值
            // 而且当前节点的右子树不为空，所以最终获取的realDelete肯定没有左子树了！也即realDelete.left = null，但是有没有右子树不可确定
            // ① realDelete.left = realDelete.right = null
            // ② realDelete.left = null realDelete.right != null
            realDelete = successor(wantDelete);
        }

        // 获取真正删除节点的子树
        if (realDelete.left != null) {
            realDeleteChildren = realDelete.left;
        }
        else {
            realDeleteChildren = realDelete.right;
        }

        // 做隐晦删除
        if (realDeleteChildren != null)
            realDeleteChildren.parent = realDelete.parent;

        // 进行焊接
        if (realDelete.parent == null) {
            // 情况一二中的特殊情况，情况三不会出现这种情况。
            tree.mRoot = realDeleteChildren;
        }
        else if (realDelete == realDelete.parent.left) {
            realDelete.parent.left = realDeleteChildren;
        }
        else {
            realDelete.parent.right = realDeleteChildren;
        }

        // 情况三特殊，因为情况三的删除是删除转移！所以删除个替身，那么留下的node要使用替身的身份证key
        if (realDelete != wantDelete)
            wantDelete.key = realDelete.key;

        return realDelete;
    }

    public void remove(T key) {
        BSTNode<T> node;

        if ((node = search(mRoot, key)) != null) {
            remove(this, node);
        }
    }
}
