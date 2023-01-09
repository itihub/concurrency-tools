package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * 演示Accumulator累加器
 */
public class LongAccumulatorDemo {

    public static void main(String[] args) {
        // 参数1：计算公式 参数2：0为初始值
        LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y, 0);

//        accumulator.accumulate(1); // 累加
//        System.out.println(accumulator.getThenReset());

        ExecutorService service = Executors.newFixedThreadPool(8);
        // 循环9次向线程池提交任务
        IntStream.range(1, 10).forEach(i -> service.submit(() -> accumulator.accumulate(i)));

        service.shutdown(); // 关闭线程池
        // 监测线程池是否终止
        while (!service.isTerminated()) {

        }
        System.out.println(accumulator.getThenReset());

    }
}
