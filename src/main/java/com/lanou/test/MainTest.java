package com.lanou.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by dllo on 17/11/23.
 */
public class MainTest {

    @Test
    public void test1(){
        //爬虫测试
        String html = "<html><head><title>首页</title></head>" +
                      "<body><p>这是一个页面</p></body></html>";
        //解析
        Document document = Jsoup.parse(html);
        System.out.println(document.title());
    }

    @Test
    public void test2(){
        //不完整的html
        String html = "<div><p>2222</p>";
        Document document= Jsoup.parseBodyFragment(html);
        System.out.println(document);
    }

    @Test
    public void test3() throws IOException {
        //根据url进行获取document
        //https://www.csdn.net/

        //userAgent()伪造浏览器
        Document document = Jsoup.connect("https://www.csdn.net/")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36").get();
        System.out.println(document.title());
    }

    @Test
    public void test4() throws IOException {
        //访问本地文件
        File input = new File("./src/main/webapp/WEB-INF/home.html");
        Document document = Jsoup.parse(input,"UTF-8");
        //System.out.println(document);

        //Jsoup关于标签的操作和JS/JQ非常类似
        Element element = document.getElementById("div1");
        System.out.println(element);
        //获取标签的值
        //获取标签内的文本信息
        System.out.println(element.text());
        System.out.println(element.html());
        //获取标签的属性值
        System.out.println(element.attr("id"));
        //获取标签名
        System.out.println(element.tagName());

        //获取一组标签 使用Element
        Elements elements = document.getElementsByClass("divClass");
        System.out.println(elements);
        //获取里面所有的值
        System.out.println(elements.text());
    }

    @Test
    public void test5() throws IOException {
        File input = new File("./src/main/webapp/WEB-INF/home1.html");
        Document document = Jsoup.parse(input,"UTF-8");
        //select(选择器)
        Elements elements = document.select("#div1");
        System.out.println(elements);

        Elements links = document.select("a[href]");
        System.out.println(links);

        System.out.println(links.attr("abs:href"));
    }

    @Test
    public void allLinks() throws IOException {
        Document document = Jsoup.connect("https://www.csdn.net/")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36").get();
        //1.a标签
        //2.媒体文件
        //3.引用文件
        Elements nav = document.select(".nav");
        Elements navs = nav.select("a[href]");
        System.out.println(navs);
        Elements links = document.select("a[href]");
        Elements media = document.select("[src]");
        Elements imports = document.select("link[href]");
        //abs 地址
        for (Element link : links) {
           // System.out.println(link.attr("abs:href") + "    " + link.text());
        }

    }


}
