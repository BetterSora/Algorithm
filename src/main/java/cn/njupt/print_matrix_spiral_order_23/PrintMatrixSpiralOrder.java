package cn.njupt.print_matrix_spiral_order_23;

/**
 * 旋转打印矩阵
 *
 * 【题目】 给定一个整型矩阵matrix， 请按照转圈的方式打印它。
 * 例如： 1   2  3  4
 *       5   6  7  8
 *       9  10 11 12
 *       13 14 15 16
 * 打印结果为： 1， 2， 3， 4， 8， 12， 16， 15， 14， 13， 9，5， 6， 7， 11， 10
 * 【要求】 额外空间复杂度为O(1)。
 *
 * @author Qin
 */
public class PrintMatrixSpiralOrder {
    public static void spiralOrderPrint(int[][] matrix) {
        int tR = 0; // 行的起始坐标
        int tC = 0; // 列的起始坐标
        int dR = matrix.length - 1; // 行的结束坐标
        int dC = matrix[0].length - 1; // 列的结束坐标

        while (tR <= dR && tC <= dC) {
            printMatrix(matrix, tR++, tC++, dR--, dC--);
        }
    }

    /**
     * 给定矩阵中左上角的一个点和左下角的一个点，打印一个框的数字
     */
    private static void printMatrix(int[][] matrix, int tR, int tC, int dR, int dC) {
        // 处于同一行
        if (tR == dR) {
            for (int i = tC; i <= dC; i++) {
                System.out.print(matrix[tR][i] + " ");
            }
        } else if (tC == dC) {
            // 处于同一列
            for (int i = tR; i <= dR; i++) {
                System.out.print(matrix[i][tC] + " ");
            }
        } else {
            int curR = tR;
            int curC = tC;

            while (curC != dC) {
                System.out.print(matrix[curR][curC] + " ");
                curC++;
            }

            while (curR != dR) {
                System.out.print(matrix[curR][curC] + " ");
                curR++;
            }

            while (curC != tC) {
                System.out.print(matrix[curR][curC] + " ");
                curC--;
            }

            while (curR != tR) {
                System.out.print(matrix[curR][curC] + " ");
                curR--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        spiralOrderPrint(matrix);
    }
}
