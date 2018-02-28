package com.ljt.picassos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.HashMap;
import java.util.Map;

import static com.ljt.picassos.PicassoActivity.TAG;

/**
 * Created by ${JT.L} on 2018/2/27.
 */

public class Test {
    private Picasso mPicasso;
    private final Map<String,Target>  mTargetMap;
    public static String TAG= PicassoActivity.class.getSimpleName();
    Test(Context context){
        mPicasso=new Picasso.Builder(context.getApplicationContext())
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        Log.e(TAG, uri.toString(), exception);
                    }
                }).build();
        mPicasso.setLoggingEnabled(true);
        mTargetMap=new HashMap<>();
    }
    public void loadBitmap(String uri, final ImageView iv){
        Target target=new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Log.e(TAG,"---->>>bitmap= "+bitmap);
                iv.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        mTargetMap.put(uri,target);
        mPicasso.load(uri).into(mTargetMap.get(uri));
    }
}
