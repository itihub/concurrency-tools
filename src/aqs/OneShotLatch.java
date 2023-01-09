package aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自己用AQS实现一个简单的线程协作器
 * 一次性门闩
 */
public class OneShotLatch {

    public static void main(String[] args) throws InterruptedException {
        OneShotLatch oneShotLatch = new OneShotLatch();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() +  "尝试获取门闩，获取失败就等待");
                    oneShotLatch.await();
                    System.out.println(Thread.currentThread().getName() +  "开闸放行，继续运行");
                }
            }).start();
        }
        Thread.sleep(5000);
        oneShotLatch.signal();
    }

    private Sync sync = new Sync();

    /**
     * 获取方法
     */
    public void await() {
        sync.acquireShared(0);
    }

    /**
     * 释放方法
     */
    public void signal() {
        sync.releaseShared(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

}
