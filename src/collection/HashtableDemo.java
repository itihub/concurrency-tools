package collection;

import java.util.Hashtable;

/**
 * 演示Hashtable
 */
public class HashtableDemo {

    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("Key1", "1");
        System.out.println(hashtable.get("Key1"));
    }
}
