package futrue;

import java.util.concurrent.*;

/**
 * 演示get的超时方法，需要注意超时后处理，调用cancel()方法
 * 演示cancel方法入参true和false的区别，代表是否中断正在执行的任务。
 */
public class Timeout {

    private static final Ad DEFAULT_AD = new Ad("无网络时的默认广告");

    private static final ExecutorService exec = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        Timeout timeout = new Timeout();
        timeout.printAd();
    }

    public void printAd() {
        Future<Ad> f = exec.submit(new FetchAdTask());
        Ad ad;
        try {
            // 获取广告，容忍时间4s
            ad = f.get(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("被中断时候的默认广告");
        } catch (ExecutionException e) {
            ad = new Ad("异常时候的默认广告");
        } catch (TimeoutException e) {
            ad = new Ad("超时时候的默认广告");
            System.out.println("超时，未获取广告");
            // 使用默认广告，由于请求超时不需要请求的广告了，结束任务
            // true的意义：任务在运行中进行中断任务
            boolean cancel = f.cancel(true);
            System.out.println("cancel的结果：" + cancel);

        }
        exec.shutdown();
        System.out.println(ad);
    }

    static class Ad {
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {
        @Override
        public Ad call() throws Exception {
            // 模拟网络请求时间
            try {
               Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断了");
                return new Ad("被中断时候的默认广告");
            }
            return new Ad("旅游订票哪家强？某程");
        }
    }

}
