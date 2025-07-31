package com.notepad.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.notepad.listener.TextChangeListener;
import com.notepad.model.FontConfiguration;

public class NotepadView {
    private final JFrame frame;
    private final JTextArea textArea;
    private final JLabel statusLabel;
    private final TextChangeListener textChangeListener;

    public NotepadView(FontConfiguration fontConfig, ActionListener actionListener, TextChangeListener textChangeListener) {
        this.frame = new JFrame("Notepad");
        this.textArea = createTextArea(fontConfig);
        this.statusLabel = new JLabel();
        
        initializeUI(actionListener);
        
        this.textChangeListener = textChangeListener;
        setupDocumentListener();
    }

    private JTextArea createTextArea(FontConfiguration fontConfig) {
        JTextArea area = new JTextArea();
        area.setFont(fontConfig.createFont());
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        return area;
    }

    private void initializeUI(ActionListener actionListener) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(980, 480);
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = MenuBarFactory.createMenuBar(actionListener);
        frame.setJMenuBar(menuBar);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(statusLabel);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.SOUTH);
    }

    private void setupDocumentListener() {
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (textChangeListener != null) {
                    textChangeListener.onTextChanged(textArea.getText());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (textChangeListener != null) {
                    textChangeListener.onTextChanged(textArea.getText());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (textChangeListener != null) {
                    textChangeListener.onTextChanged(textArea.getText());
                }
            }
        });
    }

    public JFrame getFrame() { return frame; }

    public JTextArea getTextArea() { return textArea ;}

    public void setVisible(boolean visible) { frame.setVisible(visible); }

    public String getTextContent() { return textArea.getText(); }

    public void setText(String text) { textArea.setText(text); }

    public void clearText() { textArea.setText(""); }

    public String getSelectedText() { return textArea.getSelectedText(); }

    public void insertText(String text) {
        if (text != null) {
            textArea.insert(text, textArea.getCaretPosition());
        }
    }

    public void replaceSelectedText(String replacement) {
        textArea.replaceRange(replacement, textArea.getSelectionStart(), textArea.getSelectionEnd());
    }

    public void selectAllText() { textArea.selectAll(); }

    public void updateFont(Font font) { textArea.setFont(font); }

    public void updateStatusBar(int length, int lineCount) {
        statusLabel.setText("Length: " + length + " Lines: " + lineCount);
    }

    public void printText() throws PrinterException {
        textArea.print();
    }
}
