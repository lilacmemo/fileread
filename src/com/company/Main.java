package com.company;

import com.company.entity.IDCount;
import com.company.entity.SubCount;
import com.company.reader.ReadDatas;
import com.company.writer.WriteExcel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	    //System.out.println(System.getProperty("user.dir"));
	   // File files = new File((System.getProperty("user.dir") + "/logs"));
       // ReadFile rf = new ReadFile();
       // rf.readFiles("/logs");
        ReadDatas rd = new ReadDatas();
        //idCounts用来存放统计项id热度
        List<IDCount> idCounts = new ArrayList<IDCount>();
        idCounts = rd.readId("/mivideo_tongji");
        Collections.sort(idCounts);
        //打印idCounts测试
        for (IDCount idCount : idCounts){
            System.out.println(idCount.getStaId() + "----" + idCount.getCount());
        }
        System.out.println("----------------");
        List<SubCount> subCounts = new ArrayList<SubCount>();
        subCounts = rd.readSub("/mivideo_tongji");
        Collections.sort(subCounts);
        for (SubCount subCount : subCounts){
            System.out.println(subCount.getStaId() + "---" +subCount.getSubName() + "---" + subCount.getCount());
        }

        WriteExcel we = new WriteExcel();
        we.writer(idCounts,subCounts);

    }
}


