package com.example.graduationproject.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class FileIdentification {
    public  HashMap<String,String> getAll(String info){
        HashMap<String,String> map=new HashMap();
        Object[] ChineseTitle=getChineseTitle(info);
        map.put("draftCTitle",ChineseTitle[0].toString());
        Object[] ChineseSummary=getChineseSummary(ChineseTitle[2].toString(),(int)ChineseTitle[1]);
        map.put("draftCSummary",ChineseSummary[0].toString());
        Object[] ChineseKey=getChineseKey(ChineseSummary[2].toString(),(int)ChineseSummary[1]);
        map.put("draftCKey",ChineseKey[0].toString());
        Object[] EnglishTitle=getEnglishTitle(ChineseKey[2].toString(),(int)ChineseKey[1]);
        map.put("draftETitle",EnglishTitle[0].toString());
        Object[] EnglishSummary=getEnglishSummary(EnglishTitle[2].toString(),(int)EnglishTitle[1]);
        map.put("draftESummary",EnglishSummary[0].toString());
        Object[] EnglishKey=getEnglishKey(EnglishSummary[2].toString(),(int)EnglishSummary[1]);
        map.put("draftEKey",EnglishKey[0].toString());
        Object[] Projects=getProject(EnglishKey[2].toString(),(int)EnglishKey[1]);
        map.put("draftCProject",Projects[0].toString());
        map.put("draftEProject",Projects[1].toString());
        String[] Author=getAuthor(Projects[3].toString(),(int)Projects[2]);
        map.put("authorName",Author[0]);
        map.put("authorSex",Author[2]);
        map.put("authorBirth",Author[1]);
        map.put("authorSchool",Author[6]);
        map.put("authorCounty",Author[7]);
        map.put("authorProvince",Author[4]);
        map.put("authorCity",Author[5]);
        map.put("authorEducation",Author[3]);
        map.put("authorEmail",Author[8]);
        return map;
    }
    //获取中文标题
    public  Object[] getChineseTitle(String info){
        int key=0;
        int before=0;//结束点
        ArrayList<String> arrayList=new ArrayList<>();
        for(int i=0;i<info.length();i++){
            if(!String.valueOf(info.charAt(i)).equals(" ")){
                arrayList.add(String.valueOf(info.charAt(i)));
                key=1;
            }else {
                //不允许标题存在空格
                if(key==1){
                  before=i;
                  break;
                }
            }
        }
        StringBuffer getChineseTitle=new StringBuffer();
        for(String s:arrayList){
            getChineseTitle.append(s);
        }
        Object[] objects=new Object[3];
        objects[0]=getChineseTitle.toString();
        objects[1]=before;
        objects[2]=info;
        return objects;
    }
    //截取中文摘要
    public  Object[] getChineseSummary(String info,int before){
        String ChineseSummaryState=info.substring(before);
        int start1=ChineseSummaryState.indexOf("摘要：");
        int start2=ChineseSummaryState.indexOf("摘 要：");
        int end=ChineseSummaryState.indexOf("关键词：");
        System.out.println("start1:"+start1+" start2:"+start2+" end:"+end);
        String ChineseSummaryEnd="";
        if(start1>0){
            if(start2>0){
                if(start1<start2){
                    ChineseSummaryEnd=ChineseSummaryState.substring(start1+3,end);
                }else {
                    ChineseSummaryEnd=ChineseSummaryState.substring(start2+4,end);
                }
            }else {
                ChineseSummaryEnd=ChineseSummaryState.substring(start1+3,end);
            }
        }else {
            ChineseSummaryEnd=ChineseSummaryState.substring(start2+4,end);
        }
        Object[] objects=new Object[3];
        objects[0]=ChineseSummaryEnd;
        objects[1]=end;
        objects[2]=ChineseSummaryState;
        return objects;
    }
    //截取中文关键词
    public  Object[] getChineseKey(String info,int before){
        String ChineseKey=info.substring(before+4);
        int key=0;
        int end=0;//结束点
        ArrayList<String> arrayList=new ArrayList<>();
        for(int i=0;i<ChineseKey.length();i++){
            if(!String.valueOf(ChineseKey.charAt(i)).equals(" ")){
                arrayList.add(String.valueOf(ChineseKey.charAt(i)));
                key=1;
            }else {
                if(key==1){
                    end=i;
                    break;
                }
            }
        }
        StringBuffer getChineseKey=new StringBuffer();
        for(String s:arrayList){
            getChineseKey.append(s);
        }
        Object[] objects=new Object[3];
        objects[0]=getChineseKey.toString();
        objects[1]=end;
        objects[2]=ChineseKey;
        return objects;
    }
    //截取英文标题
    public Object[] getEnglishTitle(String info,int before){
        String EnglishTitle=info.substring(before);
        int key=0;
        int etc=0;
        int end=0;//结束点
        ArrayList<String> arrayList=new ArrayList<>();
        for(int i=0;i<EnglishTitle.length();i++){
            if(!String.valueOf(EnglishTitle.charAt(i)).equals(" ")){
                arrayList.add(String.valueOf(EnglishTitle.charAt(i)));
                key=1;
                etc=0;
            }else {
                if(key==1){
                    arrayList.add(" ");
                    etc++;
                }

                if(etc>1){
                    end=i;
                    break;
                }
            }
        }
        StringBuffer getEnglishTitle=new StringBuffer();
        for(String s:arrayList){
            getEnglishTitle.append(s);
        }
        Object[] objects=new Object[3];
        objects[0]=getEnglishTitle.toString();
        objects[1]=end;
        objects[2]=EnglishTitle;
        return objects;
    }
    //截取英文摘要
    public Object[] getEnglishSummary(String info,int before){
        String EnglishSummary=info.substring(before);
        int start=EnglishSummary.indexOf("Abstract:");
        int end=EnglishSummary.indexOf("Key words:");
        String EnglishSummaryEnd=EnglishSummary.substring(start+9,end);
        Object[] objects=new Object[3];
        objects[0]=EnglishSummaryEnd;
        objects[1]=end;
        objects[2]=EnglishSummary;
        return objects;
    }
    //截取英文关键词
    public Object[] getEnglishKey(String info,int before){
        String EnglishKey=info.substring(before+10);
        int etc=0;
        int key=0;
        int end=0;//结束点
        ArrayList<String> arrayList=new ArrayList<>();
        for(int i=0;i<EnglishKey.length();i++){
            if(!String.valueOf(EnglishKey.charAt(i)).equals(" ")){
                arrayList.add(String.valueOf(EnglishKey.charAt(i)));
                key=1;
                etc=0;
            }else {
                if(key==1){
                    arrayList.add(" ");
                    etc++;
                }

                if(etc>1){
                    end=i;
                    break;
                }
            }
        }
        StringBuffer getEnglishKey=new StringBuffer();
        for(String s:arrayList){
            getEnglishKey.append(s);
        }
        Object[] objects=new Object[3];
        objects[0]=getEnglishKey.toString();
        objects[1]=end;
        objects[2]=EnglishKey;
        return objects;
    }
    //获取基金项目名
    public Object[] getProject(String info,int before){
        String getProject=info.substring(before);
        int start1=getProject.indexOf("基金项目");
        int start2=getProject.indexOf("作者简介");
        String projects=getProject.substring(start1+5,start2);
        String[] projectName=projects.split("；");
        ArrayList<String> China=new ArrayList<>();
        ArrayList<String> English=new ArrayList<>();
        for(String st:projectName){
            String[] children=st.split("，");
            China.add(children[0]);
            English.add(children[1]);
        }
        StringBuffer ChinaName=new StringBuffer();
        for(String s:China){
            ChinaName.append(s);
            ChinaName.append(";");
        }
        StringBuffer EnglishName=new StringBuffer();
        for(String s:English){
            EnglishName.append(s);
            EnglishName.append(";");
        }
        Object[] objects=new Object[4];
        objects[0]=ChinaName.toString();
        objects[1]=EnglishName.toString();
        objects[2]=start2;
        objects[3]=getProject;
        return objects;
    }
    //获取作者信息
    public String[] getAuthor(String info,int before){
        String Author=info.substring(before+5);
        int end=Author.indexOf("。");
        String AuthorNew=Author.substring(0,end);
        String str[]=AuthorNew.split("，");
        String[] AuthorEnd=new String[10];
        AuthorEnd[0]=str[0];
        for(String s:str){
            System.out.println(s+"  --s");
            if(s.charAt(0)>=48 && s.charAt(0)<=57){
                AuthorEnd[1]=s;
            }
            if(s.equals("男") || s.equals("女")){
                AuthorEnd[2]=s;
            }
            if(s.equals("博士") || s.equals("硕士") || s.equals("本科") || s.equals("其他")){
                AuthorEnd[3]=s;
            }
            if(s.indexOf("省")!=-1){
                String st=s.substring(0,s.indexOf("省")+1);
                AuthorEnd[4]=st;
            }
            if(s.indexOf("市")!=-1){
                String st="";
                if(s.indexOf("省")==-1){
                     st=s.substring(0,s.indexOf("市")+1);
                }else {
                    st=s.substring(s.indexOf("省")+1,s.indexOf("市")+1);
                }
                AuthorEnd[5]=st;
            }
            if(s.indexOf("学院")!=-1 || s.indexOf("学校")!=-1 || s.indexOf("公司")!=-1){
                AuthorEnd[6]=s;
            }
            if(s.charAt(0)<=65 || s.charAt(0)>=122){
                AuthorEnd[7]="中国";
            }
            if(s.indexOf("@")!=-1){
                AuthorEnd[8]=s;
            }
        }
        return AuthorEnd;
    }
}
