package cn.njupt.print_all_subsquences_50;

/**
 * 打印字符串的所有子序列
 */
public class Print_All_Subsquences {
    public static void printAllSubsquence(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0, "");
    }

    public static void process(char[] chs, int i, String res) {
        if (i == chs.length) {
            System.out.println(res);
        } else {
            process(chs, i + 1, res + chs[i]);
            process(chs, i + 1, res);
        }
    }

    public static void main(String[] args) {
        printAllSubsquence("abc");
    }
}
