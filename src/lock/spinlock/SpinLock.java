package lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁编写
 */
public class SpinLock {

    private AtomicReference<Thread> sign = new AtomicReference<>();

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + "开始尝试获取自旋锁");
                spinLock.lock();
                System.out.println(threadName + "获取到了自旋锁");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.unlock();
                    System.out.println(threadName + "释放了自旋锁");
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }

    public void lock() {
        Thread current = Thread.currentThread();
        // 使用compareAndSet方法进行CAS自旋 期望当前是null(代表没有线程占用) 把当前线程锁住
        while (!sign.compareAndSet(null, current)) {
            System.out.println(current.getName() + "自旋获取失败，再次尝试");
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        // 结束 如果是锁定的是当前线程引用那么意味当前线程占有锁，设置null也就进行了解锁
        sign.compareAndSet(current, null);
    }

}
