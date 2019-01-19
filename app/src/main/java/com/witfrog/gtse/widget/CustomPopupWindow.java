package com.witfrog.gtse.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

import com.witfrog.gtse.R;

public class CustomPopupWindow extends PopupWindow {

    private View     mContentView;
    private View     mParentView;
    private boolean  isOutsideTouch;
    private boolean  isFocus;
    private Drawable mBackgroundDrawable;
    private int      mAnimationStyle;
    private boolean  isWrap;

    CustomPopupWindow(Builder builder) {
        this.mContentView = builder.contentView;
        this.mParentView = builder.parentView;
        this.isOutsideTouch = builder.isOutsideTouch;
        this.isFocus = builder.isFocus;
        this.mBackgroundDrawable = builder.backgroundDrawable;
        this.mAnimationStyle = builder.animationStyle;
        this.isWrap = builder.isWrap;
        initLayout();
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * 用于填充contentView,必须传ContextThemeWrapper(比如activity)不然popupwindow要报错
     *
     * @param context
     * @param layoutId
     * @return
     */
    public static View inflateView(ContextThemeWrapper context, int layoutId) {
        return LayoutInflater.from(context).inflate(layoutId, null);
    }

    private void initLayout() {
        setWidth(isWrap ? LayoutParams.WRAP_CONTENT : LayoutParams.MATCH_PARENT);
        setHeight(isWrap ? LayoutParams.WRAP_CONTENT : LayoutParams.MATCH_PARENT);
        setFocusable(isFocus);
        setOutsideTouchable(isOutsideTouch);
        setBackgroundDrawable(mBackgroundDrawable);
        if (mAnimationStyle != -1) {
            setAnimationStyle(mAnimationStyle);
        }
        setContentView(mContentView);
    }

    /**
     * 获得用于展示popup内容的view
     *
     * @return
     */
    @Override
    public View getContentView() {
        return mContentView;
    }

    public void show() {//默认显示到中间
        if (isShowing()) {
            dismiss();
        } else {
            showAtLocation(mParentView, Gravity.CENTER, 0, 0);
        }
    }

    public static class Builder {
        private View     contentView;
        private View     parentView;
        private boolean  isOutsideTouch     = true;
        private boolean  isFocus            = true;
        private Drawable backgroundDrawable = new ColorDrawable(Color.parseColor("#40000000"));
        private int      animationStyle     = R.style.AnimFadeInOut;
        private boolean  isWrap             = false;

        public Builder contentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        public Builder parentView(View parentView) {
            this.parentView = parentView;
            return this;
        }

        public Builder isWrap(boolean isWrap) {
            this.isWrap = isWrap;
            return this;
        }

        public Builder isOutsideTouch(boolean isOutsideTouch) {
            this.isOutsideTouch = isOutsideTouch;
            return this;
        }

        public Builder isFocus(boolean isFocus) {
            this.isFocus = isFocus;
            return this;
        }

        public Builder backgroundDrawable(Drawable backgroundDrawable) {
            this.backgroundDrawable = backgroundDrawable;
            return this;
        }

        public Builder animationStyle(int animationStyle) {
            this.animationStyle = animationStyle;
            return this;
        }

        public CustomPopupWindow build() {
            if (parentView == null) {
                throw new IllegalStateException("ParentView is required");
            }
            if (contentView == null) {
                throw new IllegalStateException("ContentView is required");
            }
            return new CustomPopupWindow(this);
        }
    }

}
