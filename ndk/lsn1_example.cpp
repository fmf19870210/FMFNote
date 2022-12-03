// lsn1_example.cpp: 定义应用程序的入口点。
//

#include "lsn1_example.h"


#include <stdbool.h>


using namespace std;


void test() {
	printf("test\n");
}

int main()
{
	//更基础的头文件 部分
	 test();
	 test1();
	 test3();

// 基本数据类型 (从与java差异性来看)
//============================================================	 

	 //signed int = int
	 signed int i1 = 1;
	 //无符号 取值范围不同  (推荐使用 uint32_t )
	 unsigned int i2 = 1;
	 // long 就是 long int
	 long l1 = 1L;
	 // long long 才是java的long (推荐使用 int64_t )
	 // C99 stdbool.h中定义了 bool 
	 //非0为true ， null就是 0 

// 格式化 
//============================================================	 
	 //见资料中的笔记   String.format("%d",xx)
	 printf("%u\n",i2);
	 //常用 sprintf 来格式化字符串
	 char str[100];
	 for (size_t i = 0; i < 3; i++)
	 {
		 //讲 2、3参数 格式化的 字符 复制到 str 中
		 sprintf(str,"D:/Lance/ndk/lsn1_c/资料/新建文件夹/%d.txt",i);
		 //  \n 换行
		 printf("%s\n", str);
	 }

// 数组声明
//============================================================	 	 
	 // c当中 定义数组 必须指明数组长度 或者 声明与赋值写在一起
	 // 连续的内存 int=4*6 = 24 字节 栈内存
	 int array1[6];
	 int array2[] = { 1,2,3,4,5,6 };
	
	 //当数据无法确定 或者 比较庞大 需要使用动态内存申请 在堆中
	 int *di1 = (int*)malloc(1 * 1024 * 1024);
	 //动态申请内存应该紧跟 memset 初始化内存
	 memset(di1, 0, 1 * 1024 * 1024);

	 // 申请内存并将内存初始化为 null 
	 int *di2 = (int*)calloc(10, sizeof(int));

	 // 对malloc申请的内存进行大小的调整
	 realloc(di1, 20 * sizeof(int));
	 
	//一定要free 并养成好习惯 将指针置为 null
	 //标准写法为：
	 if (di1) {
		 free(di1);
		 di1 = 0;
	 }
	 if (di2) {
		 free(di2);
		 di2 = 0;
	 }
	
	
	system("pause");
	return 0;
}
