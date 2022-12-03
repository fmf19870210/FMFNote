package com.fmf.fmfnote.shijian_fenfa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@SuppressLint("AppCompatCustomView")
public class BarryButton extends Button {

    public BarryButton(Context context) {
        super(context);
    }

    public BarryButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BarryButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BarryButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //getParent().requestDisallowInterceptTouchEvent(true);
    }

}
