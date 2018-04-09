package cn.njupt.min_path_52;

/**
 * 给你一个二维数组，二维数组中的每个数都是正数，要求总左上角走到右下角
 * 每一步只能向右或者向下。沿途经过的数字要累加起来。返回最小路径和
 *
 * @author Qin
 */
public class MinPath {
    public static int minPath1(int[][] matrix) {
       return process1(matrix, 0, 0);
    }

    /**
     * 暴力递归
     */
    public static int process1(int[][] matrix, int i, int j) {
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            return matrix[i][j];
        }

        if (i == matrix.length - 1) {
            return matrix[i][j] + process1(matrix, i, j + 1);
        }

        if (j == matrix[0].length - 1) {
            return matrix[i][j] + process1(matrix, i + 1, j);
        }

        // 选择向右走的路径和
        int right = matrix[i][j] + process1(matrix, i, j + 1);
        // 选择向下走的路径和
        int down = matrix[i][j] + process1(matrix, i + 1, j);

        return right < down ? right : down;
    }

    /**
     * 动态规划
     */
    public static int minPath2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || matrix[0] == null) {
            return 0;
        }

        int row = matrix.length;
        int column = matrix[0].length;
        int[][] dp = new int[row][column];

        dp[row - 1][column - 1] = matrix[row - 1][column - 1];

        for (int j = column - 2; j >= 0; j--) {
            dp[row - 1][j] = dp[row - 1][j + 1] + matrix[row - 1][j];
        }

        for (int i = row - 2; i >= 0; i--) {
            dp[i][column - 1] = dp[i + 1][column - 1] + matrix[i][column - 1];
        }

        for (int i = row - 2; i >= 0; i--) {
            for (int j = column - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j+1]) + matrix[i][j];
            }
        }

        return dp[0][0];
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));
    }
}
