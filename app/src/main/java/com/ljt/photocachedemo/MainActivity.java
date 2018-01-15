package com.ljt.photocachedemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

public static String TAG= MainActivity.class.getSimpleName();
    /** 用于展示照片墙的GridView */
    private ListView mPhotoWallView;
    private int mImageThumbSize;
    private int mImageThumbSpacing;

    private static final String BASE_URL = "https://image.baidu.com/search/index?ct=201326592&cl=2&st=-1&lm=-1&nc=1&ie=utf-8&tn=baiduimage&ipn=r&rps=1&pv=&fm=rs8&word=";
    private String[] mImageWords = { "小清新美女", "天空之城", "千与千寻", "清新美女", "美女壁纸" };
    private static String url;
    private static int page = 0;


    private List<String> mImageUrlList = new ArrayList<>();

    private ImageSource imageSource;

    /** GridView的适配器 */
    private PhotosWallAdapter mWallAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initData();
        System.out.println("-----");
    }

    private void initView() {
        Map<String,String> a=new HashMap<>();
        mPhotoWallView = (ListView) findViewById(R.id.photo_wall);
        mPhotoWallView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                final Picasso picasso = Picasso.with(MainActivity.this);
                if (scrollState == SCROLL_STATE_IDLE) {
                    picasso.resumeTag("PhotoTag");
                } else {
                    picasso.pauseTag("PhotoTag");
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void initEvent() {
        mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
        mImageThumbSpacing = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);
        // 监听获取图片的宽高
        mPhotoWallView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // 计算列数
                final int numColumns = (int) Math.floor(mPhotoWallView.getWidth() / (mImageThumbSize + mImageThumbSpacing));
                if (numColumns > 0) {
                    int columnWidth = (mPhotoWallView.getWidth() / numColumns) - mImageThumbSpacing;
                    mWallAdapter.setItemSize(columnWidth);
                    mPhotoWallView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    private void initData() {
        if(mWallAdapter==null){
            mWallAdapter=new PhotosWallAdapter(this,mImageUrlList,mPhotoWallView);
        }
        mPhotoWallView.setAdapter(mWallAdapter);
        imageSource=new ImageSource();
        getImageListFromNet(0);
    }

    private void getImageListFromNet(final int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                imageSource.setDuRegex(true);
                String regex = ImageSource.regex[2];
                String word = mImageWords[i];
                url = BASE_URL + URLEncoder.encode(word);
                 Log.d(TAG,TAG+" ----->>> url= "+url);
                final ArrayList<String> images = imageSource.ParseHtmlToImage(url, regex);
                Log.d(TAG,TAG+" ----->>> images= "+images);
                runOnUiThread(new Runnable() {
                    public void run() {
                        mImageUrlList.addAll(images);
                        mWallAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
    public void add(View view){
        page++;
        if(page >= mImageWords.length){
            page = page % mImageWords.length;
        }
        getImageListFromNet(page);
    }

    public void clear(View view){
        mImageUrlList.clear();
        mWallAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWallAdapter.imageLoaders.flushCache();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出程序时结束所有的下载任务
        mWallAdapter.imageLoaders.cancelAllTasks();
        mWallAdapter.imageLoaders.deleteCache();
        Picasso.with(this).cancelTag("PhotoTag");
    }
}
