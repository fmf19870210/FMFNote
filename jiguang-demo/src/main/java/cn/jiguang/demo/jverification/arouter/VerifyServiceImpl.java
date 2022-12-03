package cn.jiguang.demo.jverification.arouter;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import cn.jiguang.demo.baselibrary.arouter.InitService;
import cn.jiguang.demo.baselibrary.arouter.ServiceConstant;
import cn.jiguang.verifysdk.api.JVerificationInterface;

/**
 * Copyright(c) 2020 极光
 * Description
 */
@Route(path = ServiceConstant.SERVICE_VERIFY)
public class VerifyServiceImpl implements InitService {

    private static final String TAG = "VerifyServiceImpl";


    @Override
    public void init(Context context) {
        Log.i(TAG, "VerifyServiceImpl init");

        JVerificationInterface.setDebugMode(true);
        JVerificationInterface.init(context);
    }
}
