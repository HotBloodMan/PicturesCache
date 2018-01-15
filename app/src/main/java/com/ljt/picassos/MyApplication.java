package com.ljt.picassos;

import android.app.Application;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ${JT.L} on 2018/1/15.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // 配置全局的Picasso instance

        Picasso.Builder builder = new Picasso.Builder(this);
        //配置下载器
        builder.downloader(new CustomDownloader());
        //配置缓存
        LruCache cache = new LruCache(5*1024*1024);// 设置缓存大小
        builder.memoryCache(cache);
        //配置线程池
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        builder.executor(executorService);

        //构造一个Picasso
        Picasso picasso = builder.build();
        // 设置全局单列instance
        Picasso.setSingletonInstance(picasso);

    }
}
