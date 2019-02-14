package com.myproject.bigdata.spider;
/*
* 爬虫类
* */

public class Spider extends Thread {
    @Override
    public void run() {
        while (true){
            String url = PageQueue.getInstance().getFirstUrl();
            PageDownLoader.getInstance().downLoadPage(url);
        }
    }
}
