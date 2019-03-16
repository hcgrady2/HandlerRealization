package com.hcworld.handlerlib;

/**
 * Created by hcw on 2019/2/22.
 * Copyright©hcw.All rights reserved.
 */


/**
 * 实现收发消息
 */
public class Handler {
    // handler 需要有一个 MessageQueue
    public MessageQueue mQueue;

    //构造函数
    public  Handler(){
        Looper mLooper = Looper.myLooper();
        mQueue = mLooper.mQueue;
    }


    /**
     *  发送消息
     * @param msg
     */
    public void sendMessage(Message msg){
        msg.target = this;
        //
        mQueue.enqueueMessage(msg);
    }


    /**
     * 处理消息
     * @param msg
     */
    public void handlerMessage(Message msg){

    }


    /**
     *    分发消息
     * @param msg
     */
    public  void dispatchMessage(Message msg){
        handlerMessage(msg);
    }


}
