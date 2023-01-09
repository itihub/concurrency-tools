package immutable;


public class FinalStringDemo {

    public static void main(String[] args) {
        String a = "wokong2"; // 初始化在常量池中
        final String b = "wokong"; // 初始化在常量池中
        String d = "wokong"; // 初始化在堆中
        String c = b + 2; // 常量池存在wokong2 所以直接拿a的引用地址
        String e = d + 2;
        System.out.println(a == c); // trye
        System.out.println(a == e); // false
    }
}
