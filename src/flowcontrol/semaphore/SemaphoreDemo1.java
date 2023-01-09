package flowcontrol.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 演示Semaphore用法
 *
 */
public class SemaphoreDemo1 {

    public static void main(String[] args) {

        // 设置并发量和公平
        Semaphore semaphore = new Semaphore(3, true);

        ExecutorService service = Executors.newFixedThreadPool(50);

        for (int i = 0; i < 1000; i++) {
            service.submit(new Task(semaphore));
        }
        service.shutdown();
    }

    static class Task implements Runnable {

        private Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 默认获取1个许可证，可以设置成多个
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了许可证");
            try {
                // 模拟耗时操作
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "释放了许可证");
            semaphore.release();
        }
    }
}
