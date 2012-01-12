/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.minichat.gui.util;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Techjar
 */
public class LimitDocument extends PlainDocument {
    private int limit;
    private boolean beep;


    public LimitDocument(int limit) {
        this(limit, false);
    }

    public LimitDocument(int limit, boolean beep) {
        super();
        this.limit = limit;
        this.beep = beep;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;

        if (getLength() + str.length() <= limit)
            super.insertString(offset, str, attr);
        else if (beep) Toolkit.getDefaultToolkit().beep();
    }
}
