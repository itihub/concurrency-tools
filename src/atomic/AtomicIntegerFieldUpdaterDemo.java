package atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 演示AtomicIntegerFieldUpdater的用法
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable {

    static Candidate tom;
    static Candidate peter;

    public static AtomicIntegerFieldUpdater<Candidate> scoreUpdate
            // 传入类型 和更新变量的名字
            = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    public static void main(String[] args) throws InterruptedException {
        tom = new Candidate();
        peter = new Candidate();
        AtomicIntegerFieldUpdaterDemo r = new AtomicIntegerFieldUpdaterDemo();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("普通变量：" + peter.score);
        System.out.println("升级为原子变量后结果：" + tom.score);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            peter.score++; // 普通变量加操作
            scoreUpdate.getAndIncrement(tom); // 升级为原子类实现加操作
        }
    }

    // 候选人类
    public static class Candidate {
        volatile int score;
    }
}
