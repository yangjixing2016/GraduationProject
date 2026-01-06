package com.example.graduationproject.service;

import com.example.graduationproject.model.*;

import java.util.List;

public interface IMenuService {
    List<Menu> findMenuWriter();
    List<Menu> findMenuReader();
    List<EditorMenu> findMenuEditor();
    List<AuditMenu> findMenuAudit();
    AuditAccount findAuditAccount(String name,String pwd);
    Account getAccount(String accountName);
    boolean rgin(String accountName,String accountPwd);
    Account getAccountPwd(String accountName,String accountPwd);
}
