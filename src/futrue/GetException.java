package futrue;

import java.util.concurrent.*;

/**
 * 演示Get方法过程中抛出异常，for循环为了演示抛出异常的时机：并不是说一产生异常就抛出，而是get()执行时，才抛出
 */
public class GetException {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(20);

        Future<Integer> future = service.submit(new CallableTask());

        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }
            System.out.println(future.isDone());
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("拦截了InterruptedException");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("拦截了ExecutionException");
        }
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            // 抛出IllegalArgumentException异常
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }
}
