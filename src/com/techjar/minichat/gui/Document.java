/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.minichat.gui;

import java.awt.Toolkit;
import javax.swing.text.*;

/**
 *
 * @author Techjar
 */
public class Document {
    public class Limited extends PlainDocument {
        private int limit;
        private boolean beep;
        

        Limited(int limit) {
            this(limit, false);
        }
        
        Limited(int limit, boolean beep) {
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
}
