package cas;

/**
 * 模拟CAS操作的等价代码
 */
public class SimulatedCAS {

    private volatile int value;

    /**
     * 比较并交换（模拟CAS操作，用synchronized保证原子性）
     * @return
     */
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }
}
