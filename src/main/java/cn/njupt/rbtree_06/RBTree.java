package cn.njupt.rbtree_06;

/**
 * Java实现红黑树
 * @author Qin
 * 2018/1/5
 */
public class RBTree<T extends Comparable<T>> {
    private RBTNode<T> mRoot;
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public class RBTNode<T extends Comparable<T>> {
        boolean color;
        T key;
        RBTNode<T> left;
        RBTNode<T> right;
        RBTNode<T> parent;

        public RBTNode(boolean color, T key, RBTNode<T> left, RBTNode<T> right, RBTNode<T> parent) {
            this.color = color;
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    /**
     * 左旋
     * @param x 要旋转的节点
     */
    private void leftRotate(RBTNode<T> x) {
        // 设置x的右孩子为y
        RBTNode<T> y = x.right;

        // 设置y的左孩子为x的右孩子
        x.right = y.left;
        // 如果y的左孩子不为空，设置y的父亲节点为x
        if (y.left != null) {
            y.left.parent = x;
        }

        // 将x的父亲设为y的父亲
        y.parent = x.parent;

        if (x.parent == null) {
            // 如果x的父亲是空节点，则将y设为根节点
            mRoot = y;
        } else {
            if (x.parent.left == x) {
                // 如果x是它父节点的左孩子，则将y设为x的父节点的左孩子
                x.parent.left = y;
            } else {
                // 如果x是它父节点的左孩子，则将y设为x的父节点的左孩子
                x.parent.right = y;
            }
        }

        // 将x设为y的左孩子
        y.left = x;
        // 将x的父节点设为y
        x.parent = y;
    }

    /**
     * 右旋
     * @param x 要旋转的节点
     */
    private void rightRotate(RBTNode<T> x) {
        RBTNode<T> y = x.left;

        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }

        y.parent = x.parent;
        if (x.parent == null) {
            mRoot = y;
        } else {
            if (x.parent.left == x) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }

        y.right = x;
        x.parent = y;
    }

    /**
     * 插入
     * @param node 要插入的节点
     */
    private void insert(RBTNode<T> node) {
        int cmp;
        RBTNode<T> y = null;
        RBTNode<T> x = mRoot;

        // 1.将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (x != null) {
            y = x;

            cmp = node.key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                throw new RuntimeException("不能插入相同节点");
            }
        }

        node.parent = y;
        if (y == null) {
            mRoot = node;
        } else {
            cmp = node.key.compareTo(y.key);
            if (cmp < 0) {
                y.left = node;
            } else {
                y.right = node;
            }
        }

        // 2.设置节点的颜色为红色
        setRed(node);

        // 3.将它重新修正为一颗二叉查找树
        insertFixUp(node);
    }

    public void insert(T key) {
        RBTNode<T> node = new RBTNode<>(BLACK, key, null, null, null);

        insert(node);
    }

    /**
     * 红黑树插入节点的修正
     * ① 情况说明：被插入的节点是根节点。
     * 处理方法：直接把此节点涂为黑色。
     * ② 情况说明：被插入的节点的父节点是黑色。
     * 处理方法：什么也不需要做。节点被插入后，仍然是红黑树。
     * ③ 情况说明：被插入的节点的父节点是红色。
     *  处理方法：有三种情况
     *   Case 1：当前节点的父节点是红色，且当前节点的祖父节点的另一个子节点（叔叔节点）也是红色。
     *          (01) 将“父节点”设为黑色。
     *          (02) 将“叔叔节点”设为黑色。
     *          (03) 将“祖父节点”设为“红色”。
     *          (04) 将“祖父节点”设为“当前节点”(红色节点)；即，之后继续对“当前节点”进行操作。
     *   Case 2：当前节点的父节点是红色，叔叔节点是黑色，且当前节点是其父节点的右孩子
     *          (01) 将“父节点”作为“新的当前节点”。
     *          (02) 以“新的当前节点”为支点进行左旋。
     *   Case 3：当前节点的父节点是红色，叔叔节点是黑色，且当前节点是其父节点的左孩子
     *          (01) 将“父节点”设为“黑色”。
     *          (02) 将“祖父节点”设为“红色”。
     *          (03) 以“祖父节点”为支点进行右旋。
     * @param node 要修正的节点
     */
    private void insertFixUp(RBTNode<T> node) {
        RBTNode<T> parent, gParent;

        // 如果父节点存在，并且父节点是红色(情况3)
        while (((parent = parentOf(node)) != null) && isRed(parent)) {
            gParent = parentOf(parent);

            //若“父节点”是“祖父节点的左孩子”
            if (parent == gParent.left) {
                // Case1：叔叔节点是红色
                RBTNode<T> uncle = gParent.right;

                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gParent);
                    node = gParent;
                    continue;
                }

                // Case2：叔叔节点是黑色，且当前节点是右孩子
                if (parent.right == node) {
                    leftRotate(parent);
                    RBTNode<T> tmp;
                    tmp = node;
                    node = parent;
                    parent = tmp;
                }

                // Case3：叔叔节点是黑色，且当前节点是左孩子
                setBlack(parent);
                setRed(gParent);
                rightRotate(gParent);

            } else { //若父节点是祖父节点的右孩子
                // Case1：叔叔节点是红色
                RBTNode<T> uncle = gParent.left;

                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gParent);
                    node = gParent;
                    continue;
                }

                // Case2：叔叔节点是黑色，且当前节点是左孩子
                if (parent.left == node) {
                    rightRotate(parent);
                    RBTNode<T> tmp;
                    tmp = node;
                    node = parent;
                    parent = tmp;
                }

                // Case3：叔叔节点是黑色，且当前节点是右孩子
                setBlack(parent);
                setRed(gParent);
                leftRotate(gParent);
            }
        }

        // 情况1和情况2，此时直接将根节点涂黑
        setBlack(mRoot);
    }

    /**
     * 删除
     * 第一步：将红黑树当作一颗二叉查找树，将节点删除。
     * 这和"删除常规二叉查找树中删除节点的方法是一样的"。分3种情况：
     * ① 被删除节点没有儿子，即为叶节点。那么，直接将该节点删除就OK了。
     * ② 被删除节点只有一个儿子。那么，直接删除该节点，并用该节点的唯一子节点顶替它的位置。
     * ③ 被删除节点有两个儿子。那么，先找出它的后继节点；然后把“它的后继节点的内容”复制给“该节点的内容”；
     * 之后，删除“它的后继节点”。在这里，后继节点相当于替身，在将后继节点的内容复制给"被删除节点"之后，再将后继节点删除。
     * 这样就巧妙的将问题转换为"删除后继节点"的情况了，下面就考虑后继节点。 在"被删除节点"有两个非空子节点的情况下，它的后继节点不可能是双子非空。
     * 既然"的后继节点"不可能双子都非空，就意味着"该节点的后继节点"要么没有儿子，要么只有一个儿子。若没有儿子，则按"情况① "进行处理；
     * 若只有一个儿子，则按"情况② "进行处理。
     * 第二步：通过"旋转和重新着色"等一系列来修正该树，使之重新成为一棵红黑树。
     * 因为"第一步"中删除节点之后，可能会违背红黑树的特性。所以需要通过"旋转和重新着色"来修正该树，使之重新成为一棵红黑树。
     * @param node 要删除的节点
     */
    private void remove(RBTNode<T> node) {
        RBTNode<T> child, parent;
        boolean color;

        // 被删除节点的左右孩子都不为空的情况
        if (node.left != null && node.right != null) {
            // 寻找后继节点
            RBTNode<T> replace = node.right;
            while (replace.left != null) {
                replace = replace.left;
            }

            // 记录后继节点的颜色
            color = replace.color;
            // 后继节点只能有右孩子
            child = replace.right;
            // 后继节点的父亲
            parent = replace.parent;

            if (parent == node) {
                // 因为node节点要被replace所替换
                parent = replace;
            } else {
                if (child != null) {
                    setParent(child, parent);
                }
                parent.left = child;

                replace.right = node.right;
                setParent(node.right, replace);
            }

            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;

            if (color == BLACK)
                removeFixUp(child, parent);
        }

        if (node.left != null) {
            child = node.left;
        } else {
            child = node.right;
        }

        parent = node.parent;
        color = node.color;

        if (child != null) {
            setParent(child, parent);
        }

        // node节点不是根节点
        if (parent != null) {
            // 判断node节点是父节点的左孩子还是右孩子
            if (parent.left == node) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            mRoot = child;
        }

        if (color == BLACK)
            removeFixUp(child, parent);
    }

    /**
     * 红黑树的删除修正函数
     * ① 情况说明：x是“红+黑”节点。
     * 处理方法：直接把x设为黑色，结束。此时红黑树性质全部恢复。
     * ② 情况说明：x是“黑+黑”节点，且x是根。
     * 处理方法：什么都不做，结束。此时红黑树性质全部恢复。
     * ③ 情况说明：x是“黑+黑”节点，且x不是根。
     * 处理方法：这种情况又可以划分为4种子情况。这4种子情况如下表所示：
     *      Case 1：x是"黑+黑"节点，x的兄弟节点是红色。(此时x的父节点和x的兄弟节点的子节点都是黑节点)。
     *          (01) 将x的兄弟节点设为“黑色”。
     *          (02) 将x的父节点设为“红色”。
     *          (03) 对x的父节点进行左旋。
     *          (04) 左旋后，重新设置x的兄弟节点。
     *      Case 2：x是“黑+黑”节点，x的兄弟节点是黑色，x的兄弟节点的两个孩子都是黑色。
     *          (01) 将x的兄弟节点设为“红色”。
     *          (02) 设置“x的父节点”为“新的x节点”。
     *      Case 3：x是“黑+黑”节点，x的兄弟节点是黑色；x的兄弟节点的左孩子是红色，右孩子是黑色的。
     *          (01) 将x兄弟节点的左孩子设为“黑色”。
     *          (02) 将x兄弟节点设为“红色”。
     *          (03) 对x的兄弟节点进行右旋。
     *          (04) 右旋后，重新设置x的兄弟节点。
     *      Case 4：x是“黑+黑”节点，x的兄弟节点是黑色；x的兄弟节点的右孩子是红色的，x的兄弟节点的左孩子任意颜色。
     *          (01) 将x父节点颜色 赋值给 x的兄弟节点。
     *          (02) 将x父节点设为“黑色”。
     *          (03) 将x兄弟节点的右子节设为“黑色”。
     *          (04) 对x的父节点进行左旋。
     *          (05) 设置“x”为“根节点”。
     * @param node 待修正的节点
     * @param parent 该节点的父节点
     */
    private void removeFixUp(RBTNode<T> node, RBTNode<T> parent) {
        RBTNode<T> other;

        // 情况3
        while ((node == null || isBlack(node)) && (node != mRoot)) {
            if (parent.left == node) {
                other = parent.right;

                if (isRed(other)) { // Case 1
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }

                if ((other.left==null || isBlack(other.left)) &&
                        (other.right==null || isBlack(other.right))) {
                    // Case 2
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.right==null || isBlack(other.right)) {
                        // Case 3
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    // Case 4
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.mRoot;
                    break;
                }
            } else {
                other = parent.left;
                if (isRed(other)) {
                    // Case 1
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left==null || isBlack(other.left)) &&
                        (other.right==null || isBlack(other.right))) {
                    // Case 2
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.left==null || isBlack(other.left)) {
                        // Case 3
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }

                    // Case 4
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.mRoot;
                    break;
                }
            }
        }

        if (node != null)
            setBlack(node);
    }

    public void remove(T key) {
        RBTNode<T> node;

        if ((node = search(mRoot, key)) != null) {
            remove(node);
        }
    }

    /**
     * 递归实现 查找二叉树中键值为key的节点
     */
    private RBTNode<T> search(RBTNode<T> node, T key) {
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

    public RBTNode<T> search(T key) {
        return search(mRoot, key);
    }

    private RBTNode<T> parentOf(RBTNode<T> node) {
        return node!=null ? node.parent : null;
    }

    private boolean colorOf(RBTNode<T> node) {
        return node!=null ? node.color : BLACK;
    }

    private boolean isRed(RBTNode<T> node) {
        return ((node!=null)&&(node.color==RED)) ? true : false;
    }

    private boolean isBlack(RBTNode<T> node) {
        return !isRed(node);
    }

    private void setBlack(RBTNode<T> node) {
        if (node!=null)
            node.color = BLACK;
    }

    private void setRed(RBTNode<T> node) {
        if (node!=null)
            node.color = RED;
    }

    private void setParent(RBTNode<T> node, RBTNode<T> parent) {
        if (node!=null)
            node.parent = parent;
    }

    private void setColor(RBTNode<T> node, boolean color) {
        if (node!=null)
            node.color = color;
    }

    /*
     * 打印"红黑树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(RBTNode<T> tree, T key, int direction) {

        if(tree != null) {

            if(direction==0)    // tree是根节点
                System.out.printf("%2d(B) is root\n", tree.key);
            else                // tree是分支节点
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.key, isRed(tree)?"R":"B", key, direction==1?"right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right,tree.key,  1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }

    /*
     * 前序遍历"红黑树"
     */
    private void preOrder(RBTNode<T> tree) {
        if(tree != null) {
            System.out.print(tree.key+" ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * 中序遍历"红黑树"
     */
    private void inOrder(RBTNode<T> tree) {
        if(tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key+" ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }


    /*
     * 后序遍历"红黑树"
     */
    private void postOrder(RBTNode<T> tree) {
        if(tree != null)
        {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key+" ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    /*
     * 查找最小结点：返回tree为根结点的红黑树的最小结点。
     */
    private RBTNode<T> minimum(RBTNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.left != null)
            tree = tree.left;
        return tree;
    }

    public T minimum() {
        RBTNode<T> p = minimum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    /*
     * 查找最大结点：返回tree为根结点的红黑树的最大结点。
     */
    private RBTNode<T> maximum(RBTNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.right != null)
            tree = tree.right;
        return tree;
    }

    public T maximum() {
        RBTNode<T> p = maximum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }
}
