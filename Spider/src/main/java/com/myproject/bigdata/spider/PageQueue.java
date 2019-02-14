package com.myproject.bigdata.spider;

import java.util.LinkedList;
/*
* url队列用于存储待爬取的url地址
* */
public class PageQueue {
    private static PageQueue instance;
    public static PageQueue getInstance(){
        if (instance == null){
            instance = new PageQueue();
        }
        return instance;
    }
    private PageQueue() {
        queue.add("http://www.it18zhang.com/");
    }
    private final int Max = 10000;
    private LinkedList<String> queue = new LinkedList<String>();



    //将url放入队列中
    public synchronized void addUrl(String url) {
        while (queue.size() >= Max){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(url);
        notifyAll();
    }
//获取队列中第一个url地址
    public synchronized String getFirstUrl(){
        while (queue.size()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String url = queue.removeFirst();
        notifyAll();
        return url;
    }
}
