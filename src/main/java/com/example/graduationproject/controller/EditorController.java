package com.example.graduationproject.controller;

import com.example.graduationproject.model.Account;
import com.example.graduationproject.model.Order;
import com.example.graduationproject.model.Vo.*;
import com.example.graduationproject.service.IEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/editor")
public class EditorController {
    @Autowired
    @Qualifier("editorService")
    private IEditorService editorService;

    @RequestMapping("/getDraftBrowse")
    public List<EditorBrowse> getDraftBrowse(String draftType,Integer index){
        return editorService.getDraftBrowse(draftType,index);
    }
    @CrossOrigin
    @RequestMapping("/adopt")
    public String adopt(String ado,Integer stateId){
        if(editorService.adopt(ado,stateId)){
            return "成功";
        }else {
            return "失败";
        }
    }
    @CrossOrigin
    @RequestMapping("/adoptRe")
    public String adoptRe(Integer stateId){
        if(editorService.adoptRe(stateId)){
            return "成功";
        }else {
            return "失败";
        }
    }
    @CrossOrigin
    @RequestMapping("/ret")
    public String ret(String adviseOne,String adviseTwo,Integer stateId){
        if(editorService.ret(adviseOne,adviseTwo,stateId)){
            return "成功";
        }else {
            return "失败";
        }
    }
    @CrossOrigin
    @RequestMapping("/original")
    public EditorOriginal original(Integer stateId){
        return editorService.original(stateId);
    }
    @RequestMapping("/getAdoptDraft")
    public List<EditorAdopt> getAdoptDraft(Integer auditBrowse,Integer index){
        return editorService.getAdoptDraft(auditBrowse,index);
    }
    @RequestMapping("/getReturnDraft")
    public List<EditorBrowse> getReturnDraft(Integer index){
        return editorService.getReturnDraft(index);
    }
    @RequestMapping("/getAuditAdopt")
    public List<EditorBrowse> getAuditAdopt(Integer auditId,String draftType,Integer index){
        return editorService.getAuditAdopt(auditId,draftType,index);
    }
    @CrossOrigin
    @RequestMapping("/auditAdoptOne")
    public String auditAdoptOne(String auditAdoptResult,Integer stateId,Integer auditId){
        if(editorService.auditAdoptOne(auditId,stateId,auditAdoptResult)){
            return "成功";
        }else {
            return "失败";
        }
    }
    @CrossOrigin
    @RequestMapping("/auditAdoptTwo")
    public String auditAdoptTwo(Integer auditId,Integer stateId,String adviseOne,String adviseTwo){
        if(editorService.auditAdoptTwo(auditId,stateId,adviseOne,adviseTwo)){
            return "成功";
        }else {
            return "失败";
        }
    }
    @RequestMapping("/getAuditAdoptTrue")
    public List<AuditAdoptTrue> getAuditAdoptTrue(Integer auditId, String draftType, Integer index){
        return editorService.getAuditAdoptTrue(auditId,draftType,index);
    }
    @RequestMapping("/getAuditAdoptFalse")
    public List<AuditAdoptTrue> getAuditAdoptFalse(Integer auditId,String draftType,Integer index){
        return editorService.getAuditAdoptFalse(auditId,draftType,index);
    }
    @RequestMapping("/Outcome")
    public String Outcome(Integer stateId,Integer draftInformationId,
                          Integer auditFree,Integer versionFree,String result,
                          String auditFreeDate,String versionFreeDate,String draftCTitle,
                          String auditAdoptAdvise,String wishDate){
        if(editorService.Outcome(stateId,draftInformationId,auditFree,versionFree,result,auditFreeDate,versionFreeDate,draftCTitle,auditAdoptAdvise,wishDate)){
            return "成功";
        }
        return "失败";
    }

    @CrossOrigin
    @RequestMapping("/getFile")
    public void download(String path, HttpServletResponse response) {
        System.out.println(path+"--path");
        //读取路径下面的文件
        File picFile = new File(path);
        String filename = picFile.getName();
        //根据路径获取文件
        //获取文件后缀名格式
        String ext = picFile.getName().substring(picFile.getName().indexOf("."));
        //判断图片格式, 设置相应的输出文件格式
        if (ext.equals("jpg")) {
            response.setContentType("image/jpeg");
        } else if (ext.equals("JPG")) {
            response.setContentType("image/jpeg");
        } else if (ext.equals("png")) {
            response.setContentType("image/png");
        } else if (ext.equals("PNG")) {
            response.setContentType("image/png");
        } else {
            response.setContentType("multipart/form-data");
        }
//        response.addHeader("content-disposition", "attachment;filename=" + new String(filename.getBytes()));
        //读取指定路径下面的文件
        try {
            InputStream in = new FileInputStream(picFile);
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            //创建存放文件内容的数组
            byte[] buff = new byte[10*1024];
            //所读取的内容使用n来接收
            int n;
            //当没有读取完时,继续读取,循环
            while ((n = in.read(buff)) != -1) {
                //将字节数组的数据全部写入到输出流中
                outputStream.write(buff, 0, n);
            }
            //强制将缓存区的数据进行输出
            outputStream.flush();
            //关流
            outputStream.close();
            in.close();
        } catch (IOException e) {

        }
    }
    @RequestMapping("/getVersionFree")
    public List<VersionFreeState> getVersionFree(Integer index){
        return editorService.getVersionFree(index);
    }
    @RequestMapping("/updateVersionFree")
    public String updateVersionFree(String endDate,Integer deliverMoney,Integer versionFreeId){
        if(editorService.updateVersionFree(endDate,deliverMoney,versionFreeId)){
            return "成功";
        }
        return "失败";
    }
    @RequestMapping("/getAuditFree")
    public List<AuditFreeState> getAuditFree(Integer index){
        return editorService.getAuditFree(index);
    }
    @RequestMapping("/updateAuditFree")
    public String updateAuditFree(String endDate,Integer deliverMoney,Integer auditFreeId){
        if(editorService.updateAuditFree(endDate,deliverMoney,auditFreeId)){
            return "成功";
        }
        return "失败";
    }
    @RequestMapping("/getVersion")
    public List<VersionFreeState> getVersion(Integer index,Integer userId){
        return editorService.getVersion(index,userId);
    }
    @RequestMapping("/getCognizance")
    public List<AuditFreeState> getCognizance(Integer index,Integer userId){
        return editorService.getCognizance(index,userId);
    }
    @RequestMapping("/CognizanceFreeTrue")
    public String CognizanceFreeTrue(Integer userId,Integer auditFreeId,Integer deliverMoney){
        return editorService.CognizanceFreeTrue(userId,auditFreeId,deliverMoney);
    }
    @RequestMapping("/versionFreeTrue")
    public String versionFreeTrue(Integer userId,Integer versionFreeId,Integer deliverMoney){
        return editorService.versionFreeTrue(userId,versionFreeId,deliverMoney);
    }
    @RequestMapping("/userBalance")
    public int userBalance(Integer userId){
        return editorService.userBalance(userId);
    }
    @RequestMapping("/userBalanceAdd")
    public String userBalanceAdd(Integer userId,Integer balanceCount){
        if(editorService.userBalanceAdd(userId,balanceCount)){
            return "充值成功";
        }else {
            return "充值失败";
        }
    }

    @RequestMapping("/getOrder")
    public List<Order> getOrder(Integer index, Integer userId){
        return editorService.getOrder(index,userId);
    }
    @RequestMapping("/getDraftFree")
    public List<DraftFreeState> getDraftFree(Integer index){
        return editorService.getDraftFree(index);
    }
    @RequestMapping("/updateDraftFree")
    public String updateDraftFree(Integer draftFreeMoney,Integer draftInformationId){
        if(editorService.updateDraftFree(draftFreeMoney,draftInformationId)){
            return "成功";
        }
        return "失败";
    }

    @RequestMapping("/getDraftFreeUser")
    public List<DraftFreeState> getDraftFreeUser(Integer index,Integer accountId){
        return editorService.getDraftFreeUser(index,accountId);
    }
    @RequestMapping("/getAccount")
    public Account getAccount(Integer accountId){
        return editorService.getAccount(accountId);
    }
    @RequestMapping("/updateAccountPwd")
    public String updateAccountPwd(Integer accountId,String accountPwd){
        if(editorService.updateAccountPwd(accountId,accountPwd)){
            return "成功";
        }
        return "失败";
    }
}
