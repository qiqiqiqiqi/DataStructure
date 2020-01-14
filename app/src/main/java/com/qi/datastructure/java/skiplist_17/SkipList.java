package com.qi.datastructure.java.skiplist_17;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 跳表的一种实现方法。
 * 跳表中存储的是正整数，并且存储的是不重复的。
 * <p>
 * Author：ZHENG
 */
public class SkipList {

    private static final float SKIPLIST_P = 0.5f;
    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;
    //遍历的入口，will record every level first value in update[]
    private Node head = new Node();  // 带头链表

    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    public void insert(int value) {
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node[] update = new Node[level];
        for (int i = 0; i < level; ++i) {
            update[i] = head;
        }
        // 找到第 i 层索引的插入位置，将插入位置前面的结点保存到 update 数组
        // record every level largest value which smaller than insert value in update[]
        Node p = head;
        for (int i = level - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;// use update save node in search path
        }
        // 更新各层的 forwards 结点，
        // in search path node next node become new node forwords(next)
        for (int i = 0; i < level; ++i) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        // update node hight
        if (levelCount < level) levelCount = level;
    }

    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }

        while (levelCount > 1 && head.forwards[levelCount] == null) {
            levelCount--;
        }

    }

    // 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
    // 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
    // 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
    //        50%的概率返回 1
    //        25%的概率返回 2
    //      12.5%的概率返回 3 ...
    private int randomLevel() {
        int level = 1;

        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL)
            level += 1;
        return level;
    }

    public void printAll() {
        //LinkedHashMap
//        Hashtable
        HashMap<String, String> hashMap = new HashMap();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    /**
     * p=p.forwars[i]代表第i层的下一结点
     * 打印每一层所有数据。
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SkipList Level Count = ").append(levelCount).append("\n");
        for (int i = levelCount - 1; i >= 0; i--) {
            sb.append("level ").append(i).append(" --> ");
            for (Node p = head; p.forwards[i] != null; p = p.forwards[i]) {
                sb.append(p.forwards[i].data);
                if (p.forwards[i].forwards[i] != null) {
                    sb.append(",").append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public class Node  {

        private int data = -1;
        /**
         * 当前索引总层数。
         * 索引从 0 开始计数，到 maxLevel-1 为止。
         * 第 0 层为原始链表，从下往上依次建立索引，最上层为第 maxLevel-1 层索引。
         */
        private int maxLevel = 0;

        /**
         * 保存当前结点的所有下一跳结点。
         * forwards[i] 表示当前结点在第 i 层索引的下一跳结点，i in [0, maxLevel-1]
         * <p>
         * 因为每个节点都有可能在多个索引层上，节点在索引层需要持有下个节点的引用，所以节点用数组去记录每层的下一个节点
         */
        private Node[] forwards = new Node[MAX_LEVEL];

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");

            return builder.toString();
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        for (int i = 1; i <= 32; i++) {
            skipList.insert((int) (Math.random() * 100));
        }
        System.out.println(skipList);
        System.out.println(skipList);
       /* Node node = skipList.find(11);
        if (node != null) {
            System.out.println(node);
        } else {
            System.out.println("not found");
        }

        skipList.delete(11);
        System.out.println(skipList);

        node = skipList.find(11);
        if (node != null) {
            System.out.println(node);
        } else {
            System.out.println("not found");
        }*/


    }

}
