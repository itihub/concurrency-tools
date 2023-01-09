package collection;

import java.util.HashMap;

/**
 * 需要修改JDK为1.7版本
 * 演示HashMap在多线程情况下造成死循环的情况
 */
public class HashMapEndlessLoop {

    // 调整 初始容量2 负载因子1.5（决定在什么时候扩容）
    private static HashMap<Integer, String> map = new HashMap<>(2, 1.5f);

    public static void main(String[] args) {
        // 1,3,5,7,9 经过Hash后得到的同一个值，则落到同一个桶方便让它发生扩容
        // 1,3,5,7,9 与Map容量2取余都是1
        map.put(5, "C");
        map.put(7, "B");
        map.put(3, "A");

        // 容量2 负载因子1.5 2*1.5=3 当超过3个内容就会发生扩容
        // 使用两个线程向map添加值，让它同时发生扩容
        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(15, "D");
                System.out.println(map);
            }
        }, "Thread 1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(1, "E");
                System.out.println(map);
            }
        }, "Thread 2").start();
    }
}
