package dev.arrokoth.phicreator.editor.controls;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class JNumberInput extends JTextField {
    public JNumberInput() {
        super();
        setKeyLayout();
    }
    
    public JNumberInput(String text) {
        super(text);
        setKeyLayout();
    }

    public JNumberInput(int columns) {
        super(columns);
        setKeyLayout();
    }

    public JNumberInput(String text, int columns) {
        super(text, columns);
        setKeyLayout();
    }

    public JNumberInput(Document doc, String text, int columns) {
        super(doc, text, columns);
        setKeyLayout();
    }

    private void setKeyLayout() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String key = ".0123456789" + (char) 8;
                if (key.indexOf(e.getKeyChar()) < 0) {
                    e.consume();
                }
            }
        });
    }
}
