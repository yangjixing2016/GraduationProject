package com.example.graduationproject.controller;

import com.example.graduationproject.model.*;
import com.example.graduationproject.service.IMenuService;
import com.example.graduationproject.utils.JWTUtils;
import com.example.graduationproject.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    @Qualifier("menuService")
    private IMenuService menuService;

    //登录
    @PostMapping( "/login/status")
    @CrossOrigin
    @ResponseBody
    public JsonResult<String> login(String username,String password){
        String name= username;
        String pwd= password;
        JsonResult<String> str=new JsonResult<>();
        Account account=menuService.getAccountPwd(username,password);
            if(account!=null){
                if(account.getAccountId()==2){
                    Map<String,String> map=new HashMap<>();
                    map.put(name,pwd);
                    str.setData(JWTUtils.getToken(map));
                    str.setMessage("编辑部登录成功");
                    str.setState(2);
                }else {
                    Map<String,String> map=new HashMap<>();
                    map.put(name,pwd);
                    str.setData(JWTUtils.getToken(map));
                    str.setMessage("用户登录成功");
                    str.setState(account.getAccountId());
                }
            }else {
                AuditAccount auditAccount= menuService.findAuditAccount(username, password);
                System.out.println(auditAccount);
                if(auditAccount!=null){
                    Map<String,String> map=new HashMap<>();
                    map.put(name,pwd);
                    str.setData(JWTUtils.getToken(map));
                    str.setMessage("专家登录成功");
                    str.setState(auditAccount.getAuditId());
                }else {
                    str.setMessage("用户名或者密码输入错误");
                }
            }
        return str;
    }
    //查询所有作者菜单
    @GetMapping("/findMenuWriter")
    @CrossOrigin
    public List<Menu> findMenuWriter(){
        System.out.println("1111");
        return menuService.findMenuWriter();
    }
    //查询所有读者菜单
    @GetMapping("/findMenuReader")
    @CrossOrigin
    public List<Menu> findMenuReader(){
        return menuService.findMenuReader();
    }


    //查询编辑部菜单
    @GetMapping("/findMenuEditor")
    @CrossOrigin
    public List<EditorMenu> findMenuEditor(){
        System.out.println("2111");
        return menuService.findMenuEditor();
    }

    //查询专家部菜单
    @GetMapping("/findMenuAudit")
    @CrossOrigin
    public List<AuditMenu> findMenuAudit(){
        return menuService.findMenuAudit();
    }

    @RequestMapping("/rgin")
    public String rgin(String accountName,String accountPwd){
        Account account=menuService.getAccount(accountName);
        if(account!=null){
            return "用户名已存在";
        }else {
            if(menuService.rgin(accountName,accountPwd)){
                return "注册成功";
            }else {
                return "注册失败";
            }
        }
    }

}
