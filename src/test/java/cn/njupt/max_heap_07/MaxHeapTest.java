package cn.njupt.max_heap_07;

public class MaxHeapTest {
    public static void main(String[] args) {
        int i;
        int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
        MaxHeap<Integer> tree=new MaxHeap<Integer>();

        System.out.printf("== 依次添加: ");
        for(i=0; i<a.length; i++) {
            System.out.printf("%d ", a[i]);
            tree.insert(a[i]);
        }

        System.out.printf("\n== 最 大 堆: %s", tree);

        i=85;
        tree.insert(i);
        System.out.printf("\n== 添加元素: %d", i);
        System.out.printf("\n== 最 大 堆: %s", tree);

        i=40;
        tree.remove(i);
        System.out.printf("\n== 删除元素: %d", i);
        System.out.printf("\n== 最 大 堆: %s", tree);
        System.out.printf("\n");
    }
}
