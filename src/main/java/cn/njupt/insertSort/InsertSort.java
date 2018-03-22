package cn.njupt.insertSort;

import cn.njupt.bubblesort.BubbleSort;

/**
 * 插入排序(O(N~N^2))
 * @author Qin
 */
public class InsertSort {
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 ; j--) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
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
