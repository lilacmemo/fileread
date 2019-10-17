package com.company;

import java.io.*;

public class IOTest {

    /*
    * 文件字节输入流
    * */

    public static void test01(){
        // 1.创建源
        File src = new File("abc.txt");
        // 2.选择流
        InputStream is = null;
        // 3.操作
        try {
            is = new FileInputStream(src);
            byte[] flush = new byte[1024*10];
            int len = -1; //接收长度
            while((len = is.read(flush)) != -1){
                //字节数组 --> 字符串  解码
                String str = new String(flush,0,len);
                System.out.println(str);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /*
    * 文件字节输出流
    * */

    public static void test02(){
        // 1.创建源
        File dest = new File("dest.txt");
        // 2.选择流
        OutputStream os = null;
        // 3.c=操作
        try {
            //false时重新执行会覆盖,true时会追加
            os = new FileOutputStream(dest,false);
            String msg = "IO is simple";
            byte[] datas = msg.getBytes();
            os.write(datas,0,datas.length);
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    * 使用文件输入流与输出流实现文件拷贝
    * */
    public static void test03(String srcPath,String destPath){
        // 1.创建源
        File src = new File("abc.txt");
        File dest = new File("dest.txt");
        // 2.选择流
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);

            // 3.操作(分段读取)
            byte[] flush = new byte[1024];
            int len = -1;
            while((len = is.read(flush)) != -1){
                os.write(flush,0,len);
            }
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            // 4.释放资源 先打开的后关闭
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
