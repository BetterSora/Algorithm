package cn.njupt.find_num_in_sorted_matrix_27;

/**
 * 在行列都排好序的矩阵中找数
 * 【要求】 时间复杂度为O(N+M)， 额外空间复杂度为O(1)
 * @author Qin
 */
public class FindNumInSortedMatrix {
    public static boolean isContains(int[][] matrix, int k) {
        int row = 0;
        int column = matrix[0].length - 1;

        if (matrix[row][column] == k) {
            return true;
        }

        while (row < matrix.length && column > -1) {
            // 如果需要查找的数大于每一行最后一个数直接跳下一行
            if (k > matrix[row][column]) {
                row++;
            } else if (k < matrix[row][column]) { // 否则，在行内向前遍历
                column--;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
                { 10, 12, 13, 15, 16, 17, 18 },// 1
                { 23, 24, 25, 26, 27, 28, 29 },// 2
                { 44, 45, 46, 47, 48, 49, 50 },// 3
                { 65, 66, 67, 68, 69, 70, 71 },// 4
                { 96, 97, 98, 99, 100, 111, 122 },// 5
                { 166, 176, 186, 187, 190, 195, 200 },// 6
                { 233, 243, 321, 341, 356, 370, 380 } // 7
        };
        int K = 233;
        System.out.println(isContains(matrix, K));
    }
}
