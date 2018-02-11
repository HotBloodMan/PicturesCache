package com.ljt.glides;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

/**
 * Created by ${JT.L} on 2018/1/22.
 */

public class CusGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        Log.d("CusGlideModule","-----配置----------------->>>>>applyOptions");
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int memoryCacheSize = calculator.getMemoryCacheSize();
        int bitmapPoolSize = calculator.getBitmapPoolSize();
        int cusMemoryCache=(int)1.2*memoryCacheSize;
        int cusBitmapPool=(int)1.2*bitmapPoolSize;
        builder.setMemoryCache(new LruResourceCache(cusMemoryCache));
        builder.setBitmapPool(new LruBitmapPool(cusBitmapPool));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
//       glide.with(context).load("").into(new ImageView(context));
        glide.register(GlideUrl.class, InputStream.class,new OkHttpGlideUrlLoader.Factory());
    }
}
