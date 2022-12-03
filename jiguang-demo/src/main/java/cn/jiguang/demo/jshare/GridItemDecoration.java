package cn.jiguang.demo.jshare;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Create by wangqingqing
 * On 2020/11/6 13:35
 * Copyright(c) 2020 极光
 * Description
 */
class GridItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private boolean mShowLastLine;
    private int mHorizonSpan;
    private int mVerticalSpan;

    GridItemDecoration(int horizonSpan, int verticalSpan, boolean showLastLine) {
        this.mHorizonSpan = horizonSpan;
        this.mShowLastLine = showLastLine;
        this.mVerticalSpan = verticalSpan;
        mDivider = new ColorDrawable(Color.parseColor("#E5E5E5"));
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            //最后一行底部横线不绘制
            if (isLastRaw(parent, i, getSpanCount(parent), childCount) && !mShowLastLine) {
                continue;
            }
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin;
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mHorizonSpan;

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            if ((parent.getChildViewHolder(child).getAdapterPosition() + 1) % getSpanCount(parent) == 0) {
                continue;
            }
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin + mHorizonSpan;
            final int left = child.getRight() + params.rightMargin;
            int right = left + mVerticalSpan;
//            //满足条件( 最后一行 && 不绘制 ) 将vertical多出的一部分去掉;
//            if (i==childCount-1) {
//                right -= mVerticalSpan;
//            }
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 计算偏移量
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

        if (itemPosition < 0) {
            return;
        }

        int column = itemPosition % spanCount;
        int bottom;

        int left = column * mVerticalSpan / spanCount;
        int right = mVerticalSpan - (column + 1) * mVerticalSpan / spanCount;

        if (isLastRaw(parent, itemPosition, spanCount, childCount)) {
            if (mShowLastLine) {
                bottom = mHorizonSpan;
            } else {
                bottom = 0;
            }
        } else {
            bottom = mHorizonSpan;
        }
        outRect.set(left, 0, right, bottom);
    }

    /**
     * 获取列数
     */
    private int getSpanCount(RecyclerView parent) {
        // 列数
        int mSpanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            mSpanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            mSpanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return mSpanCount;
    }

    /**
     * 是否最后一行
     *
     * @param parent     RecyclerView
     * @param pos        当前item的位置
     * @param spanCount  每行显示的item个数
     * @param childCount child个数
     */
    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager) {
            return getResult(pos, spanCount, childCount);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                // StaggeredGridLayoutManager 且纵向滚动
                return getResult(pos, spanCount, childCount);
            } else {
                // StaggeredGridLayoutManager 且横向滚动
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean getResult(int pos, int spanCount, int childCount) {
        int remainCount = childCount % spanCount;//获取余数
        //如果正好最后一行完整;
        if (remainCount == 0) {
            return pos >= childCount - spanCount; //最后一行全部不绘制;
        } else {
            return pos >= childCount - childCount % spanCount;
        }
    }
}
