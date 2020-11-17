package com.jdk.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * JDK 1.8 -> lambda表达式的使用
 * Create by lisang on 2018/10/6.
 */
public class LambdaTest {

    public static void main(String[] args){
        System.out.println("yuyang");
        int a = 10;
        int b = 10;
        int c = 10;

        for (int i = 0; i < 10; i++) {
            i++;
            a = i;
        }

        test();
    }

    private static void test() {
        String name = "beijing";
        System.out.println(name);
    }

}
