package cn.njupt.binarysearchtree;

import org.junit.Test;

public class BSTreeTest {
    @Test
    public void testInt() {
        BSTree<Integer> tree = new BSTree<>();
        int arr[] = {1, 2, 3, 4, 5};

        for (int i = 0; i < arr.length; i++) {
            tree.insert(arr[i]);
        }

        System.out.println("====前序遍历====");
        tree.preOrder();
        System.out.println();

        System.out.println("====中序遍历====");
        tree.inOrder();
        System.out.println();

        System.out.println("====后序遍历====");
        tree.postOrder();
        System.out.println();

        System.out.println("最大值：" + tree.maximum());
        System.out.println("最小值：" + tree.minimum());

        tree.remove(3);
        System.out.println("====前序遍历====");
        tree.preOrder();
        System.out.println();
    }
}
