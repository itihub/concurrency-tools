package immutable;

/**
 * 演示栈封闭的两种情况：基本类型和对象
 * 先演示线程争抢共享变量带来的错误，然后把变量放在方法内，情况就变了。
 */
public class StaticConfinement implements Runnable {
    // 共享变量
    int index = 0;

    // 使用方法内局部变量来进行栈封闭 累加1万次
    public void inThread() {
        // 方法内变量 多个线程是不共享的
        int neverGoOut = 0;
        for (int i = 0; i < 1000; i++) {
            neverGoOut++;
        }
        System.out.println("栈内保护的数字是线程安全的：" + neverGoOut);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            index++;
        }
        inThread();
    }

    public static void main(String[] args) throws InterruptedException {
        StaticConfinement r = new StaticConfinement();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.index);
    }
}
