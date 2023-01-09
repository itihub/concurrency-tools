package immutable;

/**
 * 演示方法中final变量
 */
public class FinalLocalVariableDemo {

    void test() {
        // 要求使用前必须赋值
        final int a;
        // 需要在使用前赋值，否则编译不通过
        //a = 1;
        //int c = a;
    }
}
