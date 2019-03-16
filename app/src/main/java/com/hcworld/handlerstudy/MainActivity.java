package com.hcworld.handlerstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hcworld.handlerlib.Handler;
import com.hcworld.handlerlib.Looper;
import com.hcworld.handlerlib.Message;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    private Button handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = findViewById(R.id.handler);

        handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerTest();
            }
        });


    }

    private void handlerTest(){
        //初始化
        //初始化
        Looper.prepare();
        final Handler handler = new Handler(){
            @Override
            public void handlerMessage(Message msg) {
                super.handlerMessage(msg);
                Log.i("HandlerTest", "handlerTest: receive the msg:" + msg.toString());
            }
        };

        Message message  = new Message();
        message.obj = UUID.randomUUID().toString();
        Log.i("HandlerTest", "handlerTest: send msg:" + message.toString());
        handler.sendMessage(message);

        Looper.loop();
    }
}
