package tech.zlia.interest.example;

import java.util.Objects;
import java.util.StringJoiner;

/**
 *  手动实现单向链表
 *  <p>链表是一种数据结构，一种线性表，但是并不会按线性的顺序存储数据，而是在每一个节点里存储下一个节点的指针
 *  <p>优点：使用链表结构可以克服数组需要预先知道数据大小的缺点，链表充分利用计算机内存空间，实现灵活的内存动态管理
 *  <p>缺点：但是失去了数组随机读取的优点，同时由于增加了节点的指针，空间开销比较大
 *
 *  <p>单向链表的节点分为两个部分：第一个部分保存或者显示关于节点的信息，另一个部分存储下一个节点的地址，最后一个节点存储地址指向空值
 *  <p>单向链表只可向一个方向遍历，一般查找一个节点的时候需要从第一个节点开始访问下一个节点，直到访问到需要的位置
 *
 * @version - 1.0.0 2019-08-29
 * @author - zlia
 */
public class SingleLinkedList {

    /**
     * 链表的长度大小
     */
    private int size;

    /**
     * 链表的头部节点
     */
    private Node head;

    /**
     * 初始化参数
     */
    SingleLinkedList() {
        this.size = 0;
    }

    /**
     * 在尾部添加节点
     * @param data 节点的信息
     */
    void addNode(Object data) {
        if (size == 0) { //添加头部节点
            this.head = new Node(data, null);
        } else {
            Node node = lastNode(this.head);//获取最后一个节点
            node.setNext(new Node(data, null));
        }
        size ++;
    }

    /**
     * 获取最后一个节点
     * @return 最后一个节点
     */
    private Node lastNode(Node node) {
        Objects.requireNonNull(node);
        Node nodeNext = node.getNext();
        if (nodeNext  == null) { //判断是否为空，若为空则说明是最后一个节点，若不为空则继续判断下一个节点
            return node;
        } else {
            return lastNode(nodeNext);
        }
    }

    /**
     * 显示所有节点信息
     */
    void display() {
        StringJoiner sj = new StringJoiner(",");

        if(size == 0) {
            sj.add("[]");
        } else {
            int tempSize = size;
            Node node = head;

           while (tempSize > 0) {
               sj.add((String)node.getData());
               tempSize --;
               node = node.getNext();
               if (node == null) {
                   break;
               }
           }
        }
        System.out.println(sj.toString());
    }

    /**
     * 删除指定的节点
     */
    boolean remove(Object data) {
        if (size == 0) {
            return false;
        }
        Node currentNode = head;
        Node previousNode = head;
        int tempSize = size;

        while (tempSize > 0) {
            if (currentNode.getData().equals(data)) {
                if (currentNode.equals(head)) {
                    head = currentNode.getNext();
                } else {
                    previousNode.setNext(currentNode.getNext());
                }
                size --;
                return true;
            } else {
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            tempSize --;
        }
        return false;
    }

    /**
     * 查找指定的节点
     * @return 指定节点
     */
    Node find(Object data) {
        int tempSize = size;
        Node node = head;

        while (tempSize > 0) {
            if (node.getData().equals(data)) {
                return node;
            } else {
                node = node.getNext();
            }
            tempSize --;
        }
        return null;
    }
}

class Node {

    /**
     * 当前节点的信息
     */
    private Object data;

    /**
     * 存储下一个节点的地址
     */
    private Node next;

    /**
     * 初始化参数
     * 每一个节点都有节点信息与下一个节点的地址
     * @param data 节点信息
     * @param next 下一个节点地址
     */
    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

/**
 * 测试手写单向链表的相关功能
 * @version - 1.0.0 2019-08-29
 * @author - zlia
 */
class SingleLinkedListTest {

    public static void main(String[] args) {
        SingleLinkedList sll = new SingleLinkedList();
        sll.addNode("A");
        sll.addNode("B");
        sll.addNode("C");
        sll.addNode("D");
        sll.display();

        sll.remove("A");

        sll.display();

    }
}

/* 结果展示

A,B,C,D
B,C,D

 */
