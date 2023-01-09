package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示CountDownLatch
 * 情景：（一等多）工厂中，5个质检工人，产品需要5个质检人员都检测通过才可以进入下一个缓解
 */
public class CountDownLatchDemo1 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(5);

        ExecutorService service = Executors.newFixedThreadPool(5);
        // 模拟5个质检人员检查工作
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No." + no + "完成了检查");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            };
            // 使用线程池让质检员同步执行
            service.submit(runnable);
        }

        System.out.println("等待5个质检人员工作完成.....");

        // 主线程等待质检人员质检工作完成
        latch.await();
        System.out.println("所有人质检人员质检通过，进入下一个环节");
    }
}
