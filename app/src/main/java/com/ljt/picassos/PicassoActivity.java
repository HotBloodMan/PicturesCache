package com.ljt.picassos;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ljt.photocachedemo.MainActivity;
import com.ljt.photocachedemo.R;
import com.squareup.picasso.Cache;
import com.squareup.picasso.Callback;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//参考：https://www.jianshu.com/p/c68a3b9ca07a
public class PicassoActivity extends Activity {
public static String TAG= PicassoActivity.class.getSimpleName();
    private ImageView ivPica;
    private ImageView ivPica2;
    private Button btnPica;
    String urlPic="http://pic.qiantucdn.com/58pic/17/53/96/22M58PICxIG_1024.jpg";
    String urlPic2="http://pic2.ooopic.com/12/43/17/81bOOOPIC01_1024.jpg";
    private Picasso picasso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        ivPica = (ImageView) findViewById(R.id.iv_picasso);
        ivPica2 = (ImageView) findViewById(R.id.iv_picasso2);
        btnPica = (Button) findViewById(R.id.btn_picasso);


        //用builder来构造一个Picasso
//        Picasso.Builder builder = new Picasso.Builder(this);
//        //构造一个Picasso
//        picasso = builder.build();
//        builder.downloader(new CustomDownloader());
//        //配置缓存
//        LruCache cache = new LruCache(5*1024*1024);// 设置缓存大小
//        builder.memoryCache(cache);
//        //配置线程池
//        ExecutorService executorService = Executors.newFixedThreadPool(8);
//        builder.executor(executorService);


        final List<Transformation>  mList=new ArrayList<>();
        mList.add(new GrayTransformation());
        mList.add(new BlurTransformation(this));
        /*
        * with(Context) 获取一个Picasso单例，参数是一个Context上下文
        load(String) 调用load 方法加载图片
        into (ImageView) 将图片显示在对应的View上，可以是ImageView，也可以是实现了Target j接口的自定义View。
        * */
        btnPica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resourceId = R.mipmap.ic_launcher;
//                Picasso.with(PicassoActivity.this)
//                        .load(urlPic)
//                        .placeholder(R.mipmap.ic_launcher)
//                        .error(R.drawable.fish)
////                        .noFade()  //取消渐入的效果
//                        .resize(240,240)
//                        .memoryPolicy(MemoryPolicy.NO_CACHE)
////                        .onlyScaleDown()  //当原始图片的尺寸大于我们指定的尺寸
//                        .centerCrop()    //居中裁剪
////                        .centerInside()  //将图片展示完全
//                        .fit()
//                        .rotate(180,200,100)
//                        .transform(new GrayTransformation())
//                        .transform(new BlurTransformation(PicassoActivity.this))
//                        .transform(mList) //添加一个transformation集合
//                        .into(ivPica);
                 Log.d(TAG,TAG+" ----->>>1111111111111111 ");
//                Picasso.with(PicassoActivity.this)
//                        .load(urlPic2)
//                        .resize(240,240)
//                        .placeholder(R.mipmap.ic_launcher)
//                        .priority(Picasso.Priority.HIGH)  //设置优先级
//                        .into(ivPica2);
//                Log.d(TAG,TAG+" ----->>>22222222222222222 ");

                //使用异步方式加载图片 适用于预加载
//                Picasso.with(PicassoActivity.this).load(urlPic).fetch(new Callback() {
//                    @Override
//                    public void onSuccess() {
//                    }
//                    @Override
//                    public void onError() {
//                    }
//                });
                /*
                *Picasso 内存缓存和磁盘缓存都开启了的，也就是加载图片的时候，
                * 内存和磁盘都缓存了，但是有些时候，我们并不需要缓存，比如说
                * ：加载一张大图片的时候，如果再内存中保存一份，很容易造成OOM
                * ,这时候我们只希望有磁盘缓存，而不希望缓存到内存，
                * 因此就需要我们设置缓存策略了。Picasso 提供了这样的方法。
                *
                * memoryPolicy 设置内存缓存策略
                  就像上面所说的，有时候我们不希望有内存缓存，我们可以
                  通过 memoryPolicy 来设置。MemoryPolicy是一个枚举，有两个值
                   NO_CACHE：表示处理请求的时候跳过检查内存缓存
                  **NO_STORE: ** 表示请求成功之后，不将最终的结果存到内存。
                * */
//                Picasso.with(PicassoActivity.this).load(urlPic)
//                        .placeholder(R.drawable.empty_photo)
//                        .memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
//                        .networkPolicy(NetworkPolicy.NO_CACHE)//跳过磁盘缓存
//                        .networkPolicy(NetworkPolicy.OFFLINE)//强制从缓存获取结果
//                        .into(ivPica);
//                Picasso.with(PicassoActivity.this).setIndicatorsEnabled(true);//显示指示器
//                Picasso.with(PicassoActivity.this).setLoggingEnabled(true);//开启日志打印

//                picasso.load(urlPic).into(ivPica);

                Picasso.with(getApplicationContext()).load(urlPic).into(ivPica);
                Log.d(TAG,TAG+" ----->>>22222222222 ");
            }
        });





    }



//    public  static class BlurTransformation implements Transformation {
//        RenderScript rs;
//
//        public BlurTransformation(Context context){
//            super();
//            rs=RenderScript.create(context);
//        }
//
//        @Override
//        public Bitmap transform(Bitmap source) {
//            Bitmap blurredBitmap = source.copy(Bitmap.Config.ARGB_8888, true);
//            Allocation input = Allocation.createCubemapFromBitmap(rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_FULL
//                    , Allocation.USAGE_SHARED);
//            Allocation output=Allocation.createTyped(rs,input.getType());
//
//            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
//            script.setInput(input);
//            // Set the blur radius
//            script.setRadius(25);
//
//            script.forEach(output);
//
//            output.copyTo(blurredBitmap);
//            source.recycle();
//            return  blurredBitmap;
//        }
//
//        @Override
//        public String key() {
//            return "blur";
//        }
//    }

}
