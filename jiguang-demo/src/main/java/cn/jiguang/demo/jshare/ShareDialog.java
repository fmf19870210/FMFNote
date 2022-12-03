package cn.jiguang.demo.jshare;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jiguang.demo.R;
import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatActionListener;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;
import cn.jiguang.share.facebook.Facebook;
import cn.jiguang.share.facebook.messenger.FbMessenger;
import cn.jiguang.share.jchatpro.JChatPro;
import cn.jiguang.share.qqmodel.QZone;
import cn.jiguang.share.twitter.Twitter;
import cn.jiguang.share.weibo.SinaWeibo;

/**
 * Create by wangqingqing
 * On 2020/11/9 14:31
 * Copyright(c) 2020 极光
 * Description
 */
public class ShareDialog extends Dialog {

    private static final String TAG = "ShareDialog";

    private ViewPager viewPager;
    private List<View> dotViewList;
    private List<cn.jiguang.demo.jshare.ShareBean> sharePlantList;
    private ProgressDialog progressDialog;
    private Context context;

    public ShareDialog(@NonNull Context context, List<cn.jiguang.demo.jshare.ShareBean> sharePlantList) {
        super(context, R.style.CustomDialog);
        this.context = context;
        this.sharePlantList = sharePlantList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.jshare_demo_dialog_share);
        findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("请稍候...");

        LinearLayout dotContainer = findViewById(R.id.dot_container);
        viewPager = findViewById(R.id.view_pager);

        RecyclerView recyclerView1 = new RecyclerView(getContext());
        RecyclerView recyclerView2 = new RecyclerView(getContext());


        List<View> viewList = new ArrayList<>();
        List<cn.jiguang.demo.jshare.ShareBean> dataList1 = new ArrayList<>();
        List<cn.jiguang.demo.jshare.ShareBean> dataList2 = new ArrayList<>();

        int size = sharePlantList.size();
        if (size > 8) {
            for (int i = 0; i < size - 8; i++) {
                dataList2.add(sharePlantList.get(8 + i));
            }
            for (int i = 0; i < size; i++) {
                dataList1.add(sharePlantList.get(i));
            }
        } else {
            for (int i = 0; i < size; i++) {
                dataList1.add(sharePlantList.get(i));
            }
        }

        initRecycleView(recyclerView1, dataList1);
        initRecycleView(recyclerView2, dataList2);

        viewList.add(recyclerView1);
        if (!dataList2.isEmpty()) {
            viewList.add(recyclerView2);
        }

        initDot(dotContainer, !dataList2.isEmpty());

        viewPager.setAdapter(new MyPagerAdapter(viewList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotViewList.size(); i++) {
                    View view = dotViewList.get(i);
                    boolean selected = i == position;
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    if (selected) {
                        layoutParams.width = 24;
                    } else {
                        layoutParams.width = 8;
                    }
                    view.setLayoutParams(layoutParams);
                    view.setSelected(selected);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = getContext().getResources().getDisplayMetrics().widthPixels;
        getWindow().setAttributes(lp);
        getWindow().setGravity(Gravity.BOTTOM);
    }

    private void initDot(LinearLayout dotContainer, boolean showDot) {
        dotContainer.setVisibility(showDot ? View.VISIBLE : View.INVISIBLE);
        if (!showDot) {
            return;
        }
        View view;
        dotContainer.removeAllViews();
        dotViewList = new ArrayList<>();

        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < 2; i++) {
            view = new View(getContext());
            view.setBackgroundResource(R.drawable.jshare_demo_selector_dot);

            view.setSelected(i == 0);
            if (i == 0) {
                view.setSelected(true);
                layoutParams = new LinearLayout.LayoutParams(24, 8);
            } else {
                layoutParams = new LinearLayout.LayoutParams(8, 8);
            }
            view.setLayoutParams(layoutParams);
            layoutParams.leftMargin = 10;

            dotContainer.addView(view, layoutParams);
            dotViewList.add(view);
        }
    }

    private void initRecycleView(RecyclerView recyclerView, final List<cn.jiguang.demo.jshare.ShareBean> dataList) {
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        BaseQuickAdapter<cn.jiguang.demo.jshare.ShareBean, BaseViewHolder> adapter = new BaseQuickAdapter<cn.jiguang.demo.jshare.ShareBean, BaseViewHolder>(R.layout.jshare_demo_layout_share, dataList) {
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, cn.jiguang.demo.jshare.ShareBean o) {
                baseViewHolder.setText(R.id.tv_text, o.getText());
                baseViewHolder.setImageResource(R.id.iv_icon, o.getDrawableResId());
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                dismiss();
                cn.jiguang.demo.jshare.ShareBean shareBean = dataList.get(position);
                ShareParams shareParams = new ShareParams();
                String platName = shareBean.getAlias();
                switch (shareBean.getId()) {
                    case 0:
                        shareParams.setTitle(cn.jiguang.demo.jshare.ShareConstant.share_title);
                        shareParams.setText(cn.jiguang.demo.jshare.ShareConstant.share_text);
                        shareParams.setShareType(Platform.SHARE_TEXT);
                        break;
                    case 1:
                        shareParams.setUrl(cn.jiguang.demo.jshare.ShareConstant.share_url);
                        shareParams.setShareType(Platform.SHARE_IMAGE);
                        //twitter支持单张、多张（最多4张本地图片）
                        if (Twitter.Name.equals(platName)) {
                            String[] array = new String[]{cn.jiguang.demo.jshare.FileTestCopy.ImagePath, cn.jiguang.demo.jshare.FileTestCopy.ImagePath};
                            shareParams.setImageArray(array);
                            //shareParams.setImagePath(MyApplication.ImagePath);
                        } else {
                            shareParams.setImagePath(cn.jiguang.demo.jshare.FileTestCopy.ImagePath);
                        }
                        break;
                    case 2:
                        if (JChatPro.Name.equalsIgnoreCase(platName)) {
                            shareParams.setShareType(Platform.SHARE_IMAGETEXT);
                            shareParams.setTitle(cn.jiguang.demo.jshare.ShareConstant.share_title);
                            shareParams.setText(cn.jiguang.demo.jshare.ShareConstant.share_text);
                        } else {
                            shareParams.setTitle(cn.jiguang.demo.jshare.ShareConstant.share_title);
                            shareParams.setText(cn.jiguang.demo.jshare.ShareConstant.share_text);
                            shareParams.setShareType(Platform.SHARE_WEBPAGE);
                            shareParams.setUrl(cn.jiguang.demo.jshare.ShareConstant.share_url);
                            shareParams.setImagePath(cn.jiguang.demo.jshare.FileTestCopy.ImagePath);

                        }
                        break;
                    case 3:
                        shareParams.setTitle(cn.jiguang.demo.jshare.ShareConstant.share_title);
                        shareParams.setText(cn.jiguang.demo.jshare.ShareConstant.share_text);
                        shareParams.setShareType(Platform.SHARE_MUSIC);
                        if (platName.equals(SinaWeibo.Name)) {
                            shareParams.setUrl(cn.jiguang.demo.jshare.ShareConstant.share_musicurl);
                        } else {
                            shareParams.setMusicUrl(cn.jiguang.demo.jshare.ShareConstant.share_musicurl);
                            shareParams.setUrl(cn.jiguang.demo.jshare.ShareConstant.music_shareUrl);
                            shareParams.setImagePath(cn.jiguang.demo.jshare.FileTestCopy.ImagePath);
                        }
                        break;
                    case 4:
                    case 5:
                        //QQ空间支持本地视频
                        shareParams.setShareType(Platform.SHARE_VIDEO);
                        if (platName.equals(QZone.Name) || platName.equals(Facebook.Name) || platName.equals(FbMessenger.Name) || platName.equals(Twitter.Name)) {
                            shareParams.setVideoPath(cn.jiguang.demo.jshare.FileTestCopy.VideoPath);
                        } else {
                            shareParams.setTitle(cn.jiguang.demo.jshare.ShareConstant.share_title);
                            shareParams.setText(cn.jiguang.demo.jshare.ShareConstant.share_text);
                            shareParams.setUrl(cn.jiguang.demo.jshare.ShareConstant.share_videourl);
                            shareParams.setImagePath(cn.jiguang.demo.jshare.FileTestCopy.ImagePath);
                        }
                        break;
                    case 6:
                        //只有微信支持表情
                        shareParams.setShareType(Platform.SHARE_EMOJI);
                        shareParams.setImagePath(cn.jiguang.demo.jshare.FileTestCopy.ImagePath);
                        shareParams.setFilePath(cn.jiguang.demo.jshare.FileTestCopy.ImagePath);
                        break;

                    case 7:
                        //只有微信支持文件
                        shareParams.setShareType(Platform.SHARE_FILE);
                        shareParams.setFilePath(cn.jiguang.demo.jshare.FileTestCopy.ImagePath);
                        break;
                    case 8:
                        //只有微信支持小程序分享
                        shareParams.setShareType(Platform.SHARE_MINI_PROGRAM);
                        shareParams.setTitle(cn.jiguang.demo.jshare.ShareConstant.share_title);
                        shareParams.setText(cn.jiguang.demo.jshare.ShareConstant.share_text);
                        shareParams.setUrl(cn.jiguang.demo.jshare.ShareConstant.share_url);
                        shareParams.setImagePath(cn.jiguang.demo.jshare.FileTestCopy.ImagePath);


                        shareParams.setMiniProgramWithShareTicket(false);//可以设置withShareTicket为true，当分享卡片在群聊中被其他用户打开时，可以获取到shareTicket
                        shareParams.setMiniProgramType(0);// 正式版:0，开发版（测试版）:1，体验版（预览版）:2
                        shareParams.setMiniProgramImagePath(cn.jiguang.demo.jshare.FileTestCopy.ImagePath);//小程序图片地址
                        shareParams.setMiniProgramPath("pages/index/index");//小程序页面路径
                        shareParams.setMiniProgramUserName("gh_cd370c00d3d4");//小程序原始id
                        break;
                    case 9:
                        //只有支持QQ APP
                        shareParams.setShareType(Platform.SHARE_APPS);
                        shareParams.setImagePath(cn.jiguang.demo.jshare.FileTestCopy.ImagePath);
                        break;
                    default:
                        shareParams.setShareType(Platform.SHARE_IMAGE);
                        shareParams.setImageUrl(cn.jiguang.demo.jshare.ShareConstant.share_imageurl);
                }

                JShareInterface.share(platName, shareParams, mPlatActionListener);
                progressDialog.show();
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String toastMsg = (String) msg.obj;
            Toast.makeText(getContext(), toastMsg, Toast.LENGTH_SHORT).show();
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    };

    private PlatActionListener mPlatActionListener = new PlatActionListener() {
        @Override
        public void onComplete(Platform platform, int action, HashMap<String, Object> data) {
            if (handler != null) {
                Message message = handler.obtainMessage();
                message.obj = "分享成功";
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Platform platform, int action, int errorCode, Throwable error) {
            if (handler != null) {
                Message message = handler.obtainMessage();
                message.obj = "分享失败:" + (error != null ? error.getMessage() : "") + "---" + errorCode;
                Log.d(TAG, message.obj + "");
                handler.sendMessage(message);
            }
        }

        @Override
        public void onCancel(Platform platform, int action) {
            if (handler != null) {
                Message message = handler.obtainMessage();
                message.obj = "分享取消";
                handler.sendMessage(message);
            }
        }
    };

    @Override
    public void dismiss() {
        super.dismiss();
        handler.removeCallbacksAndMessages(null);
    }

    private static class MyPagerAdapter extends PagerAdapter {

        List<View> viewList;

        MyPagerAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = viewList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
