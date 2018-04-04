package cn.njupt.union_find_42;

import java.util.HashMap;
import java.util.List;

/**
 * 并查集
 * @author Qin
 */
public class UnionFind {
    public static class Node {
        // TODO: 2018/4/4 随便什么结构
    }

    public static class UnionFindSet {
        // 存放当前节点和它对应的父节点
        public HashMap<Node, Node> fatherMap;
        // 存放代表节点和代表节点所在集合中节点的数目
        public HashMap<Node, Integer> sizeMap;

        public UnionFindSet() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        /**
         * 只接受用户一次给我的数据，不在接受后来给我的数据
         */
        public void makeSets(List<Node> list) {
            fatherMap.clear();
            sizeMap.clear();

            for (Node node : list) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        /**
         * 返回当前节点的代表节点，并将当前节点连接到父节点上
         */
        private Node findHead(Node node) {
            Node fatherNode = fatherMap.get(node);
            if (fatherNode != node) {
                fatherNode = findHead(fatherNode);
            }

            fatherMap.put(node, fatherNode);

            return fatherNode;
        }

        public boolean isSameSet(Node node1, Node node2) {
            return findHead(node1) == findHead(node2);
        }

        public void union(Node node1, Node node2) {
            if (node1 == null || node2 == null) {
                return;
            }

            if (isSameSet(node1, node2)) {
                return;
            }

            int size1 = sizeMap.get(node1);
            int size2 = sizeMap.get(node2);
            Node a = findHead(node1);
            Node b = findHead(node2);

            if (size1 >= size2) {
                fatherMap.put(b, a);
                sizeMap.put(a, size1 + size2);
            } else {
                fatherMap.put(a, b);
                sizeMap.put(b, size1 + size2);
            }
        }
    }
}
