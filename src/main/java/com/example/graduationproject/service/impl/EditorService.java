package com.example.graduationproject.service.impl;

import com.example.graduationproject.mapper.EditorMapper;
import com.example.graduationproject.model.*;
import com.example.graduationproject.model.Vo.*;
import com.example.graduationproject.service.IEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("editorService")
public class EditorService implements IEditorService {
    @Autowired
    private EditorMapper editorMapper;

    @Override
    public List<EditorBrowse> getDraftBrowse(String draftType,int index) {
        List<EditorBrowse> stateInformation=new ArrayList<>();
        List<EditorBrowse> st=editorMapper.getDraftBrowse();
        EditorBrowse[] array=new EditorBrowse[st.size()];
        int o=0;
        for(EditorBrowse s:st){
            array[o]=s;
            o++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getDate().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getDate().split("-")[0]);
                if(year2>year1){
                    EditorBrowse u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getDate().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getDate().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        EditorBrowse u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getDate().split("-")[1]);
            int date1=Integer.parseInt(array[i].getDate().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getDate().split("-")[0]);
                int date2=Integer.parseInt(array[j].getDate().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            EditorBrowse u=array[j];
                            array[j]=array[i];
                            array[i]=u;
                        }
                    }
                }
            }
        }
        //输出
        if(draftType.equals("全部")){
            if(index==0){
                for(int i=0;i< array.length;i++){
                    stateInformation.add(array[i]);
                }
            }else if(index==1){
                for(int i= array.length-1;i>=0;i--){
                    stateInformation.add(array[i]);
                }
            }
        }else {
            if(index==0){
                for(int i=0;i< array.length;i++){
                    if(array[i].getDraftType().equals(draftType)){
                        stateInformation.add(array[i]);
                    }
                }
            }else if(index==1){
                for(int i= array.length-1;i>=0;i--){
                    if(array[i].getDraftType().equals(draftType)){
                        stateInformation.add(array[i]);
                    }
                }
            }
        }
        return stateInformation;
    }

    @Override
    public boolean adopt(String ado,int stateId) {
        System.out.println("ado:"+ado);
        String auditIds=ado.substring(1,ado.length()-1);
        Date date=new Date();
        String edit=String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date);
        return editorMapper.adopt(stateId,edit,auditIds)>0?true:false;
    }
    @Override
    public boolean adoptRe(int stateId) {
        Date date=new Date();
        String edit=String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date);
        return editorMapper.adoptRe(stateId,edit)>0?true:false;
    }
    @Override
    public boolean ret(String adviseOne,String adviseTwo,int stateId) {
        System.out.println("adviseOne"+adviseOne);
        System.out.println("adviseTwo"+adviseTwo);
        String advise=adviseOne.substring(1,adviseOne.length()-1);
        String[] one=advise.split(",");
        StringBuffer stringBuffer=new StringBuffer();
        for(int i=0;i<one.length;i++){
            String adviseT=one[i].substring(1,one[i].length()-1);
            stringBuffer.append(adviseT);
            stringBuffer.append(";");
        }
        stringBuffer.append(adviseTwo);
        System.out.println("stringBuff:"+stringBuffer.toString());
        Date date=new Date();
        String edit=String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date);
        return editorMapper.ret(stateId,edit,stringBuffer.toString())>0?true:false;
    }

    @Override
    public EditorOriginal original(Integer stateId) {
        Modify modify= editorMapper.selectStateIdNew(stateId);
        return editorMapper.original(modify.getStateId());
    }

    @Transactional
    @Override
    public List<EditorAdopt> getAdoptDraft(int auditBrowse,int index) {
        List<EditorAdopt> stateInformation=new ArrayList<>();
        List<EditorAdopt> st=new ArrayList<>();
        List<EditorAdopt> ste=editorMapper.getAdoptDraft();
        for(EditorAdopt e:ste){
            EditorAdopt editorAdopt=e;
            String[] auditIds=editorAdopt.getAuditIds().split(",");
            ArrayList<AuditAdoptAudit>  audits=new ArrayList<>();
            for(int i=0;i<auditIds.length;i++){
                List<AuditAdoptAudit> audit= editorMapper.selectAuditAdoptAudit(Integer.parseInt(auditIds[i]));
                int ind=0;
                for(AuditAdoptAudit a:audit){
                    if(a.getStateId()==0){
                        a.setAuditAdoptResult("未审核");
                        audits.add(a);
                        break;
                    }else if(a.getStateId()==editorAdopt.getStateId()){
                        audits.add(a);
                        break;
                    }else {
                        ind++;
                    }
                    if(ind==audit.size()){
                        AuditAdoptAudit au=new AuditAdoptAudit();
                        au.setAuditId(a.getAuditId());
                        au.setAuditName(a.getAuditName());
                        au.setAuditSex(a.getAuditSex());
                        au.setAuditAge(a.getAuditAge());
                        au.setAuditGraduationSchool(a.getAuditGraduationSchool());
                        au.setAuditAdoptCount(a.getAuditAdoptCount());
                        au.setAuditBrowseCount(a.getAuditBrowseCount());
                        au.setAuditWorkYear(a.getAuditWorkYear());
                        au.setAuditPortrait(a.getAuditPortrait());
                        au.setAuditType(a.getAuditType());
                        au.setAuditAdoptResult("未审核");
                        audits.add(au);
                    }
                }
            }
            editorAdopt.setAuditAdoptAudits(audits);
            st.add(editorAdopt);
        }
        EditorAdopt[] array=new EditorAdopt[st.size()];
        int o=0;
        for(EditorAdopt s:st){
            array[o]=s;
            o++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getDate().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getDate().split("-")[0]);
                if(year2>year1){
                    EditorAdopt u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getDate().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getDate().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        EditorAdopt u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getDate().split("-")[1]);
            int date1=Integer.parseInt(array[i].getDate().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getDate().split("-")[0]);
                int date2=Integer.parseInt(array[j].getDate().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            EditorAdopt u=array[j];
                            array[j]=array[i];
                            array[i]=u;
                        }
                    }
                }
            }
        }
        //输出
        if(auditBrowse==2){
            if(index==0){
                for(int i=0;i< array.length;i++){
                    stateInformation.add(array[i]);
                }
            }else if(index==1){
                for(int i= array.length-1;i>=0;i--){
                    stateInformation.add(array[i]);
                }
            }
        }else {
            if(index==0){
                for(int i=0;i< array.length;i++){
                    if(array[i].getAuditBrowse()==auditBrowse){
                        stateInformation.add(array[i]);
                    }
                }
            }else if(index==1){
                for(int i= array.length-1;i>=0;i--){
                    if(array[i].getAuditBrowse()==auditBrowse){
                        stateInformation.add(array[i]);
                    }
                }
            }
        }
        return stateInformation;
    }

    @Override
    public List<EditorBrowse> getReturnDraft(int index) {
        List<EditorBrowse> stateInformation=new ArrayList<>();
        List<EditorBrowse> st=editorMapper.getReturnDraft();
        EditorBrowse[] array=new EditorBrowse[st.size()];
        int o=0;
        for(EditorBrowse s:st){
            array[o]=s;
            o++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEditorAdoptDate().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getEditorAdoptDate().split("-")[0]);
                if(year2>year1){
                    EditorBrowse u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEditorAdoptDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getEditorAdoptDate().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getEditorAdoptDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getEditorAdoptDate().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        EditorBrowse u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEditorAdoptDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getEditorAdoptDate().split("-")[1]);
            int date1=Integer.parseInt(array[i].getEditorAdoptDate().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getEditorAdoptDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getEditorAdoptDate().split("-")[0]);
                int date2=Integer.parseInt(array[j].getEditorAdoptDate().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            EditorBrowse u=array[j];
                            array[j]=array[i];
                            array[i]=u;
                        }
                    }
                }
            }
        }
        //输出
            if(index==0){
                for(int i=0;i< array.length;i++){
                    stateInformation.add(array[i]);
                }
            }else if(index==1){
                for(int i= array.length-1;i>=0;i--){
                    stateInformation.add(array[i]);
                }
            }
        return stateInformation;
    }

    @Transactional
    @Override
    public List<EditorBrowse> getAuditAdopt(int auditId,String draftType,Integer index) {
        List<EditorBrowse> stateInformation=new ArrayList<>();
        List<EditorBrowse> st=new ArrayList<>();
        List<EditorBrowse> se=editorMapper.getAuditAdopt();
        List<AuditAdopt> auditAdopts= editorMapper.AuditAdopt(auditId);
        for(EditorBrowse e:se){
            String[] auditIds=e.getAuditIds().split(",");
            for(int i=0;i<auditIds.length;i++){
                if(auditId==Integer.parseInt(auditIds[i])){
                    int indexY=0;
                    for(AuditAdopt a:auditAdopts){
                        if(e.getStateId()==a.getStateId()){
                            indexY=1;
                            break;
                        }
                    }
                    if(indexY==0){
                        st.add(e);
                    }
                }
            }
        }
        EditorBrowse[] array=new EditorBrowse[st.size()];
        int o=0;
        for(EditorBrowse s:st){
            array[o]=s;
            o++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEditorAdoptDate().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getEditorAdoptDate().split("-")[0]);
                if(year2>year1){
                    EditorBrowse u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEditorAdoptDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getEditorAdoptDate().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getEditorAdoptDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getEditorAdoptDate().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        EditorBrowse u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEditorAdoptDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getEditorAdoptDate().split("-")[1]);
            int date1=Integer.parseInt(array[i].getEditorAdoptDate().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getEditorAdoptDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getEditorAdoptDate().split("-")[0]);
                int date2=Integer.parseInt(array[j].getEditorAdoptDate().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            EditorBrowse u=array[j];
                            array[j]=array[i];
                            array[i]=u;
                        }
                    }
                }
            }
        }
        //输出
        if(draftType.equals("全部")){
            if(index==0){
                for(int i=0;i< array.length;i++){
                    stateInformation.add(array[i]);
                }
            }else if(index==1){
                for(int i= array.length-1;i>=0;i--){
                    stateInformation.add(array[i]);
                }
            }
        }else {
            if(index==0){
                for(int i=0;i< array.length;i++){
                    if(array[i].getDraftType().equals(draftType)){
                        stateInformation.add(array[i]);
                    }
                }
            }else if(index==1){
                for(int i= array.length-1;i>=0;i--){
                    if(array[i].getDraftType().equals(draftType)){
                        stateInformation.add(array[i]);
                    }
                }
            }
        }
        return stateInformation;
    }

    @Transactional
    @Override
    public boolean auditAdoptOne(int auditId, int stateId, String auditAdoptResult) {
        Date date=new Date();
        String edit=String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date);
        boolean index=editorMapper.auditAdoptOne(auditId, stateId, auditAdoptResult,edit)>0?true:false;
        if(index){
            index=editorMapper.updateAuditOneCount(auditId)>0?true:false;
        }
        return index;
    }

    @Transactional
    @Override
    public boolean auditAdoptTwo(int auditId, int stateId, String adviseOne, String adviseTwo) {
        System.out.println("adviseOne"+adviseOne);
        System.out.println("adviseTwo"+adviseTwo);
        String advise=adviseOne.substring(1,adviseOne.length()-1);
        String[] one=advise.split(",");
        StringBuffer stringBuffer=new StringBuffer();
        for(int i=0;i<one.length;i++){
            String adviseT=one[i].substring(1,one[i].length()-1);
            stringBuffer.append(adviseT);
            stringBuffer.append(";");
        }
        stringBuffer.append(adviseTwo);
        Date date=new Date();
        String edit=String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date);
        boolean index=editorMapper.auditAdoptTwo(auditId,stateId,stringBuffer.toString(),edit)>0?true:false;
        if(index){
            index=editorMapper.updateAuditTwoCount(auditId)>0?true:false;
        }
        return index;
    }

    @Override
    public List<AuditAdoptTrue> getAuditAdoptTrue(int auditId, String draftType, int index) {
        List<AuditAdoptTrue> stateInformation=new ArrayList<>();
        List<AuditAdoptTrue> st=new ArrayList<>();
        List<AuditAdoptTrue> se=editorMapper.getAuditAdoptTrue();
        for(AuditAdoptTrue e:se) {
            if (auditId == e.getAuditId()) {
                st.add(e);
            }
        }
        AuditAdoptTrue[] array=new AuditAdoptTrue[st.size()];
        int o=0;
        for(AuditAdoptTrue s:st){
            array[o]=s;
            o++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getAuditAdoptDateT().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getAuditAdoptDateT().split("-")[0]);
                if(year2>year1){
                    AuditAdoptTrue u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getAuditAdoptDateT().split("-")[0]);
            int month1=Integer.parseInt(array[i].getAuditAdoptDateT().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getAuditAdoptDateT().split("-")[1]);
                int year2=Integer.parseInt(array[j].getAuditAdoptDateT().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        AuditAdoptTrue u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getAuditAdoptDateT().split("-")[0]);
            int month1=Integer.parseInt(array[i].getAuditAdoptDateT().split("-")[1]);
            int date1=Integer.parseInt(array[i].getAuditAdoptDateT().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getAuditAdoptDateT().split("-")[1]);
                int year2=Integer.parseInt(array[j].getAuditAdoptDateT().split("-")[0]);
                int date2=Integer.parseInt(array[j].getAuditAdoptDateT().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            AuditAdoptTrue u=array[j];
                            array[j]=array[i];
                            array[i]=u;
                        }
                    }
                }
            }
        }
        //输出
        if(draftType.equals("全部")){
            if(index==0){
                for(int i=0;i< array.length;i++){
                    stateInformation.add(array[i]);
                }
            }else if(index==1){
                for(int i= array.length-1;i>=0;i--){
                    stateInformation.add(array[i]);
                }
            }
        }else {
            if(index==0){
                for(int i=0;i< array.length;i++){
                    if(array[i].getDraftType().equals(draftType)){
                        stateInformation.add(array[i]);
                    }
                }
            }else if(index==1){
                for(int i= array.length-1;i>=0;i--){
                    if(array[i].getDraftType().equals(draftType)){
                        stateInformation.add(array[i]);
                    }
                }
            }
        }
        return stateInformation;
    }

    @Override
    public List<AuditAdoptTrue> getAuditAdoptFalse(int auditId, String draftType, int index) {
        List<AuditAdoptTrue> stateInformation=new ArrayList<>();
        List<AuditAdoptTrue> st=new ArrayList<>();
        List<AuditAdoptTrue> se=editorMapper.getAuditAdoptFalse();
        for(AuditAdoptTrue e:se) {
            if (auditId == e.getAuditId()) {
                st.add(e);
            }
        }
        AuditAdoptTrue[] array=new AuditAdoptTrue[st.size()];
        int o=0;
        for(AuditAdoptTrue s:st){
            array[o]=s;
            o++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getAuditAdoptDateT().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getAuditAdoptDateT().split("-")[0]);
                if(year2>year1){
                    AuditAdoptTrue u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getAuditAdoptDateT().split("-")[0]);
            int month1=Integer.parseInt(array[i].getAuditAdoptDateT().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getAuditAdoptDateT().split("-")[1]);
                int year2=Integer.parseInt(array[j].getAuditAdoptDateT().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        AuditAdoptTrue u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getAuditAdoptDateT().split("-")[0]);
            int month1=Integer.parseInt(array[i].getAuditAdoptDateT().split("-")[1]);
            int date1=Integer.parseInt(array[i].getAuditAdoptDateT().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getAuditAdoptDateT().split("-")[1]);
                int year2=Integer.parseInt(array[j].getAuditAdoptDateT().split("-")[0]);
                int date2=Integer.parseInt(array[j].getAuditAdoptDateT().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            AuditAdoptTrue u=array[j];
                            array[j]=array[i];
                            array[i]=u;
                        }
                    }
                }
            }
        }
        //输出
        if(draftType.equals("全部")){
            if(index==0){
                for(int i=0;i< array.length;i++){
                    stateInformation.add(array[i]);
                }
            }else if(index==1){
                for(int i= array.length-1;i>=0;i--){
                    stateInformation.add(array[i]);
                }
            }
        }else {
            if(index==0){
                for(int i=0;i< array.length;i++){
                    if(array[i].getDraftType().equals(draftType)){
                        stateInformation.add(array[i]);
                    }
                }
            }else if(index==1){
                for(int i= array.length-1;i>=0;i--){
                    if(array[i].getDraftType().equals(draftType)){
                        stateInformation.add(array[i]);
                    }
                }
            }
        }
        return stateInformation;
    }

    @Transactional
    @Override
    public boolean Outcome(int stateId,int draftInformationId,int auditFree, int versionFree, String result,
                           String auditFreeDate,String versionFreeDate,String draftCTitle,
                           String auditAdoptAdvise,String wishDate) {
        System.out.println(auditAdoptAdvise);
        boolean index=false;
        Date date=new Date();
        String edit=String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date);
        if(result.equals("不及格")){
            String advise=auditAdoptAdvise.substring(1,auditAdoptAdvise.length()-1);
            if(advise.contains(",")){
                String[] one=advise.split(",");
                StringBuffer stringBuffer=new StringBuffer();
                for(int i=0;i<one.length;i++){
                    String adviseT=one[i].substring(1,one[i].length()-1);
                    stringBuffer.append(adviseT);
                    stringBuffer.append(";");
                }
                editorMapper.insertAuditAdoptTwo(stateId,stringBuffer.toString(),wishDate);
                editorMapper.insertAuditFree(draftInformationId,draftCTitle,auditFree,auditFreeDate,edit);
            }else {
                String adviseT=advise.substring(1,advise.length()-1);
                editorMapper.insertAuditAdoptTwo(stateId,adviseT,wishDate);
                editorMapper.insertAuditFree(draftInformationId,draftCTitle,auditFree,auditFreeDate,edit);
            }
            index=editorMapper.updateEditorTwo(stateId,edit)>0?true:false;
        }else {
            editorMapper.insertVersionFree(draftInformationId,draftCTitle,versionFree,versionFreeDate,edit);
            editorMapper.insertAuditFree(draftInformationId,draftCTitle,auditFree,auditFreeDate,edit);
            index=editorMapper.updateEditorOne(stateId,edit)>0?true:false;
        }
        return index;
    }

    @Override
    public List<VersionFreeState> getVersionFree(int index) {
        List<VersionFreeState> stateInformation=new ArrayList<>();
        List<VersionFreeState> st=editorMapper.getVersionFree();
        VersionFreeState[] array=new VersionFreeState[st.size()];
        int o=0;
        for(VersionFreeState s:st){
            array[o]=s;
            o++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEndDate().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getEndDate().split("-")[0]);
                if(year2>year1){
                    VersionFreeState u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEndDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getEndDate().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getEndDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getEndDate().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        VersionFreeState u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEndDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getEndDate().split("-")[1]);
            int date1=Integer.parseInt(array[i].getEndDate().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getEndDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getEndDate().split("-")[0]);
                int date2=Integer.parseInt(array[j].getEndDate().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            VersionFreeState u=array[j];
                            array[j]=array[i];
                            array[i]=u;
                        }
                    }
                }
            }
        }
        //输出
            if(index==0){
                for(int i=0;i< array.length;i++){
                    stateInformation.add(array[i]);
                }
            }else if(index==1){
                for(int i= array.length-1;i>=0;i--){
                    stateInformation.add(array[i]);
                }
            }
        return stateInformation;
    }

    @Override
    public boolean updateVersionFree(String endDate, int deliverMoney,int versionFreeId) {
        return editorMapper.updateVersionFree(endDate, deliverMoney, versionFreeId)>0?true:false;
    }
    @Override
    public List<AuditFreeState> getAuditFree(int index) {
        List<AuditFreeState> stateInformation=new ArrayList<>();
        List<AuditFreeState> st=editorMapper.getAuditFree();
        AuditFreeState[] array=new AuditFreeState[st.size()];
        int o=0;
        for(AuditFreeState s:st){
            array[o]=s;
            o++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEndDate().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getEndDate().split("-")[0]);
                if(year2>year1){
                    AuditFreeState u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEndDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getEndDate().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getEndDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getEndDate().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        AuditFreeState u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEndDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getEndDate().split("-")[1]);
            int date1=Integer.parseInt(array[i].getEndDate().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getEndDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getEndDate().split("-")[0]);
                int date2=Integer.parseInt(array[j].getEndDate().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            AuditFreeState u=array[j];
                            array[j]=array[i];
                            array[i]=u;
                        }
                    }
                }
            }
        }
        //输出
        if(index==0){
            for(int i=0;i< array.length;i++){
                stateInformation.add(array[i]);
            }
        }else if(index==1){
            for(int i= array.length-1;i>=0;i--){
                stateInformation.add(array[i]);
            }
        }
        return stateInformation;
    }

    @Override
    public boolean updateAuditFree(String endDate, int deliverMoney,int auditFreeId) {
        return editorMapper.updateAuditFree(endDate, deliverMoney, auditFreeId)>0?true:false;
    }

    @Override
    public List<VersionFreeState> getVersion(int index, int userId) {
        List<VersionFreeState> stateInformation=new ArrayList<>();
        List<VersionFreeState> st=editorMapper.getVersion(userId);
        VersionFreeState[] array=new VersionFreeState[st.size()];
        int o=0;
        for(VersionFreeState s:st){
            array[o]=s;
            o++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEndDate().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getEndDate().split("-")[0]);
                if(year2>year1){
                    VersionFreeState u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEndDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getEndDate().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getEndDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getEndDate().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        VersionFreeState u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEndDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getEndDate().split("-")[1]);
            int date1=Integer.parseInt(array[i].getEndDate().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getEndDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getEndDate().split("-")[0]);
                int date2=Integer.parseInt(array[j].getEndDate().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            VersionFreeState u=array[j];
                            array[j]=array[i];
                            array[i]=u;
                        }
                    }
                }
            }
        }
        //输出
        if(index==0){
            for(int i=0;i< array.length;i++){
                stateInformation.add(array[i]);
            }
        }else if(index==1){
            for(int i= array.length-1;i>=0;i--){
                stateInformation.add(array[i]);
            }
        }
        return stateInformation;
    }

    @Override
    public List<AuditFreeState> getCognizance(int index, int userId) {
        List<AuditFreeState> stateInformation=new ArrayList<>();
        List<AuditFreeState> st=editorMapper.getCognizance(userId);
        AuditFreeState[] array=new AuditFreeState[st.size()];
        int o=0;
        for(AuditFreeState s:st){
            array[o]=s;
            o++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEndDate().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getEndDate().split("-")[0]);
                if(year2>year1){
                    AuditFreeState u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEndDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getEndDate().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getEndDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getEndDate().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        AuditFreeState u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getEndDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getEndDate().split("-")[1]);
            int date1=Integer.parseInt(array[i].getEndDate().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getEndDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getEndDate().split("-")[0]);
                int date2=Integer.parseInt(array[j].getEndDate().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            AuditFreeState u=array[j];
                            array[j]=array[i];
                            array[i]=u;
                        }
                    }
                }
            }
        }
        //输出
        if(index==0){
            for(int i=0;i< array.length;i++){
                stateInformation.add(array[i]);
            }
        }else if(index==1){
            for(int i= array.length-1;i>=0;i--){
                stateInformation.add(array[i]);
            }
        }
        return stateInformation;
    }

    @Transactional
    @Override
    public String CognizanceFreeTrue(int accountId,int auditFreeId,int deliverMoney) {
        Balance balance=editorMapper.getBalance(accountId);
        if(balance.getBalanceCount()>=deliverMoney){
            boolean index=false;
            int money=balance.getBalanceCount()-deliverMoney;
            Date date=new Date();
            String edit=String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date);
            index=editorMapper.setBalance(accountId,money)>0?true:false;
            index=editorMapper.setAuditFree(auditFreeId)>0?true:false;
            index=editorMapper.setFreeOrder(accountId,deliverMoney,edit,"缴纳审理费扣除")>0?true:false;
            if(index){
                return "缴费成功";
            }else {
                return "缴费失败";
            }
        }else {
            return "余额不足";
        }
    }

    @Transactional
    @Override
    public String versionFreeTrue(int accountId,int versionFreeId,int deliverMoney) {
        Balance balance=editorMapper.getBalance(accountId);
        if(balance.getBalanceCount()>=deliverMoney){
            boolean index=false;
            int money=balance.getBalanceCount()-deliverMoney;
            Date date=new Date();
            String edit=String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date);
            index=editorMapper.setBalance(accountId,money)>0?true:false;
            index=editorMapper.setVersionFree(versionFreeId)>0?true:false;
            index=editorMapper.setFreeOrder(accountId,deliverMoney,edit,"缴纳版面费扣除")>0?true:false;
            if(index){
                return "缴费成功";
            }else {
                return "缴费失败";
            }
        }else {
            return "余额不足";
        }
    }

    @Override
    public int userBalance(int userId) {
        return editorMapper.userBalance(userId).getBalanceCount();
    }
    @Transactional
    @Override
    public boolean userBalanceAdd(int accountId, int balanceCount) {
        Date date=new Date();
        String edit=String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date);
        boolean index=editorMapper.userBalanceAdd(accountId,balanceCount)>0?true:false;
        if(index){
            index=editorMapper.setFreeOrder(accountId,balanceCount,edit,"充值")>0?true:false;
        }
        return index;
    }

    @Override
    public List<Order> getOrder(int index, int userId) {
        List<Order> stateInformation=new ArrayList<>();
        List<Order> st=editorMapper.getOrder(userId);
        Order[] array=new Order[st.size()];
        int o=0;
        for(Order s:st){
            array[o]=s;
            o++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getOrderDate().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getOrderDate().split("-")[0]);
                if(year2>year1){
                    Order u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getOrderDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getOrderDate().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getOrderDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getOrderDate().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        Order u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getOrderDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getOrderDate().split("-")[1]);
            int date1=Integer.parseInt(array[i].getOrderDate().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getOrderDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getOrderDate().split("-")[0]);
                int date2=Integer.parseInt(array[j].getOrderDate().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            Order u=array[j];
                            array[j]=array[i];
                            array[i]=u;
                        }
                    }
                }
            }
        }
        //输出
        if(index==0){
            for(int i=0;i< array.length;i++){
                stateInformation.add(array[i]);
            }
        }else if(index==1){
            for(int i= array.length-1;i>=0;i--){
                stateInformation.add(array[i]);
            }
        }
        return stateInformation;
    }

    @Override
    public List<DraftFreeState> getDraftFree(int index) {
        List<DraftFreeState> stateInformation=new ArrayList<>();
        List<DraftFreeState> st=editorMapper.getDraftFree();
        DraftFreeState[] array1=new DraftFreeState[st.size()];
        int o=0;
        for(DraftFreeState s:st){
            array1[o]=s;
            o++;
        }
        List<DraftFreeState> stateInformation1=new ArrayList<>();
        List<DraftFreeState> stateInformation2=new ArrayList<>();
        for(int i=0;i< array1.length;i++){
            if(array1[i].getDraftFreeDate()!=null && !array1[i].getDraftFreeDate().equals("")){
                stateInformation1.add(array1[i]);
            }else {
                stateInformation2.add(array1[i]);
            }
        }
        DraftFreeState[] array=new DraftFreeState[stateInformation1.size()];
        DraftFreeState[] array2=new DraftFreeState[stateInformation2.size()];
        int m=0;
        for(DraftFreeState s:stateInformation1){
            array[m]=s;
            m++;
        }
        int n=0;
        for(DraftFreeState s:stateInformation2){
            array2[n]=s;
            n++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getDraftFreeDate().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getDraftFreeDate().split("-")[0]);
                if(year2>year1){
                    DraftFreeState u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getDraftFreeDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getDraftFreeDate().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getDraftFreeDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getDraftFreeDate().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        DraftFreeState u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getDraftFreeDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getDraftFreeDate().split("-")[1]);
            int date1=Integer.parseInt(array[i].getDraftFreeDate().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getDraftFreeDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getDraftFreeDate().split("-")[0]);
                int date2=Integer.parseInt(array[j].getDraftFreeDate().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            DraftFreeState u=array[j];
                            array[j]=array[i];
                            array[i]=u;
                        }
                    }
                }
            }
        }
        //输出
        if(index==0){
            for(int i=0;i< array2.length;i++){
                stateInformation.add(array2[i]);
            }
            for(int i=0;i< array.length;i++){
                stateInformation.add(array[i]);
            }
        }else if(index==1){
            for(int i= array.length-1;i>=0;i--){
                stateInformation.add(array[i]);
            }
            for(int i=0;i< array2.length;i++){
                stateInformation.add(array2[i]);
            }
        }
        return stateInformation;
    }

    @Transactional
    @Override
    public boolean updateDraftFree(int draftFreeMoney, int draftInformationId) {
        Date date=new Date();
        String edit=String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date);
        boolean index= editorMapper.updateDraftFree(draftFreeMoney,draftInformationId,edit)>0?true:false;
        if(index){
            Account account= editorMapper.getAccountId(draftInformationId);
            index=editorMapper.setFreeOrder(account.getAccountId(),draftFreeMoney,edit,"稿费结算")>0?true:false;
            if(index){
                editorMapper.userBalanceAdd(account.getAccountId(),draftFreeMoney);
            }
        }
        return index;
    }

    @Override
    public List<DraftFreeState> getDraftFreeUser(int index, int accountId) {
        List<DraftFreeState> stateInformation=new ArrayList<>();
        List<DraftFreeState> st=editorMapper.getDraftFreeUser(accountId);
        DraftFreeState[] array1=new DraftFreeState[st.size()];
        int o=0;
        for(DraftFreeState s:st){
            array1[o]=s;
            o++;
        }
        List<DraftFreeState> stateInformation1=new ArrayList<>();
        List<DraftFreeState> stateInformation2=new ArrayList<>();
        for(int i=0;i< array1.length;i++){
            if(array1[i].getDraftFreeDate()!=null && !array1[i].getDraftFreeDate().equals("")){
                stateInformation1.add(array1[i]);
            }else {
                stateInformation2.add(array1[i]);
            }
        }
        DraftFreeState[] array=new DraftFreeState[stateInformation1.size()];
        DraftFreeState[] array2=new DraftFreeState[stateInformation2.size()];
        int m=0;
        for(DraftFreeState s:stateInformation1){
            array[m]=s;
            m++;
        }
        int n=0;
        for(DraftFreeState s:stateInformation2){
            array2[n]=s;
            n++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getDraftFreeDate().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getDraftFreeDate().split("-")[0]);
                if(year2>year1){
                    DraftFreeState u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getDraftFreeDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getDraftFreeDate().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getDraftFreeDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getDraftFreeDate().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        DraftFreeState u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getDraftFreeDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getDraftFreeDate().split("-")[1]);
            int date1=Integer.parseInt(array[i].getDraftFreeDate().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getDraftFreeDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getDraftFreeDate().split("-")[0]);
                int date2=Integer.parseInt(array[j].getDraftFreeDate().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            DraftFreeState u=array[j];
                            array[j]=array[i];
                            array[i]=u;
                        }
                    }
                }
            }
        }
        //输出
        if(index==0){
            for(int i=0;i< array2.length;i++){
                stateInformation.add(array2[i]);
            }
            for(int i=0;i< array.length;i++){
                stateInformation.add(array[i]);
            }
        }else if(index==1){
            for(int i= array.length-1;i>=0;i--){
                stateInformation.add(array[i]);
            }
            for(int i=0;i< array2.length;i++){
                stateInformation.add(array2[i]);
            }
        }
        return stateInformation;
    }

    @Override
    public Account getAccount(int accountId) {
        return editorMapper.getAccount(accountId);
    }

    @Override
    public boolean updateAccountPwd(int accountId, String accountPwd) {
        return editorMapper.updateAccountPwd(accountId, accountPwd)>0?true:false;
    }
}
