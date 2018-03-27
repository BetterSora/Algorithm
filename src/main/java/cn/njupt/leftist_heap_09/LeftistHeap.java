package cn.njupt.leftist_heap_09;

/**
 * Java实现左倾堆
 * @author Qin
 * 2018/1/12
 */
public class LeftistHeap<T extends Comparable<T>> {
    private LeftistHeapNode<T> mRoot;

    public class LeftistHeapNode<T extends Comparable<T>> {
        // 键值
        T key;
        // 零距离长度(null path length)
        int npl;
        // 左孩子
        LeftistHeapNode<T> left;
        // 右孩子
        LeftistHeapNode<T> right;

        public LeftistHeapNode(T key, LeftistHeapNode<T> left, LeftistHeapNode<T> right) {
            this.key = key;
            // 叶节点的npl为0，null节点的npl为-1
            this.npl = 0;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "key:" + key;
        }
    }

    /**
     * 合并左倾堆
     * @param x 左倾堆x
     * @param y 左倾堆y
     * @return 返回合并后的根节点
     */
    private LeftistHeapNode<T> merge(LeftistHeapNode<T> x, LeftistHeapNode<T> y) {
        // 退出递归的条件
        if (x == null) return y;
        if (y == null) return x;

        // 保证x的key小于y的key
        if (x.key.compareTo(y.key) > 0) {
            LeftistHeapNode<T> tmp = x;
            x = y;
            y = tmp;
        }

        x.right = merge(x.right, y);

        // 若右孩子的npl大于左孩子的npl，则交换左右孩子
        if (x.left == null || x.right.npl > x.left.npl) {
            LeftistHeapNode<T> tmp = x.left;
            x.left = x.right;
            x.right = tmp;
        }

        // 设置新堆根节点的npl
        if (x.left == null || x.right == null)
            x.npl = 0;
        else
            x.npl = x.right.npl + 1;

        return x;
    }

    public void merge(LeftistHeap<T> other) {
        mRoot = merge(mRoot, other.mRoot);
    }

    /**
     * 添加节点
     * @param key 节点的键值
     */
    public void insert(T key) {
        LeftistHeapNode<T> node = new LeftistHeapNode<>(key, null, null);
        mRoot = merge(mRoot, node);
    }

    /**
     * 删除根节点
     * @return 返回被删除的根节点
     */
    public T remove() {
        if (mRoot == null) return null;

        T key = mRoot.key;
        LeftistHeapNode<T> left = mRoot.left;
        LeftistHeapNode<T> right = mRoot.right;

        mRoot = merge(left, right);

        return key;
    }

    /*
     * 前序遍历"左倾堆"
     */
    private void preOrder(LeftistHeapNode<T> heap) {
        if(heap != null) {
            System.out.print(heap.key+" ");
            preOrder(heap.left);
            preOrder(heap.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * 中序遍历"左倾堆"
     */
    private void inOrder(LeftistHeapNode<T> heap) {
        if(heap != null) {
            inOrder(heap.left);
            System.out.print(heap.key+" ");
            inOrder(heap.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    /*
     * 后序遍历"左倾堆"
     */
    private void postOrder(LeftistHeapNode<T> heap) {
        if(heap != null)
        {
            postOrder(heap.left);
            postOrder(heap.right);
            System.out.print(heap.key+" ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    /*
     * 打印"左倾堆"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(LeftistHeapNode<T> heap, T key, int direction) {
        // 递归退出的条件
        if(heap != null) {

            if(direction==0)    // heap是根节点
                System.out.printf("%2d(%d) is root\n", heap.key, heap.npl);
            else                // heap是分支节点
                System.out.printf("%2d(%d) is %2d's %6s child\n", heap.key, heap.npl, key, direction==1?"right" : "left");

            print(heap.left, heap.key, -1);
            print(heap.right, heap.key,  1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }
}
