package cn.jiguang.verify;

import android.app.Application;
import android.util.Log;

import cn.jiguang.verifysdk.api.JVerificationInterface;
import cn.jiguang.verifysdk.api.RequestCallback;

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        JVerificationInterface.setDebugMode(true);
        final long start = System.currentTimeMillis();
        JVerificationInterface.init(this, new RequestCallback<String>() {
            @Override
            public void onResult(int code, String result) {
                Log.d("MyApp", "[init] code = " + code + " result = " + result + " consists = " + (System.currentTimeMillis() - start));
            }
        });
    }


}
