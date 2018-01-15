package com.ljt.picassos;

import android.net.Uri;

import com.squareup.picasso.Downloader;

import java.io.IOException;

/**
 * Created by ${JT.L} on 2018/1/15.
 *
 * 不想用默认提供的Downloader,那么我们可以自定义一个下载器然后配置进去。
 */

public class CustomDownloader implements Downloader{
    @Override
    public Response load(Uri uri, int networkPolicy) throws IOException {
        return null;
    }

    @Override
    public void shutdown() {

    }
}
