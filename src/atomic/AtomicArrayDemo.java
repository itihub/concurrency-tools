package atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 演示原子数组的使用方法
 */
public class AtomicArrayDemo {

    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);

        Increment increment = new Increment(atomicIntegerArray);
        Decremeter decremeter = new Decremeter(atomicIntegerArray);

        Thread[] threadsIncrementer = new Thread[100];
        Thread[] threadsDecrementer = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threadsIncrementer[i] = new Thread(increment);
            threadsDecrementer[i] = new Thread(decremeter);

            threadsIncrementer[i].start();
            threadsDecrementer[i].start();
        }

        for (int i = 0; i < 100; i++) {
            try {
                threadsIncrementer[i].join();
                threadsDecrementer[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 判断是否有不为0的
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0) {
                System.out.println("发现了错误" + i);
            }
        }
        System.out.println("运行结束");
    }
}

class Decremeter implements Runnable {

    private AtomicIntegerArray array;

    public Decremeter(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            // 自减操作
            array.getAndDecrement(i);
        }
    }
}

class Increment implements Runnable {

    private AtomicIntegerArray array;

    public Increment(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            // 自增操作
            array.getAndIncrement(i);
        }
    }
}
