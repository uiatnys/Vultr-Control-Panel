package com.wangzh.vultr.others.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by WangZH on 2016/12/9.
 */

public class CommonItemDecoration extends RecyclerView.ItemDecoration {

    private int left,top,right,bottom;

    public CommonItemDecoration(int left, int top, int rifht, int bottom) {
        super();
        this.left = left;
        this.top = top;
        this.right = rifht;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.top = top;
            outRect.left = left;
            outRect.right = right;
            outRect.bottom = bottom;
    }
}
