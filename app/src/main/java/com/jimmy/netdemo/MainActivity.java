package com.jimmy.netdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.jimmy.netdemo.util.HttpUtil;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private SendUrlTask mSendUrlTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView)findViewById(R.id.webview);

        mSendUrlTask = new SendUrlTask("http://www.baidu.com");
        mSendUrlTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mSendUrlTask != null) {
            mSendUrlTask.cancel(true);
        }
    }

    private class SendUrlTask extends AsyncTask<Void,Void,String>{
        private String mUrl;
        public SendUrlTask(String url){
            mUrl = url;
        }
        @Override
        protected String doInBackground(Void... voids) {
            //进行网络请求
            return HttpUtil.sendUrl(mUrl);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mWebView.loadData(s,"text/html;charset=utf-8",null);
        }
    }
}
