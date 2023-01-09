package immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * 不可变对象，对象有属性是对象，但是整体不可变，其他类无法修改
 */
public class ImmutableObjectDemo {

    // 保证对象属性不可变，private修饰其他类无法访问，构造器完成初始化
    private final Set<String> students = new HashSet<>();

    // 构造器初始化
    public ImmutableObjectDemo() {
        students.add("李小美");
        students.add("王壮");
        students.add("徐福记");
    }

    public boolean isStudent(String name) {
        return students.contains(name);
    }
}
