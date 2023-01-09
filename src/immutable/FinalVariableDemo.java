package immutable;

/**
 * 演示final变量
 */
public class FinalVariableDemo {

    // 第一种 等号右边赋值
    // private final int a = 6;
    private final int a;

    // 第二种 构造函数赋值
    //public FinalVariableDemo(int a) {
    //    this.a = a;
    //}

    // 第三种 代码块赋值
    {
        a = 7;
    }
}
