package com.cniao5.shujujiegousanfa;

import org.junit.Test;

/**
 *
 * 冒泡排序
 * */
public class BubbleSort {

    @Test
    public void bubbleSortTest() {
        Integer[] array = {7, 3, 5, 8, 6, 7, 4, 5};
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println( );
        Integer[] dest =  bubbleSort(array);
        for (int i = 0; i < dest.length; i++) {
            System.out.print(dest[i]+" ");
        }
    }



     Integer[] bubbleSort(Integer[] array) {
         if (array.length == 0) return array;
         /*循环整个数组长度的次数*/
         for (int i = 0; i < array.length; i++) {

             /**
              * 从第0个元素开始，依次和后面的元素进行比较
              * j < array.length - 1 - i表示第[array.length - 1 - i]
              * 个元素已经冒泡到了合适的位置，无需进行比较，可以减少比较次数,进行性能优化
              * */
             //每一轮的比较相邻元素的比较
             for(int j = 0;j<array.length-1-i;j++){
                 /*如果第j个元素比后面的第j+1元素大，交换两者的位置*/
                 if(array[j]>array[j+1]){
                     int tmp  = array[j];
                     array[j]=array[j+1];
                     array[j+1]=tmp;
                 }
             }

         }

         return array;
    }


}
