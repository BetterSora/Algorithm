package cn.njupt.array_queue_28;

import org.junit.Test;

public class ArrayQueueTest {
    @Test
    public void testInt() {
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>(Integer.class);
        queue.add(10);
        queue.add(20);
        queue.add(30);

        System.out.println("size=" + queue.size());
        System.out.println("front=" + queue.front());
        System.out.println("size=" + queue.size());

        System.out.println(queue.pop());
        System.out.println("size=" + queue.size());
        System.out.println("front=" + queue.front());
    }

    @Test
    public void testString() {
        ArrayQueue<String> queue = new ArrayQueue<>(String.class);
        queue.add("10");
        queue.add("20");
        queue.add("30");

        System.out.println("size=" + queue.size());
        System.out.println("front=" + queue.front());
        System.out.println("size=" + queue.size());

        System.out.println(queue.pop());
        System.out.println("size=" + queue.size());
        System.out.println("front=" + queue.front());
    }
}
