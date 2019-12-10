package com.lwx.fish.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lwx.fish.myapplication.R;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wuxing on 2019/11/7.
 */
public class OkHttpActivity extends Activity {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private static final int GET = 1;
    private static final int POST = 2;

    private Button btn_get_post;
    private TextView tv_show;

    private OkHttpClient client = new OkHttpClient();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET:
                    String strGet = (String)msg.obj;
                    tv_show.setText(strGet);
                    break;
                case POST:
                    String strPost = (String)msg.obj;
                    tv_show.setText(strPost);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        initView();
    }

    private void initView(){
        btn_get_post = findViewById(R.id.btn_get_post);
        tv_show = findViewById(R.id.tv_show);

        btn_get_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_show.setText("");
                getDataFromNet();
            }
        });
    }

    private void getDataFromNet(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
                try {
//                    String result = get(url);
                    String result = post(url, "");
                    Message msg = new Message();
//                    msg.what = GET;
                    msg.what = POST;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
