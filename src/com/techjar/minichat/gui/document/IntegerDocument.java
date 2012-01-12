/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.minichat.gui.document;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Techjar
 */
public class IntegerDocument extends PlainDocument {
    private int min, max;
    private boolean beep;


    public IntegerDocument(int min, int max) {
        this(min, max, false);
    }

    public IntegerDocument(int min, int max, boolean beep) {
        super();
        this.min = min;
        this.max = max;
        this.beep = beep;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;

        int val = 0;
        try { val = Integer.parseInt(str); }
        catch (NumberFormatException ex) {
            if (beep) Toolkit.getDefaultToolkit().beep();
            return;
        }
        if (val < min || val > max) {
            if (beep) Toolkit.getDefaultToolkit().beep();
            return;
        }
        super.insertString(offset, str, attr);
    }
}
