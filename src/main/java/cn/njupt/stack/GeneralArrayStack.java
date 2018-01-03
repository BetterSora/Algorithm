package cn.njupt.stack;

import java.lang.reflect.Array;

/**
 * Java:数组实现的栈，能存储任意类型的数据
 * @author Qin
 * 2017/12/21
 */
public class GeneralArrayStack<T> {
    private static final int DEFAULT_SIZE = 12;
    private T[] mArray;
    private int count;

    public GeneralArrayStack(Class<T> type, int size) {
        mArray = (T[]) Array.newInstance(type, size);
        count = 0;
    }

    public GeneralArrayStack(Class<T> type) {
        this(type, DEFAULT_SIZE);
    }

    // 将val添加到栈中
    public void push(T value) {
        mArray[count++] = value;
    }

    // 返回栈顶元素值
    public T peek() {
        if (count == 0) {
            throw new IndexOutOfBoundsException("stack is empty");
        }

        return mArray[count - 1];
    }

    // 返回栈顶元素值，并删除栈顶元素
    public T pop() {
        if (count == 0) {
            throw new IndexOutOfBoundsException("stack is empty");
        }

        return mArray[--count];
    }

    // 返回栈的大小
    public int size() {
        return count;
    }

    // 返回栈是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 打印栈
    public void printArrayStack() {
        if (isEmpty()) {
            System.out.println("stack is empty");
            return;
        }

        System.out.println("size=" + count);

        for (int i = count-1; i >= 0; i--) {
            System.out.println(mArray[i]);
        }
    }
}
