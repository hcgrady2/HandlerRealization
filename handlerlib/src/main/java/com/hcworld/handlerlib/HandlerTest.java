package com.hcworld.handlerlib;

import java.util.UUID;

/**
 * Created by hcw on 2019/2/22.
 * Copyright©hcw.All rights reserved.
 */

//主线程
public class HandlerTest {
    public static void main(String[] args){
        //初始化
        Looper.prepare();
        final Handler handler = new Handler(){
            @Override
            public void handlerMessage(Message msg) {
                super.handlerMessage(msg);
                System.out.println("send = " + Thread.currentThread().getName() +",msg==" + msg.toString());

            }
        };

        //创建线程
        for (int i = 0; i < 10; i ++){
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        Message message  = new Message();
                        //打印
                        //同步
                        synchronized (UUID.class){
                            message.obj = UUID.randomUUID().toString();
                        }
                        System.out.println("send = " + Thread.currentThread().getName() +",msg==" + message.toString());
                        handler.sendMessage(message);
                    }
                }
            }.start();
        }
        Looper.loop();
    }
}
