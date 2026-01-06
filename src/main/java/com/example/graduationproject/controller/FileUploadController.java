package com.example.graduationproject.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.graduationproject.model.Audit;
import com.example.graduationproject.model.AuthorInformation;
import com.example.graduationproject.model.DraftInformation;
import com.example.graduationproject.model.Vo.ModifyState;
import com.example.graduationproject.model.Vo.StateInformation;
import com.example.graduationproject.model.Vo.StateInformationAuthor;
import com.example.graduationproject.service.IFileUploadService;
import com.example.graduationproject.utils.FileIdentification;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    @Qualifier("FileUploadService")
    private IFileUploadService FileUploadService;

    @RequestMapping("/UploadFile")
    public Object[] UploadFile(MultipartFile file,String uid) throws Exception{
        System.out.println(uid+"uid");
        String fileName = file.getOriginalFilename();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
        String dateNowStr = sdf.format(d);
        String newFileName=dateNowStr+fileName;
        // 获取当前操作系统
        String osName = System.getProperties().get("os.name").toString().toLowerCase(Locale.ROOT);
        String path="";
        if(osName.startsWith("win")){
            path="D:\\graduationProject\\user\\"+uid+"\\";
        }else{
            path="/mnt/test"+"/"+uid;
        }
        Object[] objects=new Object[2];
        if(FileUploadService.addFile(fileName,path+newFileName,Integer.parseInt(uid),0,0)){
            File saveFile=new File(path+newFileName);
            if(!saveFile.getParentFile().exists()){
                saveFile.getParentFile().mkdirs();
            }
            file.transferTo(saveFile);
            objects[0]=FileUploadService.getFileId(fileName,path+newFileName,Integer.parseInt(uid));
            objects[1]=saveFile.getPath();
            }
        return objects;
    }
    @RequestMapping("/RemoveFile")
    @ResponseBody
    public String RemoveFile(String url,Integer fileId){
        String[] locations=FileUploadService.getRemoveFileEtc(fileId);
        boolean index=false;
        if(FileUploadService.RemoveFile(fileId)){
            File file=new File(url);
            index=file.delete();
            System.out.println("执行删除："+url+":"+index);
            for(int i=0;i< locations.length;i++){
                File filet=new File(locations[i]);
                index=filet.delete();
                System.out.println("执行删除："+locations[i]+":"+index);
            }
        }
        if(index){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }
    @RequestMapping("/RemoveFileName")
    @ResponseBody
    public String RemoveFileName(String newFileName,Integer fileId){
        if(FileUploadService.RemoveFileName(newFileName,fileId)){
            return "成功";
        }
        return "失败";
    }
    @RequestMapping("/UploadFileEtc")
    public Object[] UploadFileEtc(MultipartFile file,String uid,Integer beforeFileId) throws Exception{
        System.out.println(uid+":uid   "+beforeFileId+":beforeFileId");
        String fileName = file.getOriginalFilename();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
        String dateNowStr = sdf.format(d);
        String newFileName=dateNowStr+fileName;
        // 获取当前操作系统
        String osName = System.getProperties().get("os.name").toString().toLowerCase(Locale.ROOT);
        String path="";
        if(osName.startsWith("win")){
            path="D:\\graduationProject\\user\\"+uid+"\\";
        }else{
            path="/mnt/test"+"/"+uid;
        }
        Object[] objects=new Object[2];
        if(FileUploadService.addFileEtc(fileName,path+newFileName,Integer.parseInt(uid),1,beforeFileId)){
            File saveFile=new File(path+newFileName);
            if(!saveFile.getParentFile().exists()){
                saveFile.getParentFile().mkdirs();
            }
            file.transferTo(saveFile);
            objects[0]=FileUploadService.getFileId(fileName,path+newFileName,Integer.parseInt(uid));
            objects[1]=saveFile.getPath();
        }
        return objects;
    }
    @RequestMapping("/getFileInformation")
    @ResponseBody
    public Object[] getFileInformation(String url){
        System.out.println("url:"+url);
        File file=new File(url);
        FileIdentification fileIdentification=new FileIdentification();
        try {
            if(FileMagic.valueOf(file)==FileMagic.OLE2){
                //doc格式 2003以前
                System.out.println("doc");
                FileInputStream fis1 = new FileInputStream(file);
                WordExtractor wordExtractor1 = new WordExtractor(fis1);
                String content=wordExtractor1.getText();
                System.out.println(content);
                HashMap<String,String > hashMap=fileIdentification.getAll(content);
                Set<String> keys= hashMap.keySet();
                Object[] objects=new Object[hashMap.size()];
                int index=0;
                for(String key:keys){
                    String[] children=new String[2];
                    children[0]=key;
                    children[1]=hashMap.get(key);
                    objects[index]=children;
                    index++;
                    System.out.println(key+"："+hashMap.get(key));
                }
                return objects;
            } else if (FileMagic.valueOf(file)==FileMagic.OOXML) {
                //docx格式
                System.out.println("docx");
                FileInputStream fileInputStream= new FileInputStream(file);
                XWPFWordExtractor docx = new XWPFWordExtractor(new XWPFDocument(fileInputStream));
                String text = docx.getText();
                System.out.println(text);
                HashMap<String,String > hashMap=fileIdentification.getAll(text);
                Set<String> keys= hashMap.keySet();
                Object[] objects=new Object[hashMap.size()];
                int index=0;
                for(String key:keys){
                    String[] children=new String[2];
                    children[0]=key;
                    children[1]=hashMap.get(key);
                    objects[index]=children;
                    index++;
                    System.out.println(key+"："+hashMap.get(key));
                }
                return objects;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/conserveInformation")
    public String conserve(String draft,String authors){
        System.out.println("访问");
        if(FileUploadService.setDraftInformation(draft,authors)){
            return "投稿成功";
        }
        return "投稿失败";
    }
    @RequestMapping("/selectDraft")
    public List<StateInformation> selectDraft(Integer accountId,Integer index){
        System.out.println("accountId"+accountId);
        List<StateInformation> draftInformation=FileUploadService.selectDraft(accountId,index);
        System.out.println(draftInformation);
        return draftInformation;
    }
    @RequestMapping("/selectAudit")
    public List<Audit> selectAudit(String draftColumn){
        List<Audit> audit=FileUploadService.selectAudit(draftColumn);
        System.out.println(audit);
        return audit;
    }
    @RequestMapping("/getDraftInformation")
    public StateInformationAuthor getDraftInformation(Integer draftInformationId){
        StateInformationAuthor audit=FileUploadService.getDraftInformation(draftInformationId);
        System.out.println(audit);
        return audit;
    }
    @RequestMapping("/getDraftInformationAuthor")
    public List<AuthorInformation> getDraftInformationAuthor(Integer draftInformationId){
        List<AuthorInformation> audit=FileUploadService.getAuthorInformation(draftInformationId);
        System.out.println(audit);
        return audit;
    }
    @RequestMapping("/getAuthor")
    public AuthorInformation getAuthor(Integer authorId){
        AuthorInformation audit=FileUploadService.getAuthor(authorId);
        System.out.println(audit);
        return audit;
    }
    @RequestMapping("/updateAuthor")
    public String updateAuthor(String author,Integer draftInformationId){
        if(FileUploadService.updateAuthor(author,draftInformationId)){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }
    @RequestMapping("/delAuthor")
    public String delAuthor(Integer authorId){
        if(FileUploadService.delAuthor(authorId)){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }
    @RequestMapping("/modify")
    public List<ModifyState> modify(Integer accountId,Integer index){
        return FileUploadService.modify(accountId,index);
    }
    @RequestMapping("/modifyUpdate")
    public String modifyUpdate(String draft,String authors,String auditIds,Integer modifyId){
        System.out.println("访问");
        if(FileUploadService.modifyUpdate(draft,authors,auditIds,modifyId)){
            return "投稿成功";
        }
        return "投稿失败";
    }
    @CrossOrigin
    @RequestMapping("/getFile")
    public void download(String path,Integer stateId, HttpServletResponse response) {
        if(FileUploadService.down(stateId)){
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
    }
    @CrossOrigin
    @RequestMapping("/getFileAudit")
    public void getFileAudit(String path,Integer stateId, HttpServletResponse response) {
        if(FileUploadService.getFileAudit(stateId)){
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
    }

}
