package com.example.graduationproject.model;

import java.util.List;

public class EditorMenu {
    private int editorMenuId;
    private String path;
    private String name;
    private String label;
    private String icon;
    private String url;
    private int signal;
    private List<EditorMenu> children;

    public int getEditorMenuId() {
        return editorMenuId;
    }

    public void setEditorMenuId(int editorMenuId) {
        this.editorMenuId = editorMenuId;
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

    public List<EditorMenu> getChildren() {
        return children;
    }

    public void setChildren(List<EditorMenu> children) {
        this.children = children;
    }
}
