package com.lyloou.chapter9;

import java.util.ArrayList;
import java.util.List;

public class GenericJava {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
        }};
//        genericNumber(list); // can not pass the variable
    }

    private static void genericNumber(List<Number> list) {
        System.out.println(list.size());
    }
}
