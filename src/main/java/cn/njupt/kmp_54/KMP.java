package cn.njupt.kmp_54;

/**
 * KMP算法
 * 解决的问题：字符串匹配。给你两个字符串，寻找其中一个字符串是否包含另一个字符串，如果包含，返回包含的起始位置
 *
 * @author Qin
 */
public class KMP {
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        // 字符串s头指针
        int s1 = 0;
        // 字符串m头指针
        int s2 = 0;
        // 记录每个位置最长前缀和最长后缀的匹配长度，最长前缀不能包含最后一个字符，最长后缀不能包含第一个字符
        int[] next = getNextArray(m);

        while (s1 < s.length() && s2 < m.length()) {
            if (s.charAt(s1) == m.charAt(s2)) {
                s1++;
                s2++;
            } else if (next[s2] == -1) {
                // m字符串停留在第一个位置，说明他们第一个字符就不匹配
                s1++;
            } else {
                s2 = next[s2];
            }
        }

        return s2 == m.length() ? s1 - s2 : -1;
    }

    public static int[] getNextArray(String m) {
        if (m.length() == 1) {
            return new int[] {-1};
        }

        int[] next = new int[m.length()];
        next[0] = -1;
        next[1] = 0;
        // 当前位置
        int pos = 2;
        // 当前位置的前一个位置的最长前缀数，如果是坐标的话，则刚好是最长前缀的下一个字符
        int cn = 0;

        while (pos < m.length()) {
            if (m.charAt(pos - 1) == m.charAt(cn)) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }

        return next;
    }

    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));
    }
}
