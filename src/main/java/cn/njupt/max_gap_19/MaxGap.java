package cn.njupt.max_gap_19;

import java.util.Arrays;

/**
 * 给定一个数组， 求如果排序之后， 相邻两数的最大差值， 要求时间复杂度O(N)， 且要求不能用非基于比较的排序
 *
 * 根据戈隆原理，N个数，N+1个桶，第一个桶和最后一个桶非空，所以中间必存在一个空桶，空桶两边的差值肯定大于一个桶内的差值
 * 所以最大差值一定不来自一个桶内部
 * @author Qin
 */
public class MaxGap {
    public static int maxGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int N = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // 遍历数组找出最大值和最小值
        for (int i = 0; i < N; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }

        if (max == min) {
            return 0;
        }

        // 桶内是否有值
        boolean[] hasNum = new boolean[N + 1];
        // 桶的最小值
        int[] bucketMin = new int[N + 1];
        // 桶的最大值
        int[] bucketMax = new int[N + 1];
        int index = 0;

        for (int i = 0; i < N; i++) {
            index = bucket(nums[i], N, min, max);
            bucketMin[index] = hasNum[index] ? Math.min(bucketMin[index] ,nums[i]) : nums[i];
            bucketMax[index] = hasNum[index] ? Math.max(bucketMax[index], nums[i]) : nums[i];
            hasNum[index] = true;
        }

        int res = 0;
        // 第一个桶肯定非空
        int lastMax = bucketMax[0];
        for (int i = 1; i < N + 1; i++) {
            // 遇到空桶就跳过
            if (hasNum[i]) {
                // 遇到非空桶，当前桶的最小值减去前一个非空桶的最大值
                res = Math.max(bucketMin[i] - lastMax, res);
                lastMax = bucketMax[i];
            }
        }

        return res;
    }

    /**
     * 返回num对应的桶，必须使用long不然会出事情！！
     * @param num 要放入桶的数据
     * @param max 数组的最大值
     * @param min 数组的最小值
     * @param len 数组的长度
     * @return 桶的编号
     */
    public static int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }

    // for test
    public static int comparator(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
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

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (maxGap(arr1) != comparator(arr2)) {
                System.out.println(maxGap(arr1));
                System.out.println(comparator(arr2));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
