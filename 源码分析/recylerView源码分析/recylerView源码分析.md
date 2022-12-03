

一:RecyclerView 常用的几个刷新模式


  notifyDataSetChanged()   刷新全部的item

		   mDataList.addAll(addData());
		   mRvAdapter.notifyDataSetChanged();


  


  notifyItemChanged(int)   局部刷新指定的item  



notifyItemChanged(int, Object)  局部刷新指定的数据







  notifyItemRangeChanged(int,int) 从指定的位置开始刷新指定个item  这个刷新onBindViewHolder方法，position才能保持一直
   
    
      mDataList.remove(1);
    // 移除position=1位置的item 
    mRvAdapter.notifyItemRemoved(1);
    //notifyItemRangeChanged(1, mDataList.size() - 1)记得调用   否者RecyclerView会报错,(坑记得要填)
    mRvAdapter.notifyItemRangeChanged(1, mDataList.size() - 1);





notifyItemInserted(int)、notifyItemMoved(int)、notifyItemRemoved(int) 
  插入、移动指定位置的item，并刷新

      //删除某个item
      mDataList.remove(position); 
      notifyItemMoved(position)  
     指定移除某个item(自带刷新动画)  mDataList数据一定要同步更新,否者RecyclerView会报错,(坑记得要填)
     mRvAdapter.notifyItemRangeChanged(position, mDataList.size() - position);


 

      //插入一条数据
     Data data=new Data(); 
     mDataList.add(1,data);
     mRvAdapter.notifyItemInserted(1);//插入一条数据
     mRvAdapter.notifyItemRangeChanged(1, mDataList.size() + 1);  //千万别忘了，否者会有多个item同一个position（后果很严重的哦）。



        


notifyItemMoved(int fromPosition, int toPosition) 
从指定位置到指定位置的移动，数据更新加移动动画 配合notifyItemRangeChanged一起使用







   二:几个关于position的区别

getLayoutPosition()


getAdapterPosition()






           