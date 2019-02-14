package com.myproject.bigdata.spider;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/*
* 页面下载器
* */
public class PageDownLoader {
    private static PageDownLoader instance;
    public static PageDownLoader getInstance(){
        if (instance == null){
            instance = new PageDownLoader();
        }
        return instance;
    }
    /*
    * 按照网页地址进行下载
    * */
    public void downlodPage(String url){
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            InputStream in = conn.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf))!= -1){
                baos.write(buf,0,len);
            }
            in.close();
            baos.close();
            //这个是整个网页
            String pageStr = new String(baos.toByteArray());
            Set<String> urls = RegexUtils.paserPage(url,pageStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
