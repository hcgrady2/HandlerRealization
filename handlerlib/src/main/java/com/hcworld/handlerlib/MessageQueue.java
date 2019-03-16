package com.hcworld.handlerlib;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hcw on 2019/2/22.
 * Copyright©hcw.All rights reserved.
 */

/**
 * 消息队列
 */
public class MessageQueue {

    //用数组模拟队列
    private Message[] items;

    //入队和出队的 message 坐标
    private int putIndex;
    private int takeIndex;

    //计数器
    private int count;

    //锁
    private Lock lock;

    //条件变量
    private Condition notEmpty;
    private Condition notFull;

    //构造函数进行变量初始化
    public MessageQueue(){
        items = new Message[50];
        lock = new ReentrantLock();//可重入锁
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }


    /**
     * 入队,msg 入队
     * @param msg
     */
    public void enqueueMessage(Message msg){
         items[putIndex] = msg;


        try {
            lock.lock();
            //消息队列满，等待消费
            while (count == 50){
                notFull.await();
            }
            items[putIndex] = msg;
            //入队
            putIndex = (++ putIndex == items.length)?0:putIndex;
            count++;

            //已经生成了，可以消费
            notEmpty.signalAll();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //解锁
            lock.unlock();
        }




    }


    /**
     * 出队,取出队列中的消息
     * @return
     */
    public Message next(){

        Message msg = null;
        try{
            lock.lock();
            while (count == 0){
                notEmpty.await();
            }

            //取出消息
            msg = items[takeIndex];
            items[takeIndex] = null;
            takeIndex =(++ takeIndex == items.length)?0:takeIndex;
            count--;

            //用 signalAll 与 while 对应
            notFull.signalAll();



        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        return msg;
    }




}
