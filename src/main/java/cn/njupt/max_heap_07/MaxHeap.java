package cn.njupt.max_heap_07;

import java.util.ArrayList;
import java.util.List;

/**
 * Java实现二叉堆(最大堆)
 * @author Qin
 * 2018/1/8
 */
public class MaxHeap<T extends Comparable<T>> {
    private List<T> mHeap;

    public MaxHeap() {
        this.mHeap = new ArrayList<>();
    }

    /*
     * 最大堆的向上调整算法(从start开始向上直到0，调整堆)
     *
     * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
     * 参数说明：
     *     start -- 被上调节点的起始位置(一般为数组中最后一个元素的索引)
     */
    private void filterUp(int start) {
        // 当前位置索引
        int c = start;
        // 父节点索引
        int p = (c - 1) / 2;
        // 要添加的值
        T tmp = mHeap.get(c);

        while (c > 0) {
            int cmp = tmp.compareTo(mHeap.get(p));

            if (cmp > 0) {
                mHeap.set(c, mHeap.get(p));
                c = p;
                p = (c - 1) / 2;
            } else {
                break;
            }
        }

        mHeap.set(c, tmp);
    }

    public void insert(T data) {
        int size = mHeap.size();
        mHeap.add(data);
        filterUp(size);
    }

    /*
     * 最大堆的向下调整算法
     *
     * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
     *
     * 参数说明：
     *     start -- 被下调节点的起始位置(一般为0，表示从第1个开始)
     *     end   -- 截至范围(一般为数组中最后一个元素的索引)
     */
    private void filterDown(int start, int end) {
        int c = start;
        int l = 2 * c + 1;
        T tmp = mHeap.get(c);

        while (l <= end) {
            if (l < end) {
                int cmp = mHeap.get(l).compareTo(mHeap.get(l+1));

                if (l < end && cmp < 0) {
                    l++;
                }
            }

            int cmp = mHeap.get(c).compareTo(mHeap.get(l));
            if (cmp < 0) {
                mHeap.set(c, mHeap.get(l));
                c = l;
                l = 2 *c + 1;
            } else {
                break;
            }
        }

        mHeap.set(c, tmp);
    }

    public int remove(T data) {
        if (mHeap.isEmpty())
            return -1;

        int index = mHeap.indexOf(data);
        if (index == -1)
            return -1;

        int size = mHeap.size();
        // 用最后一位填补
        mHeap.set(index, mHeap.get(size-1));
        // 将最后一位删除
        mHeap.remove(size-1);
        if (index == size-1) {
            return 0;
        }

        if (mHeap.size() > 1)
            filterDown(index, mHeap.size()-1);

        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (T t : mHeap) {
            sb.append(t + " ");
        }

        return sb.toString();
    }
}
