package com.ljt.glides;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ljt.photocachedemo.R;

/**
 * Created by ${JT.L} on 2018/1/22.
 */

public class CusGlideView extends FrameLayout {

    private ImageView ivGlideCus;

    public CusGlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CusGlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
      inflate(context, R.layout.custom_glide_view,this);
        ivGlideCus = (ImageView) findViewById(R.id.iv_glides_cus);
    }
}
