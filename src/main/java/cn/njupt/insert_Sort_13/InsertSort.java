package cn.njupt.insert_Sort_13;

import cn.njupt.bubble_sort_11.BubbleSort;

/**
 * 插入排序(O(N~N^2))
 * 有序：O(N)
 * 无序：O(N^2)
 *
 * 相当于手里拿的扑克牌以及有序，新抓一张，向前找位置插入，在数组中就是比较交换
 *
 * @author Qin
 */
public class InsertSort {
    public static void insertSort(int[] arr) {
        // 表示从0到i-1的位置已经排好序了，考察i位置的数，0位置的数不用考察
        // 当前i位置的数向有序区域插入
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                // 和前一个位置比较，如果小就交换，否则直接跳下一个数
                swap(arr, j, j - 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = BubbleSort.generateRandomArray(maxSize, maxValue);
            int[] arr2 = BubbleSort.copyArray(arr1);
            int[] arr3 = BubbleSort.copyArray(arr1);

            insertSort(arr1);
            BubbleSort.comparator(arr2);

            if (!BubbleSort.isEqual(arr1, arr2)) {
                succeed = false;
                BubbleSort.printArray(arr3);
                break;
            }
        }

        System.out.println(succeed ? "Nice" : "Fucking fuck");
    }
}
