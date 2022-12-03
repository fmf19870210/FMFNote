package com.cniao5.shujujiegousanfa;

import org.junit.Test;

import java.util.Arrays;

public class ChoiceSort {


    @Test
    public void choiceSortTest() {
        Integer[] arr = {101, 34, 119, 1, -1, 90, 123};
        System.out.println("排序前 "+Arrays.toString(arr));
        choiceSort(arr);
        System.out.println("选择排序后 "+Arrays.toString(arr));

    }


    /**
     * 直接选择排序，每次选最小的放到最前
     */
    private void choiceSort(Integer[] arr) {

      //在推导的过程，我们发现了规律，因此，可以使用for来解决
        //选择排序时间复杂度是 O(n^2)(嵌套for循环)

        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;// 假定最小的索引 i
            int min = arr[i]; // 假定最小数值  arr[i]
          for(int j=i+1;j<arr.length;j++){
            //todo 如果要从大到小,只需 min<arr[j] 判断即可
              if(min>arr[j]){// 说明假定的最小值arr[i]，并不是最小
                min = arr[j]; //重置min最小值
                minIndex=j;//重置minIndex最小值的角标
              }
          }
     //将最小值arr[minIndex]，放在arr[0], 即交换
            if(minIndex!=i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

        }




    /* //使用逐步推导的方式来，讲解选择排序
        //第1轮
        //原始的数组 ： 	101, 34, 119, 1
        //第一轮排序 :   	1, 34, 119, 101
        //算法 先简单--》 做复杂， 就是可以把一个复杂的算法，拆分成简单的问题-》逐步解决
        //第1轮
        int minIndex = 0;// 假定最小的索引 0
        int min = arr[0]; // 假定最小数值  arr[0]
       for(int j = 0+1;j<arr.length;j++){ //从当前这个元素之后(0 + 1)开始遍历
            if(min>arr[j]){// 说明假定的最小值arr[i]，并不是最小
              min = arr[j]; //重置min最小值
              minIndex = j;    //重置minIndex最小值的角标
            }
        }

        //将最小值arr[minIndex]，放在arr[0], 即交换
         if(minIndex!=0){
             arr[minIndex] = arr[0];
             arr[0]  = min;
         }*/

    }
}
