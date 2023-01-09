package collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 组合操作并不保证线程安全
 */
public class OptionsNotSafe implements Runnable {

    private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("小明", 0);
        Thread t1 = new Thread(new OptionsNotSafe());
        Thread t2 = new Thread(new OptionsNotSafe());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(scores);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {

            // 方式一：组合操作导致线程不安全的累加
            // 读取值（线程安全操作）
            //Integer score = scores.get("小明");
            // 计算新值(线程不安全操作)
            //Integer newScore = score + 1;
            // 赋值（线程安全操作）
            //scores.put("小明", newScore);

            // 方式二：使用原子方法解决
            // 使用自旋修改成功后退出
            while (true) {
                // 读取值（线程安全操作）
                Integer score = scores.get("小明");
                // 计算新值(线程不安全操作)
                Integer newScore = score + 1;
                // 使用replace来保证值相加的线程安全
                boolean b = scores.replace("小明", score, newScore);
                if (b) {
                    break;
                }
            }
        }
    }
}
