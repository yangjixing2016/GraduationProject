package com.example.graduationproject.model;

public class Modify {
    private int modifyId;
    private String modifyWishDate;
    private String modifyAdvise;
    private int stateId;
    private int stateIdNew;

    public int getStateIdNew() {
        return stateIdNew;
    }

    public void setStateIdNew(int stateIdNew) {
        this.stateIdNew = stateIdNew;
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
}
