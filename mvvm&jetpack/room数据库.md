

一:Room 倒入库 

 dependencies {
  def room_version = "2.2.6"

  implementation "androidx.room:room-runtime:$room_version"
  kapt "androidx.room:room-compiler:$room_version"

  // optional - Kotlin Extensions and Coroutines support for Room
  implementation "androidx.room:room-ktx:$room_version"

  // optional - Test helpers
  testImplementation "androidx.room:room-testing:$room_version"
}



二:ROOM的三个重要组件


第一个组件:Database：包含数据库持有者，并充当与应用程序持久化的、关系型的数据的底层连接的主要访问点。
   
 **@Database**注解的类应满足以下条件：

    是一个继承RoomDatabase的抽象类。

    在注释中包含与数据库相关联的实体列表。

    包含一个具有0个参数的抽象方法，并返回用@Dao注释的类。


    通过调用Room.databaseBuilder()或Room.inMemoryDatabaseBuilder()获取数据库实例。



		@Database(entities = {User.class}, version = 1)
		public abstract class AppDatabase extends RoomDatabase {
		    public abstract UserDao userDao();
		}

    在创建上面的文件之后，使用以下代码获得创建数据库的实例：
    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
        AppDatabase.class, "database-name").build();

	注意：在实例化AppDatabase对象时，应遵循单例设计模式，因为每个Roomdatabase实例都相当消耗性能，并且您很少需要访问多个实例。

第二个组件:

**@Entity**：表示数据库中的表。

Room为实体中定义的每个字段创建一个列。如果实体有不想持久的字段，则可以使用**@Ignore**来注解它们。必须通过Database类中的entities数组引用实体类。


每个实体必须定义至少1个字段作为主键。即使只有1个字段，仍然需要用@PrimaryKey注解字段。此外，如果您想Room自动分配IDs给实体，则可以设置@ PrimaryKey的autoGenerate属性。如果实体具有复合主键，则可以使用@Entity注解的primaryKeys属性，

	
默认情况下，Room使用类名作为数据库表名。如果希望表具有不同的名称，请设置@Entity注解的tableName属性


Room使用字段名称作为数据库中的列名。如果希望列具有不同的名称，请将@ColumnInfo注解添加到字段中

数据库中的某些字段或字段组必须是唯一的。可以通过将@Index注解的唯一属性设置为true来强制执行此唯一性属性。


表对应的实体类的字段的注解关键字:
**tableName**：设置表名，如果未设置，默认为类名




**@PrimaryKey**：设置单主键。每个实体必须将至少1个字段定义为主键。即使只有1个字段，您仍然需要为该字段添加 @PrimaryKey 注释。每个{@Entity}必须声明一个主键，除非它的一个超类声明了主键。如果{@Entity}及其超类都定义了{@PrimaryKey}，则子类的{@PrimaryKey}定义将覆盖父项的{@PrimaryKey}。  
 如果您想让 Room 为实体分配自动 ID，则可以设置 @PrimaryKey 的 autoGenerate = true 属性。
     @PrimaryKey(autoGenerate = true)
     var uid = 0

**primaryKeys()**:设置复合主键(多个主键 )(如何表中每一个字段都可能重复，无法使用单一字段作为主键，这时我们可以将多个字段设置为复合主键，由复合主键标识唯一性)
     primaryKeys = {"firstName", "lastName"}



**@ColumnInfo ** ：设置数据库列名称，使用@ColumnInfo 注释添加到字段
**@Ignore** : Room 会为实体类中定义的每个字段创建一个数据库表中的对应的列,如果不想保留的字段,忽略此字段,添加@ignoredColumns 就不会在表中产生对应的列

**indices索引**:数据库索引用于提高数据库表的数据访问速度的,
   在@Entity添加indices属性来给表格添加索引
   数据库里面的索引有单列索引和组合索引
   数据库中的某些字段或字段组必须是唯一的。您可以通过将 @Index 注释的 unique 属性设为 true，强制实施此唯一性属性。
     @Index(value={"address"},unique = true)
     @Index(value = {"first_name", "last_name"},unique = true)
 

User实体类通过@foreignKeys关联外表Book,之后User表中的bookId来源于Book表中的bid，


**@Embedded**:嵌套对象.User对象需要将多个对象组合成一个对象。对象和对象之间是有嵌套关系的
   那么User表包含的列有：id, firstName,address,bookId, street, state, city,  post_code,cname,cage,sex


			 @Entity(tableName = "users",primaryKeys = {"firstName", "lastName"},
             indices = {
               @Index(value={"address"},unique = true),
               @Index(value = {"first_name", "last_name"},unique = true)
                       }

           foreignKeys = [ForeignKey(entity = Book::class, parentColumns = ["bid"], childColumns = ["bookId"], onDelete = SET_DEFAULT)]

                    )
  
 
			 public class User {
		 		 		    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "rowid")
						    val id: Int
						
						    @ColumnInfo(name = "first_name")
						     val firstName: String?

						    @ColumnInfo(name = "last_name")
						    val lastName: String?

						      @ColumnInfo(name = "addressed")  
                              val address:String?

						    @Ignore
						    val picture: Bitmap?

                            var bookId: Int = 0


                           @Embedded
			               val address:Address?

                           @Embedded
			               val child:Child

	            	}



**@foreignKeys**：设置外键。SQLite是关系形数据库，表和表之间是有关系的。通过foreignKeys来建立表与表之间的关系。
定义对象之间的关系
Book实体类通过@foreignKeys关联外表User,之后Book表中的userId来源于User表中的id，
Book实体类通过@foreignKeys关联外表Child,之后Book表中的childId来源于Child表中的cid，  
		  

       @Entity(foreignKeys = @ForeignKey(entity = User.class,
		                                  parentColumns = "id",
		                                   childColumns = "user_id"))
		@Entity(foreignKeys = @ForeignKey(entity = Child.class,
		                                  parentColumns = "cid",
		                                   childColumns = "childId"))
		public class Book {
				    @PrimaryKey
				    val bid: Int
				    val name: String
				    val price: Double


				    @ColumnInfo(name = "user_id")
				    val userId:Int;

                   
                   val childId:Int
							
                           @Embedded
			               val address:Address?
				}





当前实体类中创建嵌套对象

使用@Embedded 表示当前实体类中的一个字段为嵌套的实体类

   data class Child(
				    val cid: Int,
				    val cname: String,
				    val cAge: Int,
				    val sex: Int
				)

       	 
          data class Address {
			     val street: String?,
                 val state: String?,
               val city: String?,

			
			    @ColumnInfo(name = "post_code")
			    val postCode: Int

			}




第三个组件:

@DAO: 每个DAO都包含了对应用程序数据库访问的抽象方法
DAO既可以是接口，也可以是抽象类。	。如果是抽象类，它可以有一个构造函数，它把RoomDatabase作为唯一的参数。Room在编译时创建每个DAO实现。
Room不支持在主线程上访问数据库,
查询数据库返回LiveData或Flowable 自带异步查询查询功能

查询
 @Query是DAO类中使用的主要注解。它允许您在数据库上执行读/写操作。

将参数传递到查询中
大多数情况下，需要将参数传递到查询中以执行筛选操作，例如只显示年龄大于某一年龄的用户。


可观察的查询 查询返回 LiveData<List<User>>  集合
    @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
    public LiveData<List<User>> loadUsersFromRegionsSync(List<String> regions);

RXJava的响应式查询  查询返回 Publisher和Flowable动对象
    @Query("SELECT * from user where id = :id LIMIT 1")
    public Flowable<User> loadUserById(int id);


插入
@Insert  Room生成一个实现，在一个事务中将所有参数插入到数据库中。

更新
Update方法在数据库中用于修改一组实体的字段。

删除

@Delete方法用于从数据库中删除给定参数的一系列实体，

        @Dao
		public interface UserDao {
		    @Query("SELECT * FROM user")
		    List<User> getAll();
		
		   @Query("SELECT * FROM user WHERE age > :minAge")
          public User[] loadAllUsersOlderThan(int minAge);


		  @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
		  public User[] loadAllUsersBetweenAges(int minAge, int maxAge);

          
            @Query("SELECT * FROM user WHERE first_name LIKE :search "
             + "OR last_name LIKE :search")
            public List<User> findUserWithName(String search); 


          @Query("SELECT * FROM user WHERE uid IN (:userIds)")
		    List<User> loadAllByIds(int[] userIds);
		
		    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
		           + "last_name LIKE :last LIMIT 1")
		    User findByName(String first, String last);
		

            @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
           public LiveData<List<User>> loadUsersFromRegionsSync(List<String> regions);



		   
             @Query("SELECT * from user where id = :id LIMIT 1")
              public Flowable<User> loadUserById(int id);


			 @Query("SELECT * FROM book "
			           + "INNER JOIN loan ON loan.book_id = book.id "
			           + "INNER JOIN user ON user.id = loan.user_id "
			           + "WHERE user.name LIKE :userName")
			   public List<Book> findBooksBorrowedByNameSync(String userName);



            @Insert(onConflict = OnConflictStrategy.REPLACE)
		    public void insertUsers(User... users);
		
		    @Insert
		    public void insertBothUsers(User user1, User user2);
		
		    @Insert
		    public void insertUsersAndFriends(User user, List<User> friends);
            
            @Insert
		    void insertAll(User... users);


			 @Update
			 public void updateUsers(User... users);
		
		      @Delete
              public void deleteUsers(User... users);
		}
