package cn.njupt.stack_and_queue_convert_21;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 如何仅用队列结构实现栈结构？
 * 如何仅用栈结构实现队列结构？
 * @author Qin
 */
public class StackAndQueueConvert {
    /**
     * 两个队列实现栈
     */
    public static class TwoQueuesStack {
        private Queue<Integer> queue;
        private Queue<Integer> help;

        public TwoQueuesStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(int num) {
            queue.add(num);
        }

        public int pop() {
            if (queue.isEmpty()) {
                throw new RuntimeException("stack_02 is empty");
            }

            while (queue.size() > 1) {
                help.add(queue.poll());
            }
            int res = queue.poll();
            swap();

            return res;
        }

        public int peek() {
            if (queue.isEmpty()) {
                throw new RuntimeException("stack_02 is empty");
            }

            while (queue.size() > 1) {
                help.add(queue.poll());
            }
            int res = queue.poll();
            help.add(res);
            swap();

            return res;
        }

        private void swap() {
            Queue<Integer> tmp = queue;
            queue = help;
            help = tmp;
        }
    }

    /**
     * 两个栈实现队列
     */
    public static class TwoStacksQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }

        public void push(int num) {
            stackPush.push(num);
        }

        public int poll() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("Queue is empty");
            }

            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }

            return stackPop.pop();
        }

        public int peek() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("Queue is empty");
            }

            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }

            return stackPop.peek();
        }
    }
}
