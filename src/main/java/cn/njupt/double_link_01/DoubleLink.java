package cn.njupt.double_link_01;

/**
 * Java实现的双向列表
 * 注：java自带的集合包中有实现双向链表，路径是:java.util.LinkedList
 *
 * @author Qin
 * 2017/12/20
 */
public class DoubleLink<T> {
    // 表头
    private DNode<T> head;
    // 节点个数
    private int count;

    // 双向链表的节点
    private class DNode<T> {
        public DNode<T> prev;
        public DNode<T> next;
        public T value;

        // 节点的构造方法
        public DNode(DNode prev, DNode next, T value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }

    // 双向链表的构造方法
    public DoubleLink() {
        head = new DNode<T>(null, null, null);
        head.prev = head.next = head;
        count = 0;
    }

    // 返回节点数目
    public int size() {
        return count;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 获取指定index位置的节点
    private DNode<T> getNode(int index) {
        if (index<0 || index>=count) {
            throw new IndexOutOfBoundsException();
        }

        // 从左边开始搜索节点
        if (index <= count/2) {
            DNode<T> node = head.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }

            return node;
        } else {
            // 从右边开始搜索节点
            DNode<T> node = head.prev;
            int rIndex = count - index - 1;
            for (int i = 0; i < rIndex; i++) {
                node = node.prev;
            }

            return node;
        }
    }

    // 获取第index位置节点的值
    public T get(int index) {
        return getNode(index).value;
    }

    // 获取第一个节点的值
    public T getFirst() {
        return getNode(0).value;
    }

    // 获取最后一个节点的值
    public T getLast() {
        return getNode(count - 1).value;
    }

    // 将节点插入到第index位置之前
    public void insert(int index, T t) {
        if (count == 0) {
            DNode<T> node = new DNode<T>(head, head.next, t);
            head.next.prev = node;
            head.next = node;
            count++;
        } else {
            DNode<T> currentNode = getNode(index);
            DNode<T> node = new DNode<T>(currentNode.prev, currentNode, t);
            currentNode.prev.next = node;
            currentNode.prev = node;
            count++;
        }
    }

    // 将节点插入到开头
    public void insertFirst(T t) {
        insert(0, t);
    }

    // 将节点插入到结尾
    public void appendLast(T t) {
        DNode<T> node = new DNode<T>(head.prev, head, t);
        head.prev.next = node;
        head.prev = node;
        count++;
    }

    // 删除index位置的节点
    public void del(int index) {
        if (isEmpty() || index>=count) {
            throw new IndexOutOfBoundsException();
        }

        DNode<T> current = getNode(index);
        current.prev.next = current.next;
        current.next.prev = current.prev;
        count--;
    }

    // 删除第一个节点
    public void deleteFirst() {
        del(0);
    }

    // 删除最后一个节点
    public void deleteLast() {
        del(count-1);
    }
}
