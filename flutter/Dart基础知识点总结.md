
Dart基础知识博客: https://blog.csdn.net/qq_33750826/article/details/105523142

一: Dart 变量：

     dart是一个强大的脚本类语言，可以不预先定义变量类型 ，自动会类型推倒

    dart中定义变量可以通过var关键字可以通过类型来申明变量


二:Dart 常量：   final 和 const修饰符 
     
     const值不变 一开始就得赋值
    final 可以开始不赋值 只能赋一次 ;优于final，只有在运行时才会惰性初始化此常量
    而final不仅有const的编译时常量的特性，最重要的它是运行时常量，并且final是惰性初始化，即在运行时第一次使用前才初始化


    永远不改量的量，请使用final或const修饰它，而不是使用var或其他变量类型。


三:Dart中支持以下数据类型：
    
      
   1.字符串类型: 
          
        (1)字符串换行:
            可以用'...'和"..."表示 字符串单行不换行
            也可以用 '''...'''表示  字符串多行可以换行
                    """...""" 表示  字符串多行可以换行

        (2)字符串拼接:
          
          print("$str1 $str2");
          print(str1 + str2);

    2.数值类型:
          int   必须是整型 
         double  既可以是整型 也可是浮点型
                 double b=23.5;
                 double b=24;



    3. 布尔类型:bool表示
        bool flag1=true;
        bool flag2=false;  
        var flag3=true;
        var flag4=false;



    4.List数组/集合
       var l1=['aaa','bbbb','cccc'];
        var l2=new List<String>();
        




 
    5.Maps集合
          通常来说，Map 是一个键值对相关的对象。 键和值可以是任何类型的对象。每个 键 只出现一次， 而一个值则可以出现多次
       var person={"name":"张三", "age":20, "work":["程序员","送外卖"]};
        var p=new Map();
            p["name"]="李四";
		    p["age"]=22;
		    p["work"]=["程序员","送外卖"];
	
    6.	 使用  is 关键词来判断类型
          if(str is String){}else if(str is int){}else if(str is double){} 



四:Dart运算符：


     1. 算术运算符
          +     加
        
          -     减  
   
           *    乘
           /    除 
           ~/  取整 : 两数字相处的结果 去小数点 取整数
           %   取余 :  两数字相除除不尽取余数



			  int a=13;
			  int b=5;
			
			  print(a+b);   //加   18
			  print(a-b);   //减  8
			  print(a*b);   //乘  65
			  print(a/b);   //除  2.6
              print(a~/b);  //取整  2
			  print(a%b);   //其余  3 
			 







   2. 关系运算符:返回bool 布尔类型

          ==  等于
         ！=   不等于
          >  
          <  
          >= 
          <=




   3.逻辑运算符

        !   取反  
       &&  并且:全部为true的话值为true 否则值为false
       ||  或者：全为false的话值为false 否则值为true

  
    bool flag=false;
    print(!flag);  

     bool a=true;
     bool b=true;
     print(a && b);




   4. 赋值运算符

     4.1  Null-aware (null感知运算符):??     ?  
        
        ?? 的使用 
      
        x=y??z : 如果y 不为null 把y赋值给x, 如果y为null 则把z赋值给x

        String a = b ?? 'hello';  如果b是null,那么 a='hello',如果b不为null a=b
      
        典型案例:
        方法一: 基础IF语句
         String gender;
			if(person.gender!=null){
			    gender = person.gender;
			}else{
			    gender = '未知';
			}
        方法二: 三目运算
         String gender = person.gender!=null ? person.gender : '未知';
       方法三:最佳方案: ??运算符
         String gender = person.gender??"未知";  //如果person.gender=null 则把"未知" 赋值给String gender   如果person.gender不为null,则把person.gender赋值给String gender



           

          ??=的使用
          
           A??= B   如果A为null,则把B值赋值给A; 
          b ??= 'hello';   如果b是null 则把'hello'  赋值给b
           person.gender ??= "男性";  若Person对象的gender属性为null, 为其赋值为”男性”.
           _savedValue??="暂无缓存数据"


          ？.  的使用
          obj?.method()     当obj不为null时, 调用method函数:
         person?.changeToFemale(); 当person不为null时, 变更性别为女性:




 
     4.2 复合赋值运算符
          +=    a+=10;    表示a=a+10
          -= 
          *=     a*=3;    a=a*3;
          /=  
          %= 
          ~/=


  5. 条件表达式符

      1.   if  else   switch case 
       
    var sex="女";
    switch(sex){
     case "男":
        print('性别是男');
        break;
     case "女":
        print('性别是女');
         break;
      default:
        print('传入参数错误');
       break;
     }
         



     2. 三目运算符 : if else 的简写
       bool flag=false;
	  String c=flag?'flag是true':'flag是false';
	  print(c);
     


    3. ??运算符：
   
       var a;
      var b= a ?? 10;

      print(b);   10
    
    var a=22;
    var b= a ?? 10;

    print(b);  22
       
   

 五:类型转换

     1、Number与String类型之间的转换  

        1.1 String类型转成Number类型  int.parse()   double.parse( )
            var myNum=int.parse("123");
            var myNum=double.parse('123');


        1.2  Number类型转换成String类型  数字.toString()
             
   




     2、其他类型转换成Booleans类型





六：  ++  --   表示自增1  自减 1

     1.在赋值运算里面 如果++/--A 写在前面 这时候A先运算(自增/减1) 再赋值，
	   如果A++/--写在后面 先A赋值后运行运算(自增/减1)


     var a=10;
    var b=a++;
   // ++在后 ：表示 a先运算,赋值给b 所以输出b=a=10，然后在自增+1  输出a =11
    print(a);  //11   //a=a+1=11;
    print(b);  //10  //b=a=10



     var a=10;
    var b=++a;
    // ++在前: a 先自增+1，a=11,a再赋值给b 所以b=11
    print(a);  //11  a先要自加+1 a=11
    print(b);  //11   a先要自加1 a=11  在赋值给b  a=b=11



     var a=10;
     var b=a--;
   // --在后 ：表示 a先运算,赋值给b 所以输出b=a=10，然后在自减-1  输出a =9
    print(a);  //9    a 再自减1
    print(b);  //10  a先赋值给b  b=a=10


    var a=10;
    var b=--a;
  // --在前: a 先自减-1，a=9 ,a再赋值给b 所以b=9
    print(a);  //9
    print(b);  //9



  2. 如果不是赋值运算,那么无论++/-- 在前在后 结果都是一样的 都是自增/减 1  
       var A=10;
       ++A  //11
       A++  //11
       --A  //9       
       A--  //9
       
       

         

七: for while forEach map where any every 循环
           
         var myList=[1,3,4];
         var newList=new List();
       
        for(var i=0;i<myList.length;i++){
          print(myList[i]);
        }

         for(var item in myList){
          print(item);
          }

         
         myList.forEach((value){
           print("$value");
          });

          
        var myList2=[1,3,4];
        var newList=myList2.map((value){return value*2;});
        print(newList.toList()); //[2, 6, 8]

          
         List list2=[4,1,2,3,4];
 	      
       var newList3 = list2.map((value){
        if(value>2){
        return value*2;
        }else {
          return value;
        }
      });

     print(newList3.toList()); // [8, 1, 2, 6, 8]


      var newList4= list2.map((value) => value>2?value*2:value);
      print(newList4.toList()); // [8, 1, 2, 6, 8]






       where 循环 满足某个条件,返回新集合
          List myList3=[1,3,4,5,6,7,8,9];
            var newList2=myList3.where((value){
            return value>6;
            });
            print(newList2.toList()); //[7, 8, 9]

      
       any只要集合myList3里面有一个满足条件的元素值就返回true,
        如果集合里元素都不满足条件就返回false    
          var flag2=myList3.any((value) {
            return value>5;
             });
             print(flag2); //true

           
       every 集合里每一个元素值都必须满足条件才会返回true  否则返回false
		 var flag3 = myList3.every((value) {
		   return value>5;
		 });

         print(flag3);//false



八:  break     continue

        
   8.1.	break语句功能:
          1、在switch语句中使流程跳出switch结构。
          2、在循环语句中使流程跳出当前循环,遇到break 循环终止，后面代码也不会执行
          
          强调:
          1、如果在循环中已经执行了break语句,就不会执行循环体中位于break后的语句。
          2、在多层循环中,一个break语句只能向外跳出一层循环
          3,  break可以用在switch case中 也可以用在 for 循环和 while循环中
   
    8.2. continue语句的功能:
			  
        【注】只能在循环语句中使用,使本次循环结束，即跳过循环体重下面尚未执行的语句，接着进行下次的是否执行循环的判断。

        continue可以用在for循环以及 while循环中，但是不建议用在while循环中，不小心容易死循环

      //1、如果i等于4的话跳过

     for(var i=1;i<=10;i++){
       if(i==4){
         continue;  /*跳过当前对4的循环体 然后循环还会继续执行*/
      }
       print(i); //1235678910
     }
  

      //2、如果 i等于4的话跳出循环

     for(var i=1;i<=10;i++){
       if(i==4){
         break;  /*跳出循环体,该循环结束*/
       }
       print(i); //123
     }



    //3、break语句只能向外跳出一层


    	 for(var i=0;i<5;i++){	 	
           	
		 	print('外层---$i');
           for(var j=0;j<3;j++){	
            
            if(j==1){
               break;
             }
            print('里层$j');		 	

           }	
	  }





九:

1.List里面常用的属性和方法：

    集合常用属性：
        length          长度
        reversed        翻转
        isEmpty         是否为空
        isNotEmpty      是否不为空
    集合常用方法：  
        add         增加
        addAll(subList)      拼接数组subList
        indexOf(A)     查找集合中是否包含元素A;  传入具体值A,查找到返回索引值   查找不到返回-1  
        remove(A)     删除集合的元素A  传入具体值A
        removeAt(i)    通过角标i删除集合改角标对应的元素  传入索引值i
        fillRange(startIndex,endIndex,A);   修改元素值:把集合的开始的角标startIndex到结束的角标endIndex 对应的旧元素值修改为A   
        insert(index,value);            指定位置index插入元素value    
        insertAll(index,list)           指定位置index插入list
        toList()    其他类型转换成List 集合 
        join(",")      List转换成字符串, 以 , 分割
        split('-')     把字符串转化成List集合,以'-'分割字符串
        forEach   
        map
        where
        any
        every
	

2.	Set集合 
    去重无序

		 List myList=['香蕉','苹果','西瓜','香蕉','苹果','香蕉','苹果'];
		
		  var s=new Set();
		
		  s.addAll(myList);
		
		  print(s);
		
		  print(s.toList());



3.Map集合

   3.1 Map集合常用属性：
        keys            获取所有的key值
        values          获取所有的value值
        isEmpty         是否为空
        isNotEmpty      是否不为空

  3.2常用方法:
        remove(key)     删除指定key的value元素值数据
        addAll({...})   合并映射  给映射内增加属性
        containsValue(vlaue)   查看映射内的值  是否包含元素值value 返回true/false

 
    var  map={
    "name":"张三",
    "age":20,
    "sex":"男",
    "intrest":['上网','打球']
    };

    var subMap = { "work":['敲代码','送外卖'], "height":160};
     map.addAll(subMap);

    print(map);// {name: 张三, age: 20, sex: 男, intrest: [上网, 打球], work: [敲代码, 送外卖], height: 160}
    print(map.keys.toList());//输出key集合  [name, age, sex, intrest, work, height]
    print(map.values.toList());//输出value集合   [张三, 20, 男, [上网, 打球], [敲代码, 送外卖], 160]







十：函数/方法 


   1. 有名函数无参方法   
      //有名函数方法的调用
      fn1();

       void fn1(){
       print('我是一个有名函数无参方法');
       }

   1.1 有名函数有参方法   
    
         print(getNum(12));

         int getNum(int n){
          return n;
          }


   2.  函数作为参数传入另一个方法里
        注意:在dart中，当参数是一个函数时，只需要将函数名字传入，并不需要写() 小括号。
       
          //这里传入的参数: fn1 是  fn1()方法
           fn2(fn1);  
  

        	 void fn1(){
			  print('我是一个有名函数方法');
			}

               //注意 :参数fnName 是个方法
				void fn2(fnName){
				  print('我是fn2方法');
				  fnName(); //执行参数fnName 方法
				}



  3.无参匿名函数 方法

         //无参匿名函数方法的调用
         fnm(); //我是一个无参匿名函数方法

          //无参匿名函数 方法
           var fnm =(){
			  print('我是一个无参匿名函数方法');
			};


  4.  有参匿名函数方法 

     fnmm("hello dart"); // 我是一个有参匿名函数方法: hello dart

       //有参匿名函数方法 
       var fnmm =(String value){
		  print('我是一个有参匿名函数方法: '+value);
		};





  5.自执行方法
     5.1无参自执行方法： ((){....})();
          ((){print("我是自执行方法");})(); //我是一个无参自执行方法

      5.2 有参自执行方法:  ((形参){....})(实参);
           (String value){print('我是一个有参自执行方法'+value);}("hell dart"); //我是一个有参自执行方法hell dart
          

             ((int n){print("我是自执行方法 n=$n");})(11);



6.箭头函数 (少用感觉有问题)
     注意：箭头函数只能有一条语句
  
	    /**
	  *  forEach(void f(E element))  传入形参参数是个方法 void f(E element)
	  *   实参1: (value){print(value);};
	  *  实参2: (value)=> print(value)
	  *  实参3:(value)=> {print(value)}
	  * */
    var value1 = (value){print(value);};
    var value2=(value)=> print(value);//=>右边只能一句话
    var value3=(value)=> {print(value) };//{}里只能一句话
     list.forEach(value1);
     list.forEach(value2);
     list.forEach(value3);

7.自定义方法

    自定义方法的基本格式：

      返回类型  方法名称（参数1，参数2,...）{
        方法体
        return 返回值;
      }
            
				int getNum(){
				  var myNum=123;
				  return myNum;
				}

			String printUserInfo(){
			
			  return 'this is str';
			}
			
			
			List getList(){
			
			  return ['111','2222','333'];
			}



8.方法的必传参数,方法的默认参数 方法的可选参数[]  方法的命名参数{}  方法作为参数
		方法的默认参数:就是这个参数有了默认值(如果你没传值)
 
        方法的可选参数：意识就是可以传也可以不传,都不会报错。可选参数可以传多个
                       必须放在必传参数的后面,并且用[]包含可选参数

        命名参数: 命名参数的实参必须和形参一样,用{} 包含命名参数

        注意:
       1.默认参数如果不传入则使用默认，如果传入则覆盖，并且只能在可选参数[]中括号 命名参数{}大括号里面定义
       2.在一个方法里  可选参数[] 和 命名参数{} 不能同时使用 

        functionWontWork(String foo, [String positonal], {String named}) {} //此方法是错误的





  
 
     返回类型  方法名称（必传参数1=姓名，必传参数2=XXX, [可选参数1="男",可选参数2=25,可选参数3,可选参数4,可选参数5,......],{命名参数1,命名参数2,命名参数3,....}）{
        方法体
        return 返回值;
      }
       
     printUserInfo('张三') //必传参数 '张三'
     printUserInfo('小李','女') //必传参数 '小李'   可选参数   '女' 
     printUserInfo('小李','女',30)  //必传参数 '小李'   可选参数   '女'  30  
    printUserInfo('小李','女',30,work:"程序员",interest:"上网") //必传参数 '小李'   可选参数   '女'  30   命名参数  work:"程序员",interest:"上网"
    
    

 
  



  9.闭包：

    1、全局变量特点:    全局变量常驻内存、全局变量污染全局
    2、局部变量的特点：  不常驻内存会被垃圾机制回收、不会污染全局  


    闭包实现的功能：

        1.让变量常驻内存        
        2.变量不污染全局   

          产生了闭包,闭包可以解决这个问题.....  

          闭包: 函数嵌套函数, 内部函数会调用外部函数的变量或参数, 变量或参数不会被系统回收(不会释放内存)
  
	        闭包的写法： 函数嵌套函数，并return 里面的函数，这样就形成了闭包。





 十一:类 对象 构造函数

	 1.默认构造函数 :
        默认构造函数(无参/有参) 默认构造函数只能有一个

         命名构造函数:	
         命名构造函数可以有多个

       class Person{
			  String _name; //私有属性
			  int age; 
			  //默认构造函数的简写 只能由一个
			  Person(this._name,this.age);
			  
			  //命名构造函数 可以有多个
			  //now()命名构造函数 
			  Person.now(){print('我是命名构造函数');}
			
			 //setInfo(String name,int age)命名构造函数 
			  Person.setInfo(String name,int age){ this.name=name; this.age=age; }
			
			  void printInfo(){   
			    print("${this.name}----${this.age}");
			  }

             //私有方法            
				void _run(){
				    print('这是一个私有方法');
				  }

               //对外提供的公共方法 调用内部的私有方法 _run()  
				execRun(){
				    this._run();  //类里面方法的相互调用
				  }
				}
 
               //对外提供的公共方法 调用内部的私有私有属性_name
                String getName(){
                  return this._name;  
                 }
			} 



  Person p1=new Person('张三', 20);   //默认实例化类的时候调用的是 默认构造函数
  Person p1=new Person.now();   //命名构造函数now()
  Person p1=new Person.setInfo('李四',30);//  命名构造函数setInfo('李四',30)
  p1.printInfo();
 


  2.类的私有属性 私有方法

	   Dart和其他面向对象语言不一样，Data中没有 public  private protected这些访问修饰符合
		
	   但是我们可以使用_把一个属性或者方法定义成私有。
    



   3.静态成员

       3.1、使用static 关键字来实现类级别的变量和函数

       3.2、静态方法不能访问非静态成员，非静态方法可以访问静态成员   

 

			 class Person {
			  static String name = '张三'; //静态属性
			  int age=20;
			  
			  static void show() { //静态方法
			    print(name);
			  }
			  
			  /*非静态方法可以访问静态成员以及非静态成员*/
			  void printInfo(){  
			
			     print(name);  //访问静态属性name
			     print(this.age);  //访问非静态属性age
			
			      show();   //访问静态方法show()
			  }
			    
				 //静态方法 只能访问静态成员(属性 方法)
			    static void printUserInfo(){//静态方法
			
			         print(name);   //访问静态属性name
			         show();        //访问静态方法show()
			
			        //print(this.age);     //静态方法没法访问非静态的属性age
			
			        // this.printInfo();   //静态方法没法访问非静态的方法printInfo()
			        // printInfo();
			
			  }
			
			}

    

    4.   Dart中的对象操作符:

    ?     条件运算符 （了解）  类似kontlin 里的？ 
    as    类型转换
    is    类型判断
    ..    级联操作 （连缀）  (记住)


       4.1 ： ?     条件运算符 （了解）  类似kontlin 里的？  
       Person p;
       p?.printInfo();  //？ 非空判断  p为空不会调用 
        
        Person p=new Person('张三', 20);
         p?.printInfo();  //？ 非空判断  p不为空会调用




       4.2  is as 
            var p1;

             p1='';

              p1=new Person('张三1', 20);   
            if(p1 is Person){
              p1.name="李四";
              (p1 as Person).printInfo();
             }
             
          

      4.3    ..    级联操作 （连缀）  (记住)



          Person p1=new Person('张三1', 20);
             p1.name='张三222';
              p1.age=40;
              p1.printInfo();
  
          //使用..对以上赋值操作进行简化赋值操作,效果一样的  

              p1..name="李四"
                 ..age=30
                 ..printInfo();
  



十二:Dart的面向对象的三大特性：封装 、继承、多态


   12.1 Dart中的类的继承：  
    1、子类使用extends关键词来继承父类
    2、子类会继承父类里面可见的属性和方法 但是不会继承构造函数
    3、子类能复写父类的方法 getter和setter 
             class Web extends Person{
				  String sex;
				  //子类给父类默认构造函数Person(this.name,this.age)传参
				  Web(String name, num age,String sex) : super(name, age){
				    this.sex=sex;
				  }
				  //子类给父类的自命名构造函数Person.xxx(this.name,this.age)传参
				  Web(String name, num age,String sex) : super.xxx(name, age){
				    this.sex=sex;
				  }
				
				
				  //子类覆写父类的方法printInfo()
				  //可以写也可以不写  建议在覆写父类方法的时候加上 @override
				  @override
				  void printInfo(){
				    print("姓名：${this.name}---年龄：${this.age}");
				  }
				  @override
				  work(){
				    print("${this.name}的工作是写代码");
				  }
				
				
				
				   //子类自己的方法
				  run(){
				    print("${this.name}---${this.age}--${this.sex}");
				    super.work();  //子类的自己的方法里调用父类的方法
				  }
				
			 }





             class Person {
				  String name;
				  num age;
				  Person(this.name,this.age);
				  Person.xxx(this.name,this.age);
				  void printInfo() {
				    print("${this.name}---${this.age}");
				  }
				  work(){
				    print("${this.name}在工作...");
				  }
				 }


   12.2    Dart中抽象类: Dart抽象类主要用于定义标准，子类可以继承抽象类，也可以实现抽象类接口。


		  1、抽象类通过abstract 关键字来定义
		
		  2、Dart中的抽象方法不能用abstract声明，Dart中没有方法体的方法我们称为抽象方法。
		
		  3、如果子类继承抽象类必须得实现里面的抽象方法
		
		  4、如果把抽象类当做接口实现的话必须得实现抽象类里面定义的所有属性和方法。
		
		  5、抽象类不能被实例化，只有继承它的子类可以
          6.抽象类里可以定义非抽象的共用的方法,供子类调用 
		
		extends抽象类 和 implements的区别：
		
		  1、如果要复用抽象类里面的方法，并且要用抽象方法约束自类的话我们就用extends继承抽象类
		
		  2、如果只是把抽象类当做标准的话我们就用implements实现抽象类
     


12.3  Datr中的多态： 
     
      允许将子类类型的指针赋值给父类类型的指针, 同一个函数调用会有不同的执行效果 。

      子类的实例赋值给父类的引用。
	         Animal d=new Dog();
                   d.eat();
    
    多态就是父类定义一个方法不去实现，让继承他的子类去实现，每个子类有不同的表现。



12.4 Dart 接口

     和Java一样，dart也有接口，但是和Java还是有区别的。
	
	  首先，dart的接口没有interface关键字定义接口，而是普通类或抽象类都可以作为接口被实现。
	
	  同样使用implements关键字进行实现。
	
	  但是dart的接口有点奇怪，如果实现的类是普通类，会将普通类和抽象中的属性的方法全部需要覆写一遍。
	  
	  而因为抽象类可以定义抽象方法，普通类不可以，所以一般如果要实现像Java接口那样的方式，一般会使用抽象类。
	
	  建议使用抽象类定义接口。
     
     Dart中一个类实现多个接口,不能多继承：   
     
     abstract class Db{   //当做接口   接口：就是约定 、规范
      String uri;      //数据库的链接地址
      add(String data);
      save();
      delete();
     }


     abstract class DD{   //当做接口   接口：就是约定 、规范
      String uri;      //数据库的链接地址
      add(String data);
      save();
      delete();
     }


    

                        //实现接口Db
					     class MsSql implements Db，DD{
					  @override
					  String uri;
					  @override
					  add(String data) {
					    print('这是mssql的add方法'+data);
					  }
					
					  @override
					  delete() {
					     return null;
					  }
					
					  @override
					  save() {
					     return null;
					  }



                      //实现接口Db
                      class Mysql implements Db，DD{
  
							  @override
							  String uri;
							
							  Mysql(this.uri);
							
							  @override
							  add(data) {   
							    print('这是mysql的add方法'+data);
							  }
							
							  @override
							  delete() {   
							    return null;
							  }
							
							  @override
							  save() {   
							    return null;
							  }
							
							  
							}
         



  十三:  with 类


       mixins的中文意思是混入，就是在类中混入其他功能。

		在Dart中可以使用mixins实现类似多继承的功能


		因为mixins使用的条件，随着Dart版本一直在变，这里讲的是Dart2.x中使用mixins的条件：
		
		  1、作为mixins的类只能继承自Object，不能继承其他类
		      (如果一个mixins类 集成了父类,那么 这个mixins类 就不能被with了 会报错)
		  2、作为mixins的类不能有构造函数
		  3、一个类可以mixins多个mixins类
		  4、mixins绝不是继承，也不是接口，而是一种全新的特性,使用关键字with，类似多继承
		      一个类with多个类,那么这个类 可以overide 多个类里面的方法
		  	  
		  5.如果 集成 with的 多个类 中有相同的方法,最终输出 最后一个类的方法的结果	

          6.mixins的类型就是其超类的子类型。

  
              var sun = new Sun("臧三",20);
			  print(sun.infoA); // 我是 公有 info A
			  print(sun._infoA); //我是 私有 info A
			  sun.printInfoA(); //臧三----20---我是 私有 info A
			  sun.printA(); //我是 A
			  sun.printB(); //我是 B
			  sun.printC(); // 我是 C   实现C类的printC方法      


      

           					 class Sun extends A with B,C{
								  Sun(String name, num age) : super(name, age);
								
								  @override
								  void printInfoA() {
								    return super.printInfoA();
								  }
								   @override
								  void printA() {
								    super.printA();
								  }
								
								  @override
								  void printB() {
								    super.printB();
								  }
								
								 @override
								  void printC() {
								    super.printC();
								    print("实现C类的printC方法");
								  }
								
								}
								
								abstract class A{
								  String _name;
								  num _age;
								  A(this._name,this._age);
								  String infoA= "我是 公有 info A";
								  String _infoA= "我是 私有 info A";
								   void  printInfoA(){
								    print('${this._name}----${this._age}---${this._infoA}');
								  }
								  void printA(){
								    print(" 我是 A");
								}
								}
								
								
								abstract class B {
								
								  void printB(){
								    print("我是 B");
								  }
								}
								
								
								abstract class C {
								
								  void printC(){
								    print("我是 C");
								  }
								}
								
	


十四:  泛型

     泛型就是解决 类 接口 方法的复用性、以及对不特定数据类型的支持(类型校验)  	



    14.1 泛型方法
           // 对传入参数T类型  进行校验  对返回数据T类型 进行校验
			   T getData<T>(T value){
			       return value;
			   }

       		 //只对 对传入参数T类型  进行校验
				  getData<T>(T value){
				      return value;
				  }



    14.2 泛型类  
          把下面类转换成泛型类，要求List里面可以增加int类型的数据，也可以增加String类型的数据。但是每次调用增加的类型要统一

              PrintClass p=new PrintClass<String>();
              PrintClass p=new PrintClass<int>(); 

                   class PrintClass<T>{
					      List list=new List<T>();
					      void add(T value){
					          this.list.add(value);
					      }
					      void printInfo(){          
					          for(var i=0;i<this.list.length;i++){
					            print(this.list[i]);
					          }
					      }
					 }


    14.3 泛型接口  
           1、定义一个泛型接口 约束实现它的子类必须有getByKey(key) 和 setByKey(key,value)

           2、要求setByKey的时候的value的类型和实例化子类的时候指定的类型一致



      			  abstract class Cache<T>{
					  getByKey(String key);
					  void setByKey(String key, T value);
					}
     


                   class MemoryCache<T> implements Cache<T>{
						  @override
						  getByKey(String key) {   
						    return null;
						  }
						
						  @override
						  void setByKey(String key, T value) {
						       print("我是内存缓存 把key=${key}  value=${value} -写入到了内存中");
						  }
						}
          			




                 MemoryCache m=new MemoryCache<String>();

                    m.setByKey('index', '首页数据');	 




十五:库的导入 

   1. 本地自定库的导入 
     import 'lib/xxx.dart';
     import 'lib/Person2.dart';
    




   2.  系统内置库导入
          import 'dart:math';    
          import 'dart:io'; 
          import 'dart:convert';






   3. 第三方 Pub包管理系统中的库的导入(第三库的依赖)(类似kotlin 的implmention )
         https://pub.dev/packages  //搜索别人写好上传的第三方的包 进行下载
         https://pub.flutter-io.cn/packages
         https://pub.dartlang.org/flutter/

        1、需要在自己想项目根目录新建一个pubspec.yaml(android studio 已经帮我们自动建好了该文件 )
        2、在pubspec.yaml文件 然后配置名称 、描述、依赖等信息
               				name: flutter_app //as 自动添加了
                            description: A new Flutter application //as 自动添加了
							 dependencies:
							  http: ^0.13.2 //第三库的依赖版本http(https://pub.dev/packages/http/install)
							  dependencies:
                               date_format: ^2.0.2   //第三方日期格式化依赖库  (https://pub.dev/packages/date_format/install) 
            
        3、然后打开dos窗口,找到自己项目的路径   E:\Android\AndroidAPP\myApp\flutter_app
       输入 flutter/dart pub get  获取第三方库包下载到自己的项目的本地  
        4、在我的项目中导入第三库: 
        import 'dart:convert' as convert;   //系统内置的库   //as 对此库进行重命名 防止冲突
        import  'package:http/http.dart' as http; //引入第三方库hhtp库  //as 对此库进行重命名 防止冲突
        import 'package:date_format/date_format.dart'; //引入第三方库date_format库 



        5. 详见官网:查找搜索每个需要依赖的第三方依赖,查看使用安装步骤:
            https://pub.dev/packages/http/install


   
        6.博客 关于Pub 命名的使用 
           https://www.cnblogs.com/lulushen/p/14150544.html

    
 
        7.导入包出现冲突的解决

         
		当引入两个库中有相同名称标识符的时候，如果是java通常我们通过写上完整的包名路径来指定使用的具体标识符，甚至不用import都可以，但是Dart里面是必须import的。当冲突的时候，可以使用as关键字来指定库的前缀。如下例子所示：
         

        import 'package:lib1/lib1.dart';
       import 'package:lib2/lib2.dart' as lib2; //as 对此库进行重命名 防止冲突


       Element element1 = new Element();           // Uses Element from lib1.
       lib2.Element element2 = new lib2.Element(); // Uses Element from lib2.


     	 import 'lib/Person1.dart';
		 import 'lib/Person2.dart' as lib;

         Person p1=new Person('张三', 20);
		  p1.printInfo();
		
		
		  lib.Person p2=new lib.Person('李四', 20);
		
		  p2.printInfo();




     8. 对第三方库进行延迟加载

        也称为懒加载，可以在需要的时候再进行加载。
        懒加载的最大好处是可以减少APP的启动时间。
        懒加载使用deferred as关键字来指定，如下例子所示：

        import 'package:deferred/hello.dart' deferred as hello;

         当需要使用的时候，需要使用loadLibrary()方法来加载：

          greet() async {
          await hello.loadLibrary(); 
          hello.printGreeting();
         }
   

十六:网络库框架

      1.async和await关键字的使用 
        async是让方法变成异步;await是等待异步方法执行完成
     
       这两个关键字的使用只需要记住两点：
       只有在async方法才能使用await关键字调用异步方法
       如果调用别的async异步方法必须使用await关键字进行调用异步方法
   
          
       void main() async{
			  var result = await testAsync();
			  print(result);

              var result2 = await getDataFromZhihuAPI();
              print(result2);
			
			}
			
			//异步方法
			testAsync() async{
			  return 'Hello async';
			}



          //api接口： http://news-at.zhihu.com/api/3/stories/latest
				getDataFromZhihuAPI() async {
				  //1、创建HttpClient对象
				  var httpClient = new HttpClient();
				  //2、创建Uri对象
				  var uri = new Uri.http('news-at.zhihu.com','/api/3/stories/latest');
				  //3、发起请求，等待请求
				  var request = await httpClient.getUrl(uri);
				//4、关闭请求，等待响应
				  var response = await request.close();
				  //5、解码响应的内容
				  return await response.transform(utf8.decoder).join();
				
				}
                   