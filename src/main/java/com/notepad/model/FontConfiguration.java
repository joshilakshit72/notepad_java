package com.notepad.model;

import java.awt.Font;

public class FontConfiguration {
    public static final String[] FONT_FAMILIES = {
        "Agency FB", "Antiqua", "Architect", "Arial", "Calibri", 
        "Comic Sans", "Courier", "Cursive", "Impact", "Serif"
    };
    
    public static final String[] FONT_SIZES = {
        "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70"
    };
    
    public static final int[] FONT_STYLES = {Font.PLAIN, Font.BOLD, Font.ITALIC};
    public static final String[] FONT_STYLE_NAMES = {"PLAIN", "BOLD", "ITALIC"};
    
    private String fontFamily = "SAN_SERIF";
    private int fontSize = 20;
    private int fontStyle = Font.PLAIN;

    public Font createFont() {
        return new Font(fontFamily, fontStyle, fontSize);
    }

    // Getters and Setters
    public String getFontFamily() { return fontFamily; }
    public void setFontFamily(String fontFamily) { this.fontFamily = fontFamily; }
    
    public int getFontSize() { return fontSize; }
    public void setFontSize(int fontSize) { this.fontSize = fontSize; }
    
    public int getFontStyle() { return fontStyle; }
    public void setFontStyle(int fontStyle) { this.fontStyle = fontStyle; }

}
