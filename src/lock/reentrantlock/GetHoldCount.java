package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁演示
 */
public class GetHoldCount {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 使用lock.getHoldCount() 打印获得锁的次数
        System.out.println(lock.getHoldCount());
        lock.lock(); // 第一次获取锁
        System.out.println(lock.getHoldCount());
        lock.lock(); // 可重入获取锁，上次获得没有解锁
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock(); // 解锁1次
        System.out.println(lock.getHoldCount());
        lock.unlock(); // 解锁2次
        System.out.println(lock.getHoldCount());
        lock.unlock(); // 解锁3次
        System.out.println(lock.getHoldCount());
    }
}
