package futrue;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 演示一个Future的使用方式，lambda形式
 */
public class OneFutureLambda {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

        // lambda写法
        Callable<Integer> callable = () -> {
            Thread.sleep(3000);
            return new Random().nextInt();
        };

        // 提交任务并获取Future
        service.submit(callable);
        Future<Integer> future = service.submit(callable);

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
}
