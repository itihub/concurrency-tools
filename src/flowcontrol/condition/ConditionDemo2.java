package flowcontrol.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示用Condition实现生产者消费者模式
 */
public class ConditionDemo2 {

    private int queueSize = 10;

    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);

    private Lock lock = new ReentrantLock();

    // 未满条件
    private Condition notFull = lock.newCondition();

    // 不为空条件，用于消费者
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo2 conditionDemo2 = new ConditionDemo2();
        Produce produce = conditionDemo2.new Produce();
        Cunsumer cunsumer = conditionDemo2.new Cunsumer();
        produce.start();
        cunsumer.start();
    }

    class Cunsumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    // 检查队列容量
                    while (queue.size() == 0) {
                        System.out.println("队列空，等待数据");
                        // 等待队列不为空信号
                        notEmpty.await();
                    }

                    // 获取队列数据
                    queue.poll();
                    // 获取数据后传递队列数据减少信号
                    notFull.signalAll();
                    System.out.println("从队列中取走一个数据，队列剩余" + queue.size() + "个于元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Produce extends Thread {
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();
                try {
                    // 检查队列容量是否已满
                    while (queue.size() == queueSize) {
                        System.out.println("队列已满，等待空余");
                        // 等待队列不满信号
                        notFull.await();
                    }

                    // 生成数据到队列
                    queue.offer(1);
                    // 生成数据后传递队列数据不为空信号
                    notEmpty.signalAll();
                    System.out.println("箱队列中插入一个元素，队列剩余" + (queueSize - queue.size()) + "个于元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
