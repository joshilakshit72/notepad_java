package com.notepad.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileOperations {
    private static final String DEFAULT_DIRECTORY = "C:/";
    private static final String FILE_EXTENSION = "txt";
    private static final String FILE_DESCRIPTION = "Only .txt files";

    public String openFile(JFrame parent) {
        JFileChooser chooser = createFileChooser();
        int result = chooser.showOpenDialog(parent);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            return readFileContent(file);
        }
        return null;
    }

    public boolean saveFile(JFrame parent, String content) {
        JFileChooser saveDialog = new JFileChooser();
        saveDialog.setApproveButtonText("Save");
        
        int actionDialog = saveDialog.showSaveDialog(parent);
        if (actionDialog != JFileChooser.APPROVE_OPTION) {
            return false;
        }

        File fileName = new File(saveDialog.getSelectedFile() + "." + FILE_EXTENSION);
        return writeFileContent(fileName, content);
    }

    private JFileChooser createFileChooser() {
        JFileChooser chooser = new JFileChooser(DEFAULT_DIRECTORY);
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter restrict = new FileNameExtensionFilter(FILE_DESCRIPTION, FILE_EXTENSION);
        chooser.addChoosableFileFilter(restrict);
        return chooser;
    }

    private String readFileContent(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
        return content.toString();
    }

    private boolean writeFileContent(File file, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            return false;
        }
    }
}
