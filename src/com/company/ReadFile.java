package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/*  读文件夹下名称为contrast_type_use.log*的所有文件
*
* **/
public class ReadFile {
    public void readFiles(String path){
        //定义hashMap用来统计统计项id的热度
        Map<String,Integer> statsMap = new HashMap<String,Integer>();
        //定义hashMap用来统计统计项名称的热度
        Map<String,Integer> subMap = new HashMap<String,Integer>();
        // 1.确定源
        String realPath = System.getProperty("user.dir") + path;
        File dir = new File(realPath);
        // 2.确定流
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        // 3.筛选log文件并处理
        File[] files = dir.listFiles();
        for (File file : files){
            if(file.getName().startsWith("contrast_type_use.log") && 0!=file.length()){
                System.out.println(file.getName());
                //读取log文件
                try {
                    fis = new FileInputStream(file);
                    isr = new InputStreamReader(fis);
                    br = new BufferedReader(isr);
                    String str = "";
                    while((str=br.readLine()) != null){
                        //System.out.println(str);
                        String[] item = str.split("\t");
                        String staId = item[1];
                        //System.out.println(staId);
                        String subName = item[2];
                        System.out.println(subName);
                        //统计统计项id点击次数
                        if(!statsMap.containsKey(staId)){
                            statsMap.put(staId,1);
                        }else{
                            int count = statsMap.get(staId);
                            count++;
                            statsMap.put(staId,count);
                        }
                        //统计数据分类点击次数
                        if(!subMap.containsKey(subName)){
                            subMap.put(subName,1);
                        }else{
                            int count2 = subMap.get(subName);
                            count2++;
                            subMap.put(subName,count2);
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        br.close();
                        isr.close();
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        }
        for(String key : statsMap.keySet()){
            System.out.println(key + "\t" +statsMap.get(key));
        }
        System.out.println("----------------");
        for(String key: subMap.keySet()) {
            System.out.println(key + "\t" + subMap.get(key));
        }

        // 4.将处理完成的数据写入到一个文件中
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            fos = new FileOutputStream(System.getProperty("user.dir") + "/result.txt");
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            bw.write("统计项id\t点击次数\n");
            for(String key : statsMap.keySet()){
                bw.write(key + "\t" + statsMap.get(key) + "\n");
            }
            bw.write("\n\n\n");
            bw.write("数据分类\t点击次数\n");
            for(String key : subMap.keySet()){
                bw.write(key + "\t" + subMap.get(key) + "\n");
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
                osw.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
