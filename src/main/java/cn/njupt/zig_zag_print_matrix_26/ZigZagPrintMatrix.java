package cn.njupt.zig_zag_print_matrix_26;

/**
 * “之” 字形打印矩阵
 * 【要求】 额外空间复杂度为O(1)。
 * @author Qin
 */
public class ZigZagPrintMatrix {
    public static void printMatrixZigZag(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean flag = true;

        while (tR != endR +1) {
            printLevel(matrix, tR, tC, dR, dC, flag);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            flag = !flag;
        }
    }

    public static void printLevel(int[][] matrix, int tR, int tC, int dR, int dC, boolean flag) {
        // 根据flag判断是正向打印还是反向打印
        if (flag) {
            while (tR != dR + 1) {
                System.out.print(matrix[tR++][tC--] + " ");
            }
        } else {
            while (dR != tR - 1) {
                System.out.print(matrix[dR--][dC++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }
}
