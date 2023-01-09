package threadlocal;

/**
 * ThreadLocal空指针异常
 */
public class ThreadLocalNPE {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }

    /**
     * 如果返回基本类型long而不是包装类型Long,如果没有先set，直接get类型转换会发生空指针异常
     * @return
     */
    public Long get() {
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        }).start();

        threadLocalNPE.set();
        System.out.println(threadLocalNPE.get());
    }
}
