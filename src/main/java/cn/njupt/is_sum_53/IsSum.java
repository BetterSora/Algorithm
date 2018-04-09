package cn.njupt.is_sum_53;

import java.util.Arrays;

/**
 * 给你一个数组arr,和一个正数aim。如果可以任意选择arr中的数字，能不能累加得到aim，返回true或者false
 */
public class IsSum {
    /**
     * 暴力递归
     */
    public static boolean isSum1(int[] arr, int aim) {
        return process1(arr, 0, 0, aim);
    }

    public static boolean process1(int[] arr, int i, int sum, int aim) {
        if (i == arr.length) {
            return sum == aim;
        }

        return process1(arr, i + 1, sum + arr[i], aim) || process1(arr, i + 1, sum, aim);
    }

    /**
     * 动态规划
     */
    public static boolean isSum2(int[] arr, int aim) {
        int min = Arrays.stream(arr).filter(x -> x < 0).sum();
        int max = Arrays.stream(arr).filter(x -> x > 0).sum();
        int row = arr.length;
        int column = max - min + 1;
        boolean[][] dp = new boolean[row][column];

        for (int j = 0; j < column; j++) {
            if (j + min == aim) {
                dp[row - 1][j] = true;
            } else {
                dp[row - 1][j] = false;
            }
        }

        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j < column; j++) {
                if (j + arr[i] >= column || j + arr[i] < 0) {
                    dp[i][j] = dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j + arr[i]] || dp[i + 1][j];
                }
            }
        }

        /*for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(dp[i][j] + "  ");
            }
            System.out.println();
        }*/

        return dp[0][-min];
    }

    // for test
    public static int[] generateRandomMatrix(int n) {
        if (n < 0) {
            return null;
        }
        int[] result = new int[n];
        for (int i = 0; i != result.length; i++) {
            result[i] = (int) ((Math.random() * 2 - 1) * 10);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomMatrix(10);
        Arrays.stream(arr).forEach(x -> System.out.print(x + "  "));
        System.out.println();

        System.out.println(isSum1(arr, -3));
        System.out.println(isSum2(arr, -3));
    }
}
