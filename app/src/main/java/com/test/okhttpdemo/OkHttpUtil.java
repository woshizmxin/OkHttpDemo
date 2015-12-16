package com.test.okhttpdemo;

import android.os.Handler;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by zhoumao on 15/12/1.
 */
public class OkHttpUtil {

    private final static OkHttpClient okHttpClient = new OkHttpClient();
    private Handler handler;

    public OkHttpUtil(Handler handler){
        this.handler = handler;
    }

    public void syncGet() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Request request = new Request.Builder()
                            .url("http://publicobject.com/helloworld.txt")        //因为浏览器自动添加为https以至于访问出错；
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    if(response.isSuccessful()){
                        Log.i("TAG", "+++CODE: " + response.code() + "+++BODY: " + response.body().string());
                        Log.i("TAG", "response success");
                    }
                }catch (Exception e){
                    Log.i("TAG","response exception");
                }
            }
        });
        thread.start();
    }

    public void asyncGet(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url("http://publicobject.com/helloworld.txt")
                            .build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            //NOT UI Thread
                            if(response.isSuccessful()){
                                Log.i("TAG", "+++CODE: " + response.code() + "+++BODY: " + response.body().string());
                                Log.i("TAG", "response success");
                            }
                        }
                    });
                }catch (Exception e){
                    Log.i("TAG","response exception");
                }
            }
        });
        thread.start();
    }

    public void syncPost(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RequestBody formBody = new FormEncodingBuilder()
                            .add("content", "android")
                            .add("pubUser", "bug")
                            .add("pubTimestamp", "1435541999")
                            .build();

                    Request request = new Request.Builder()
                            .url("http://api.leancloud.cn/1.1/classes/Post")
                            .header("X-LC-Id","W53eoE4Hws6FA2vYAKd2h3Ws")
                            .addHeader("X-LC-Key","osqY32WxfJ25M0NVwJxBrbd6")
                            .addHeader("Content-Type","application/json")
                            .post(formBody)
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("TAG","response.body().string(): "+response.body().string());
                    } else {
                    }

                }catch (Exception e){

                }
            }
        });
        thread.start();
    }


    public void asyncPost(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RequestBody formBody = new FormEncodingBuilder()
                            .add("content", "android")
                            .add("pubUser", "bug")
                            .add("pubTimestamp", "1435541999")
                            .build();

                    Request request = new Request.Builder()
                            .url("http://api.leancloud.cn/1.1/classes/Post")
                            .header("X-LC-Id","W53eoE4Hws6FA2vYAKd2h3Ws")
                            .addHeader("X-LC-Key","osqY32WxfJ25M0NVwJxBrbd6")
                            .addHeader("Content-Type","application/json")
                            .post(formBody)
                            .build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            //NOT UI Thread
                            if(response.isSuccessful()){
                                Log.i("TAG", "+++CODE: " + response.code() + "+++BODY: " + response.body().string());
                                Log.i("TAG", "response success");
                            }
                        }
                    });

                }catch (Exception e){

                }
            }
        });
        thread.start();
    }

}

