 
C++与Java的相互作用,就是Java进行输入传递数据给C++,经C++转化将有价值的东西传给Java端

新建Native C++ 的项目之后，main文件夹下会有cpp文件夹，这里存放所有关于C++的文件
cpp文件夹里面有 CMakeLists.txt,XXX.cpp文件(可以多个C++文件),jni.h 头文件(可以多个) 
下面做一一介绍



一：System.loadLibrary("native-lib")什么意识?
  
	用于在应用程序启动时加载“native-lib”C++类库  加载动态so库,System.loadLibrary指定so库名:库全名为native-lib.so

	static {
        System.loadLibrary("native-lib");
    }





二:public native String stringFromJNI(); 
public native String stringFromJNI(int i,String j,float k); 什么意识？



三 XXX.h 头文件






四: XXX.cpp(native-lib)  C++文件

					 //头文件 jni.h   在java的jdk/jnclude/jni.h  文件夹里
					#include <jni.h>
					#include <string>
					#include <android/log.h> //ndk 提供的日志库
					
					
					
					//  __VA_ARGS__ 代表... 可变参数
					#define  LOGE(...) __android_log_print(ANDROID_LOG_ERROR,"JNI",__VA_ARGS__);
					
					
					
					
					
					int JNI_Onload(){}
					
					
					
					
					
					//c++编译   需要 加 extern "C"
					extern "C"
					
					// JNIEXPORT  JNICALL  宏
					JNIEXPORT jstring JNICALL
					//静态注册:  Java_PACKAGENAME_CLASSNAME_METHODNAME
					Java_com_fmf_dn_1jni_1demo_MainActivity_stringFromJNI(
					        //JNIEnv: 由Jvm传入与线程相关的变量。定义了JNI系统操作、java交互等方法。
					        JNIEnv *env,
					        //jobject: 表示当前java调用对象，即 this , 如果是静态的native方法，则获得jclass
					        jobject, /* this */
					        // java 传递过来的参数int i,String j,float k
					        jint i, jstring j, jfloat k)
					        {
					    //std::string hello = "Hello from C++";
					  // 返回java字符串
					  // return env->NewStringUTF(hello.c_str());
					            //使用 env->GetStringUTFChars将jstring转化为string  JNI_FALSE=0
					    const char* str_hello =  env->GetStringUTFChars(j,JNI_FALSE);
					
					     // 返回java字符串
					     return env->NewStringUTF(str_hello);
					
					}
					
					
					
					
					
					
					extern "C"
					JNIEXPORT jint JNICALL
					Java_com_fmf_dn_1jni_1demo_MainActivity_test(JNIEnv *env, jobject  ,
					                             //int[] i,String[] j
					                              jintArray i_, jobjectArray j_) {
					   //把java 的数组i_ 转为 c++的jint指针 jint *i(首元素地址)
					    jint *i = env->GetIntArrayElements(i_, NULL);
					    //获取数组i_的长度
					  int32_t  i_length=env->GetArrayLength(i_);
					    //获得字符串数组的数据
					    for (int k = 0; k < i_length; ++k) {
					       // __android_log_print(ANDROID_LOG_ERROR,"JNI","获取java的参数:%d",*(i+k));
					        LOGE("获取java的参数:%d",*(i+k));
					    }
					
					    //释放
					    // 参数3：mode 0:  刷新java数组 并 释放c/c++数组  1 = JNI_COMMIT: 只刷新java数组   2 = JNI_ABORT  只释放
					    env->ReleaseIntArrayElements(i_, i, 0);
					
					
					
					
					    //================================================
					
					
					
					    return 100;
					
					}
					
					
					  




五: CMakeLists.txt 的配置

  1.设置构建本机库所需的最小CMake版本
   cmake_minimum_required(VERSION 3.4.1)


  2.add_library(
     #  设置库的名称(自己随便取名称 src/main/cpp/native-lib.cpp)
        native-lib


        #设置库的类型,这里设置为shared 动态库(anndroid的动态库.so) 分享给java程序加载的
        SHARED


​         
       #直接要加载的C++文件(src/main/cpp/native-lib.cpp),可以同时加载多个不同的c++文件,
        native-lib.cpp)


# 在ndk中查找log库 取别名log-lib
3.find_library( 
        # 设置路径变量的名称
        log-lib
        log)


#设置 target 需要链接的库
4.target_link_libraries( 
        native-lib


        ${log-lib})



六:动态库与静态库的区别

动态库:anndroid的动态库.so(windows 的动态库.dll) 给java使用的













七:jni 数据类型

Java类型 	jni数据类型 	      	描述
boolean 	jboolean 	  		C/C++8位整型
byte 	jbyte 					C/C++带符号的8位整型
char 	jchar 					C/C++无符号的16位整型
short 	jshort 					C/C++带符号的16位整型
int 	jint 					      C/C++带符号的32位整型
long 	jlong 					C/C++带符号的64位整型
float 	jfloat 					C/C++32位浮点型
double 	jdouble 				C/C++64位浮点型
Object 	jobject 				任何Java对象，或者没有对应java类型的对象
Class 	jclass 					Class对象
String 	jstring 				字符串对象
Object[] 	jobjectArray 		任何对象的数组
boolean[] 	jbooleanArray 		布尔型数组
byte[] 	jbyteArray 				比特型数组
char[] 	jcharArray 				字符型数组
short[] 	jshortArray 		短整型数组
int[] 	jintArray 				 整型数组
long[] 	jlongArray 				长整型数组
float[] 	jfloatArray 		   浮点型数组
double[] 	jdoubleArray 		双浮点型数组

 





八:装 CMake 插件 








九: java 给jni 传递object bean  对象(JNI 反射java)

1.在C/C++中反射创建Java的对象，调用Java的方法

 //反射调用java方法
    //1、获取java对应的class对象
    jclass beanCls =env->GetObjectClass(bean);
    //2、找到要调用的javaBean的对应的方法
    // 参数1:反射的bean 对象 beanCls
    //参数2:调用javaBean的方法名 getI
    // 参数3：签名   ():表示方法getI()的签名(没有参数)   I(int) 表示  方法getI()返回值类型的签名  这里是int 返回值类型
   jmethodID  getI = env->GetMethodID(beanCls,"getI","()I");
//3、调用:在c++里输出javaBean的getI()方法里的数据
 jint i=env->CallIntMethod(bean,getI);
    LOGE("在C++ 里调用JavaBean getI()方法数据:%d",i); //在C++ 里调用JavaBean getI()方法数据:10000



    //2、找到要调用的javaBean的对应的 setXXX(XXX)方法(有参数)
// 参数1:反射的bean 对象 beanCls
    //参数2:调用javaBean的方法名  setI(int i)方法
    // 参数3：签名   (I):表示方法setI(int i)的签名((I)括号里面的I表示参数类型int(I) )
//   V 表示  javaBean方法setI(int i)返回值类型的签名  这里是返回值类型void(V)

    jmethodID  setI = env->GetMethodID(beanCls,"setI","(I)V");
       env->CallVoidMethod(bean,setI,200);
    LOGE("在C++ 里调用JavaBean setI()方法，进行数据修改后,再次输出getI()方法输出数据:%d",i);//200




2.通过手写JNI反射很麻烦, 通过写   javap -s 全限定名,查看输出的 descriptor 自动获取 




十:  java的方法的返回值类型的签名  

Java类型 	签名
boolean 	Z
short 		S
float 		F
byte 		B
int 		I
double 		D
char 		C
long 		J
void 		V
引用类型 	L + 全限定名 + ;
数组 		[+类型签名

 












十一:JNI引用
 局部引用（Local Reference）
全局引用（Global Reference）
弱全局引用（Weak Global Reference）。


1.局部引用

 1.1大多数JNI函数会创建局部引用。NewObject/FindClass/NewStringUTF 等等都是局部引用。
局部引用只有在创建它的本地方法返回前有效,本地方法返回后，局部引用会被自动释放。
因此无法跨线程、跨方法使用

1.2释放一个局部引用有两种方式:
(1)本地方法执行完毕后VM自动释放；
(2)通过DeleteLocalRef手动释放；


1.3  VM会自动释放局部引用，为什么还需要手动释放呢？
因为局部引用会阻止它所引用的对象被GC回收。


1.4  局部引用的问题？ 
  局部引用内部被释放了,虽然指针有值, 但是指针指向的地址数据被释放了为空, (即悬空指针)



2.全局引用

    全局引用可以跨方法、跨线程使用，直到它被手动释放才会失效 。
    由 NewGlobalRef 函数创建
   全局引用会阻止回收 


3.弱引用

  与全局引用类似，弱引用可以跨方法、线程使用。与全局引用不同的是，弱引用不会阻止GC回收它所指向的VM内部的对象 。

在对Class进行弱引用是非常合适（FindClass），因为Class一般直到程序进程结束才会卸载。

在使用弱引用时，必须先检查缓存过的弱引用是指向活动的对象，还是指向一个已经被GC的对象



 弱引用不会阻止回收,









十二:jni的动态注册和静态注册
        
      
      1. 静态注册  :  在jni中使用的 Java_PACKAGENAME_CLASSNAME_METHODNAME 来进行与java方法的匹配  
      2.动态注册: 很麻烦,还是用静态注册吧



 






十三:jni native线程调用Java 代码 
native调用java需要使用JNIEnv这个结构体，而JNIEnv是由Jvm传入与线程相关的变量。
但是可以通过JavaVM的AttachCurrentThread方法来获取到当前线程中的JNIEnv指针






十四: 处理器的介绍
   
    1.arm64-v8a、armeabi-v7a、x86、x86_64的介绍
     
        arm 架构注重的是续航能力:大部分的移动设备
 	    x86 架构注重的是性能:大部分的台式机和笔记本电脑	
        arm64-v8a :第8代、64位ARM处理器
        armeabi-v7a :第7代及以上的 ARM处理器
        x86：x86 架构的 CPU（Intel 的 CPU）
        x86_64:x86 架构的64位 CPU（Intel 的 CPU）
    2.配置输出的.so架构类型
    
        在app下的build.gradle来指定编译的.so类型,编译出来的只有'armeabi-v7a', 'arm64-v8a'
        android {
	    defaultConfig {
	        externalNativeBuild {
	            cmake {
	                abiFilters 'armeabi-v7a', 'arm64-v8a'
	            }
	        }
	    }

 
    3. .so文件是什么?
       .so是用在linux上的，.dll是用在Windows上的。

