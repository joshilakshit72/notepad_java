package com.notepad.model;

public class TextEditorModel {
    private String content = "";
    private boolean isModified = false;

    public String getContent() { return content; }
    
    public void setContent(String content) {
        this.content = content;
        this.isModified = true;
    }
    
    public boolean isModified() { return isModified; }
    
    public void setModified(boolean modified) { this.isModified = modified; }
    
    public int getLength() { return content.length(); }
    
    public int getLineCount() {
        if (content.isEmpty()) return 1;
        return content.split("\n").length;
    }
}
