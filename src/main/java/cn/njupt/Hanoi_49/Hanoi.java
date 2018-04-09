package cn.njupt.Hanoi_49;

/**
 * 汉诺塔
 *
 * @author Qin
 */
public class Hanoi {
    /**
     * 主函数
     * @param n 盘子数目
     */
    public static void hanoi (int n) {
        if (n > 0) {
           move(n, "左", "右", "中");
        }
    }

    public static void move(int n, String from, String to, String help) {
        if (n == 1) {
            System.out.println("move 1 from " + from + " to " + to);
        } else {
            move(n-1, from, help, to);
            System.out.println("move " + n + " from " + from + " to " + to);
            move(n-1, help, to, from);
        }
    }

    public static void main(String[] args) {
        hanoi(3);
    }
}
