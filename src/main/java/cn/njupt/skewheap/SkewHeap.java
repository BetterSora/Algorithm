package cn.njupt.skewheap;

/**
 * Java实现斜堆
 * @author Qin
 * 2018/1/16
 */
public class SkewHeap<T extends Comparable<T>> {
    private SkewHeapNode<T> mRoot;

    public class SkewHeapNode<T> {
        T key;
        SkewHeapNode<T> left;
        SkewHeapNode<T> right;

        public SkewHeapNode(T key, SkewHeapNode<T> left, SkewHeapNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "key:" + key;
        }
    }

    private SkewHeapNode<T> merge(SkewHeapNode<T> x, SkewHeapNode<T> y) {
        if (x == null) return y;
        if (y == null) return x;

        if (x.key.compareTo(y.key) > 0) {
            SkewHeapNode<T> tmp = x;
            x = y;
            y = tmp;
        }

        SkewHeapNode<T> tmp = merge(x.right, y);
        x.right = x.left;
        x.left = tmp;

        return x;
    }

    public void merge(SkewHeap<T> other) {
        mRoot = merge(mRoot, other.mRoot);
    }

    public void insert(T data) {
        SkewHeapNode<T> node = new SkewHeapNode<>(data, null, null);
        mRoot = merge(mRoot, node);
    }

    public T remove() {
        if (mRoot == null) return null;

        T key = mRoot.key;
        SkewHeapNode<T> left = mRoot.left;
        SkewHeapNode<T> right = mRoot.right;

        mRoot = merge(left, right);

        return key;
    }

    private void preOrder(SkewHeapNode<T> heap) {
        if(heap != null) {
            System.out.print(heap.key+" ");
            preOrder(heap.left);
            preOrder(heap.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    private void inOrder(SkewHeapNode<T> heap) {
        if(heap != null) {
            inOrder(heap.left);
            System.out.print(heap.key+" ");
            inOrder(heap.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    private void postOrder(SkewHeapNode<T> heap) {
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

    private void print(SkewHeapNode<T> heap, T key, int direction) {
        if (heap != null) {
            if (direction == 0)
                System.out.printf("%2d is root\n", heap.key);
            else
                System.out.printf("%2d is %2d's %6s child\n", heap.key, key, direction==1?"right":"left");

            print(heap.left, heap.key, -1);
            print(heap.right, heap.key, 1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }
}
