
一:NestedScrollView如何滚动到顶部

		第一种方式:nestedScrollView.scrollTo(0,0)

		第二种方式:滚动操作
                 scrollView.fullScroll(ScrollView.FOCUS_DOWN);滚动到底部
                  scrollView.fullScroll(ScrollView.FOCUS_UP);滚动到顶部

二:NestedScrollView为何有时滚不到顶部
       在使用NestedScrollView嵌套RecyclerView中，首先会出现的问题就是RecyclerView滑动会出现卡顿，没有惯性滑动的效果。RecyclerView嵌套在NestedScrollView中会出现无法滚动到顶部问题。    
       所以,设置RecyclerView不处理滚动事件，全部交由ScorllView去处理，这样就解决了滑动卡顿的问题。
        recycler.setNestedScrollingEnabled(false); 

























