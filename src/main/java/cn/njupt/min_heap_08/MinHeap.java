package cn.njupt.min_heap_08;

import java.util.ArrayList;
import java.util.List;

/**
 * Java实现二叉堆(最小堆)
 * @author Qin
 * 2018/1/9
 */
public class MinHeap<T extends Comparable<T>> {
    private List<T> mHeap;

    public MinHeap() {
        this.mHeap = new ArrayList<>();
    }


    private void filterUp(int start) {
        int c = start;
        int p = (c - 1) / 2;
        T data = mHeap.get(c);

        while (c > 0) {
            int cmp = mHeap.get(c).compareTo(mHeap.get(p));

            if (cmp < 0) {
                mHeap.set(c, mHeap.get(p));
                c = p;
                p = (c - 1) / 2;
            } else {
                break;
            }
        }

        mHeap.set(c, data);
    }

    public void insert(T data) {
        int size = mHeap.size();
        mHeap.add(data);
        filterUp(size);
    }

    private void filterDown(int start, int end) {
        int c = start;
        int l = 2 * c + 1;
        T tmp = mHeap.get(c);

        while (l <= end) {
            if (l < end) {
                int cmp = mHeap.get(l).compareTo(mHeap.get(l+1));
                if (cmp > 0) {
                    l++;
                }
            }

            int cmp = mHeap.get(c).compareTo(mHeap.get(l));
            if (cmp > 0) {
                mHeap.set(c, mHeap.get(l));
                c = l;
                l = 2 * c + 1;
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
        mHeap.set(index, mHeap.get(size-1));
        mHeap.remove(size-1);
        if (index == size-1) {
            return 0;
        }

        if (mHeap.size() > 1) {
            filterDown(index, mHeap.size() - 1);
        }

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
