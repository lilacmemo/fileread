package com.company.reader;

import com.company.entity.IDCount;
import com.company.entity.SubCount;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadDatas {

    //判断统计项idlist当中是否包含特定对象,如果包含返回下标,不包含返回-1
    public int adjustContain(List<IDCount> lists,String staId){
        for (int i = 0 ;i < lists.size();i++){
            if (lists.get(i).getStaId().equals(staId)){
                return i;
            }
        }
        return -1;
    }

    //统计统计项id的热度
    public List<IDCount> readId(String path){
        //idCounts用来存放统计项id的热度
        List<IDCount> idCounts = new ArrayList<>();
        // 1.确定文件的读取源
        String realPath = System.getProperty("user.dir") + path;
        File dir = new File(realPath);
        // 2.确定使用的数据流
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        // 3.数据操作
        File[] files = dir.listFiles();
        for (File file : files){
            if (file.getName().startsWith("contrast_type_use.log") && (file.length() != 0)){
                //System.out.println(file.getName());
                try {
                    fis = new FileInputStream(file);
                    isr = new InputStreamReader(fis);
                    br = new BufferedReader(isr);
                    String str = "";
                    while ((str=br.readLine()) != null){
                        String[] items = str.split("\t");
                        String staId = items[1];
                        //如果当前的统计项id存在则修改对应的count++,如果不存在则添加
                        int index = adjustContain(idCounts,staId);
                        //System.out.println(index);
                        if (index != -1){
                            int newCount = idCounts.get(index).getCount();
                            idCounts.get(index).setCount(++newCount);
                        }else {
                            IDCount idCount = new IDCount(staId,1);
                            idCounts.add(idCount);
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    {
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
        }
        return idCounts;
    }

    //如果当前统计分类热度已经存在,则返回list的index,否则返回-1
    public int containSub(List<SubCount> subCounts,String staId,String subName){
       for (int i = 0;i < subCounts.size();i++){
           if (subCounts.get(i).getSubName().equals(subName) && subCounts.get(i).getStaId().equals(staId)){
               return i;
           }
       }
       return -1;
    }

    //统计数据分类name的热度

    public List<SubCount> readSub(String path){
        List<SubCount> subCounts = new ArrayList<>();
        // 1.确定文件的读取源
        String realPath = System.getProperty("user.dir") + path;
        File dir = new File(realPath);
        // 2.确定流
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        // 3.数据处理
        File[] files = dir.listFiles();
        for (File file : files){
            if(file.getName().startsWith("contrast_type_use.log") && (file.length() != 0)){
                try {
                    fis = new FileInputStream(file);
                    isr = new InputStreamReader(fis);
                    br = new BufferedReader(isr);
                    String str = "";
                    while((str = br.readLine()) != null){
                        String[] item = str.split("\t");
                        String staId = item[1];
                        String subName = item[2];
                        //System.out.println(subName);
                        int index = containSub(subCounts,staId,subName);
                        if(index != -1){
                            int newCount = subCounts.get(index).getCount();
                            subCounts.get(index).setCount(++newCount);
                        }else{
                            SubCount subCount = new SubCount(staId,subName,1);
                            subCounts.add(subCount);
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

        return subCounts;
    }
}
