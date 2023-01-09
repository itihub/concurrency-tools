package futrue;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 演示一个Future的使用方式
 */
public class OneFuture {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

        // 提交任务并获取Future
        Future<Integer> future = service.submit(new CallableTask());

        try {
            Integer result = future.get();
            // 打印线程执行结果
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
