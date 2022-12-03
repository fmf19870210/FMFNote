package com.cniao5.shujujiegousanfa;

import org.junit.Test;

import java.util.Arrays;


/**
 *  插入排序
 *
 *
 * */
public class InsertSort {

    @Test
    public void insertSortTest() {
        Integer[] arr = {101, 34, 119, 1, -1, 90, 123};
        System.out.println("插入排序前 "+ Arrays.toString(arr));
        //insertSort(arr);
        // insertSort2(arr);
        insertSort3(arr);

    }

   private  void   insertSort3(Integer[] arr){
       if (arr.length == 0) return ;
       int  currentValue;
       for (int i = 0; i < arr.length-1; i++) {
           //已被排序数据的索引
           int preIndex = i;
            //定义待排序的数值array[preIndex + 1]  待排序的角标preIndex + 1
           //待排序的元素之前的元素arr[i]均已被排序过
           currentValue = arr[preIndex+1];
           System.out.println("待排序元素索引:"+(i + 1)+"， 待排序元素值为：" +currentValue+
                   ",已被排序数据的索引:"+preIndex);
           //1. preIndex >= 0 保证在给待插入元素currentValue 找插入位置，不越界
           //2.如果当前待排序数据currentValue比 比较的已排序的元素值array[preIndex]要小，
           //将比较的已排序的元素值array[preIndex]后移一位,腾出一个空位置给currentValue插入
           while (preIndex>=0&&currentValue<arr[preIndex]){
               //将比较的已排序的元素值array[preIndex]后移一位
               arr[preIndex+1]=arr[preIndex];
               preIndex--;
           }
           //while循环结束时，说明已经找到了当前待排序数据的合适插入位置，preIndex + 1就是要插入的位置
           arr[preIndex+1]=currentValue;
           System.out.println("第"+i+"轮插入排序 "+Arrays.toString(arr));
       }

   }




    private void insertSort2(Integer[] arr) {
        if (arr.length == 0) return ;
        int insertVal = 0;
        int insertIndex = 0;
        //使用for循环来把代码简化
        for (int i = 1; i < arr.length; i++) {
               insertVal = arr[i];//定义原表arr中待插入的数值arr[i]  角标i
              insertIndex = i - 1; //定义已被排序数据的索引i - 1
            System.out.println("待排序元素索引:"+(i )+"，值为：" +insertVal+
                    ",已被排序数据的索引:"+insertIndex);
            //给待插入数值insertVal 找到插入的位置
            //1. insertIndex >= 0 保证在给待插入数值insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置  如果当前待排序数据insertVal比比较的元素arr[insertIndex]要小，
            // 将比较的元素元素arr[insertIndex]后移一位
             while (insertIndex>=0&&insertVal<arr[insertIndex]){
                 arr[insertIndex+1]=arr[insertIndex];//将比较的元素元素arr[insertIndex]后移一位
                 insertIndex--;
             }

             //当退出while循环时，说明插入的位置找到,  将待插入的数值insertVal  赋值给arr[insertIndex+1]
            arr[insertIndex+1]= insertVal;
          System.out.println("第"+i+"轮插入排序 "+Arrays.toString(arr));

        }

    }





    private void insertSort(Integer[] arr) {
        //使用逐步推导的方式来讲解，便利理解
        //第1轮 {101, 34, 119, 1};  => {34, 101, 119, 1}
        //{101, 34, 119, 1}; => {101,101,119,1}
        //原表arr 分为 有序列表 {101}  无序列表{34, 119, 1}
        int insertVal = arr[1];//定义原表arr中待插入的第一个数值arr[1]=34
        int insertIndex = 1 - 1; //定义待插入数值arr[1]的前一位的下标1-1=0
         //给待插入数值insertVal 找到插入的位置
        //1. insertIndex >= 0 保证在给待插入数值insertVal 找插入位置，不越界
        // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置  如果当前待排序数据insertVal比比较的元素arr[insertIndex]要小，
        // 将比较的元素元素arr[insertIndex]后移一位
      while (insertIndex>=0&&insertVal<arr[insertIndex]){
         arr[insertIndex+1]=arr[insertIndex];//将比较的元素元素arr[insertIndex]后移一位
         insertIndex--;
     }
     //当退出while循环时，说明插入的位置找到,  将待插入的数值insertVal  赋值给arr[insertIndex+1]
        arr[insertIndex+1]=insertVal;
        System.out.println("第1轮插入排序"+Arrays.toString(arr));






    }

}
