package cn.jiguang.demo.jshare.arouter;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import java.io.File;

import cn.jiguang.demo.baselibrary.arouter.InitService;
import cn.jiguang.demo.baselibrary.arouter.ServiceConstant;
import cn.jiguang.demo.jshare.FileTestCopy;
import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatformConfig;

/**
 * Create by wangqingqing
 * On 2020/11/4 10:11
 * Copyright(c) 2020 极光
 * Description
 */
@Route(path = ServiceConstant.SERVICE_SHARE)
public class ShareServiceImpl implements InitService {

    private static final String TAG = "ShareServiceImpl";


    @Override
    public void init(final Context context) {

        Log.i(TAG, "ShareServiceImpl init");


        JShareInterface.setDebugMode(true);
        PlatformConfig platformConfig = new PlatformConfig()
                .setWechat("wxa7da3a99a2a6b6ed", "efb871b32cbf46ca82fecb75bd72386d")
                .setQQ("101912127", "31a653f75ddbefeafadfe394c31c37a5")
                .setSinaWeibo("3591004520", "fea28430023dc31e0d8baad14dc9a623", "https://www.jiguang.cn")
                .setFacebook("413313183164990", "JShareDemo")
                .setTwitter("4hCeIip1cpTk9oPYeCbYKhVWi", "DuIontT8KPSmO2Y1oAvby7tpbWHJimuakpbiAUHEKncbffekmC", "https://www.jiguang.cn/")
                .setJchatPro("1847959632183996");
        /**
         * since 1.5.0，1.5.0版本后增加API，支持在代码中设置第三方appKey等信息，当PlatformConfig为null时，或者使用JShareInterface.init(Context)时需要配置assets目录下的JGShareSDK.xml
         **/
        JShareInterface.init(context, platformConfig);

        new Thread("share_file_copy") {
            @Override
            public void run() {
                File imageFile = FileTestCopy.copyResurces(context, "jshare_jiguang_test_img.png", "jshare_jiguang_test_img.png", 0);
                File videoFile = FileTestCopy.copyResurces(context, "jshare_jiguang_test.mp4", "jshare_jiguang_test.mp4", 0);
                if (imageFile != null) {
                    FileTestCopy.ImagePath = imageFile.getAbsolutePath();
                }

                if (videoFile != null) {
                    FileTestCopy.VideoPath = videoFile.getAbsolutePath();
                }

                super.run();
            }
        }.start();
    }
}
