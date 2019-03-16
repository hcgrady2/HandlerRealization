package com.hcworld.handlerlib;

/**
 * Created by hcw on 2019/2/22.
 * Copyright©hcw.All rights reserved.
 */

/**
 * Message 主要是
 */
public class Message {
    Handler target;
    public Object obj;

    public int what;

    public  Message(){

    }


    @Override
    public String toString() {

        return obj.toString();
    }


}
