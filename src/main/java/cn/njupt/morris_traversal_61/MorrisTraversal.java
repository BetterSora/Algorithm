package cn.njupt.morris_traversal_61;

import java.util.Stack;

/**
 * Morris遍历
 * 时间复杂度O(N)    空间复杂度O(1)
 *
 * 流程：
 * 来到的当前节点记为cur(引用)
 * 1)如果cur无左孩子，cur向右移动(cur = cur.right)
 * 2)如果cur有左孩子，找到cur左子树上最右的节点，记为mostright
 *      1.如果mostright的right指针指向null，让其指向cur，cur向左移动
 *      2.如果mostright的right指针指向cur，让其指向null，cur向右移动
 */
public class MorrisTraversal {
    public static class Node {
        public Node left;
        public Node right;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 经典的递归遍历，其实就是每个节点来到3次，选择在第几次来到节点时打印就构成了先序、中序和后序遍历
     * Morris遍历其实就是有左子树的节点来到2次，没有左子树的节点只来到一次
     */
    public static void process(Node head) {
        if (head == null) {
            return;
        }

        // 1
        // System.out.println(head.value);
        process(head.left);
        // 2
        // System.out.println(head.value);
        process(head.right);
        // 3
        // System.out.println(head.value);
    }

    /**
     * morris实现中序遍历
     * 什么是中序？左子树都处理完再打印，所以打印时机放在向右窜之前(这种想法类似于递归)
     */
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            // 有左孩子
            if (mostRight != null) {
                // 找到左子树最右的节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    /**
     * morris实现先序遍历
     * 在第一次到达节点的时候打印
     */
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.value + " ");
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println();
    }

    /**
     * morris实现后序遍历
     * 只关注能够来到两次的节点，在第二次来到节点的时候，逆序打印它左子树的右边界，整个函数退出前，单独逆序打印整棵树的右边界
     */
    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                    cur = cur.right;
                }
            } else {
                cur = cur.right;
            }
        }
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(Node head) {
        Node cur = head;
        Node pre = null;
        Node next = null;
        // 反转节点
        while (cur != null) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }

        cur = pre;
        pre = null;
        next = null;
        // 打印后再次反转
        while (cur != null) {
            System.out.print(cur.value + " ");
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }

        /*Stack<Node> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.right;
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop().value + " ");
        }*/
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        System.out.println("-----pre order-----");
        morrisPre(head);
        System.out.println("-----in  order-----");
        morrisIn(head);
        System.out.println("-----pos order-----");
        morrisPos(head);
    }
}
