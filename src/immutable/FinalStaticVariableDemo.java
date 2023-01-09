package immutable;

/**
 * 演示static final变量
 */
public class FinalStaticVariableDemo {

    // 第一种 等号右边赋值
    // private static final int a = 6;

    private static final int a;

    // 第二种 静态代码块中赋值
    static {
        a = 6;
    }


}
