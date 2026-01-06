package com.example.graduationproject.service;


import com.example.graduationproject.model.Audit;
import com.example.graduationproject.model.AuthorInformation;
import com.example.graduationproject.model.DraftInformation;
import com.example.graduationproject.model.Vo.ModifyState;
import com.example.graduationproject.model.Vo.StateInformation;
import com.example.graduationproject.model.Vo.StateInformationAuthor;

import java.util.List;

public interface IFileUploadService {
    //上传主文件
    boolean addFile(String fileName,String fileLocation,int accountId,int fileType,int fileBody);
    //获取上传文件的文件id
    int getFileId(String fileName,String fileLocation,int accountId);
    //删除上传文件
    boolean RemoveFile(int fileId);
    //得到要删除文件的附件
    String[] getRemoveFileEtc(int fileId);
    //文件重命名
    boolean RemoveFileName(String newFileName, Integer fileId);
    //上传附件
    boolean addFileEtc(String fileName,String fileLocation,int accountId,int fileType,int fileBody);
    //储存投稿信息
    boolean setDraftInformation(String draft, String authors);
    //查询稿件信息
    List<StateInformation> selectDraft(int accountId,int index);
    //获取评审专家集合
    List<Audit> selectAudit(String draftColumn);
    //获取稿件及其对应作者信息
    StateInformationAuthor getDraftInformation(int draftInformationId);
    //获取作者信息
    List<AuthorInformation> getAuthorInformation(int draftInformationId);
    //获取单个作者信息
    AuthorInformation getAuthor(int authorId);
    //修改作者信息
    boolean updateAuthor(String author,int draftInformationId);
    //删除作者信息
    boolean delAuthor(int authorId);
    //得到待修改稿件
    List<ModifyState> modify(int accountId,int index);
    //储存重投稿信息
    boolean modifyUpdate(String draft, String authors,String auditIds,int modifyId);
    //编辑部浏览确认
    boolean down(int stateId);
    //专家浏览确认
    boolean getFileAudit(int stateId);
}
