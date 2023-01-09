package immutable;

/**
 * 演示final修饰方法
 */
public class FinalMethodDemo {

    // 普通方法
    public void drink() {

    }

    // final修饰的方法
    public final void eat() {

    }

    // static修饰的方法
    public static void sleep() {

    }
}

// 子类继承父类
class SubClass extends FinalMethodDemo {
    // 重写普通变量
    @Override
    public void drink() {
        super.drink();
    }

    // 不能被重写，编译错误
    //public void eat() {
    //
    //}


    // 静态方法不能重写，编译错误。但是允许有重名方法，final不允许有重名方法
    //public void sleep() {
    //
    //}
}