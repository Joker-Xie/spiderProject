package com.myproject.bigdata.spider;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    private String pageDir = "d:/pages/";
    public void downLoadPage(String url){
        //1.下载网页内容
        String pageCont = downDownload(url);
        //将内容保存到本地
        String localPath = pageDir+System.currentTimeMillis()+".html";
        savePageToLocal(localPath,pageCont);
        //2.解析网页
        Set<String> hrefs = RegexUtils.paserPage(url,pageCont);
        processHrefs(hrefs);
    }

    private void processHrefs(Set<String> hrefs) {
        for (String url :hrefs){
            PageQueue.getInstance().addUrl(url);
        }
        System.out.println("xxxxx");
    }

    //用于保存网页到本地
    private void savePageToLocal(String localPath ,String pageCont) {
        try {
            FileOutputStream fos = new FileOutputStream(localPath);
            fos.write(pageCont.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String downDownload(String url){
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
//            Set<String> urls = RegexUtils.paserPage(url,pageStr);
            return pageStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
