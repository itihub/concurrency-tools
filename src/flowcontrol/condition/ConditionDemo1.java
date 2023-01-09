package flowcontrol.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示condition的基本用法
 */
public class ConditionDemo1 {

    private ReentrantLock lock = new ReentrantLock();

    // Condition是绑定在锁上的
    private Condition condition = lock.newCondition();

    void method1() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取了锁");
            System.out.println("条件不满足，开始await");
            condition.await();
            System.out.println("条件满足，开始执行后续任务");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        }
    }

    void method2() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取了锁");
            System.out.println("条件准备工作完成，唤醒其他线程");
            condition.signal();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        }
    }

    public static void main(String[] args) {
        ConditionDemo1 conditionDemo1 = new ConditionDemo1();

        // 另开线程执行method2
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    conditionDemo1.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 主线程执行method1（方法子线程启动后执行，避免主线程先执行卡死不执行子线程启动）
        conditionDemo1.method1();
    }
}
