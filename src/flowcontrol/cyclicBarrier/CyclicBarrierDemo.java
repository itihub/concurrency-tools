package flowcontrol.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 演示CyclicBarrier
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        // 构造栅栏，设置需要多少个线程到达后触发的统一方法
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程都到达了，大家统一出发");
            }
        });

        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(i, cyclicBarrier)).start();
        }

    }

    static class Task implements Runnable {
        //线程号码
        private int id;
        private CyclicBarrier cyclicBarrier;

        public Task(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + id + "现在前往集合地点");
            try {
                Thread.sleep((long) Math.random() * 10000);
                System.out.println("线程" + id + "到达集合地点，等待其他线程到达");
                // 等待栅栏释放
                cyclicBarrier.await();
                System.out.println("线程" + id + "再次出发");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
