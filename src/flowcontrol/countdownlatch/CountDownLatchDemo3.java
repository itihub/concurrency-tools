package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示CountDownLatch
 * 短跑比赛，多个运动员等待起点裁判发令，几乎同一时刻起跑（抢跑犯规），终点裁判所有人都到终点比较结束
 */
public class CountDownLatchDemo3 {

    public static void main(String[] args) throws InterruptedException {

        // (起点裁判)一次倒数，多个线程等待一个线程调用countDown()方法
        CountDownLatch begin = new CountDownLatch(1);
        // (终点裁判)多次倒数，所有运动员都到终点后比赛结束
        CountDownLatch end = new CountDownLatch(5);

        ExecutorService service = Executors.newFixedThreadPool(5);

        // 模拟多个远动员等待裁判发令
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("准备完毕，等待裁判发令");
                    try {
                        begin.await();
                        System.out.println("No." + no + "起跑");
                        // 模拟跑步时间
                        Thread.sleep((long) Math.random() * 10000);
                        System.out.println("No." + no + "到达终点");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            };

            service.submit(runnable);
        }

        // 起点裁判员检查发令枪
        Thread.sleep(5000);
        System.out.println("发令枪响");
        begin.countDown();

        // 终点裁判等待所有运动员到达终点
        end.await();
        System.out.println("所有运动员到达终点,比赛结束！");
    }
}
