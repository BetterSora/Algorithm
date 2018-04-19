package cn.njupt.maximal_rectangle_60;

import java.util.Stack;

/**
 * 求最大子矩阵的大小
 * 给定一个整型矩阵map，其中的值只有0和1两种，求其中全是1的所有矩形区域中，最大矩形区域中的1的数量
 *
 * 思路：
 * 1 0 1 1
 * 1 1 1 1
 * 1 1 1 0
 * 以每一行为底，找到最大长方形，例如：第一行[1 0 1 1]，第二行[2 1 2 2]
 * 从而题目转化为用一个数组表示直方图，找到其中的最大正方形
 * 利用单调栈来完成(找左右两边离它最近的比它小的)  小 -> 大
 */
public class MaximalRectangle {
    public static int getMaximalRectangle(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return 0;
        }

        int maxArea = 0;
        int[] height = new int[arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                height[j] = arr[i][j] == 1 ? height[j] + 1 : 0;
            }
            maxArea = maxRecFromBottom(height);
        }

        return maxArea;
    }

    /**
     * 从数组直方图中找到最大的矩形
     */
    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int high = height[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;
                int curArea = (right - left - 1) * high;
                maxArea = Math.max(curArea, maxArea);
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int high = height[stack.pop()];
            int left = stack.isEmpty() ? -1 : stack.peek();
            int right = height.length;
            int curArea = (right - left - 1) * high;
            maxArea = Math.max(curArea, maxArea);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 0, 1, 1},
                       {1, 1, 1, 1},
                       {1, 1, 1, 0}};

        System.out.println(getMaximalRectangle(arr));
    }
}
