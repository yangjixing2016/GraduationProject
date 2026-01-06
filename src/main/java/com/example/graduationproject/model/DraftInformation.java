package com.example.graduationproject.model;

import java.util.Date;

public class DraftInformation {

    private String draftCKey;
    private String draftEKey;
    private String draftCProject;
    private String draftEProject;
    private String draftCSummary;
    private String draftESummary;
    private String draftCTitle;
    private String draftETitle;
    private String draftColumn;
    private int draftInformationId;
    private int draftPage;
    private String draftRemark;
    private String draftType;
    private int fileId;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DraftInformation() {
    }

    public String getDraftCKey() {
        return draftCKey;
    }

    public void setDraftCKey(String draftCKey) {
        this.draftCKey = draftCKey;
    }

    public String getDraftEKey() {
        return draftEKey;
    }

    public void setDraftEKey(String draftEKey) {
        this.draftEKey = draftEKey;
    }

    public String getDraftCProject() {
        return draftCProject;
    }

    public void setDraftCProject(String draftCProject) {
        this.draftCProject = draftCProject;
    }

    public String getDraftEProject() {
        return draftEProject;
    }

    public void setDraftEProject(String draftEProject) {
        this.draftEProject = draftEProject;
    }

    public String getDraftCSummary() {
        return draftCSummary;
    }

    public void setDraftCSummary(String draftCSummary) {
        this.draftCSummary = draftCSummary;
    }

    public String getDraftESummary() {
        return draftESummary;
    }

    public void setDraftESummary(String draftESummary) {
        this.draftESummary = draftESummary;
    }

    public String getDraftCTitle() {
        return draftCTitle;
    }

    public void setDraftCTitle(String draftCTitle) {
        this.draftCTitle = draftCTitle;
    }

    public String getDraftETitle() {
        return draftETitle;
    }

    public void setDraftETitle(String draftETitle) {
        this.draftETitle = draftETitle;
    }

    public String getDraftColumn() {
        return draftColumn;
    }

    public void setDraftColumn(String draftColumn) {
        this.draftColumn = draftColumn;
    }

    public int getDraftInformationId() {
        return draftInformationId;
    }

    public void setDraftInformationId(int draftInformationId) {
        this.draftInformationId = draftInformationId;
    }

    public int getDraftPage() {
        return draftPage;
    }

    public void setDraftPage(int draftPage) {
        this.draftPage = draftPage;
    }

    public String getDraftRemark() {
        return draftRemark;
    }

    public void setDraftRemark(String draftRemark) {
        this.draftRemark = draftRemark;
    }

    public String getDraftType() {
        return draftType;
    }

    public void setDraftType(String draftType) {
        this.draftType = draftType;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return "DraftInformation{" +
                "draftCKey='" + draftCKey + '\'' +
                ", draftEKey='" + draftEKey + '\'' +
                ", draftCProject='" + draftCProject + '\'' +
                ", draftEProject='" + draftEProject + '\'' +
                ", draftCSummary='" + draftCSummary + '\'' +
                ", draftESummary='" + draftESummary + '\'' +
                ", draftCTitle='" + draftCTitle + '\'' +
                ", draftETitle='" + draftETitle + '\'' +
                ", draftColumn='" + draftColumn + '\'' +
                ", draftInformationId=" + draftInformationId +
                ", draftPage=" + draftPage +
                ", draftRemark='" + draftRemark + '\'' +
                ", draftType='" + draftType + '\'' +
                ", fileId=" + fileId +
                '}';
    }
}
