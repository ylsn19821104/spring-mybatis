package com.shihui.test;

import com.google.common.collect.Lists;
import com.shihui.util.HashMap;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Java8Test {
    @Test
    public void test() {
        List<String> names = Lists.newArrayList("peter", "anna", "mike", "xenia");
        /**Collections.sort(names, new Comparator<String>() {
        @Override public int compare(String o1, String o2) {
        return o2.compareTo(o1);
        }
        });
         Collections.sort(names, Comparator.reverseOrder());*/
        Collections.sort(names, (a, b) -> b.compareTo(a));
        System.err.println("" + names);
    }

    @Test
    public void testHashMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        System.err.println("" + map);
    }
}
