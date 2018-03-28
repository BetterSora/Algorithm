package cn.njupt.smaller_equal_bigger_31;

/**
 * 将单向链表按某值划分成左边小、 中间相等、 右边大的形式
 * 进阶： 在原问题的要求之上再增加如下两个要求
 * 在左、 中、 右三个部分的内部也做顺序要求， 要求每部分里的节点从左 到右的顺序与原链表中节点的先后次序一致。
 * 如果链表长度为N， 时间复杂度请达到O(N)， 额外空间复杂度请达到O(1)。
 */
public class SmallerEqualBigger {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }

        Node cur = head;
        int i = 0;
        // 算出链表的长度
        while (cur != null) {
            i++;
            cur = cur.next;
        }

        Node[] nodeArr = new Node[i];
        cur = head;
        // 将链表节点放入数组
        for (i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }

        // 对数组进行排序
        arrPartition(nodeArr, pivot);

        // 将数组内的链表重新连接
        for (i = 1; i < nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;

        return nodeArr[0];
    }

    public static Node listPartition2(Node head, int pivot) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }

            head = next;
        }

        if (sT != null && eT != null && bT != null) {
            sT.next = eH;
            eT.next = bH;
            return sH;
        }

        if (eT != null && bT != null) {
            eT.next = bH;
            return eH;
        }

        if (sT != null && bT != null) {
            sT.next = bH;
            return sH;
        }

        if (sT != null && eT != null) {
            sT.next = eH;
            return sH;
        }

        return bT != null ? bH : sT != null ? sH : eT != null ? eH : null;
    }

    public static void arrPartition(Node[] nodeArr, int pivot) {
        int less = -1;
        int cur = 0;
        int more = nodeArr.length;

        while (cur != more) {
            if (nodeArr[cur].value < pivot) {
                swap(nodeArr, ++less, cur++);
            } else if (nodeArr[cur].value > pivot) {
                swap(nodeArr, --more, cur);
            } else {
                cur++;
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        //head1 = listPartition1(head1, 5);
        head1 = listPartition2(head1, 4);
        printLinkedList(head1);

    }
}
