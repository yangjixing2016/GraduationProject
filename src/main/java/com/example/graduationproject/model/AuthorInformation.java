package com.example.graduationproject.model;

public class AuthorInformation {
    private int authorId;
    private String authorName;
    private String authorSex;
    private String authorBirth;
    private String authorSchool;
    private String authorAddress;
    private int authorNumber;
    private String authorCounty;
    private String authorProvince;
    private String authorProfession;
    private String authorEducation;
    private String authorPhone;
    private String authorEmail;
    private String authorIntroduce;
    private int draftInformationId;
    private String author;
    private int radio;

    public AuthorInformation() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSex() {
        return authorSex;
    }

    public void setAuthorSex(String authorSex) {
        this.authorSex = authorSex;
    }

    public String getAuthorBirth() {
        return authorBirth;
    }

    public void setAuthorBirth(String authorBirth) {
        this.authorBirth = authorBirth;
    }

    public String getAuthorSchool() {
        return authorSchool;
    }

    public void setAuthorSchool(String authorSchool) {
        this.authorSchool = authorSchool;
    }

    public String getAuthorAddress() {
        return authorAddress;
    }

    public void setAuthorAddress(String authorAddress) {
        this.authorAddress = authorAddress;
    }

    public int getAuthorNumber() {
        return authorNumber;
    }

    public void setAuthorNumber(int authorNumber) {
        this.authorNumber = authorNumber;
    }

    public String getAuthorCounty() {
        return authorCounty;
    }

    public void setAuthorCounty(String authorCounty) {
        this.authorCounty = authorCounty;
    }

    public String getAuthorProvince() {
        return authorProvince;
    }

    public void setAuthorProvince(String authorProvince) {
        this.authorProvince = authorProvince;
    }

    public String getAuthorProfession() {
        return authorProfession;
    }

    public void setAuthorProfession(String authorProfession) {
        this.authorProfession = authorProfession;
    }

    public String getAuthorEducation() {
        return authorEducation;
    }

    public void setAuthorEducation(String authorEducation) {
        this.authorEducation = authorEducation;
    }

    public String getAuthorPhone() {
        return authorPhone;
    }

    public void setAuthorPhone(String authorPhone) {
        this.authorPhone = authorPhone;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorIntroduce() {
        return authorIntroduce;
    }

    public void setAuthorIntroduce(String authorIntroduce) {
        this.authorIntroduce = authorIntroduce;
    }

    public int getDraftInformationId() {
        return draftInformationId;
    }

    public void setDraftInformationId(int draftInformationId) {
        this.draftInformationId = draftInformationId;
    }

    public AuthorInformation(int authorId, String authorName, String authorSex, String authorBirth, String authorSchool, String authorAddress, int authorNumber, String authorCounty, String authorProvince, String authorProfession, String authorEducation, String authorPhone, String authorEmail, String authorIntroduce, int draftInformationId, String author, int radio) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorSex = authorSex;
        this.authorBirth = authorBirth;
        this.authorSchool = authorSchool;
        this.authorAddress = authorAddress;
        this.authorNumber = authorNumber;
        this.authorCounty = authorCounty;
        this.authorProvince = authorProvince;
        this.authorProfession = authorProfession;
        this.authorEducation = authorEducation;
        this.authorPhone = authorPhone;
        this.authorEmail = authorEmail;
        this.authorIntroduce = authorIntroduce;
        this.draftInformationId = draftInformationId;
        this.author = author;
        this.radio = radio;
    }

    @Override
    public String toString() {
        return "AuthorInformation{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", authorSex='" + authorSex + '\'' +
                ", authorBirth='" + authorBirth + '\'' +
                ", authorSchool='" + authorSchool + '\'' +
                ", authorAddress='" + authorAddress + '\'' +
                ", authorNumber=" + authorNumber +
                ", authorCounty='" + authorCounty + '\'' +
                ", authorProvince='" + authorProvince + '\'' +
                ", authorProfession='" + authorProfession + '\'' +
                ", authorEducation='" + authorEducation + '\'' +
                ", authorPhone='" + authorPhone + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", authorIntroduce='" + authorIntroduce + '\'' +
                ", draftInformationId=" + draftInformationId +
                ", author='" + author + '\'' +
                ", radio=" + radio +
                '}';
    }
}