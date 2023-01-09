package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池执行1000个任务打印格式化的日期
 *
 * 进阶：解决00示例每个任务都创建SimpleDateFormat对象问题，将SimpleDateFormat对象声明成静态变量
 *
 * 现象：出现重复日期，所有线程都共用一个SimpleDateFormat对象发生线程安全问题
 * 解决：使用加锁的方式来解决线程安全问题
 */
public class ThreadLocalNormalUsage02 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage02().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }

    public String date(int seconds) {
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT 计时
        Date date = new Date(1000 * seconds);
        // 使用synchronized 添加类锁
        String s = null;
        synchronized (ThreadLocalNormalUsage02.class) {
            s = dateFormat.format(date);

        }
        return s;
    }
}
