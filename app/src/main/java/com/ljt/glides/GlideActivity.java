package com.ljt.glides;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.ljt.photocachedemo.R;

import java.io.File;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

//参考：http://blog.csdn.net/shangmingchao/article/details/51125554/
public class GlideActivity extends AppCompatActivity {

    private Button btnGlide;
    private ImageView ivGlide;
    private ImageView ivGlide2;
    private Context mContext;


    String urlImg="http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png";
    String urlPic="http://pic.qiantucdn.com/58pic/17/53/96/22M58PICxIG_1024.jpg";
    private TextView tvGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        mContext=GlideActivity.this;
        btnGlide = (Button) findViewById(R.id.btn_glide);
        ivGlide = (ImageView) findViewById(R.id.iv_glide);
        ivGlide2 = (ImageView) findViewById(R.id.iv_glide2);
        tvGlide = (TextView) findViewById(R.id.tv_glide);

        btnGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Glide.with(mContext)
//                     .load("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png")
//                     .into(ivGlide);
                //获取缓存大小
//                new GetDiskCacheSizeTask(tvGlide).execute(new File(getCacheDir(), DiskCache.Factory.DEFAULT_DISK_CACHE_DIR));
                //优先级
//                Glide.with(mContext).load(urlImg).priority(Priority.HIGH).into(ivGlide);
//                Glide.with(mContext).load(urlPic).priority(Priority.LOW).into(ivGlide2);

                //用原图的1/10作为缩略图
//                Glide.with(mContext).load(urlImg)
//                        .thumbnail(0.6f)
//                        .into(ivGlide);
//                DrawableRequestBuilder<Integer> themRequest=Glide.with(mContext).load(R.drawable.fish);
//                Glide.with(mContext).load(urlImg)
//                        .thumbnail(themRequest)
//                        .into(ivGlide);
//                Glide.with(mContext).load(urlImg)
////                        .bitmapTransform(new  RoundedCornersTransformation(mContext,3,0, RoundedCornersTransformation.CornerType.ALL))
//                        .bitmapTransform(new GrayscaleTransformation(mContext))
//                        .into(ivGlide);
//                Glide.with(mContext).load(urlImg)
//                        .into(ivGlide);
                //缓存所有版本的图像（默认行为）
                Glide.with(mContext).load(urlPic).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivGlide);
                  //磁盘缓存。目录在/data/data/<package-name>/cache,其他应用程序无法访问。

            }
        });
    }
}
