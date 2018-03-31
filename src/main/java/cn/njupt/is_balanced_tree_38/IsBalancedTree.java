package cn.njupt.is_balanced_tree_38;

/**
 * 判断一棵二叉树是否是平衡二叉树
 * @author Qin
 */
public class IsBalancedTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class NodeData {
        public boolean flag;
        public int h;

        public NodeData(boolean flag, int h) {
            this.flag = flag;
            this.h = h;
        }
    }

    public static boolean isBalance(Node head) {
        NodeData data = process(head);
        System.out.println(data.h);

        return data.flag;
    }

    public static NodeData process(Node head) {
        if (head == null) {
            // 空树是平衡的，高度为0
            return new NodeData(true, 0);
        }

        NodeData leftData = process(head.left);
        if (!leftData.flag) {
            return new NodeData(false, 0);
        }

        NodeData rightData = process(head.right);
        if (!rightData.flag) {
            return new NodeData(false, 0);
        }

        if (Math.abs(leftData.h - rightData.h) > 1) {
            return new NodeData(false, 0);
        } else {
            return new NodeData(true, Math.max(leftData.h, rightData.h) + 1);
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalance(head));
    }
}
