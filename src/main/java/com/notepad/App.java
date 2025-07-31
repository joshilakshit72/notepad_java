package com.notepad;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.notepad.controller.NotepadController;


public class App 
{
    public static void main(String[] args) {
        initializeLookAndFeel();

        SwingUtilities.invokeLater(() -> {
            try {
                NotepadController controller = new NotepadController();
                controller.initializeView();
                controller.show();
            } catch (Exception e) {
                System.err.println("Error starting application: " + e.getMessage());
                System.exit(1);
            }
        });
    }

    private static void initializeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            System.out.println("Using L&F: " + UIManager.getLookAndFeel().getName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.err.println("System L&F failed: " + e.getMessage());
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                System.out.println("Using fallback L&F: " + UIManager.getLookAndFeel().getName());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
                System.err.println("Fallback L&F also failed.");
            }
        }
    }
}
