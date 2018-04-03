package cn.njupt.complete_tree_node_number_40;

/**
 * 已知一棵完全二叉树， 求其节点的个数
 * 要求： 时间复杂度低于O(N)， N为这棵树的节点个数
 * @author Qin
 */
public class CompleteTreeNodeNumber {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }

        return process(head, 1, mostLeftLevel(head, 1));
    }

    /**
     * 递归求节点个数
     * @param node 节点
     * @param l 节点所在的层
     * @param h 整个树的深度，固定值
     * @return 以Node为头的整棵树的节点个数
     */
    public static int process(Node node, int l, int h) {
        if (l == h) {
            return 1;
        }

        if (mostLeftLevel(node.right, l + 1) == h) {
            return (1 << (h - l)) + process(node.right, l + 1, h);
        } else {
            return (1 << (h - l - 1)) + process(node.left, l + 1, h);
        }
    }

    /**
     * 求完全二叉树最左边的深度
     * @param node 当前节点
     * @param level 当前节点所在的层数
     * @return 最左边的深度
     */
    public static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }

        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));
    }
}
