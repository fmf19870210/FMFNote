package cn.jiguang.demo.jshare;

import java.io.Serializable;

/**
 * Create by wangqingqing
 * On 2020/11/6 11:30
 * Copyright(c) 2020 极光
 * Description
 */
public class ShareBean implements Serializable {
    private int id;
    private String text;
    private int drawableResId;
    private String alias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDrawableResId() {
        return drawableResId;
    }

    public void setDrawableResId(int drawableResId) {
        this.drawableResId = drawableResId;
    }

    @Override
    public String toString() {
        return "ShareBean{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", drawableResId=" + drawableResId +
                '}';
    }
}
