package cn.njupt.all_less_num_sub_array_58;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 最大值减去最小值小于或等于num的子数组数量
 * 给定数组arr和整数num，共返回有多少个子数组满足如下情况
 * max(arr[i..j]) - min(arr[i..j]) <= num
 * 要求：如果数组长度为N，请实现时间复杂度为O(N)的解法
 *
 * 思路：
 * 如果数组在L到R内满足，则它的任一子数组都满足
 * 如果数组在L到R内不满足，则它向外扩一定不满足
 */
public class AllLessNumSubArray {
    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int L = 0;
        int R = 0;
        int res = 0;
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();

        while (L < arr.length) {
            while (R < arr.length) {
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);

                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);

                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                } else {
                    R++;
                }
            }

            // 判断头部是否过期
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
            res += R - L;
            L++;
        }

        return res;
    }

    /*----------------------------------------for test----------------------------------------------*/

    public static int func(int[] arr, int num) {
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (isValid(arr, i, j, num)) {
                    res++;
                }
            }
        }

        return res;
    }

    public static boolean isValid(int[] arr, int start, int end, int num) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int tmp = start; tmp <= end; tmp++) {
            max = arr[tmp] > max ? arr[tmp] : max;
            min = arr[tmp] < min ? arr[tmp] : min;
        }

        return max - min <= num;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[((int) ((maxSize + 1) * Math.random()))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }

        return arr;
    }

    public static void main(String[] args) {
        int testTime = 5000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int num1 = getNum(arr, 3);
            int num2 = func(arr, 3);

            if (num1 != num2) {
                succeed = false;
                System.out.println("num1: " + num1);
                System.out.println("num2: " + num2);
                Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
                System.out.println();
                break;
            }
        }

        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
