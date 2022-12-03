一:CoordinatorLayout是什么

			1.定义:
             CoordinatorLayout 就是一个 ViewGroup，然后可以作为一个容器 ViewGroup指定与 child 的一些交互规则。 实现了 NestedScrollingParent 接口

            通过协调调度子布局的形式实现触摸影响布局的形式产生动画效果.
			CoordinatorLayout通过设置子View的 Behaviors来调度子View.
			AppBarLayout.Behavior, AppBarLayout.ScrollingViewBehavior, FloatingActionButton.Behavior, SwipeDismissBehavior 


			2.CoordinatorLayout作为应用顶层的布局（必须是根部局），提供交互行为.
			通过给子View(RecyclerView，NestedScrollView、TabLayout)设定Behavior可以实现他们的交互行为。
			Behavior能实现一系列的交互行为和布局变化，包括侧滑菜单、可滑动删除的UI元素、View之间跟随移动。

			3.怎么实现复杂效果
			CoordinatorLayout为我们提供了一个叫做Behavior的东西，我们基本上的复杂交互都是使用Behavior来协调完成。


           

二:CoordinatorLayout.Behavior 
          Behavior 本身是一个抽象类，它的实现类都是为了能够让用户作用在一个 View 上进行拖拽、滑动、快速滑动等手势。如果自己要定制某个交互动作，就需要自己实现一个 Behavior。  


                                    