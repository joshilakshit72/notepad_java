package com.notepad.ui;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import com.notepad.model.FontConfiguration;

public class FontSelectionDialog {
    public static String selectFontFamily(JFrame parent) {
        JList<String> fontList = new JList<>(FontConfiguration.FONT_FAMILIES);
        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        int result = JOptionPane.showConfirmDialog(
            parent, fontList, "Choose Font Family", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION && fontList.getSelectedValue() != null) {
            return fontList.getSelectedValue();
        }
        return null;
    }
    
    public static Integer selectFontStyle(JFrame parent) {
        JList<String> styleList = new JList<>(FontConfiguration.FONT_STYLE_NAMES);
        styleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        int result = JOptionPane.showConfirmDialog(
            parent, styleList, "Choose Font Style", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION && styleList.getSelectedIndex() != -1) {
            return FontConfiguration.FONT_STYLES[styleList.getSelectedIndex()];
        }
        return null;
    }
    
    public static Integer selectFontSize(JFrame parent) {
        JList<String> sizeList = new JList<>(FontConfiguration.FONT_SIZES);
        sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        int result = JOptionPane.showConfirmDialog(
            parent, sizeList, "Choose Font Size", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION && sizeList.getSelectedValue() != null) {
            return Integer.valueOf(sizeList.getSelectedValue());
        }
        return null;
    }
}
