一:dart /flutter  一些常用的命名


 flutter --version  ：查看fluttersdk 是否配置成功  以及版本号

 flutter doctor : 查看配置as 是否安装配置成功 ,本地配置情况   
 
flutter upgrade   :升级flutter SDK 
flutter clean ：   清楚缓存  同步
  


flutter 一些常见的异常错误:

 


 




二:flutter 加载本地图片

     1.加载本地图片 需要在目录创建assets/images/
        2.0X文件夹   放2.0X的图片
        3.0X文件夹   放3.0X的图片
        4.0X文件夹   放4.0X的图片
    注意:1.0x图 直接放在images目录文件家下 不需要创建1.0X文件夹


    2.在pubspec.yaml中 进行图片路径配置
     把assets注释打开(这块地方尤其要注意空格缩进,这个文件对空格缩进及其敏感)
    稍不注意就会加载不出来本地图片
       
        
     flutter:

      # The following line ensures that the Material Icons font is
      # included with your application, so that you can use the icons in
      # the material Icons class.
      uses-material-design: true

      # To add assets to your application, add an assets section, like this:
      assets:
        - assets/images/a.jpeg
  



三:ListView组件

        注意:
         1.水平的ListView的高度自适应,可以不设置
         注意当你的ListView是水平的时候, 最 外层层容器就不能是ListView,否则水平布局的ListView的高度默认会填充满整个布局手机屏幕
         所以我们需要在ListView外加个父容器Container,并指定这个父容器Container的具体高度,里面加个child ListView ，
           这样才能避免水平的ListView的高度填充满整个布局
         2.如果你的ListView是垂直的  宽度是自适应的 可以不设置



四:GridView组件+Padding组件

 		
        1.在GridView外面在嵌套一个Padding控件，来设置 右边 和下面的padding值，这样整体布局才对称好看      
        2.GridView 的 item Image 控件 没有padding 属性,所以需要在item Image外面嵌套一个Padding 组件
        虽然我们给 item Padding 设置了padding 10, 10, 0, 0  但是  右边 和下面的padding值没设置
        注意:这里我们不能给每个item 的控件Padding设置右边 和下面的padding值 界面显示布局效果不对称
        这时我们需要在最外层给GridView嵌套一个Padding控件来设置 右边 和下面的padding值 见 LayoutDemo_padding_1




五:Row水平布局组件(使用最多)
    mainAxisAlignment: MainAxisAlignment.spaceEvenly
       调整child: Row在父容器Container的主轴(水平X轴)方向的位置(以父容器Container为参照物):left center end   spaceEvenly 水平剧中散开,间距等分(用的最多)  spaceAround

    crossAxisAlignment: CrossAxisAlignment.center,
       调整child: Row在父容器Container的纵轴(垂直Y轴)方向的位置(以父容器Container为参照物):
       这个方法比较特殊看不出效果 需要在组件Row 外在嵌套一个组件Container容器  在设置才能看出效果



				class RowDemo  extends StatelessWidget{
				  @override
				  Widget build(BuildContext context) {
				      return new Container(
				        //Container容器宽 高 背影颜色
				          height: 400.0,
				          width: 370.0,
				          color: Colors.grey,
				          //Container容器的child:Row
				        child: Row(
				          //调整child: Row在父容器Container的主轴(水平X轴)方向的位置(以父容器Container为参照物):left center end   spaceEvenly 水平剧中散开,间距等分(用的最多)  spaceAround
				          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
				            //调整child: Row在父容器Container的纵轴(垂直Y轴)方向的位置(以父容器Container为参照物):
				            // 这个方法比较特殊看不出效果 需要在组件Row 外在嵌套一个组件Container容器  在设置才能看出效果
				            crossAxisAlignment: CrossAxisAlignment.center,
				          children: <Widget>[
				              IconContainer(Icons.search,containner_color:Colors.blue,iconColor: Colors.white),
				             IconContainer(Icons.home,containner_color:Colors.orange,iconColor: Colors.white),
				            IconContainer(Icons.select_all,containner_color:Colors.green,iconColor: Colors.white)
				          ]
				        )
				      );
				  }
				}



六:Column垂直布局组件(使用最多)

    与Row 一样


 


七:Expanded 这个组件很重要 类似Weight 可以水平 垂直 权重布局
  1. flex:设置布局权重的大小值

	    class ExpandedDemo  extends StatelessWidget{
		  @override
		  Widget build(BuildContext context) {
		      return new Container(
		        //Container容器 高400  宽自适应填充满屏幕 背影颜色
		           height: 400.0,
		       //   width: 370.0,
		          color: Colors.grey,
		          //Container容器的child:Row:有3个child:水平权重比flex  1：2：1
		        child: Row(
		            children: <Widget>[
		             Expanded(flex: 1,child:IconContainer(Icons.search,containner_color:Colors.blue,iconColor: Colors.white) ),
		             Expanded(flex: 2,child: IconContainer(Icons.home,containner_color:Colors.orange,iconColor: Colors.white)),
		            Expanded(flex: 1,child:  IconContainer(Icons.select_all,containner_color:Colors.green,iconColor: Colors.white))
		           ]
		        )
		      );
		  }
		}
    
  
    2.也可以 左边/右边组件设置具体的宽度值,剩余的宽度让另一个组件Expanded 设置flex:1 自适应占满剩余的宽度
     

     class ExpandedDemo2  extends StatelessWidget{
	  @override
	  Widget build(BuildContext context) {
	    return new Container(
	      //Container容器 高400  宽自适应填充满屏幕 背影颜色
	        height: 400.0,
	        //   width: 370.0,
	        color: Colors.grey,
	        //Container容器的child:Row:有3个child: 左边IconContainer  固定宽75.0  中间Expanded :宽自适应填充满(flex: 1)  右边IconContainer 固定宽45.0
	        //类似weight水平权重
	        child: Row(
	            children: <Widget>[
	              IconContainer(Icons.search,containner_color:Colors.blue,iconColor: Colors.white,containner_width:75.0),
	              Expanded(flex: 1,child: IconContainer(Icons.home,containner_color:Colors.orange,iconColor: Colors.white)),
	              IconContainer(Icons.select_all,containner_color:Colors.green,iconColor: Colors.white,containner_width:45.0)
	            ]
	        )
				    );
			  }
			} 












八:自定义组件IconContainer

		
		  class IconContainer extends StatelessWidget{
		  var icon;
		  double iconSize=32.0;
		  Color iconColor=Colors.red;
		  var containner_color=Colors.white;
		  var containner_height;
		  var containner_width;
		  IconContainer(this.icon,{this.containner_color,this.iconColor,this.iconSize,this.containner_height=100.0,this.containner_width=100.0});
		
		  @override
		  Widget build(BuildContext context) {
		
		
		    return Container(
		        height: this.containner_height,
		        width: this.containner_width,
		        color: this.containner_color,
		        child: Center(
		            child: Icon(this.icon,size:this.iconSize,color:this.iconColor)
		        )
		    );
		  }
		
		
		}




九:使用Row Culumn Expanded组件的一个练习Demo

         对一个整体的布局
        1.如果整体布局是上下 使用Column, 再用children: []  放所有的children， 然后再看每个children是什么布局:如果是上下布局,在使用Column组件,如果是水平 使用Row组件
        2.如果整体布局是水平 使用Row  ,再用children: []  放所有的children ， 然后再看每个children是什么布局:如果是上下布局,在使用Column组件,如果是水平 使用Row组件
        3.布局上下间距  使用SizedBox(height: 10)
        4.布局左右间距  使用 SizedBox(width: 10)

      
        var imageUrl="https://www.itying.com/images/flutter/2.png";
       class LayoutDemo extends StatelessWidget {
       @override
       Widget build(BuildContext context) {
	      /**
	       * 对一个整体的布局
	       * 1.如果整体布ClipOval局是上下 使用Column, 再用children: []  放所有的children， 然后再看每个children是什么布局:如果是上下布局,在使用Column组件,如果是水平 使用Row组件
	       * 2.如果整体布局是水平 使用Row  ,再用children: []  放所有的children ， 然后再看每个children是什么布局:如果是上下布局,在使用Column组件,如果是水平 使用Row组件
	       * 3.布局上下间距  使用SizedBox(height: 10)
	       * 4.布局左右间距  使用 SizedBox(width: 10)
	       * */
       return Column(

         children: [
           //第一个child 是水平布局使用Row
           Row(children: <Widget>[
                         Expanded(flex:1,child:Container(height: 180,color: Colors.pink,child:Text("hello flutter",textAlign:TextAlign.center))),
                        SizedBox(width: 10),
                        Expanded(flex:2,child: Container(height: 180,color: Colors.deepOrange,child: Image.network(imageUrl,fit:BoxFit.cover )))
           ]),
           SizedBox(height: 10),
           //第二个child 是水平布局使用Row
           Row(children: <Widget>[
                    Expanded(flex: 2,child: Container(height: 180,child: Image.network(imageUrl,fit: BoxFit.cover,))),
                    SizedBox(width: 10),
                    Expanded(flex: 1,child: Container(height: 180,child: ListView(
                                                                   children: <Widget>[
                                                                                   Container(height: 85,child: Image.network(imageUrl,fit: BoxFit.cover)),
                                                                                    SizedBox(height: 10),
                                                                                     Container(height: 85,child:Image.network(imageUrl,fit: BoxFit.cover))
                                                                                ])))
              ])

         ],


       );
    }
   }



  


十:Stack层叠组件
  1. Stack 表示堆的意思， 顾名思义就是小组件 要堆在大组件上面(范围区域内)的某个角落。
   我们可以用 Stack 或者 Stack 结合 Align 或者 Stack 结合 Positiond 来实 现页面的定位布局
   Stack 定位布局:让里面的多个/任意一个child组件 可以显示在外层组件的任何一个位置

    alignment 把Stack组件的所有的子组件堆在stack上的任意一个位置
    children 子组件 


   2. Stack的全局alignment的问题？       
         (1)alignment: Alignment.center
         让 Stack里面的所有子组件都堆在一起居中,这样会导致所有的子组件重叠居中
         注意:这样会导致所有子组件会相互覆盖,大组件盖住小组件,这样就看不到小组件了
         所以要注意大小组件的排列顺序,大组件先布局,小组件后布局,小组件堆在大组件上面
          如何解决多个子组件重叠的问题呢？ 就要使用Align组件去包裹子组件即可解决
          (2)Alignment(x,y) x,y -1 1  可以设置Stack里面所有的子组件堆在任意一个具体位置(详见源码)
          (3)Stack的全局alignment  不适用多个子组件，会导致多个子组件重叠堆在一起,
          针对多个子组件我们使用Align 或Positioned 来让多个子组件堆在不同的位置

       class stackDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
         //如何让组件Stack居中了,外层嵌套一个居中组件Center
		  return Center(
		    child:  Stack(
		             //alignment: Alignment.center,
		             alignment: Alignment(1,0.3),
		           children: <Widget>[
		                  Container(height: 300,width: 300,color: Colors.red),
		                  Text("我是一个小组件text1"),
		                  Text("我是一个小组件text2")
			    ] ));}} 





  3.Align组件 ：解决多个子组件的显示位置一样堆在一起重叠的问题(堆在Stack组件上面)


     class stackAlignDemo extends StatelessWidget {
		  @override
		  Widget build(BuildContext context) {
		
		     //如何让组件Stack居中了,外层嵌套一个居中组件Center
		     return Center(
		     child: Container(
		         height: 350,
		         width: 350,
		       color: Colors.green,
		       child:    Stack(
		          //alignment: Alignment.center 代码无效 多个子组件会重叠推在Stack组件中间,多个子组件的显示位置一样
		         //对所有的子组件外需要嵌套一个Align组件
		        //alignment: Alignment.center,
		      children: <Widget>[
		        Align(alignment:Alignment(1,-0.2),child: Container(height: 250,width: 250,color: Colors.red)),
		       Align(alignment:Alignment.center,child: Text("我是一个小组件text")),
		       Align(  alignment: Alignment.bottomRight,child: Icon(Icons.settings_applications,size: 30,color: Colors.white))
		      ]))); }}





4.Positioned组件
Positioned组件通过left top right bottom 控制多个子组件的堆放显示不同位置


    class stackPositionedDemo extends StatelessWidget {
      @override
     Widget build(BuildContext context) {

     //如何让组件Stack居中了,外层嵌套一个居中组件Center
     return Center(
     child: Container(
         height: 350,
         width: 350,
       color: Colors.green,
       child:    Stack(
       children: <Widget>[
        Positioned( left: 10,child: Container(height: 250,width: 250,color: Colors.red)),
        Positioned( bottom: 0,left: 100,child: Text("我是一个小组件text")),
        Positioned( right: 0,child: Icon(Icons.settings_applications,size: 30,color: Colors.white))
      ]))); }}






十一:AspectRatio组件 
	  AspectRatio :
	   的作用是根据设置调整子元素 child 的宽高比。(设置子元素的宽高比相对于父容器尽量铺满父容器的空间)
	属性:
	aspectRatio 设置宽高比，最终可能不会根据这个值去布局， 具体则要看综合因素，外层是否允许按照这 种比率进行布局，这只是一个参考值
	 child 子组件
  
       1.这里父容器Container 宽300 高自适应
        子元素AspectRatio的宽高比3.0/1.0(具体宽高:宽300 高:100) 会填充铺满父容器
         因为子元素AspectRatio会填充满父容器 所以子元素AspectRatio宽=父容器宽=300
        再根据元素AspectRatio的宽高比3.0/1.0 计算出子元素AspectRatio的高=100

  
      class AspectRatioDemo extends StatelessWidget {
      @override
       Widget build(BuildContext context) {
       return Container(
          width: 300,
          color: Colors.red,
          child:AspectRatio (aspectRatio: 3.0/1.0,child: Container(color: Colors.green))
      ); }}

 

     2.这里父容器是整个屏幕,宽是屏幕宽度
     子元素AspectRatio的宽高比3.0/1.0(具体宽高:宽屏幕宽度 高:100) 会填充铺满父容器(屏幕)
      因为子元素AspectRatio会填充满父容器(屏幕) 所以子元素AspectRatio宽=父容器宽(屏幕)=屏幕的宽度
      再根据元素AspectRatio的宽高比3.0/1.0 计算出子元素AspectRatio的高= 父容器宽(屏幕)的宽度/3


      class AspectRatioDemo2 extends StatelessWidget {
      @override
      Widget build(BuildContext context) {
       return AspectRatio(
        aspectRatio: 3.0/1.0,
        child:Container(color: Colors.red)
       );}}




十二:Card 卡片组件
    Card 具有圆角和阴影，这让它 看起来有立体感。

    属性:
    margin 外边距
    child 子组件
    Shape Card 的阴影效果，默认的阴影效果为圆角的 长方形边。
 

  

十三:ClipOval 组件：加载的图片为圆角 


	   Card(margin: EdgeInsets.all(10),child:Column(children: <Widget>[AspectRatio(aspectRatio: 25/9,child:  Image.network(imageUrl,fit: BoxFit.cover)),
	                                       ListTile(leading:ClipOval( child: Image.network(imageUrl,fit: BoxFit.cover,height: 60,width: 60,) ) ,title: Text("xxxxxxx"),subtitle: Text("xxxxxxx"),)] )),



 十四:CircleAvatar：专门加载图片的组件
     Card(margin: EdgeInsets.all(10),child: Column(children: [AspectRatio(aspectRatio: 16/9,child: Image.network(imageUrl,fit:BoxFit.cover )),
           ListTile(leading:CircleAvatar(backgroundImage:NetworkImage(imageUrl) ) ,title:Text("xxxxxxx") ,subtitle:Text("xxxxxxx") )]))



 
    一个练习:使用  ListView Card  CircleAvatar 加载本地数据集合 

            class CardDemo3 extends StatelessWidget {
				  @override
				  Widget build(BuildContext context) {
				
				     return ListView(
				       children: listData.map((value){
				         print(value);
				         return Card(
				              margin: EdgeInsets.all(10),
				              child:Column(children: <Widget>[AspectRatio(aspectRatio: 20/9,child: Image.network(value["imageUrl"],fit: BoxFit.cover)),ListTile(leading:CircleAvatar(backgroundImage: NetworkImage(value["imageUrl"])),title: Text(value["title"],style: TextStyle(fontSize: 20,color: Colors.red)),subtitle: Text(value["description"],maxLines: 1,overflow: TextOverflow.ellipsis))]) );
				         }).toList()
				     );
				  }
				}






十五: RaisedButton 按钮组件
 
十六:Wrap 组件(等同于android 的流式布局 FlowLayout )

  
    Row(水平布局单行) 与 Column (垂直布局单列)都是单行单列的

    Wrap 则突破了这个限制,当mainAxis 上空 间不足时(X轴方向)(水平方向第一行)，则向 crossAxis(Y轴方向)(第二行) 上去扩展显示,就变成了多行,类似流式布局
  

 
    属性:

            spacing 主轴X方向上的每个子组件的间距
            spacing:10,

           runSpacing run 的行与行(Y轴)之间的间距
           runSpacing: 15,

           direction 主轴的方向(水平的还是垂直的流式布局)，默认水平
           direction: Axis.horizontal,

           alignment 主轴X的对其方式:start 左对齐 end 右对齐
           alignment:WrapAlignment.start,

          runAlignment 设置Y轴的对齐方式(必须要设置父容器具体高度600 ,
           这里设置的runAlignment 才有效果,如果不设置父容器具体高度,父容器高度有子组件的高度决定,这样设置这个属性就看不出效果)
          runAlignment: WrapAlignment.start,




     class RaisedButtonDemo extends StatelessWidget {
	  @override
	  Widget build(BuildContext context) {
	    /**
	     * 这里父容器Container没有设置高度 高度  是自适应的  高度有子组件Wrap决定
	     * 及 子组件Wrap多高   父容器Container 就多高
	     * */
     return Container(
       width: 350,
       color: Colors.pink,
       child:  Wrap(
         //spacing 主轴X方向上的每个子组件的间距
           spacing:10,
           //runSpacing run 的行与行(Y轴)之间的间距
           runSpacing: 15,
           //direction 主轴的方向(水平的还是垂直的流式布局)，默认水平
           direction: Axis.horizontal,
           //alignment 主轴X的对其方式:start 左对齐 end 右对齐
           alignment:WrapAlignment.start,
           // runAlignment 设置Y轴的对齐方式
           //因为这里没有设置父容器的具体高度 所有这里设置效果看不出  需要设置父容器具体高度
          // runAlignment: WrapAlignment.start,
           children: <Widget>[
             MyButton("第1集"),
             MyButton("第2集"),
             MyButton("第3集第10集"),
             MyButton("第4集"),
             MyButton("第5集"),
             MyButton("第6集第10集第10集"),
             MyButton("第7集"),
             MyButton("第8集第10集第10集"),
             MyButton("第9集"),
             MyButton("第10集第10集第10集"),
             MyButton("第11集第10集"),
             MyButton("第3集"),
             MyButton("第4集"),
             MyButton("第5集"),
             MyButton("第6集第10集第10集"),
             MyButton("第7集"),
             MyButton("第8集"),
             MyButton("第9集第10集第10集第10集第10集第10集第10集第10集"),
             MyButton("第10集"),
             MyButton("第11集第10集")])
     );
    }
    }





    class RaisedButtonDemo2 extends StatelessWidget {
    @override
     Widget build(BuildContext context) {
	    /**
	     * 这里父容器没有设置高度 高度  是自适应的  高度有子组件决定
	     * 及 子组件Wrap多高   父容器 就多高
	     * */
    return Container(
      height: 600,
        width: 350,
        color: Colors.pink,
        //设置父容器的padding 值
        padding:EdgeInsets.all(5),
        child:  Wrap(
          //spacing 主轴X方向上的每个子组件的间距
            spacing:10,
            //runSpacing run 的行与行(Y轴)之间的间距
            runSpacing: 10,
            //direction 主轴的方向(水平的还是垂直的流式布局)，默认水平
             direction: Axis.horizontal,
            //alignment 主轴X的对其方式:start 左对齐 end 右对齐  center  居中
             alignment:WrapAlignment.start,
            // runAlignment 设置Y轴的对齐方式:start 据顶部  end 据底部
            //设置父容器具体高度600 这里设置的runAlignment 就有效果了
              runAlignment: WrapAlignment.start,
            children: <Widget>[
              MyButton("第1集"),
              MyButton("第2集"),
              MyButton("第3集第10集"),
              MyButton("第4集"),
              MyButton("第5集"),
              MyButton("第6集第10集第10集"),
              MyButton("第10集第10集第10集"),
              MyButton("第11集第10集"),
              MyButton("第3集"),
              MyButton("第6集第10集第10集"),
              MyButton("第7集"),
              MyButton("第8集"),
              MyButton("第9集第10集第10集第10集第10集第10集第10集第10集"),
              MyButton("第10集"),
              MyButton("第11集第10集")])
    );}}





  

十七:StatelessWidget  StatefulWidget  状态组件  
 
 StatelessWidget 是无 状态组件，状态不可变的 widget

 StatefulWidget 是有状态组件，持有的状态可能在 widget 生命周期改变。
通俗的讲： 如果我 们想改变页面中的数据的话这个时候就需要用到 StatefulWidget


 注意:
     1.只要数据一旦发生了改变 其就会重新渲染界面 更新数据。
     2.继承使用StatefulWidget状态组件,实现界面的数值状态更新,必须要使用
     setState(() {....}方法 才能实现更行的效果,  类似适配器的 notifyDataChanges
    

  案例如下:

	   class MyApp extends StatelessWidget {
		  @override
		  Widget build(BuildContext context) {
		    return MaterialApp(
		        home:Scaffold(
		          appBar: AppBar(
		              title: Text('StatefulWidget  Demo')
		          ),
		        //  body: StatefulWidgetDemo1(),
		          body: StatefulWidgetDemo2(),
		        )
		    );
		  }
		}

    
		class _StatefulWidgetDemo1 extends State {
		  int countNum=0;
		  @override
		  Widget build(BuildContext context) {
		    return Column(
		      children: <Widget>[
		        SizedBox(height: 200),
		        Chip(
		          label:Text('${this.countNum}') ,
		        ),
		        SizedBox(height: 20),
		        RaisedButton(
		          child: Text('按钮'),
		          onPressed: (){
		            setState(() {   //只有有状态组件里面才有setState((){})  类似notifyDataChanges
		              this.countNum++;
		            });
		          },
		        )
		      ],
		    );
		  }
		}


     
       class StatefulWidgetDemo2 extends StatefulWidget{
		  @override
		  State<StatefulWidget> createState() {
		      return  new  _StatefulWidgetDemo2();
		  }
		}





          class  _StatefulWidgetDemo2 extends  State{
			 List list=new List();
			   @override
			  Widget build(BuildContext context) {
			    list.asMap().keys.map((key){print(key);}).toList(); //输出list的角标
			    list.asMap().values.map((value){print(value);}).toList(); //输出list的元素值
			
			        return  ListView(children: <Widget>[
			          Column(
			              children:this.list.map((value){return ListTile(title: Text(value));}).toList()),
			          SizedBox(height:20),
			           RaisedButton(
			               child: Text("按钮"),
			                  onPressed: (){
			                 setState(() {
			                   this.list.add('新增数据1');
			                   this.list.add('新增数据2');
			                 });
			
			          })
			        ]);
			  }
			}






  

十八:  BottomNavigationBar 是底部导航条，可以让我们定义底部 Tab 切换，bottomNavigationBar 是 Scaffold 组件的参数。


     items    List<BottomNavigationBarItem> 底部导航 条按钮集合

    iconSize   设置tab图标icon的大小
     iconSize:36.0, 

    currentIndex 默认选中第几个
       currentIndex:this._currentIndex,


    onTap  tab选中变化回调函数

      onTap: (int index){
             //使用setState重新渲染
             setState(() {
               print(index);
               this._currentIndex=index;
             });
           }
 
   
     fixedColor 选中的tab颜色  
     fixedColor:Colors.red,  
  
       
    type     BottomNavigationBarType.fixed
              BottomNavigationBarType.shifting
       配置底部tabs可以有多个按钮
      当底部导航按钮>3个时,必须要添加此方法,否则添加的多个按钮会异样



       
      案例:

      	import 'package:flutter/material.dart';
		import 'tabs/Home.dart';
		import 'tabs/Category.dart';
		import 'tabs/Setting.dart';

		void main() => runApp(MyApp());

		class MyApp extends StatelessWidget {
		  @override
		  Widget build(BuildContext context) {
		    return MaterialApp(
		        home:Tabs()
		    );
		  }
		}

		class Tabs  extends StatefulWidget{
		  Tabs({Key key}) : super(key: key);
		
		  @override
		  State<StatefulWidget> createState() {
		      return _TabsState();
		  }
		}

	class _TabsState extends State<Tabs> {
	 var _currentIndex=0;
	 List _pageList= [HomePage(),CategoryPage(),SettingPage(),SettingPage(),CategoryPage()];
	  @override
	  Widget build(BuildContext context) {
     return Scaffold(
       appBar: AppBar(title: Text("BottomNavigationBar 点击导航切换tab Demo")),
        // body: Text("${_currentIndex}"),
        // body:HomePage(),
         //返回一个具体的组件 点击时此组件会进行渲染
         body:_pageList[_currentIndex],
         bottomNavigationBar: BottomNavigationBar(
           // currentIndex 默认选中第几个
           // currentIndex: 0,
             currentIndex:this._currentIndex,
            // onTap  tab选中变化回调函数
             onTap: (int index){
             //使用setState重新渲染
             setState(() {
               print(index);
               this._currentIndex=index;
             });
           },
          iconSize:36.0,      //设置tab图标icon的大小
             fixedColor:Colors.red,  //选中的tab颜色
             //配置底部tabs可以有多个按钮(当底部导航按钮>3个时,必须要添加此方法,否则添加的多个按钮会异样)
              type:BottomNavigationBarType.fixed,
           items: [
             BottomNavigationBarItem(
                 icon: Icon(Icons.home),
                 title: Text("首页")
             ),
             BottomNavigationBarItem(
                 icon: Icon(Icons.category),
                 title: Text("分类")
             ),
             BottomNavigationBarItem(
                 icon: Icon(Icons.settings),
                 title: Text("设置")
             ),
             BottomNavigationBarItem(
                 icon: Icon(Icons.settings),
                 title: Text("我的")
             ),
             BottomNavigationBarItem(
                 icon: Icon(Icons.settings),
                 title: Text("商城")
             )
           ]
         )
		     );
		  }
		
		
		}






十九:路由

    路由通俗的讲就是页面跳转。 通过 Navigator 组件管理路由导航
  
  1. Navigator.pushNamed(context，routerName) 
       跳转到下一个页面,当前页面还存在,如果下一个页面往上一级返,还是返回到当前页面
  2. Navigator.pop(context);  
    返回上一个页面

 
 3. Navigator.pushReplacementNamed(context，routerName) 
      替换当前页面路由
      由  当前页面 跳转到下一个页面, 下一个页面会替换当前页面, 即当前页面不存在了,
       如果下一个页面往上一级返, 就会返回到上上级页面


 4. pushAndRemoveUntil<T extends Object?>(BuildContext context,Route<T> newRoute,RoutePredicate predicate)

  把前面的所有的路由页面置为空 销毁,返回到指定的路由页面  

     参数: context  上下文
         Route<T> newRoute 返回到指定的路由页面
         RoutePredicate predicate   前面的所有的路由页面置为空 销毁的对象 
     

		 Navigator.pushAndRemoveUntil(context,
		  new MaterialPageRoute(builder: (context){return new Tabs(index:2);}),
		   (route) => route == null);

 
       




   路由跳转的方式：
   1、基本路由 :用于小项目



     //路由跳转页面SearchPage 箭头函数   ( context) => SearchPage()
          //    Navigator.of(context).push(MaterialPageRoute(builder: ( context) => SearchPage()));
              //路由跳转页面SearchPage  匿名方法:(BuildContext context){return  SearchPage();}
          Navigator.of(context).push(MaterialPageRoute(builder: (BuildContext context){return  SearchPage();} ));




      Navigator.of(context).push(MaterialPageRoute(builder:(BuildContext context){
             // return FormPage();
               return FormPage(title:"员工工资表单");
             }));



     Navigator.of(context).pop();


2、命名路由:用于大项目

    命名路由的难点在于:
     1.路由传递可选命名参数的方法
     2.通过路由给下个页面传递参数
   
			 //全局配置命名路由集合: Map<String, WidgetBuilder> routes
								// key ："/search"
								// value : (context,{arguments}){return SearchPage(arguments:arguments);}
								final Map<String, WidgetBuilder> routes = {
								  //底部导航路由
								  Tabs.ROUTE_NAME: (context) {
								    return Tabs();
								  },
								  // Category界面的 命名路由跳转页面FormPage
								  FormPage.ROUTE_NAME: (context) {
								    return FormPage();
								  },
								  //Home界面的 命名路由跳转页面SearchPage 需要配置传参数  arguments
								  SearchPage.ROUTE_NAME: (context, {arguments}) {
								    return SearchPage(arguments: arguments);
								  },
								  ProductPage.ROUTE_NAME: (context, {arguments}) {
								    return ProductPage(arguments: arguments);
								  },
								  ProductInfoPage.ROUTE_NAME: (context, {arguments}) {
								    return ProductInfoPage(arguments: arguments);
								  },
								
								  LoginPage16.ROUTE_NAME: (context) {
								    return LoginPage16();
								  },
								  RegisterFirstPage16.ROUTE_NAME: (context) {
								    return RegisterFirstPage16();
								  },
								  RegisterSecondPage16.ROUTE_NAME: (context) {
								    return RegisterSecondPage16();
								  },
								  RegisterThirdPage16.ROUTE_NAME: (context) {
								    return RegisterThirdPage16();
								  },
								
								  LoginPage181.ROUTE_NAME: (context) {
								    return LoginPage181();
								  },
								  RegisterFirstPage181.ROUTE_NAME: (context) {
								    return RegisterFirstPage181();
								  },
								  RegisterSecondPage181.ROUTE_NAME: (context) {
								    return RegisterSecondPage181();
								  },
								  RegisterThirdPage181.ROUTE_NAME: (context) {
								    return RegisterThirdPage181();
								  },
								
								  LoginPage182.ROUTE_NAME: (context) {
								    return LoginPage182();
								  },
								  RegisterFirstPage182.ROUTE_NAME: (context) {
								    return RegisterFirstPage182();
								  },
								  RegisterSecondPage182.ROUTE_NAME: (context) {
								    return RegisterSecondPage182();
								  },
								  RegisterThirdPage182.ROUTE_NAME: (context) {
								    return RegisterThirdPage182();
								  }
								};


    案例:商品详情界面



       import 'package:flutter/material.dart';
		import 'package:flutter_app/16/constant/Constant.dart';
		
		class ProductInfoPage extends StatefulWidget {
		 static const  ROUTE_NAME ='/product_info_page';
		
		  final Map arguments;
		 //可选(不是必须要传的)的命名参数(自己命名的参数)arguments
		const  ProductInfoPage({Key key, this.arguments}) : super(key: key);
		
		  @override
		  _ProductInfoPageState createState()  {
		    //这里传入的可选(不是必须要传的)的命名参数(自己命名的参数)arguments 这里作为实参
		    return  _ProductInfoPageState(arguments:this.arguments);
		  }
		}
		
		class _ProductInfoPageState extends State<ProductInfoPage> {
		  final  Map arguments;
		  //可选(不是必须要传的)的命名参数(自己命名的参数)arguments 这里作为形参
		  _ProductInfoPageState({this.arguments}); 
		
		  @override
		  Widget build(BuildContext context) {
		   return Scaffold(appBar: AppBar(title: Text("商品详情届满展示传递过来的参数")),
		      body:  Container(child: Text("接收的参数数据 ${arguments[Constant.PRODUCT_NAME]}  ${arguments[Constant.PRODCT_ID]}"))
		       );
		
		  }
		
		
		}
		
		  

二十:AppBar组件  TabBar组件   TabView

 AppBar 就是单纯的给界面添加标题栏

  注意:如果设置左侧图标按钮,会覆盖默认的左侧的返回按钮图标

     class AppBarPage extends StatelessWidget {
		  static const  ROUTE_NAME ='/AppBarPage';
		
		  @override
		  Widget build(BuildContext context) {
		    return Scaffold(
		      //顶部标题栏 AppBar
		     appBar: AppBar(
		       //标题栏背景颜色
		       backgroundColor: Colors.red,
		       //标题居中
		       centerTitle:true,
		       title: Text("标题"),
		       //设置左侧图标按钮,会覆盖默认的左侧的返回按钮
		       leading: IconButton(icon: Icon(Icons.menu), onPressed: (){print("点击左侧图标按钮");}),
		        //添加右侧按钮
		       actions: <Widget>[
		         IconButton( icon: Icon(Icons.search),color: Colors.black, onPressed:(){print("点击右侧搜索图标");}),
		         IconButton(icon: Icon(Icons.settings),color: Colors.black, onPressed:(){print("点击右侧设置图标");})
		       ],
		     ),
		
		        body: Center(child: Text("AppBarPage区域内容....."))
		    );
		
		
		  }
		}
		 



TabBar组件:如果你要给标题栏添加多个Tab切换 就使用该控件
 详见分类CategoryPage 对TabBar组件的使用

 注意:1.在Scaffold外嵌套一个组件DefaultTabController 必须要添加属性length 顶部  TabBar 点击切换数量

   2.本界面Category  有个标题栏    外面Tabs页面  也有个标题栏。
 如何去重复的标题栏 把本界面的 属性bottom的TabBar组件 放到  属性title:的位置

   3.TabBarView里添加的子组件数量要和上面的TabBar的子组件数量对应    DefaultTabController的length 一致 否则报错  
  


  案例:

   class _CategoryPageState2  extends State<CategoryPage>{
  @override
  Widget build(BuildContext context) {
    final List<Widget> tabList  =  <Widget>[Tab(text:"热门热门热门"),Tab(text: "推荐"),Tab(text: "精选"),Tab(text: "商城1商城1"),Tab(text: "商城2"),Tab(text: "商城3商城3商城3"),Tab(text: "商城4商城4商城4商城4商城4商城4商城4")];

    //在Scaffold外嵌套一个组件DefaultTabController 必须要添加属性length 顶部  TabBar 点击切换数量
    return DefaultTabController(
             length: 7,
             child: Scaffold(
               appBar: AppBar(
                     //标题栏背景颜色
                       backgroundColor: Colors.pink,
                       //标题居中
                       centerTitle:true,
                      // title: Text("TabBarPage标题"),
                       //设置左侧图标按钮,会覆盖默认的左侧的返回按钮
                      //  leading: IconButton(icon: Icon(Icons.menu), onPressed: (){print("点击左侧图标按钮");}),
                       //添加右侧按钮
                      actions: <Widget>[
                      //   IconButton( icon: Icon(Icons.search),color: Colors.black, onPressed:(){print("点击右侧搜索图标");}),
                        IconButton(icon: Icon(Icons.settings),color: Colors.black, onPressed:(){print("点击右侧设置图标");})
                      ],
                    // bottom: TabBar(tabs: <Widget>[Tab(text:"热门"),Tab(text: "推荐"),Tab(text: "精选"),Tab(text: "商城")])
                /**
                 *  本界面Category  有个标题栏    外面Tab  也有个标题栏
                 *  如何去重复的标题栏
                 *  把本界面的 属性bottom的TabBar组件 放到  属性title:的位置
                 * */
                //title:  TabBar(tabs: <Widget>[Tab(text:"热门"),Tab(text: "推荐"),Tab(text: "精选"),Tab(text: "商城1"),Tab(text: "商城2"),Tab(text: "商城3"),Tab(text: "商城4")])
              title: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[Expanded(child: TabBar(
                                indicatorColor: Colors.black,
                                labelColor: Colors.black,
                                unselectedLabelColor: Colors.white,
                                //指示器下划线的长度
                                indicatorSize: TabBarIndicatorSize.label,
                                isScrollable: true,
                                tabs: tabList
                                 ))])


               ),
                 //TabBarView里添加的子组件数量要和上面的TabBar的子组件数量对应    DefaultTabController的length 一致 否则报错
               body:TabBarView(children: [
                 ListView(children: [ListTile(title: Text("第一个tab")),ListTile(title: Text("第一个tab")),RaisedButton( child:Text("点击按 钮跳转到表单界面"),onPressed: (){Navigator.pushNamed(context, FormPage.ROUTE_NAME);})]),
                 ListView(children: [ListTile(title: Text("第2个tab")),ListTile(title: Text("第2个tab")),ListTile(title: Text("第2个tab"))]),
                 ListView(children: [ListTile(title: Text("第3个tab")),ListTile(title: Text("第3个tab")),ListTile(title: Text("第3个tab"))]),
                 ListView(children: [ListTile(title: Text("第4个tab")),ListTile(title: Text("第4个tab")),ListTile(title: Text("第4个tab"))]),
                 ListView(children: [ListTile(title: Text("第5个tab")),ListTile(title: Text("第5个tab")),ListTile(title: Text("第5个tab"))]),
                 ListView(children: [ListTile(title: Text("第6个tab")),ListTile(title: Text("第6个tab")),ListTile(title: Text("第6个tab"))]),
                 ListView(children: [ListTile(title: Text("第7个tab")),ListTile(title: Text("第7个tab")),ListTile(title: Text("第7个tab"))])
                 ])
               )



         );
  }

}



二十一 :TabBarController
 使用TabBarController实现顶部标题兰的tab切换,如果有请求网络数据和分页加载更多,就是用该控件。

 1. 创建类需要extends StatefulWidget
 2.state  类 需要  继承  with SingleTickerProviderStateMixin 

 3.实现initState方法 创建TabController对象


   案例:TabBarControllerPage.dart
	   class TabBarControllerPage extends StatefulWidget {
	  static const  ROUTER_NAME = "/tabBarControllerPage";
	
	  TabBarControllerPage({Key key}) : super(key: key);
	
	  @override
	  _TabBarControllerPageState createState() => _TabBarControllerPageState();
	
	
	}
	
	
	class _TabBarControllerPageState extends State<TabBarControllerPage>  with SingleTickerProviderStateMixin {
	  TabController _tabController;
	  /**
	  * 实现initState方法 初始化 TabController实体对象:length: 7
	  * */
	  @override
	  void initState() {
	      super.initState();
	         _tabController = new TabController(vsync: this,length: 7);
	
	  }
	  @override
	  void dispose() {   //生命周期函数
	    super.dispose();
	    _tabController.dispose();
	  }
	
	  @override
	  Widget build(BuildContext context) {
    final List<Widget> tabList  =  <Widget>[Tab(text:"热门热门热门"),Tab(text: "推荐"),Tab(text: "精选"),Tab(text: "商城1商城1"),Tab(text: "商城2"),Tab(text: "商城3商城3商城3"),Tab(text: "商城4商城4商城4商城4商城4商城4商城4")];

    //在Scaffold外嵌套一个组件DefaultTabController 必须要添加属性length 顶部  TabBar 点击切换数量
    return   Scaffold(
    appBar: AppBar(
    //标题栏背景颜色
    backgroundColor: Colors.pink,
    //标题居中
    centerTitle:true,
     title: Text("TabController 带标题"),
    //设置左侧图标按钮,会覆盖默认的左侧的返回按钮
    //  leading: IconButton(icon: Icon(Icons.menu), onPressed: (){print("点击左侧图标按钮");}),
    //添加右侧按钮
    actions: <Widget>[
      IconButton( icon: Icon(Icons.search),color: Colors.black, onPressed:(){print("点击右侧搜索图标");}),
    IconButton(icon: Icon(Icons.settings),color: Colors.black, onPressed:(){print("点击右侧设置图标");})
    ],
      bottom: TabBar(
          controller: this._tabController, //todo 注意 添加controller属性
          indicatorColor: Colors.black,
          labelColor: Colors.black,
          unselectedLabelColor: Colors.white,
          //指示器下划线的长度
          indicatorSize: TabBarIndicatorSize.label,
          isScrollable: true, //tab 可以滚动
          tabs: tabList
      ),
    ),

    //TabBarView里添加的子组件数量要和上面的TabBar的子组件数量对应    DefaultTabController的length 一致 否则报错
    body:TabBarView(
     controller :this._tabController,//todo 注意 添加controller属性
        children: [
    ListView(children: [ListTile(title: Text("第一个tab")),ListTile(title: Text("第一个tab")),RaisedButton( child:Text("点击按 钮跳转到表单界面"),onPressed: (){Navigator.pushNamed(context, FormPage.ROUTE_NAME);})]),
    ListView(children: [ListTile(title: Text("第2个tab")),ListTile(title: Text("第2个tab")),ListTile(title: Text("第2个tab"))]),
    ListView(children: [ListTile(title: Text("第3个tab")),ListTile(title: Text("第3个tab")),ListTile(title: Text("第3个tab"))]),
    ListView(children: [ListTile(title: Text("第4个tab")),ListTile(title: Text("第4个tab")),ListTile(title: Text("第4个tab"))]),
    ListView(children: [ListTile(title: Text("第5个tab")),ListTile(title: Text("第5个tab")),ListTile(title: Text("第5个tab"))]),
    ListView(children: [ListTile(title: Text("第6个tab")),ListTile(title: Text("第6个tab")),ListTile(title: Text("第6个tab"))]),
    ListView(children: [ListTile(title: Text("第7个tab")),ListTile(title: Text("第7个tab")),ListTile(title: Text("第7个tab"))])
    ])
    );
    }
   }





 二十二:Drawer   DrawerHeader  UserAccountsDrawerHeader

Drawer ： 侧边栏抽屉组件
DrawerHeader :侧边栏头组件 可以自定义比较灵活
 UserAccountsDrawerHeader:专门针对侧边栏的个人用户信息展示的组件 格式比较固定 官方提供的


 DrawerHeader(child: Text("hello flutter"),decoration: BoxDecoration(color: Colors.pink,image: DecorationImage(image: NetworkImage(imageUrl),fit: BoxFit.cover))))]),


点击侧边栏item 跳转到对应的页面
     案例:Tabs.dart   
    ListTile(leading: CircleAvatar(child: Icon(Icons.people)),title: Text("用户中心"),
                              //点击侧边栏item 跳转到对应的页面
                              onTap: (){
                                 Navigator.pop(context);//隐藏侧边栏
                                 Navigator.pushNamed(context, UserPage.ROUTER_NAME);                       //跳转到用户中心UserPage页面
                                }
                                ),






二十三:常见的按钮组件

 RaisedButton ：凸起的按钮，其实就是
 Material Design 风格的 Button FlatButton ：扁平化的按钮 OutlineButton：线框按钮 
IconButton ：图标按钮 
ButtonBar:按钮组 
FloatingActionButton:浮动按钮


 属性:

onPressed VoidCallback ，一般接收一个 方法 必填参数，按下按钮时触发的回调，接收一个 方法，传 null 表示按钮禁用，会显示禁用相关 样式.

child Widget 文本控件

注意:
注意:1.Column没有margin属性 所以需要外面嵌套一个父组件Container 通过父组件添加margin属性
 Container(
        margin: EdgeInsets.fromLTRB(10, 0, 10, 0),
         color: Colors.greenAccent,
         child: Column(.....)
)




  2.button 本身没有宽高属性 需要外面嵌套一个父组件Container来设置宽400高50 ,来改变button的宽400高50

                      Row(
                       //Row调整水平轴方向 剧中
                       mainAxisAlignment: MainAxisAlignment.center,
                       children: <Widget>[Container(height:50,width:300,child: RaisedButton(child: Text('限定宽高的button'), color: Colors.blue,textColor: Colors.white,elevation:50,onPressed: (){ print("宽度高度");}))]
                     ),



    3. button 本身没有宽高属性 需要外面嵌套一个父组件Container这里只设置 高60 ,来改变button的 高60 那么button 的宽度是自适应的 填充满屏幕
                                  Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                           children: <Widget>[Expanded(child: Container(height:60,child: RaisedButton(child: Text('自适应宽度按钮1'),color: Colors.blue,margin:EdgeInsets.all(10),textColor: Colors.white,elevation: 20,onPressed: (){print("自适应按钮1");})))]
                        ),
                      SizedBox(height: 10),



   4.button 本身没有宽高属性 需要外面嵌套一个父组件Container这里只设置 高60 ,来改变button的 高60 那么button 的宽度是自适应的 填充满屏幕
  
    Row(
                         mainAxisAlignment: MainAxisAlignment.center,
                      children: <Widget>[Expanded(child: Container(height:60,margin:EdgeInsets.all(10),child: RaisedButton(child: Text('自适应宽度按钮2'),color: Colors.blue,textColor: Colors.white,elevation: 20,onPressed: (){print("自适应按钮2");})))]
              ),


   5.设置按钮为圆角的(使用的最多)

                           RaisedButton(child:Text("设置圆角按钮"),color:Colors.pink,textColor:Colors.white,elevation:20,shape:RoundedRectangleBorder(borderRadius: BorderRadius.circular(15)),onPressed:(){print("圆角按钮");}),

 6.带边框订的按钮  设置 color: Colors.red,  没有效果
                            OutlineButton( child: Text("带边框订的按钮"),color: Colors.red, textColor: Colors.white,onPressed: (){ print('OutlineButton');}),



   7.案例:自定义组件按钮

  //自定义可变名称参数
      MyButton(text:"自定义按钮",height:60.0,width:100.0,pressed:(){print('自定义按钮');})



			class MyButton extends StatelessWidget {
			  final text;
			
			  final  double height;
			
			  final double width;
			
			  final Function() pressed;
			
			
			  MyButton({this.text="",  this.height=30.0, this.width=80.0, this.pressed});
			
			
			  @override
			  Widget build(BuildContext context) {
			       return Container(
			           height: this.height,
			           width: this.width,
			           child: RaisedButton(child: Text(this.text),onPressed:this.pressed )
			       );
			  }
			
			
			}



二十四:FloatingActionButton 浮动按钮组件

 FloatingActionButton 简称 FAB ,可以实现浮动按钮，也可以实现类似闲鱼 app 的底部中间的一个tab凸 起导航


  浮动按钮组件FloatingActionButton的添加 作为  Scaffold的属性进行添加
  动按钮floatingActionButton    和  appBar  body   同级别的
  
 案例:见Tab.dart 
  FloatingActionButton 实现闲鱼 app 底部凸起按钮
    
     Scaffold(
         backgroundColor: Colors.pink.shade50,
          appBar: AppBar(title: Text("基本/命名路由跳转页面Demo")),
         //浮动按钮floatingActionButton    和  appBar  body   同级别的
           //FloatingActionButton外面加个父组件Container(圆形的)
           floatingActionButton: Container(
               height:70,
               width:70,
                //设置padding
               padding: EdgeInsets.all(5),
               //设置margin距上10的距离
               margin: EdgeInsets.only(top: 20),
                //设置了BoxDecoration 属性后,color: Colors.red 必须要放在BoxDecoration里否则会报错。
               //color: Colors.red,
               decoration: BoxDecoration(borderRadius:BorderRadius.circular(40),color: Colors.pink.shade50),
               child: FloatingActionButton(child: Icon(Icons.add,color: this._currentIndex==2?Colors.white:Colors.black,size:40),backgroundColor:this._currentIndex==2?Colors.red:Colors.yellow,
                   onPressed: (){setState(() {this._currentIndex=2;print("悬浮按钮,_currentIndex=${_currentIndex}");});})),
           //设置浮动按钮的位置
           floatingActionButtonLocation:FloatingActionButtonLocation.centerDocked,
           body:_pageList[_currentIndex],

        );



二十五: TextField 文本框组件(等同EditText)
   maxLines 设置此参数可以把文本框改为多行文本框
   icon  在TextField 文本框前面添加图标
           icon: Icon(Icons.people,color: Colors.black ),
   hintText   hint 输入提示语
   labelText lable 的名称
   labelStyle 配置 lable 的样式
   border 配置文本框边框 OutlineInputBorder 配合使用  给TextField设置边框
           border: OutlineInputBorder()
    
  obscureText: true, TextField组件的obscureText属性为密码框 把文本框框改为密码框
   onChanged 文本框改变的时候触发的事件
    
   ontroller: 
     结合 TextEditingController()可以配置表单默认显示的内容
     如果你一进入界面就需要对 用户名的表单进行初始化赋值的话 就需要
     创建TextEditingController对象 赋值给变量 _userName
     var _userName = new TextEditingController();
   
     如果你一进入界面就需要对其进行初始值赋值的话,就不需要创建 TextEditingController对象对其进行赋值     
      var  _password;
 

    案例:见 TextFieldPage.dart

	           class _TextFieldPageState extends State<TextFieldPage> {
	  //如果你一进入界面就需要对 用户名的表单进行初始化赋值的话 就需要
	  //创建TextEditingController对象 赋值给变量 _userName
	  //controller 结合 TextEditingController()可以配置表单默认显示的内容
	   var _userName = new TextEditingController();
	  // 如果你一进入界面就需要对其进行初始值赋值的话,就不需要创建 TextEditingController对象对其进行赋值
	  var  _password;
	
	  //初始化默认状态值 等同于 Activity 的 onCteate()方法
	   @override
	  void initState() {
	     super.initState();
	     //給TextField內容默認出事賦值
	     _userName.text="默认初始值:hello flutter";
	  }
	
	  @override
	  Widget build(BuildContext context) {
	    return Scaffold(
	      backgroundColor: Colors.pink.shade50,
	      appBar: AppBar(title: Text("表单演示页面")),
	
	      //使用Padding组件
	      body: Padding(
	          padding: EdgeInsets.all(20),
	           child: Column(children:  <Widget>[
	              // TextField 组件  等同EditText
	               TextField(),
	               SizedBox(height: 20),
	                TextField(
	                  //设置 TextField 组件 可以输入多行文本框
	                  maxLines:4,
	                  decoration: InputDecoration(
	                    //TextField 文本框前面添加图标
	                      icon: Icon(Icons.people,color: Colors.black ),
	                    //hint 输入提示语
	                     hintText: "请输入用户名",
	                    //   labelText lable 的名称
	                      labelText: "请输入用户名",
	                      //设置labelStyle  配置 lable 的样式
	                      labelStyle: TextStyle(),
	                   // border 配置文本框边框 OutlineInputBorder 配合使用  给TextField设置边框
	                   border: OutlineInputBorder()
	                  ),
	                  //一進入界面需要加载 默認加載初始值内容 就添加此属性
	                  controller:this._userName ,
	                  // 手動輸入文本框內容value  更新数据值内容_userName.text
	                  onChanged: (value){
	                    setState(() {
	                  //    this._userName.text=value; //这个不能要 会导致输入光标出现问题
	                      print("输入的内容${_userName.text}");
	                    });
	                  },
	                ),
	               SizedBox(height: 20),
	               TextField(
	                maxLines: 1,
	                 //TextField组件的obscureText属性为密码框 把文本框框改为密码框
	                 obscureText: true,
	                 decoration: InputDecoration(
	                   //TextField 文本框前面添加图标
	                     icon: Icon(Icons.lock,color: Colors.black ),
	                     hintText: "请输入密码",
	                     //   labelText lable 的名称
	                      labelText: "请输入密码",
	                     //设置labelStyle  配置 lable 的样式
	                     labelStyle: TextStyle(),
	                     border: OutlineInputBorder()
	
	                 ),
	                 onChanged: (value){
	                   setState(() {
	                     print("輸入的密碼$_password");
	                     this._password= value;
	                   });
	                 },
	               ),
	             SizedBox(height: 20),
	             TextField(
	               //设置 TextField 组件 可以输入多行文本框
	                 maxLines:4,
	                 decoration: InputDecoration(
	                   //TextField 文本框前面添加图标
	                     icon: Icon(Icons.people,color: Colors.black ),
	                     //hint 提示语
	                     hintText: "请输入内容",
	                     //   labelText lable 的名称
	                     labelText: "请输入内容",
	                     //设置labelStyle  配置 lable 的样式
	                     labelStyle: TextStyle(),
	                     // border 配置文本框边框 OutlineInputBorder 配合使用(带边框)
	                     border: OutlineInputBorder()
	                 )
	             ),
	             SizedBox(height: 40),
	              Container(
	                 width: double.infinity,
	                height: 40,
	                child: RaisedButton( child: Text("登录(获取用户名和密码)"), color: Colors.blue,textColor: Colors.white,
	                     onPressed: (){
	                      print("登录获取 用户名: ${this._userName.text}  密码${this._password}");
	                     }),
	              )
	               ]
	           ),
	      )
	    );
	  }
	}
	       

    




二十六: Checkbox、CheckboxListTile 多选框组件
  Checkbox 属于一级多选组件
  CheckboxListTile 是属于一级Checkbox  下的多选组件
    
   Checkbox 
   value true 或者 false
   onChanged 改变的时候触发的事件
   activeColor 选中的颜色、背景颜色
   checkColor 选中的颜色、Checkbox 里面对号的颜色



   CheckboxListTile 常见属性：

 value true 或者 false
 onChanged 改变的时候触发的事件 
 activeColor 选中的颜色、背景颜色
 title 标题 
 subtitle 二级标题 
 secondary 配置图标或者图片
 selected 选中的时候文字颜色是否跟着改变


		   案例:见 CheckboxPage.dart
		
		  class _CheckboxPageState extends State<CheckboxPage> {
		  var flag=false;
		   @override
		  Widget build(BuildContext context) {
		
		    return Scaffold(
		      appBar: AppBar(title: Text("checkbox组件的使用"),),
		       body: Column(
		           mainAxisAlignment: MainAxisAlignment.center,
		         children: <Widget>[
		              SizedBox(width: 50),
		               Row(children: [
		       
		                   Checkbox(
		                       // 默认选中状态   true 或者 false
		                       value: this.flag,
		                        onChanged: (value){setState(() {this.flag=value;print("是否被选中${flag}");});},
		                           //选中的颜色
		                       activeColor: flag?Colors.red:Colors.red.shade50)]),
		               SizedBox(width: 40),
		               Row(children: <Widget>[Text( this.flag?"选中":"未选中")]),
		              SizedBox(width: 40),
		               Divider(),
		              CheckboxListTile(
		                    value: this.flag,
		                    title: Text("标题"),
		                    subtitle:Text("这是二级标题") ,
		                    secondary:Icon(Icons.help),
		                    activeColor: flag?Colors.red:Colors.red.shade50,
		                    onChanged: (value){setState(() {this.flag=value;});}
		                  )
		         ]
		       )
		
		    );
		  }
		}

  
  


  二十七:  Radio、RadioListTile 单选按钮组件


  Radio  是个单选按钮组件 多个Radio 组成一个按钮组合集
 
 1.每个Radio的groupValue属性值必须要一样   才能组成按钮组合集。 所以按钮组集合 最少必须要有两个Radio,否则不成立
 
 2.如果单个的Radio 的 value值 和groupValue值 相等 就表示 当前这个Radio是被选中状态
  
 3.onChanged 改变时触发事件  onChanged : (value){setState(() { this.sex = value;print("${sex}");});}



   RadioListTile
   
   value true 或者 false 也可以 1 2 表示
   
   selected 表示是否被选中 true 选中  false 未选中  必须要添加该属性 被选中 还会发生状态的改变 颜色 图标


  Switch 开关
 
  value 单选的值  true false
  
  activeColor 被选中的颜色、背景颜色

    
    案例1见 main24_Radio_RadioListTitle_Switch.dart          RadioPage.dart 

    class _RadioPageState extends State<RadioPage> {
	  int sex=1;
	  var flag=true;
	
	  @override
	  Widget build(BuildContext context) {
	   return Scaffold(
           appBar: AppBar(title: Text("Radio选择按钮使用")),
            backgroundColor: Colors.pink,
            body: Padding(
                padding:EdgeInsets.all(20),
                child: Column(children:<Widget> [
                      SizedBox(height: 40),
                      Row(children: <Widget>[
                           Text("男:"),
                          /**
                           * Radio  是个单选按钮组件 多个Radio 组成一个按钮组合集
                           *   1.每个Radio的groupValue属性值必须要一样   才能组成按钮组合集。 所以按钮组集合 最少必须要有两个Radio,否则不成立
                           *  2.如果单个的Radio 的 value值 和groupValue值 相等 就表示 当前这个Radio是被选中状态
                           *  3. onChanged 改变时触发事件  onChanged : (value){setState(() { this.sex = value;print("${sex}");});}
                           * */
                           //value 单选的值  groupValue 选择组的值
                           Radio(value: 1, groupValue: this.sex, onChanged: (value){setState(() { this.sex = value;print("${sex}");});}),
                           SizedBox(width: 20),
                           Text("女:"),
                          Radio(value: 2, groupValue: this.sex, onChanged: (value){setState(() {this.sex = value;print("${sex}");});}),
                          Row(children: <Widget>[Text("${this.sex}"),Text(this.sex==1?"男":"女")]),


                         ]),
                      SizedBox(height: 40),
                      Column(children: <Widget>[
                        /** RadioListTile
                         * value true 或者 false 也可以 1 2 表示
                         *  selected 表示是否被选中 true 选中  false 未选中  必须要添加该属性 被选中 还会发生状态的改变 颜色 图标
                         * */
                        RadioListTile(value: 1, groupValue: this.sex,title: Text("标题"),subtitle: Text("这是二级标题"),selected:this.sex==1,secondary: Icon(Icons.help), onChanged: (value){setState(() {this.sex=value;});}),
                        RadioListTile(value: 2, groupValue: this.sex,title: Text("标题"),subtitle: Text("这是二级标题"),selected:this.sex==2,secondary: Image.network(this.sex==1?Constant.imageUrl1:Constant.imageUrl2), onChanged: (value){setState(() {this.sex=value;});})
                      ]),
                      SizedBox(height: 40),
                  /**
                   * 开关 Switch
                   * value 单选的值  true false
                   * activeColor 被选中的颜色、背景颜色
                   * */
                    Switch(value: this.flag, activeColor:Colors.green,onChanged: (value){setState(() {
                     this.flag=value;print("开关按钮:${value}");
                   }); })

                ]



                ))


              );
      }
     }


     案例2:一个学生登录系统：使用了TextField Radio   Checkbox   
          见 FormStudentPage.dart
              
        注意事项:
		如果调用 _sexChanged() 表示调用这个方法
  		      
        如果 _sexChanged 表示把这个方法_sexChanged() 赋值给onChanged:
 		
        即  onChanged: this._sexChanged





        class FormStudentPage extends StatefulWidget {
		  static const  ROUTE_NAME ="/formStudentPage";
		 
		  FormStudentPage({Key key}) : super(key: key);
		
		  @override
		  _FormStudentPageState createState() => _FormStudentPageState();
		}
		
		class _FormStudentPageState extends State<FormStudentPage> {
		  String username;
		  var sex=1;
		  String info="";
		  List hobby=[{"checked":false, "title":"吃饭"},{"checked":true, "title":"睡觉"},{"checked":false, "title":"写代码"}];
		  List<Widget> _getHobby() {
		    List<Widget> tempList=[];
		    //这里只能用for 我们需要key i
		    for (var i=0;i<this.hobby.length;i++) {
		      tempList.add(Row(children: <Widget>[
		        Text(this.hobby[i]["title"]+":",style: TextStyle(color: this.hobby[i]["checked"]?Colors.red:Colors.grey)),
		        Checkbox(value: this.hobby[i]["checked"],activeColor:this.hobby[i]["checked"]?Colors.red: Colors.grey,
		            onChanged:(value){setState(() {this.hobby[i]["checked"]=value;});})]));
		    }
		    return tempList;
		  }
		
		
		  /**
		  *   如果调用 _sexChanged() 表示调用这个方法
		   * 如果 _sexChanged 表示把这个方法_sexChanged() 赋值给onChanged:
		  *    即  onChanged: this._sexChanged
		  * */
		  void _sexChanged(value) {
		    setState(() {
		      this.sex = value;
		    });
		  }
		
		
		
		  @override
		  Widget build(BuildContext context) {
		    return Scaffold(
		      backgroundColor: Colors.pink.shade50,
		       appBar: AppBar(  title: Text("学员信息登记系统")),
		      body: Padding(
		           padding: EdgeInsets.all(20),
		           child:Column(children: <Widget>[
		                 TextField(
		                   maxLines: 4,
		                   decoration: InputDecoration(icon: Icon(Icons.people,color: Colors.black ),hintText: "请输入用户信息",labelText: "请输入用户信息",border: OutlineInputBorder()),
		                   onChanged: (value){setState(() {this.username = value;});}
		                 ),
		              SizedBox(height:10),
		             Row(mainAxisAlignment :MainAxisAlignment.start,children:
		                   [
		                     SizedBox(width:20),Text("男",style:TextStyle(color: this.sex==1?Colors.pink:Colors.grey)),
		                     //onChanged: (value){setState(() {this.sex=value;});
		                     //注意啦:如果这样
		                     Radio(value: 1,groupValue: this.sex,activeColor:Colors.pink,onChanged: this._sexChanged),
		                     SizedBox(width:20),Text("女",style:TextStyle(color: this.sex==2?Colors.pink:Colors.grey)),
		                     Radio(value: 2,groupValue: this.sex,activeColor:Colors.pink,onChanged: this._sexChanged)
		                   ]),
		             SizedBox(height:40),
		             Column(children: this._getHobby()),
		             TextField(
		                 maxLines: 4,
		                 decoration: InputDecoration(icon: Icon(Icons.info,color: Colors.black ),hintText: "请输入描述信息",labelText: "请输入描述信息",border: OutlineInputBorder()),
		                 onChanged: (value){setState(() {this.info = value;});}
		             ),
		             SizedBox(height:40),
		             Container(width: double.infinity,
		               height: 40,
		                 child: RaisedButton(
		                   child: Text("提交信息"),
		                   color: Colors.blue,
		                   textColor: Colors.white,
		                    onPressed: ( ){
		                     setState(() {
		                     print(this.sex);
		                      print(this.username);
		                      print(this.hobby);
		               });}))
		              ])
		      )
		    );
		  }
		
		 }
          

  


 1.DateTime日期组件
 2.TimeOfDay时间组 件
 3.将flutter 自带日期组件和时间组 件改为中文
   

二十八: 日期组件DateTime 和时间组 件TimeOfDay

  
  
  DateTime日期组件
  
  
  1. 获取当前时间  var now =DateTime.now();
  
  2.将当前时间 转为时间戳  DateTime.now().millisecondsSinceEpoch
 
  3.将某个时间撮转为日期时间  DateTime.fromMillisecondsSinceEpoch(1559967090998)


  4.将日期时间 转为xx年xx月xx日 或 xx-xx-xx 或 xx/xx/xx 格式
   
    formatDate(DateTime.now(), [yyyy, '年', mm, '月', dd, "日"])
    formatDate(DateTime.now(), [yyyy, '-', mm, '-', dd, ])
    formatDate(DateTime.now(), [yyyy, '/', mm, '/', dd,])

  5.flutter原生的日期组件 :showDatePicker()
    
	   showDatePicker()方法是异步的方法,所以要异步调用
	   1.使用then(（result）{})回调方法
	   2.使用 async  await
     
        void _showDatePicker(BuildContext context) {
          showDatePicker(context: context,
            initialDate: _nowDate, //初始化时期 等于当前日期
            firstDate: DateTime(1980),//起始日期
            lastDate: DateTime(2100),  //结束日期
             locale: Locale('zh'),      //非必须  国际汉化
           ).then((result){
             setState(() {
             this._nowDate= result;
           });
             print("输出选择的时期时间${_nowDate}");
        });

        }


      void _showDatePicker2(BuildContext context) async{
          var  result= await  showDatePicker(context: context,
		        initialDate: _nowDate, //初始化时期 等于当前日期
		        firstDate: DateTime(1980),//起始日期
		        lastDate: DateTime(2100),  //结束日期
		        locale: Locale('zh'),      //非必须  国际汉化
		  );
		   setState(() {
		     this._nowDate= result;
		     print("输出选择的时期${_nowDate}");
		   });
		
		  }


 

  TimeOfDay 时间组 件  
   
   1.获取(初始化)当前的时分
   
    var _nowTime=TimeOfDay(hour: DateTime.now().hour, minute: DateTime.now().minute);
  
   2.将获取当前时分进行格式化  
     
    TimeOfDay(hour: DateTime.now().hour, minute: DateTime.now().minute).format(context)
    
   3.flutter原生的时间组件 showTimePicker()
      
      showTimePicker()方法是异步的方法,所以要异步调用
	   1.使用then(（result）{})回调方法
	   2.使用 async  await 



       void _showTimePicker() async {
       var result = await showTimePicker(
         context: context,
         initialTime: _nowTime,
       );
       setState(() {
         this._nowTime=result;
         print("输出选择的时间${_nowTime}");
       });

       }



   
 日期时间组件使用demo  见DatePickPage.dart
	      class _DatePickerPageState extends State<DatePickerPage> {
	  DateTime _nowDate = DateTime.now();
	  var _nowTime=TimeOfDay(hour: DateTime.now().hour, minute: DateTime.now().minute);//初始化当前的时分
	
	  void _showDatePicker(BuildContext context) {
	    /**
	     * showDatePicker()方法是异步的方法,所以要异步调用
	     *  1.使用then(（result）{})回调方法
	     *  2. 使用 async  await
	     * */
	        showDatePicker(context: context,
	            initialDate: _nowDate, //初始化时期 等于当前日期
	            firstDate: DateTime(1980),//起始日期
	            lastDate: DateTime(2100),  //结束日期
	             locale: Locale('zh'),      //非必须  国际汉化
	           ).then((result){
	             setState(() {
	             this._nowDate= result;
	           });
	             print("输出选择的时期时间${_nowDate}");
	        });
	
	  }
	
	
	  void _showDatePicker2(BuildContext context) async{
	    /**
	     * showDatePicker()方法是异步的方法,所以要异步调用
	     *  1.使用then(（result）{})回调方法
	     *  2. 使用 async  await
	     * */
	  var  result= await  showDatePicker(context: context,
	        initialDate: _nowDate, //初始化时期 等于当前日期
	        firstDate: DateTime(1980),//起始日期
	        lastDate: DateTime(2100),  //结束日期
	        locale: Locale('zh'),      //非必须  国际汉化
	  );
	   setState(() {
	     this._nowDate= result;
	     print("输出选择的时期${_nowDate}");
	   });
	
	  }
	
	
	  void _showTimePicker() async {
	       var result = await showTimePicker(
	         context: context,
	         initialTime: _nowTime,
	       );
	       setState(() {
	         this._nowTime=result;
	         print("输出选择的时间${_nowTime}");
	       });
	
	  }
	
	
	 
	
	  @override
	  Widget build(BuildContext context) {
	
	    return Scaffold(
	      appBar: AppBar(title: Text("DatePicker 日期时间 Demo ")),
	       body: Column(
	               mainAxisAlignment: MainAxisAlignment.center,
	                children: <Widget>[
	                Row(
	                    mainAxisAlignment: MainAxisAlignment.center,
	                     children: <Widget>[
	                       //InkWell(类似 button) 有监听事件    onTap(){} 就是他的点击触发事件
	                         InkWell(child: Row(
	                                mainAxisAlignment: MainAxisAlignment.center,
	                                          children: <Widget>[
	                                                Text("${formatDate(_nowDate,[yyyy, "年", mm, "月", dd, "日"])}"),
	                                               Icon(Icons.arrow_drop_down)
	                                            ]
	                                          ),
	                               onTap:  (){
	                                        print("打开日期组件");
	                                        //注意: _showDatePicker2(context) 与 _showDatePicker2 调用一样
	                                        _showDatePicker2(context);
	                                       // _showDatePicker2;
	                                       }
	                                 ),
	                       InkWell(
	                           child: Row(
	                                 mainAxisAlignment: MainAxisAlignment.center,
	                                   children: <Widget>[
	                                      Text("${_nowTime.format(context)}"),
	                                     Icon(Icons.arrow_drop_down)
	                                   ]
	                                ),
	                          onTap:_showTimePicker,
	
	                       )
	                    ]
	
	                )
	             ]
	
	       ));
	  }
	
	 }      






  
二十九:flutter 国际化 项目汉化

 1.在pubspec.yaml 添加(注意对齐方式 否则报错)

	   dependencies:
	  flutter:
	    sdk: flutter
	  flutter_localizations:
	    sdk: flutter
	  flutter_cupertino_localizations: ^1.0.1
 

 2.在跟项目入口给MaterialApp组件配置localizationsDelegates和supportedLocales属性:
    
     MaterialApp(
        localizationsDelegates: [
          //GlobalMaterialLocalizations.delegate 为 Material 组件库提供本地化的字符串和一些其他的值。GlobalWidgetsLocalizations.delegate 为 widgets 库定义了默认的文本排列方向，由左到右或者由右到
          GlobalMaterialLocalizations.delegate,
          GlobalWidgetsLocalizations.delegate,
          GlobalCupertinoLocalizations.delegate,
        ],

      supportedLocales: [
        //Locale 类用来识别用户的语言。
        //Localizations widget 定义了它的子节点的语言环境和依赖的本地化的资源。WidgetsApp 创建了一个本地化的 widget，如果系统的语言环境变化了，它会重建这个 widget。
        const Locale('zh', 'CH'),  // 中文简体
        const Locale('en', 'US'), // 美国英语
      ],
        debugShowCheckedModeBanner: false,  //去掉debug图标
        initialRoute: Tabs.ROUTE_NAME, //初始化底部导航Tabs 路由 页面
        //initialRoute:   ButtonPage.ROUTE_NAME, ////初始化ButtonPage路由 页面
        onGenerateRoute: onGenerateRoute
    );
         




三十:第三方日期时间组件的使用  

   在pubspec.yaml 里添加第三方日期时间组件的依赖

   flutter_cupertino_datetime_picker: ^2.0.1 #第三方日期时间组件选择器

  案例:详见  DatePickerPubPage.dart


三十一:第三方轮播图组件Swiper(有几种轮播图样式)

   在pubspec.yaml 里添加第三方播图组件Swiper的依赖

   flutter_swiper: ^1.1.6  #第三方轮播图组件
 
   案例:详见 SwiperPage1.dart  SwiperPage2.dart  SwiperPage3.dart
       
  github地址:https://github.com/best-flutter/flutter_swiper/blob/master/README-ZH.md



     注意：使用轮播图如何适配所有的屏幕而试图片不变形？
          添加的轮播图会变形 所以我们要给Container 添加子组件为AspectRatio(很有用)， 适配所有的屏幕 不变形
          再在子组件为AspectRatio里 添加子组件 轮播图组件 Swiper

          
         案例:详见 SwiperPage2.dart 


	    class _SwiperPage2State extends State<SwiperPage2> {
		  List<Map> imgList=[
		    {
		      "url":"https://www.itying.com/images/flutter/1.png"
		    },
		    {
		      "url":"https://www.itying.com/images/flutter/2.png"
		    },
		    {
		      "url":"https://www.itying.com/images/flutter/3.png"
		    },
		    {
		      "url":"https://www.itying.com/images/flutter/4.png"
		    }
		  ];
	
	
	
	  @override
	  Widget build(BuildContext context) {
	    return Scaffold(
	      appBar: AppBar(title: Text('2第三方轮播图SwiperPage2演示二')),
	     body:Column(
	         children: <Widget>[
	           //todo 注意:添加的轮播图会变形 所以我们要给Container 添加子组件为AspectRatio(很有用)， 适配所有的屏幕 不变形
	           //再在子组件为AspectRatio里 添加子组件 轮播图组件 Swiper
	         Container(
	              // width:double.infinity,
	                 child: AspectRatio(
	                       aspectRatio: 16/9, //配置AspectRatio里面的Swiper组件的   宽度和高度的  比 16 ：9
	                      child:Swiper(
	                        loop: true, //无线循环模式
	                         autoplay: true, //自动无线轮播
	                        itemBuilder:(BuildContext context,int index){return new Image.network(imgList[index]["url"],fit: BoxFit.cover);} ,
	                        itemCount: imgList.length,
	                        pagination: new SwiperPagination(), //配置底部的分页器(点点)
	                      )
	                      )),
	         Row( mainAxisAlignment: MainAxisAlignment.center,children: <Widget>[  Text("我是一个文本")])]
	     )
	
	    );
	  }
	}







三十二: Dialog
 原生的Dialog组件:
 AlertDialog 
 SimpleDialog
 


     案例:见DialogPage.dart

        1.alert弹出框-AlertDialog

          void _alertDialog(BuildContext context)  async {
         var result  = await showDialog (
       context: context,
       builder: (context) {
        return AlertDialog(
          title: Text('提示信息'),
          content: Text('您确定要删除吗'),
          //但我们点击按钮时 就会把点击的结果只 返回给result 我们就可以输出result 查看结果
          actions: <Widget>[
            FlatButton(
              child: Text('取消'),
              onPressed: () {
                print("取消");
                Navigator.pop(context,'Cancle'); // Dismiss alert dialog
              },
            ),
            FlatButton( child: Text("确定"),
                    onPressed: (){
                     print("确定");
                     Navigator.pop(context,"Ok");
                   } )
          ],
        );
      },
    );

         print(result);
    }


 
     2.select弹出框-SimpleDialog
    
      void _simpleDialog() async {
    var result = await showDialog(
      barrierDismissible:true,   //表示点击灰色背景的时候是否消失弹出框
      context:context,
      builder: (context){
         return SimpleDialog(
           title:Text("选择内容"),

            children: <Widget>[
              SimpleDialogOption(child: Text("Option A"), onPressed: (){print("Option A");Navigator.pop(context,"A");}),
              Divider(),
              SimpleDialogOption(   child: Text("Option B"),onPressed:(){  print("Option B");Navigator.pop(context,"B");}),
              Divider(),
              SimpleDialogOption( child: Text("Option C"),onPressed: (){print("Option C");Navigator.pop(context,"C");}),
              Divider()

            ]
         );
      }
    );
    print(result);
    }





三十三:Toast土司库
 Fluttertoast.showToast()     



   
     案例:见DialogPage.dart


      1.本类中调用: this.showToast(msg:"hello flutter");
  


         //非必选参数
		  //命名可选参数 用{}表示
		  void showToast({String msg=""}) {
		    Fluttertoast.showToast(
		      msg: msg,
		      toastLength: Toast.LENGTH_SHORT,
		      gravity: ToastGravity.BOTTOM,
		      timeInSecForIosWeb: 1,
		      backgroundColor: Colors.black,
		      textColor: Colors.white,
		     fontSize: 16.0
		    );
		  }
  
  

      2.外类中调用土司
           DialogPage().createState().showToast(msg: "3秒后关闭弹框");
         
 


三十四:自定义对话框
    

      详见:MyDialog.dart


        class MyDialog extends Dialog {
	  static const  ROUTE_NAME ="/myDialog";
	  var title;
	  var content;

	  //非必选参数
	  //命名可选参数 用{}表示
	  MyDialog({this.title="",this.content="" });

  	
      //定义一个定时器  弹出对话框 3秒后 关闭对话框
  	    
     void _showTimer( context) {
    //(timer) { } 是个回调函数
     Timer.periodic(
             Duration(milliseconds: 3000),
             (timer) {
                 DialogPage().createState().showToast(msg: "3秒后关闭弹框");
                 Navigator.pop(context);
                 timer.cancel();//取消定时器
             }

              );
      }



	 @override
	  Widget build(BuildContext context) {
	   _showTimer(context);

    return Material(
      type: MaterialType.transparency,  //Material透明的属性
        //Center让组件居中
      child: Center(
	//Center包含子组件Container 里面包含
	// Column组件 包含二个子组件:上区域Padding(包含子组件Stack  Align)   下区域  Container. 组成自定义Dialog
        child: Container(
          height: 300,
          width: 300,
          color: Colors.yellow.shade100,

          child: Column(
         //    mainAxisAlignment: MainAxisAlignment.start,
         //   crossAxisAlignment:CrossAxisAlignment.center,

              children: <Widget>[
                   Padding(
                       padding: EdgeInsets.all(10),
                       child:Stack(alignment:Alignment.center,children: <Widget>[
                                    Align(alignment: Alignment.center,child: Text("${this.title}",maxLines:1,style:TextStyle(color: Colors.black,backgroundColor: Colors.red,fontSize: 25,fontStyle:FontStyle.italic))),
                                    Align(alignment: Alignment.centerRight,child: InkWell(child: Icon(Icons.close),onTap: (){Navigator.pop(context);}))])
                     ),
                   Divider(),
                  Container(
                      color: Colors.white,
                      width: double.infinity,

                      padding: EdgeInsets.all(10),
                      child: Text( "${this.content}", maxLines:8,overflow:TextOverflow.ellipsis,textAlign: TextAlign.left,style: TextStyle(color: Colors.grey,fontSize: 15))
                   )
             ]
          )
	        )
	      )
	    );
	  }
    }





三十五:  








三十六:Flutter 的json字符串和MAP集合互转


  Map  








三十七:网络请求

   使用Http
   
     Http的get请求
   
        Map map =  {"name":"张三", "age":20, "_id": "5ac0896ca880f20358495508"};
		  var  jsonStr = '{"name":"张三","age":20,"_id":"5ac0896ca880f20358495508","work":["程序员","送外卖"]}';
		  var _news="";
		  void _getGetData() async{
		    var url = Uri.https('www.googleapis.com', '/books/v1/volumes', {'q': '{http}'});
		
		    var response= await http.get(url);
		     if(response.statusCode == 200){
		       setState(() {
		         var  jsonResponse =  json.decode(response.body);
		         print(jsonResponse);
		         var  jsonResponseMap = jsonResponse as  Map<String, dynamic>;
		         var itemCount = jsonResponseMap["totalItems"];
		         this._news=itemCount;
		         print('Number of books about http: $itemCount.');
		   });
		
		     }else{
		       print('get Request failed with status: ${response.statusCode}.');
		     }
		
		  }



         Http的post请求 
		 
            void _postData()  async{
		    var url = Uri.parse('https://example.com/whatsit/create');
		    var response = await http.post(url,body:  {'username': '张三', 'age': '20'});
		      if(response.statusCode==200){
		        var  jsonResponse =  json.decode( response.body);
		           print(jsonResponse);
		
		      }else{
		        print('post  Request failed with status: ${response.statusCode}.');
		      }
		
		  }




 使用Dio 
dio 是一个强大的 Dart Http 请求库，支持 Restful API、FormData、拦截器、请 求取消、Cookie 管理、文件上传/下载、超时、自定义适配器等... 

     所有案例demo 详见  
     https://github.com/flutterchina/dio/blob/master/README-ZH.md#%E7%A4%BA%E4%BE%8B
     
      Dio 的get请求
   

         List _list=[];
		  //get请求数据
		  Response response;
		  void _getData()  async{
		   response = await Dio().get("http://192.168.0.5:3000/news?id=12&name=wendu");
		    response = await Dio().get("http://192.168.0.5:3000/news",queryParameters:{'id': 12, 'name': 'wendu'});
		    print("返回Dio get请求的字符串数据:${response.data}");
		
		  setState(() {
		    Map   responsJesonDecodeMap=    json.decode(response.data)  as Map<String, dynamic>;
		    print("responsJesonDecodeMap is Map ${responsJesonDecodeMap is Map}    返回的原生数据json字符串结果  解析为Map集合: ${responsJesonDecodeMap}");
		    this._list=responsJesonDecodeMap["result"];
		    print("输出集合${_list}");
		  });
		  }

         
         把_list 集合 赋值给组件进行展示
            
         Container(child:this._list.length>0?
                   ListView(children: this._list.map((value){return ListTile(title: Text(value["title"]));}).toList())
                 :Text("加载中...")
  
         


    Dio 的post 请求

    
       Map<String, dynamic> postMapParams= {"username":"哈哈哈", "age":20};
		  //post 提交数据
		  void _postData()  async{
		    var apiUrl="http://192.168.0.5:3000/dologin";
		    Response response =    await Dio().post(apiUrl,data:postMapParams);
		    print("输出Dio post请求数据:${response.data}");
		  }




   

  


三十八:下拉刷新和上拉加载更多
  详见:RefreshIndicatorPage.dart
    
  


  
  使用第三方库实现下拉刷新和上拉加载更多
     
      详见:https://github.com/xuelongqy/flutter_easyrefresh
    



三十八:Flutter WebView 组件 flutter_inappbrowser 加载远程 web 页 面


   注意:  minSdkVersion 17 必须>17

      案例: 详见 ItemDetailWebViewPage

         class _ItemDetailWebViewPageState extends State<ItemDetailWebViewPage> {
			  _ItemDetailWebViewPageState( this.arguments);
			  Map  arguments;
			   var _flag=false;
			 @override
			  void initState() {
			    // TODO: implement initState
			    super.initState();
			    print("输出传递过来的参数Map:${arguments}");
			  }
			
			  @override
			  Widget build(BuildContext context) {
			    return Scaffold(
			        appBar: AppBar(title: Text("新闻详情页 webView")),
			        body:  Column(children: <Widget>[
			            this._flag?CommonWidgetUtil.showLoadingWidget():Text(""),
			             Expanded(
			                 child: InAppWebView(
			                     initialUrl: "http://www.phonegap100.com/newscontent.php?aid=${this.arguments["aid"]}",
			                     //加载网页的进度
			                       onProgressChanged:(InAppWebViewController controller, int progress) {
			                         print("加载进度${progress / 100}");
			                         if ((progress / 100) > 0.999) { //加载完毕关闭加载圈圈
			                           setState(() {
			                             this._flag = false;
			                           });
			                         }
			                       }
			
			                   )
			
			             ),
			
			        ]

				     ));
				  }
				
				
				
				
				}
   
 



三十九:  获取设备信息 

   dependencies:
   device_info: ^2.0.1
   

    
       案例；详见 DevicePage.dart



   


四十:flutter 高德定位 获取定位信息


   在Androuid 项目里 

    在高德平台申请Key 
    
     在gradle 的

    defaultConfig里添加申请的key 
          manifestPlaceholders = [
                AMAP_KEY : "753cf3200366d516b033760c9d316980", /// 高德地图key
         ]
    

    dependencies里添加依赖
    implementation 'com.amap.api:location:latest.integration'
  


 在flutter 项目里的pubspec.yaml文件里添加定位依赖
     
      amap_location: ^0.2.0 #高德定位  

       案例:详见 LocationPage.dart




 四十一: flutter 第三方权限申请   
         在pubspec.yaml文件里添加权限依赖
         super_easy_permissions: any  #申请权限

          案例详见:LocationPage.dart
           https://pub.dev/packages/super_easy_permissions/example 


        
  

四十二:  flutter 本地缓存  SharedPreferences
        
 
    注意:因为shared_preferences提供的获取缓存的数据的函数是async(异步)的, 所以我们不能立马就能获取到缓存的数据结果.
     所以需要通过.then()方法来保证其一定获取到的缓存数据结果后    才能再进行后续其他操作.  获取到value后, 存至_savedValue.
   
    案例详见：  main40_SharedPreferencesDemo.dart
               SharedPreferencesPage.dart


    封装的SharedPreferences工具类: SharedPreferencesUtil




四十三:  监听网络NetWork状态的变化
     
         详见:NetworkPage.dart




四十四:  第三方的视频播放组件(带有进度条时间暂停):Chewie


         详见:ChewieVideoPage.dart



四十四五:相机拍照 相册选择图片 录制视频   播放视频(原生的) 

        详见: ImagePickerPage.dart



四十六:  APK版本更新
  需要4个依赖:
    package_info: ^2.0.2  #检测app的版本号
    path_provider: ^2.0.2  #获取APK文件手机本地存储的路径
    flutter_downloader: ^1.6.1   #下载服务器文最新版本APK件
    open_file: ^3.2.1  #打开文APK件插件
 

 注意打包安卓realse APK 注意修改 Android 的grade的 versionName  versionCode



 注意清单文件AndroidManifest.xml的 配置:权限的添加 和相关的配置类

      <!--为了处理通知上的点击操作以在 Android 上打开下载的文件，
         您需要添加一些额外的配置。将以下代码添加到您的AndroidManifest.xml：-->
        <provider
            android:name="vn.hunghd.flutterdownloader.DownloadedFileProvider"
            android:authorities="${applicationId}.flutter_downloader.provider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/provider_paths"/>
        </provider>



         <provider
            android:name="androidx.work.impl.WorkManagerInitializer"
            android:authorities="${applicationId}.workmanager-init"
            tools:node="remove"
            android:enabled="false"
            android:exported="false"/>



        <provider
            android:name="vn.hunghd.flutterdownloader.FlutterDownloaderInitializer"
            android:authorities="${applicationId}.flutter-downloader-init"
            android:exported="false">
            <!-- changes this number to configure the maximum number of concurrent tasks -->
            <meta-data
                android:name="vn.hunghd.flutterdownloader.MAX_CONCURRENT_TASKS"
                android:value="5" />
        </provider>



  

注意: package_info: ^2.0.2 的PackageInfo
  		
		 获取的的_packageInfo.version 指的是pubspec.yaml里的version: 5.0.0+7 的  5.0.0


        _packageInfo.buildNumber 指的是pubspec.yaml里的version: 5.0.0+7 的  7


      
 
注意:在 pubspec.yaml里version: 5.0.0+7   
    
     注意:5.0.0  等同于Anddroid项目的 android.versionName     +7 等同于android.versionCode  



案例: 见ApkUpdateVersionPage.dart
  



四十七: flutter 的url_launcher 

Flutter 调用 url_launcher 模块打开外部浏 览器 打开外部应用 拨打电话 发送短信