package com.example.graduationproject.model;

public class AuditAccount {
    private int auditAccountId;
    private String auditAccountName;
    private String auditAccountPwd;
    private int auditId;

    public int getAuditAccountId() {
        return auditAccountId;
    }

    public void setAuditAccountId(int auditAccountId) {
        this.auditAccountId = auditAccountId;
    }

    public String getAuditAccountName() {
        return auditAccountName;
    }

    public void setAuditAccountName(String auditAccountName) {
        this.auditAccountName = auditAccountName;
    }

    public String getAuditAccountPwd() {
        return auditAccountPwd;
    }

    public void setAuditAccountPwd(String auditAccountPwd) {
        this.auditAccountPwd = auditAccountPwd;
    }

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    @Override
    public String toString() {
        return "AuditAccount{" +
                "auditAccountId=" + auditAccountId +
                ", auditAccountName='" + auditAccountName + '\'' +
                ", auditAccountPwd='" + auditAccountPwd + '\'' +
                ", auditId=" + auditId +
                '}';
    }
}
