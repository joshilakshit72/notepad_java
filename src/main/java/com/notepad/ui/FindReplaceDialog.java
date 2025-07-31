package com.notepad.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FindReplaceDialog {
    public static void showFindDialog(JFrame parent, JTextArea textArea) {
        JDialog dialog = new JDialog(parent, "Find", false);
        dialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel findLabel = new JLabel("Find:");
        JTextField findField = new JTextField(20);
        JButton findButton = new JButton("Find");

        inputPanel.add(findLabel);
        inputPanel.add(findField);
        inputPanel.add(findButton);
        dialog.add(inputPanel, BorderLayout.CENTER);

        findButton.addActionListener((ActionEvent e) -> {
            String textToFind = findField.getText();
            String textContent = textArea.getText();
            int index = textContent.indexOf(textToFind);
            if (index != -1) {
                textArea.requestFocus();
                textArea.select(index, index + textToFind.length());
            }
        });

        dialog.setSize(350, 100);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    public static void showReplaceDialog(JFrame parent, JTextArea textArea) {
        JDialog dialog = new JDialog(parent, "Replace", false);
        dialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel findLabel = new JLabel("Find:");
        JTextField findField = new JTextField(10);
        JLabel replaceLabel = new JLabel("Replace with:");
        JTextField replaceField = new JTextField(10);
        JButton replaceButton = new JButton("Replace");

        inputPanel.add(findLabel);
        inputPanel.add(findField);
        inputPanel.add(replaceLabel);
        inputPanel.add(replaceField);
        inputPanel.add(replaceButton);

        dialog.add(inputPanel, BorderLayout.CENTER);

        replaceButton.addActionListener((ActionEvent e) -> {
            String findText = findField.getText();
            String replaceText = replaceField.getText();
            String content = textArea.getText();
            int index = content.indexOf(findText);
            if (index != -1) {
                textArea.select(index, index + findText.length());
                textArea.replaceSelection(replaceText);
            }
        });

        dialog.setSize(450, 120);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
