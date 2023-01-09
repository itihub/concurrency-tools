package collection;

import java.util.HashMap;
import java.util.Map;

/**
 * 演示Map的常用方法
 */
public class MapDemo {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        System.out.println("判断Map里面是否为空方法：" + map.isEmpty());
        map.put("东哥", 38); // 赋值方法
        map.put("西哥", 28);
        System.out.println("返回整个键的集合方法：" + map.keySet());
        System.out.println("拿到一个键对应的值方法：" + map.get("西哥"));
        System.out.println("返回Map的大小方法：" + map.size());
        System.out.println("判断键是否在Map里面方法：" + map.containsKey("东哥"));
        map.remove("东哥"); // 删除key的方法
    }
}
