package com.example.graduationproject.model;

public class AuditAdopt {
    private int auditAdoptId;
    private int auditId;
    private int stateId;
    private String auditAdoptResult;
    private String auditAdoptAdvise;
    private String auditAdoptDateT;

    public String getAuditAdoptDateT() {
        return auditAdoptDateT;
    }

    public void setAuditAdoptDateT(String auditAdoptDateT) {
        this.auditAdoptDateT = auditAdoptDateT;
    }

    public String getAuditAdoptAdvise() {
        return auditAdoptAdvise;
    }

    public void setAuditAdoptAdvise(String auditAdoptAdvise) {
        this.auditAdoptAdvise = auditAdoptAdvise;
    }

    public int getAuditAdoptId() {
        return auditAdoptId;
    }

    public void setAuditAdoptId(int auditAdoptId) {
        this.auditAdoptId = auditAdoptId;
    }

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getAuditAdoptResult() {
        return auditAdoptResult;
    }

    public void setAuditAdoptResult(String auditAdoptResult) {
        this.auditAdoptResult = auditAdoptResult;
    }
}
