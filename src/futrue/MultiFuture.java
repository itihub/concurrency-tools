package futrue;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 演示多个任务，用Future数组来获取结果
 */
public class MultiFuture {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        ArrayList<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Future<Integer> future = service.submit(new CallableTask());
            futures.add(future);
        }

        for (int i = 0; i < 20; i++) {
            Future<Integer> future = futures.get(i);
            try {
                Integer reuslt = future.get();
                System.out.println(reuslt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
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
