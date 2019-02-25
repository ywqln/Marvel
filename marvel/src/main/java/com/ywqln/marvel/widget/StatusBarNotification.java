package com.ywqln.marvel.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * 描述:顶部通知.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2018/6/26
 */
public class StatusBarNotification {
    private Builder mBuilder;
    private LinearLayout mContainer;
    private long startTime = System.currentTimeMillis();

    protected StatusBarNotification(Activity activity) {
        mBuilder = new Builder(activity);
    }

    protected void setBuilder(Builder builder) {
        mBuilder = builder;
    }

    public void show() {
        startTime = System.currentTimeMillis();
        addContainer();
        gone();
        mContainer.setVisibility(View.VISIBLE);
        mContainer.setTag(true);


        WindowManager.LayoutParams textViewParam = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                0, 0,
                PixelFormat.TRANSPARENT
        );
        TextView messageTextView = new TextView(mBuilder.mActivity);
        messageTextView.setBackgroundColor(mBuilder.bgColor);
        messageTextView.setTextColor(mBuilder.textColor);
        messageTextView.setGravity(Gravity.CENTER);
        messageTextView.setTextSize(mBuilder.textSize);

        int status = getStatusBarHeight(mBuilder.mActivity);
        if (mBuilder.getHeight() > 0) {
            messageTextView.setPadding(0, status, 0, 0);
            messageTextView.setHeight(mBuilder.getHeight() + status);
        } else {
            messageTextView.setPadding(0, mBuilder.verticalMargin + status, 0,
                    mBuilder.verticalMargin);
        }
        messageTextView.setText(mBuilder.getMessage());
        mContainer.addView(messageTextView, textViewParam);
        // 从上而下的动画
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        animation.setDuration(mBuilder.animationDuration);
        messageTextView.startAnimation(animation);

        mContainer.postDelayed(new Runnable() {
            @Override
            public void run() {
                long duration = System.currentTimeMillis() - startTime;
                if (duration >= mBuilder.displayDelayed) {
                    dismiss();
                }
            }
        }, mBuilder.displayDelayed);
    }

    private void addContainer() {
        if (mContainer != null) {
            return;
        }
        mContainer = new LinearLayout(mBuilder.mActivity);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0,
                PixelFormat.TRANSPARENT
        );
        // flag 设置 Window 属性
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        // type 设置 Window 类别（层级）
        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        layoutParams.gravity = Gravity.TOP;
        mContainer.setGravity(Gravity.CENTER);

        FrameLayout layout = (FrameLayout) mBuilder.mActivity.getWindow().getDecorView();
        layout.addView(mContainer, layoutParams);
    }

    private int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;

        try {

            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();

            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sbar;
    }

    public boolean isShow() {
        if (mContainer == null) {
            return false;
        }

        return (boolean) mContainer.getTag();
    }

    public void dismiss() {
        int count = mContainer.getChildCount();
        if (isShow()) {
            for (int i = 0; i < count - 1; i++) {
                TextView msgTextView = (TextView) mContainer.getChildAt(i);
                mContainer.removeView(msgTextView);
            }

            if (count <= 0) {
                gone();
                return;
            }

            TextView lastMsgTextView = (TextView) mContainer.getChildAt(count - 1);

            TranslateAnimation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f
            );
            animation.setDuration(mBuilder.animationDuration);
            lastMsgTextView.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    gone();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    public void gone() {
        mContainer.setVisibility(View.GONE);
        mContainer.removeAllViews();
        mContainer.setTag(false);
    }

    public static class Builder {
        private Activity mActivity;
        private StatusBarNotification mNotification;

        private String message = "";
        private float textSize = 16;
        private int textColor = Color.WHITE;
        private int bgColor = Color.argb(200, 250, 50, 15);
        private long displayDelayed = 2000;
        private long animationDuration = 300;
        private int verticalMargin = 52;
        private int height = 180;

        public Builder(Activity activity) {
            this.mActivity = activity;
            setHeight(56);
        }

        public Builder warningStyle() {
            bgColor = Color.argb(200, 190, 170, 80);
            textColor = Color.WHITE;
            return this;
        }

        public Builder errorStyle() {
            bgColor = Color.argb(200, 250, 50, 15);
            textColor = Color.WHITE;
            return this;
        }

        public Builder tipStyle() {
            bgColor = Color.argb(200, 66, 66, 66);
            textColor = Color.WHITE;
            return this;
        }

        public StatusBarNotification build() {
            if (mNotification == null) {
                mNotification = new StatusBarNotification(mActivity);
                mNotification.setBuilder(this);
            }
            return mNotification;
        }

        public StatusBarNotification show() {
            build();
            mNotification.show();
            return mNotification;
        }

        public String getMessage() {
            return message;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public float getTextSize() {
            return textSize;
        }

        public Builder setTextSize(float textSize) {
            this.textSize = textSize;
            return this;
        }

        public int getTextColor() {
            return textColor;
        }

        public Builder setTextColor(int color) {
            this.textColor = color;
            return this;
        }

        public int getBgColor() {
            return bgColor;
        }

        public Builder setBgColor(int bgColor) {
            this.bgColor = bgColor;
            return this;
        }

        public long getDisplayDelayed() {
            return displayDelayed;
        }

        public Builder setDisplayDelayed(long displayDelayed) {
            this.displayDelayed = displayDelayed;
            return this;
        }

        public long getAnimationDuration() {
            return animationDuration;
        }

        public Builder setAnimationDuration(long animationDuration) {
            this.animationDuration = animationDuration;
            return this;
        }

        public int getVerticalMargin() {
            return verticalMargin;
        }

        public Builder setVerticalMargin(int verticalMargin) {
            this.verticalMargin = verticalMargin;
            return this;
        }

        public int getHeight() {
            return height;
        }

        public Builder setHeight(int height) {
            this.height = dp2px(height);
            return this;
        }

        private final int dp2px(int dp) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                    mActivity.getBaseContext().getResources().getDisplayMetrics());
        }
    }
}
