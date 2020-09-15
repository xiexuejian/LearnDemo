package com.tky.sentinel02.test;

import org.junit.Test;

import java.util.Arrays;

public class TestOneWayLinkedList {

    @Test
    public void test01(){
        OneWayLinkedList<String> my = new OneWayLinkedList<String>();
        my.add("hello");
        my.add("java");
        my.add("world");
        my.add("atguigu");
        my.add("list");
        my.add("data");

        System.out.println("元素个数：" + my.size());
        Object[] all = my.getAll();
        System.out.println(Arrays.toString(all));
    }

    @Test
    public void test02(){
        OneWayLinkedList<String> my = new OneWayLinkedList<String>();
        my.add("hello");
        my.add("java");
        my.add("world");
        my.add("atguigu");
        my.add("list");
        my.add("data");

        my.delete("hello");
        System.out.println("元素个数：" + my.size());
        Object[] all = my.getAll();
        System.out.println(Arrays.toString(all));

        my.delete("atguigu");
        System.out.println("元素个数：" + my.size());
        all = my.getAll();
        System.out.println(Arrays.toString(all));

        my.delete("data");
        System.out.println("元素个数：" + my.size());
        all = my.getAll();
        System.out.println(Arrays.toString(all));
    }

    @Test
    public void test03(){
        OneWayLinkedList<String> my = new OneWayLinkedList<String>();
        my.add("hello");
        my.add("java");
        my.add("world");
        my.add("atguigu");
        my.add("list");
        my.add("data");

        my.update("java", "Java");
        System.out.println("元素个数：" + my.size());
        Object[] all = my.getAll();
        System.out.println(Arrays.toString(all));
    }

    @Test
    public void test04(){
        OneWayLinkedList<String> my = new OneWayLinkedList<String>();
        my.add("hello");
        my.add("java");
        my.add("world");
        my.add("atguigu");
        my.add("list");
        my.add("data");

        System.out.println(my.contains("java"));
        System.out.println(my.indexOf("java"));
    }
}
