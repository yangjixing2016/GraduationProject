package com.example.graduationproject.model;

public class FileAddress {
    private int fileId;
    private String fileName;
    private String fileLocation;
    private int accountId;
    private int fileType;
    private int fileBody;

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getFileBody() {
        return fileBody;
    }

    public void setFileBody(int fileBody) {
        this.fileBody = fileBody;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
