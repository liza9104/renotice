package com.cookandroid.renotice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String url ="http://www.inu.ac.kr/user/indexSub.do?codyMenuSeq=1202839&siteId=dorm";
    String msg;
    final Bundle bundle =new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =findViewById(R.id.textView2);
        new Thread() {
            @Override
           public void run(){
                Document doc = null;
                try{
                    doc = Jsoup.connect(url).get();
                    Element contents =doc.select("#content-container").first();

                    bundle. putString ("message",msg);
                    Message  msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            textView.setText(bundle.getString("message"));
        }
    };

}