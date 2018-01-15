package com.ljt.photocachedemo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${JT.L} on 2017/10/17.
 */

public class PhotosWallAdapter extends BaseAdapter {

    public static String TAG= PhotosWallAdapter.class.getSimpleName();
    private Context context;
    private List<String> imageUrlList = new ArrayList<>();


    /**
     * 记录每个子项的高度
     */
    private int mItemHeight=0;

    public ImageLoaders imageLoaders;

    public PhotosWallAdapter(Context context, List<String> imageUrlList, AbsListView absListView) {
        this.context = context;
        this.imageUrlList = imageUrlList;
        imageLoaders=new ImageLoaders(context,absListView);
    }

    @Override
    public int getCount() {
        if (imageUrlList != null) {
            return imageUrlList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return imageUrlList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String url=imageUrlList.get(position);
         Log.d(TAG,TAG+" getView ----->>>url= "+url);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(mItemHeight, mItemHeight);
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= View.inflate(context, R.layout.photo_layout,null);
            holder.iv= (ImageView) convertView.findViewById(R.id.photo);
            holder.iv.setLayoutParams(layoutParams);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        //设置默认图片
        holder.iv.setImageResource(R.drawable.empty_photo);
        Picasso.with(context).load(url).placeholder(R.mipmap.ic_launcher).tag("PhotoTag").into(holder.iv);
        //加载数据
//        imageLoaders.loadBitmaps(holder.iv,url);
        return convertView;
    }


    private class ViewHolder{
        ImageView iv;
    }

    //设置Item子项的大小
    public void setItemSize(int edgeLength){
        mItemHeight=edgeLength;
        imageLoaders.setItemLength(edgeLength);
    }



}
