package cn.njupt.min_heap_08;

public class MinHeapTest {
    public static void main(String[] args) {
        int i;
        int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
        MinHeap<Integer> tree=new MinHeap<Integer>();

        System.out.printf("== 依次添加: ");
        for(i=0; i<a.length; i++) {
            System.out.printf("%d ", a[i]);
            tree.insert(a[i]);
        }

        System.out.printf("\n== 最 小 堆: %s", tree);

        i=85;
        tree.insert(i);
        System.out.printf("\n== 添加元素: %d", i);
        System.out.printf("\n== 最 小 堆: %s", tree);

        i=90;
        tree.remove(i);
        System.out.printf("\n== 删除元素: %d", i);
        System.out.printf("\n== 最 小 堆: %s", tree);
        System.out.printf("\n");
    }
}
