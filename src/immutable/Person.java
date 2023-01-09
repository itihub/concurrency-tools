package immutable;

/**
 * 不可变的对象，演示其他类无法修改这个对象 public也不行
 * 如果对象里有属性可以被修改那么这个对象不叫不可变对象
 */
public class Person {

    final int age = 18;
    final String name = "Alice";

    // 普通属性 不被final修饰可变
    int score = 0;
}
