package com.example.graduationproject.model.Vo;

import com.example.graduationproject.model.AuthorInformation;

import java.util.List;

public class StateInformationAuthor {
    private StateInformation stateInformation;

    private List<AuthorInformation> authorInformationList;

    public StateInformation getStateInformation() {
        return stateInformation;
    }

    public void setStateInformation(StateInformation stateInformation) {
        this.stateInformation = stateInformation;
    }

    public List<AuthorInformation> getAuthorInformationList() {
        return authorInformationList;
    }

    public void setAuthorInformationList(List<AuthorInformation> authorInformationList) {
        this.authorInformationList = authorInformationList;
    }
}
