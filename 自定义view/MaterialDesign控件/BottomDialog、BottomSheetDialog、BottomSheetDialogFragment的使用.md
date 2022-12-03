

 一:bottomDialog （有缺陷）

依赖于CoordinatorLayout和BottomSheetBehavior,需要将底部菜单作为CoordinatorLayout的子View，并且需要设置app:layout_behavior="@string/bottom_sheet_behavior",适合固定的底部菜单，但是不够灵活，需要依赖父布局和behavior；
 


二:bottomSheetDialog
 BottomSheetDialog是support.v7下的扩展类，是Dialog的子类；bottomDialog依赖于CoordinatorLayout和behavior
  当我们通过setContentView方法传入自定义布局的时候，它会将这个布局使用CoordinatorLayout包裹起来，所以当使用BottomSheetDialog的时候，底部菜单和根布局并不属于同一个window；
  Dialog的根节点其实并不是通过setContentView()传入的View，它实际上是用CoordinatorLayout把它包装了起来，这才实现了拖动展开和隐藏的行为。
 



 1.使用父布局CoordinatorLayout实现布局
 
      <?xml version="1.0" encoding="utf-8"?>
		<android.support.design.widget.CoordinatorLayout 
		    xmlns:android="http://schemas.android.com/apk/res/android"
		    xmlns:app="http://schemas.android.com/apk/res-auto"
		    xmlns:tools="http://schemas.android.com/tools"
		    android:layout_width="match_parent"
		    android:layout_height="match_parent"
		    tools:context="com.androidwanga.serenitynanian.serenityproject.BottomDialogActivity">
		
		    <!--底部菜单布局-->
		    <include layout="@layout/layout_bottom_sheet_linear" />
		
		</android.support.design.widget.CoordinatorLayout>

  2.使用父布局LinearLayout实现布局
        **注意：
           (1)底部菜单必须设置：app:layout_behavior,只有设置了才能拖拽打开或隐藏，否则和普通的布局没什么差别；  
          （2）必须设置peekHeight高度，否则在底部菜单直接用自己的默认的高度；
           (3)是否支持隐藏，如果设置了app:behavior_hideable 为false或者不设置，那么调用setState(BottomSheetBehavior.STATE_HIDDEN)没有效果；

                  <?xml version="1.0" encoding="utf-8"?>
							<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
							    xmlns:app="http://schemas.android.com/apk/res-auto"
							    android:layout_width="match_parent"
							    android:layout_height="match_parent"
							    app:layout_behavior="@string/bottom_sheet_behavior"
							    app:behavior_hideable = "true"
							    app:behavior_peekHeight="66dp"
							    android:orientation="vertical">
							
							    <LinearLayout
							        android:layout_width="match_parent"
							        android:layout_height="wrap_content"
							        android:gravity="center_vertical">
							
							        <ImageView
							            android:layout_width="wrap_content"
							            android:layout_height="wrap_content"
							            android:src="@mipmap/ic_launcher_round" />
							
							        <TextView
							            android:layout_width="wrap_content"
							            android:layout_height="wrap_content"
							            android:layout_marginLeft="12dp"
							            android:text="@string/app_name" />
							    </LinearLayout>
							
							    <View
							        android:layout_width="match_parent"
							        android:layout_height="1dp"
							        android:background="#999999" />
							
							    <LinearLayout
							        android:layout_width="match_parent"
							        android:layout_height="wrap_content"
							        android:gravity="center_vertical">
							
							        <ImageView
							            android:layout_width="wrap_content"
							            android:layout_height="wrap_content"
							            android:src="@mipmap/ic_launcher_round" />
							
							        <TextView
							            android:layout_width="wrap_content"
							            android:layout_height="wrap_content"
							            android:layout_marginLeft="12dp"
							            android:text="@string/app_name" />
							    </LinearLayout>
							
							    <View
							        android:layout_width="match_parent"
							        android:layout_height="1dp"
							        android:background="#999999" />
							
							</LinearLayout>


 3.使用Recycler实现BottomDialog 布局
      

 
                 <?xml version="1.0" encoding="utf-8"?>
				<android.support.design.widget.CoordinatorLayout
				    xmlns:android="http://schemas.android.com/apk/res/android"
				    xmlns:app="http://schemas.android.com/apk/res-auto"
				    xmlns:tools="http://schemas.android.com/tools"
				    android:layout_width="match_parent"
				    android:layout_height="match_parent"
				    														     															tools:context="com.androidwanga.serenitynanian.serenityproject.BottomDialogActivity">
				
				    <!--底部菜单布局-->
				
				    <!--LinearLayout底部布局菜单-->
				    <!--<include layout="@layout/layout_bottom_sheet_linear" />-->
				
				    <!--RecyclerView底部布局菜单-->
				    <include layout="@layout/layout_bottom_dialog_recycler"/>
				
				
				</android.support.design.widget.CoordinatorLayout>

                底部菜单的布局如下：

                <?xml version="1.0" encoding="utf-8"?>
				<android.support.v7.widget.RecyclerView
				    xmlns:android="http://schemas.android.com/apk/res/android"
				    android:layout_width="match_parent"
				    android:layout_height="match_parent"
				    android:id="@+id/bottom_dialog_recyclerview"
				    xmlns:app = "http://schemas.android.com/apk/res-auto"
				    app:layout_behavior="@string/bottom_sheet_behavior"
				    app:behavior_peekHeight = "100dp"
				    app:behavior_hideable = "true"
				    >
				</android.support.v7.widget.RecyclerView>



       注意:
        在RecyclerView的adapter中的onCreateViewHolder方法中，在inflater item布局时，第二个parent参数必须设置null，否则只会显示一个item项(见如下代码)；
        @Override
        public BottomDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //注意这里的第二个参数必须为null，如果为parent时，由于嵌套的作用，只会显示一个item项
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_item, null, false);
            return new BottomDialogViewHolder(view);
        }

4.通过BottomSheetBehavior监听底部菜单各种状态的变化：
   第一个回调函数用来监听BottomSheet状态的改变，也就是我们上面所说到的五种状态，
   第二个回调而onSlide回调当中的slideOffset则用来监听底部菜单的偏移量：
      当处于展开状态时，偏移量为1
      当处于收起状态时，偏移量为0
      当处于隐藏状态时，偏移量为-1

            //监听底部菜单的状态变化
        recyclerViewBottomSheetBehavior = BottomSheetBehavior.from(mRecyclerView);
        recyclerViewBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                System.out.println("bottomSheet = [" + bottomSheet + "], newState = [" + newState + "]");
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                System.out.println("bottomSheet = [" + bottomSheet + "], slideOffset = [" + slideOffset + "]");
            }
        });
          

5.BottomSheetDialog类简单的使用方式
       private void showSharedDialog() {

        if (bottomSheetDialog == null) {
            bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setCancelable(true);
            bottomSheetDialog.setCanceledOnTouchOutside(true);
            //这里的layout是要显示的布局内容，里面可以放RecyclerView等
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_share_dialog, null);
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();

            //以下设置是为了解决：下滑隐藏dialog后，再次调用show方法显示时，不能弹出Dialog----在真机测试时不写下面的方法也未发现问题
            View delegateView = bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
            final BottomSheetBehavior<View> sheetBehavior = BottomSheetBehavior.from(delegateView);
            sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                //在下滑隐藏结束时才会触发
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        bottomSheetDialog.dismiss();
                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }
                //每次滑动都会触发
                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    System.out.println("onSlide = [" + bottomSheet + "], slideOffset = [" + slideOffset + "]");
                }
            });
        } else {
            bottomSheetDialog.show();
        }
        }




6.BottomSheetDialog类复杂用法--里面使用RecyclerView
        
									          private void showBottomDialog() {
									
									        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
									        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_layout, null);
									
									        handleList(view);
									
									        bottomSheetDialog.setContentView(view);
									        bottomSheetDialog.setCancelable(true);
									        bottomSheetDialog.setCanceledOnTouchOutside(true);
									        bottomSheetDialog.show();
									    }
									
									  private void handleList(View view) {
									        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
									        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
									        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
									        recyclerView.setLayoutManager(linearLayoutManager);
									        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
									        MyRecyclerAdapter adapter = new MyRecyclerAdapter();
									        recyclerView.setAdapter(adapter);
									
									        adapter.setData(getDatas());
									        adapter.notifyDataSetChanged();
									    }
									
									    private List<String> getDatas() {
									        List<String> list = new ArrayList<>();
									        for (int i = 0 ;i<30 ;i++) {
									            list.add("android："+i);
									        }
									        return list;
									    }
									
									 public static class MyRecyclerAdapter extends RecyclerView.Adapter{
									
									        private List<String> mData ;
									
									        public void setData(List<String> list) {
									            mData = list ;
									        }
									        @Override
									        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
									            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_item,null));
									        }
									
									        @Override
									        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
									            MyViewHolder myViewHolder = (MyViewHolder) holder;
									            myViewHolder.index.setText(mData.get(position)+"");
									            myViewHolder.name.setText(mData.get(position)+"");
									        }
									
									        @Override
									        public int getItemCount() {
									            return mData == null ? 0 :mData.size();
									        }
									
									
									
									        public static class MyViewHolder extends RecyclerView.ViewHolder{
									
									            private TextView name ;
									            private TextView index ;
									            public MyViewHolder(View itemView) {
									                super(itemView);
									                name = (TextView) itemView.findViewById(R.id.bottom_sheet_dialog_item_name);
									                index = (TextView) itemView.findViewById(R.id.bottom_sheet_dialog_item_index);
									            }
									        }
									    }
									
									//下面是recycler.xml布局文件
									<?xml version="1.0" encoding="utf-8"?>
									<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
									    android:orientation="vertical" android:layout_width="match_parent"
									    android:layout_height="match_parent">
									
									
									    <RelativeLayout
									        android:layout_width="match_parent"
									        android:gravity="center_vertical"
									        android:layout_height="?attr/actionBarSize">
									
									        <TextView
									            android:text="收藏全部"
									            android:gravity="center"
									            android:layout_width="wrap_content"
									            android:layout_height="match_parent" />
									        <TextView
									            android:text="播放列表（20）"
									            android:gravity="center"
									            android:layout_centerInParent="true"
									            android:layout_width="wrap_content"
									            android:layout_height="match_parent" />
									        <TextView
									            android:text="清空"
									            android:gravity="center"
									            android:layout_alignParentRight="true"
									            android:layout_width="wrap_content"
									            android:layout_height="match_parent" />
									
									    </RelativeLayout>
									
									    <View
									        android:layout_width="match_parent"
									        android:background="@android:color/black"
									        android:layout_height="1dp"/>
									
									    <android.support.v7.widget.RecyclerView
									        android:layout_width="match_parent"
									        android:id="@+id/recycler"
									        android:layout_height="match_parent"></android.support.v7.widget.RecyclerView>
									
									</LinearLayout>
									
									
									






三:.bottomSheetDialogFragment

继承与AppCompatDialogFragment，而AppCompatDialogFragment继承与DialogFragment；系统重写了onCreateDialog方法，返回一个与上面分析的BottomSheetDialog一样，具体的使用用法和DialogFragment一样；
拥有自己的生命周期；
可对整个页面进行折叠、展开和销毁；
可灵活使用自定义样式。
 

通过继承与BottomSheetFragment来实现底部菜单布局，适用于动态指定的布局，并且根据Fragment的生命周期做较多逻辑操作的情况；
 BottomSheetDialogFragment 的父类是 AppCompatDialogFragment，重写了 onCreateDialog() 方法，返回了一个 BottomSheetDialog 实例
****

   1.几种状态值的介绍说明

         STATE_EXPANDED:展开状态，显示完整布局；
         STATE_COLLAPED:折叠状态，显示设置的peekHeight高度，如果peekHeight的高度为0，则全部隐藏，与STATE_HIDDEN状态一样；
         STATE_HIDDEN:隐藏状态，隐藏全部布局；
         STATE_DRAGGING:拖拽时的状态；是个中间状态；
         STATE_SETLLING:释放时的状态；是个中间状态；


   2.需要注意区别的是折叠和隐藏：
        折叠
		当我们设置收起的高度app:behavior_peekHeight，在折叠的稳定状态时，不会完全隐藏，可以通过拖动这部分布局使它进入展开状态
		隐藏
		意味着整个底部菜单完全不可见，但是默认情况下是没有这种状态的，需要设置：app：behavior_hidbehavior_hideable = “true"才会出现这种状态；

   [https://upload-images.jianshu.io/upload_images/3150565-f4b0fc017b46b35b.png](https://upload-images.jianshu.io/upload_images/3150565-f4b0fc017b46b35b.png)






3.具体使用案例：
首先定义一个Fragment继承BottomSheetDialogFragment,如下：

 
			
			public class DemoBottomSheetDialogFragment extends BottomSheetDialogFragment {
			
			    public static DemoBottomSheetDialogFragment newInstance() {
			
			        Bundle args = new Bundle();
			
			        DemoBottomSheetDialogFragment fragment = new DemoBottomSheetDialogFragment();
			        fragment.setArguments(args);
			        return fragment;
			    }
			
			    @Nullable
			    @Override
			    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
			        //填充自己的想要的布局
			        View view = inflater.inflate(R.layout.layout_bottom_sheet_linear, container, false);
			        return view;
			    }
			}


弹出和隐藏的的实现：



          public class BottomDialogFragmentActivity extends AppCompatActivity {

			    private DemoBottomSheetDialogFragment demoBottomSheetDialogFragment ;
			    @Override
			    protected void onCreate(Bundle savedInstanceState) {
			        super.onCreate(savedInstanceState);
			        setContentView(R.layout.activity_bottom_dialog_fragment);
			
			        demoBottomSheetDialogFragment = DemoBottomSheetDialogFragment.newInstance();
			        //显示dialogFragment
			        showBottomSheetDialogFragment();
			
			
			    }
			
			    public void showdialog(View view) {
			        showBottomSheetDialogFragment();
			    }
			    public void hidedialog(View view) {
			        //隐藏dialogFragment
			        hideBottomSheetDialogFragment();
			    }
			
			    /**
			     * 显示BottomSheetDialogFragment
			     */
			    private void hideBottomSheetDialogFragment() {
			        if (demoBottomSheetDialogFragment == null) {
			            demoBottomSheetDialogFragment.dismiss();
			        }
			    }
			
			    /**
			     * 显示BottomSheetDialogFragment
			     */
			    private void showBottomSheetDialogFragment() {
			        demoBottomSheetDialogFragment.show(getSupportFragmentManager(),"bottomSheetDialogFragment");
			    }
			}



基类封装

			   public class BaseFullBottomSheetFragment extends BottomSheetDialogFragment {
			    /**
			     * 顶部向下偏移量
			     */
			    private int topOffset = 0;
			    private BottomSheetBehavior<FrameLayout> behavior;
			    @NonNull
			    @Override
			    public Dialog onCreateDialog(Bundle savedInstanceState) {
			        if (getContext() == null) {
			            return super.onCreateDialog(savedInstanceState);
			        }
			        return new BottomSheetDialog(getContext(), R.style.TransparentBottomSheetStyle);
			    }
			    @Nullable
			    @Override
			    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
			        return super.onCreateView(inflater, container, savedInstanceState);
			    }
			    @Override
			    public void onStart() {
			        super.onStart();
			        // 设置软键盘不自动弹出
			        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
			        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
			        FrameLayout bottomSheet = dialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
			        if (bottomSheet != null) {
			            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
			            layoutParams.height = getHeight();
			            behavior = BottomSheetBehavior.from(bottomSheet);
			            // 初始为展开状态
			            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
			        }
			    }
			    /**
			     * 获取屏幕高度
			     *
			     * @return height
			     */
			    private int getHeight() {
			        int height = 1920;
			        if (getContext() != null) {
			            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
			            Point point = new Point();
			            if (wm != null) {
			                // 使用Point已经减去了状态栏高度
			                wm.getDefaultDisplay().getSize(point);
			                height = point.y - getTopOffset();
			            }
			        }
			        return height;
			    }
			    public int getTopOffset() {
			        return topOffset;
			    }
			    public void setTopOffset(int topOffset) {
			        this.topOffset = topOffset;
			    }
			    public BottomSheetBehavior<FrameLayout> getBehavior() {
			        return behavior;
			    }
			}




子类实现
       子类只需重写 onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) 方法，传入自己的 dialog 界面：



			@Nullable
			@Override
			public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
			    View dialogView = inflater.inflate(R.layout.dialog_monitor_detail, container, false);
			    return dialogView;
			}


       可通过 Behavior 的方式关闭它：

		     if (getBehavior() != null) {
			 getBehavior().setState(BottomSheetBehavior.STATE_HIDDEN);
			}


          设置全部展开时距离顶部的偏移量：

             setTopOffset(100);