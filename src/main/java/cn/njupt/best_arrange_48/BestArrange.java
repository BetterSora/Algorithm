package cn.njupt.best_arrange_48;

import java.util.Arrays;

/**
 * 一些项目要占用一个会议室宣讲， 会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间(给你一个数组， 里面 是一个个具体的项目)，
 * 你来安排宣讲的日程， 要求会议室进行 的宣讲的场次最多。 返回这个最多的宣讲场次。
 *
 * @author Qin
 */
public class BestArrange {
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int bestArrange(Program[] programs, int start) {
        Arrays.sort(programs, (o1, o2) -> o1.end - o2.end);

        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if (start <= programs[i].start) {
                result++;
                start = programs[i].end;
            }
        }
        return result;
    }
}
