package cn.njupt.select_sort_12;

/**
 * 选择排序(O(N^2))
 * 每次找一个最小的数放在前面
 *
 * @author Qin
 */
public class SelectSort {
    public static void selectSort(int[] arr) {
        // i记录开始的位置
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
