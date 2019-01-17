package com.ywqln.marvellib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.text.DecimalFormat;

/**
 * 描述:蒙层进度视图.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2017/7/18
 */
public class ShadowProgressView extends AppCompatImageView {
    private final float MAX = 100;
    private float progress = -1;
    private final float textSize = 50;

    public ShadowProgressView(Context context) {
        super(context);
    }

    public ShadowProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShadowProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawShadowProgress(canvas);
    }

    private void drawShadowProgress(Canvas canvas) {
        if (progress < 0) {
            return;
        }
        int w = this.getWidth();
        int h = this.getHeight();
        float percent = 1 - progress / MAX;
        float yp = percent * h;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAlpha(100);
        canvas.drawRect(0, 0, w, yp, paint);

        paint.setAntiAlias(true);
        paint.setTextSize(textSize);
        paint.setColor(Color.WHITE);

        DecimalFormat df = new DecimalFormat("#");
        String percentText = df.format(progress) + "%";
        float percentWidth = paint.measureText(percentText);
        float percentX = w / 2 - percentWidth / 2;
        float percentY = h / 2 + textSize / 2;

        canvas.drawText(percentText, percentX, percentY, paint);
        canvas.save();
    }
}
