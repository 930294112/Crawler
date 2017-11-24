package com.lanou.test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dllo on 17/11/23.
 */
public class imgDownloadUtils extends Thread {

    private String imgURL;
    private String imgName;


    @Override
    public void run() {
       saveImg(imgURL,imgName);
    }
    //下载图片的方法
    public void saveImg(String imgURl,String imgNum){
        //具体下载的代码
        URL url = null;
        try {
            url = new URL(imgURl);
            URLConnection connection = url.openConnection();
            //获取数据流
            InputStream is = connection.getInputStream();
            File file = new File("/Users/dllo/Desktop/img");
            if (!file.exists()){
                file.mkdirs();
            }
            OutputStream os = new FileOutputStream(new File("/Users/dllo/Desktop/img",imgNum + ".png"));
            //写入图片到本地
            byte[] buf = new byte[1024];
            int l=0;
            while ((l=is.read(buf))!=-1){
                os.write(buf,0,l);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //利用构造函数从外部传值
    public imgDownloadUtils(String imgURL, String imgName) {
        this.imgURL = imgURL;
        this.imgName = imgName;
    }
}
