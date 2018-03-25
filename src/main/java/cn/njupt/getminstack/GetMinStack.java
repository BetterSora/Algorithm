package cn.njupt.getminstack;

import java.util.Stack;

/**
 * 实现一个特殊的栈， 在实现栈的基本功能的基础上， 再实现返回栈中最小元素的操作。
 * 【要求】
 * 1．pop、 push、 getMin操作的时间复杂度都是O(1)。
 * 2．设计的栈类型可以使用现成的栈结构。
 * @author Qin
 */
public class GetMinStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public GetMinStack() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int num) {
        if (stackMin.isEmpty()) {
            stackMin.push(num);
        } else if (num <= getMin()) {
            stackMin.push(num);
        }

        stackData.push(num);
    }

    public int pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("stack is empty");
        }

        int value = stackData.pop();
        if (value == getMin()) {
            stackMin.pop();
        }

        return value;
    }

    public int getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("stack is empty!");
        }

        return stackMin.peek();
    }
}
