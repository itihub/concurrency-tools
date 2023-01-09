package queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 一个面试官对10个面试者，大厅里有3个位置提供面试者休息，每个人面试时间是10秒，模拟所有人面试的场景
 */
public class ArrayBlockingQueueDemo {


    public static void main(String[] args) {

        // 声明阻塞队列长度为3
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        Interviewer r1 = new Interviewer(queue);
        Consumer r2 = new Consumer(queue);
        new Thread(r1).start();
        new Thread(r2).start();
    }

}

// 面试官类
class Interviewer implements Runnable {
    BlockingQueue<String> queue;

    public Interviewer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("10个候选人都来了");
        for (int i = 0; i < 10; i++) {
            String candidate = "Candidate" + i;
            try {
                queue.put(candidate);
                System.out.println("安排好了：" + candidate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            // 结束信号
            queue.put("stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

// 候选人类
class Consumer implements Runnable {
    BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msg;
        try {
            while (!(msg = queue.take()).equals("stop")) {
                System.out.println(msg + "到了");
            }
            System.out.println("所有候选人都结束了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}