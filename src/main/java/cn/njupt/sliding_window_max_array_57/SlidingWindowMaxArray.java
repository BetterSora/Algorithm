package cn.njupt.sliding_window_max_array_57;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 生成窗口最大值数组
 * 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次滑一个位置，求窗口内最大值组成的数组
 *
 * 如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值
 *
 * 思路：
 * 利用双端队列(头->尾，大->小)，从尾部加数，从头部减数
 * 若窗口的左边界L，右边界R，则双端队列中留的记录是当L开始缩的时候，有可能成为最大值的数
 * 存的是下标，为什么？因为要知道具体哪个位置的值过期了
 * 为什么加数时，小于等于加的数的数要出去，因为加的数比原数大，也比它晚过期，所以小的数已经没用了
 */
public class SlidingWindowMaxArray {
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length < w || w < 1) {
            return null;
        }

        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);

            // 判断头部是否过期
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }

            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        Arrays.stream(getMaxWindow(arr, 3)).forEach(value -> System.out.print(value + " "));
    }
}
