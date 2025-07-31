package com.notepad.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBarFactory {
    public static JMenuBar createMenuBar(ActionListener listener) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu(listener));
        menuBar.add(createEditMenu(listener));
        menuBar.add(createFormatMenu(listener));
        menuBar.add(createViewMenu(listener));
        return menuBar;
    }

    private static JMenu createViewMenu(ActionListener listener){
        JMenu fileMenu = new JMenu("View");

        fileMenu.add(createMenuItem("Find", KeyEvent.VK_F, ActionEvent.CTRL_MASK , listener));
        fileMenu.add(createMenuItem("Replace", KeyEvent.VK_H, ActionEvent.CTRL_MASK , listener));

        return fileMenu;
    }

    private static JMenu createFileMenu(ActionListener listener) {
        JMenu fileMenu = new JMenu("File");

        fileMenu.add(createMenuItem("New", KeyEvent.VK_N, ActionEvent.CTRL_MASK, listener));
        fileMenu.add(createMenuItem("Open", KeyEvent.VK_O, ActionEvent.CTRL_MASK, listener));
        fileMenu.add(createMenuItem("Save", KeyEvent.VK_S, ActionEvent.CTRL_MASK, listener));
        fileMenu.add(createMenuItem("Print", KeyEvent.VK_P, ActionEvent.CTRL_MASK, listener));
        fileMenu.addSeparator();
        fileMenu.add(createMenuItem("Exit", KeyEvent.VK_ESCAPE, 0, listener));

        return fileMenu;
    }

    private static JMenu createEditMenu(ActionListener listener) {
        JMenu editMenu = new JMenu("Edit");

        editMenu.add(createMenuItem("Copy", KeyEvent.VK_C, ActionEvent.CTRL_MASK, listener));
        editMenu.add(createMenuItem("Paste", KeyEvent.VK_V, ActionEvent.CTRL_MASK, listener));
        editMenu.add(createMenuItem("Cut", KeyEvent.VK_X, ActionEvent.CTRL_MASK, listener));
        editMenu.addSeparator();
        editMenu.add(createMenuItem("Select All", KeyEvent.VK_A, ActionEvent.CTRL_MASK, listener));

        return editMenu;
    }

    private static JMenu createFormatMenu(ActionListener listener) {
        JMenu formatMenu = new JMenu("Format");

        formatMenu.add(createMenuItem("Font Family", 0, 0, listener));
        formatMenu.add(createMenuItem("Font Style", 0, 0, listener));
        formatMenu.add(createMenuItem("Font Size", 0, 0, listener));

        return formatMenu;
    }

    private static JMenuItem createMenuItem(String text, int keyCode, int modifiers, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.setActionCommand(text); // ensures the event carries the correct name
        if (keyCode != 0) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(keyCode, modifiers));
        }
        menuItem.addActionListener(listener); // <- directly attach listener
        return menuItem;
    }
}
