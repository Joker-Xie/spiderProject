package com.myproject.bigdata.spider;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
    public static Set<String> paserPage(String srcUrl,String page) {
        /*
         * <a    href="https://www.douyu.com/"
         * url的正则表达式 <a\s*href="[\u0000-\uffff&&[^\u005c\u0022]]*"
         * */
        Set<String> urls = new HashSet<String>();
        Pattern pattern = Pattern.compile("<a\\s*href=\"([\\u0000-\\uffff&&[^\\u005c\\u0022]]*)\"");
        Matcher m = pattern.matcher(page);
        while (m.find()) {
            String url = m.group(1);
            if (url.startsWith("http")) {
                urls.add(url);
            }
            else if(url.startsWith("/")){
                String daomain = RegexUtils.paserDaomainnamme(srcUrl);
                urls.add(daomain+url);
            }
        }
        return urls;
    }

    public static String paserDaomainnamme(String srcUrl) {
        /*
         * https://www.douyu.com/xxxx
         * url的正则表达式 http:\/\/[\u0000-\uffff&&[^/]]*\/
         * */
        if (!srcUrl.contains("/")){
            return srcUrl;
        }
        Pattern pattern = Pattern.compile("http:\\/\\/[\\u0000-\\uffff&&[^\\/]]*\\/");
        Matcher m = pattern.matcher(srcUrl);
        while (m.find()) {
            String url = m.group();
            return url.substring(0,url.length()-1);
        }
        return null;
    }
}
