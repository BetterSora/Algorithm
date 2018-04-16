package cn.njupt.BFPRT_56;

import java.util.Arrays;

import static cn.njupt.netherlands_flag_16.NetherlandsFlag.partition;

/**
 * BFPRT算法
 * 解决问题：在一个无序数组中找到第K小（大）的数
 *
 * @author Qin
 */
public class BFPRT {
    // O(N*logK)
    public static int[] getMinKNumsByHeap(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }

        int[] heap = new int[k];
        for (int i = 0; i < k; i++) {
            // 生成大根堆
            heapInsert(heap, arr[i], i);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < heap[0]) {
                heap[0] = arr[i];
                // 对堆进行调整，使其继续变成大根堆
                heapify(heap, 0, k);
            }
        }

        return heap;
    }

    public static void heapify(int[] heap, int index, int size) {
        int left = 2 * index + 1;
        while (left < size) {
            int largest = left + 1 < size && heap[left + 1] > heap[left] ? left + 1 : left;
            if (heap[largest] > heap[index]) {
                swap(heap, largest, index);
                index = largest;
                left = 2 * index + 1;
            } else {
                break;
            }
        }
    }

    public static void heapInsert(int[] heap, int value, int index) {
        // 将数据插到堆的末尾
        heap[index] = value;
        int parent = (index - 1) / 2;

        // 从下至上对堆进行调整
        while (parent > 0) {
            if (heap[index] > heap[parent]) {
                swap(heap, index, parent);
                index = parent;
                parent = (index - 1) / 2;
            } else {
                break;
            }
        }
    }

    // O(N)
    public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        // minKth: 第K小的数
        int minKth = getMinKthByBFPRT(arr, k);

        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] < minKth) {
                res[index++] = arr[i];
            }
        }
        for (; index != res.length; index++) {
            res[index] = minKth;
        }
        return res;
    }

    public static int getMinKthByBFPRT(int[] arr, int k) {
        //拷贝数组的原因是，下面要对数组进行局部的排序
        int[] newArr = Arrays.copyOf(arr, arr.length);

        return bfprt(newArr, 0, newArr.length - 1, k - 1);
    }

    /**
     * 在begin和end上求第k小的数
     * 1.分组：5个一组
     * 2.组间排序
     * 3.取出每组中位数组成新数组，递归调用num = bfprt(...)
     * 4.以num进行划分
     */
    public static int bfprt(int[] arr, int begin, int end, int k) {
        if (begin == end) {
            return arr[begin];
        }

        // 划分值
        int num = medianOfMedians(arr, begin, end);
        // 以num进行划分
        int[] range = partition(arr, begin, end, num);

        if (k >= range[0] && k <= range[1]) {
            return arr[k];
        } else if (k < range[0]) {
            return bfprt(arr, begin, range[0] - 1, k);
        } else {
            return bfprt(arr, range[1] + 1, end, k);
        }
    }

    public static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        // 每组中位数组成的新数组
        int[] mArr = new int[num / 5 + offset];
        // 分组
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    public static int getMedian(int[] arr, int begin, int end) {
        // 组间排序
        insertionSort(arr, begin, end);
        // 取出每组中位数
        int sum = end + begin;
        // 若为偶，则取下中位数
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    public static void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9 };
        // sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
        printArray(getMinKNumsByHeap(arr, 13));
        printArray(getMinKNumsByBFPRT(arr, 13));
    }
}
