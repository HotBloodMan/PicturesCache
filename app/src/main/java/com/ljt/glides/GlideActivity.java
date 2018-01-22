package com.ljt.glides;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
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
//                Glide.with(mContext).load(urlPic).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivGlide);
                //禁止磁盘缓存和内存缓存
                /*
                *例如，Picasso只缓存全尺寸图片。Glide，会缓存原始，
                * 全尺寸的图片和额外的小版本图片。例如，如果你请求一个1000x1000像素的图片，
                * 你的ImageView是500x500像素，Glide会保存两个版本的图片到缓存里
                * DiskCacheStrategy.NONE 啥也不缓存
                * SOURCE 只缓存全尺寸图. 上面例子里的1000x1000像素的图片
                * .RESULT 只缓存最终降低分辨后用到的图片
                *.ALL 缓存所有类型的图片 (默认行为)
                * */
                Glide.with(mContext).load(urlPic).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(ivGlide);
                  //磁盘缓存。目录在/data/data/<package-name>/cache,其他应用程序无法访问。

                /*
                * 设定特定大小的Target
                *如果你传递一个ImageView作为.into()的参数，
                * Glide会使用ImageView的大小来限制图片的大小。
                * 例如如果要加载的图片是1000x1000像素，但是ImageView的尺寸只有250x250像素，
                * Glide会降低图片到小尺寸，以节省处理时间和内存。
                * 如果你知道图片应当为多大，那么在你的回调定义里应当指明，以节省内存：
                * */
//             SimpleTarget target1=new SimpleTarget<Bitmap>(250,250) {  //这里是以像素为单位
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                }
//            };
                // 直接收Bitmap类型的图片，不接收如.gif图片。
//                Glide.with(getApplicationContext()).load("").asBitmap().into(target1);


               /*
               *假设你有个自定义的View。由于没有已知的方法在哪里设置图片，
               * Glide并不支持加载图片到定制的View内。然而用ViewTarget会让这个更简单。
               * */
//                CusGlideView cusGlideView= (CusGlideView) findViewById(R.id.cus_glideview);
//                ViewTarget<CusGlideView,GlideDrawable> viewTarget=new ViewTarget<CusGlideView, GlideDrawable>(cusGlideView) {
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                        Drawable current = resource.getCurrent();
//                    }
//                };
//                Glide.with(getApplicationContext()).load("").into(viewTarget);
            }
        });
    }
}
