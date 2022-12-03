package cn.jiguang.demo.jshare;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import cn.jiguang.share.android.api.AuthListener;
import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.model.AccessTokenInfo;
import cn.jiguang.share.android.model.BaseResponseInfo;
import cn.jiguang.share.android.model.UserInfo;

/**
 * Create by wangqingqing
 * On 2020/11/6 13:49
 * Copyright(c) 2020 极光
 * Description
 */
public class ShareInfoActivity extends AppCompatActivity {

    private static final String TAG = "ShareInfoActivity";
    private static final String SHARE_TYPE = "share_type";

    private BaseQuickAdapter<cn.jiguang.demo.jshare.ShareBean, BaseViewHolder> adapter;
    private List<cn.jiguang.demo.jshare.ShareBean> shareList;
    private ProgressDialog progressDialog;
    private int shareType;


    public static void startTypeActivity(Context context, int type) {
        Intent intent = new Intent(context, cn.jiguang.demo.jshare.ShareInfoActivity.class);
        intent.putExtra(SHARE_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jshare_demo_activity_share_info);
        ScreenUtils.setStatusBarTransparentAndWordsGray(getWindow());
        initView();
        getData();
    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("请稍候");

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        RecyclerView recycleView = findViewById(R.id.recycle_view);


        shareList = new ArrayList<>();
        recycleView.addItemDecoration(new cn.jiguang.demo.jshare.GridItemDecoration(1, 1, true));
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
                progressDialog.show();
                cn.jiguang.demo.jshare.ShareBean shareBean = shareList.get(position);
                String platName = shareBean.getAlias();

                if (shareType == Platform.ACTION_AUTHORIZING) {
                    if (!JShareInterface.isAuthorize(platName)) {
                        JShareInterface.authorize(platName, mAuthListener);
                    } else {
                        JShareInterface.removeAuthorize(platName, mAuthListener);
                    }
                } else {
                    JShareInterface.getUserInfo(platName, mAuthListener);
                }
            }
        });

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvTop = findViewById(R.id.tv_top);

        shareType = getIntent().getIntExtra(SHARE_TYPE, 1);
        tvTitle.setText(shareType == Platform.ACTION_AUTHORIZING ? "授权" : "获取用户信息");
        tvTop.setText(shareType == Platform.ACTION_AUTHORIZING ? "请选择授权平台" : "请选择获取用户信息的平台");
    }


    private void getData() {

        int length = cn.jiguang.demo.jshare.ShareConstant.textArray.length;
        for (int i = 0; i < length; i++) {
            cn.jiguang.demo.jshare.ShareBean bean = new cn.jiguang.demo.jshare.ShareBean();
            bean.setText(cn.jiguang.demo.jshare.ShareConstant.textArray[i]);
            bean.setDrawableResId(cn.jiguang.demo.jshare.ShareConstant.iconArray[i]);
            bean.setAlias(cn.jiguang.demo.jshare.ShareConstant.textAliasArray[i]);
            shareList.add(bean);
        }
        adapter.notifyDataSetChanged();
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String toastMsg = (String) msg.obj;
            Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT).show();
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    };


    AuthListener mAuthListener = new AuthListener() {
        @Override
        public void onComplete(Platform platform, int action, BaseResponseInfo data) {
            Log.d(TAG, "onComplete:" + platform + ",action:" + action + ",data:" + data);
            String toastMsg = null;
            switch (action) {
                case Platform.ACTION_AUTHORIZING:
                    if (data instanceof AccessTokenInfo) {        //授权信息
                        String token = ((AccessTokenInfo) data).getToken();//token
                        long expiration = ((AccessTokenInfo) data).getExpiresIn();//token有效时间，时间戳
                        String refresh_token = ((AccessTokenInfo) data).getRefeshToken();//refresh_token
                        String openid = ((AccessTokenInfo) data).getOpenid();//openid
                        //授权原始数据，开发者可自行处理
                        String originData = data.getOriginData();
                        toastMsg = "授权成功:" + data.toString();
                        Log.d(TAG, "openid:" + openid + ",token:" + token + ",expiration:" + expiration + ",refresh_token:" + refresh_token);
                        Log.d(TAG, "originData:" + originData);
                    }
                    break;
                case Platform.ACTION_REMOVE_AUTHORIZING:
                    toastMsg = "删除授权成功";
                    break;
                case Platform.ACTION_USER_INFO:
                    if (data instanceof UserInfo) {      //第三方个人信息
                        String openid = ((UserInfo) data).getOpenid();  //openid
                        String name = ((UserInfo) data).getName();  //昵称
                        String imageUrl = ((UserInfo) data).getImageUrl();  //头像url
                        int gender = ((UserInfo) data).getGender();//性别, 1表示男性；2表示女性
                        //个人信息原始数据，开发者可自行处理
                        String originData = data.getOriginData();
                        toastMsg = "获取个人信息成功:" + data.toString();
                        Log.d(TAG, "openid:" + openid + ",name:" + name + ",gender:" + gender + ",imageUrl:" + imageUrl);
                        Log.d(TAG, "originData:" + originData);
                    }
                    break;
            }
            if (handler != null) {
                Message msg = handler.obtainMessage(1);
                msg.obj = toastMsg;
                msg.sendToTarget();
            }
        }

        @Override
        public void onError(Platform platform, int action, int errorCode, Throwable error) {
            Log.d(TAG, "onError:" + platform + ",action:" + action + ",error:" + error);
            String toastMsg = null;
            switch (action) {
                case Platform.ACTION_AUTHORIZING:
                    toastMsg = "授权失败";
                    break;
                case Platform.ACTION_REMOVE_AUTHORIZING:
                    toastMsg = "删除授权失败";
                    break;
                case Platform.ACTION_USER_INFO:
                    toastMsg = "获取个人信息失败";
                    break;
            }
            if (handler != null) {
                Message msg = handler.obtainMessage(1);
                msg.obj = toastMsg + (error != null ? error.getMessage() : "") + "---" + errorCode;
                msg.sendToTarget();
            }
        }

        @Override
        public void onCancel(Platform platform, int action) {
            Log.d(TAG, "onCancel:" + platform + ",action:" + action);
            String toastMsg = null;
            switch (action) {
                case Platform.ACTION_AUTHORIZING:
                    toastMsg = "取消授权";
                    break;
                case Platform.ACTION_REMOVE_AUTHORIZING:
                    break;
                case Platform.ACTION_USER_INFO:
                    toastMsg = "取消获取个人信息";
                    break;
            }
            if (handler != null) {
                Message msg = handler.obtainMessage(1);
                msg.obj = toastMsg;
                msg.sendToTarget();
            }
        }
    };
}

