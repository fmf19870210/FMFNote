一：什么是Koin
Koin是适用于Kotlin的轻量级注入工具。无代理、无代码生成、无反射，所以性能比较好。


二:koin 依赖
     koin_version = '2.2.2'//koin依赖注入框架
    // Koin for Kotlin
    implementation "org.koin:koin-core:$koin_version"
    implementation "org.koin:koin-core-ext:$koin_version"

    // Koin for AndroidX
    implementation "org.koin:koin-androidx-scope:$koin_version"
    implementation "org.koin:koin-androidx-viewmodel:$koin_version"
    implementation "org.koin:koin-androidx-fragment:$koin_version"
    implementation "org.koin:koin-androidx-ext:$koin_version"

    testImplementation "org.koin:koin-test:$koin_version"



三:  在`Application`调用`stratKoin`内部初始化`modules`
       通过调用startKoin来启动koin,里面填注入对象.
  	  
         //koin的注解初始化
        startKoin {
            androidLogger(Level.ERROR)//目前已知bug，除了level.error外，使用androidlogger会导致崩溃
            //context
            androidContext(this@CnApplication)
             //assets 资源数据
             //默认名字为koin.properties,你也可以直接重新设置名称
             //默认取值assets下koin.properties文件内的属性配置，可自定义
            androidFileProperties("ass.file")
            //加载需要的module
            modules(cnModules)
        }

   



四:需要加载的module的描述:
			
			     val cnModules = module {
			
			    //fixme 不要空声明，会报错
			//    //单例模式
			//    single { }
			    single(createdAtStart = false) { CnPerson() }/* bind DashboardFragment::class*/
			    //bind的意思就是，可以通过get<DashboardFragment>（）
			//    //工厂模式，就是将创建过程眼不见为净
			
			    //覆盖声明
			    factory(override = true) { CnPerson() }
			
			    //多构造函数
			    factory(named("name")) { CnStudent("haha") }
			    factory(TypeQualifier(CnPerson::class)) { CnStudent(get<CnPerson>()) }
			
			    //接收外部参数的形式
			    factory { (view: View) ->
			        ViewInfo(view)
			    }
			
			    //viewModel
			    viewModel { DashboardViewModel() }
			    viewModel { HomeViewModel() }
			//    viewModel { NotificationsViewModel() }
			
			
			//    fragment { HomeFragment() }
			
			} 



  五:  module中的关键函数符号
  (1)single 	生成单一对象CnPerson   	
           
          single(createdAtStart = false) { CnPerson() }

         
            调用单一对象CnPerson:
              通过 by inject()方法获取单一对象CnPerson
                   //必须是val  变量没给定类型，需要在inject中使用泛型
                   val p  by inject<CnPerson>()//lazy 模式 
                   //必须是val 如果已经给定了类型inject不用使用类型
                   val p: CnPerson by inject () 

 
              
              通过get<T>()方法获取单一对象CnPerson: var 和val都行
               val aa = get<CnPerson>()


  (2) factory 	每次都会生成新的对象CnPerson

           
           //覆盖声明
         factory(override = true) { CnPerson() }
          //多构造函数
          factory(named("name")) { CnStudent("haha") }




        获取新生对象CnPerson: 	 
          val ss = get<CnStudent>(named("name"))
          val ss2 = get<CnStudent>(qualifier<CnPerson>())
          val s: CnStudent by inject<CnStudent>(named("name"))  



 (3)viewModel 	用来创建ViewModel实例，默认生成的都是新对象 

       
       viewModel { DashboardViewModel() }
       viewModel { HomeViewModel() }




       通过by viewModel()//getViewModel()来获取的ViewModel是不同的对象:
	     
       private val dashboardViewModel: DashboardViewModel by viewModel()
       private  val homeViewModel = getViewModel<HomeViewModel>()




(4)fragment  用来创建fragment对象  	
       fragment { HomeFragment() }
       fragment { MyFragment("方明飞啊") }


     通过by inject()获取该fragment 对象
     val myFragment:MyFragment by inject () 
         


		    class MyFragment (val str: String) : Fragment(){
		   override fun onCreateView(
		        inflater: LayoutInflater,
		        container: ViewGroup?,
		        savedInstanceState: Bundle?
		    ): View? {
		        CSKoinLog.I("传递给碎片MyFragment的参数:"+str)  // 传递给碎片MyFragment的参数:方明飞啊
		        return inflater.inflate(R.layout.fragment_my, container, false)
		    }
		
		}

 


(5) 多构造函数，需要使用`qualifiers`限定符号  


 
     





     案例一:
     //多构造函数
    factory(named("name")) { CnStudent("haha") }
    factory(TypeQualifier(CnPerson::class)) { CnStudent(get<CnPerson>()) }
  
      调用多构造函数CnStudent:
      //多构造函数的注入使用
      val s: CnStudent by inject<CnStudent>(named("name"))
      val ss = get<CnStudent>(named("name"))
      val ss2 = get<CnStudent>(qualifier<CnPerson>()) 
     

    案例二:


        //传递了两个参数
        factory(named("double")) { Person(get(),get()) }
        //传递了一个参数
        factory(named("single")) { Person(get()) }


         调用多构造函数Person:
	     val person:Person by inject(named("single"))
	     val person2:Person by inject(named("double"))


   

(6)  外部参数的构造函数，初始化可用表达式构造如：`(view:View)->ViewInfo(view)`，使用方get/inject时候，接收参数使用`parametersOf()`、、最多5个参数 

    案例一:
     在module里初始化外部参数的构造函数ViewInfo 

       factory { (view: View) -> ViewInfo(view) }
   
				    


           //外部参数演示
				class ViewInfo(val view: View) : KoinComponent {
				
				    val s: CnStudent by inject<CnStudent>(named("name"))
				    val p = get<CnPerson>()
				
				    fun showId() {
				        Log.d("ViewInfo", "show view的id ${view.id} p $p  s ${s.name}")
				    }
				} 



        
      
     通过通过parametersOf()传递参数进行调用:

		    //外部参数
		    var tv: TextView? = null//别都懒      
		    val viewInfo by inject<ViewInfo> { parametersOf(tv) }



        viewInfo.showId()

  案例二:
      factory{ (girl:Girl) ->Person(girl) }

           
		   class Person: KoinComponent {
			    val girl by inject<Girl>()
			    fun speak(){
			        println("我的女朋友是${girl.name}")
			    }
			}





	 val girl by inject<Girl>()
			    val person:Person by inject(){
			      //通过parametersOf()传递参数
			        parametersOf(girl)
			    }  







          person.speak()






（7） 我们大多数是在Activity/fragment中   获取使用注入inject对象,
   那么如何在普通类 获取使用注入inject对象呢？    此普通类需要实现`KoinComponent`接口
		class CompontData : KoinComponent {
		   val  appD1 by inject<AppData>() //懒加载模式获取注入对象AppData
		    val appD2 = get<AppData>()//非懒加载模式 获取注入对象AppData
		    fun  printInjectInfo(){
		       CSKoinLog.I("普通类CompontData中输出注入对象appD1地址信息:" + appD1.hashCode() + "////输出注入对象appD2地址信息:" + appD2.hashCode())
		   }
		
		}
 
     //普通类CompontData中输出注入对象appD1地址信息:262823653////输出注入对象appD2地址信息:244473274
      CompontData().printInjectInfo() 
     



 


(8)Activity/Fragment默认扩展了scope字段/Scope作用域的使用  

   什么是Scope作用域,这个东西其实跟viewModel有点相似,scope下的对象可以跟一个视图绑定起来,并且该被绑定的对象是单例的模式,其他界面通过scopeId可以获取这个对象.当该视图被销毁的时候,被绑定的对象也会被销毁.其他界面也就获取不到这个scope对象了.
   
   之前我们学习的single只能创建一个单例对象，factroy每次都创建新的对象。Scope的出现能把不同作用域内的对象作为一个对象，比如说我们想在Activity1和Activity2中的person是一个单例，Activity3和activity4中的Person是一个单例。我们就可以使用Scope来实现

 

(9)  跨Module模块如何注入inject依赖

 如果项目中有多个Module依赖,那么如何调用其他Module中注入的对象呢？



(10)Koin调用assets资产文件夹下的文件koin.properties(默认自动生成的文件),并读取文件里的内容数据


  