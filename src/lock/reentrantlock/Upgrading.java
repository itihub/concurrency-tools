package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示锁的升降级
 */
public class Upgrading {

    // 构造参数 false代表 非公平锁， true代表公平锁
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("演示降级（读锁降级写锁）");
        Thread thread1 = new Thread(() -> writeDowngrading(), "Thread1");
        thread1.start();
        thread1.join(); // 线程执行完启动其他线程

        System.out.println("------------------------------------");

        System.out.println("演示升级（读锁升级写锁）是不行的");
        Thread thread2 = new Thread(() -> readUpgrading(), "Thread2");
        thread2.start();
    }

    private static void readUpgrading() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到读锁，正在读取");
            Thread.sleep(1000);
            // 尝试拿到写锁 （读升级）
            System.out.println(Thread.currentThread().getName() +"升级不会成功会带来阻塞");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() +"获取到写锁，升级成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void writeDowngrading() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到写锁，正在读写入");
            Thread.sleep(1000);
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "在不释放写锁的情况下，直接获取读锁，成功降级");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }
}
