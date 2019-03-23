package cn.njupt;

import java.util.Scanner;

// 40%的数据 M <= N
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // 初始绳子长度
        int[] nums = new int[N];
        double l = 0;
        double r = 0;
        for (int i = 0; i < N; i++) {
            int tmp = scanner.nextInt();
            nums[i] = tmp;
            r = Math.max(r,tmp);
        }

        while (Math.abs(l - r) > 1e-8) {
            double mid = (r + l) / 2;
            if (check(mid, nums, M)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        System.out.printf("%.2f\n", l);
        System.out.printf("%.2f\n", r);
    }

    public static boolean check(double x, int[] arr, int k) {
        int s = 0; // 目前能切割的个数
        for (int i = 0; i < arr.length; i ++) {
            s += ((int) (arr[i] / x));
        }

        if (s >= k) {
            return true;
        } else {
            return false;
        }
    }
}
