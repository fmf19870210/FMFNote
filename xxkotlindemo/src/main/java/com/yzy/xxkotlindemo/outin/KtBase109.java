package com.yzy.xxkotlindemo.outin;


import java.util.ArrayList;
import java.util.List;

public class KtBase109 {

    public static void main(String[] args) {
        List<CharSequence> list = new ArrayList<CharSequence>();

        // 泛型默认情况下是：泛型的子类对象 不可以赋值给 泛型的父类对象
        // List<CharSequence> list1 = new ArrayList<String>(); //todo 这个就不能子类赋值给父类
        // 泛型默认情况下是：泛型具体处的子类对象  不可以赋值给 泛型声明处的父类对象

        // CharSequence父类        String子类

        // ? extends T 就相当于 KT里面的out，所以才可以 泛型子类对象 赋值给 泛型父类对象
        // out: 泛型具体出的子类对象 可以赋值给 泛型声明处的父类对象  

        List<? extends CharSequence> list2 = new ArrayList<String>(); // todo 这个就可以 子类赋值给父类

        // 协变：父类 泛型声明处  可以接收   子类 泛型具体处
    }
}
