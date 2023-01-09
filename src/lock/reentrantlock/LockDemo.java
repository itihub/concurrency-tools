package lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示ReentrantLock的基本用法 演示被打断
 */
public class LockDemo {

    public static void main(String[] args) {
        new LockDemo().init();
    }

    static class Outputer {
        Lock lock = new ReentrantLock();

        // 字符串打印方法，一个个字符的打印
        public void output(String name) {
            int len = name.length();
            lock.lock();
            try {
                // 用锁保证输出的完整性
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private void init() {
        final Outputer outputer = new Outputer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("悟空");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("大师兄");
                }
            }
        }).start();
    }

}
