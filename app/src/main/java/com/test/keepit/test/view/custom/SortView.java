/*
 * Copyright (c) 2019. Created by Hameliak Maksym
 */

package com.test.keepit.test.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.test.keepit.test.R;
import com.test.keepit.test.view.callback.SortCallback;
import com.test.keepit.test.vo.Sort;

import static com.test.keepit.test.util.SortDirection.ASC;
import static com.test.keepit.test.util.SortDirection.DESC;

public class SortView extends LinearLayout implements View.OnClickListener {
    private Sort sort;
    private SortCallback sortCallback;

    ImageView arrow;

    public SortView(Context context) {
        super(context);
        init();
    }

    public SortView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SortView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SortView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.sort_item, this);
        this.setOnClickListener(this);
        arrow = findViewById(R.id.arrow);
    }

    public Sort getSort() {
        return sort;
    }

    public void animateArrow(int visibility){
        ViewPropertyAnimator animator = arrow.animate();
        animator.setDuration(350);

        if(visibility != arrow.getVisibility()) {
            float alpha = (visibility == View.VISIBLE) ? 1.0f : 0f;
            animator.alpha(alpha);
        }

        Runnable animRunnable = () -> arrow.setVisibility(visibility);

        if(visibility != View.VISIBLE){
            animator.withEndAction(animRunnable);
        } else {
            animator.withStartAction(animRunnable);
        }

        animator.rotation(sort.getDirection() == ASC ? 0 : 180);
        animator.start();
    }

    public void setSort(Sort sort) {
        this.sort = sort;

        TextView title = findViewById(R.id.sort_title);
        title.setText(sort.getName(getContext()));
    }

    public void setSortCallback(SortCallback sortCallback) {
        this.sortCallback = sortCallback;
    }

    @Override
    public void onClick(View v) {
        sort.setDirection(sort.getDirection() == ASC ? DESC : ASC);
        sortCallback.onSort(sort);
    }
}
