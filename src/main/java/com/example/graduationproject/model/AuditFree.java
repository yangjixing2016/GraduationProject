package com.example.graduationproject.model;

public class AuditFree {
    private int auditFreeId;
    private int draftInformationId;
    private String draftCTitle;
    private int deliverMoney;
    private String startDate;
    private String endDate;
    private String expendMoney;

    public String getExpendMoney() {
        return expendMoney;
    }

    public void setExpendMoney(String expendMoney) {
        this.expendMoney = expendMoney;
    }

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
}
