package com.example.graduationproject.service;


import com.example.graduationproject.model.Account;
import com.example.graduationproject.model.Order;
import com.example.graduationproject.model.Vo.*;

import java.util.List;

public interface IEditorService {
    List<EditorBrowse> getDraftBrowse(String draftType,int index);
    boolean adopt(String ado,int stateId);
    boolean adoptRe(int stateId);
    boolean ret(String adviseOne,String adviseTwo,int stateId);
    EditorOriginal original(Integer stateId);
    List<EditorAdopt> getAdoptDraft(int auditBrowse,int index);
    List<EditorBrowse> getReturnDraft(int index);
    List<EditorBrowse> getAuditAdopt(int auditId,String draftType,Integer index);
    boolean auditAdoptOne(int auditId,int stateId,String auditAdoptResult);
    boolean auditAdoptTwo(int auditId,int stateId,String adviseOne,String adviseTwo);
    List<AuditAdoptTrue> getAuditAdoptTrue(int auditId, String draftType, int index);
    List<AuditAdoptTrue> getAuditAdoptFalse(int auditId,String draftType,int index);
    boolean Outcome(int stateId,int draftInformationId,int auditFree,int versionFree,
                    String result,String auditFreeDate,String versionFreeDate,String draftCTitle,
                    String auditAdoptAdvise,String wishDate);
    List<VersionFreeState> getVersionFree(int index);
    boolean updateVersionFree(String endDate,int deliverMoney,int versionFreeId);
    List<AuditFreeState> getAuditFree(int index);
    boolean updateAuditFree(String endDate,int deliverMoney,int auditFreeId);
    List<VersionFreeState> getVersion(int index,int userId);
    List<AuditFreeState> getCognizance(int index,int userId);
    String CognizanceFreeTrue(int accountId,int auditFreeId,int deliverMoney);
    String versionFreeTrue(int accountId,int versionFreeId,int deliverMoney);
    int userBalance(int userId);
    boolean userBalanceAdd(int accountId,int balanceCount);
    List<Order> getOrder(int index, int userId);
    List<DraftFreeState> getDraftFree(int index);
    boolean updateDraftFree(int draftFreeMoney,int draftInformationId);
    List<DraftFreeState> getDraftFreeUser(int index,int accountId);
    Account getAccount(int accountId);
    boolean updateAccountPwd(int accountId,String accountPwd);
}
