package com.example.graduationproject.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.graduationproject.mapper.FileMapper;
import com.example.graduationproject.model.Audit;
import com.example.graduationproject.model.AuthorInformation;
import com.example.graduationproject.model.DraftInformation;
import com.example.graduationproject.model.FileAddress;
import com.example.graduationproject.model.Vo.ModifyState;
import com.example.graduationproject.model.Vo.StateInformation;
import com.example.graduationproject.model.Vo.StateInformationAuthor;
import com.example.graduationproject.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("FileUploadService")
public class FileUploadServiceImpl implements IFileUploadService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public boolean addFile(String fileName,String fileLocation,int accountId,int fileType,int fileBody) {
        FileAddress fileAddress=new FileAddress();
        fileAddress.setFileName(fileName);
        fileAddress.setFileLocation(fileLocation);
        fileAddress.setAccountId(accountId);
        fileAddress.setFileType(fileType);
        fileAddress.setFileBody(fileBody);
        boolean index=fileMapper.addFile(fileAddress)>0?true:false;
        return index;
    }
    @Override
    public int getFileId(String fileName, String fileLocation, int accountId) {
        FileAddress fileAddress=new FileAddress();
        fileAddress.setFileName(fileName);
        fileAddress.setFileLocation(fileLocation);
        fileAddress.setAccountId(accountId);
        return fileMapper.getFileId(fileAddress).getFileId();
    }
    @Override
    public boolean RemoveFile(int fileId) {
        return fileMapper.RemoveFile(fileId)>0?true:false;
    }

    @Override
    public String[] getRemoveFileEtc(int fileId) {
        ArrayList<FileAddress> fileAddresses= fileMapper.getRemoveFileEtc(fileId);
        String[] fileLocations=new String[fileAddresses.size()];
        int i=0;
        for(FileAddress f:fileAddresses){
            fileLocations[i]=f.getFileLocation();
            i++;
        }
        return fileLocations;
    }

    @Override
    public boolean RemoveFileName(String newFileName, Integer fileId) {
        return fileMapper.RemoveFileName(newFileName,fileId)>0?true:false;
    }
    @Override
    public boolean addFileEtc(String fileName,String fileLocation,int accountId,int fileType,int fileBody) {
        FileAddress fileAddress=new FileAddress();
        fileAddress.setFileName(fileName);
        fileAddress.setFileLocation(fileLocation);
        fileAddress.setAccountId(accountId);
        fileAddress.setFileType(fileType);
        fileAddress.setFileBody(fileBody);
        boolean index=fileMapper.addFile(fileAddress)>0?true:false;
        return index;
    }
    @Transactional
    public boolean setDraftInformation(String draft, String authors){
        //处理信息对象
        JSONObject jsonObject = JSONObject.parseObject(draft);
        DraftInformation draftInformation=new DraftInformation();
        draftInformation.setDraftCTitle(jsonObject.getString("draftCTitle"));
        draftInformation.setDraftETitle(jsonObject.getString("draftETitle"));
        draftInformation.setDraftCKey(jsonObject.getString("draftCKey"));
        draftInformation.setDraftEKey(jsonObject.getString("draftEKey"));
        draftInformation.setDraftCSummary(jsonObject.getString("draftCSummary"));
        draftInformation.setDraftESummary(jsonObject.getString("draftESummary"));
        draftInformation.setDraftCProject(jsonObject.getString("draftCProject"));
        draftInformation.setDraftEProject(jsonObject.getString("draftEProject"));
        draftInformation.setDraftColumn(jsonObject.getString("draftColumn"));
        draftInformation.setDraftPage(jsonObject.getInteger("draftPage"));
        draftInformation.setDraftRemark(jsonObject.getString("draftRemark"));
        draftInformation.setDraftType(jsonObject.getString("draftType"));
        draftInformation.setFileId(jsonObject.getInteger("fileId"));
        Date date=new Date();
        draftInformation.setDate(String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date));
        //处理作者信息集合
        List<AuthorInformation> parse = JSON.parseArray(authors,AuthorInformation.class);
        for(AuthorInformation p:parse){
            System.out.println("parse:"+p);
        }
        boolean index=fileMapper.insertDraftInformation(draftInformation)>0?true:false;
        if(index){
            DraftInformation  dif=fileMapper.selectDraftInformationId(draftInformation.getFileId());
            System.out.println("输出1："+dif);
            int draftInformationId=dif.getDraftInformationId();
            System.out.println("id:"+draftInformationId);
            boolean sign=false;
            for(AuthorInformation au:parse){
                AuthorInformation t=au;
                t.setDraftInformationId(draftInformationId);
                sign=fileMapper.insertAuthorsInformation(t)>0?true:false;
                System.out.println("sign:"+sign);
            }
            System.out.println("sign-e");
            if(sign){
                boolean stateIndex=fileMapper.insertStateNotAudit(draftInformationId)>0?true:false;
                return stateIndex;
            }
        }
        return false;
    }
    @Override
    public List<StateInformation>selectDraft(int accountId,int index) {
        List<StateInformation> stateInformation=new ArrayList<>();
        List<StateInformation> st=fileMapper.selectDraft(accountId);
        StateInformation[] array=new StateInformation[st.size()];
        int o=0;
        for(StateInformation s:st){
            array[o]=s;
            o++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getDate().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getDate().split("-")[0]);
                if(year2>year1){
                    StateInformation u=array[j];
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
                        StateInformation u=array[j];
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
                            StateInformation u=array[j];
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
    public List<Audit> selectAudit(String draftColumn) {
        return fileMapper.selectAudit(draftColumn);
    }
    @Transactional
    @Override
    public StateInformationAuthor getDraftInformation(int draftInformationId) {
        StateInformation st= fileMapper.getDraftInformation(draftInformationId);
        List<AuthorInformation> authors=fileMapper.getDraftInformationAuthor(draftInformationId);
        StateInformationAuthor stateInformationAuthor=new StateInformationAuthor();
        stateInformationAuthor.setStateInformation(st);
        stateInformationAuthor.setAuthorInformationList(authors);
        return stateInformationAuthor;
    }

    @Override
    public List<AuthorInformation> getAuthorInformation(int draftInformationId) {
        return fileMapper.getDraftInformationAuthor(draftInformationId);
    }

    @Override
    public AuthorInformation getAuthor(int authorId) {
        return fileMapper.getAuthor(authorId);
    }
    @Transactional
    @Override
    public boolean updateAuthor(String author,int draftInformationId) {
        List<AuthorInformation> getDraftInformationAuthor= fileMapper.getDraftInformationAuthor(draftInformationId);
        JSONObject jsonObject = JSONObject.parseObject(author);
        AuthorInformation authorInformation=new AuthorInformation();
        authorInformation.setAuthor(jsonObject.getString("author"));
        authorInformation.setAuthorId(jsonObject.getInteger("authorId"));
        authorInformation.setAuthorName(jsonObject.getString("authorName"));
        authorInformation.setAuthorSex(jsonObject.getString("authorSex"));
        authorInformation.setAuthorBirth(jsonObject.getString("authorBirth"));
        authorInformation.setAuthorSchool(jsonObject.getString("authorSchool"));
        authorInformation.setAuthorAddress(jsonObject.getString("authorAddress"));
        authorInformation.setAuthorNumber(jsonObject.getInteger("authorNumber"));
        authorInformation.setAuthorCounty(jsonObject.getString("authorCounty"));
        authorInformation.setAuthorProvince(jsonObject.getString("authorProvince"));
        authorInformation.setAuthorProfession(jsonObject.getString("authorProfession"));
        authorInformation.setAuthorEducation(jsonObject.getString("authorEducation"));
        authorInformation.setAuthorPhone(jsonObject.getString("authorPhone"));
        authorInformation.setAuthorEmail(jsonObject.getString("authorEmail"));
        authorInformation.setAuthorIntroduce(jsonObject.getString("authorIntroduce"));
        authorInformation.setRadio(jsonObject.getInteger("radio"));
        boolean index=false;
        if(jsonObject.getInteger("radio")==1){
            for(AuthorInformation a:getDraftInformationAuthor){
                if(a.getAuthorId()==jsonObject.getInteger("authorId")){
                    index=fileMapper.updateAuthor(authorInformation)>0?true:false;
                }else {
                    index=fileMapper.updateAuthorEtc(a.getAuthorId())>0?true:false;
                }
            }
        }else {
            index=fileMapper.updateAuthor(authorInformation)>0?true:false;
        }
        if(index){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public boolean delAuthor(int authorId) {
        return fileMapper.delAuthor(authorId)>0?true:false;
    }

    @Override
    public List<ModifyState> modify(int accountId,int index) {
        List<ModifyState> stateInformation=new ArrayList<>();
        List<ModifyState> st=fileMapper.modify(accountId);
        ModifyState[] array=new ModifyState[st.size()];
        int o=0;
        for(ModifyState s:st){
            array[o]=s;
            o++;
        }
        //排序年份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getAuditAdoptDate().split("-")[0]);
            for(int j=i+1;j< array.length;j++){
                int year2=Integer.parseInt(array[j].getAuditAdoptDate().split("-")[0]);
                if(year2>year1){
                    ModifyState u=array[j];
                    array[j]=array[i];
                    array[i]=u;
                }
            }
        }
        //排序月份
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getAuditAdoptDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getAuditAdoptDate().split("-")[1]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getAuditAdoptDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getAuditAdoptDate().split("-")[0]);
                if(month2>month1){
                    if(year2>=year1){
                        ModifyState u=array[j];
                        array[j]=array[i];
                        array[i]=u;
                    }
                }
            }
        }
        //排序日期
        for(int i=0;i< array.length-1;i++){
            int year1=Integer.parseInt(array[i].getAuditAdoptDate().split("-")[0]);
            int month1=Integer.parseInt(array[i].getAuditAdoptDate().split("-")[1]);
            int date1=Integer.parseInt(array[i].getAuditAdoptDate().split("-")[2]);
            for(int j=i+1;j< array.length;j++){
                int month2=Integer.parseInt(array[j].getAuditAdoptDate().split("-")[1]);
                int year2=Integer.parseInt(array[j].getAuditAdoptDate().split("-")[0]);
                int date2=Integer.parseInt(array[j].getAuditAdoptDate().split("-")[2]);
                if(date2>date1){
                    if(month2>=month1){
                        if(year2>=year1){
                            ModifyState u=array[j];
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
    public boolean modifyUpdate(String draft, String authors, String auditIds, int modifyId) {
        //处理信息对象
        JSONObject jsonObject = JSONObject.parseObject(draft);
        DraftInformation draftInformation=new DraftInformation();
        draftInformation.setDraftCTitle(jsonObject.getString("draftCTitle"));
        draftInformation.setDraftETitle(jsonObject.getString("draftETitle"));
        draftInformation.setDraftCKey(jsonObject.getString("draftCKey"));
        draftInformation.setDraftEKey(jsonObject.getString("draftEKey"));
        draftInformation.setDraftCSummary(jsonObject.getString("draftCSummary"));
        draftInformation.setDraftESummary(jsonObject.getString("draftESummary"));
        draftInformation.setDraftCProject(jsonObject.getString("draftCProject"));
        draftInformation.setDraftEProject(jsonObject.getString("draftEProject"));
        draftInformation.setDraftColumn(jsonObject.getString("draftColumn"));
        draftInformation.setDraftPage(jsonObject.getInteger("draftPage"));
        draftInformation.setDraftRemark(jsonObject.getString("draftRemark"));
        draftInformation.setDraftType(jsonObject.getString("draftType"));
        draftInformation.setFileId(jsonObject.getInteger("fileId"));
        Date date=new Date();
        draftInformation.setDate(String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date));
        //处理作者信息集合
        List<AuthorInformation> parse = JSON.parseArray(authors,AuthorInformation.class);
        for(AuthorInformation p:parse){
            System.out.println("parse:"+p);
        }
        boolean index=fileMapper.insertDraftInformation(draftInformation)>0?true:false;
        if(index){
            DraftInformation  dif=fileMapper.selectDraftInformationId(draftInformation.getFileId());
            System.out.println("输出1："+dif);
            int draftInformationId=dif.getDraftInformationId();
            System.out.println("id:"+draftInformationId);
            boolean sign=false;
            for(AuthorInformation au:parse){
                AuthorInformation t=au;
                t.setDraftInformationId(draftInformationId);
                sign=fileMapper.insertAuthorsInformation(t)>0?true:false;
                System.out.println("sign:"+sign);
            }
            System.out.println("sign-e");
            if(sign){
                boolean t=false;
                t=fileMapper.insertState(draftInformationId,auditIds)>0?true:false;
                int stateId=fileMapper.modifyIdSelect(draftInformationId).getStateId();
                t=fileMapper.modifyUpdate(modifyId,stateId)>0?true:false;
                return t;
            }
        }
        return false;
    }

    @Override
    public boolean down(int stateId) {
        Date date=new Date();
        String edit=String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date);
        return fileMapper.down(stateId,edit)>0?true:false;
    }

    @Override
    public boolean getFileAudit(int stateId) {
        Date date=new Date();
        String edit=String.format("%tY", date)+"-"+String.format("%tm", date)+"-"+String.format("%td", date);
        return fileMapper.getFileAudit(stateId,edit)>0?true:false;
    }
}
