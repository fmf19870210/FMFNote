

一:AppBarLayout是什么
     1.定义:AppBarLayout是一个垂直的LinearLayout.AppBarLayout的子View应该声明想要具有的“滚动行为”，这可以通过layout_scrollFlags属性或是setScrollFlags()方法来指定。AppBarLayout只有作为CoordinatorLayout的直接子View时才能正常工作，
     2.如何滚动AppBarLayout的子view 
        在CoordinatorLayout布局中提供一个可滚动的scrolling view。让scrolling view和AppBarLayout之间的关联。
        通过将scrolling view的Behavior设为AppBarLayout.ScrollingViewBehavior来建立。 
      
      
二: app bar的概念:
        1. app bar是Material Design中的一个概念，我们可以把它看做是一种ToolBar。
        我们把TooBar套上一层AppBarLayout，就能把顶部栏玩出各种花样(各种滚动手势scrolling gestures).
        2. 滚动手势scrolling gestures:
            向上滚动可滚动View时，ToolBar会消失；向下滚动可滚动View时，ToolBar出现.
           ToolBar本身可没有这个能耐，我们通过为它包上一层AppBarLayout，并为ToolBar指定一个滚动行为behavior，
          就能够让ToolBar随着下面的可滚动View(RecyleView)的滚动而发生滚动。可滚动View(RecyleView)也就是我们上面提到的scrollingview(RecyleView)。
            
          
       3.通过为AppBarLayout的子View(ToolBar)设定不同的layout_scrollFlags值.可以定义不同的滚动行为
 

    

三:AppBarLayout属性介绍
       layout_scrollFlags 
        1.scroll：设成这个值的效果就好比AppBarLayout和scrolling view是“一体”的
        2.exitUntilCollapsed:当AppBarLayout离开屏幕时，会被“折叠”直到达到其最小高度。
        3.enterAlways：当scrolling view向下滚动时，本View会一起跟着向下滚动。实际上就好比我们同时对scrolling view和本View进行向下滚动，与                    exitUntilCollapsed不同，当scrolling view一开始滚动，ToolBar便已开始跟着滚动，而无需scrolling view将其内容滚动到顶部。     
      



