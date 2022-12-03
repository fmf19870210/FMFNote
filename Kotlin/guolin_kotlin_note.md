



一:常量与变量

1.val（value的简写）用来声明一个不可变的变量，这种变量在初始赋值之后就再也不能重新赋值，对应Java中的final变量。 不能进行二次赋值(优先使用val)

var（variable的简写）用来声明一个可变的变量，这种变量在初始赋值之后仍然可以再被重新赋值，对应Java中的非final变量。可多次赋值变化


val name = "Amit Shekhar"
var name = "Amit Shekhar"



2. 显式地声明变量/常量类型
 val a:Int =10   常量只能进行一次赋值 
  
   var b:String ="hello"  变量可以进行多次赋值变化
            b="word"

3. kotlin 基本数据类型
   ![](http://epub.ituring.com.cn/api/storage/getbykey/screenshow?key=2003604633b220f59101)




 


  









二：函数（方法）基础总结
  1.1、函数的声明
      关键字为：fun          
      定义格式为：可见性修饰符 fun 函数名(参数名 ：类型,...) : 返回值{}
                 ()圆括号必须存在，即使是没有参数的情况下
                 {}大括号必须存在

		//无返回类型数据  (Unit类型：该类型即无返回值的情况，可以省略。)
		fun(函数的关键字)  methodName(函数名) (): Unit {
		}
		//有返回数据类型
		fun(函数的关键字)  methodName(函数名) ():数据类型{
		}
		//有返回数据类型
		fun(函数的关键字)  methodName(函数名) (参数名: 参数类型,参数名: 参数类型,...):数据类型{
		}
		
		  //无返回值  
 		  methodName("str")
         // 如果函数有返回值
         val x = methodName("str")  
        //无返回值
        Test(). methodName("str")
        // 如果函数有返回值
         val x = Test().methodName("str") 

  1.1.1  函数的返回值
     返回Unit类型。这个类型可以理解为函数无返回值。 
		fun unitFun() : Unit{
		    println("我是返回值为Unit的函数，Unit可省略")
		    return
		    
		    // return Unit 可省略
		    // 或者 return  可省略
		}





1.2函数的参数
 
  1.3默认参数 
     当一个函数有默认参数值时,我们调用该函数时可以不传参数, 
      默认参数，即使指一个函数中的参数具有默认值，这样在使用该函数的时候，可以省略一部分参数，可以减少函数的重载
        fun defArgs(numA : Int  = 1, numB : Float = 2f, numC : Boolean = false){
			    println("numA  =  $numA \t numB = $numB \t numC = $numC")
			}
 

      
    defArgs()  // 默认参数的函数使用  不用传参数 
    defArgs(1,10f,true) // 调用重新传参 重新赋值 


 1.4 命名参数
     即在使用函数时显示使用  参数名 = 参数值  这种方式传递参数
  
     fun callFun(str : String, 
        isTrue : Boolean = false,
        numA : Int = 2,
        numB: Float = 2.0f,
        numC : Int = 2){}

     
      
      callFun("str")  // 这样是可以的，因为后面的参数都具有默认值
      callFun("str",true,2,2.0,2)   // 这样阅读性很差，因为你除了看函数的定义外，你不知道这些参数的含义
      callFun("str",isTrue = true,numA = 3, numB = 3.0f, numC = 3)  //使用命名参数我们可以使代码更具有可读性：
      callFun("str",isTrue = true)  //当我们不需要所有的参数时：
 
1.5  可变数量参数
      fun 函数名(vararg 参数名 ： 类型，...) ：返回值{}
      fun varargFun(numA: Int, vararg str : String){
       // ...
       }


1.5  单表达式函数
      单表达式函数：即函数具备返回值的时候，可以省略花括号并且在=赋值符号之后指定代码体，而函数的返回值是有编辑器自动推断的。
     // 无参数的情况
     fun test1() = 2    // 自动推断为：返回类型为Int
   
     // 有参数的情况
     fun test2(num : Int) = num * 2      // 自动推断为：返回类型为Int
     fun test3(x : Float, y : Int = 2) = x * y  // 和默认参数一起使用，返回值为Float型


1.6  函数的引用
       fun foo(){}                    ::foo   
      
       class Foo{
       fun bar():Any{                 Foo::bar
   }
   }


1.7 函数运算符

    a in b      b.contains(a)
   
    a ！in b     ！ b.contains(a)


1.8 invoke方法
      a()             a.invoke()
     a(i)           a.invoke(i)
    a(i,j)           a.invoke(i,j) 



 





三:循环语句
 
1. 升区间:
      0 until 10  左闭右开区间    [0, 10)   0到10的左闭右开区间，它的数学表达方式是[0, 10)
      0..10       左闭右闭区间    [0, 10]   0到10的区间，并且两端都是闭区间


2.降序的区间
     10 downTo 1   左闭右闭区间  [10,1]    一个[10, 1]的降序区间
   



3. 循环
    java  循环
    for (int i = 1; i <= 10 ; i++) { }

	for (int i = 1; i < 10 ; i++) { }
	
	for (int i = 10; i >= 0 ; i--) { }
	
	for (int i = 1; i <= 10 ; i+=2) { }
	
	for (int i = 10; i >= 0 ; i-=2) { }
	
	for (String item : collection) { }
	
	for (Map.Entry<String, String> entry: map.entrySet()) { }

 



    
    Kotlin循环

		for (i in 1..10) { }
		
		for (i in 1 until 10) { }
		
		for (i in 10 downTo 0) { }
		
		for (i in 1..10 step 2) { }
		
		for (i in 10 downTo 0 step 2) { }
		
		for (item in collection) { }
		
		for ((key, value) in map) { }
		  
         list.forEach {
          if(it==2) return@forEach
            println(it)
        }





 三一:kotlin的case语句

      when和if语句一样，都是有返回值的表达式，可以在->后面直接返回一个值或者一个表达体函数。



           java 
           int score = // some score;
			String grade;
			switch (score) {
			    
			    case 6:
			        grade = "Good";
			        break;
			    case 5:
			    case 4:
			        grade = "OK";
			        break;
			    case 3:
			    case 2:
			    case 1:
			        grade = "Fail";
			        break;
			    default:
			        grade = "Fail";
			}      


         Kotlin的when语句判断(相当于Java的swich 判断语句)
              fun getScore(name: String) = when {
				    name.startsWith("Tom") -> 86
				    name == "Jim" -> 77
				    name == "Jack" -> 95
				    name == "Lily" -> 100
				    else -> 0
				}  
            





             var score = // some score
			 var grade = when (score) {
			    9, 10 -> "Excellent"
			    in 6..8 -> "Good"
			    4, 5 -> "OK"
			    in 1..3 -> "Fail"
			    else -> "Fail"
			 } 
  
               
             when (permission) {
                                Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                                    ToastUtil.showCommonToast(applicationContext, getString(R.string.sdcard_denied))
                                }
                                Manifest.permission.CAMERA -> {
                                    ToastUtil.showCommonToast(applicationContext, getString(R.string.camera_denied))
                                }
                                Manifest.permission.READ_PHONE_STATE -> {
                                    requestPhoneStateFailed()
                                    ToastUtil.showCommonToast(applicationContext, getString(R.string.read_phone_state_denied_not_login))
                                }
                                Manifest.permission.ACCESS_FINE_LOCATION -> {
                                    ToastUtil.showCommonToast(applicationContext, getString(R.string.location_denied))
                                }
                                Manifest.permission.RECORD_AUDIO -> {
                                    ToastUtil.showCommonToast(applicationContext, getString(R.string.audio_denied))
                                }
                                Manifest.permission.CALL_PHONE -> {
                                    ToastUtil.showCommonToast(applicationContext, getString(R.string.call_phone_denied))
                                }
                            }


                -----------
                fun main(args: Array<String>) {
					    useWhen(1)
					    useWhen(2)
					}
					
					fun useWhen(flag: Int) {
					    when (flag) {
					        1 -> println("when 1")
					        2 -> println("when 2")
					    }
					}

                 ---------
                   fun main(args: Array<String>) {
							    useWhen(Anima.BEAR)
							    useWhen(Anima.CAT)
							}
							
							enum class Anima {
							    DOG, CAT, BEAR
							}
							
							fun useWhen(anima: Anima) {
							    when (anima) {
							        Anima.BEAR -> println("when bear")
							        Anima.DOG -> println("when dog")
							        Anima.CAT -> println("when cat")
							    }
							}

----------------------------
          
       when(a){
         0->C=5
         1->C=100
         else->c=20           
          
           }

      
    val  C=when(a){
           0-> 5
           1-> 100
          else -> 20 
        }

     var a:Any=...
      val C=when{
          x is String ->x.length
         x==1 -> 100
         else -> 20        
}  


四:类  
    类的构成由构造函数和初始化代码块、属性（字段）、函数（方法）、内部类（嵌套类）、对象声明五部分组成   
    Kotlin中的类可详细的分为：密封类、内部类（嵌套类）、抽象类、枚举类、接口类、数据类

   四零:类的方法重载:Overloads 方法名称相同 参数不同 返回值不同
        kotlin的方法默认带有默认参数,
         如果要在java里调用Kotlin这个类带的默认参数的方法  ,需要在此方法加@JvmOverloads  

      @JvmOverloads 
      fun a(int:Int=0):Int{
      return int }       




四一:构造函数

 1. 在Kotlin中任何一个非抽象类默认都是不可以被继承的，相当于Java中给类声明了final关键字
   非抽象类加上open关键字之后,就允许被继承了。  


2.类的构造函数
   允许有一个主构造函数和多个二级构造函数（辅助构造函数）。其中主构造函数是类头的一部分。


2.1主构造函数 primary constructor
			每个类默认都会有一个不带参数的主构造函数
			也可以显式地给它指明参数。主构造函数的特点是没有函数体，直接定义在类名的后面即可
			class Student(val sno: String, val grade: Int) : Person() {
			//构造函数中不能出现其他的代码，只能包含初始化代码。包含在初始化代码块init中。
            //init结构体，初始化主构造函数中的参数
			init{
			 ....
			}
			
			}


          val student = Student("a123", 5)

   2.1.1  如果类主构造函数的所有参数都具有默认值，编译器将生成一个额外的无参数构造函数，它将使用默认值。 

            class Test constructor(num1: Int = 10 , num2: Int = 20){

			    init {
			        println("num1 = $num1\t num2 = $num2")
			    }
			
			    constructor(num1 : Int = 1, num2 : Int = 2, num3 : Int = 3) : this(num1 , num2){
			        println("num1 = $num1\t num2 = $num2 \t num3 = $num3")
			    }
			}



2.2次构造函数。 secondary constructor
 



3.类的修饰符
public(默认项)、
private、
protected(只对当前类和子类可见)
internal  只对同一模块中的类可见
 

![](http://epub.ituring.com.cn/api/storage/getbykey/screenshow?key=20038dfc5bdf78e4947e)







四二:接口类
接口的后面不用加上括号，因为它没有构造函数可以去调用。
使用override关键字来重写父类或者实现接口中的函数

interface Study {
    fun readBooks()
    fun doHomework()
}
 

class Student(name: String, age: Int) :   Study {
    override fun readBooks() {
        println(name + " is reading.")
    }

    override fun doHomework() {
        println(name + " is doing homework.")
    }
}









四三:数据类 

1.数据类

数据类通常占据着非常重要的角色，它们用于将服务器端或数据库中的数据映射到内存中，为编程逻辑提供数据模型的支持。
MVC、MVP、MVVM之类的架构模式 ,其中的M指的就是数据类
 在一个类前面声明了data关键字时，就表明这个类是一个数据类  
  注意:数据类data class 是个final 类 默认是个带参构造方法,不能作为javaBean 来用,
       要想用 需要使用插件 allOpen  noArg 



    data class Cellphone(val brand: String, val price: Double)

   
    data class TabInfoBean(val tabInfo: TabInfo) {
    data class TabInfo(val tabList: ArrayList<Tab>)

    data class Tab(val id: Long, val name: String, val apiUrl: String)
    }



    data class AuthorInfoBean(val tabInfo: TabInfo,
                          val pgcInfo: PgcInfo) : Serializable {

    data class TabInfo(val tabList: List<TabList>,
                       val defaultIdx: Int) : Serializable

    data class TabList(val id: Int,
                       val name: String,
                       val apiUrl: String) : Serializable


    data class PgcInfo(val dataType: String,
                       val id: Int,
                       val icon: String,
                       val name: String,
                       val brief: String,
                       val description: String,
                       val actionUrl: String,
                       val area: String,
                       val gender: String,
                       val registDate: Int,
                       val followCount: Int,
                       val follow: Follow,
                       val self: Boolean,
                       val cover: String,
                       val videoCount: Int,
                       val shareCount: Int,
                       val collectCount: Int,
                       val shield: Shield) : Serializable


    data class Follow(val itemType: String,
                      val itemId: Int,
                      val followed: Boolean) : Serializable

    data class Shield(val itemType: String,
                      val itemId: Int,
                      val shielded: Boolean) : Serializable
    }


四四:单例类

 四四一:只需要加个关键字 Object 即可。我们不需要私有化构造函数，也不需要提供getInstance()这样的静态方法，只需要把class关键字改成object关键字，一个单例类就创建完成了。

			    object Singleton {
			
			     //单例非静态方法
			    fun singletonTest(str:String) {
			       Log.d("TAG",str)
			    }
			
			    //单例静态方法(需要添加@JvmStatic注解)
			    @JvmStatic
			     fun getVersionCode(mContext: Context):Int{
			         //获取软件版本号，对应AndroidManifest.xml下android:versionCode
			      var versionCode=   mContext.packageManager.getPackageInfo(mContext.packageName,0).versionCode
			        return versionCode
			
			     }
			    
			
			   }


              Singleton.singletonTest("singletonTest is called") //singletonTest is called
               var versionCode=Singleton.getVersionCode(this)
              Log.d("TAG","版本号是$versionCode")  //版本号是1


 
 四四二: Kotlin的伴生对象 
      Kotlin的每一个类都有一个伴生对象,需要加关键字 companion object{}
            伴生对象companion object{}里面的成员方法和成员变量可以直接调用获取   等同于java的静态函数

             class AndroidUtils {
                     companion object {
				         //伴生对象的变量(需要添加@JvmField 注解)
                         @JvmField 
                         var tag:String="hello"

				        //伴生对象的非静态方法
				        fun doAction2() {
				            Log.d("TAG", "do action2")
				        }
				
				
				        //伴生对象的静态方法(需要添加@JvmStatic注解)
				        @JvmStatic
				        fun doAction3() {
				            Log.d("TAG", "do action3")
				        }
				
				
				        //调用伴生对象的静态方法(需要添加@JvmStatic注解)
				        @JvmStatic
				        fun getVersionCode(mContext: Context): Int {
				            var versionCode = mContext.packageManager.getPackageInfo(
				                mContext.packageName,
				                0
				            ).versionCode
				
				            return versionCode
				        }
				
				
				    }
				
				}   
				  


             AndroidUtils.doAction2()  //do action2
             AndroidUtils.doAction3()  //do action3
             var versionCode2= AndroidUtils.getVersionCode(this)

            Log.d("TAG","V"+versionCode2) //V1





 四四三: 抽象类
        声明一个抽象（类或函数）的关键字为：abstract 
        抽象类除了可以有其自己的属性、构造函数、方法等组成部分，还包含了抽象函数以及抽象属性。
        其抽象了类的子类必须全部重写带abstract修饰的属性和方法。 
         抽象成员只有定义，没有实现。都有abstract修饰符修饰。
         抽象类在顶层定义的时候只能使用public可见性修饰符修饰。
         抽象类中可以定义内部抽象类。
         只能继承一个抽象类。

            open class Base{
			    open fun init(){}
			}

          abstract class Lanauage: Base() {
            val TAG = this.javaClass.simpleName  // 自身的属性(非抽象)

               // 自身的函数 (非抽象)
			    fun test() : Unit{
			        // exp
			    }

            }

   
              abstract var name : String           // 抽象属性
              abstract fun init()                  // 抽象方法
              abstract override fun init()         // 覆写父类的抽象方法
              abstract class Name(){}              // 嵌套抽象类，可查看第二节中的嵌套类使用 


			  /**
			 * 抽象类Lanauage的实现类TestAbstarctA
			 */
             class TestAbstarctA : Lanauage(){

				    override var name: String
				        get() = "Kotlin"
				        set(value) {}
				
				    override fun init() {
				        println("我是$name")
				    }
				}
    

            /**
				 * 抽象类Lanauage的实现类TestAbstarctB
				 */
				class TestAbstarctB : Lanauage(){
				    override var name: String
				        get() = "Java"
				        set(value) {}
				
				    override fun init() {
				        println("我是$name")
				    }
				}





 四四四:Kotlin 的内部类（嵌套类） 
     四四四一:  内部类
             
                 1.默认是静态的内部类(不加inner),
                 调用格式为： 外部类.内部类().内部类方法/属性  Other.Nested().init() 
                  静态内部类不能访问外部类的属性和成员


        class Other{           // 外部类
				    val numOuther = 1
				
				    class Nested {      // 嵌套类
				        fun init(){
                           println(this@Outter.numOuther)  /错误的 静态内部类不能访问外部类的属性
				          println("执行了init方法")
				        }
				    }
					}
				
					fun main(args: Array<String>) {
					    Other.Nested().init()      // 调用格式为：外部类.嵌套类().嵌套类方法/属性
					}

 

                2.非静态内部类(有inner) 
               声明格式：inner class 类名(参数){}
               调用格式为：外部类().内部类().内部类方法/属性
               非静态inner内部类可以访问外部类的属性和成员


                  class Other{            // 外部类
					    val numOther = 1
					
					    inner class InnerClass{     // 非静态内部类
					        val name = "InnerClass"
					        fun init(){
                                println(this@Outter.numOther) //OK 正确的
					            println("我是内部类")
					        }
> 
					    }
					}

              Other().InnerClass().init()  // 调用格式为：外部类().内部类().内部类方法/属性
    



              
             3.this.@Outer   this@Inner 的用法


 四四四二:匿名内部类 
          
             Java的匿名内部类
				      interface Contents {
				    void absMethod();
				}
				public class Hello {
				
 				      //contents()方法返回的是一个匿名内部类的对象，这个匿名内部类实现了Contents接口
                      public Contents contents() {
				        return new Contents() {
				            private int i = 1;

                           public int value() {
                             return i;
                              }

                             
				            @Override
				            public void absMethod() {
				                System.out.println("method invoked...");
				            }
				        };
				    }
				
				    public static void main(String[] args) {
				
				        Hello hello = new Hello();
				        hello.contents().absMethod();    //打印method invoked...
				    }
				}
				 

           Kotlin 的匿名内部类
           语法形式：object [ : 接口1,接口2,类型1, 类型2]{}    //中括号中的可省略
          
       使用示例1：          
          -----------------------
            实现一个接口AA 
          interface AA {
            fun a()
              }

           fun main(args: Array<String>) {

             val aa = object : AA {
                override fun a() {
                  println("a invoked")
                  }
               }

     
              }

          -------------------
           实现多个接口AA   BB
           interface AA {
            fun a()
              }
            interface BB {
            fun b()
              }
          
            fun main(args: Array<String>) {
            val cc = object : AA, BB() {
            override fun a() {

            }

            override fun b() {

            }

            }

			    cc.a()
			    cc.b()
			
			}
 

	 -----------------------------------
       点击事件的匿名内部类回调
		view.setOnClickListener(object : OnClickListener {
		    override fun onClick(v: View) {
		        toast("Click")
		    }
		}

    //  一个lambda表达式通过参数的形式被定义在箭头的左边（普通圆括号包围），然后在箭头的右边返回结果值。    
     view.setOnClickListener({ view -> toast("Click")})
    //当我们定义了一个方法，我们必须使用大括号包围。如果左边的参数没有用到，我们甚至可以省略左边的参数。
     view.setOnClickListener({ toast("Click") })
 
    
    使用示例2： 
		  //定义接口        
		 interface OnClickListener{
					    fun onItemClick(str : String)
			  }  

			
      class Other{
                      
                   fun testListener(){
                    //赋值
			        listener.onItemClick("我是匿名内部类的测试方法")
			    }


                       //初始化
			        lateinit private var listener : OnClickListener    
			        fun setOnClickListener(listener: OnClickListener){
				        this.listener = listener
				    }
                }



         
           fun main(args: Array<String>){
				    // 测试匿名内部类
				    val other = Other()
				    other.setOnClickListener(object : OnClickListener{
				        override fun onItemClick(str: String) {
				            // todo
				            println(str)
				        }
				    })
				    other.testListener()
				}





五:枚举




 六:密封类







八: 数组
    
        1.创建一个包含指定元素的数组
          val arr1= arrayOf("1",2,3,"发现", "热门",2.5)

            for (item in arr1) {
            print("$item \t")  //属于元素  1 	2 	3 	发现 	热门 	2.5

               }

         arr1.reverse() //翻转集合元素
          for(i in  0..arr1.size-1){
              print(arr1[i])  //属于元素 2.5 热门 发现 3 2 1
        }     

           arr1.asList(); //数组转为集合
         

        2.创建一个数组 并指定长度, 元素为null的数组
        
        val arr2 = arrayOfNulls<String>(4)
        arr2[0]="HomeFragment"
        arr2[1]="BookShelfFragment"
        arr2[2]="MyFragment"

         for(i in 0..arr2.size-1){
             print(arr2[i]) // HomeFragment BookShelfFragment MyFragment null
         }

   
        
       3.数组的一些方法
          arr1.reverse() //翻转数组元素
          arr1.asList(); //数组转为集合 
          arr1.any({it>20} //数组的其中的某个元素  是否  >20 
          arr1.all({it>20}  //数组中的所有元素  是否都  >20




九:Kotlin  的 list map集合的集中遍历方式及集合的映射
       

      





九一: Kotlin的list集合的创建与使用

			1.不可变集合list：
             listOf() 创建不可变的list集合。不可变的集合指的就是该集合只能用于读取，我们无法对集合进行添加、修改或删除操作。 不支持add()、remove()、clear()
			 
             val  list = listOf(1,2,"3","4",5) //任意创建 支持类型
			 val list = listOf<String>("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelons")

             //for 高级循环  升序
	         for (s in list) {
	             println(s)  //Apple Banana Orange Pear Grape Watermelons
	             println(list.indexOf(s)) //返回s 对应的角标位置索引
	         }
	
	        // for 普通循环 升序
	       for(i in 0..list.size-1){
	           print(list.get(i)+" ")  //Apple Banana Orange Pear Grape Watermelons
	           //print(list[i]+" ")
	       }


            // for 普通循环 升序
	        for(i in 0 until list.size){
	          print(list.get(i)+" ")  //Apple Banana Orange Pear Grape Watermelons
	       }

            
            // for 普通循环  降序

	         for(i in list.size-1 downTo  0){
	             print(list.get(i)+" ")  //Watermelons Grape Pear Orange Banana Apple
	         }





    2.可变List    
     mutableListOf()函数创建一个可变的集合, 可对集合进行添加、修改或删除操作。支持add()、remove()、clear()

          val list1 = mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape")
         val  list1:MutableList<String> = ArrayList()

       val listt = ArrayList<String>()
        listt.add("Apple")
        listt.add("Banana")
        listt.add("Orange")
        listt.add("Pear")
        listt.add("Grape")

       list.forEach {
          if(it==2) return@forEach
            println(it)
         }



九二: Set集合

1.setOf() 创建不可变的set集合
val set = setOf("Apple", "Banana", "Orange", "Pear", "Grape")

2.mutableSetOf()  创建可变的set集合




九三:map 集合
       1.不可变Map  固定大小  不可 增删
        val  map1=mapOf("key0" to 0,"key1" to 1,"key2" to 2)     
         val map0=   mapOf<Int,String>()

        2.可变Map  固定变化   可 增删
         

  
     val map = HashMap<Int ,String >()
         map.put(0,"apple")
         map[1]="apple"
         map[2]="Banana"

        for (mutableEntry in map) {
           print(mutableEntry) //0=apple  1=apple  2=Banana
            print( mutableEntry.key) //0 1 2
            print( mutableEntry.value) //apple  apple  Banana
        }


       val  map1= hashMapOf<Int,String>()
        map1.put(0,"apple")
        map1.put(1,"apple")
        map1.put(2,"Banana")


        for(key in map1.keys){
            print(key)  //0 1 2
        }

        for (value in map1.values) {
            print(value)  //apple  apple  Banana

        }


      mutavleMapOf.forEach {
             println("${it.key}+${it.value}") 
         }



 九四 :  集合的映射


   
   1.filter即过滤，保留满足条件的元素。它会遍历集合并选出应用给定lambda后返回未true的元素。使用它可以移除不满足条件的元素（数据源并不会改变）

   val list2 = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
   val  newList = list2.filter { it.length<=5 }
                       .map { it.toUpperCase() }

    for (s in newList) {
            println("list "+s)
            println( "list "+newList.indexOf(s))
        }




            val list = listOf(1,2,3,4,5,6)
			    //过滤奇数，保留偶数
			  println(list.filter { it % 2==0 }) //[2, 4, 6]
			  
			     //数据源
			    val people = listOf(Person("jack", 29),
			            Person("nick", 23),
			            Person("jone", 26))
			
			    //过滤掉年龄小于25的，保留年龄大于25的
			    println(people.filter { it.age>25 })
			    //[Person(name=jack, age=29), Person(name=jone, age=26)]



   2.map对集合每一个元素应用给定的函数并把结果收集到一个新集合，即元素变换。
       把旧的集合里的每个元素 映射成新的元素 然后把这些新的元素组成新的集合
         元素的一一映射
 
              val list = listOf(1,2,3,4,5,6)
			    //map把元素换为它的平方集合
			    println(list.map { it*it }) //[1, 4, 9, 16, 25, 36]
			
			
			    //使用map将元素为Person类型转换为String
			       println(people.map { it.name }) //[jack, nick, jone]
			    //可以使用成员引用简写
			    println(people.map(Person::name))   //[jack, nick, jone]


               val list5 = listOf(100, 200, 300, 400)
               val newList =list5.map { it*2+3 }
               val   newList2=newList.map(Int::toDouble)
               newList2.forEach(::println)




    3. filter  map 合用  
                 val list5 = listOf(1, 2, 3, 4,5,6,7,8,9,10)
             list5.filter {
               println("被过滤的元素${it}")
                 it%2==0
                 }
                .map {
                   println("组合新的元素 $it")
                  it*2+1

                    }
               .forEach {
                 println("遍历新的集合元素 $it")
                }

       

   4.  filterKeys（过滤map的键） filterValues(过滤map的值)
        mapKeys(变换map的键)     mapValues(变换对应的map值)     

           val numbers = mapOf(0 to "ZERO",1 to "ONE")
		    //移除map里小于1的key
		    println(numbers.filterKeys { it<1 })//{0=ZERO}
		    //把key变换为value
		    println(numbers.mapKeys {it.value})//{ZERO=ZERO, ONE=ONE}
		    //过滤map的value
		    println(numbers.filterValues { it.startsWith("Z") })//{0=ZERO}
		    //转换map的value为小写
		    println(numbers.mapValues { it.value.toLowerCase() })//{0=zero, 1=one}


   5. flatMap 变换  
          把一个元素1  映射成一个对应的集合1,
          把一个元素2  映射成一个对应的集合2  
          然后在把这些集合1,2拼接起来组成新的集合
  




  九五:  集合的 any和all函数

 1.any函数用于判断集合中是否至少存在一个元素满足指定条件

		 //any函数就表示集合中是否存在5个字母以内的单词元素
		  val anyResult = list.any { it.length <= 5 }


    // any 判断数组arr 里的元素值 其中有一个元素值大于20, 如果是返回true 否则返回false 

     println(arr1.any({it>20}))  //true
     println(arr1.any({it.equals("发现")}))  //true




2.all函数用于判断集合中是否所有元素都满足指定条件

		//all函数就表示集合中是否所有单词元素都在5个字母以内
		val allResult = list.all { it.length <= 5 }
		
		//判断数组arr里的所有元素值 是否大于20  , 如果是返回true 否则返回false 
		  println(arr1.all({it>20}))  //fasle






十:集合的函数式API

十(一):Lambda编程

 java 版本的Lambda表达式的使用
      1.举例： (o1,o2) -> Integer.compare(o1,o2);
	 *     2.格式：
	 *      -> :lambda操作符 或 箭头操作符
	 *      ->左边：lambda形参列表 （其实就是接口中的抽象方法的形参列表）
	 *      ->右边：lambda体 （其实就是重写接口的的抽象方法的方法体）
	 * 3. Lambda表达式的使用：（分为6种情况介绍）
            (1)  语法格式一：(接口的抽象方法首先是无参无返回值)无参，无返回值  
		原生: Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
 
		lambda:	Runnable r2 = () -> {System.out.println("我爱北京故宫");};   

 		
		(2)语法格式二：(接口的抽象方法首先是有一个参数无返回值)Lambda 需要一个参数，但是没有返回值。 
		  原生: Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
		 
		 lambda: Consumer<String> con1 = (String s) -> { System.out.println(s); };

		(3)语法格式三：数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
          lambda1: Consumer<String> con1 = (String s) -> { System.out.println(s); };
		  lambda2: Consumer<String> con2 = (s) -> {System.out.println(s);};

	     (4) 语法格式四：Lambda 若只需要一个参数时，参数的小括号可以省略
		  lambda1:Consumer<String> con1 = (s) -> { System.out.println(s);};
		  lambda2:Consumer<String> con2 = s -> {System.out.println(s);};
  			
			 (5)语法格式五：Lambda 需要两个或以上的参数，多条执行语句，并且可以有返回值  
				 原生: Comparator<Integer> com1 = new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					System.out.println(o1);
					System.out.println(o2);
					return o1.compareTo(o2);
				}
				};
				 com1.compare(12,21)
       
	    lambda:  Comparator<Integer> com2 = (o1,o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };

       com2.compare(12,6)						

     (6)语法格式六：当 Lambda 体只有一条语句时，return 与大括号若有，都可以省略
	    原生: Comparator<Integer> com1 = (o1,o2) -> {
            return o1.compareTo(o2);
        };
           com1.compare(12,6)

        lambda:  Comparator<Integer> com2 = (o1,o2) -> o1.compareTo(o2);
                  com2.compare(12,21);

  lambda总结：
      ->左边：lambda形参列表的参数类型可以省略(类型推断)；如果lambda形参列表只有一个参数，其一对()也可以省略
              Consumer<String> con2 = s -> {System.out.println(s);};   

      ->右边：lambda体应该使用一对{}包裹；如果lambda体只有一条执行语句（可能是return语句），省略这一对{}和return关键字
              Comparator<Integer> com2 = (o1,o2) -> o1.compareTo(o2);

  4.Lambda表达式的本质：就是  函数式接口(此接口有且只能有一个方法)的实例对象
 5. 如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口。我们可以在一个接口上使用 
     @FunctionalInterface 注解，
 *   这样做可以检查它是否是一个函数式接口。
         @FunctionalInterface   
         public interface  Runnable{
			 public abstract ovid run();
		 }
   
      5.1 所以 我们可以使用lambda 表达式来创建  函数式接口(只有一个方法)的 实例对象
  

  6. java的函数(方法)引用方式的使用
        6.1.使用情境：当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！
         6.2.方法引用，本质上就是Lambda表达式，无非换了一种格式,而Lambda表达式作为函数式接口的实例。
         所以 方法引用，也是函数式接口的实例。
          6.3. 使用格式：  类(或对象) :: 方法名
          6.4. 具体分为如下的三种情况：
                情况1     对象 :: 非静态方法
			      lambda表达式形式:   Consumer<String> con1 = str -> System.out.println(str);
			           PrintStream ps = System.out;
				  方法引用格式:	  Consumer<String> con2 = ps::println;
 				 
                  情况2     类 :: 静态方法
			        lambda表达式形式:   Comparator<Integer> com1 = (t1,t2) -> Integer.compare(t1,t2); 
			         方法引用格式:Comparator<Integer> com2 = Integer::compare;
                  情况3     类 :: 非静态方法
			        lambda表达式形式:   Comparator<String> com1 = (s1,s2) -> s1.compareTo(s2);
			        方法引用格式:    Comparator<String> com2 = String :: compareTo;

                   	
			6.5.方法引用使用的要求：要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的
			 形参列表和返回值类型相同！（针对于情况1和情况2）


 7.kotlin 的函数引用方式:
  



初识lambda
		  
       Lambda表达式的本质其实是匿名函数 
       lambda表达式存在于{}中
            
		lambda无需显式指定参数类型，这是因为jac会在编译时候会根据上下文进行类型推导（注：有事为了提升代码可读性，根据习惯仍需指定类型，编译器不一定能根据上下文推导出类型）
         参数及参数类型(可省略)在->左边
         函数体在->右边
         lambda表达式返回值总是返回函数体内部最后一行表达式的值

Lambda表达式的语法结构:
        参数(参数类型可以省略) -> 函数体
          {[参数列表] -> [函数体]}

lambda表达式的类型:
      ()->Unit   无参 ,返回值为Unit      {println()}
      (Int)->Int  传入整型,返回一个整型
      (String,(String)->String) ->Boolean  参数:传入一个字符串,传入一个lambda表达式，  返回值:Boolean
       

       
 lambda 左边无参数:
          val/var 变量名 = { 函数体/操作的代码 }
           
           val  runable={  print("hello") }  //kotlin 写法
         或  Runnable mRunnable = () -> print("hello");  //java写法 没有参数  用（）表示，返回类型为void  
            


         //kotlin 写法
		 val mRunnable3 = {
            print("hello")
            print("world")
        }
         //java写法
         Runnable mRunnable1 = () -> {
	            System.out.print("hello");
	            System.out.print("world");
	        };



 lambda 左边带参数:
      val/var 变量名 : (参数的类型，参数类型，...) -> 返回值类型 = {参数1，参数2，... -> 操作参数的代码(可以时若干代码) }
        
       可等价于(表达式的返回值类型会根据操作的代码自推导出来)
        val/var 变量名 = { 参数1 ： 类型，参数2 : 类型, ... -> 操作参数的代码(可以时若干代码)  }
       
    案例1：
     val sum : (Int , Int) -> Int = {x , y ->println("this is $x and $y and sum is")  x+y }
      // 等价于
         val sum =  { x: Int, y: Int ->
            println("this is $x and $y and sum is")
            x+y
            } 
          println(sum(1,2)) //如何调用

          val getAge = { p: Person -> p.age }
          people.maxBy(getAge) 
          println(people.maxBy(getAge))



          //如果有且只有一个参数，参数括号可省略
          // view是参数和上面的内部类是同一个参数.  -> 将参数view和lambda主体分开
    
			btnTest.setOnClickListener(object : OnClickListener {
			    override fun onClick(v: View) {
			        toast("Click")
			    }
			}
 			
           
            //  一个lambda表达式通过参数的形式被定义在箭头的左边（普通圆括号包围），然后在箭头的右边返回结果值。    
		      view.setOnClickListener({ view -> toast("Click")})
		      //果 Lambda 是一个函数的唯一参数，那么调用这个函数时可以省略圆括号{}  
		    view.setOnClickListener(view -> toast("Click") );
		    // 如果 Lambda 所表示的匿名函数只有一个参数，那么可以省略它的声明以及->符号
		     view.setOnClickListener( toast("Click") )



       //表示包含多个参数，
       BinaryOperator<Long> add = (Long x, Long y) -> x + y;



 lambda的调用有两种方式
 一种是通过()来进行调用，另一种是通过invoke()函数进行调用
      val lambda = { println("test") }
      lambda()
      lambda.invoke()        



lambda 函数变量
一个变量可以等于一个lambda表达式
一个变量等于一个普通函数，但是在函数名前需要加上(::)来获取函数引用。

    val sumLambda = {a: Int, b: Int -> a + b}
       
 
 lambda表达式作为函数中的参数
    公式:  fun test(a : Int, 参数名 : (参数1 ： 类型，参数2 : 类型, ... ) -> 表达式返回类型){
        ...
    }


   --------------------------普通方法
		 // 源代码
		    fun test(a : Int , b : Int) : Int{
		        return a + b
		    }


            fun sum(num1 : Int , num2 : Int) : Int{
              return num1 + num2
              }

     
   // 普通调用 把 函数方法sum(3,5)  作为 函数fun test(a : Int , b : Int)的第二参数
    test(10,sum(3,5)) // 结果为：18

 
   ------------------------------lambda方法(重点 难点 )

         案例1:
			  // 当Lambda表达式作为其一个参数时，只为其表达式提供了参数类型与返回类型
			// invoke()函数：表示为通过函数变量调用自身，因为上面例子中的变量b是一个匿名函数
			fun test(a:Int,b:(num1:Int,num2:Int) -> Int):Int{
			     return a+b.invoke(3,5)
			}

 

		   // 调用
		  test(10,{num1: Int, num2: Int -> num1+num2 }) // 结果为：18

   
         (1)如果lambda表达式是函数调用的最后一个实参，它可以放到括号外面。
                //lambda是函数调用的最后一个实参，可以放到（）外
                people.maxBy( ){ P:Person ->P.age}

          (2) 当lambda表达式是函数唯一实参时，还可以去掉（）
                people.maxBy{ P:Person ->P.age}


           (3) lambda也能作为命名实参传递
                   //数据源
				    val people = listOf(Person("jack", 29),
				            Person("nick", 23),
				            Person("jone", 26))
				
				    //把lambda作为命名实参
				    val names =people.joinToString(separator = " ",transform = {p:Person -> p.name})
				
				    println(names)//jack nick jone


lambda的下划线（_）

 在使用Lambda表达式的时候，可以用下划线(_)表示未使用的参数，表示不处理这个参数。
 
 案例1:

     val mapp = mapOf("key1" to "value1","key2" to "value2","key3" to "value3")


           //遍历需要key  value 参数
        mapp.forEach { key, value  -> Log.d("TAG","$key \t $value") }


        // 不需要key的时候
        mapp.forEach { _,value -> Log.d("TAG","$value")}

        // 不需要value的时候
        mapp.forEach { key,_ -> Log.d("TAG","$key")}

        // 不需要key value 的时候
        mapp.forEach { _, _-> Log.d("TAG","呵呵") }






lambda的匿名函数
         匿名函数的特点是可以明确指定其返回值类型。
         它和常规函数的定义几乎相似。他们的区别在于，匿名函数没有函数名。

    val 函数名 = fun(参数1:类型1, 参数2:类型2, ...): 返回值类型 { 函数体 }


    val sum = fun(a: Int, b: Int): Int {
    return a + b
     }
    // 等价于函数
			fun sum(a: Int, b: Int): Int {
			    return a + b
			}
    



常规函数：
     fun test(x : Int , y : Int) : Int{  return x + y  }
    或
       fun test(x : Int , y : Int) : Int = x + y

 匿名函数：
       fun(x : Int , y : Int) : Int{  return x + y}
        或
        fun(x : Int , y : Int) : Int = x + y

案例1:
     val test1 = fun(x : Int , y : Int) = x + y  // 当返回值可以自动推断出来的时候，可以省略，和函数一样 
     val test2 = fun(x : Int , y : Int) : Int = x + y
     val test3 = fun(x : Int , y : Int) : Int{ return x + y }





在Android开发中为RecyclerView的适配器编写一个Item点击事件的接口回调  (Kt原始版(接近java) 简单易接受)
   class TestAdapter(val context : Context , val data: MutableList<String>)
    : RecyclerView.Adapter<TestAdapter.TestViewHolder>(){   

    //创建用于回调接口
        interface OnItemClickListener {
          // **Any**：所有没有显式声明基类的class，它的默认父类就是Any；相当于Java中的Object  
          //**object **：object的作用是调用内部匿名类
          fun onItemClick(obj: Any?, position: Int)
        }
 
      //初始化 
       private var mItemClickListener: OnItemClickListener? = null
     //方法调用
    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.mItemClickListener = itemClickListener
    }
 

    override fun onBindViewHolder(holder: TestViewHolder?, position: Int) {

             // holde的itemView点击事件,内部添加一个点击的事件的回调接口 进行数据的传递回调  
			 holder?.itemView?.setOnClickListener {
			    //赋值
              mItemClickListener.onItemClick(mData[position], position)
			}
   }

  
     override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TestViewHolder {
        return TestViewHolder(View.inflate(context,layoutId,parent))
      }



			override fun getItemCount(): Int {
			        return data.size
			    }

      inner class TestViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
 }

 
//测试回调调用
    mineFragmentRvAdapter?.setOnItemClickListener(object : OnItemClickListener {
      override fun onItemClick(obj: Any?, position: Int) {
        showToast("$position ==== $obj")
      }
    });




在Android开发中为RecyclerView的适配器编写一个Item点击事件的接口回调  (Kt写法缩略版 重点 难点)
//使用在RV的adapter中的回调里面

  class TestAdapter(val context : Context , val data: MutableList<String>)
    : RecyclerView.Adapter<TestAdapter.TestViewHolder>(){
   //初始化创建匿名接口  回调
   private var mListener : ((a: Int, b: String) -> Unit)? = null

   //**Unit**：无任何返回的类型  相当于Java的void
    fun setOnItemClickListener(mListener : (position : Int, item : String) -> Unit){
       this.mListener = mListener    

      }

 
     override fun onBindViewHolder(holder: TestViewHolder?, position: Int) {

             // holde的itemView点击事件,内部添加一个点击的事件的回调接口 进行数据的传递回调  
			 holder?.itemView?.setOnClickListener {
			 // //调匿名回调接口赋值position 和  data[position] 
           // **invoke**：mListener?.invoke(data, position.toString()) 相当于mListener.data, position.toString()
			mListener?.invoke(position, data[position])
			}
 }


      override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TestViewHolder {
        return TestViewHolder(View.inflate(context,layoutId,parent))
      }



			override fun getItemCount(): Int {
			        return data.size
			    }

      inner class TestViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
 
  }





 //  测试 调用 kt的回调接口函数 
TestAdapter(this,dataList).setOnItemClickListener { position, item ->
        showToast("$position ==== $item")
    }




 
lambda表达式与接口回调

Java思想的kotlin写法
          interface ICallback {
			    fun onSuccess(msg: String)
			
			    fun onFail(msg: String)
			}

		
		  class NormalCallback {

				    var mCallback: ICallback? = null
				
				    fun setCallback(callback: ICallback) {
				        mCallback = callback
				    }
				
				    fun init() {
				        mCallback?.onSuccess("success message")
				    }
				}
 

 
     //调用
		fun main(args: Array<String>) {
		    val normalCallback = NormalCallback()
		    normalCallback.setCallback(object : ICallback {
            override fun onSuccess(msg: String) {
                println("常见接口回调 onSuccess : $msg")
            }

            override fun onFail(msg: String) {
                println("常见接口回调  onFail : $msg")
            }
        })

        normalCallback.init()
    }
 


使用lambda表达式替代匿名内部类实现。
        
      形式1:
         接口函数只有一个参数
       
		  class TestCallback {

			    var mySuccessCallback: (String) -> Unit? = {}
			    var myFailCallback: (String) -> Unit? = {}
			
			    fun setCallback(successCallback: (String) -> Unit, failCallback: (String) -> Unit) {
			        mySuccessCallback = successCallback
			        myFailCallback = failCallback
			    }
			
			    fun init() {
			        mySuccessCallback("success message")
			        myFailCallback("fail message")
			    }
			}


                   
              //测试
				fun main(args: Array<String>) {
				    val testCallback = TestCallback()
				    testCallback.setCallback({ println("success $it") }, { println("fail $it") })
				    testCallback.init()
				}


       形式2：
        接口函数 有多个参数 
          
          class TestCallbackC {
                 //  var mSuccessCallback:((String, Boolean) -> Unit)? = null
                 //private var mSuccessCallback: ((a: String, b: Boolean) -> Unit)? = null
                //    private var mSuccessCallback: ((a: String, b: Boolean) -> Unit)? = { _, _ -> }
               //    private var mSuccessCallback: (a: String, b: Boolean) -> Unit = { _, _ -> }
                   var mSuccessCallback: (String, Boolean) -> Unit? = { _, _ -> }
                  var mFailCallback: (String) -> Unit? = {}


               fun setCallback(successCallback: (String, Boolean) -> Unit,
                    failCallback: (String) -> Unit) {
			        this.mSuccessCallback = successCallback
			        this.mFailCallback = failCallback
			    }
 
       }

      
           //测试
          private fun testC() {
        val testCallbackC = TestCallbackC()
        testCallbackC.setCallback({ a, b ->
            println("c success : $a - $b")
        }, {
            println("c failure : $it")
        })
        testCallbackC.init()
    }

  






成员引用
        
            1.kotlin和java8一样，如果把函数转换成一个值，就可以直接转换它使用::运算符转换。
                //成员引用
				    val getAge = Person::age
				 //等价
				   val getAge = {person:Person ->person.age}
    
             2.成员引用还可以引用顶层函数
                 //顶层函数
					fun salute()= println("Salute")
					//引用顶层函数，::salute当作实参传递给了lambda
					run(::salute)
					 println(run(::salute))//Salute
 
               3.如果lambda要委托一个或者多个参数的函数，提供成员引用代替非常方便。
                 
                   //将lambda委托给sendEmail函数
					    val action={person:Person,msg:String ->sendEmail(person,msg)}
					 //成员引用代替
					    val nextAction = ::sendEmail

                      
 kotlin的匿名内部类 结合lambda 的使用
                   //每次调用都会常见匿名累不累对象
                   postponeComputation(1000, object :Runnable{
			            override fun run() {
			                println(42)
			            }
			
			        })     
               
       
             // 简化   全局变量，   只会创建一个Runnable的实例
             val  runable= Runnable { println(42) }
             postponeComputation(1000,runable)
            

lambda  针对多个不同的按钮的点击事件
			         val listener = View.OnClickListener { view ->
			         val text = when (view.id){
			                R.id.btn -> "first button"
			                R.id.btn1 ->"Second button"
			                else ->"UnClick"
			            }
			         println(text)
			        }
			        btn.setOnClickListener(listener)
			        btn1.setOnClickListener(listener)


 

        其他:
          run { println(42) }//42
          people.maxBy({ P:Person ->P.age} ) 




 **lambda的高阶函数**

  什么是高阶函数？

  高阶函数就是：将一个函数(一般是lambda表达式)   用作  另一个函数的参数
  高阶函数就是: 一个函数的返回值类型  是   另一个函数
  高阶函数就是: 就是这个作为作为参数的函数和 返回返回值类型函数 这2个函数是有类型的,就是函数类型, 
              我们把2个函数的对应的函数类型添加到一个新函数的参数声明或者返回值声明当中,就是高阶函数了

  什么是 lambda的函数类型？  
    即函数是有类型的,一个函数的类型用它的所有的参数的类型和它的函数值的类型共同表示。
     格式:(参数1类型, 参数2类型, ..., 参数n类型)->返回值类型。 
	 如: 函数类型 (String, Int) -> Int, 
     -> 左边:左边用来声明该函数接收什么参数,如果不接受任何参数用用空()表示就可以了
          第一个参数是 String ，第二个参数 Int ，函数返回值类型也是 Int 类型。
	 -> 右边:右边声明该函数返回值是什么类型， 如果没有返回值就用Unit 表示

		  fun num1AndnUM2(num1:Int,num2:Int, operation:(Int, Int) -> Int):Int{
              val result = operation(num1,num2) 
                   return result 
              } 
			 
              num1AndnUM2()就是高阶函数，它的第三个参数 就是一个函数类型参数: operation:(Int, Int) -> Int  
		

         定义与函数类型参数: operation:(Int, Int) -> Int相对应的函数

          inline fun num1Andnum2(num1:Int,num2:Int, operation:(Int, Int) -> Int):Int{
			    val result1 = operation(num1,num2)
			    return result1
			}

		 inline  fun num1Andnum3(num1:Int,num2:Int, operation:(Int, Int) -> Int):Int{
		        val result2=   operation.invoke(num1,num2)
		        return result2
		    }


    inline fun num1Andnum4(num1:Int,num2:Int, operation:(Int, Int) -> Int):Int=operation(num1,num2)
    inline fun num1Andnum5(num1:Int,num2:Int, operation:(Int, Int) -> Int):Int=operation.invoke(num1,num2)  







  ** 函数类型实例调用**
       invoke(……) 或   f(x)



	 
 ** inline内联函数**
               
 1. 为什么要使用内联函数呢？
   其实还是跟高阶函数有关.因为我们给一个高阶函数 传递函数类型 lamdba表达式 作为参数时,是有一定问题的,会造成额外的内存和性能开销, 因为lambda表达式是一个匿名内部类实例对象,每次调用都会创建一个新的匿名内部类实例对象,造成额外的内存和性能开销
   为了解决这个问题,我们就要使用内联函数将Lambda表达式运行时造成的开销完全消除
  
   **inline内联函数:就是在高阶函数前 加个关键字 inline 即可**

2.inline内联函数的工作原理:

    就是 在kotlin 编译时,将 内联函数  inline 高阶函数的  里的lambda表达式中的代码 替换到 函数类型参数 调用的地方    

   
    inline  fun inlineTest(block:()->Unit,x:Int,y:Int, noinline  block2:(x:Int,y:Int)->Int):Unit{
    block.invoke()
    block2.invoke(x,y)
    }

	inline fun num1Andnum2(num1:Int,num2:Int, operation:(Int, Int) -> Int):Int{
	    val result1 = operation(num1,num2)
	    return result1
	}

	 inline  fun num1Andnum3(num1:Int,num2:Int, operation:(Int, Int) -> Int):Int{
	        val result2=   operation.invoke(num1,num2)
	        return result2
	    }


    inline fun num1Andnum4(num1:Int,num2:Int, operation:(Int, Int) -> Int):Int=operation(num1,num2)
    inline fun num1Andnum5(num1:Int,num2:Int, operation:(Int, Int) -> Int):Int=operation.invoke(num1,num2)




3. **noinline  crossinline 关键字**

  如果内联高阶函数有多个函数类型参数, 那么编译器就会将所有的Lambda表达式 函数类型全部进行内联,
   但实际情况是我可能不想让某个Lambda表达式函数类型 进行内联,那么你就在此Lambda表达式函数类型前加上 noinline关键字,就不会对这个Lambda表达式函数类型进行内联了
		  inline  fun inlineTest(block:()->Unit,x:Int,y:Int, noinline  block2:(x:Int,y:Int)->Int):Unit{
		    block.invoke()
		    block2.invoke(x,y)
		}







十一: 　Java函数式API的使用
1.创建子线程 实现Runable方法
Thread(object : Runnable {
    override fun run() {
        println("Thread is running")
    }
}).start()


Thread(Runnable {
    println("Thread is running") 
}).start()


Thread({
    println("Thread is running") 
}).start()



Thread {
    println("Thread is running")
}.start()




2.按钮的点击事件

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
   println("button is clicked")
    }
});


button.setOnClickListener {
 println("button is clicked")
}
 




十二:Kotlin可空性
  https://www.jianshu.com/p/9a788202efa9
https://github.com/yangchong211/YCBlogs/blob/master/Kotlin/13.Kotlin%E5%8F%AF%E7%A9%BA%E6%80%A7.md

		
         1.Kotlin 如何解决java的空指针异常问题:
          Kotlin将空指针异常的检查提前到了编译时期，如果我们的程序存在空指针异常的风险，那么在编译的时候会直接报错，修正之后才能成功运行，这样就可以保证程序在运行时期不会出现空指针异常了。
		   Kotlin默认所有的参数和变量都不可为空,否则就会报错不予编译通过
		   
		  我们的业务逻辑就是需要某个参数或者变量为空该怎么办呢？不用担心，Kotlin提供了另外一套可为空的类型系统，只不过在使用可为空的类型系统时，我们需要在编译时期就将所有潜在的空指针异常都处理掉，否则代码将无法编译通过。



         1.1 类型?    可以加在任何类型的后面来表示这个类型的变量可以存储 null 

             Int  不可为空的整型(默认)           Int?  可为空的整型
		      String 不可为空的字符串(默认)       String?  表示可为空的字符串。
		

           var b: String? = "abc" // 变量b 可为null
               b = null

           var b: String = "abc" 
               b = null  // 报错 变量b 不能为null
           



		
		//传递一个对象study
		doStudy(study)
		// 参数 study: Study 不为空
		fun doStudy(study: Study) {
		 study.readBooks()
		 study.doHomework()																																														}																																																																																																																	
        
          //传递一个空对象null																				             	doStudy1 										  fun doStudy1(study: Study?) {
               study?.readBooks()
               study?.doHomework()
             }
          
             或																								fun doStudy(study: Study?) {
		     if (study != null) {
			 study.readBooks()
			 study.doHomework()
			 }
			 }																																																																																 	
		
      1.2   ?. 操作符 :
           允许把一次 null 检查和一次方法调用合并为一个操作，如果变量值非空，则方法或属性会被调用，否则直接返回 null 
		   ?.  当对象不为空时正常调用相应的方法,当对象为空时则什么都不做 (等同于if(study!=null){...})
		   kotlin  非空判断 操作符 ?.
		
		  
		// 传个空对象 null 
		doStudy(null)
		
		//Study? 接收参数可空null  
		fun doStudy(study: Study?){
		  //?.  当对象不为空时正常调用相应的方法,当对象为空时则什么都不做 (等同于if(study!=null){...})
		  study?.readBooks()
		  study?.doHomework()
		 }
		
		
         kotlin 还支持链式调用, 只要链式上有一个值为空, 那么将不会往下执行      
         
          bob?.department?.head?.name

 
 
 ![](https://github.com/fmf19870210/FMFNote/blob/master/Kotlin/kotlinimage/%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20210104162056.png)


		
		1.3  ?:操作符 
		用于替代 ?. 直接返回默认值 null 的情况，Elvis 运算符接收两个运算数，如果第一个运算数不为 null ，运算结果就是其运算结果值，如果第一个运算数为 null ，运算结果就是第二个运算数
		?:操作符 ,左右两边都接收一个表达式。如果左边表达式的结果不为空就返回左边表达式的结果，否则就返回右边表达式的结果
		 val c = a ?: b   // 等于  val c = if (a ! = null) {a} else {b}
		
		//由于文本是可能为空的，因此我们需要先进行一次非判空判断操作?. ，如果text文本不为空就返回它的长度text?.length，如果文本为空(null)  就返回0。
		fun getTextLength(text: String?) = text?.length   ?:  0
		
					  (  等于  fun getTextLength(text: String?): Int {
						    if (text != null) {
						        return text.length
						    }
						    return 0
						}      )
		
		
		    
           
           var bannerBean : BannerBean? = null  // bannerBean 可为null
           var a = bannerBean?.title ?: "null"  //?. 是对bannerBean 进行非空判断, 如果有值 输出左边的值, 否则输出右边的"null"   
		
		
          // 如果name 不为空 输出name 值   如果name 为null, 输出右边结果 "yc"
           fun check(name: String?) {
               println(name ?: "yc")
             }


		
		1.4 let函数:
		    let将原始调用对象作为参数传递到Lambda表达式中
            let 就是为了避免多次频繁使用?.  麻烦
			let 函数可用于在表达式不为 null 时才执行指定代码块 

             	
		        obj?.let { obj2(obj2就是obj对象) ->
				 // 编写具体的业务逻辑
				}
		
		
		
		
		
		   
		    fun doStudy2(study: Study?) {
		       /*?.操作符表示对象为空时什么都不做，对象不为空时就调用let函数，而let函数会将study对象本身作为参数传递到Lambda表达式中，此时的study        对象肯定不为空了，我们就能放心地调用它的任意方法了。
		        当Lambda表达式的参数列表中只有一个参数时，可以不用声明参数名，直接使用it关键字来代替即可
		         */
		        study?.let {
		            it.readBooks()
		            it.doHomework()
		        }
		    }



         1.5   !!.   非空断言
                非空断言用于把任何值转换为非空类型,非空判断(=java是 *!=null)
                可以跳过限制检查通过编译，此时如果变量为空会抛出空指针异常
                非空断言用于把任何值转换为非空类型，如果对 null 值做非空断言，则会抛出异常 
                  name!!.length
                  chatFragment!!.arguments 
                  a!!.foo() 
                    [等于 if(a!=null){
					 a.foo();
					}else{
					 throw new KotlinNullPointException();
					}]

 
                   --------
                   fun main(args: Array<String>) {
					    var name: String? = "yc"
					    check(name) //7
					
					    name = null
					    check(name) //KotlinNullPointerException
					}
					
					fun check(name: String?) {
					    println(name!!.length)
					}
					                   
                  

             1.6   安全转换运算符：as?
                    安全转换运算符：as? 用于把值转换为指定的类型，当类型转换失败时，它会返回 null，但不会抛出异常崩溃：
                    
                    var a1="abc"
			        var  b1:Int?=a1 as? Int
			        Log.d("TAG","$b1") // 输出null

                    		 val y = null
							val x: String? = y as? String
							println("x = $x")   // x = null


                       
 
              1.6.1  不安全的 类型转换操作符 as
                       如果类型转换不成功，类型转换操作符通常会抛出一个异常崩溃 
                      val y = null
						val x: String = y as String  //注意 null 不能被转换为 String  输出 异常 


            
            1.6.2   java类型转换: val sub:SubClass = parent as SubClass //类似java 类型转换,失败则抛异常
                                 val sub:SubClass?=parent as? SubClass //转换失败,返回null,不抛出异常 
                                val sexx:String= (student as? Person)?.sex //安全类型转换 避免报空指针异常  
                   
        

            1.6.3 空类型
           
  ![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/Kotlin/kotlinimage/20210104162056.png?token=AEZ37PUHAALFKLDEV4SXYVK77UMRI)
 




十三:kotlin 常用的几个标准高阶函数let,with、run和apply
 

    1.let 





   2.with  (带接收者的lambda) (在lambda函数体内可以调用一个不同对象的方法，而且无需借助任何额外限定符)  
     
		with函数用来对同一个对象执行多次操作，而不需要反复把对象名称写出来    
		with函数接收两个参数：第一个参数可以是一个任意类型的对象，第二个参数是一个Lambda表达式。with函数会在Lambda表达式中提供第一个参数对象的上下文，并使用Lambda表达式中的最后一行代码作为返回值返回。

    with结构语法:
    val result = with(obj) {
      // 这里是obj的上下文
      "value" // with函数的返回值
     }



	   //不使用with函数来构建字母表
		fun alphabet():String{
		    val result = StringBuilder()
		    for (letter in 'A'..'Z'){
		        result.append(letter)
		    }
		     result.append("Ok!")
		    return result.toString()
		
		}
		  println(alphabet())//ABCDEFGHIJKLMNOPQRSTUVWXYZOk!

      
      //使用with函数来构建字母表
		fun alphabet():String {
		    val stringBuilder = StringBuilder()
		    return with(stringBuilder) {
		        //指定接收者的值，会调用它的方法
		        for (letter in 'A'..'Z') {
		            this.append(letter) //通过显示的"this"来调用接收者方法  this   表示当前对象 可省略
		        }
		        append("Ok!") //也可以省略this
		        this.toString() //从lambda返回值
		    }

     //  进一步重构成终极版，使用with和一个表达式函数体。

            fun alphabet() = with(StringBuilder()) {
			
			    for (letter in 'A'..'Z') {
			        append(letter)
			    }
			    append("Ok!") 
			    toString() 



         








    3.  run

      run()源码: run函数仅仅是执行了我们的block()，即一个Lambda表达式，而后返回了执行的结果。 
        		 public inline fun <R> run(block: () -> R): R {
				contract {
				    callsInPlace(block, InvocationKind.EXACTLY_ONCE)
				}
				return block()





       首先 run函数是不能直接调用的，而是一定要调用某个对象的run函数才行；
        其次run函数只接收一个Lambda参数，并且会在Lambda表达式中提供调用对象的上下文。 



        val result = obj.run {
           // 这里是obj的上下文
           "value" // run函数的返回值
           }




    4. apply
      apply函数和run函数也是极其类似的，都是要在某个对象上调用，并且只接收一个Lambda参数，也会在Lambda表达式中提供调用对象的上下文，但是apply函数无法指定返回值，而是会自动返回调用对象本身。
     apply函数几乎和with函数一样，唯一区别就是apply会始终返回作为实参传递给它的对象（接收者对象）






      apply结构语法: 
       // result == obj
       val result = obj.apply {
       // 这里是obj的上下文
       }





      val arrayList = ArrayList<String>().apply {
            add("haha0")
            add("haha1")
            add("haha2")
            add("haha3")
            add("haha4")
        }  

      
       val stringbuilder =StringBuilder().apply {
            append("{")
            for (param in arrayList) {
                append(param)
                append(",") 
            }
            append("}")
        }


        Log.d("TAG",stringbuilder.toString())   //{haha0,haha1,haha2,haha3,haha4,}








十四:Kotlin的两种延迟初始化的方式。

 十四一: 对全局变量延迟初始化 lateinit var  

延迟初始化使用的是lateinit关键字，它可以告诉Kotlin编译器，我会在晚些时候对这个全局变量进行初始化，这样就不用在一开始的时候将它赋值为null了。lateinit 修饰var的变量，且变量是非空的类型

  注意:
1.ateinit var只能用来修饰类属性，不能用来修饰局部变量，并且只能用来修饰对象，不能用来修饰基本类型(因为基本类型的属性在类加载后的准备阶段都会被初始化为默认值)。

2.lateinit var的作用也比较简单，就是让编译期在检查时不要因为属性变量未被初始化而报错(UninitializedPropertyAccessException)。

3.lateinit 本身的含义是延迟初始化，但是在编译时必须保证 lateinit 修饰的参数被初始化，否则编译之后运行会报错。注意是运行是会报错，这个问题很严重，所以用的话一定要记得初始化。

			private var name0: String //报错 没有进行初始化 或非空判断 
			private var name1: String = "yc逗比" //不报错  进行了初始化
			private var name2: String? = null //不报错  进行了非空判断  
			private lateinit var name: String               //正确的  延迟初始化
			private lateinit var adapter : AndroidCollectAdapter        //正确的 延迟初始化
			private lateinit var a : Int                //报错   不能延迟初始化 基本类型变量





    //  没有使用延迟初始化lateinit关键字,需要对全局变量 MsgAdapter惊醒非空判断?, 并赋值null        
     class MainActivity : AppCompatActivity(), View.OnClickListener {

		    private  var adapter: MsgAdapter?=null
		
		    override fun onCreate(savedInstanceState: Bundle?) {
		        …
		        adapter = MsgAdapter(msgList)
		        …
		    }
		
		    override fun onClick(v: View?) {
		        …
		        adapter?.notifyItemInserted(msgList.size - 1)
		        …
		    }
		
		}

 



 

 
 
    //使用延迟初始化lateinit关键字的全局变量MsgAdapter 

		    class MainActivity : AppCompatActivity(), View.OnClickListener {
		
		    private lateinit var adapter: MsgAdapter
		
		    override fun onCreate(savedInstanceState: Bundle?) {
		        …
		        adapter = MsgAdapter(msgList)
		        …
		    }
		
		    override fun onClick(v: View?) {
		        …
		        adapter.notifyItemInserted(msgList.size - 1)
		        …
		    }
		
		   }







十四二: 一种是by lazy: 原理:属性代理 
    定义方法:
       val/var <property name>:<Type> by <expression>
    代理者对象 需要实现相应的setValue/getValue方法





  //变量hello的值的初始化 交给了 lazy去处理初始化
   val  hello:String by lazy{
       "犊子啊"
   }






十五:  
十五一:扩展函数
扩展函数表示即使在不修改某个类的源码的情况下，仍然可以打开这个类，向该类添加新的函数方法。
扩展函数表现得就像是属于这个类本身的一样，可以使用 this 关键字并直接调用所有 public 方法


注意要点
     1.Kotlin 的方法扩展并不是真正修改了对应的类文件，而是在编译器和 IDE方面做了处理。使我们看起来像是扩展了方法。
     2.扩展函数 和扩展属性   必须要在顶层类里面定义 
       SpreadFunctions.kt  是个顶层类   其里面的方法函数和 属性字段    可以 在任何地方调用

     3.扩展函数和扩展属性内只能访问到类的公有方法和属性，私有的和protected是访问不了的
     4.扩展函数不能被override，因为Java中它是静态的函数



扩展函数其语法结构非常简单:

     fun ClassName.methodName() {
         ........  
     }

     fun ClassName.methodName(param1: Int, param2: Int) {
         ........  
     }


     fun ClassName.methodName(param1: Int, param2: Int): Int {
      return 0
     }


     fun ClassName.methodName(param1: Int, param2: Int): String {
      return str
     }
 




  在顶层类SpreadFunctions.kt 里定义扩展函数:
   
			//为 String 类声明一个扩展函数 lastChar() ，用于返回字符串的最后一个字符
			//get方法是 String 类的内部方法，length 是 String 类的内部成员变量，在此处可以直接调用
			fun  String.lastChar():Char{
			    return   this.get(this.length-1)
			}
			
			
			
			
			//为 Int 类声明一个扩展函数 doubleValue() ，用于返回其两倍值
			//this 关键字代表了 Int 值本身
			
			fun  Int.doubleValue():Int{
			    return  this*2
			}
			
			
			
			
			/**
			 * 拓展函数，此处duration已经赋了默认值，所以这个参数可传可不传。
			 */
			fun FragmentActivity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
			    Toast.makeText(this, message, duration).show()
			}
			
			


   在MainActivity.kt 进行扩展函数的调用


      override fun onCreate(savedInstanceState: Bundle?) {
         



            val result ="fangmingfeiC".lastChar()
           Log.d("TAG",result.toString()) //C


           val result2=100.doubleValue()
            Log.d("TAG","$result2") //200


           this.toast("拓展函数")


            }












十六:内联类 (类似java的装箱拆箱) 
   在类前面加  inline 

 





十七:kotlin json 数据的解析


 




十八:泛型

**  1.函数声明泛型: fun <T> maxOf(a:T,b:T):T**
     具体使用: val max = maxOf<String> ("hello","world")



*** 2.类声明泛型: class X<T> ***



**  3.泛型约束:**
   比较任意类型a  b  的大小:让T 实现Comparable接口,可能比较大小
			fun <T> maxOf(a:T,b:T):T{}
            fun<T:Comparable<T>>  maxOf(a:T,b:T):T{}

 
 **  4.泛型型变: 实参的继承关系  对  泛型类型的继承关系的 影响**

       		 (1) 协变:继承关系一致   泛型前加个out 变成协变
                 协变小节:子类Derived 兼容父类Base
                         生产者Producer<Derived> 兼容 Producer<Base>
               		  sealed class List<out T> {
				        object Nil : List<Nothing>(){
				            override fun toString(): String {
				                return "Nil"
				            }
				        }




              协变点的对应的类的泛型的参数必须声明为 协变 out T/V 或不变
                            /*
							* key 的 getter 返回K    叫做协变点
							* value 的 getter 返回V  叫做协变点
							* */
							   public interface  Entry<out K,out V>{
							       public val key:K
							       public val value:V
							   }
 
         

			 (2)逆变: 继承关系相反  类型前加 in  
       
                     子类Derived 兼容父类Base
                    父类消费者Consumer<Base> 兼容子类Consumer<Derived> 
                    存在逆变点的类的泛型的参数 必须声明为 逆变 或 不变



           (3)不变:没有继承关系

     5. 星投影:  * 可以表示类型
      * 在变量类型前声明
      * 在未知类型前声明
      * 替换协变点返回类型参数的类型  表示 上限类型
      * 替换逆变点接收类型参数的类型  表示 下限类型   所有的下限都用Nothing 表示
       
val queryMap: QueryMap<*, *> = QueryMap<String, Int>()

  
 ![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/Kotlin/kotlinimage/20210111220756.png?token=AEZ37PRUDBBPFBN5IMDSNMC77UNKC)


 ![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/Kotlin/kotlinimage/20210111221132.png?token=AEZ37PWJQA3PEB3GZYYS6OC77UQPI)
 
 ![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/Kotlin/kotlinimage/20210111221307.png?token=AEZ37PXANFWFK3PAOOPX4KC77UQU6)



十九:kotlin反射

 1.反射:容许程序在进行访问程序结构的一类特性
 程序结构包括: 类  接口 方法  属性等语法特性 
   
 2. 反射依赖:
  
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    implementation "org.jetbrains.kotlin:kotlin-reflect"


3.反射数据结构
   ![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/Kotlin/kotlinimage/20210112150240.png?token=AEZ37PXMRYJH5PLMM44EAKK77VQAA)

   ![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/Kotlin/kotlinimage/20210112150326.png?token=AEZ37PR2NNTXOKUY5CIVVU277VP2K)
  
 4. 反射常见用途 


  4.java反射 与kotlin反射的优缺点
   
  ![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/Kotlin/kotlinimage/20210112150602.png?token=AEZ37PW5UOUGRACETPTYWEC77VQHC)


二十： 协程
   ** 携程的概念**
   协程是可以由程序自行控制**挂起,恢复**的程序,
   由于可以挂起和恢复,就可以用来实现多任务的协作的执行
   可以用来解决异步任务的控制，让异步代码逻辑用同步代码形式写出
    (注意协程不能让代码异步,可以让异步代码更简单)
     
   协程也可以认为是一个轻量级的线程    
   协程的异步机制: 就是把主调用流程挂起(非阻塞式挂起)，
 ** 协程的优点:**
    0.轻量:以在单个线程上可以运行多个协程，因为协程支持挂起，不会使正在运行协程的线程阻塞。
    1.提升CPU的利用率,减少线程的切换,提升程序的运行效率
    2.协程可以被控制发起子任务
    3.协程占用资源比线程少,节省内存，且支持多个**并行**操作。
    4. “回调地狱”,代码可读性急剧降低,处理线程切换,线程使用不当导致的异常,协程可以帮助我们解决这些问题
    5.(线程的实现是映射到内核,线程当中的代码逻辑在线程抢到 CPU 的时间片的时候才可以执行，否则就得歇着)
      协程更轻量，协程并不会像线程那样进行映射成内核和抢占CPU,协程的调度在用户层就可以搞定,
        任务之间是协作式的，非抢占式的。


进程就是应用程序的启动实例。比如我们运行一个游戏，打开一个软件，就是开启了一个进程。
线程从属于进程，是程序的实际执行者。一个进程至少包含一个主线程，也可以有更多的子线程。



协程和线程是有点类似的，可以简单地将它理解成一种轻量级的线程。
协程就像非常轻量级的线程。线程是由系统调度的，线程切换或线程阻塞的开销都比较大。而协程依赖于线程，但是协程挂起时不需要阻塞线程，几乎是无代价的，协程是由开发者控制的。所以协程也像用户态的线程，非常轻量级，一个线程中可以创建任意个协程。
协程可以简化异步编程，可以顺序地表达程序，协程也提供了一种避免阻塞线程并用更廉价、更可控的操作替代线程阻塞的方法 – 协程挂起。
协程就像非常轻量级的线程。线程是由系统调度的，线程切换或线程阻塞的开销都比较大。而协程依赖于线程，但是协程挂起时不需要阻塞线程，几乎是无代价的，协程是由开发者控制的。所以协程也像用户态的线程，非常轻量级，一个线程中可以创建任意个协程。
在一个线程上可以同时跑多个协程，同一时间只有一个协程被执行，在单线程上模拟多线程并发，协程何时运行，何时暂停，都是有程序员自己决定的，
协程是非阻塞式的(也有阻塞API)，一个协程在进入阻塞后不会阻塞当前线程，当前线程会去执行其他协程任务 
协程通过将复杂性放入库来简化异步编程。程序的逻辑可以在协程中顺序地表达，而底层库会为我们解决其异步性。该库可以将用户代码的相关部分包装为回调


协程通过将复杂性放入库来简化异步编程。程序的逻辑可以在协程中顺序地表达，而底层库会为我们解决其异步性。该库可以将用户代码的相关部分包装为回调

协程在线程中是顺序执行的，既然是顺序执行的那怎么实现异步，这自然是有手段的。Thread 中我们有阻塞、唤醒的概念，协程里同样也有，挂起等同于阻塞，区别是 Thread 的阻塞是会阻塞当前线程的(此时线程只能空耗 cpu 时间而不能执行其他计算任务，是种浪费)，而协程的挂起不会阻塞线程。当线程接收到某个协程的挂起请求后，会去执行其他计算任务，比如其他协程。协程通过这样的手段来实现多线程、异步的效果，在思维逻辑上同 Thread 的确有比较大的区别，大家需要适应下思路上的变化

 

协程是用来干什么的？ 
协程是一种语法糖 协程的出现是来解决异步问题的，但它本身却不提供异步的能力，协程某种意义上更像是一种语法糖，它为我们隐藏了异步调用和回调的细节，让我们更关注于业务逻辑的实现。
协程是一种轻量级的方便操作异步代码的语法糖，而它本身不提供异步能力。

为什么要用协程？
协程让代码更简洁 协程可以允许我们用同步代码的方式写出异步代码的功能。
协程让异步异常处理更方便 如果你的异步代码出现异常，通常你会在你的回调中加入一个 onError 来传递这个异常信息：

异步编程中最为常见的场景是：在后台线程执行一个复杂任务，下一个任务依赖于上一个任务的执行结果，所以必须等待上一个任务执行完成后才能开始执行。看下面代码中的三个函数，后两个函数都依赖于前一个函数的执行结果。
			fun requestToken(): Token {
			    // makes request for a token & waits
			    return token // returns result when received 
			}
			
			fun createPost(token: Token, item: Item): Post {
			    // sends item to the server & waits
			    return post // returns resulting post 
			}
			
			fun processPost(post: Post) {
			    // does some local processing of result
			}

 


协程 app/build.gradle文件当中添加如下依赖库：

  dependencies {
     kotlin{
        experimental {
            coroutines 'enable'
        }
    }

    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9'

}
       

 buildscript {
    ext.kotlin_version = '1.3.21'
    ......
}
implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"




**携程的分类:**

  1.按调用栈分类:
  有栈协程 Stackful Coroutine：每个携程会分配单独的调用栈(跟线程的调用栈一样)。

      优点:因为有栈，所以在任何一个调用的地方运行时都可以选择把栈保存起来，暂停这个协程，听起来就跟线程一样了，只不过挂起和恢复执行的权限在程序自己，而不是操作系统。可以在任意函数调用层级的任意位置进行挂起，并转移调度权，
      缺点:每创建一个协程不管有没有在运行都要为它开辟一个栈内存,占用内存成本,因为栈内存是有限的.这也是目前无栈协程流行的原因。
  
   无栈协程 Stackless Coroutine：每一个协程都不会单独开辟调用栈
     不会分配单独的调用栈,通过**闭包**或**对象** 记录挂起点的状态 。不开辟内存,现在大部分都用这个无栈协程
     当我们的无栈协程被挂起/中断,那我们的无栈协程要保存到哪儿呢？
       保存到 Continuation 对象当中，就是一个普通的对象，占用内存非常小，
       在Kotlin 中Continuation 的类本质上就是一个回调Callback，
       resume 就是 onSuccess，resumeWithException 就是 onFailure。
		对于 Kotlin 来讲，每一个 suspend 函数都是一个挂起点，意味着对于当前协程来说，每遇到一个 suspend 函数的调用，它都有可能会被挂起。每一个 suspend 函数都被编译器插入了一个 Continuation 类型的参数用来保存当前的调用点：
       
      线程调度是抢占式调度，很有可能线程 A 运行得美滋滋的，线程 B 突然把 CPU 抢过来，跟 A 说“你给我下去吧你”，于是线程 A 只能干瞪眼没办法；而协程的调度是非抢占式的，目前常见的各支持协程的语言实现中都有 yield 关键字，它有“妥协、退让”的意思，如果一个协程执行到一段代码需要歇会儿，那么它将把执行权让出来，如果它不这么做，没人跟它抢。



    ** Kotlin 协程的本质了，它是一种无栈协程实现，它的本质就是一段代码 + Continuation 实例。**
           
         suspend fun hello() = suspendCoroutine<Int>{ continuation ->
          println("Hello")
          continuation.resumeWith(Result.success(10086))
           }




  2. 按调度方式分类:
  ** 对称协程 Symmetric Coroutine：任何一个协程都是相互独立且平等的，调度权可以在任意协程之间转移。**
   **非对称协程 Asymmetric Coroutine：调度权只能转移给调用自己的协程,协程之间存在父子关系**
   **典型的案例就是：在kotlin中用到的 async/await，await 时将调度权转移到异步调用中，异步调用返回结果或抛出异常时总是将调度权转移回 await 的位置。**
    如何将  非对称协程转为 对称协程？  
 	 在非对称协程的基础上，我们只需要添加一个中立的第三方作为协程调度权的分发中心，所有的协程在挂起时都将控制权转移给分发中心，分发中心根据参数来决定将调度权转移给哪个协程
     Kotlin 协程框架中基于 Channel(https://kotlinlang.org/docs/reference/coroutines/channels.html) 的通信等。
	Kotlin 的协程用一个最基本的 suspend 关键字来支持了最基本的挂起恢复逻辑，进而在上层封装，衍生出了以上提到的几乎所有的模型，让我们在 Kotlin 当中可以有机会使用 async/await、Channel，以及最新出的 Flow API，将来还会有更多（也许包括在 issue 中被提到想要重做的 Actor），它想做的事儿太多了，也确实在一步一步地做到。

   ![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/Kotlin/kotlinimage/20210114215708.png?token=AEZ37PWNVSXWXSMVI4SSL7TAAEC4A)

   
 ![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/Kotlin/kotlinimage/20210114223103.png?token=AEZ37PQH7GKXUAVKAI53WFLAAEDHY)


![](http://https://raw.githubusercontent.com/fmf19870210/FMFNote/master/Kotlin/kotlinimage/20210114223142.png?token=AEZ37PUTF7CK7URQ4QF7GZLAAEDM4)


![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/Kotlin/kotlinimage/20210114223330.png?token=AEZ37PT7FBE4MTKP2E4PXD3AAEDSK)

** 协程的实现**

 **1.挂起韩函数: suspend 是修饰函数的关键字**
      意思是当前的函数是可以挂起的，但是它仅仅起着提醒的作用
	  挂起函数只能在其他其他挂起函数或协程中调用
	  挂起函数 调用时包含了协程"挂起"的语义
	  挂起函数 返回时包含了协程"恢复"的语义
 
	suspend 关键字
    suspend，用作修饰会被暂停的函数，被标记为 suspend 的函数只能运行在协程或者其他 suspend 函数当中。
	协程里使用 suspend 关键字修饰方法，既该方法可以被协程挂起，没用suspend修饰的方法不能参与协程任务，suspend修饰的方法只能在协程中只能与另一个suspend修饰的方法交流
	挂起函数挂起协程时，不会阻塞协程所在的线程。挂起函数执行完成后会恢复协程，后面的代码才会继续执行。但是挂起函数只能在协程中或其他挂起函数中调用。

   
什么时候需要使用挂起函数呢？常见的场景有：
(1)耗时操作：使用 withContext 切换到指定的 IO 线程去进行网络或者数据库请求。
             
			private suspend fun getResult(num: Int): Int {
			    return withContext(Dispatchers.IO) {
			        num * num
			    }
			}


(2)等待操作：使用delay方法去等待某个事件。
   delay 会让协程挂起，这个过程并不会阻塞 CPU. delay 也可以是让协程休眠
        	 private suspend fun getResult(num: Int): Int {
			    delay(5000)
			    return num * num
			}



 **2. 挂起函数的类型:**
  // 挂起函数  返回类型:suspend() ->Unit
      suspend fun foo(){}  
      fun foo(continuati on:Continuation<Unit>):Any{}
        continuation= suspend fun foo(){}  
 // 挂起函数  返回类型:suspend(Int) ->String
    suspend fun bar(a:Int):String{return "hello"}
    fun bar(a:Int,continuation:Continuation<String>):Any{return "hello"}
         continuation=suspend fun bar(a:Int):String  

  
       
		suspend fun requestToken(): Token {  return Token() }   
		suspend fun createPost(token: Token, item: Item): Post { return Post() }  
		fun postItem(item: Item) {
		    GlobalScope.launch { // 创建一个新协程
		        val token = requestToken()
		        val post = createPost(token, item)
		        processPost(post)
		        // 需要异常处理，直接加上 try/catch 语句即可
		    }
		}



**3. 回调转写成挂起函数**

			再来看看我们通常的回调版本：
			
			 interface Callback<T>{ 
			     fun onError(e: Exception) 
			 　   fun onSuccess(data: T) 
			 } 
			


		  我们稍微关注一下 Continuation 接口：
            public interface Continuation<in T> { 
            public val context: CoroutineContext 
 　         public fun resume(value: T) 
 　         public fun resumeWithException(exception: Throwable) 
           } 
		(1)参数:CoroutineContext，协程的上下文，这个上下文可以是多个的组合，组合的上下文可以通过 key 来获取。EmptyCoroutineContext 是一个空实现，没有任何功能，如果我们在使用协程时不需要上下文，那么我们就用这个对象作为一个占位即可。
		
         (2)参数:Continuation，顾名思义，继续、持续的意思。
		Continuation 有两个方法，一个是 resume，如果我们的程序没有任何异常，那么直接调用这个方法并传入需要返回的值；
            另一个是 resumeWithException，如果我们的程序出了异常，那我们可以通过调用这个方法把异常传递出去。 简直与 Callback 一模一样。
         
		    使用 suspendCoroutine 获取挂起函数的Continuation
		    回调成功的分支使用 Continuation.resume(value) 
		    回调失败的分支使用 Continuation.resumeWithException(e)



**4.创建协程**
 fun <T>(suspend()->T).createCoroutine(completion:Continuation<T>):Continuation<Unit>
   suspend 函数本身需要一个Continuation 实例在恢复时调用,
  返回值类型Continuation<Unit> 则是创建出来的协程载体, receiver suspend 函数会作为 协程的实际执行体

**5.启动协程**
  fun <T>(suspend()->T).startCoroutine(completion:Continuation<T>) 
  fun <T>(suspend R.()->T).startCoroutine(receiver:R,completion:Continuation<T>) 
   public fun CoroutineScope.launch(

        context: CoroutineContext = EmptyCoroutineContext, // 上下文

        start: CoroutineStart = CoroutineStart.DEFAULT,  // 启动模式

        block: suspend CoroutineScope.() -> Unit // 协程体

    ): Job 

   协程上下文就是在协程执行过程中 携带的数据。我们不需要自己去实现上下文，只需要使用现成的就好。上下文有一个重要的作用就是线程切换， 
   上下文索引   CoroutineContext.Key
   上下文元素  CoroutineContext.Element




**6.协程上下文CoroutineContext**
    CoroutineContext 作为一个集合，它的元素就是源码中看到的 Element，每一个 Element 都有一个 key，因此它可以作为元素出现，同时它也是 CoroutineContext 的子接口，因此也可以作为集合出现。  
		CoroutineContext，协程的上下文，这个上下文可以是多个的组合，组合的上下文可以通过 key 来获取。EmptyCoroutineContext 是一个空实现，没有任何功能，如果我们在使用协程时不需要上下文，那么我们就用这个对象作为一个占位即可。 
  

 

**7.协程拦截器**
   拦截器ContinuationInterceptor 就是协程上下文的元素
  可以对协程上下文所在的协程Continuation 进行拦截
  协程的本质就是回调 (就是Continuation)+ “黑魔法”。

 

  interface ContinuationIntercetor:CoroutineContext.Element{
       fun<T>          
 }
 


**8.协程的作用域**
8.1 常用协程作用域:  GlobeScope coroutinesScope   supervisorScope  runBlocking
     1.coroutinesScope与supervisorScope的区别
      coroutinesScope协程作用域里执行多个子协程,其中有一个协程失败了,其他的兄弟协程也会取消不执行了
     supervisorScope协程作用域里执行多个子协程,其中有一个协程失败了,不影响其他兄弟协程的执行
     2.coroutineScope  与 runBlocking的区别
         coroutineScope是挂起函数，会等待其协程体以及所有子协程结束。只是挂起，会释放底层线程用于其他用途
         runBlocking是常规函数,会等待其协程体以及所有子协程结束。会阻塞当前线程来等待。

     3.什么是runBlocking
      runBlocking把主线程包装成一个主协程，它会等待自己里面的所有子协程都结束了,自己才会结束。
		           runBlocking<Unit> {
		
		            val job1: Job = launch {
		                delay(150)
		                LogUtil.e("协程","job1 执行launch协程体")
		            }
		
		
		            val job2: Deferred<String> = async {
		                delay(100)
		                LogUtil.e("协程", "job2 执行 async协程体")
		                " hello fmf"
		            }
		            val job2Str = job2.await()
		
		            LogUtil.e("协程"," job2 获取async{}协程体的数据结果值=: $job2Str")
		        }




**顶级作用域  (GlobeScope“自成一派”)**       
       通过 GlobeScope 启动的协程单独启动一个协程作用域，内部的子协程遵从默认的作用域规则
       通过 GlobeScope 启动的协程“自成一派”。
   GlobeScope 全局协程作用域，可以在整个应用的声明周期中操作，且不能取消，所以仍不适用于业务开发。


**协同作用域(coroutineScope ”一损俱损“ ,"自下而上传播")**
    coroutineScope 是继承外部 Job 的上下文创建作用域，在其内部的取消操作是双向传播的，子协程未捕获的异常也会向上传递给父协程。
    它更适合一系列对等的协程并发的完成一项工作，任何一个子协程异常退出，那么整体都将退出，简单来说就是”一损俱损“。这也是协程内部再启动子协程的默认作用域。
	 在 coroutineScope 当中协程异常会触发父协程的取消，进而将整个协程作用域取消掉，如果对 coroutineScope 整体进行捕获，也可以捕获到该异常，所谓“一损俱损”；
     其可以追踪和取消由他启动的所有协程操作。
  


    作用域常用api:  
    GlobalScope  全局协程  少用   即使 activity fragment 销毁了  其协程任然可以执行
    MainScope    在Activity 中使用  在Activity的onDestroyI()中 取消协程  mainScope.cancel()
    vieModelScope  只能在ViewModel中使用  绑定ViewModel 生命周期
    lifecycleScope  只能在 activity fragment 中使用,会绑定activity fragment的生命周期。



主从作用域(supervisorScope ”自作自受“ )   
   supervisorScope 同样继承外部作用域的上下文，但其内部的取消操作是单向传播的，父协程向子协程传播，反过	 来则不然，这意味着子协程出了异常并不会影响父协程以及其他兄弟协程。
	它更适合一些独立不相干的任务，任何一个任务出问题，并不会影响其他任务的工作，简单来说就是”自作自受“
      子协程的异常不会向上传递，所谓“自作自受”。
 

   究竟使用什么 Scope协程：
     对于没有协程作用域，但需要启动协程的时候，适合用 GlobalScope。
     GlobeScope 全局协程作用域，可以在整个应用的声明周期中操作，且不能取消，所以仍不适用于业务开发。
	 对于已经有协程作用域的情况（例如通过 GlobalScope 启动的协程体内），直接用协程启动器启动
      对于明确要求子协程之间相互独立不干扰时，使用 supervisorScope
      对于通过标准库 API 创建的协程，这样的协程比较底层，没有 Job、作用域等概念的支撑，例如我们前面提到过 suspend main 就是这种情况，对于这种情况优先考虑通过 coroutineScope 创建作用域；更进一步，大家尽量不要直接使用标准库 API，除非你对 Kotlin 的协程机制非常熟悉。**




**8.2  协程启动的方式方法**
       协程启动方式一:launch协程的join()方法  
       协程启动方式二:async协程的await()方法
 

 
8.2.0  协程启动方式的launch启动方式   与async 启动方式的区别
     launch与async构建器都用来启动新协程
    launch():  返回一个Job 并且不附带任何结果值 无返回结果值 
    async():它的作用是创建一个协程，之后返回一个 Deferred<T>对象(继承Job)，调用 Deferred#await()去获取一个返回的最终结果值


    runBlocking<Unit> {

            val job1: Job = launch {
                delay(150)
                LogUtil.e("协程","job1 执行launch协程体")
            }


            val job2: Deferred<String> = async {
                delay(100)
                LogUtil.e("协程", "job2 执行 async协程体")
                " hello fmf"
            }
            val job2Str = job2.await()

            LogUtil.e("协程"," job2 获取async{}协程体的数据结果值=: $job2Str")
         }




8.2.1 launch 的join()方法(只有这一个方法)使用


8.2.2  async 的await()方法使用
        注意:调用await(): T 是有返回结果值的,这个返回结果值从GlobalScope.async<Any> {数据结果值} 获取得到的
          
    我们调用 await() 时，期望 GlobalScope.async<Any> {数据结果值} 能够给我们提供一个合适的结果
      如果 GlobalScope.async<Any> {数据结果值} 提供的是一个数据结果值,调用 await() 就输出这个数据结果值
      如果 GlobalScope.async<Any> {异常} 提供的是一个异常,调用 await() 就输出这个异常结果



         GlobalScope.launch (start = CoroutineStart.DEFAULT){
             val deferred = GlobalScope.async<Any> {
                   "方明飞"
                //  throw ArithmeticException("出异常了!!")
            }
            try {
                val value=   deferred.await()
                log("1. $value")  //1. 方明飞
            }catch (e:Exception){
                log("2. $e") // 2. java.lang.ArithmeticException: 出异常了
            }
        }


 8.2.3   async 协程(具有这2个方法)的  join()方法 的  await()方法的结合使用
          注意:join()无返回结果类型,所以调用join（）方法 无返回结果值的,它只关注是否执行完,不关注是否有数据结果返回
                job.join 其实就是要求当前协程等待 job 执行完成之后再继续执行。

		     GlobalScope.launch (start = CoroutineStart.DEFAULT){
		              val deferred  =GlobalScope.async<Any> {
		                   throw ArithmeticException("出异常了!!")
		              }
		             try {
		                  deferred.join()
		                 log("11. join()无返回结果值,") //输出:  11. join()无返回结果值,
		
		             }  catch (e:Exception){
		                 log("22. $e")
		             }
		         }

8.2.4 ： lauch的join()方法   与  async的await()方法的区别         
         lauch的join()  和 async的await() 都是挂起函数 不会阻塞主线程
   
          (1) 如果我们需要使用 GlobalScope.async<Any> {数据结果值}的 数据结果值(网络请求返回数据),就是通过调用await()方法来返回这个数据结果值;如果我们不需要GlobalScope.async<Any>{...}的数据结果值,就是用join（）方法

           join 只关心协程是否执行完，await 则关心运行的结果，因此 join 在协程出现异常时也不会抛出该异常，而 await 则会；考虑到作用域的问题，如果协程抛异常，可能会导致父协程的取消，因此调用 join 时尽管不会对协程本身的异常进行抛出，但如果 join 调用所在的协程被取消，那么它会抛出取消异常，这一点需要留意。
            job.join 其实就是要求当前协程等待 job 执行完成之后再继续执行。



          (2)我们用 launch 替换 async， join 处仍然不会有任何异常抛出，还是那句话，它只关心有没有完成，至于怎么完成的它不关心。不同之处在于， launch 中未捕获的异常与 async 的处理方式不同， launch 会直接抛出给父协程，如果没有父协程（顶级作用域中）或者处于 supervisorScope 中父协程不响应，那么就交给上下文中指定的 CoroutineExceptionHandler处理，如果没有指定，那传给全局的 CoroutineExceptionHandler 等等，而 async 则要等 await 来消费。
          不管是哪个启动器，在应用了作用域之后，都会按照作用域的语义进行异常扩散，进而触发相应的取消操作，对于 async 来说就算不调用 await 来获取这个异常，它也会在 coroutineScope 当中触发父协程的取消逻辑，这一点请大家注意

          （3）launch ()与async()对异常处理的流程
			launch 会在内部出现未捕获的异常时尝试触发对父协程的取消，能否取消要看作用域的定义，如果取消成功，那么异常传递给父协程，否则传递给启动时上下文中配置的 CoroutineExceptionHandler 中，如果没有配置，会查找全局（JVM上）的 CoroutineExceptionHandler 进行处理，如果仍然没有，那么就将异常交给当前线程的 UncaughtExceptionHandler 处理；
            async 则在未捕获的异常出现时同样会尝试取消父协程，但不管是否能够取消成功都不会后其他后续的异常处理，直到用户主动调用 await 时将异常抛出。
  

		            runBlocking {
		            val job1=  launch {
		                delay(1000)
		                LogUtil.e("协程","job1 执行协程体 finish  one ")
		            }
		            // todo job1.join(),即要等job1执行逻辑完毕后 程序才会执行下面的job3  job2(根据他们的延迟时间来决定先后执行顺序)
		            job1.join()
		
		            val job2 = launch {
		                delay(500)
		                LogUtil.e("协程","job2 执行协程体 finished two ")
		            }
		            val job3 = launch {
		                delay(200)
		                LogUtil.e("协程","job3 执行协程体 finished three ")
		            }
		
		            //  job1 执行协程体 finish  one   job3 执行协程体 finished three  job2 执行协程体 finished two
		           }


-------------------------------------
			            runBlocking {
			             val job1: Deferred<String>  = async {
			                 delay(1000)
			                 LogUtil.e("协程","job1 执行协程体async  finish  one ")
			                " hello fmfmf"
			             }
			          //todo 要等 job1.await() 执行完输出结果值后 才会执行后面的 协程async{}体   job3  job2
			            // 输出job1的async协程体 的数据结果值= hello fmf ， job3 执行协程体async  finish  three  ，job2 执行协程体async  finish  two
			          val job1Str:String  =   job1.await()
			          LogUtil.e("协程"," 输出job1的async协程体 的数据结果值=${job1Str} ")
			
			
			            val job2: Deferred<String>  = async {
			                delay(500)
			                LogUtil.e("协程","job2 执行协程体async  finish  two ")
			                "  "
			            }
			
			
			
			            val job3: Deferred<String>  = async {
			                delay(200)
			                LogUtil.e("协程","job3 执行协程体async  finish  three ")
			                " "
			            }
			
			        }

**9.协程启动模式**
 协程启动模式是默认的DEAFAULT，也就是创建并立即启动的，我们也可以设置启动模式为LAZY，来自己安排是什么时候需要启动：
		    public enum class CoroutineStart {
		
		        DEFAULT,
		
		        LAZY,
		
		        @ExperimentalCoroutinesApi
		
		        ATOMIC,
		
		        @ExperimentalCoroutinesApi
		
		        UNDISPATCHED;
		
		    }

**DEFAULT    协程创建后，立即开始调度， 立即执行协程体 。 饿汉式启动   一旦调度器 OK 就可以开始执行 **
    **       在调度前如果协程被取消，其将直接进入取消响应的状态。 **
               
    MainScope().launch(start = CoroutineStart.DEFAULT)  {
			            log(1)
			
			            val job = GlobalScope.launch (start = CoroutineStart.DEFAULT) {
			              log(2)
			             }
			            log(3)
                       //join()方法要等上面的代码逻辑执行完毕后,才会放行执行下面的代码
                         //输出结果 1 3 2 4  //如果没有join()方法  输出 1 3 4 2
			            job.join() 
			            log(4)
			
			        }

**LAZY         只有在需要的情况下运行。 是懒汉式启动 llaunch 后并不会有任何调度行为，协程体也自然不会进入执行状态，                        				直到我们需要它执行的时候。 只有协程被需要时，包括主动调用协程的start、join或者await等函数时才会开始调度，如果调度前就被取消，那么该协程将直接进入异常结束状态。 **
             那什么时候我们需要它执行的时候呢？
             launch 调用后会返回一个 Job 实例,
              调用 Job.start()，主动触发协程的调度执行,
              调用 Job.join()，隐式的触发协程的调度执行

    
   **(1)调用 Job.start()方法**
                MainScope().launch(start = CoroutineStart.DEFAULT) {
                 log(1)
		            val job= GlobalScope.launch(start = CoroutineStart.LAZY) {
		                log(2)
		                }
		             log(3)
		            //子协程启动模式是LAZY的话,job 必须要调用start方法,否则通过LAZY启动的子协程代码逻辑不执行  仅输出: 1 3  4
		             //调用start方法, 子协程代码逻辑执行  输出: 1 3  4  2
		             job.start()
		            log(4)
		        }

     
  **(2) 调用 Job.join()方法**
		             MainScope().launch(start = CoroutineStart.DEFAULT) {
		            log(1)
		            val job= GlobalScope.launch(start = CoroutineStart.LAZY) {
		                log(2)
		            }
		            log(3)
		            //子协程启动模式是LAZY的话,job 必须要调用join方法,否则通过LAZY启动的子协程代码逻辑不执行  仅输出: 1 3  4
		            //调用join方法,   要等待子协程代码逻辑(log(2))执行完毕后,才会执行下面的代码(log(4)),    因此输出的结果一定是： 1 3  2   4(注意与start()方法的区别)
		            job.join()
		            log(4)
		        }
               






**ATOMIC	   协程创建后，立即开始调度，协程执行到第一个挂起点之前不响应取消。** 
**UNDISPATCHED  立即在当前线程执行协程体，直到第一个 suspend 调用**

    
         




	  //设置启动模式
		val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
		    delay(1000L)
		    LogUtils.i("AndroidActivity"+"设置启动模式")
		}
		LogUtils.i("AndroidActivity"+"hello world")
		job.start()



协程间是如何切换的

		//协程间是如何切换的
		//这段代码先打印出next，然后延迟1秒钟后打印出now，像是android里handler的post和postDelay方法。
		GlobalScope.launch(Dispatchers.Default){
		    LogUtils.i("AndroidActivity"+"协程间是如何切换的")
		    LogUtils.i("AndroidActivity---${Thread.currentThread().name}")
		    LogUtils.i("AndroidActivity"+"next") 
            launch {
		        delay(1000)
		        LogUtils.i("AndroidActivity"+"now")
		    }
		   
		}



**10.作用域的取消 和 协程的取消**

  10.1   
      1.如果作用域取消 ,那么它里面的所有的协程都会取消
                 runBlocking {
							       val scope =    CoroutineScope(Dispatchers.Default)
							
							          scope.launch {
							              delay(1000)
							              log("job 1")
							          }
							
							
							          scope.launch {
							              delay(1000)
							              log("job 2")
							          }
							          delay(100)
							          scope.cancel()
							          delay(1000)
							
							      }





        2.某一个子协程取消,并不会影响其余兄弟协程,兄弟协程任然执行

              runBlocking<Unit> {
			        val scope = CoroutineScope(Dispatchers.Default)
			        val job1 = scope.launch {
			            delay(1000)
			            println("job 1")
			        }
			
			        val job2 = scope.launch {
			            delay(1000)
			            println("job 2")
			        }
			        delay(100)
			        job1.cancel() //输出 job 2 
			        delay(1000)
			    }


 
 
 10.2 协程取消的副作用

   







**11.kotlin的主要调度器Dispatchers**

    Default      默认的调度器，  线程池   主线程循环   用于CPU密集型,不会有IO操作,做逻辑算法    

    Main           UI线程  调用suspend函数  更新LiveData    

    Unconfined 非限制的调度器，指定的线程可能会随着挂起的函数的发生变化。 (很少使用)   直接执行
   
    IO           指定执行的线程是 IO 线程。  线程池    IO密集型 发个网络请求,读取文件
 
    Channel     热数据流  并发安全的通信机制  (即使你不去订阅 数据依然会产生发送)
     
     **Flow       冷数据流   协程的响应式API    (只有你去订阅了 数据才会产生发送给你) 不消  费则不生产，多次消费则多次生产，生产和消费总是相对应的。Flow                                                				可以被重复消费 **
                
    Select         IO多路复用概念
                select 总是按顺序检查事件,存在偏向
                selectUnbiased 会随机检查事件,对事件处理更公平
  
  
12.协程任务泄露
    
   执行一个无用的协程任务,或发送一个无用的协程网络请求,没有接收处理,导致内存 CPU 磁盘资源浪费,这种情况称为 任务泄漏

   为了避免协程的任务泄漏,kotlin 引入 结构化并发机制(详见协程作用域模块知识点)
     结构化并发:可以取消协程任务,追踪协程任务, 当协程操作失败时,发出错误的信息 。
   
  

**13.协程的上下文**

  协程CoroutineContext上下文 用来定义协程行为的元素。有以下元素组成:
    Job: 控制协程的生命周期
    CoroutineDispatcher: 向合适的线程分发任务
     CoroutineName ： 定义携程的名称
     CoroutineExceptionHandler：处理未被捕捉的异常

 CoroutineContext = 默认值+继承的CoroutineContext+参数  
 CoroutineContext=  Job() + Dispatchers.Main + coroutineExceptionHandler
 

				runBlocking<Unit> {
				        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
				            println("Caught $exception")
				        }
				        val scope = CoroutineScope(Job() + Dispatchers.Main + coroutineExceptionHandler
				        )
				
				        val job = scope.launch(Dispatchers.IO) {
				            //新协程
				        }
				    }

 

**14.认识 Channel**
  Channel 实际上就是一个队列，而且是并发安全的，它可以用来连接协程，实现不同协程的通信
  有发送端 可挂起协程  接收端可挂起协程,
   发送端 发送数据 到Channel队列的缓冲区，接收端从缓冲区里取走数据
    通过 produce 这个方法启动一个生产者协程，并返回一个接收协程 ReceiveChannel
  
    通过 actor 启动一个消费者协程,返回一个生产者协程 SendChannel
    ReceiveChannel 和 SendChannel 都是 Channel 的父接口，前者定义了 receive，后者定义了 send，Channel 也因此既可以 receive 又可以 send。 

   produce 和 actor 与 launch 一样都被称作“协程启动器”


** 13. Select 多路复用**



** 14.Flow 篇**
    (1)什么是Flow
     一般挂起函数可以异步返回单个值,而 Flow 就是让挂起函数异步返回多个值。
    (2) 异步返回多个值有多个方案:
         集合
         序列
         挂起函数
         Flow  最优 


  （3） 通常如果异步结果只有一个值，那么挂起函数足够了；如果有多个值呢，例如下载进度这种？用Flow正好。
    Flow 就是 Kotlin 协程与响应式编程模型结合的产物。
    Flow 的执行体内部也可以调用其他挂起函数，这样我们就可以在每次提供一个新元素后再延时 100ms 了。
    Flow的调度器： 
    intFlow.flowOn(Dispatchers.IO)
    flowOn 设置的调度器只对它之前的操作有影响，因此这里意味着 intFlow 的构造逻辑会在 IO 调度器上执行。

  （4）末端操作符
       基本的末端操作符 collect 
       集合类型转换操作末端操作符 toList、toSet   
       聚合操作末端操作符  Flow 规约到单值的 reduce、fold的末端操作符
                          获得单个元素的末端操作符包括 single、singleOrNull、first
   (5)Flow 的取消
      Flow 没有提供取消操作
      Flow 的消费依赖于 collect 这样的末端操作符. Flow 的取消主要依赖于末端操作符所在的协程的状态。
       想要取消 Flow 只需要取消它所在的协程即可。

   (4)创建Flow的几种方式

     //默认的创建 Flow数据对象
        val   intFlow =  flow<Any> {
                (1..5).forEach {
                    log(it)
                    emit(it)//新元素通过 emit 函数提供  使用 emit 重新生产新元素出
                    log(" emit "+emit(it))
                    delay(100)
                }
            }

       val myDispatcher = Executors.newSingleThreadExecutor { Thread(it, "MyThread").also { it.isDaemon = true } }.asCoroutineDispatcher()
          MainScope().launch {
              GlobalScope.launch(myDispatcher) {
                  intFlow.flowOn(Dispatchers.IO) //flowOn 与 RX的 subscribeOn(Schedulers.io()) 对应
                      //collect 所在的协程自然就是观察者,它想运行在什么调度器上它自己指定即可，非常容易区分。
                      //collect 所在协程的调度器则与 RX的observeOn(Schedulers.from(myExecutor)) 指定的调度器对应。
                   // collect  是 消费 Flow 的数据末端操作符
                      .collect {  //collect 所在的协程就是观察者
                          log(" collect1 "+it)
                      }
                  //Flow产生的数据 可以被重复消费
                  intFlow.collect {  log(" collect2 "+it) }
              }.join()
          }

    ----------------------------------------------------------------------------
   //使用channelFlow 函数来创建 Flow对象,生成元素数据时可以切换调度器

       val channelflow=   channelFlow<Any> {
              send("方")
           //切换调度器 在IO里生产数据
              withContext(Dispatchers.IO) {
                  send("明")
                  send("飞")
              }
          }

     GlobalScope.launch{
            channelflow.collect {
                log( it)  //输出 方明飞
            }
        }

------------------------------------------
 // 通过集合 框架来创建 Flow对象：
扩展函数 .asFlow()：面向数组、列表等集合
 listOf(1, 2, 3, 4).asFlow()
setOf(1, 2, 3, 4).asFlow()
flowOf(1, 2, 3, 4)
(1..10).asFlow() 


     val listflow= listOf("hello",1,"2",3.0).asFlow<Any>()
        MainScope().launch {
           listflow.collect {
               log( it)  //输出 "hello",1,"2",3.0
           }
        }
 
(5)Flow 的背压
   背压问题在生产者的生产速率高于消费者的处理速率的情况下出现。为了保证数据不丢失，我们也会考虑添加缓存来缓解问题：
  如果单纯地添加缓存,并不能从根本上解决数据积压的问题
 (1)解决被压方式一:使用 conflate 解决背压问题,新数据会覆盖老数据 不可取
 (2)解决被压方式二:使用 collectLatest 解决背压问题
  collectLatest不会直接用新数据覆盖老数据，而是每一个都会被处理，只不过如果前一个还没被处理完后一个就来了的话，处理前一个数据的逻辑就会被取消。
		      MainScope().launch {
		         flow<Any> {
		             List(100){
		                 emit(it)                                                                                                                       )
		             }
		         }.collectLatest() {
		               log( it)
		             }
		     }






**15.  协程取消:协程取消 任务停止 suspendCancellableCoroutine**
      协程的任务的取消需要靠协程内部调用的协作支持，这就类似于我们线程中断以及对中断状态的响应一样。 






16. 协程 与LifecycleOwner配合使用
     协程也要内存泄漏的问题啊
     协程主要作用是处理线程操作，若处理不当会出现内存泄露等问题，如Activity或者Fragment已销毁，但是界面内的协程却依旧在执行，就会产生内存泄露的问题。所以，我们在界面销毁时，必须取消界面内的协程操作。我们可以自己在界面销毁时调用cancel()方法，但是无良好的编程习惯就很容易被忽略。建议采用Google给我们提供的扩展方法。




17.协程 与ViewModel配合使用
 

18.协程与Retrofit真香组合

implementation "com.squareup.retrofit2:retrofit:2.9.0"
implementation "com.squareup.retrofit2:converter-gson:2.9.0"






线程和协程区别
协程和线程，都能用来实现异步调用，但是这两者之间是有本质区别的 
 协程适合io密集型的程序，多线程适合计算密集型的程序(适用于多核cpu的情况)。当你的程序大部分是文件读写操作或者网络请求操作的时候，这时你应该首选协程而不是多线程，首先这些操作大部分不是利用cpu进行计算而是等待数据的读写，其次因为协程执行效率较高，子程序切换不是线程切换，是由程序自身控制，因此，没有线程切换的开销，和多线程比，线程数量越多，协程的性能优势就越明显。

implementation "androidx.activity:activity-ktx:1.1.0"
implementation "androidx.fragment:fragment-ktx:1.2.5"


挂起与阻塞的区别



增补: 20210308

**一:runCatching的源码:**
 
1. 是一个内联的高阶函数，返回了一个 Result 类，函数里面对回调出去的 lambda 表达式进行了 try catch 的处理。返回的内容使用 Result.success 和 Result.failure 进行的包裹

 2.Result 类中，一共定义了  4 个方法
    
   (1)getOrNull 是一个可空类型的,返回的是 runCatching 中处理的结果
    (2)getOrDefault  返回的是一个不可空的类型,如果是异常的情况返回默认值
    (3)getOrThrow 
    (4)getOrElse




增补:20210309
  扩展函数









