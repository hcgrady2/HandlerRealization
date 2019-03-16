package com.hcworld.handlerlib;

/**
 * Created by hcw on 2019/2/22.
 * Copyright©hcw.All rights reserved.
 */

public class Looper {

    //消息队列
    public MessageQueue mQueue;

    static ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();

    private Looper(){
        mQueue = new MessageQueue();
    }

    /**
     * 初始化 looper，这个方法只能调用一次
     */
    public static void prepare(){
        //判断是否是当前线程
        if (sThreadLocal.get() != null){
            throw  new RuntimeException("一个线程只能有一个 looer");
        }
        sThreadLocal.set(new Looper());
    }


    //获取当前线程对象
    //外界拿对象
    public static Looper myLooper(){
        //保证返回的对象是当前线程的
        return sThreadLocal.get();
    }

    /**
     * 实现循环
     */
    public static void loop(){
        Looper me = myLooper();
        if (me == null){
            throw  new RuntimeException("当前线程没有 Looper 对象，请调用  prepare");
        }
        //拿到 Looper 里面的 队列
        MessageQueue queue = me.mQueue;
        //开始循环
        for (;;){
            Message msg = queue.next();
            if (msg != null){
                msg.target.dispatchMessage(msg);
            }
        }
    }



}
