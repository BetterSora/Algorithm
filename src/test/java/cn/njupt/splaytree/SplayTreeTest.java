package cn.njupt.splaytree;

public class SplayTreeTest {
    public static void main(String[] args) {
        SplayTree<Integer> tree = new SplayTree<>();

        int[] arr = {10, 50, 40, 30, 20, 60};
        for (int i = 0; i < arr.length; i++) {
            tree.insert(arr[i]);
        }

        tree.preOrder();
    }
}
