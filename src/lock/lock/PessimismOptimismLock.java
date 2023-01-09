package lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 乐观锁和悲观锁的例子
 */
public class PessimismOptimismLock {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        // 原子类增加类（底层乐观锁）
        atomicInteger.incrementAndGet();
    }

    int a;

    /**
     * 悲观锁
     */
    public synchronized void testMethod() {
        a++;
    }
}
