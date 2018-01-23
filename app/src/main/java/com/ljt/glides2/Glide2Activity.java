package com.ljt.glides2;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ljt.photocachedemo.R;

import org.json.JSONObject;

import java.util.List;

public class Glide2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private Glide2Adapter mGlidAdapter;
    private List<Meizi> meiziList;
    int  page=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide2);
        initWidget();
        initData();
    }
    private void initWidget() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_glide2);
        gridLayoutManager = new GridLayoutManager(Glide2Activity.this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void initData() {
        //这是一个htmL 需要正则
//        final String BASE_URL = "https://image.baidu.com/search/index?ct=201326592&cl=2&st=-1&lm=-1&nc=1&ie=utf-8&tn=baiduimage&ipn=r&rps=1&pv=&fm=rs8&word=小清新美女";
        String urlImg="http://gank.io/api/data/福利/10/1";
        new GetData().execute(urlImg);

    }
    class GetData extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            return MyOkhttp.load(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("GetData","----->>>s= "+s.toString());
            if(!TextUtils.isEmpty(s)){
                JSONObject jsonObject;
                Gson gson = new Gson();
                String jsonData=null;
                try {
                    jsonObject=new JSONObject(s);
                    jsonData=jsonObject.getString("results");
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(meiziList==null||meiziList.size()==0){
                    meiziList=gson.fromJson(jsonData,new TypeToken<List<Meizi>>(){}.getType());
                    Meizi pages = new Meizi();
                    pages.setPage(page);
                    meiziList.add(pages);
                }else{
                    List<Meizi> more= gson.fromJson(jsonData, new TypeToken<List<Meizi>>() {}.getType());
                    meiziList.addAll(more);
                    Meizi pages=new Meizi();
                    pages.setPage(page);
                    meiziList.add(pages);
                }
                Log.d("GetData","----->>>meiziList= "+meiziList.size());
                if(mGlidAdapter==null){
                    mGlidAdapter= new Glide2Adapter(Glide2Activity.this,meiziList);
                    recyclerView.setAdapter(mGlidAdapter);
                }else{
                    mGlidAdapter.notifyDataSetChanged();
                }

            }
        }
    }
}
