package com.example.graduationproject.mapper;

import com.example.graduationproject.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> findAll();
    List<Menu> findMenuWriter();
    List<Menu> findMenuReader();
    List<EditorMenu> findMenuEditor();
    List<AuditMenu> findMenuAudit();
    AuditAccount findAuditAccount(@Param("name") String name, @Param("pwd")String pwd);
    Account getAccount(String accountName);
    int rgin(@Param("accountName")String accountName,@Param("accountPwd")String accountPwd);
    Account getAccountPwd(@Param("accountName")String accountName,@Param("accountPwd")String accountPwd);
}
