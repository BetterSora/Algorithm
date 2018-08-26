package cn.njupt.merge_sort_14;

import cn.njupt.bubble_sort_11.BubbleSort;

/**
 * 归并排序(O(N*logN))
 *
 * 归并为什么快？两个有序数组进行外排的时候，没有浪费比较，有序这件事情在后面被用上了，而冒泡每次排好后对下次没有作用
 * @author Qin
 */
public class MergeSort {
    public static void mergeSort(int[] arr) {
        int size = arr.length;

        if (arr == null || size < 2) {
            return;
        }

        mergeSort(arr, 0, size - 1);
    }

    public static void mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }

        int mid = L + ((R - L) >> 1); // 防止溢出

        // 左边排好序
        mergeSort(arr, L, mid);
        // 右边排好序
        mergeSort(arr, mid + 1, R);
        // 外排整合
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }

        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
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

            mergeSort(arr1);
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
