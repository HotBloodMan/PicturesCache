package com.ljt.photocachedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ${JT.L} on 2017/10/17.
 */

public class ImageLoaders {
    private View view;
    private DiskLruCacheUtil mDiskLruCacheUtil;
    private Set<BitmapWorkerTask> taskCollection;
    private LruCache<String,Bitmap> mLruCache;
    private DiskLruCache mDiskLruCache;
    private int itemLength;

    public ImageLoaders(Context context, View view) {
        this.view = view;
        taskCollection=new HashSet<>();
        int maxMemory= (int) Runtime.getRuntime().maxMemory();
        int cacheSize=maxMemory/8;
        mLruCache=new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        mDiskLruCacheUtil=new DiskLruCacheUtil(context);
        mDiskLruCache=mDiskLruCacheUtil.doOpen();

    }

    public void setItemLength(int itemLength){
        this.itemLength=itemLength;
    }

    /**
     * 加载Bitmap对象。此方法会在LruCache中检查所有屏幕中可见的ImageView的Bitmap对象，
     * 如果发现任何一个ImageView的Bitmap对象不在缓存中，就会开启异步线程去下载图片。
     */
    public void loadBitmaps(ImageView imageView, String imageUrl){
        mDiskLruCacheUtil.setItemLength(itemLength);
        if(imageView==null ||imageUrl== null){
            return;
        }
        //从内存中获取数据
        Bitmap bitmap = mLruCache.get(imageUrl);
        if(bitmap !=null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        //从缓存文件中获取数据DiskUrlCache
        bitmap=mDiskLruCacheUtil.doGet(imageUrl);
        if(bitmap !=null){
            imageView.setImageBitmap(bitmap);
            if(mLruCache.get(imageUrl)==null){
                //把缓存文件中的数据加入内存中
                mLruCache.put(imageUrl,bitmap);
            }
            return;
        }
        //从网络中获取数据
        getBitmapFromNet(imageUrl);
    }

    //从网络中获取数据
    private void getBitmapFromNet(String imageUrl) {
        BitmapWorkerTask task = new BitmapWorkerTask();
        taskCollection.add(task);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,imageUrl);
    }

    //异步下载图片任务
    private class BitmapWorkerTask extends AsyncTask<String,Void,Bitmap> {
        //图片的URL地址
        String imageUrl;
        @Override
        protected Bitmap doInBackground(String... params) {
          imageUrl=params[0];
           mDiskLruCacheUtil.doEdit(imageUrl);
            Bitmap bitmap = mDiskLruCacheUtil.doGet(imageUrl);
            if(bitmap!=null && mLruCache.get(imageUrl)==null){
                // 把网络下载的数据加入内存中
                mLruCache.put(imageUrl,bitmap);
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // 根据Tag找到相应的ImageView控件，将下载好的图片显示出来。
            ImageView imageView = (ImageView) view.findViewWithTag(imageUrl);
            if(imageView!=null && bitmap !=null){
                imageView.setImageBitmap(bitmap);
            }
            taskCollection.remove(this);
        }
    }

    public void flushCache(){
        if(mDiskLruCache !=null){
            try {
                mDiskLruCache.flush();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void deleteCache(){
        if(mDiskLruCache !=null){
            try {
                mDiskLruCache.delete();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 取消所有正在下载或等待下载的任务
     */
    public void cancelAllTasks() {
        if (taskCollection != null) {
            for (BitmapWorkerTask task : taskCollection) {
                task.cancel(false);
            }
        }
    }

}
