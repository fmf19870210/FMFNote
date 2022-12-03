package cn.jiguang.demo.jverification;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import cn.jiguang.demo.R;
import cn.jiguang.demo.baselibrary.ScreenUtils;
import cn.jiguang.demo.baselibrary.ToastHelper;
import cn.jiguang.verifysdk.api.AuthPageEventListener;
import cn.jiguang.verifysdk.api.JVerificationInterface;
import cn.jiguang.verifysdk.api.JVerifyUIClickCallback;
import cn.jiguang.verifysdk.api.JVerifyUIConfig;
import cn.jiguang.verifysdk.api.VerifyListener;

/**
 * Copyright(c) 2020 极光
 * Description
 */
public class VerifyActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "VerifyActivity";
    private AlertDialog alertDialog;
    private int winWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jverification_demo_activity_verify);
        findViewById(R.id.btn_auth).setOnClickListener(this);
        findViewById(R.id.btn_auth_dialog).setOnClickListener(this);
        findViewById(R.id.btn_setting).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        ScreenUtils.setStatusBarTransparent(getWindow());

        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        if (point.x>point.y){
            winWidth =   point.y;
        }else {
            winWidth = point.x;
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.btn_auth) {
            loginAuth(false);
        } else if (id == R.id.btn_auth_dialog) {
            loginAuth(true);
        } else if (id == R.id.btn_setting) {
            getToken();
        } else if (id == R.id.iv_back) {
            onBackPressed();
        }
    }

    private void getToken() {
        JVerificationInterface.getToken(this, new VerifyListener() {
            @Override
            public void onResult(int code, final String content, final String operator) {
                Log.e(TAG, "getToken result:"+code +",content:"+content+",operator:"+operator);
                ToastHelper.showOther(getApplicationContext(), "getToken result:"+code +",content:"+content+",operator:"+operator);
            }
        });
    }

    private void loginAuth(boolean isDialogMode) {

        int result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            result = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
            if (result != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "[2016],msg = 当前缺少权限", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        boolean verifyEnable = JVerificationInterface.checkVerifyEnable(this);
        if (!verifyEnable) {
            Toast.makeText(this, "[2016],msg = 当前网络环境不支持认证", Toast.LENGTH_SHORT).show();
            return;
        }
        JVerificationInterface.clearPreLoginCache();
        showLoadingDialog();

        setUIConfig(isDialogMode);
        //autoFinish 可以设置是否在点击登录的时候自动结束授权界面
        JVerificationInterface.loginAuth(this, true, new VerifyListener() {
            @Override
            public void onResult(final int code, final String content, final String operator) {
                Log.d(TAG, "[" + code + "]message=" + content + ", operator=" + operator);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoadingDialog();

                        if (code == cn.jiguang.demo.jverification.Constants.CODE_LOGIN_SUCCESS) {
                            toSuccessActivity(cn.jiguang.demo.jverification.Constants.ACTION_LOGIN_SUCCESS, content);
                            Log.e(TAG, "onResult: loginSuccess");
                        } else if (code != cn.jiguang.demo.jverification.Constants.CODE_LOGIN_CANCELD) {
                            Log.e(TAG, "onResult: loginError");
                            toFailedActivigy(code, content);
                        }
                    }
                });
            }
        }, new AuthPageEventListener() {
            @Override
            public void onEvent(int cmd, String msg) {
                Log.d(TAG, "[onEvent]. [" + cmd + "]message=" + msg);
            }
        });
    }

    private void toSuccessActivity(int action, String token) {
        Intent intent = new Intent(this, cn.jiguang.demo.jverification.LoginResultActivity.class);
        intent.putExtra(cn.jiguang.demo.jverification.Constants.KEY_ACTION, action);
        intent.putExtra(cn.jiguang.demo.jverification.Constants.KEY_TOKEN, token);
        startActivity(intent);
    }

    private void toFailedActivigy(int code, String errorMsg) {
        String msg = errorMsg;
        if (code == 2003) {
            msg = "网络连接不通";
        } else if (code == 2005) {
            msg = "请求超时";
        } else if (code == 2016) {
            msg = "当前网络环境不支持认证";
        } else if (code == 2010) {
            msg = "未开启读取手机状态权限";
        } else if (code == 6001) {
            msg = "获取loginToken失败";
        } else if (code == 6006) {
            msg = "预取号结果超时，需要重新预取号";
        }
        Intent intent = new Intent(this, cn.jiguang.demo.jverification.LoginResultActivity.class);
        intent.putExtra(cn.jiguang.demo.jverification.Constants.KEY_ACTION, cn.jiguang.demo.jverification.Constants.ACTION_LOGIN_FAILED);
        intent.putExtra(cn.jiguang.demo.jverification.Constants.KEY_ERORRO_MSG, msg);
        intent.putExtra(cn.jiguang.demo.jverification.Constants.KEY_ERROR_CODE, code);
        startActivity(intent);
    }

    private void setUIConfig(boolean isDialogMode) {
        JVerifyUIConfig portrait;
        JVerifyUIConfig landscape;
        if (isDialogMode) {
            portrait = getDialogPortraitConfig();
            landscape = getDialogLandscapeConfig();
        } else {
            portrait = getFullScreenPortraitConfig();
            landscape = getFullScreenLandscapeConfig();
        }
        //支持授权页设置横竖屏两套config，在授权页中触发横竖屏切换时，sdk自动选择对应的config加载。
        JVerificationInterface.setCustomUIWithConfig(portrait, landscape);
    }



    private JVerifyUIConfig getFullScreenPortraitConfig(){
        JVerifyUIConfig.Builder uiConfigBuilder = new JVerifyUIConfig.Builder();
        uiConfigBuilder.setSloganTextColor(0xFFD0D0D9);
        uiConfigBuilder.setLogoOffsetY(103);
        uiConfigBuilder.setNumFieldOffsetY(190);
        uiConfigBuilder.setPrivacyState(true);
        uiConfigBuilder.setLogoImgPath("jverification_demo_ic_icon");
        uiConfigBuilder.setNavTransparent(true);
        uiConfigBuilder.setNavReturnImgPath("jverification_demo_btn_back");
        uiConfigBuilder.setCheckedImgPath(null);
        uiConfigBuilder.setNumberColor(0xFF222328);
        uiConfigBuilder.setLogBtnImgPath("selector_btn_normal");
        uiConfigBuilder.setLogBtnTextColor(0xFFFFFFFF);
        uiConfigBuilder.setLogBtnText("一键登录");
        uiConfigBuilder.setLogBtnOffsetY(255);
        uiConfigBuilder.setLogBtnWidth(300);
        uiConfigBuilder.setLogBtnHeight(45);
        uiConfigBuilder.setAppPrivacyColor(0xFFBBBCC5,0xFF8998FF);
//        uiConfigBuilder.setPrivacyTopOffsetY(310);
        uiConfigBuilder.setPrivacyText("登录即同意《","","","》并授权极光认证Demo获取本机号码");
        uiConfigBuilder.setPrivacyCheckboxHidden(true);
        uiConfigBuilder.setPrivacyTextCenterGravity(true);
        uiConfigBuilder.setPrivacyTextSize(12);
//        uiConfigBuilder.setPrivacyOffsetX(52-15);

        // 手机登录按钮
        RelativeLayout.LayoutParams layoutParamPhoneLogin = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamPhoneLogin.setMargins(0, dp2Pix(this,360.0f),0,0);
        layoutParamPhoneLogin.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        layoutParamPhoneLogin.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
        TextView tvPhoneLogin = new TextView(this);
        tvPhoneLogin.setText("手机号码登录");
        tvPhoneLogin.setLayoutParams(layoutParamPhoneLogin);
        uiConfigBuilder.addCustomView(tvPhoneLogin, false, new JVerifyUIClickCallback() {
            @Override
            public void onClicked(Context context, View view) {
                toNativeVerifyActivity();
            }
        });

        // 微信qq新浪登录

        LinearLayout layoutLoginGroup = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutLoginGroupParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutLoginGroupParam.setMargins(0, dp2Pix(this, 450.0f), 0, 0);
        layoutLoginGroupParam.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        layoutLoginGroupParam.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        layoutLoginGroupParam.setLayoutDirection(LinearLayout.HORIZONTAL);
        layoutLoginGroup.setLayoutParams(layoutLoginGroupParam);

        ImageView btnWechat = new ImageView(this);
        ImageView btnQQ = new ImageView(this);
        ImageView btnXinlang = new ImageView(this);

        btnWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"click wechat");
            }
        });
        btnQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"click QQ");
            }
        });
        btnXinlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"click XinLang");
            }
        });

        btnWechat.setImageResource(R.drawable.jverification_demo_o_wechat);
        btnQQ.setImageResource(R.drawable.jverification_demo_o_qqx);
        btnXinlang.setImageResource(R.drawable.jverification_demo_o_weibo);

        LinearLayout.LayoutParams btnParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnParam.setMargins(25,0,25,0);

        layoutLoginGroup.addView(btnWechat,btnParam);
        layoutLoginGroup.addView(btnQQ,btnParam);
        layoutLoginGroup.addView(btnXinlang,btnParam);
        uiConfigBuilder.addCustomView(layoutLoginGroup, false, new JVerifyUIClickCallback() {
            @Override
            public void onClicked(Context context, View view) {
//                ToastUtil.showToast(MainActivity.this, "功能未实现", 1000);
            }
        });


        final View dialogViewTitle = LayoutInflater.from(getApplicationContext()).inflate(R.layout.jverification_demo_dialog_login_title,null, false);

        uiConfigBuilder.addNavControlView(dialogViewTitle, new JVerifyUIClickCallback() {
            @Override
            public void onClicked(Context context, View view) {

            }
        });

        final View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.jverification_demo_dialog_login_agreement,null, false);

        dialogView.findViewById(R.id.dialog_login_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JVerificationInterface.dismissLoginAuthActivity();
            }
        });

        dialogView.findViewById(R.id.dialog_login_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.setVisibility(View.GONE);
                dialogViewTitle.setVisibility(View.GONE);
            }
        });


        uiConfigBuilder.addCustomView(dialogView, false, new JVerifyUIClickCallback() {
            @Override
            public void onClicked(Context context, View view) {
            }
        });


        return uiConfigBuilder.build();
    }

    private JVerifyUIConfig getFullScreenLandscapeConfig(){
        JVerifyUIConfig.Builder uiConfigBuilder = new JVerifyUIConfig.Builder();
        uiConfigBuilder.setStatusBarHidden(true);
        uiConfigBuilder.setSloganTextColor(0xFFD0D0D9);
        uiConfigBuilder.setSloganOffsetY(145);
        uiConfigBuilder.setLogoOffsetY(20);
        uiConfigBuilder.setNumFieldOffsetY(110);
        uiConfigBuilder.setPrivacyState(true);
        uiConfigBuilder.setLogoImgPath("jverification_demo_ic_icon");
        uiConfigBuilder.setNavTransparent(true);
        uiConfigBuilder.setNavReturnImgPath("jverification_demo_btn_back");
        uiConfigBuilder.setCheckedImgPath("jverification_demo_cb_chosen");
        uiConfigBuilder.setUncheckedImgPath("jverification_demo_cb_unchosen");
        uiConfigBuilder.setNumberColor(0xFF222328);
        uiConfigBuilder.setLogBtnImgPath("jverification_demo_selector_btn_main");
        uiConfigBuilder.setLogBtnTextColor(0xFFFFFFFF);
        uiConfigBuilder.setLogBtnText("一键登录");
        uiConfigBuilder.setLogBtnOffsetY(175);
        uiConfigBuilder.setLogBtnWidth(300);
        uiConfigBuilder.setLogBtnHeight(45);
        uiConfigBuilder.setAppPrivacyColor(0xFFBBBCC5,0xFF8998FF);
        uiConfigBuilder.setPrivacyText("登录即同意《","","","》并授权极光认证Demo获取本机号码");
        uiConfigBuilder.setPrivacyCheckboxHidden(true);
        uiConfigBuilder.setPrivacyTextCenterGravity(true);
        uiConfigBuilder.setPrivacyTextSize(12);
//        uiConfigBuilder.setPrivacyOffsetX(52-15);
        uiConfigBuilder.setPrivacyOffsetY(18);

        // 手机登录按钮
        RelativeLayout.LayoutParams layoutParamPhoneLogin = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamPhoneLogin.setMargins(0,  dp2Pix(this,15.0f),dp2Pix(this,15.0f),0);
        layoutParamPhoneLogin.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        layoutParamPhoneLogin.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        TextView tvPhoneLogin = new TextView(this);
        tvPhoneLogin.setText("手机号码登录");
        tvPhoneLogin.setLayoutParams(layoutParamPhoneLogin);
        uiConfigBuilder.addNavControlView(tvPhoneLogin, new JVerifyUIClickCallback() {
            @Override
            public void onClicked(Context context, View view) {
                toNativeVerifyActivity();
            }
        });

        // 微信qq新浪登录

        LinearLayout layoutLoginGroup = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutLoginGroupParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutLoginGroupParam.setMargins(0,dp2Pix(this,235), 0,0);
        layoutLoginGroupParam.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        layoutLoginGroupParam.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
        layoutLoginGroupParam.setLayoutDirection(LinearLayout.HORIZONTAL);
        layoutLoginGroup.setLayoutParams(layoutLoginGroupParam);

        ImageView btnWechat = new ImageView(this);
        ImageView btnQQ = new ImageView(this);
        ImageView btnXinlang = new ImageView(this);


        btnWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"click wechat");
            }
        });
        btnQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"click QQ");
            }
        });
        btnXinlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"click XinLang");
            }
        });


        btnWechat.setImageResource(R.drawable.jverification_demo_o_wechat);
        btnQQ.setImageResource(R.drawable.jverification_demo_o_qqx);
        btnXinlang.setImageResource(R.drawable.jverification_demo_o_weibo);

        LinearLayout.LayoutParams btnParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnParam.setMargins(25,0,25,0);

        layoutLoginGroup.addView(btnWechat,btnParam);
        layoutLoginGroup.addView(btnQQ,btnParam);
        layoutLoginGroup.addView(btnXinlang,btnParam);
        uiConfigBuilder.addCustomView(layoutLoginGroup, false, new JVerifyUIClickCallback() {
            @Override
            public void onClicked(Context context, View view) {
            }
        });



        final View dialogViewTitle = LayoutInflater.from(getApplicationContext()).inflate(R.layout.jverification_demo_dialog_login_title,null, false);

        uiConfigBuilder.addNavControlView(dialogViewTitle, new JVerifyUIClickCallback() {
            @Override
            public void onClicked(Context context, View view) {

            }
        });

        final View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.jverification_demo_dialog_login_agreement_land,null, false);

        dialogView.findViewById(R.id.dialog_login_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JVerificationInterface.dismissLoginAuthActivity();
            }
        });

        dialogView.findViewById(R.id.dialog_login_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.setVisibility(View.GONE);
                dialogViewTitle.setVisibility(View.GONE);
            }
        });

        uiConfigBuilder.addCustomView(dialogView, false, new JVerifyUIClickCallback() {
            @Override
            public void onClicked(Context context, View view) {
//                ToastUtil.showToast(MainActivity.this, "功能未实现", 1000);
            }
        });

        return uiConfigBuilder.build();
    }


    private JVerifyUIConfig getDialogPortraitConfig(){
        int widthDp = px2dip(this, winWidth);
        JVerifyUIConfig.Builder uiConfigBuilder = new JVerifyUIConfig.Builder().setDialogTheme(widthDp-60, 300, 0, 0, false);
//        uiConfigBuilder.setLogoHeight(30);
//        uiConfigBuilder.setLogoWidth(30);
//        uiConfigBuilder.setLogoOffsetY(-15);
//        uiConfigBuilder.setLogoOffsetX((widthDp-40)/2-15-20);
//        uiConfigBuilder.setLogoImgPath("logo_login_land");
        uiConfigBuilder.setLogoHidden(true);

        uiConfigBuilder.setNumFieldOffsetY(104).setNumberColor(Color.BLACK);
        uiConfigBuilder.setSloganOffsetY(135);
        uiConfigBuilder.setSloganTextColor(0xFFD0D0D9);
        uiConfigBuilder.setLogBtnOffsetY(161);

        uiConfigBuilder.setPrivacyOffsetY(15);
        uiConfigBuilder.setCheckedImgPath("jverification_demo_cb_chosen");
        uiConfigBuilder.setUncheckedImgPath("jverification_demo_cb_unchosen");
        uiConfigBuilder.setNumberColor(0xFF222328);
        uiConfigBuilder.setLogBtnImgPath("jverification_demo_selector_btn_main");
        uiConfigBuilder.setPrivacyState(true);
        uiConfigBuilder.setLogBtnText("一键登录");
        uiConfigBuilder.setLogBtnHeight(44);
        uiConfigBuilder.setLogBtnWidth(250);
        uiConfigBuilder.setAppPrivacyColor(0xFFBBBCC5,0xFF8998FF);
        uiConfigBuilder.setPrivacyText("登录即同意《","","","》并授权极光认证Demo获取本机号码");
        uiConfigBuilder.setPrivacyCheckboxHidden(true);
        uiConfigBuilder.setPrivacyTextCenterGravity(true);
        uiConfigBuilder.setPrivacyTextSize(10);



        // 图标和标题
        LinearLayout layoutTitle = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutTitleParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutTitleParam.setMargins(0,dp2Pix(this,50), 0,0);
        layoutTitleParam.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        layoutTitleParam.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
        layoutTitleParam.setLayoutDirection(LinearLayout.HORIZONTAL);
        layoutTitle.setLayoutParams(layoutTitleParam);

        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.jverification_demo_logo_login_land);
        TextView tvTitle = new TextView(this);
        tvTitle.setText("极光认证");
        tvTitle.setTextSize(19);
        tvTitle.setTextColor(Color.BLACK);
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        LinearLayout.LayoutParams imgParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgParam.setMargins(0,0,dp2Pix(this,6),0);
        LinearLayout.LayoutParams titleParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgParam.setMargins(0,0,dp2Pix(this,4),0);
        layoutTitle.addView(img,imgParam);
        layoutTitle.addView(tvTitle,titleParam);
        uiConfigBuilder.addCustomView(layoutTitle,false,null);

        // 关闭按钮
        ImageView closeButton = new ImageView(this);

        RelativeLayout.LayoutParams mLayoutParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams1.setMargins(0, dp2Pix(this,10.0f),dp2Pix(this,10),0);
        mLayoutParams1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        mLayoutParams1.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        closeButton.setLayoutParams(mLayoutParams1);
        closeButton.setImageResource(R.drawable.jverification_demo_btn_close);
        uiConfigBuilder.addCustomView(closeButton, true, null);

        return uiConfigBuilder.build();
    }

    private JVerifyUIConfig getDialogLandscapeConfig(){
        int widthDp = px2dip(this, winWidth);
        JVerifyUIConfig.Builder uiConfigBuilder = new JVerifyUIConfig.Builder().setDialogTheme(480, widthDp-100, 0, 0, false);
//        uiConfigBuilder.setLogoHeight(40);
//        uiConfigBuilder.setLogoWidth(40);
//        uiConfigBuilder.setLogoOffsetY(-15);
//        uiConfigBuilder.setLogoImgPath("logo_login_land");
        uiConfigBuilder.setLogoHidden(true);

        uiConfigBuilder.setNumFieldOffsetY(104).setNumberColor(Color.BLACK);
        uiConfigBuilder.setNumberSize(22);
        uiConfigBuilder.setSloganOffsetY(135);
        uiConfigBuilder.setSloganTextColor(0xFFD0D0D9);
        uiConfigBuilder.setLogBtnOffsetY(161);

        uiConfigBuilder.setPrivacyOffsetY(15);
        uiConfigBuilder.setCheckedImgPath("jverification_demo_cb_chosen");
        uiConfigBuilder.setUncheckedImgPath("jverification_demo_cb_unchosen");
        uiConfigBuilder.setNumberColor(0xFF222328);
        uiConfigBuilder.setLogBtnImgPath("jverification_demo_selector_btn_main");
        uiConfigBuilder.setPrivacyState(true);
        uiConfigBuilder.setLogBtnText("一键登录");
        uiConfigBuilder.setLogBtnHeight(44);
        uiConfigBuilder.setLogBtnWidth(250);
        uiConfigBuilder.setAppPrivacyColor(0xFFBBBCC5,0xFF8998FF);
        uiConfigBuilder.setPrivacyText("登录即同意《","","","》并授权极光认证Demo获取本机号码");
        uiConfigBuilder.setPrivacyCheckboxHidden(true);
        uiConfigBuilder.setPrivacyTextCenterGravity(true);
//        uiConfigBuilder.setPrivacyOffsetX(52-15);
        uiConfigBuilder.setPrivacyTextSize(10);

        // 图标和标题
        LinearLayout layoutTitle = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutTitleParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutTitleParam.setMargins(dp2Pix(this,20),dp2Pix(this,15), 0,0);
        layoutTitleParam.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        layoutTitleParam.setLayoutDirection(LinearLayout.HORIZONTAL);
        layoutTitle.setLayoutParams(layoutTitleParam);

        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.jverification_demo_logo_login_land);
        TextView tvTitle = new TextView(this);
        tvTitle.setText("极光认证");
        tvTitle.setTextSize(19);
        tvTitle.setTextColor(Color.BLACK);
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        LinearLayout.LayoutParams imgParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgParam.setMargins(0,0,dp2Pix(this,6),0);
        LinearLayout.LayoutParams titleParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgParam.setMargins(0,0,dp2Pix(this,4),0);
        layoutTitle.addView(img,imgParam);
        layoutTitle.addView(tvTitle,titleParam);
        uiConfigBuilder.addCustomView(layoutTitle,false,null);


        // 关闭按钮
        ImageView closeButton = new ImageView(this);

        RelativeLayout.LayoutParams mLayoutParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams1.setMargins(0, dp2Pix(this,10.0f),dp2Pix(this,10),0);
        mLayoutParams1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        mLayoutParams1.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        closeButton.setLayoutParams(mLayoutParams1);
        closeButton.setImageResource(R.drawable.jverification_demo_btn_close);
        uiConfigBuilder.addCustomView(closeButton, true, null);

        return uiConfigBuilder.build();
    }

    private void toNativeVerifyActivity() {
        Intent intent = new Intent(this, cn.jiguang.demo.jverification.NativeVerifyActivity.class);
        startActivity(intent);
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
        alertDialog.setContentView(R.layout.jverification_demo_loading_alert);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //一定要在setContentView之后调用，否则无效
        alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void dismissLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    private int dp2Pix(Context context, float dp) {
        try {
            float density = context.getResources().getDisplayMetrics().density;
            return (int) (dp * density + 0.5F);
        } catch (Exception e) {
            return (int) dp;
        }
    }

    private int px2dip(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissLoadingDialog();
    }


}
