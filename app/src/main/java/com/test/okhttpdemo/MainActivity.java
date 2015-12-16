package com.test.okhttpdemo;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    public final static int SyncGet = 0;
    public final static int SyncPost = 1;

    private OkHttpUtil okHttpUtil;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        okHttpUtil = new OkHttpUtil(handler);    //保证只有一个okhttpclient；
        handler =  new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case SyncGet :
                        break;
                    case SyncPost:

                        break;
                    default:break;
                }
            }
        };

        okHttpUtil.asyncPost();
    }
}