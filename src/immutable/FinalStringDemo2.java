package immutable;


public class FinalStringDemo2 {

    public static void main(String[] args) {
        String a = "wokong2"; // 初始化在常量池中
        final String b = getDashixiong(); // b是在运行后方法获得
        String c = b + 2; // 常量池存在wokong2 但是b是在运行后生成的
        System.out.println(a == c); // false 所以不相等
    }

    private static String getDashixiong() {
        return "wukong";
    }
}
