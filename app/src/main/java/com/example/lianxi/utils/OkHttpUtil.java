package com.example.lianxi.utils;

import android.os.Environment;
import android.os.Handler;

import com.example.lianxi.inter.ICallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtil extends OkHttpClient {
    private Handler handler=new Handler();
    private OkHttpClient client;
    private static  volatile OkHttpUtil instance;

        private OkHttpUtil(){
            client = new OkHttpClient.Builder()//构建器
                    .connectTimeout(15, TimeUnit.SECONDS)//连接超时
                    .writeTimeout(20, TimeUnit.SECONDS)//写入超时
                    .readTimeout(20, TimeUnit.SECONDS)//读取超时
                    .build();
        }
    public static  OkHttpUtil getInstance(){
        if (instance==null){
            synchronized (OkHttpUtil.class){
                if (instance==null){
                    instance=new OkHttpUtil();
                }
            }
        }
        return  instance;
    }
    public void doget(String  url, final ICallBack callBack, final Type type){
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                final Object o = gson.fromJson(string, type);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(o);
                    }
                });
            }
        });
    }



}
