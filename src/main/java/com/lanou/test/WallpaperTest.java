package com.lanou.test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dllo on 17/11/23.
 */
public class WallpaperTest {
    public static void main(String[] args) throws IOException {
        //线程池 容纳5个线程
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Document document = Jsoup.connect("https://bing.ioliu.cn/")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36").get();

        //每张图片对应的信息都包含在一个item中
        Elements itmes = document.select(".item");
        //获取图片的下载地址/下载a标签
        Elements downloadA = itmes.select("a.download");
        System.out.println(downloadA);
        for (int i = 0; i < downloadA.size(); i++) {
            Element aElement = downloadA.get(i);
            //获取图片地址
            String imgURl = aElement.attr("abs:href");
            //下载
            //System.out.println(imgURl);
           // saveImg(imgURl,String.valueOf(i));

            imgDownloadUtils idu = new imgDownloadUtils(imgURl,String.valueOf(i));
            //启动线程
            //idu.start();
            executorService.submit(idu);

        }
    }

//    //下载图片的方法
//    public static void saveImg(String imgURl,String imgNum){
//        //具体下载的代码
//        URL url = null;
//        try {
//            url = new URL(imgURl);
//            URLConnection connection = url.openConnection();
//            //获取数据流
//            InputStream is = connection.getInputStream();
//            File file = new File("/Users/dllo/Desktop/img");
//            if (!file.exists()){
//                file.mkdirs();
//            }
//            OutputStream os = new FileOutputStream(new File("/Users/dllo/Desktop/img",imgNum + ".png"));
//            //写入图片到本地
//            byte[] buf = new byte[1024];
//            int l=0;
//            while ((l=is.read(buf))!=-1){
//                os.write(buf,0,l);
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
