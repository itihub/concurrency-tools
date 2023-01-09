package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 演示Collections.synchronizedList(new ArrayList<E>())
 */
public class SynList {

    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        list.add(5);
        System.out.println(list.get(0));
    }
}
