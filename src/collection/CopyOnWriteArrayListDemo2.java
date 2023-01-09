package collection;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 演示对比两个迭代器
 */
public class CopyOnWriteArrayListDemo2 {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1,2,3});

        System.out.println(list);

        // 获取一个迭代器
        Iterator<Integer> iterator = list.iterator();
        // 对数组修改
        list.add(4);
        System.out.println(list);
        // 获取一个迭代器
        Iterator<Integer> iterator2 = list.iterator();

        iterator.forEachRemaining(System.out::println); // 迭代器1还是拿到旧数据
        iterator2.forEachRemaining(System.out::println); // 迭代器2还是拿到旧数据

        // 决定迭代器内容是生成时期
    }
}
