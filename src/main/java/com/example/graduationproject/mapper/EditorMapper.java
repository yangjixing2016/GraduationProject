package com.example.graduationproject.mapper;

import com.example.graduationproject.model.*;
import com.example.graduationproject.model.Vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EditorMapper {
    List<EditorBrowse> getDraftBrowse();
    int adopt(@Param("stateId")int stateId, @Param("editorAdoptDate")String editorAdoptDate,@Param("auditIds")String auditIds);
    int adoptRe(@Param("stateId")int stateId, @Param("editorAdoptDate")String editorAdoptDate);
    int ret(@Param("stateId")int stateId, @Param("editorAdoptDate")String editorAdoptDate,@Param("advise")String advise);
    Modify selectStateIdNew(int stateIdNew);
    EditorOriginal original(int stateId);
    List<EditorAdopt> getAdoptDraft();
    List<AuditAdoptAudit> selectAuditAdoptAudit(@Param("auditId")int auditId);
    List<EditorBrowse> getReturnDraft();
    Audit selectAudit(int auditId);
    List<EditorBrowse> getAuditAdopt();
    List<AuditAdopt> AuditAdopt(int auditId);
    int auditAdoptOne(@Param("auditId")int auditId, @Param("stateId")int stateId, @Param("auditAdoptResult")String auditAdoptResult,@Param("auditAdoptDateT")String auditAdoptDateT);
    int auditAdoptTwo(@Param("auditId")int auditId, @Param("stateId")int stateId, @Param("auditAdoptAdvise")String auditAdoptAdvise,@Param("auditAdoptDateT")String auditAdoptDateT);
    int updateAuditOneCount(int auditId);
    int updateAuditTwoCount(int auditId);
    List<AuditAdoptTrue> getAuditAdoptTrue();
    List<AuditAdoptTrue> getAuditAdoptFalse();
    int updateEditorTwo(@Param("stateId")int stateId,@Param("auditAdoptDate")String auditAdoptDate);
    int insertAuditAdoptTwo(@Param("stateId")int stateId,@Param("modifyAdvise")String modifyAdvise,@Param("wishDate")String wishDate);
    int insertAuditFree(@Param("draftInformationId")int draftInformationId,@Param("draftCTitle")String draftCTitle,
                        @Param("auditFree")int auditFree,@Param("auditFreeDate")String auditFreeDate,
                        @Param("startDate")String startDate);
    int updateEditorOne(@Param("stateId")int stateId,@Param("auditAdoptDate")String auditAdoptDate);
    int insertVersionFree(@Param("draftInformationId")int draftInformationId,@Param("draftCTitle")String draftCTitle,
                        @Param("auditFree")int auditFree,@Param("auditFreeDate")String auditFreeDate,
                        @Param("startDate")String startDate);
    List<VersionFreeState> getVersionFree();
    int updateVersionFree(@Param("endDate")String endDate,@Param("deliverMoney")int deliverMoney,@Param("versionFreeId")int versionFreeId);
    List<AuditFreeState> getAuditFree();
    int updateAuditFree(@Param("endDate")String endDate,@Param("deliverMoney")int deliverMoney,@Param("auditFreeId")int auditFreeId);
    List<VersionFreeState> getVersion(int userId);
    List<AuditFreeState> getCognizance(int userId);
    Balance getBalance(int accountId);
    int setBalance(@Param("accountId")int accountId,@Param("balanceCount")int balanceCount);
    int setAuditFree(int auditFreeId);
    int setVersionFree(int versionFreeId);
    int setFreeOrder(@Param("accountId")int accountId,@Param("orderMoney")int orderMoney,@Param("orderDate")String orderDate,@Param("orderType")String orderType);
    Balance userBalance(int accountId);
    int userBalanceAdd(@Param("accountId")int accountId, @Param("balanceCount")int balanceCount);
    List<Order> getOrder(@Param("accountId")int accountId);
    List<DraftFreeState> getDraftFree();
    int updateDraftFree(@Param("draftFreeMoney")int draftFreeMoney,@Param("draftInformationId")int draftInformationId,@Param("draftFreeDate")String draftFreeDate);
    Account getAccountId(int draftInformationId);
    List<DraftFreeState> getDraftFreeUser(int accountId);
    Account getAccount(int accountId);
    int updateAccountPwd(@Param("accountId")int accountId,@Param("accountPwd")String accountPwd);
}
