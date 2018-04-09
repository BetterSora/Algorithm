package cn.njupt.lowest_lexicography_47;

import java.util.Arrays;

/**
 * 给定一个字符串类型的数组strs， 找到一种拼接方式， 使得把所有字 符串拼起来之后形成的字符串具有最低的字典序。
 *
 * @author Qin
 */
public class LowestLexicography {
    public static String lowestString(String[] arr) {
        Arrays.sort(arr, (a, b) -> (a + b).compareTo(b + a));

        String res = "";
        for (int i = 0; i < arr.length; i++) {
            res += arr[i];
        }

        return res;
    }

    public static void main(String[] args) {
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println(lowestString(strs1));

        String[] strs2 = { "ba", "b" };
        System.out.println(lowestString(strs2));
    }
}
