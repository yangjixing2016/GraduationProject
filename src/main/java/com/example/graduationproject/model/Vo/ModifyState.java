package com.example.graduationproject.model.Vo;

public class ModifyState {
    private int modifyId;
    private String modifyWishDate;
    private String modifyAdvise;
    private int stateId;

    private String auditAdoptDate;
    private String auditIds;

    private int fileId;
    private String draftCTitle;
    private int draftInformationId;
    private String draftType;
    private String draftColumn;

    private String fileName;
    private String fileLocation;


    public String getDraftColumn() {
        return draftColumn;
    }

    public void setDraftColumn(String draftColumn) {
        this.draftColumn = draftColumn;
    }

    public String getDraftType() {
        return draftType;
    }

    public void setDraftType(String draftType) {
        this.draftType = draftType;
    }

    public String getAuditIds() {
        return auditIds;
    }

    public void setAuditIds(String auditIds) {
        this.auditIds = auditIds;
    }

    public int getModifyId() {
        return modifyId;
    }

    public void setModifyId(int modifyId) {
        this.modifyId = modifyId;
    }

    public String getModifyWishDate() {
        return modifyWishDate;
    }

    public void setModifyWishDate(String modifyWishDate) {
        this.modifyWishDate = modifyWishDate;
    }

    public String getModifyAdvise() {
        return modifyAdvise;
    }

    public void setModifyAdvise(String modifyAdvise) {
        this.modifyAdvise = modifyAdvise;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }


    public String getAuditAdoptDate() {
        return auditAdoptDate;
    }

    public void setAuditAdoptDate(String auditAdoptDate) {
        this.auditAdoptDate = auditAdoptDate;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getDraftCTitle() {
        return draftCTitle;
    }

    public void setDraftCTitle(String draftCTitle) {
        this.draftCTitle = draftCTitle;
    }

    public int getDraftInformationId() {
        return draftInformationId;
    }

    public void setDraftInformationId(int draftInformationId) {
        this.draftInformationId = draftInformationId;
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
}
