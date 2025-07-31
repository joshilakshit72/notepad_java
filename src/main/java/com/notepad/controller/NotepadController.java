package com.notepad.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.JOptionPane;

import com.notepad.listener.TextChangeListener;
import com.notepad.model.FontConfiguration;
import com.notepad.model.TextEditorModel;
import com.notepad.service.ClipboardOperations;
import com.notepad.service.FileOperations;
import com.notepad.ui.FindReplaceDialog;
import com.notepad.ui.FontSelectionDialog;
import com.notepad.ui.NotepadView;

public class NotepadController implements ActionListener, TextChangeListener {
    private NotepadView view;
    private final TextEditorModel model;
    private final FileOperations fileOperations;
    private final ClipboardOperations clipboardOperations;
    private final FontConfiguration fontConfig;

    public NotepadController() {
        this.model = new TextEditorModel();
        this.fileOperations = new FileOperations();
        this.clipboardOperations = new ClipboardOperations();
        this.fontConfig = new FontConfiguration();
    }

    public void initializeView() {
        this.view = new NotepadView(fontConfig, (ActionListener) this, (TextChangeListener) this);
        updateStatusBar();
    }

    private void updateStatusBar() {
        view.updateStatusBar(model.getLength(), model.getLineCount());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "New" :  
                handleNew();
                break;
            case "Open" :
                handleOpen();
                break;
            case "Save": 
                handleSave();
                break;
            case "Print" :
                handlePrint();
                break;
            case "Exit" : 
                handleExit();
                break;
            case "Copy" : 
                handleCopy();
                break;
            case "Paste" : 
                handlePaste();
                break;
            case "Cut" : 
                handleCut();
                break;
            case "Select All" : 
                handleSelectAll();
                break;
            case "Font Family" : 
                handleFontFamily();
                break;
            case "Font Style" : 
                handleFontStyle();
                break;
            case "Font Size" : 
                handleFontSize();
                break;
            case "Find":
                handleFind();
                break;
            case "Replace" :
                handleReplace();
                break;
        }
    }

    @Override
    public void onTextChanged(String content) {
        model.setContent(content);
        updateStatusBar();
    }

    private void handleNew() {
        view.clearText();
        model.setContent("");
        model.setModified(false);
    }

    private void handleOpen() {
        String content = fileOperations.openFile(view.getFrame());
        if (content != null) {
            view.setText(content);
            model.setContent(content);
            model.setModified(false);
        }
    }

    private void handleSave() {
        boolean saved = fileOperations.saveFile(view.getFrame(), view.getTextContent());
        if (saved) {
            model.setModified(false);
        }
    }

    private void handlePrint() {
        try {
            view.printText();
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(view.getFrame(), 
                "Print failed: " + e.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleExit() {
        if (model.isModified()) {
            int option = JOptionPane.showConfirmDialog(view.getFrame(),
                "Do you want to save changes?", "Save Changes", JOptionPane.YES_NO_CANCEL_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                handleSave();
            } else if (option == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        System.exit(0);
    }

    private void handleCopy() {
        String selectedText = view.getSelectedText();
        clipboardOperations.copy(selectedText);
    }

    private void handlePaste() {
        String content = clipboardOperations.paste();
        view.insertText(content);
    }

    private void handleCut() {
        String selectedText = view.getSelectedText();
        clipboardOperations.cut(selectedText);
        view.replaceSelectedText("");
    }

    private void handleSelectAll() {
        view.selectAllText();
    }

    private void handleFontFamily() {
        String fontFamily = FontSelectionDialog.selectFontFamily(view.getFrame());
        if (fontFamily != null) {
            fontConfig.setFontFamily(fontFamily);
            view.updateFont(fontConfig.createFont());
        }
    }

    private void handleFontStyle() {
        Integer fontStyle = FontSelectionDialog.selectFontStyle(view.getFrame());
        if (fontStyle != null) {
            fontConfig.setFontStyle(fontStyle);
            view.updateFont(fontConfig.createFont());
        }
    }

    private void handleFontSize() {
        Integer fontSize = FontSelectionDialog.selectFontSize(view.getFrame());
        if (fontSize != null) {
            fontConfig.setFontSize(fontSize);
            view.updateFont(fontConfig.createFont());
        }
    }

    private void handleFind(){
        FindReplaceDialog.showFindDialog(view.getFrame(), view.getTextArea());
    }

    private void handleReplace(){
        FindReplaceDialog.showReplaceDialog(view.getFrame(), view.getTextArea());
    }

    public void show() {
        view.setVisible(true);
    }
}
