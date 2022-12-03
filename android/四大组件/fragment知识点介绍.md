

  一:Fragment生命周期
      onAttach(Activity)： 当Fragment与Activity发生关联的时候调用
      onCreateView(LayoutInflater, ViewGroup, Bundle): 创建该Fragment的视图
       onViewCreated(View view, Bundle savedInstanceState)： 试图创建后调用该方法

       onActivityCreated(Bundle): 当Activity的onCreated方法返回时调用
       onDestroyView()： 与onCreateView方法相对应，当该Fragment的视图被移除时调用
        onDetach(): 与onAttach方法相对应，当Fragment与Activity取消关联时调用





二:Fragment与Activity通信


 