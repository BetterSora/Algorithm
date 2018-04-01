package cn.njupt.is_BST_and_CBT_39;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 判断一棵树是否是搜索二叉树(中序遍历从小到大)
 * 判断一棵树是否是完全二叉树
 */
public class IsBSTAndCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }

        Stack<Node> stack = new Stack<>();
        int pre = Integer.MIN_VALUE;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.value > pre) {
                    pre = head.value;
                } else {
                    return false;
                }

                head = head.right;
            }
        }

        return true;
    }

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        Node L = null;
        Node R = null;
        boolean leaf = false;
        queue.offer(head);

        while (!queue.isEmpty()) {
            head = queue.poll();
            L = head.left;
            R = head.right;

            // 有右孩子没有左孩子，直接return false
            if (R != null && L == null) {
                return false;
            }

            if (leaf && (head.left != null || head.right != null)) {
                return false;
            }

            // 有左孩子没有右孩子，或者左右孩子都没有，后面遇到的节点都必须是叶节点
            if (L != null) {
                queue.offer(L);
            }

            if (R != null) {
                queue.offer(R);
            } else {
                leaf = true;
            }
        }

        return true;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        printTree(head);
        System.out.println(isBST(head));
        System.out.println(isCBT(head));
    }
}
