package cn.njupt.IPO_46;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 输入： 参数1， 正数数组costs 参数2， 正数数组profits 参数3，正数k 参数4， 正数m
 * costs[i]表示i号项目的花费 profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润) k表示你不能并行、 只能串行的最多做k个项目 m表示你初始的资金
 * 说明： 你每做完一个项目， 马上获得的收益， 可以支持你去做下一个 项目。
 * 输出： 你最后获得的最大钱数。
 *
 * @author Qin
 */
public class IPO {
    public static class Project {
        // 项目利润
        public int p;
        // 项目花费
        public int c;

        public Project(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Project[] projects = new Project[Capital.length];
        for (int i = 0; i < Profits.length; i++) {
            projects[i] = new Project(Profits[i], Capital[i]);
        }

        PriorityQueue<Project> minCostQ = new PriorityQueue<>();
        PriorityQueue<Project> maxProfitQ = new PriorityQueue<>((o1, o2) -> o2.p - o1.p);

        Arrays.stream(projects).forEach(i -> minCostQ.add(i));

        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }
}
