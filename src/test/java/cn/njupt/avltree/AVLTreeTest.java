package cn.njupt.avltree;

import org.junit.Test;

public class AVLTreeTest {
    @Test
    public void testInt() {
        int[] arr = {5, 3, 6, 2, 4, 7, 1};

        AVLTree<Integer> tree = new AVLTree<>();

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

        System.out.println("height:" + tree.height());

        tree.remove(4);

        System.out.println("====中序遍历====");
        tree.inOrder();
        System.out.println();

        System.out.println("height:" + tree.height());
    }
}
