  一:CollapsingToolbarLayout是什么

	CollapsingToolbarLayout通常用来在布局中包裹一个Toolbar，以实现具有“折叠效果“”的顶部栏。它需要是AppBarLayout的直接子View，这样才能发挥出效果。


二:CollapsingToolbarLayout包含以下特性

   	Collasping title（可折叠标题）：当布局完全可见时，这个标题比较大；当折叠起来时，标题也会变小。标题的外观可以通过expandedTextAppearance和collapsedTextAppearance属性来调整。