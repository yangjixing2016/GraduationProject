package com.example.graduationproject.model.Vo;

public class AuditFreeState {
    private int auditFreeId;
    private int draftInformationId;
    private String draftCTitle;
    private int deliverMoney;
    private String startDate;
    private String endDate;
    private String expendMoney;

    private String draftColumn;

    private int fileId;
    private String fileName;
    private String fileLocation;

    public int getAuditFreeId() {
        return auditFreeId;
    }

    public void setAuditFreeId(int auditFreeId) {
        this.auditFreeId = auditFreeId;
    }

    public int getDraftInformationId() {
        return draftInformationId;
    }

    public void setDraftInformationId(int draftInformationId) {
        this.draftInformationId = draftInformationId;
    }

    public String getDraftCTitle() {
        return draftCTitle;
    }

    public void setDraftCTitle(String draftCTitle) {
        this.draftCTitle = draftCTitle;
    }

    public int getDeliverMoney() {
        return deliverMoney;
    }

    public void setDeliverMoney(int deliverMoney) {
        this.deliverMoney = deliverMoney;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getExpendMoney() {
        return expendMoney;
    }

    public void setExpendMoney(String expendMoney) {
        this.expendMoney = expendMoney;
    }

    public String getDraftColumn() {
        return draftColumn;
    }

    public void setDraftColumn(String draftColumn) {
        this.draftColumn = draftColumn;
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
}
