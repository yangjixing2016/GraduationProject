package com.example.graduationproject.service.impl;

import com.example.graduationproject.mapper.UserMapper;
import com.example.graduationproject.model.*;
import com.example.graduationproject.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("menuService")
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Menu> findMenuWriter() {
        List<Menu> list=userMapper.findMenuWriter();
        List<Menu> menu=new ArrayList<>();
        List<Menu> menuChildren=new ArrayList<>();
        for(Menu m : list){
            if(m.getSignal()==0){
                menu.add(m);
            }else {
                menuChildren.add(m);
            }
        }
        for(Menu m : menu){
            List<Menu> menus=new ArrayList<>();
            for(Menu n : menuChildren){
                if(m.getMenuId()==n.getSignal()){
                    menus.add(n);
                }
            }
            m.setChildren(menus);
        }
        return menu;
    }
    @Override
    public List<Menu> findMenuReader() {
        List<Menu> list=userMapper.findMenuReader();
        List<Menu> menu=new ArrayList<>();
        List<Menu> menuChildren=new ArrayList<>();
        for(Menu m : list){
            if(m.getSignal()==0){
                menu.add(m);
            }else {
                menuChildren.add(m);
            }
        }
        for(Menu m : menu){
            List<Menu> menus=new ArrayList<>();
            for(Menu n : menuChildren){
                if(m.getMenuId()==n.getSignal()){
                    menus.add(n);
                }
            }
            m.setChildren(menus);
        }
        return menu;
    }

    @Override
    public List<EditorMenu> findMenuEditor() {
        List<EditorMenu> list=userMapper.findMenuEditor();
        List<EditorMenu> menu=new ArrayList<>();
        List<EditorMenu> menuChildren=new ArrayList<>();
        for(EditorMenu m : list){
            if(m.getSignal()==0){
                menu.add(m);
            }else {
                menuChildren.add(m);
            }
        }
        for(EditorMenu m : menu){
            List<EditorMenu> menus=new ArrayList<>();
            for(EditorMenu n : menuChildren){
                if(m.getEditorMenuId()==n.getSignal()){
                    menus.add(n);
                }
            }
            m.setChildren(menus);
        }
        return menu;
    }

    @Override
    public List<AuditMenu> findMenuAudit() {
        List<AuditMenu> list=userMapper.findMenuAudit();
        List<AuditMenu> menu=new ArrayList<>();
        List<AuditMenu> menuChildren=new ArrayList<>();
        for(AuditMenu m : list){
            if(m.getSignal()==0){
                menu.add(m);
            }else {
                menuChildren.add(m);
            }
        }
        for(AuditMenu m : menu){
            List<AuditMenu> menus=new ArrayList<>();
            for(AuditMenu n : menuChildren){
                if(m.getAuditMenuId()==n.getSignal()){
                    menus.add(n);
                }
            }
            m.setChildren(menus);
        }
        return menu;
    }

    @Override
    public AuditAccount findAuditAccount(String name, String pwd) {
        return userMapper.findAuditAccount(name,pwd);
    }

    @Override
    public Account getAccount(String accountName) {
        return userMapper.getAccount(accountName);
    }

    @Override
    public boolean rgin(String accountName, String accountPwd) {
        return userMapper.rgin(accountName,accountPwd)>0?true:false;
    }

    @Override
    public Account getAccountPwd(String accountName, String accountPwd) {
        return userMapper.getAccountPwd(accountName, accountPwd);
    }
}
