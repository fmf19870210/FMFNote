package cn.jiguang.demo.jverification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.jiguang.demo.R;

public class NativeVerifyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jverification_demo_activity_native_verify);

        View login = findViewById(R.id.tv_go_login);
        View back = findViewById(R.id.iv_back);
        View login2 = findViewById(R.id.btn_login);


        View.OnClickListener fi = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };

        login.setOnClickListener(fi);
        back.setOnClickListener(fi);
        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSuccessActivity(Constants.ACTION_NATIVE_VERIFY_SUCCESS,"");
                finish();
            }
        });

//        ScreenUtils.tryFullScreenWhenLandscape(this);
    }

    private void toSuccessActivity(int action, String token) {
        Intent intent = new Intent(this, LoginResultActivity.class);
        intent.putExtra(Constants.KEY_ACTION, action);
        intent.putExtra(Constants.KEY_TOKEN,token);
        startActivity(intent);
    }
}
