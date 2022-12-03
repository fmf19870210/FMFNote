一:RecyclerView简单用法流程

					 recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
					 LinearLayoutManager layoutManager = new LinearLayoutManager(this);
					 //设置layoutManager
					 recyclerView.setLayoutManager(layoutManager);
					 final RecycleViewItemLine line = new RecycleViewItemLine(this, LinearLayout.HORIZONTAL,1,this.getResources().getColor(R.color.colorAccent));
					 //设置添加分割线
					 recyclerView.addItemDecoration(line);
					 //创建adapter
					 adapter = new MultipleItemAdapter(this);
					 //设置adapter
					 recyclerView.setAdapter(adapter);
					 //添加数据并且刷新adapter
					 adapter.addAll(mDataList);
					 adapter.notifyDataSetChanged();
					
					
					 public class MultipleItemAdapter extends RecyclerView.Adapter<recyclerview.viewholder> {
					
					  public static enum ITEM_TYPE {
					          ITEM_TYPE_IMAGE,
					          ITEM_TYPE_TEXT
					      }
					
					 private final LayoutInflater mLayoutInflater;
					    private final Context mContext;
					    private ArrayList<String> mTitles;
					
					public MultipleItemAdapter(Context context) {
					        mContext = context;
					        mLayoutInflater = LayoutInflater.from(context);
					    }
					
					
					
					 @Override
					    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
					        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
					            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_image, parent, false));
					        } else {
					            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
					        }
					    }
					
					
					 @Override
					    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
					        if (holder instanceof TextViewHolder) {
					            ((TextViewHolder) holder).mTextView.setText(mTitles[position]);
					        } else if (holder instanceof ImageViewHolder) {
					            ((ImageViewHolder) holder).mTextView.setText(mTitles[position]);
					        }
					    }
					
					
					  @Override
					     public int getItemViewType(int position) {
					         return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
					     }
					
					
					 @Override
					    public int getItemCount() {
					        return mTitles == null ? 0 : mTitles.length;
					    }
					
					
					    public void addAll(ArrayList<String> list){
					            if(mTitles!=null){
					                mTitles.clear();
					            }else {
					                mTitles = new ArrayList<>();
					            }
					            mTitles.addAll(list);
					        }
					
					
					
					public static class TextViewHolder extends RecyclerView.ViewHolder {
					        @InjectView(R.id.text_view)
					        TextView mTextView;
					        TextViewHolder(View view) {
					            super(view);
					            ButterKnife.inject(this, view);
					        }
					    }
					
					
					
					 public static class ImageViewHolder extends RecyclerView.ViewHolder {
					        @InjectView(R.id.text_view)
					        TextView mTextView;
					        @InjectView(R.id.image_view)
					        ImageView mImageView;
					        ImageViewHolder(View view) {
					            super(view);
					            ButterKnife.inject(this, view);
					        }
					    }
					}
					
					
					}



 

二:.RecyclerView.Adapter的介绍:

  1.一般常用的重写方法有以下这么几个：

			 public VH onCreateViewHolder(ViewGroup parent, int viewType)
			创建Item视图，并返回相应的ViewHolder
			public void onBindViewHolder(VH holder, int position)
			绑定数据到正确的Item视图上。
			public int getItemCount()
			返回该Adapter所持有的Itme数量
			public int getItemViewType(int position)
			用来获取当前项Item(position参数)是哪种类型的布局


2.notifyDataSetChanged()刷新数据
                 
            
               当时据集合发生改变时，我们通过调用.notifyDataSetChanged()，来刷新列表，因为这样做会触发列表的重绘，
                public final void notifyItemInserted(int position) 向指定位置插入Item
				public final void notifyItemRemoved(int position) 移除指定位置Item
				public final void notifyItemChanged(int position) 更新指定位置Item
              
               notifyDataSetChanged()方法的源码分析:通过查看notifyDataSetChanged()源码可知
    
                被观察者AdapterDataObservable 
                观察者AdapterDataObserver集合
                一旦被观察者发送来的数据源发生变更,   就会立马通知观察者更新数据 ,观察者调用notify**方法( mObservers.get(i).onChanged()) ,更新UI界面数据





三:RecyclerView.Adapte的介绍:
          
              1.需要我们复写两个方法：  
				onCreateViewHolder()
				onBindViewHolder()
 

 四:LayoutManager的介绍(略)


五:SpanSizeLookup的介绍使用
      
          1.作用:GridLayoutManager.SpanSizeLookup 实现指定某个item所占多少列数
 
		              GridLayoutManager manager = new GridLayoutManager(this, 6);
						manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
						    @Override
						    public int getSpanSize(int position) {
						        SpanModel model = mDataList.get(position);
						        if (model.getType() == 1) {
						            return 6;//getSpanSize返回6，就表示当前position索引处的item占用6列
						        } else if(model.getType() == 2){
						            return 3; //getSpanSize返回3，就表示当前position索引处的item占用3列
						        }else if (model.getType() == 3){
						            return 2;
						        }else if (model.getType() == 4){
						            return 2;
						        } else {
						            return 1;
						        }
						    }
						});
   

                          
					
   ![](https://camo.githubusercontent.com/12f573ffb00195d0ba5140eae7c27187c59afcdb/68747470733a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f343433323334372d356133636663366534343739356139352e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430) 





六：RecyclerView上拉加载解析
    
  1.recyclerView添加滑动监听事件:

    // 实现上拉加载重要步骤，设置滑动监听器，RecyclerView自带的ScrollListener
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

    //用来标记是否正在向上滑动
    private boolean isSlidingUpward = false;

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        // 当不滑动的时候
        // 在newState为滑到底部时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            //获取最后一个完全显示的itemPosition
            int lastItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
            int itemCount = layoutManager.getItemCount();
            //int itemCount1 = adapter.getItemCount();

            // 判断是否滑动到了最后一个item，并且是向上滑动
            if (lastItemPosition == (itemCount - 1) && isSlidingUpward) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateRecyclerView(datas);
                    }
                }, 2000);
            }
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
        isSlidingUpward = dy > 0;
    }
    });
  


2.  recyclerView上拉加载分页数据 
        然后看一下updateRecyclerView的方法。注意这里的刷新数据，可以直接用notifyItemRangeInserted方法，不要用notifyDataSetChanged方法。 
			/**
			 * 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
			 */
			public void updateList(List<PersonData> newDatas, boolean hasMore) {
			    int size = data.size();
			    // 在原有的数据之上增加新数据
			    if (newDatas != null) {
			        data.addAll(newDatas);
			        this.hasMore = hasMore;
			        notifyItemRangeInserted(size,newDatas.size());
			    }
			}      
    

  3.添加上拉加载的底部footer布局

 4.  显示和隐藏footer布局
		一般情况下，滑动底部最后一个item时，然后显示footer上拉加载布局，然后让其显示加载500毫秒，最后加载出下一页集合数据后,再把footer布局隐藏起来     








七:RecyclerView的优化
      1.RecyclerView滑动卡顿的优化:            
     
             第一种：嵌套布局滑动冲突 
				导致嵌套滑动难处理的关键原因在于当子控件消费了事件, 那么父控件就不会再有机会处理这个事件了, 所以一旦内部的滑动控件消费了滑动操作, 外部的滑动控件就再也没机会响应这个滑动操作了
             解决方案: RecyclerView 滑动时不让 Glide 加载图片。滚动停止后才开始恢复加载图片。
                  //RecyclerView.SCROLL_STATE_IDLE //空闲状态
				 //RecyclerView.SCROLL_STATE_FLING //滚动状态
				//RecyclerView.SCROLL_STATE_TOUCH_SCROLL //触摸后状态
				recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
				    @Override
				    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
				        super.onScrollStateChanged(recyclerView, newState);
				        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
				            LoggerUtils.e("initRecyclerView"+ "恢复Glide加载图片");
				            Glide.with(ImageBrowseActivity.this).resumeRequests();
				        }else {
				            LoggerUtils.e("initRecyclerView"+"禁止Glide加载图片");
				            Glide.with(ImageBrowseActivity.this).pauseRequests();
				        }
				    }
				});



            第二种：嵌套布局层次太深，比如六七层等 
				 测量，绘制布局可能会导致滑动卡顿	
            第三种：比如用RecyclerView实现画廊，加载比较大的图片，如果快速滑动，则可能会出现卡顿，主要是加载图片需要时间 
            第四种：在onCreateViewHolder或者在onBindViewHolder中做了耗时的操作(I/O读写，Bitmap解码)导致卡顿 


             

   2.item点击事件放在哪里优化：
 
       rv设置item条目点击事件,可以放在onCreateViewHolder中写(放在这里最好),放在onBindViewHolder中写,  放在ViewHolder中写


  3.item拖拽排序与滑动删除优化：
  4.rv的布局优化:
      (1)减少 xml 文件 inflate 时间
      (2)减少 item里的View 对象的创建,大量的 View 的创建也会消耗大量时间
 
   5. RecycleView与NestedScrollView的嵌套滑动卡顿优化：
  





八:关于RecyclerView问题汇总 


 1.Recyclerview.getLayoutPosition()问题   

2.RecyclerView使用Glide加载图片导致图片错乱问题解决

      (1)导致图片加载后出现错乱的原因:
              因为有ViewHolder的重用机制，每一个item在移除屏幕后都会被重新使用以节省资源，避免滑动卡顿。而在图片的异步加载过程中，从发出网络请求到完全下载并加载成Bitmap的图片需要花费很长时间，而这时候很有可能原先需要加载图片的item已经划出界面并被重用了。而原先下载的图片在被加载进ImageView的时候没有判断当前的ImageView是不是原先那个要求加载的，故可能图片被加载到被重用的item上，就产生了图片错位的问题。解决思路也很简单，就是在下载完图片，准备给ImageView装上的时候检查一下这个ImageView。
      (2)解决方案:使用settag（key，value）方式进行设置标签。当异步请求回来图片的时候对比下tag是否一样在判断是否显示图片
            //给ImageView打上Tag作为特有标记
				imageView.setTag(tag);
				 
				//下载图片
				loadImage();
				 
				//根据tag判断是不是需要设置给ImageView
				if(tag == iamgeView.getTag()) {
				    imageView.setBitmapImage(iamge);
				}
				      
         






  

九:RecyclerView滑动冲突

    


1.ScrollView嵌套RecyclerView滑动冲突问题   
         问题:NestedScrollView中嵌入一个或多个RecyclerView会出现页面滑动卡顿,ScrollView高度显示不正常,RecyclerView内容显示不全,NestedScrollView滚动不到顶部等问题
         

           解决方案: (1) 去除recyclerview的焦点  
                      recyclerview.setFocusableInTouchMode(false);// 去除rv的触摸获取焦点的功能
                      recyclerview.requestFocus();

                     (2)  让RecyclerView不处理滚动事件，全部交由ScorllView去处理，这样就解决了滑动卡顿的问题
                            recyclerView.setHasFixedSize(true);
							recyclerView.setNestedScrollingEnabled(false);
 
                      
                    (3) 让外面的控件 ScrollView处理滑动事件, 直接拦截滑动事件，在onInterceptTouchEvent()方法里,
                        判断是否滑动，若滑动就拦截滑动事件，返回true 
                        不向下分发给 RecyclerView(rv不处理滑动事件)，




                    public class NoNestedScrollview extends NestedScrollView {

					    private int downX;
					    private int downY;
					    private int mTouchSlop;
					    
					    public NoNestedScrollview(Context context) {
					        super(context);
					        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
					    }
					    
					    public NoNestedScrollview(Context context, AttributeSet attrs) {
					        super(context, attrs);
					        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
					    }
					    
					    public NoNestedScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
					        super(context, attrs, defStyleAttr);
					        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
					    }
					    
					    @Override
					    public boolean onInterceptTouchEvent(MotionEvent e) {
					        int action = e.getAction();
					        switch (action) {
					            case MotionEvent.ACTION_DOWN:
					                downX = (int) e.getRawX();
					                downY = (int) e.getRawY();
					                break;
					            case MotionEvent.ACTION_MOVE:
					                //判断是否滑动，若滑动就拦截事件
					                int moveY = (int) e.getRawY();
					                if (Math.abs(moveY - downY) > mTouchSlop) {
					                    return true;
					                }
					                break;
					            default:
					                break;
					        }
					        return super.onInterceptTouchEvent(e);
					    }
					}
 

                    