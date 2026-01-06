package com.example.graduationproject.mapper;

import com.example.graduationproject.model.*;
import com.example.graduationproject.model.Vo.ModifyState;
import com.example.graduationproject.model.Vo.StateInformation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface FileMapper {
    int addFile(FileAddress fileAddress);
    FileAddress getFileId(FileAddress fileAddress);
    int RemoveFile(@Param("fileId") int fileId);
    ArrayList<FileAddress> getRemoveFileEtc(@Param("fileId")int fileId);
    int RemoveFileName(@Param("newFileName")String newFileName, @Param("fileId")Integer fileId);
    int insertDraftInformation(DraftInformation df);
    DraftInformation selectDraftInformationId(int fileId);
    int insertAuthorsInformation(AuthorInformation af);
    int insertStateNotAudit(@Param("draftInformationId")int draftInformationId);
    int insertState(@Param("draftInformationId")int draftInformationId,@Param("auditIds") String auditIds);
    List<StateInformation> selectDraft(int accountId);
    List<Audit> selectAudit(String draftColumn);
    StateInformation getDraftInformation(int draftInformationId);
    List<AuthorInformation> getDraftInformationAuthor(int draftInformationId);
    AuthorInformation getAuthor(int authorId);
    int updateAuthor(AuthorInformation authorInformation);
    int updateAuthorEtc(int authorId);
    int delAuthor(int authorId);
    List<ModifyState> modify(int accountId);
    int modifyUpdate(@Param("modifyId")int modifyId,@Param("stateIdNew")int stateIdNew);
    State modifyIdSelect(int draftInformationId);
    int down(@Param("stateId")int stateId,@Param("editorBrowseDate")String editorBrowseDate);
    int getFileAudit(@Param("stateId")int stateId,@Param("auditBrowseDate")String auditBrowseDate);
}
