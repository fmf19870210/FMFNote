package cn.jiguang.demo.jshare;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cn.jiguang.demo.R;
import cn.jiguang.demo.baselibrary.ScreenUtils;
import cn.jiguang.share.facebook.Facebook;
import cn.jiguang.share.facebook.messenger.FbMessenger;
import cn.jiguang.share.qqmodel.QQ;
import cn.jiguang.share.twitter.Twitter;
import cn.jiguang.share.wechat.Wechat;
import cn.jiguang.share.weibo.SinaWeiboMessage;

/**
 * Create by wangqingqing
 * On 2020/11/6 11:13
 * Copyright(c) 2020 极光
 * Description
 */
public class ScenesActivity extends AppCompatActivity {

    private BaseQuickAdapter<cn.jiguang.demo.jshare.ShareBean, BaseViewHolder> adapter;
    private List<cn.jiguang.demo.jshare.ShareBean> shareList;
    private List<cn.jiguang.demo.jshare.ShareBean> platformList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jshare_demo_activity_scenes);
        ScreenUtils.setStatusBarTransparentAndWordsGray(getWindow());
        initView();
        getData();
        createPlantData();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        RecyclerView recycleView = findViewById(R.id.recycle_view);


        shareList = new ArrayList<>();
        recycleView.addItemDecoration(new GridItemDecoration(1, 1, true));
        recycleView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new BaseQuickAdapter<cn.jiguang.demo.jshare.ShareBean, BaseViewHolder>(R.layout.jshare_demo_layout_share, shareList) {
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, cn.jiguang.demo.jshare.ShareBean o) {
                baseViewHolder.setText(R.id.tv_text, o.getText());
                baseViewHolder.setImageResource(R.id.iv_icon, o.getDrawableResId());
            }
        };
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                showShareDialog(position);
            }
        });
    }

    private static final String[] textArray = {
            "纯文本", "本地图片", "链接",
            "音乐", "视频", "本地视频",
            "emoji表情", "文件分享", "小程序分享",
            "APP分享"
    };
    private static final int[] iconArray = {
            R.drawable.jshare_demo_share_text, R.drawable.jshare_demo_share_pic, R.drawable.jshare_demo_share_link,
            R.drawable.jshare_demo_share_music, R.drawable.jshare_demo_share_video, R.drawable.jshare_demo_share_film,
            R.drawable.jshare_demo_share_emoji, R.drawable.jshare_demo_share_file, R.drawable.jshare_demo_share_mini,
            R.drawable.jshare_demo_share_app
    };

    private void getData() {

        int length = textArray.length;
        for (int i = 0; i < length; i++) {
            cn.jiguang.demo.jshare.ShareBean bean = new cn.jiguang.demo.jshare.ShareBean();
            bean.setText(textArray[i]);
            bean.setDrawableResId(iconArray[i]);
            shareList.add(bean);
        }
        adapter.notifyDataSetChanged();
    }

    private void createPlantData() {
        int size = cn.jiguang.demo.jshare.ShareConstant.plantAliasArray.length;
        platformList = new ArrayList<>(size);

        cn.jiguang.demo.jshare.ShareBean shareBean;
        for (int i = 0; i < size; i++) {
            shareBean = new cn.jiguang.demo.jshare.ShareBean();
            shareBean.setText(cn.jiguang.demo.jshare.ShareConstant.plantNameArray[i]);
            shareBean.setAlias(cn.jiguang.demo.jshare.ShareConstant.plantAliasArray[i]);
            shareBean.setDrawableResId(cn.jiguang.demo.jshare.ShareConstant.plantIconArray[i]);
            platformList.add(shareBean);
        }
    }

    private void showShareDialog(int position) {

        List<cn.jiguang.demo.jshare.ShareBean> sharePlantList = new ArrayList<>();

        cn.jiguang.demo.jshare.ShareBean shareBean;
        String platName;
        for (int i = 0; i < platformList.size(); i++) {
            shareBean = platformList.get(i);
            shareBean.setId(position);
            platName = shareBean.getAlias();
            if (position == 0) {
                if (!platName.equals(QQ.Name) && !platName.equals(Facebook.Name) && !platName.equals(FbMessenger.Name)) {
                    sharePlantList.add(shareBean);
                }
            } else if (position == 1 || position == 4 || position == 5) {
                if (!platName.equals(SinaWeiboMessage.Name)) {
                    sharePlantList.add(shareBean);
                }
            } else if (position == 2) {
                sharePlantList.add(shareBean);
            } else if (position == 3) {
                if (!platName.equals(Facebook.Name) && !platName.equals(FbMessenger.Name) && !platName.equals(Twitter.Name)) {
                    sharePlantList.add(shareBean);
                }
            } else if (position == 6 || position == 7 || position == 8) {
                if (platName.equals(Wechat.Name)) {
                    sharePlantList.add(shareBean);
                    break;
                }
            } else if (position == 9) {
                if (platName.equals(QQ.Name)) {
                    sharePlantList.add(shareBean);
                    break;
                }
            }
        }
        ShareDialog shareDialog = new ShareDialog(this, sharePlantList);
        shareDialog.show();
    }
}
