package immutable;

/**
 * 测试final能否被修改
 */
public class TestFinal {

    public static void main(String[] args) {
        Person person = new Person();
        // 被final修饰无法修改，编译不通过
//        person.age = 19;

    }

}
