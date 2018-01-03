package cn.njupt.arrayqueue;

import java.lang.reflect.Array;

/**
 * Java:数组实现队列，可以存储任意数据
 * @author Qin
 * 2017/12/23
 */
public class ArrayQueue<T> {
    private T[] mArray;
    private static final int DEFAULT_SIZE = 12;
    private int count;

    public ArrayQueue(Class<T> type, int size) {
        System.out.println("带两个参数的构造器");
        mArray = (T[]) Array.newInstance(type, size);
        count = 0;
    }

    public ArrayQueue(Class<T> type) {
        this(type, DEFAULT_SIZE);
    }

    // 返回队列的大小
    public int size() {
        return count;
    }

    // 返回队列是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 将val添加到队列的末尾
    public void add(T value) {
        mArray[count++] = value;
    }

    // 返回队首元素
    public T front() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty!");
        }

        return mArray[0];
    }

    // 返回队首元素并删除
    public T pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty!");
        }

        T res = mArray[0];
        for (int i = 1; i < count; i++) {
            mArray[i-1] = mArray[i];
        }
        count--;

        return res;
    }
}
