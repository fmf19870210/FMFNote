package com.yzy.xxkotlindemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Create by Fangmingfei on 2022-12-19 下午 6:14
 * Describe ：
 */
public class JavaMainActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<? extends TextView> textViews = new ArrayList<TextView>();
        TextView textView = textViews.get(0);
          //todo Error
      //    textViews.add(textView);


    }
}
