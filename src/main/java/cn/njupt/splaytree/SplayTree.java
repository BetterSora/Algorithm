package cn.njupt.splaytree;

/**
 * Java实现伸展树
 * @author Qin
 * 2018/1/2
 */
public class SplayTree<T extends Comparable<T>> {
    private SplayTreeNode<T> mRoot;

    public class SplayTreeNode<T extends Comparable<T>> {
        T key;
        SplayTreeNode<T> left;
        SplayTreeNode<T> right;

        public SplayTreeNode(T key, SplayTreeNode<T> left, SplayTreeNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public SplayTreeNode() {
            this.left = null;
            this.right = null;
        }
    }


    /*
     * 旋转key对应的节点为根节点，并返回根节点。
     *
     * 注意：
     *   (a)：伸展树中存在"键值为key的节点"。
     *          将"键值为key的节点"旋转为根节点。
     *   (b)：伸展树中不存在"键值为key的节点"，并且key < tree.key。
     *      b-1 "键值为key的节点"的前驱节点存在的话，将"键值为key的节点"的前驱节点旋转为根节点。
     *      b-2 "键值为key的节点"的前驱节点不存在的话，则意味着，key比树中任何键值都小，那么此时，将最小节点旋转为根节点。
     *   (c)：伸展树中不存在"键值为key的节点"，并且key > tree.key。
     *      c-1 "键值为key的节点"的后继节点存在的话，将"键值为key的节点"的后继节点旋转为根节点。
     *      c-2 "键值为key的节点"的后继节点不存在的话，则意味着，key比树中任何键值都大，那么此时，将最大节点旋转为根节点。
     *
     * @param tree 根节点
     * @param key 键值
     * @return 旋转后的根节点
     */
    private SplayTreeNode<T> splay(SplayTreeNode<T> tree, T key) {
        if (tree == null) {
            return tree;
        }

        SplayTreeNode<T> N = new SplayTreeNode<T>();
        SplayTreeNode<T> l = N;
        SplayTreeNode<T> r = N;
        // 辅助节点
        SplayTreeNode<T> c;

        while (true) {
            int tmp = key.compareTo(tree.key);
            if (tmp < 0) {
                if (tree.left == null) {
                    break;
                }

                if (key.compareTo(tree.left.key) < 0) { // 右旋
                    c = tree.left;
                    tree.left = c.right;
                    c.left = tree;
                    // 此时的根节点变成了c
                    tree = c;
                    if (tree.left == null) {
                        break;
                    }
                }
                // 右连接
                r.left = tree;
                r = tree;
                tree = tree.left;
            } else if (tmp > 0) {
                if (tree.right == null) {
                    break;
                }

                if (key.compareTo(tree.right.key) > 0) { //左旋
                    c = tree.right;
                    tree.right = c.left;
                    c.left = tree;
                    tree = c;
                    if (tree.right == null)
                        break;
                }

                // 左连接
                l.right = tree;
                l = tree;
                tree = tree.right;
            } else {
                break;
            }
        }

        // 组合
        l.right = tree.left;
        r.left = tree.right;
        tree.left = N.right;
        tree.right = N.left;

        return tree;
    }


    public void splay(T key) {
        mRoot = splay(mRoot, key);
    }

    private SplayTreeNode<T> insert(SplayTreeNode<T> tree, SplayTreeNode<T> z) {
        int cmp;
        SplayTreeNode<T> y = null;
        SplayTreeNode<T> x = tree;

        // 查找z的插入位置
        while (x != null) {
            y = x;
            cmp = z.key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else {
                System.out.printf("不允许插入相同节点(%d)!\n", z.key);

                return tree;
            }
        }

        if (y == null)
            tree = z;
        else {
            cmp = z.key.compareTo(y.key);
            if (cmp < 0)
                y.left = z;
            else
                y.right = z;
        }

        return tree;
    }

    public void insert(T key) {
        SplayTreeNode<T> z= new SplayTreeNode<T>(key,null,null);

        // 插入节点
        mRoot = insert(mRoot, z);

        // 将节点(key)旋转为根节点
        mRoot = splay(mRoot, key);
    }

    private SplayTreeNode<T> remove(SplayTreeNode<T> tree, T key) {
        SplayTreeNode<T> x;

        if (tree == null)
            return null;

        // 查找键值为key的节点，找不到的话直接返回。
        if (search(tree, key) == null)
            return tree;

        // 将key对应的节点旋转为根节点。
        tree = splay(tree, key);

        if (tree.left != null) {
            // 将"tree的前驱节点"旋转为根节点
            x = splay(tree.left, key);
            // 移除tree节点
            x.right = tree.right;
        }
        else
            x = tree.right;

        return x;
    }

    public void remove(T key) {
        mRoot = remove(mRoot, key);
    }

    private SplayTreeNode<T> search(SplayTreeNode<T> node, T key) {
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

    public SplayTreeNode<T> search(T key) {
        return search(mRoot, key);
    }

    private SplayTreeNode<T> maximum(SplayTreeNode<T> tree) {
        SplayTreeNode<T> maxNode = null;

        while (tree != null) {
            maxNode = tree;
            tree = tree.right;
        }

        return maxNode;
    }

    public SplayTreeNode<T> maximum() {
        return maximum(mRoot);
    }

    private SplayTreeNode<T> minimum(SplayTreeNode<T> tree) {
        SplayTreeNode<T> minNode = null;

        while (tree != null) {
            minNode = tree;
            tree = tree.left;
        }

        return minNode;
    }

    public SplayTreeNode<T> minimum() {
        return minimum(mRoot);
    }

    /**
     * 前序遍历 中左右
     */
    private void preOrder(SplayTreeNode<T> node) {
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
    private void inOrder(SplayTreeNode<T> node) {
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
    private void postOrder(SplayTreeNode<T> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }
}
