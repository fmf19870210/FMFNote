package cn.jiguang.demo.jshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import cn.jiguang.demo.R;
import cn.jiguang.demo.baselibrary.ScreenUtils;
import cn.jiguang.share.android.api.Platform;

/**
 * Create by wangqingqing
 * On 2020/11/5 10:58
 * Copyright(c) 2020 极光
 * Description
 */
public class ShareActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jshare_demo_activity_share);
        ScreenUtils.setStatusBarTransparent(getWindow());
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_auth).setOnClickListener(this);
        findViewById(R.id.tv_share).setOnClickListener(this);
        findViewById(R.id.tv_self).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {
            onBackPressed();
        } else if (id == R.id.tv_share) {
            startActivity(new Intent(this, cn.jiguang.demo.jshare.ScenesActivity.class));
        } else if (id == R.id.tv_auth) {
            ShareInfoActivity.startTypeActivity(this, Platform.ACTION_AUTHORIZING);
        } else if (id == R.id.tv_self) {
            ShareInfoActivity.startTypeActivity(this, Platform.ACTION_USER_INFO);
        }
    }
}
