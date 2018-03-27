package cn.njupt.array_queue_28;

import java.lang.reflect.Array;

//			  |__4_| <--end
//            |__3_|
//            |__2_|
//  start-->  |__1_|

/**
 * Java:数组实现队列，可以存储任意数据
 * @author Qin
 * 2017/12/23
 */
public class ArrayQueue<T> {
    private T[] mArray;
    private static final int DEFAULT_SIZE = 12;
    private int count;
    private int start;
    private int end;

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
        if (count == mArray.length) {
            throw new IndexOutOfBoundsException("Queue is full!");
        }

        count++;
        mArray[end] = value;
        end = end == mArray.length - 1 ? 0 : end + 1;
    }

    // 返回队首元素
    public T front() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty!");
        }

        return mArray[start];
    }

    // 返回队首元素并删除
    public T pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty!");
        }

        count--;
        T res = mArray[start];
        start = start == mArray.length - 1 ? 0 : start + 1;

        return res;
    }
}
