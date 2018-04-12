package cn.njupt.manacher_55;

/**
 * Manacher算法
 * 解决问题：字符串中最长回文子串
 *
 * @author Qin
 */
public class Manacher {
    public static char[] manacherString(String m) {
        char[] chars = m.toCharArray();
        char[] newChars = new char[m.length() * 2 + 1];
        int index = 0;

        for (int i = 0; i < newChars.length; i++) {
            newChars[i] = (i & 1) == 0 ? '#' : chars[index++];
        }

        return newChars;
    }

    public static int maxLcpsLength(String m) {
        char[] chars = manacherString(m);
        // 回文半径数组
        int[] pArr = new int[chars.length];
        // 回文半径右边界
        int pR = -1;
        // 回文右边界中心
        int index = -1;
        // 最长回文子串长度
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < chars.length; i++) {
            // 1) i在R外，暴力扩
            // 2) i在R内 => i关于回文右边界中心的对称点i'
            //		i'的回文半径在LR内,pArr[i']=pArr[i]  t[f[aba]kTk[aba]f]s
            //		i'的回文半径在LR外,pArr[i]=pR - i    [ab[cEcba]tttab[cEc]]f
            //		i'的回文半径与LR压线,需要试,从pR向外的位置继续比对   [abcEcbaFFFabcEcba]
            // 2 * index - i: i'的位置
            pArr[i] = pR > i ? Math.min(index * 2 - i, pR - i) : 1; // 解决了情况2的第一种和第二种
            // 求i点的回文半径(暴力扩)
            while (i + pArr[i] < chars.length && i - pArr[i] > -1) {
                if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            if (pArr[i] > pR) {
                pR = pArr[i];
                index = i;
            }

            max = Math.max(max, pArr[i]);
        }

        return max - 1;
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));
    }
}
