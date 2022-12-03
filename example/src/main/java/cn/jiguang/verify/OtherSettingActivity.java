package cn.jiguang.verify;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import cn.jiguang.verifysdk.api.JVerificationInterface;
import cn.jiguang.verifysdk.api.PreLoginListener;
import cn.jiguang.verifysdk.api.VerifyListener;

/**
 * Create by wangqingqing
 * On 2020/10/12 14:50
 * Copyright(c) 2020 极光
 * Description
 */
public class OtherSettingActivity extends Activity implements View.OnClickListener {

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_setting);
        ScreenUtils.setStatusBarTransparent(getWindow());
        findViewById(R.id.btn_pre).setOnClickListener(this);
        findViewById(R.id.btn_token).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pre:
                preLogin();
                break;
            case R.id.btn_token:
                getToken();
                break;
            case R.id.btn_del:
                delPreLoginCache();
                break;
        }
    }

    private void preLogin() {
        boolean verifyEnable = JVerificationInterface.checkVerifyEnable(this);
        if (!verifyEnable) {
            Toast.makeText(this, "[2016],msg = 当前网络环境不支持认证", Toast.LENGTH_SHORT).show();
            return;
        }

        showLoadingDialog();
        JVerificationInterface.preLogin(this, 5000, new PreLoginListener() {
            @Override
            public void onResult(final int code, final String content) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showResult(code, content, "");
                    }
                });
            }
        });
    }

    private void getToken() {
        boolean verifyEnable = JVerificationInterface.checkVerifyEnable(this);
        if (!verifyEnable) {
            Toast.makeText(this, "[2016],msg = 当前网络环境不支持认证", Toast.LENGTH_SHORT).show();
            return;
        }

        showLoadingDialog();
        JVerificationInterface.getToken(this, 5000, new VerifyListener() {
            @Override
            public void onResult(final int code, final String content, final String operator) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showResult(code, content, operator);
                    }
                });
            }
        });
    }

    private void showResult(int code, String content, String operator) {
        dismissLoadingDialog();
        String msg;
        if (code == 2000) {
            msg = "[" + code + "]token=" + content + ", operator=" + operator;
        } else {
            msg = "[" + code + "]message=" + content;
        }

        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showLoadingDialog() {
        dismissLoadingDialog();
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setCancelable(false);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
        alertDialog.show();
        alertDialog.setContentView(R.layout.loading_alert);
        alertDialog.setCanceledOnTouchOutside(false);
    }

    public void dismissLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    private void delPreLoginCache() {
        JVerificationInterface.clearPreLoginCache();
        Toast.makeText(getBaseContext(), "删除成功", Toast.LENGTH_SHORT).show();
    }
}
