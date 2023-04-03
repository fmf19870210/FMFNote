package com.yzy.xxkotlindemo.伴生对象饿汉式懒汉式对象懒加载注解;

/**
 * Create by Fangmingfei on 2023-04-01 下午 8:49
 * Describe ：  懒汉式的实现 双重校验安全  Java版本
 */
public class SingletonDemo4 {


    private   volatile  static SingletonDemo4 instance;

    public static SingletonDemo4 getInstance(){
        if (instance==null) {
            synchronized (SingletonDemo4.class) {
                instance = new SingletonDemo4();
            }
        }
        return  instance;
     }


      public void show(){
          System.out.println("SingletonDemo4 show");
      }

}
