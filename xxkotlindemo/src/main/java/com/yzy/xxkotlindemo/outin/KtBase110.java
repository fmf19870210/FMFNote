 package com.yzy.xxkotlindemo.outin;


import java.util.ArrayList;
import java.util.List;

public class KtBase110 {

    public static void main(String[] args) {
        List<CharSequence> list = new ArrayList<CharSequence>();

        // 泛型默认情况下是：泛型的父类对象 不可以赋值给 泛型的子类对象
        // List<String> list1 = new ArrayList<CharSequence>(); //todo 这个就不行 父类不能赋值给子类

        // 泛型默认情况下是：泛型具体处的父类对象  不可以赋值给  泛型声明处的子类对象

        // CharSequence父类        String子类

        // ? super T 就相当于 KT里面的in，所以才可以 泛型父类对象 赋值给 泛型子类对象
        // in: 泛型具处的父类对象 可以赋值给 泛型声明处的子类对象

        List<? super String> list2 = new ArrayList<CharSequence>();  //todo 这个就可以父类赋值给子类

        // 逆变：子类 泛型声明处  可以接收   父类 泛型具体处
    }
}
