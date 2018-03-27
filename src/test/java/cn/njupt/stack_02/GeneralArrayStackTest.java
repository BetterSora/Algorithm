package cn.njupt.stack_02;

import org.junit.Test;

import java.util.Stack;

public class GeneralArrayStackTest {
    @Test
    public void testInt() {
        GeneralArrayStack<Integer> stack = new GeneralArrayStack<Integer>(Integer.class);
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("size=" + stack.size());

        Integer tmp = stack.pop();
        System.out.println("tmp=" + tmp);

        tmp = stack.peek();
        System.out.println("tmp=" + tmp);

        stack.printArrayStack();
    }

    @Test
    public void testString() {
        GeneralArrayStack<String> stack = new GeneralArrayStack<String>(String.class);
        stack.push("10");
        stack.push("20");
        stack.push("30");

        System.out.println("size=" + stack.size());

        String tmp = stack.pop();
        System.out.println("tmp=" + tmp);

        tmp = stack.peek();
        System.out.println("tmp=" + tmp);

        stack.printArrayStack();
    }

    @Test
    public void testStack() {
        int tmp = 0;

        //测试java集合中的Stack
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);

        tmp = stack.pop();
        System.out.println("tmp=" + tmp);

        tmp = stack.peek();
        System.out.println("tmp=" + tmp);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
