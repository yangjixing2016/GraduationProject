package com.example.graduationproject.model;

public class State {
    private int stateId;
    private String stateInformation;
    private String advise;
    private int editorBrowse;
    private String editorBrowseDate;
    private int editorAdopt;
    private String editorAdoptDate;
    private int auditBrowse;
    private String auditBrowseDate;
    private int auditAdopt;
    private String auditAdoptDate;
    private int draftInformationId;
    private String auditIds;

    public String getAuditIds() {
        return auditIds;
    }

    public void setAuditIds(String auditIds) {
        this.auditIds = auditIds;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStateInformation() {
        return stateInformation;
    }

    public void setStateInformation(String stateInformation) {
        this.stateInformation = stateInformation;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    public int getEditorBrowse() {
        return editorBrowse;
    }

    public void setEditorBrowse(int editorBrowse) {
        this.editorBrowse = editorBrowse;
    }

    public String getEditorBrowseDate() {
        return editorBrowseDate;
    }

    public void setEditorBrowseDate(String editorBrowseDate) {
        this.editorBrowseDate = editorBrowseDate;
    }

    public int getEditorAdopt() {
        return editorAdopt;
    }

    public void setEditorAdopt(int editorAdopt) {
        this.editorAdopt = editorAdopt;
    }

    public String getEditorAdoptDate() {
        return editorAdoptDate;
    }

    public void setEditorAdoptDate(String editorAdoptDate) {
        this.editorAdoptDate = editorAdoptDate;
    }

    public int getAuditBrowse() {
        return auditBrowse;
    }

    public void setAuditBrowse(int auditBrowse) {
        this.auditBrowse = auditBrowse;
    }

    public String getAuditBrowseDate() {
        return auditBrowseDate;
    }

    public void setAuditBrowseDate(String auditBrowseDate) {
        this.auditBrowseDate = auditBrowseDate;
    }

    public int getAuditAdopt() {
        return auditAdopt;
    }

    public void setAuditAdopt(int auditAdopt) {
        this.auditAdopt = auditAdopt;
    }

    public String getAuditAdoptDate() {
        return auditAdoptDate;
    }

    public void setAuditAdoptDate(String auditAdoptDate) {
        this.auditAdoptDate = auditAdoptDate;
    }

    public int getDraftInformationId() {
        return draftInformationId;
    }

    public void setDraftInformationId(int draftInformationId) {
        this.draftInformationId = draftInformationId;
    }

    @Override
    public String toString() {
        return "State{" +
                "stateId=" + stateId +
                ", stateInformation='" + stateInformation + '\'' +
                ", advise='" + advise + '\'' +
                ", editorBrowse=" + editorBrowse +
                ", editorBrowseDate='" + editorBrowseDate + '\'' +
                ", editorAdopt=" + editorAdopt +
                ", editorAdoptDate='" + editorAdoptDate + '\'' +
                ", auditBrowse=" + auditBrowse +
                ", auditBrowseDate='" + auditBrowseDate + '\'' +
                ", auditAdopt=" + auditAdopt +
                ", auditAdoptDate='" + auditAdoptDate + '\'' +
                ", draftInformationId=" + draftInformationId +
                '}';
    }
}
