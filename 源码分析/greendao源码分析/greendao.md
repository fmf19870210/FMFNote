




 一:GreenDao 的三个核心类

 		 DaoMaster:(类似于SQLiteDatabase)
            DaoMaster 保存数据库对象（SQLiteDatabase）并管理特定模式的 DAO 类（而不是对象）。它有静态方法来创建表或删除它们。它的内部类 OpenHelper 和DevOpenHelper 是 SQLiteOpenHelper 实现，它们在 SQLite 数据库中创建模式。                 
         
 		 DaoSession：管理特定模式的所有可用 DAO 对象，您可以使用其中一个getter方法获取该对象。DaoSession 还提供了一些通用的     			 持久性方法，如实体的插入，加载，更新，刷新和删除。

		XXXDao(StudentDao)：数据访问对象（DAO）持久存在并查询实体。对于每个实体，greenDAO生成DAO。它具有比DaoSession更多的持久性方法，例如：count，loadAll和insertInTx。

		Entities ：可持久化对象。通常, 实体对象代表一个数据库行使用标准 Java 属性(如一个POJO 或 JavaBean )
         


二: 导入Gradle插件和Dao代码生成
    
   1.导入插件

			    // 在 Project的build.gradle 文件中添加:
			     buildscript {
			    repositories {
			        jcenter()
			        mavenCentral() // add repository
			    }
			    dependencies {
			        classpath 'com.android.tools.build:gradle:3.1.2'
			        classpath 'org.greenrobot:greendao-gradle-plugin:3.2.2' // add plugin
			    }
			    }



  2.配置相关依赖
   			// 在 Moudle:app的  build.gradle 文件中添加:
		apply plugin: 'com.android.library'
         apply plugin: 'org.greenrobot.greendao
		 
		dependencies {
		    implementation 'org.greenrobot:greendao:3.2.2' // add library
		}


 
 3.配置数据库相关信息

  
greendao {
 schemaVersion 1 //数据库版本号
 daoPackage 'com.androidtv.greendaodemo.dao'  // 设置DaoMaster、DaoSession、Dao 包名
 targetGenDir 'src/main/java'//设置DaoMaster、DaoSession、Dao目录
}


三:  创建存储对象实体类  Student    以及实体类对应使用的注解 

   三一: 
         注意:使用GreenDao存储数据只需要在存储数据类Student前面声明@Entity注解
        ,然后点击build->make project 实体类Student 就会自动生成GreenDao为其生成必要的代码：
         比如 带参数的构造方法Student(....)，为对应的字段属性生成get() set()方法。
         同时在dao目录下生成DaoMaster DaoSession StudentDao 三个核心类    
         


    			@Entity
				public class Student {
				    @Id(autoincrement = true)
				    Long id;
				    @Unique
				    int studentNo;//学号
				    int age; //年龄
				    String telPhone;//手机号
				    String sex; //性别
				    String name;//姓名
				    String address;//家庭住址
				    String schoolName;//学校名字
				    String grade;//几年级
				    ……getter and setter and constructor method……
				    }

   三二: 注解讲解

       1.@Entity注解
          @Entity是GreenDao必不可少的注解，只有在实体类中使用了@Entity注解GreenDao才会创建对应的表。当然我们也可以使用@Entity配置一些细节： 表明这个实体类会在数据库中生成一个与之相对应的表。

          schema：如果你有多个架构，你可以告诉GreenDao当前属于哪个架构。
           active：标记一个实体处于活跃状态，活动实体有更新、删除和刷新方法。
           nameInDb：在数据中使用的别名，默认使用的是实体的类名。
          createInDb：标记创建数据库表。
          generateGettersSetters：如果缺少，是否应生成属性的getter和setter方法。

				          @Entity(
				
				        schema = "myschema",
				        active = true,
				        nameInDb = "AWESOME_USERS",
				        indexes = {
				                @Index(value = "message DESC", unique = true)
				        },
				        createInDb = false,
				        generateConstructors = true,
				        generateGettersSetters = true
						)
						public class Student{	
							……
						}

      2.基础属性注解

        @Id
		@Id注解选择 long / Long属性作为实体ID。在数据库方面，它是主键。参数autoincrement = true 表示自增，id不给赋值或者为赋值为null即可（这里需要注意，如果要实现自增，id必须是Long,为long不行！)。

        @Entity
		public class Student {
		    @Id(autoincrement = true)
		    Long id;
		    ……
		}


     
      @Property
       可以自定义字段名，注意外键不能使用该属性
      允许您定义属性映射到的非默认列名。如果不存在，GreenDAO将以SQL-ish方式使用字段名称（大写，下划线而不是camel情况，例如 name将成为 NAME）。注意：您当前只能使用内联常量来指定列名。
        @Property(nameInDb = “STUDENTNUM”) 　表名这个属性对应数据表中的 STUDENTNUM 字段。  




       @Entity
		public class Student {
	    @Id(autoincrement = true)
	    Long id;
	    @Property (nameInDb="name") //设置了，数据库中的表格属性名为"name",如果不设置，数据库中表格属性名为"NAME"
	    String name;
	    ……
		}


      @NotNull ：设置数据库表当前列不能为空 。 该属性值不能为空

     @Transient ：该属性不会被存入数据库中。添加次标记之后不会生成数据库表的列。标记要从持久性中排除的属性。将它们用于临时状态等。或者，您也可以使用Java中的transient关键字。


     3.索引注解
     @Index：使用@Index作为一个属性来创建一个索引，通过name设置索引别名，也可以通过unique给索引添加约束。 
     @Unique：表名该属性在数据库中只能有唯一值。向索引添加UNIQUE约束，强制所有值都是唯一的。


       @Entity
		public class Student {
	    @Id(autoincrement = true)
	    Long id;
	    @Property(nameInDb="name")
	    @Index(unique = true)
	     String name;
	    ……
		}



     4.关系注解
      @ToOne：定义与另一个实体（一个实体对象）的关系
      @ToMany：定义与多个实体对象的关系




 四: GreenDao初始化 

在Application中维持一个全局的会话。我们在Applicaiton进行数据库的初始化操作：

		 /**
		     * 初始化GreenDao,直接在Application中进行初始化操作
		     */
		    private void initGreenDao() {
		        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "aserbao.db");
		        SQLiteDatabase db = helper.getWritableDatabase();
		        DaoMaster daoMaster = new DaoMaster(db);
		        daoSession = daoMaster.newSession();
		    }
		    
		    private DaoSession daoSession;
		    public DaoSession getDaoSession() {
		        return daoSession;
		    }
		
		
		
五:使用GreenDao实现增删改查

     1. 增
       insert() 插入数据
        insertOrReplace() 数据存在则替换，数据不存在则插入

    2. 删
    delete()和deleteAll()；分别表示删除单个和删除所有

    3.update() 对数据库存储的bean实体对象进行属性修改
               daoSession.getStudentDao().update(s);
                mDataBaseAdapter.notifyItemChanged(position,s);
     
     updateInTx(list)   批量更新数据集合  
               
              List<Student>  studentListFromDB=  daoSession.getStudentDao().loadAll();
		        for (Student student : studentListFromDB) {
		            student.setName("方明飞2");
		        }
		        daoSession.getStudentDao().updateInTx(studentListFromDB);
		        mDataBaseAdapter.notifyDataSetChanged();


    4.loadAll()：查询所有数据。
      queryRaw()：根据条件查询。
       queryBuilder() : 方便查询的创建



    5.查询  
		loadAll()：查询所有数据。
		queryRaw()：根据条件查询。
		queryBuilder() : 方便查询的创建， 


    6.QueryBuilder的使用

      where(WhereCondition cond, WhereCondition… condMore):
       查询条件，参数为查询的条件！

     or(WhereCondition cond1, WhereCondition cond2, WhereCondition… condMore): 
     嵌套条件或者，用法同or。
      
        
       and(WhereCondition cond1, WhereCondition cond2, WhereCondition… condMore): 
       嵌套条件且，用法同and。


      join(Property sourceProperty, Class destinationEntityClass):多表查询，

      输出结果有四种方式，选择其中一种最适合的即可，list()返回值是List,而其他三种返回值均实现Closeable,需要注意的不使用数据时游标的关闭操作：


     list （）
       所有实体都加载到内存中。结果通常是一个没有魔法的 ArrayList。最容易使用。

     listLazy （）
      当你需要使用时，才会加载，会自动缓存。使用完必须关闭。实体按需加载到内存中。首次访问列表中的元素后，将加载并缓存该元素以供将来使用。必须关闭。


    listLazyUncached （）
    不会缓存的意思。使用完必须关闭。   实体的“虚拟”列表：对列表元素的任何访问都会导致从数据库加载其数据。必须关闭。

    listIterator （）
    通过迭代器遍历结果集，不会缓存。使用完必须关闭。 让我们通过按需加载数据（懒惰）来迭代结果。数据未缓存。必须关闭。



  7.GreenDao中SQL语句的缩写

		   eq()：“equal (’=?’)” 等于；

           otEq() ：“not equal (’<>?’)” 不等于；

           like()：" LIKE ?" 值等于；

          between()：" BETWEEN ? AND ?" 取中间范围；


          in()：" IN (" in命令;


          notIn()：" NOT IN (" not in 命令;

          gt()：">?" 大于;
          ge()：">=?" 大于等于; 

          lt()："<? " 小于;
          le()："<=? " 小于等于;


          isNull()：" IS NULL" 为空;
          isNotNull()：" IS NOT NULL" 不为空;


          orderAsc() 按某个属性升序排；
          orderDesc() 按某个属性降序排；


          offset(i)表示往后偏移i个数
           offset(2)表示往后偏移2个 

          
           limit(10)  限制取多少条数据


          

            
   7.QueryBuilder进行批量删除操作

使用QueryBuilder进行批量删除操作，不会删除单个实体，但会删除符合某些条件的所有实体。要执行批量删除，请创建QueryBuilder，调用其 buildDelete （）方法，然后执行返回的 DeleteQuery。

        QueryBuilder<Student> qb =daoSession.queryBuilder(Student.class);
        QueryBuilder<Student> studentQueryBuilder4 =   qb.where(StudentDao.Properties.Name.eq("方明飞2"),
                StudentDao.Properties.Id.gt(5)
                   );
         DeleteQuery<Student> deleteQuery =   studentQueryBuilder4.buildDelete();  //批量删除 id>5的数据
         deleteQuery.executeDeleteWithoutDetachingEntities();



        List<Student> list4 =  daoSession.queryBuilder(Student.class).list(); //获取剩余的数据集合
        studentArrayList.clear();
        studentArrayList.addAll(list4);
        mDataBaseAdapter.setData(studentArrayList);
         mDataBaseAdapter.notifyDataSetChanged();






  8. 批量更新

      QueryBuilder<Student> qb =daoSession.queryBuilder(Student.class);
        QueryBuilder<Student> studentQueryBuilder =  qb.where(StudentDao.Properties.Name.eq("方明飞2"),
                qb.and(StudentDao.Properties.Id.gt(5),StudentDao.Properties.Id.le(10))
        ).orderAsc(StudentDao.Properties.Id);


        List<Student> studentList =studentQueryBuilder.list();

        for (Student student : studentList) {
            student.setName("哈哈");
        }

          daoSession.getStudentDao().updateInTx(studentList); //批量更新
          studentArrayList.clear();
          studentArrayList.addAll(studentList);
         mDataBaseAdapter.setData(studentArrayList);
         mDataBaseAdapter.notifyDataSetChanged();





9.批量插入
    同上    daoSession.getStudentDao().insertInTx(studentList); //批量插入



10. 批量删除
     同上    daoSession.getStudentDao().deleteInTx(studentList); //批量删除
   
 







六:   一对一，一对多，多对多关系表的创建(这里暂不详细学习,项目暂时没涉及到)


   六一:一对一
        一个学生对应一个身份证号:

     做法：

     1.我们在Student中设置一个注解@ToOne(joinProperty = “name”)
     2.在创建Student的时候，将对应的数据传递给IdCard;

        学生Student代码：
                @Entity
				public class Student {
				    @Id(autoincrement = true)
				    Long id;
				    @Unique
				    int studentNo;//学号
				    int age; //年龄
				    String telPhone;//手机号
				    String sex; //性别
				    String name;//姓名
				    String address;//家庭住址
				    String schoolName;//学校名字
				    String grade;//几年级
				    @ToOne(joinProperty = "name")
				    IdCard student;
				    ……getter and setter ……
				}
   

        身份证IdCard代码：

                    @Entity
					public class IdCard {
					    @Id
					    String userName;//用户名
					    @Unique
					    String idNo;//身份证号
					    @Generated(hash = 1028827110)
					    public IdCard(String userName, String idNo) {
					        this.userName = userName;
					        this.idNo = idNo;
					    }
					    @Generated(hash = 1500073048)
					    public IdCard() {
					    }
					    public String getUserName() {
					        return this.userName;
					    }
					    public void setUserName(String userName) {
					        this.userName = userName;
					    }
					    public String getIdNo() {
					        return this.idNo;
					    }
					    public void setIdNo(String idNo) {
					        this.idNo = idNo;
					    }
					   
					}

  
           insert一组数据：

          public void addStudent(){
						DaoSession daoSession = ((AserbaoApplication) getApplication()).getDaoSession();
 						Student student = new Student();
                        student.setStudentNo(i);
                        int age = mRandom.nextInt(10) + 10;
                        student.setAge(age);
                        student.setTelPhone(RandomValue.getTel());
                        String chineseName = RandomValue.getChineseName();
                        student.setName(chineseName);
                        if (i % 2 == 0) {
                            student.setSex("男");
                        } else {
                            student.setSex("女");
                        }
                        student.setAddress(RandomValue.getRoad());
                        student.setGrade(String.valueOf(age % 10) + "年纪");
                        student.setSchoolName(RandomValue.getSchoolName());
                        daoSession.insert(student);
						
						//插入对应的IdCard数据
                        IdCard idCard = new IdCard();
				        idCard.setUserName(userName);
				        idCard.setIdNo(RandomValue.getRandomID());
				        daoSession.insert(idCard);
        }




六二：一对多
       一个人拥有多个信用卡
    做法：

     1.在我们在Student中设置@ToMany(referencedJoinProperty = “studentId”);
     2.我们在CreditCard中设置编写对应的studentId主键；


     Student的代码

        @Entity
	    public class Student {
	    @Id(autoincrement = true)
	    Long id;
	
	    @Unique
	    int studentNo;//学号
	
	    int age; //年龄
	    String telPhone;//手机号
	    String sex; //性别
	    String name;//姓名
	    String address;//家庭住址
	    String schoolName;//学校名字
	    String grade;//几年级
	    
	    @ToMany(referencedJoinProperty = "id") // 这个id是对应在CreditCard中的id
	    List<CreditCard> creditCardsList;

      @ToMany(referencedJoinProperty = "studentId")  // 这个studentId是对应在CreditCard中的studentId字段属性
      List<CreditCard> creditCardsList;


	      ……getter and setter ……
	    }


  

      CreditCard的代码：
     CreditCard中不能只使用一个useId来做关联，因为我这里Teacher和Student都和CreditCard是一对多关系，所以我们需要建两个对应关系字段。为了分辨添加了studentId和teacherId。


       @Entity
		public class CreditCard {
	    @Id
	    Long id;
	    String studentId;   //可以用String
	    Long teacherId;
	    String userName;//持有者名字
	    String cardNum;//卡号
	    String whichBank;//哪个银行的
	    int cardType;//卡等级，分类 0 ~ 5 

         ……getter and setter ……
        }









七:数据库的升级

 		
      GreenDao的OpenHelper下有个 onUpgrade(Database db, int oldVersion, int newVersion)方法，当设置的数据库版本改变时，在数据库初始化的时候就会回调到这个方法，我们可以通过继承OpenHelper重写onUpgrade方法来实现数据库更新操作：
 




八. GreenDao数据库加密