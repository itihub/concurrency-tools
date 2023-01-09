package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 递归
 * 演示 打印字符串 可重入锁使用案例
 */
public class RecursionDemo {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        accessResource();
    }

    private static void accessResource() {
        lock.lock();
        try {
            System.out.println("已经对资源进行了处理");
            // 资源处理5次才可以释放锁
            if (lock.getHoldCount() < 5) {
                System.out.println(lock.getHoldCount());
                // 递归调用
                accessResource();
                System.out.println(lock.getHoldCount());
            }
        } finally {
            lock.unlock();
            System.out.println("释放锁" + lock.getHoldCount());
        }
    }
}
