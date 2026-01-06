package com.example.graduationproject.model;

import java.util.List;

public class AuditMenu {
    private int auditMenuId;
    private String path;
    private String name;
    private String label;
    private String icon;
    private String url;
    private int signal;
    private List<AuditMenu> children;

    public int getAuditMenuId() {
        return auditMenuId;
    }

    public void setAuditMenuId(int auditMenuId) {
        this.auditMenuId = auditMenuId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public List<AuditMenu> getChildren() {
        return children;
    }

    public void setChildren(List<AuditMenu> children) {
        this.children = children;
    }
}
