package com.notepad.service;

public class ClipboardOperations {
    private String clipboardContent = "";

    public void copy(String selectedText) {
        if (selectedText != null) {
            clipboardContent = selectedText;
        }
    }

    public String paste() {
        return clipboardContent;
    }

    public void cut(String selectedText) {
        copy(selectedText);
    }
}
