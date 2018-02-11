package com.ljt.glides2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.ljt.photocachedemo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ${JT.L} on 2018/1/23.
 */
public class Glide2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<Meizi> datas;

    public Glide2Adapter(Context mContext, List<Meizi> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            View view= LayoutInflater.from(mContext).inflate(R.layout.item_glide2,parent,false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }else{
            MyViewHolder2 holder2 = new MyViewHolder2(LayoutInflater.from(mContext).inflate(R.layout.page_item_glide2,parent,false));
            return holder2;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            String urls = datas.get(position).getUrl();
            Log.d("GetData","----->>>url= "+urls.toString());
//            Picasso.with(mContext).load(datas.get(position).getUrl()).into(((MyViewHolder) holder).iv);

            ((MyViewHolder) holder).ivs.setTag(R.id.iv_glide2_item,urls);
            Glide.with(mContext).load(urls)
                    .into(((MyViewHolder)holder).ivs);

        }else if(holder instanceof MyViewHolder2){
            ((MyViewHolder2) holder).tv.setText(datas.get(position).getPage()+"页");
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        //判断item是图还是显示页数（图片有URL）
        if(!TextUtils.isEmpty(datas.get(position).getUrl())){
            return 0;
        }else{
            return 1;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivs;
        public MyViewHolder(View itemView) {
            super(itemView);
            ivs= (ImageView) itemView.findViewById(R.id.iv_glide2_item);
        }
    }
    class MyViewHolder2 extends RecyclerView.ViewHolder{
    private TextView tv;
        public MyViewHolder2(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_page);
        }
    }

}
