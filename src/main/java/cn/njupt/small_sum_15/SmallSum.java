package cn.njupt.small_sum_15;

/**
 * 小和问题
 * 在一个数组中， 每一个数左边比当前数小的数累加起来， 叫做这个数组的小和。 求一个数组的小和。
 * 例子：
 * [1,3,4,2,5]
 * 1左边比1小的数， 没有；
 * 3左边比3小的数， 1；
 * 4左边比4小的数， 1、 3；
 * 2左边比2小的数， 1；
 * 5左边比5小的数， 1、 3、 4、 2；
 * 所以小和为1+1+3+1+1+3+4+2=16
 *
 * 思路：利用归并排序炸出小和
 *
 * @author Qin
 */
public class SmallSum {
    public static int smallSum(int[] arr) {
        int size = arr.length;
        if (arr == null || size < 2) {
            return 0;
        }

        return smallSum(arr, 0, size - 1);
    }

    public static int smallSum(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }

        int mid = L + ((R - L) >> 1);

        return smallSum(arr, L, mid) + smallSum(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int m, int R) {
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = m + 1;
        int i = 0;
        int res = 0;

        while (p1 <= m && p2 <= R) {
            res += arr[p1] < arr[p2] ? (arr[p1] * (R - p2 + 1)) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= m) {
            help[i++] = arr[p1++];
        }

        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

        return res;
    }

    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int res = 0;

        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    res += arr[j];
                }
            }
        }

        return res;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[((int) ((maxSize + 1) * Math.random()))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxSize + 1) * Math.random()) - (int) (maxSize * Math.random());
        }

        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }

        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }

        return res;
    }


    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int res1 = smallSum(arr1);
            int res2 = comparator(arr2);
            if (res1 != res2) {
                succeed = false;
                System.out.println(res1);
                System.out.println(res2);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }

        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
